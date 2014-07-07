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
    private String description;
    private String notes;
    private Date dateCreated;

    @ManyToOne
    @JoinColumn(name="customer")
    private Customer customer;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="rooms")
    private Collection<Room> rooms = new ArrayList<Room>();
    private double jobTotal;

    public Job() {
    }

    public String toString() {
        return String.format(
            "<ol class=\"breadcrumb\">" +
                "<li><a href=\"/view/customer/%d\">%s</a></li>" +
                "<li class=\"active\">Job %d</li>" +
            "</ol>",
            customer.getId(), customer.getCompany(),
            id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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