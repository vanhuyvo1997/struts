package com.tpssoft.devicesmanagement.model;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "supercategory")
public class SuperCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idsupercategory;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy="supercategory")
	private Set<SubCategory> subcategories;

	public int getIdsupercategory() {
		return idsupercategory;
	}

	public void setIdsupercategory(int idsupercategory) {
		this.idsupercategory = idsupercategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SubCategory> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(Set<SubCategory> subcategories) {
		this.subcategories = subcategories;
	}

}