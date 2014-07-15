package com.cagnosolutions.cei.company.appname.domain;

import javax.persistence.*;

@Entity
@Table(name = "lineitem")
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private String name;

    public LineItem() {
    }

    public Long getId() {
        return id;
    }

	public void setId(Long id) {
		this.id = id;
	}
}