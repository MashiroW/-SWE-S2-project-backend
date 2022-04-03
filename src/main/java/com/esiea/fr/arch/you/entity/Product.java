package com.esiea.fr.arch.you.entity;

import javax.persistence.Id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;


@Entity
@Table(name ="PRODUCT")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 0L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name ="PRODUCT_ID")
	private long id;
	
	@Column(name ="NAME")
	private String name;
	
	@Column(name ="PRICE")
	private long price;
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public long getPrice() {
		return price;
	}
	
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(long price) {
		this.price = price;
	}
		
}