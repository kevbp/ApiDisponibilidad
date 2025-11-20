/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clinica.ApiDisponibilidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author broncake
 */
@Entity
public class Disponibilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idMed;
    private Long idEsp;
    private LocalDate fec;
    private LocalTime horIni;
    private LocalTime horFin;
    private String est;

    public Disponibilidad() {
    }

    public Disponibilidad(Long id, Long idMed, Long idEsp, LocalDate fec, LocalTime horIni, LocalTime horFin, String est) {
        this.id = id;
        this.idMed = idMed;
        this.idEsp = idEsp;
        this.fec = fec;
        this.horIni = horIni;
        this.horFin = horFin;
        this.est = est;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMed() {
        return idMed;
    }

    public void setIdMed(Long idMed) {
        this.idMed = idMed;
    }

    public Long getIdEsp() {
        return idEsp;
    }

    public void setIdEsp(Long idEsp) {
        this.idEsp = idEsp;
    }

    public LocalDate getFec() {
        return fec;
    }

    public void setFec(LocalDate fec) {
        this.fec = fec;
    }

    public LocalTime getHorIni() {
        return horIni;
    }

    public void setHorIni(LocalTime horIni) {
        this.horIni = horIni;
    }

    public LocalTime getHorFin() {
        return horFin;
    }

    public void setHorFin(LocalTime horFin) {
        this.horFin = horFin;
    }

    public String getEst() {
        return est;
    }

    public void setEst(String est) {
        this.est = est;
    }

    
}
