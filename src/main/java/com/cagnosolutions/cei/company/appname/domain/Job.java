package com.cagnosolutions.cei.company.appname.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private String name;

	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "job_id")
	private Set<Room> rooms;

    public Job() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Set<Room> getRooms() {
		return rooms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	public void addRoom(Room room) {
		rooms.add(room);
	}
}