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

	@ManyToOne
	@JoinColumn(name="rooms")
	private Job job;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="room_item")
    private Collection<Item> items = new ArrayList<Item>();
    private double roomTotal;
    private int labor;

    public Room() {
    }

    public String toString() {
        return "Id: " + id + ", Entity: Room";
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

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
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