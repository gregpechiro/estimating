package com.cagnosolutions.cei.company.appname.domain;

/**
 * Created by Scott Cagno on 7/7/14.
 * Copyright Cagno Solutions. All rights reserved.
 */

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String company;
    private String contact;
    private String email;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="customer")
    private Collection<Job> jobs = new ArrayList<Job>();

    public Customer() {
    }

    public String toString() {
        return String.format(
                "<ol class=\"breadcrumb\">" +
					"<li><a href=\"/app/list/customer\">Customers</a></li>"+
                    "<li class=\"active\">%s</li>" +
                "</ol>",
                company);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Collection<Job> jobs) {
        this.jobs = jobs;
    }

	public int getJobCount() {
		return jobs.size();
	}
}