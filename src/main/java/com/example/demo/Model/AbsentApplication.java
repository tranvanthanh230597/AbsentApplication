package com.example.demo.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "absentApplication")
@Data
public class AbsentApplication implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String reason;
    @CreationTimestamp
    private Date sentDate;
    private Date startDay;
    private Integer leaveOfAbsent;
    private String applicationStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public AbsentApplication() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Integer getLeaveOfAbsent() {
        return leaveOfAbsent;
    }

    public void setLeaveOfAbsent(Integer leaveOfAbsent) {
        this.leaveOfAbsent = leaveOfAbsent;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
