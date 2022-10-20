package com.justimagine.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.justimagine.Entity.Book;

public interface IGenerateBookReportService {
	
	
	public ByteArrayInputStream BookReport(List<Book> books);

}
