package com.hackerrank.github.repository;

import com.hackerrank.github.model.Actor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActorRepository extends CrudRepository<Actor, Long> {

}
