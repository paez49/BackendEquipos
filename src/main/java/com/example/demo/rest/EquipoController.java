package com.example.demo.rest;


import com.example.demo.domain.Equipo;
import com.example.demo.dto.EquipoDTO;
import com.example.demo.repository.EquipoRepository;
import com.example.demo.service.EquipoService;
import com.example.demo.util.ConverterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/equipos")
public class EquipoController {
    @Autowired
    EquipoRepository equipoRepository;
    @Autowired
    EquipoService equipoService;
    @Autowired
    private ConverterDTO converterDTO;
    //Obtener todos los equipos
    @PostMapping("/create/{idUsuario}")
    public ResponseEntity<EquipoDTO> crearEquipo(@RequestBody EquipoDTO equipoDTO,@PathVariable Long idUsuario) {
        System.out.println(equipoDTO);
        Equipo equipo = converterDTO.toEntity(equipoDTO);
        equipo = equipoService.save(equipo,idUsuario);
        equipoDTO = converterDTO.toDto(equipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipoDTO);
    }
    @GetMapping("/{idEquipo}")
    public ResponseEntity<EquipoDTO> obtenerEquipo(@PathVariable Long idEquipo) {
        Equipo equipo = equipoService.findById(idEquipo);
        if (equipo == null) {
            return ResponseEntity.notFound().build();
        }
        EquipoDTO equipoDTO = converterDTO.toDto(equipo);
        return ResponseEntity.ok(equipoDTO);
    }
    //Traer equipos en los que no hace parte el usuario
    // Lista equipos -> Presentacion
    @GetMapping("/{idUsuario}/equipos_disponibles")
    public List<EquipoDTO> obtenerEquiposDisponibles(@PathVariable Long idUsuario) {
        List<Equipo> equipos = equipoService.buscarEquiposDisponibles(idUsuario);
        List<EquipoDTO> equipoDTO = converterDTO.toDtoListEquipos(equipos);
        return equipoDTO;
    }
    @GetMapping("/{idUsuario}/equipos_participe")
    public List<EquipoDTO> obtenerEquiposParticipe(@PathVariable Long idUsuario) {
        Set<Equipo> equipos =  equipoService.buscarEquiposParticipe(idUsuario);
        List<EquipoDTO> equipoDTO = converterDTO.toDtoSetEquipos(equipos);
        return equipoDTO;
    }
    @DeleteMapping("/delete/{idEquipo}")
    public ResponseEntity<?> eliminarEquipo(@PathVariable Long idEquipo) {
        return equipoService.delete(idEquipo);
    }
    @PutMapping("/{idEquipo}")
    public ResponseEntity<EquipoDTO> actualizarEquipo(@PathVariable Long idEquipo, @RequestBody EquipoDTO equipoDTO) {
        Equipo equipo = equipoService.findById(idEquipo);
        if (equipo == null) {
            return ResponseEntity.notFound().build();
        }
        if(equipoDTO.getNombreEquipo() != null){
            equipo.setNombreEquipo(equipoDTO.getNombreEquipo());
        }
        if (equipoDTO.getSiglas() != null){
            equipo.setSiglas(equipoDTO.getSiglas());
        }

        equipo = equipoService.save(equipo); // Guarda los cambios en la base de datos

        equipoDTO = converterDTO.toDto(equipo);
        return ResponseEntity.ok(equipoDTO);
    }
}
