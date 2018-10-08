package com.sponsors.repository;

import com.sponsors.model.Sponsor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SponsorRepository extends CrudRepository<Sponsor, Long> {

    @Query("SELECT sponsor FROM Sponsor sponsor JOIN FETCH sponsor.staff WHERE sponsor.id = :id")
    Optional<Sponsor> getSponsorAndStaffDetails(@Param("id") long id);

    @Query("SELECT sponsor FROM Sponsor sponsor JOIN FETCH sponsor.staff WHERE sponsor.organizationName LIKE %:search% ")
    List<Sponsor> getSponsorsAndStaffByOrganizationName(@Param("search") String search);

    @Query("SELECT sponsor FROM Sponsor sponsor JOIN FETCH sponsor.candidates WHERE sponsor.id = :id")
    Optional<Sponsor> getSponsorAndCandidateDetails(@Param("id") long id);

    @Query("SELECT sponsor FROM Sponsor sponsor JOIN FETCH sponsor.candidates WHERE sponsor.organizationName LIKE %:search% ")
    List<Sponsor> getSponsorsAndCandidatesByOrganizationName(@Param("search") String search);
}