package com.cagnosolutions.cei.company.appname.domain;

/**
 * Created by Scott Cagno on 7/7/14.
 * Copyright Cagno Solutions. All rights reserved.
 */

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Date dateCreated;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="rooms")
    private Collection<Room> rooms = new ArrayList<Room>();
    private double jobTotal;

    public Job() {
    }

    public String toString() {
        return "Id: " + id + ", Entity: Job";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public double getJobTotal() {
        return jobTotal;
    }

    public void setJobTotal(double jobTotal) {
        this.jobTotal = jobTotal;
    }
}