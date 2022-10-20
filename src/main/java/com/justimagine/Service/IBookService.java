package com.justimagine.Service;

import java.util.List;

import com.justimagine.Entity.Book;

public interface IBookService {
	
	
	
	public String saveBook(Book b);
	
	public String saveAllBook(List<Book> b);
	
	public Book getBookById(Integer id);
	
	public List<Book> getAllBook();
	
	public String UpdateBookById(Book b);
	
	public String deleteById(Integer Id);
	
	public String deleteAllBook();
	
	

}
