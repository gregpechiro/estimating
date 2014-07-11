package com.cagnosolutions.cei.company.appname.domain;

/**
 * Created by Scott Cagno on 7/7/14.
 * Copyright Cagno Solutions. All rights reserved.
 */

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String notes;

	@ManyToOne
	@JoinColumn(name="job")
	private Job job;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="room")
    private Collection<LineItem> items = new ArrayList<>();

    private double roomTotal;
    private int labor;

    public Room() {
    }

    public String toString() {
        return String.format(
            "<ol class=\"breadcrumb\">" +
				"<li><a href=\"/app/list/customer\">Customers</a></li>"+
                "<li><a href=\"/app/view/customer/%d\">%s</a></li>" +
                "<li><a href=\"/app/view/job/%d\">Job %d</a></li>" +
                "<li class=\"active\">%s</li>" +
            "</ol>",
            job.getCustomer().getId(), job.getCustomer().getCompany(),
            job.getId(), job.getId(),
            name);
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Collection<LineItem> getItems() {
        return items;
    }

    public void setItems(Collection<LineItem> items) {
        this.items = items;
    }

    public double getRoomTotal() {
        return roomTotal;
    }

    public void setRoomTotal(double roomTotal) {
        this.roomTotal = roomTotal;
    }

    public int getLabor() {
        return labor;
    }

    public void setLabor(int labor) {
        this.labor = labor;
    }
}