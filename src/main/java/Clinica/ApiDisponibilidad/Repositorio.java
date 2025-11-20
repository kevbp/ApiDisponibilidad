/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Clinica.ApiDisponibilidad;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author broncake
 */
public interface Repositorio extends JpaRepository<Disponibilidad, Long>{
    
}
