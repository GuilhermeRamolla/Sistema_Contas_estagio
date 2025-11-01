package com.sistema.contas.repository;

import com.sistema.contas.model.ContaCopel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContaCopelRepository extends JpaRepository<ContaCopel,Integer>{

    List<ContaCopel> findByLocalCopelId(int idLocalCopel);
}
