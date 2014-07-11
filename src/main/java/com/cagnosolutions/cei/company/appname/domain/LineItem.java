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

	//private Long itemId;
    private Integer quantity;

	@ManyToOne
	@JoinColumn(name="room")
	private Room room;

	@ManyToOne
	@JoinColumn(name="item")
	private Item item;

	private Double lineItemTotal;

    public LineItem() {
    }

	public LineItem(Item item, Integer quantity) {
		//this.itemId = itemId;
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

	/*public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}*/

	public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Double getTotal() {
		return lineItemTotal;
	}

	public void setTotal() {
		this.lineItemTotal = item.getPrice() * quantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}