package com.justimagine.Service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.justimagine.Entity.Book;
import com.justimagine.Exception.NoSuchBookExistsException;
import com.justimagine.Repositroy.BookRepositroy;

@Service
@Primary
public class BookServiceImpl2 implements IBookService {

	
	private final BookRepositroy bookRepo;
	
	
	
	public BookServiceImpl2(BookRepositroy bookRepo) {
		super();
		this.bookRepo = bookRepo;
	}

	@Override
	public String saveBook(Book b) {
		
		
		
		
					
					
					
					 bookRepo.save(b);
					 return "Book saved Successfully";
				
							
							
					
			
	
	}

	@Override
	public String saveAllBook(List<Book> b) {
		
		
		
			
			
		
		
			bookRepo.saveAll(b);
			return "All Books Saved Successfully";
			
			
		
			
		
	}
	
	

	@Override
	public Book getBookById(Integer id) {
		
		
			
			return bookRepo.findById(id).orElseThrow(()->new NoSuchBookExistsException("Book is Not Present ID="+id));
		
	}

	@Override
	public List<Book> getAllBook() {
		List<Book> bookList = bookRepo.findAll();
		
		
		return bookList;	
	}

	@Override
	public String UpdateBookById(Book b) {
		
		
			Book book=bookRepo.findById(b.getId()).orElse(null);
			
			if(book!=null)
			{
			
				
			
				bookRepo.save(book);
				
				return "Book Info Updated Successfully with ID="+b.getId();
			
			
			}
			else
			{
				throw new NoSuchBookExistsException("Book not Found with ID="+b.getId());
				
			}
		
	
	
	
		
		
	}

	@Override
	public String deleteById(Integer id) {
		
		
		 Book book = bookRepo.findById(id).orElse(null);
		 
		 if(book!=null)
		 {	 
			 bookRepo.deleteById(id);
			 return "Book Info Deleted Sucessfully with ID="+id;
		 }
		 else
		 {
			 throw new NoSuchBookExistsException("Book Not found with ID="+id);
		 }

	}
	@Override
	public String deleteAllBook() {
		
		List<Book> list = bookRepo.findAll();
		
		if(list.isEmpty())
		{
			
			throw new NoSuchBookExistsException("Books are not Present to delete");
		}
		else
		{
			bookRepo.deleteAll();
		
			return "All Books are Deleted Successfully";
		}

	}
	
	

	
	
}
