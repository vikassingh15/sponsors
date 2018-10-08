package com.sponsors.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sponsors")
public class Sponsor {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "organization_name")
  private String organizationName;

  @Column(name = "country_code")
  private String countryCode;

  @Column(name = "phone_number")
  private long phoneNumber;

  private String address;

  @OneToMany(mappedBy = "sponsor")
  private List<Staff> staff;

  @OneToMany(mappedBy = "sponsor")
  private List<Candidate> candidates;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getOrganizationName() {
    return organizationName;
  }

  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName;
  }


  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }


  public long getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(long phoneNumber) {
    this.phoneNumber = phoneNumber;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<Staff> getStaff() {
    return staff;
  }

  public void setStaff(List<Staff> staff) {
    this.staff = staff;
  }

  public List<Candidate> getCandidates() {
    return candidates;
  }

  public void setCandidates(List<Candidate> candidates) {
    this.candidates = candidates;
  }
}
