package Clinica.ApiDisponibilidad;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    @Autowired
    private Repositorio repo;

    public Disponibilidad grabar(Disponibilidad dis) {
        return repo.save(dis);
    }

    public Disponibilidad buscar(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Disponibilidad> listar() {
        return repo.findAll();
    }

    public Disponibilidad actualizar(Long id, Disponibilidad dis) {
        return repo.findById(id).map(existing -> {
            existing.setIdMed(dis.getIdMed());
            existing.setIdEsp(dis.getIdEsp());
            existing.setFec(dis.getFec());
            existing.setHorIni(dis.getHorIni());
            existing.setHorFin(dis.getHorFin());
            existing.setEst(dis.getEst());
            return repo.save(existing);
        }).orElse(null);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
