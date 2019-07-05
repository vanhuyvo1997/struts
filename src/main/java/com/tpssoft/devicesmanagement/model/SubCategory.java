package com.tpssoft.devicesmanagement.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subcategory")
public class SubCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idsubcategory;
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "subcategory")
	private Set<Device> devices;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idsupercategory")
	private SuperCategory supercategory;

	public int getIdsubcategory() {
		return idsubcategory;
	}

	public void setIdsubcategory(int idsubcategory) {
		this.idsubcategory = idsubcategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	public SuperCategory getSupercategory() {
		return supercategory;
	}

	public void setSupercategory(SuperCategory supercategory) {
		this.supercategory = supercategory;
	}

	public SubCategory() {
		super();

	}

}