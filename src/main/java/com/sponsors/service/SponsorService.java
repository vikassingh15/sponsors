package com.sponsors.service;

import com.sponsors.dto.CandidateDto;
import com.sponsors.dto.StaffDto;
import com.sponsors.model.Sponsor;

import java.util.List;

public interface SponsorService {
    Sponsor getById(long id);

    List<StaffDto> getStaffDetails(long id);

    List<CandidateDto> searchCandiatesByOrganizationName(String search);

    void save(Sponsor sponsor);

    List<Sponsor> getAll();

    List<StaffDto> searchStaffByOrganizationName(String search);

    List<CandidateDto> getCandidatesForSponsor(long id);
}
