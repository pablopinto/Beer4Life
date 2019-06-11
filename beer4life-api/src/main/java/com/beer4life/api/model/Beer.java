/*
 *
 *  Copyright (c) 2018-2020 Givantha Kalansuriya, This source is a part of
 *   Staxrt - sample application source code.
 *   http://staxrt.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.beer4life.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "beer")
@EntityListeners(AuditingEntityListener.class)
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Brewery", nullable = false)
    private String Brewery;

    @Column(name = "City", nullable = false)
    private String City;

    @Column(name = "Name", nullable = false)
    private String Name;

    @Column(name = "Abv", nullable = true)
    private String Abv;
    
    @Column(name = "Ibu", nullable = true)
    private String Ibu;
    
    @Column(name = "Srm", nullable = true)
    private String Srm;
    
    @Column(name = "Tags", nullable = true)
    private String Tags;
    


    public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getBrewery() {
		return Brewery;
	}



	public void setBrewery(String brewery) {
		Brewery = brewery;
	}



	public String getCity() {
		return City;
	}



	public void setCity(String city) {
		City = city;
	}



	public String getName() {
		return Name;
	}



	public void setName(String name) {
		Name = name;
	}



	public String getAbv() {
		return Abv;
	}



	public void setAbv(String abv) {
		Abv = abv;
	}



	public String getIbu() {
		return Ibu;
	}



	public void setIbu(String ibu) {
		Ibu = ibu;
	}



	public String getSrm() {
		return Srm;
	}



	public void setSrm(String srm) {
		Srm = srm;
	}



	public String getTags() {
		return Tags;
	}



	public void setTags(String tags) {
		Tags = tags;
	}



	@Override
	public String toString() {
		return "Beer id=" + id + ", Brewery=" + Brewery + ", City=" + City + ", Name=" + Name + ", Abv=" + Abv
				+ ", Ibu=" + Ibu + ", Srm=" + Srm + ", Tags=" + Tags + "";
	}



}
