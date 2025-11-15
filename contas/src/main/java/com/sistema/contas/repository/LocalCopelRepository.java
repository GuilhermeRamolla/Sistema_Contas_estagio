package com.sistema.contas.repository;

import com.sistema.contas.model.LocalCopel;
import org.springframework.data.repository.CrudRepository;

public interface LocalCopelRepository extends CrudRepository<LocalCopel,Integer>{
    LocalCopel findByIdCopel(String idCopel);
}