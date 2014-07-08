package com.cagnosolutions.cei.company.appname.domain;

/**
 * Created by Scott Cagno on 7/7/14.
 * Copyright Cagno Solutions. All rights reserved.
 */

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private String notes;
    private Integer timeStamp;

    @ManyToOne
    @JoinColumn(name="jobs")
    private Customer customer;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="rooms")
    private Collection<Room> rooms = new ArrayList<Room>();
    private double jobTotal;
	private Short status;

    public Job() {
    }

    public String toString() {
        return String.format(
            "<ol class=\"breadcrumb\">" +
				"<li><a href=\"/list/customer\">Customers</a></li>"+
                "<li><a href=\"/view/customer/%d\">%s</a></li>" +
                "<li class=\"active\">Job %d</li>" +
            "</ol>",
            getCustomer().getId(), getCustomer().getCompany(),
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

    public Integer getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Integer timeStamp) {
        this.timeStamp = timeStamp;
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

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public int getRoomCount() {
		return rooms.size();
	}
}