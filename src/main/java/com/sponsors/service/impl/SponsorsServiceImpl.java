package com.sponsors.service.impl;

import com.sponsors.dto.CandidateDto;
import com.sponsors.dto.StaffDto;
import com.sponsors.exception.NotFoundException;
import com.sponsors.model.Sponsor;
import com.sponsors.repository.SponsorRepository;
import com.sponsors.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.sponsors.exception.NotFoundException.NotFound.SPONSOR_NOT_FOUND;

@Service
public class SponsorsServiceImpl implements SponsorService {

    @Autowired
    private SponsorRepository sponsorRepository;

    @Override
    public Sponsor getById(long id){
        return sponsorRepository.findById(id)
                .orElseThrow( () -> new NotFoundException(SPONSOR_NOT_FOUND));
    }
    @Override
    public List<StaffDto> getStaffDetails(long id){
        Sponsor sponsor = sponsorRepository.getSponsorAndStaffDetails(id)
                .orElseThrow( () -> new NotFoundException(SPONSOR_NOT_FOUND));
        return prepareStaffDtoFromSponsor(sponsor);
    }

    private List<StaffDto> prepareStaffDtoFromSponsor(Sponsor sponsor) {
        return sponsor.getStaff().stream().map( staff -> staff.createStaffDto(sponsor)).collect(Collectors.toList());
    }

    @Override
    public List<StaffDto> searchStaffByOrganizationName(String search) {
        List<StaffDto> allStaffDtos = new ArrayList<>();
        sponsorRepository.getSponsorsAndStaffByOrganizationName(search)
                .stream().forEach( sponsor -> {
            allStaffDtos.addAll(prepareStaffDtoFromSponsor(sponsor));
        });

        return allStaffDtos;
    }

    @Override
    public List<CandidateDto> getCandidatesForSponsor(long id) {
        Sponsor sponsor = sponsorRepository.getSponsorAndCandidateDetails(id)
                .orElseThrow( () -> new NotFoundException(SPONSOR_NOT_FOUND));
        return prepareCandidateDtoFromSponsor(sponsor);
    }

    @Override
    public List<CandidateDto> searchCandiatesByOrganizationName(String search) {
        List<CandidateDto> allCandidates = new ArrayList<>();
        sponsorRepository.getSponsorsAndCandidatesByOrganizationName(search)
                .stream().forEach( sponsor -> {
            allCandidates.addAll(prepareCandidateDtoFromSponsor(sponsor));
        });

        return allCandidates;
    }

    private List<CandidateDto> prepareCandidateDtoFromSponsor(Sponsor sponsor) {
        return sponsor.getCandidates().stream().map( candidate -> candidate.createCandidateDto()).collect(Collectors.toList());
    }



    @Override
    public void save(Sponsor sponsor){
        sponsorRepository.save(sponsor);
    }

    @Override
    public List<Sponsor> getAll(){
        return (List<Sponsor>) sponsorRepository.findAll();
    }

}
