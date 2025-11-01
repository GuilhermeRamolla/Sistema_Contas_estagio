package com.sistema.contas.repository;

import com.sistema.contas.model.ContaSercomtel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContaSercomtelRepository extends JpaRepository<ContaSercomtel,Integer>{

    List<ContaSercomtel> findByLocalSercomtelId(int idLocalSercomtel);
}
