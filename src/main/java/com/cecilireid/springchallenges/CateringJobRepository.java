package com.cecilireid.springchallenges;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CateringJobRepository extends CrudRepository<CateringJob, Long> {
    List<CateringJob> findAll();
}
