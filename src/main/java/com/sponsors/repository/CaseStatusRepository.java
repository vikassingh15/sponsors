package com.sponsors.repository;

import com.sponsors.model.CaseStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseStatusRepository extends CrudRepository<CaseStatus, Long> {
}