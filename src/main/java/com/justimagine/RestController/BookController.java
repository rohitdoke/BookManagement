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
import com.justimagine.Service.IBookService;
import com.justimagine.Service.IGenerateBookReportService;

@RestController
@RequestMapping("/book")
class BookController {
	
	private static final Logger log=LoggerFactory.getLogger(BookController.class);
	private final IBookService bookService;
	
	private final IGenerateBookReportService bookReport;

	
	public BookController(IBookService bookService, IGenerateBookReportService bookReport) {
		
	
		super();
		this.bookService = bookService;
		this.bookReport = bookReport;
	}
	@PostMapping("/save")
	public ResponseEntity<String>saveBook( @RequestBody   Book b)
	{
		
		log.info("saveBook Method is called");
		try {
			
			
			
					bookService.saveBook(b);
					log.info("saveBook method is executed");
					return ResponseEntity.status(HttpStatus.CREATED).body("Record Saved Successfully");
					
		} catch (Exception e) {
			log.info("Book are Not saved");
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Record not Saved ");
		}
		
		
		
	}
	@PostMapping("/saveAll")
	public ResponseEntity<String> saveAllBook(@RequestBody List<Book> b)
	{
		log.info("saveAllBook method is Called");
		
		try {
			
			bookService.saveAllBook(b);
			log.info("saveAllBook method is executed");
			return ResponseEntity.status(HttpStatus.CREATED).body("All list of Record Saves Successfully");
		} catch (Exception e) {
			log.info(" Books aren't saved exception occured");
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("All Record Not Saved.");
		}
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable  Integer id)

	{
		log.info("getBookById method is Called");

		try {
			
			
				
			 Book book = bookService.getBookById(id);
			
			 
			 
			 log.info("getBookById method is executed");
			return ResponseEntity.ok(book);
			
			
		} catch ( Exception e) {
			
			log.info("Book are not found By this id");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		
		
	}
	@GetMapping("/getAll")
	public ResponseEntity<List<Book>> getAllBook()
	{
		log.info("getAllBook method is called");
		try {
			
			List<Book> allBook = bookService.getAllBook();
			log.info("getAllBook  method is executed");
			return ResponseEntity.ok(allBook);
					
		} catch (Exception e) {
			
			log.info("No Book present in  the database");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	
		
	}
	
	@PutMapping("/update") 
	public ResponseEntity<String> UpdateBookById(@RequestBody Book b)
	{log.info("UpdateBookbyId method is called");
		
		try {
			bookService.UpdateBookById(b);
			log.info("UpdateBookById method is executed");
			return ResponseEntity.ok("Record Updated");
		} catch (Exception e) {
	
			log.info("Book id is not found or Incorrect Details");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found ");
		}
		
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletedById(@PathVariable(name="id") Integer id)
	{
		log.info(	"deleteById  method is called");
		
		try {
			bookService.deleteById(id);
			log.info("deleteById method is executed");
			return ResponseEntity.ok("Record deleted ID="+ id);
			
		} catch (Exception e) {
			
			log.info("Book id not found to delete");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found By this ID="+id);
		}
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String > delelteAllBook()
	{
		log.info("deleteAll method is calld");
		try {
			
			bookService.deleteAllBook();
			log.info("deleteAll method is executed");
			return ResponseEntity.ok("all Records are Deleted");
			
		} catch (Exception e) {
			
			log.info("Books are not present to delete");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Records are not prensent");
		}
		
		
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
	
	
	
	
	
}
