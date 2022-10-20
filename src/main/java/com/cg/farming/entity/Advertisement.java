package com.cg.farming.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="advertisement")
public class Advertisement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int advid;
	@NotBlank(message = "cropType is mandatory")
	@Size(max = 30)
	private String cropType;
	private int quantity;
	@NotBlank(message = "weight is mandatory")
	@Size(max = 20)
	private String weight;
	@NotBlank(message = "price is mandatory")
	@Size(max = 30)
	private String price;
	private String name;
	private boolean status=false;
	
}
