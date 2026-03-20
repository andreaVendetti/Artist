package it.home.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.home.models.Opera;

@Repository
public interface OperaRepository extends CrudRepository<Opera, Integer>{

}
