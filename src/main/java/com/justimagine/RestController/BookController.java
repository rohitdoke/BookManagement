package com.justimagine.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

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
		try {
			
					bookService.saveBook(b);
					return ResponseEntity.status(HttpStatus.CREATED).body("Record Saved Successfully");
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Record not Saved ");
		}
		
		
		
	}
	@PostMapping("/saveAll")
	public ResponseEntity<String> saveAllBook(@RequestBody List<Book> b)
	{
		
		
		try {
			
			bookService.saveAllBook(b);
			return ResponseEntity.status(HttpStatus.CREATED).body("All list of Record Saves Successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("All Record Not Saved.");
		}
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable  Integer id)

	{

		try {
			
			
				
			 Book book = bookService.getBookById(id);
			
			 
			 
			
			return ResponseEntity.ok(book);
			
			
		} catch ( Exception e) {
			
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		
		
	}
	@GetMapping("/getAll")
	public ResponseEntity<List<Book>> getAllBook()
	{
		try {
			
			List<Book> allBook = bookService.getAllBook();
			return ResponseEntity.ok(allBook);
					
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	
		
	}
	
	@PutMapping("/update") 
	public ResponseEntity<String> UpdateBookById(@RequestBody Book b)
	{
		
		try {
			bookService.UpdateBookById(b);
			
			return ResponseEntity.ok("Record Updated");
		} catch (Exception e) {
	
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found ");
		}
		
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletedById(@PathVariable(name="id") Integer id)
	{
		
		
		try {
			bookService.deleteById(id);
			return ResponseEntity.ok("Record deleted ID="+ id);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found By this ID="+id);
		}
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String > delelteAllBook()
	{
	
		try {
			
			bookService.deleteAllBook();
			
			return ResponseEntity.ok("all Records are Deleted");
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Records are not prensent");
		}
		
		
	}
	
	@RequestMapping(value = "/bookreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
            
    public ResponseEntity<InputStreamResource> BookReport() {

		try {
		
        List<Book> books = (List<Book>) bookService.getAllBook();

        ByteArrayInputStream bis = bookReport.BookReport(books);

        
        
        return ResponseEntity
                .ok().header("Disposition", "BookReport.pdf").contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
		}
		catch(Exception e)
		{
	
			return ResponseEntity.notFound().build();
			
		}
          
    }
	
	
	
	
	
}
