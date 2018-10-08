package com.sponsors.model;

import com.sponsors.dto.StaffDto;

import javax.persistence.*;

@Entity
@Table(name = "staff")
public class Staff extends AuditBaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "user_id")
  private long userId;

  @Column(name = "sponsor_id")
  private long sponsorId;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
  private User user;

  @OneToOne
  @JoinColumn(name = "sponsor_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Sponsor sponsor;

  @OneToOne
  @JoinColumn(name = "created_by", referencedColumnName = "id", insertable = false, updatable = false)
  private Staff createdByStaff;

  @OneToOne
  @JoinColumn(name = "updated_by", referencedColumnName = "id", insertable = false, updatable = false)
  private Staff updatedByStaff;

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

  public long getSponsorId() {
    return sponsorId;
  }

  public void setSponsorId(long sponsorId) {
    this.sponsorId = sponsorId;
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

  public StaffDto createStaffDto(Sponsor sponsor) {
    StaffDto staffDto = new StaffDto();
    staffDto.setEmail(getUser().getEmail());
    staffDto.setName(getUser().getName());
    staffDto.setPhoneNumber(getUser().getPhone());
    staffDto.setRole(getUser().getUserRoles().get(0).getRole().getName());
    staffDto.setOrganizationName(sponsor.getOrganizationName());

    return staffDto;
  }
}
