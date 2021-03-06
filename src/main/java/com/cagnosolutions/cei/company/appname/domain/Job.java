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
    @JoinColumn(name="customer")
    private Customer customer;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="job")
    private Collection<Room> rooms = new ArrayList<Room>();
    private double jobTotal;
	private Short status;
	private String po;

    public Job() {
		this.po = "PO_"+String.valueOf(System.currentTimeMillis()).substring(6,12);
    }

    public String toString() {
        return String.format(
            "<ol class=\"breadcrumb\">" +
				"<li><a href=\"/app/list/customer\">Customers</a></li>"+
                "<li><a href=\"/app/view/customer/%d\">%s</a></li>" +
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

    public void setJobTotal(Double hourlyRate) {
		Double total = 0.0;
        for (Room next : rooms) {
			next.setRoomTotal(hourlyRate);
			total = total + next.getRoomTotal();
		}
		this.jobTotal = total;
    }

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}

	public int getRoomCount() {
		return rooms.size();
	}

	public void nextStatus() {
		this.status++;
	}
}