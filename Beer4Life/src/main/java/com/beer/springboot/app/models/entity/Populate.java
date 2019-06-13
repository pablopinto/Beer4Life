package com.beer.springboot.app.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "populate")
public class Populate implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Double input = (Math.random()*6) + 1.30; 
	BigDecimal bd = new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String tags;
	private String brewery;
	private String city;
	private String abv;
	private String srm;
	private String ibu;
	private Double price = bd.doubleValue();
	
	
	
	
	
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
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getBrewery() {
		return brewery;
	}
	public void setBrewery(String brewery) {
		this.brewery = brewery;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAbv() {
		return abv;
	}
	public void setAbv(String abv) {
		this.abv = abv;
	}
	public String getSrm() {
		return srm;
	}
	public void setSrm(String srm) {
		this.srm = srm;
	}
	public String getIbu() {
		return ibu;
	}
	public void setIbu(String ibu) {
		this.ibu = ibu;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	

}
