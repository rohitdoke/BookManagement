package com.justimagine.Service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.justimagine.Entity.Book;
import com.justimagine.Exception.NoSuchBookExistsException;
import com.justimagine.Repositroy.BookRepositroy;
@Service
public class BookServiceImpl implements IBookService {

	
	private final BookRepositroy bookRepo;
	
	
	
	public BookServiceImpl(BookRepositroy bookRepo) {
		super();
		this.bookRepo = bookRepo;
	}

	@Override
	public String saveBook(Book b) {
		
		
		
		Boolean flag;
		flag=(checkNullEmptyBlank(b.getName()) || checkNullEmptyBlank(b.getAuthor()));
				
				
				if(flag!=true && b.getPrice()>0)
				{
					
					
					
					 bookRepo.save(b);
				
							
							
					
							return "Book Saved Successfully";
			}
			else
			{
				
				throw new NoSuchBookExistsException("Please Enter Correct Info ");
			}
	
	}

	@Override
	public String saveAllBook(List<Book> b) {
		
		
		Boolean CheckListFlag=false;
		
		for(Book b1:b)
		{	
			
			Boolean CheckNullFlag=true;
			
			
			if((CheckNullFlag==(checkNullEmptyBlank(b1.getName()))|| (CheckNullFlag==checkNullEmptyBlank(b1.getAuthor())) ||CheckNullFlag!=(b1.getPrice()>0)))
			{
				
				CheckListFlag=true;
				
			}
			
			
		}
		if(CheckListFlag!=true)
		{
			bookRepo.saveAll(b);
			
			return "All Book are Saved Successfully";
		
			
		}
		else
		{
			throw new NoSuchBookExistsException("Please Enter Valid Info");
			
		}
	}
	
	

	@Override
	public Book getBookById(Integer id) {
		
		if(id<=0)	
		{
			throw new NoSuchBookExistsException("Enter Valid id");
			
		}
		else
		{	
			return bookRepo.findById(id).orElseThrow(()->new NoSuchBookExistsException("Book is Not Present ID="+id));
		}
	}

	@Override
	public List<Book> getAllBook() {
		List<Book> bookList = bookRepo.findAll();
		
		if(bookList.isEmpty())
		{
			throw new NoSuchBookExistsException("No Book Present");
		}
		else
		{	
			return bookList ;
		}	
	}

	@Override
	public String UpdateBookById(Book b) {
		
		Boolean flag;
		flag=(checkNullEmptyBlank(b.getName()) || checkNullEmptyBlank(b.getAuthor()));
		if(  flag !=true && b.getPrice()>0 && b.getId()>0)
		{
			Book book=bookRepo.findById(b.getId()).orElse(null);
			if(book!=null)
			{
			
				book.setAuthor(b.getAuthor());
				book.setName(b.getName());
				book.setPrice(b.getPrice());
			
				bookRepo.save(book);
				
				return "Book Info Updated Successfully with ID="+b.getId();
			
			
			}
			else
			{
				throw new NoSuchBookExistsException("Book not Found with ID="+b.getId());
				
			}
		}
		else
		{
			
			
			 throw new NoSuchBookExistsException("please enter Correct Info ");
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
	
	public static Boolean checkNullEmptyBlank(String strToCheck) {  
        
    if (strToCheck == null) {  
        return true;  
        }  
        
    else if(strToCheck.isEmpty()) {  
        return true;  
        }  
        
    else if(strToCheck.isBlank()) {  
        return true;  
        }  
    else {  
        return false;  
        }  
    }

	
	
}
