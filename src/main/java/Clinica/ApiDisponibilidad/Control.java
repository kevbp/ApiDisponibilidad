package Clinica.ApiDisponibilidad;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disponibilidad")
public class Control {

    @Autowired
    private Servicio serv;

    @PostMapping("/grabar")
    public Disponibilidad grabar(@RequestBody Disponibilidad dis) {
        return serv.grabar(dis);
    }

    @GetMapping("/buscar/{id}")
    public Disponibilidad buscar(@PathVariable Long id) {
        return serv.buscar(id);
    }

    @GetMapping("/listar")
    public List<Disponibilidad> listar() {
        return serv.listar();
    }

    @PutMapping("/actualizar/{id}")
    public Disponibilidad actualizar(@PathVariable Long id, @RequestBody Disponibilidad dis) {
        return serv.actualizar(id, dis);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        serv.eliminar(id);
    }
}
