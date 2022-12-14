package com.justimagine.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.justimagine.Entity.Book;
import com.justimagine.Exception.NoSuchBookExistsException;
import com.justimagine.Service.IBookService;
import com.justimagine.Service.IGenerateBookReportService;

@RestController
@RequestMapping("/book")
class BookController2 {
	
	private static final Logger log=LoggerFactory.getLogger(BookController2.class);
	private final IBookService bookService;
	
	private final IGenerateBookReportService bookReport;

	
	public BookController2(IBookService bookService, IGenerateBookReportService bookReport) {
		
	
		super();
		this.bookService = bookService;
		this.bookReport = bookReport;
	}
	@PostMapping("/save")
	public String saveBook( @RequestBody   Book b)
	{
		
		log.info("saveBook Method is called");
		Boolean flag;
		flag=(checkNullEmptyBlank(b.getName()) || checkNullEmptyBlank(b.getAuthor()));
				
				
				if(flag!=true && b.getPrice()>0)
				{
			
			
			
					bookService.saveBook(b);
					
					log.info("saveBook method is executed");
					return "Record Saved Successfully";
				}
				else
				{
					log.info("Book are Not saved");
					throw new NoSuchBookExistsException("Please Enter Correct Info ");
				}
		
			
		
		
		
	}
	@PostMapping("/saveAll")
	public String saveAllBook(@RequestBody List<Book> b)
	{
		log.info("saveAllBook method is Called");
		
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
		
			bookService.saveAllBook(b);
			
			log.info("saveAllBook method is executed");
			 return "All Book are Saved Successfully";
		}
		else
		{	
			log.info(" Books aren't saved exception occured");
			throw new NoSuchBookExistsException("Please Enter Valid Info");
		}
	}
	@GetMapping("/get/{id}")
	public Book getBookById(@PathVariable  Integer id)

	{
		log.info("getBookById method is Called");
		if(id<=0)	
		{
			log.info("this is not Valid id");
			throw new NoSuchBookExistsException("Enter Valid id");
			
		}
		else
		{	
			log.info("getBookById method is executed");
			 Book book = bookService.getBookById(id);
			 
			 return book;	 
		}
		
			
			
	
	}
	@GetMapping("/getAll")
	public List<Book> getAllBook()
	{
		log.info("getAllBook method is called");
		
			
			List<Book> allBook = bookService.getAllBook();
			log.info("getAllBook  method is executed");
			if(allBook.isEmpty())
			{
				log.info("No Book present in  the database");
				throw new NoSuchBookExistsException("Books are Not Present. ");
					
			}
			else
			{
				return allBook;
			}
			
			
	
		
	}
	
	@PutMapping("/update") 
	public String UpdateBookById(@RequestBody Book b)
	{
		Boolean flag;
		flag=(checkNullEmptyBlank(b.getName()) || checkNullEmptyBlank(b.getAuthor()));
		if(  flag !=true && b.getPrice()>0 && b.getId()>0)
		{
			
			bookService.UpdateBookById(b);
			return "book Saved Suceessfully";
		}
		else
		{
			throw new NoSuchBookExistsException("please enter Correct Info ");
			
		}	
	}
	
	
	@DeleteMapping("/delete/{id}")
	public String deletedById(@PathVariable(name="id") Integer id)
	{
		log.info(	"deleteById  method is called");
		
		
			bookService.deleteById(id);
			log.info("deleteById method is executed");
			return "Record deleted ID="+ id;
			
		
	}
	
	@DeleteMapping("/deleteAll")
	public String  delelteAllBook()
	{
		log.info("deleteAll method is calld");
		
			
			bookService.deleteAllBook();
			log.info("deleteAll method is executed");
			return "all Records are Deleted";
			
		
		
	}
	
	@RequestMapping(value = "/bookreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
            
    public ResponseEntity<InputStreamResource> BookReport() {

		log.info("BookReport  method is executed");
		try {
		
        List<Book> books = (List<Book>) bookService.getAllBook();

        ByteArrayInputStream bis = bookReport.BookReport(books);

        
        log.info("BookReport method is executed");
        return ResponseEntity
                .ok().header("Disposition", "BookReport.pdf").contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
		}
		catch(Exception e)
		{
			log.info("Books are not found generate pdf");
			return ResponseEntity.notFound().build();
			
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
