package com.cagnosolutions.cei.company.appname.domain;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */


import javax.persistence.*;

@Entity
@Table(name = "lineitem")
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="item_id")
    private Item item;

    private Integer quantity;

    public LineItem() {
    }

	public LineItem(Item item, Integer quantity) {
		this.item = item;
		this.quantity = quantity;
	}

	public String toString() {
        return "Id: " + id + ", Entity: LineItem";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}