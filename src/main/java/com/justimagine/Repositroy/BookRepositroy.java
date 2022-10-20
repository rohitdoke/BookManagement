package com.justimagine.Repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.justimagine.Entity.Book;
@Repository
public interface BookRepositroy extends JpaRepository<Book, Integer> {
	
	
	
	
	
	

}
