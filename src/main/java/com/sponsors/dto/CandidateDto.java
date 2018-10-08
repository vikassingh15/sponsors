package com.sponsors.dto;

import java.util.Date;

public class CandidateDto extends UserDto {

    private String comments;
    private String caseStatus;
    private String assignedOfficer;
    private Date expirationDate;
    private String sponsoredBy;
    private long sponsoredContact;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getAssignedOfficer() {
        return assignedOfficer;
    }

    public void setAssignedOfficer(String assignedOfficer) {
        this.assignedOfficer = assignedOfficer;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSponsoredBy() {
        return sponsoredBy;
    }

    public void setSponsoredBy(String sponsoredBy) {
        this.sponsoredBy = sponsoredBy;
    }

    public long getSponsoredContact() {
        return sponsoredContact;
    }

    public void setSponsoredContact(long sponsoredContact) {
        this.sponsoredContact = sponsoredContact;
    }
}
