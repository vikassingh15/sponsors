package com.sponsors.model;

import sun.dc.pr.PRError;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "candidate_status_change_log")
public class CaseStatusChangeLog {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "start_time")
  private Timestamp startTime;

  @Column(name = "end_time")
  private Timestamp endTime;

  @Column(name = "case_status_id")
  private long caseStatusId;

  @Column(name = "candidate_id")
  private long candidateId;

  @OneToOne
  @JoinColumn(name = "case_status_id", referencedColumnName = "id", insertable = false, updatable = false)
  private CaseStatus caseStatus;

  @OneToOne
  @JoinColumn(name = "candidate_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Candidate candidate;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }

  public Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(Timestamp endTime) {
    this.endTime = endTime;
  }

  public long getCaseStatusId() {
    return caseStatusId;
  }

  public void setCaseStatusId(long caseStatusId) {
    this.caseStatusId = caseStatusId;
  }

  public long getCandidateId() {
    return candidateId;
  }

  public void setCandidateId(long candidateId) {
    this.candidateId = candidateId;
  }

  public CaseStatus getCaseStatus() {
    return caseStatus;
  }

  public void setCaseStatus(CaseStatus caseStatus) {
    this.caseStatus = caseStatus;
  }

  public Candidate getCandidate() {
    return candidate;
  }

  public void setCandidate(Candidate candidate) {
    this.candidate = candidate;
  }
}
