package com.erod.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.erod.spring5webapp.model.Author;
import com.erod.spring5webapp.model.Book;
import com.erod.spring5webapp.repositories.AuthorRepository;
import com.erod.spring5webapp.repositories.BookRepository;
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{
	
	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	
	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initData();
	}

	private void initData() {
		//Eric
		Author eric = new Author("Eric", "Evans");
		Book ddd = new Book("Domain Driven Design", "1234","Harper Collins");
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);
		
		authorRepository.save(eric);
		bookRepository.save(ddd);
		
		//Rod
		Author rod = new Author("Rod", "Johnson");
		Book noEJB = new Book("J2EE Development without EJB", "23444","Worx");
		rod.getBooks().add(noEJB);
		
		authorRepository.save(rod);
		bookRepository.save(noEJB);
	}

}
