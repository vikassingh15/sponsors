package com.sponsors.model;

import javax.persistence.*;

@Entity
@Table(name = "case_status")
public class CaseStatus {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String status;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
