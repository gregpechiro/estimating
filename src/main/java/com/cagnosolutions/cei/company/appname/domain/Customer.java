package com.cagnosolutions.cei.company.appname.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private String name;

	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name="customer_id")
	private Set<Job> jobs;

    public Customer() {
    }

	public String toString() {
		return "Customer #" + id + "'s name is " + name;
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

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	public void addJob(Job job) {
		jobs.add(job);
	}
}