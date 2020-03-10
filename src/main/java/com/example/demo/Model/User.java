package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private String userStatus;
    private Integer dayOff;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassJoin classJoin;

    @OneToMany(mappedBy = "user" , fetch = FetchType.EAGER)
    @JsonIgnore
    private List<AbsentApplication> absentApplicationList;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getDayOff() {
        return dayOff;
    }

    public void setDayOff(Integer dayOff) {
        this.dayOff = dayOff;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public ClassJoin getClassJoin() {
        return classJoin;
    }

    public void setClassJoin(ClassJoin classJoin) {
        this.classJoin = classJoin;
    }

    public List<AbsentApplication> getAbsentApplicationList() {
        return absentApplicationList;
    }

    public void setAbsentApplicationList(List<AbsentApplication> absentApplicationList) {
        this.absentApplicationList = absentApplicationList;
    }
}
