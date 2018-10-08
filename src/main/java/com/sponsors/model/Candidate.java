package com.sponsors.model;


import com.sponsors.dto.CandidateDto;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "candidates")
public class Candidate extends AuditBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "sponsored_by")
    private long sponsoredBy;

    @Column(name = "internal_reference")
    private String internalReference;

    @Column(name = "assigned_veeting_officer")
    private Long assignedVeetingOfficer;

    @Column(name = "comments")
    private String comments;

    @Column(name = "case_status_id")
    private long caseStatusId;

    @Column(name = "activation_code")
    private String activationCode;

    @Column(name = "activations_code_expiration")
    private Timestamp activationsCodeExpiration;

    @Column(name = "interview_audio")
    private String interviewAudio;

    @Column(name = "interview_transcript")
    private String interviewTranscript;

    @Column(name = "interview_sar")
    private String interviewSar;


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "sponsored_by", referencedColumnName = "id", insertable = false, updatable = false)
    private Sponsor sponsor;

    @OneToOne
    @JoinColumn(name = "case_status_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CaseStatus caseStatus;

    @OneToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id", insertable = false, updatable = false)
    private Staff createdByStaff;

    @OneToOne
    @JoinColumn(name = "updated_by", referencedColumnName = "id", insertable = false, updatable = false)
    private Staff updatedByStaff;

    @OneToOne
    @JoinColumn(name = "assigned_veeting_officer", referencedColumnName = "id", insertable = false, updatable = false)
    private Staff assignedVeetingOfficerStaff;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSponsoredBy() {
        return sponsoredBy;
    }

    public void setSponsoredBy(long sponsoredBy) {
        this.sponsoredBy = sponsoredBy;
    }

    public String getInternalReference() {
        return internalReference;
    }

    public void setInternalReference(String internalReference) {
        this.internalReference = internalReference;
    }

    public Long getAssignedVeetingOfficer() {
        return assignedVeetingOfficer;
    }

    public void setAssignedVeetingOfficer(Long assignedVeetingOfficer) {
        this.assignedVeetingOfficer = assignedVeetingOfficer;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public long getCaseStatusId() {
        return caseStatusId;
    }

    public void setCaseStatusId(long caseStatusId) {
        this.caseStatusId = caseStatusId;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Timestamp getActivationsCodeExpiration() {
        return activationsCodeExpiration;
    }

    public void setActivationsCodeExpiration(Timestamp activationsCodeExpiration) {
        this.activationsCodeExpiration = activationsCodeExpiration;
    }

    public String getInterviewAudio() {
        return interviewAudio;
    }

    public void setInterviewAudio(String interviewAudio) {
        this.interviewAudio = interviewAudio;
    }

    public String getInterviewTranscript() {
        return interviewTranscript;
    }

    public void setInterviewTranscript(String interviewTranscript) {
        this.interviewTranscript = interviewTranscript;
    }

    public String getInterviewSar() {
        return interviewSar;
    }

    public void setInterviewSar(String interviewSar) {
        this.interviewSar = interviewSar;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public CaseStatus getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(CaseStatus caseStatus) {
        this.caseStatus = caseStatus;
    }

    public Staff getCreatedByStaff() {
        return createdByStaff;
    }

    public void setCreatedByStaff(Staff createdByStaff) {
        this.createdByStaff = createdByStaff;
    }

    public Staff getUpdatedByStaff() {
        return updatedByStaff;
    }

    public void setUpdatedByStaff(Staff updatedByStaff) {
        this.updatedByStaff = updatedByStaff;
    }

    public Staff getAssignedVeetingOfficerStaff() {
        return assignedVeetingOfficerStaff;
    }

    public void setAssignedVeetingOfficerStaff(Staff assignedVeetingOfficerStaff) {
        this.assignedVeetingOfficerStaff = assignedVeetingOfficerStaff;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", userId=" + userId +
                ", sponsoredBy=" + sponsoredBy +
                ", internalReference='" + internalReference + '\'' +
                ", assignedVeetingOfficer=" + assignedVeetingOfficer +
                ", comments=" + comments +
                ", caseStatusId=" + caseStatusId +
                ", activationCode='" + activationCode + '\'' +
                ", activationsCodeExpiration=" + activationsCodeExpiration +
                ", interviewAudio='" + interviewAudio + '\'' +
                ", interviewTranscript='" + interviewTranscript + '\'' +
                ", interviewSar='" + interviewSar + '\'' +
                '}';
    }

    public CandidateDto createCandidateDto() {
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setEmail(getUser().getEmail());
        candidateDto.setPhoneNumber(getUser().getPhone());
        candidateDto.setName(getUser().getName());
        candidateDto.setAssignedOfficer(assignedVeetingOfficerStaff!= null ?
                                        assignedVeetingOfficerStaff.getUser().getEmail()
                                        : null);

        candidateDto.setCaseStatus(getCaseStatus().getStatus());
        candidateDto.setComments(getComments());
        candidateDto.setExpirationDate(getActivationsCodeExpiration());
        candidateDto.setSponsoredBy(getSponsor().getOrganizationName());
        candidateDto.setSponsoredContact(getSponsor().getPhoneNumber());
        return candidateDto;
    }
}
