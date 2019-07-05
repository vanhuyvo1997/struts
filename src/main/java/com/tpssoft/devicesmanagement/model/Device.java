package com.tpssoft.devicesmanagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "device")
public class Device implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "model")
	private String model;
	@Column(name = "serial")
	private String serial;
	@Column(name = "description")
	private String description;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idsubcategory")
	private SubCategory subcategory;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="username")
	private User user;



}