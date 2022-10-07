package com.cg.farming.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Supplier {
	@Id
	@GeneratedValue
	private int supplierId;	
	@NotBlank
	@Size(max = 120)
	private String name;	
	@NotBlank
	@Size(max = 120)
	private String address;
	@NotBlank
	@Size(max = 12)
	private String phoneNo;

	// OneToOne unidirectional relationship 
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private User user;
	
}
