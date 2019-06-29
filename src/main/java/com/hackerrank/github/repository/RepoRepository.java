package com.hackerrank.github.repository;

import com.hackerrank.github.model.Repo;
import org.springframework.data.repository.CrudRepository;

public interface RepoRepository extends CrudRepository<Repo,Long> {
}
