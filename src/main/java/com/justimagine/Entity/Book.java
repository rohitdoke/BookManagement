package com.justimagine.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book_info")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_id")
	private Integer Id;
	
	@Column(name="book_name")
	private String Name;
	
	@Column(name="book_author")
	private String Author;
	
	
	@Column(name="book_price")
	private Integer price;
	
	public Book( ) {
		
	}
	
	public Book( String name, String author, Integer price) {
		super();
		
		Name = name;
		Author = author;
		this.price = price;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [Id=" + Id + ", Name=" + Name + ", Author=" + Author + ", price=" + price + "]";
	}
	
	
	

}
