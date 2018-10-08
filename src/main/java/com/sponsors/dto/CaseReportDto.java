package com.sponsors.dto;

public class CaseReportDto {

    public long candidateId;
    private long timeTaken;
    private String status;
    private CandidateDto candidateDto;

    public CaseReportDto(long candidateId, long timeTaken, String  status, CandidateDto candidateDto){
        this.candidateId = candidateId;
        this.timeTaken = timeTaken;
        this.status = status;
        this.candidateDto = candidateDto;
    }

    public long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(long candidateId) {
        this.candidateId = candidateId;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CandidateDto getCandidateDto() {
        return candidateDto;
    }
}
