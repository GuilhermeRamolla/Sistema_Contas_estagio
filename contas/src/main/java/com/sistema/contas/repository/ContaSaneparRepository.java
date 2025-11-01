package com.sistema.contas.repository;

import com.sistema.contas.model.ContaSanepar;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContaSaneparRepository extends JpaRepository<ContaSanepar,Integer>{

    List<ContaSanepar> findByLocalSaneparId(int idLocalSanepar);
}
