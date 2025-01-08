package com.laptrinhweb.demo.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name =  "categoryName")
	private String categoryName;
	@Column(name = "categoryQuantity")
	private Integer categoryQuantity;
	@OneToMany(mappedBy ="category")
	private Set<Product> products;



    public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	public Integer getCategoryQuantity() {
		return categoryQuantity;
	}



	public void setCategoryQuantity(Integer categoryQuantity) {
		this.categoryQuantity = categoryQuantity;
	}


	public Set<Product> getProducts() {
		return products;
	}



	public void setProducts(Set<Product> products) {
		this.products = products;
	}



	public Category() {
	}

	public Category(Integer id ,String categoryName, Integer categoryQuantity,Set<Product> products) {
        this.id = id;
		this.categoryName = categoryName;
        this.categoryQuantity = categoryQuantity;
		this.products = products;
    }
	
}
