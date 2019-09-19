package telran.java29.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.java29.book.dto.AuthorDto;
import telran.java29.book.dto.BookDto;
import telran.java29.book.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@PostMapping("/book")
	public boolean addBook(@RequestBody BookDto bookDto) {
		return bookService.addBook(bookDto);
	}
	
	@DeleteMapping("/book/{isbn}")
	public BookDto removeBook(@PathVariable long isbn) {
		return bookService.removeBook(isbn);
	}
	
	@GetMapping("/book/{isbn}")
	public BookDto getBook(@PathVariable long isbn) {
		return bookService.getBookByIsbn(isbn);
	}
	
	@GetMapping("/books/{author}")
	public Iterable<BookDto> getBooksByAuthor(@PathVariable String author) {
		return bookService.getBooksByAuthor(author);
	}
	
	@GetMapping("/publishers/{author}")
	public Iterable<String> getPublishersByAuthor(@PathVariable String author) {
		return bookService.getPublishersByAuthor(author);
	}
	
	@GetMapping("/books/publisher/{publisherName}")
	public Iterable<BookDto> getBooksByPublisher(@PathVariable String publisherName) {
		return bookService.getBooksByPublisher(publisherName);
	}
	
	@GetMapping("/authors/book/{isbn}")
	public Iterable<AuthorDto> getBookAuthors(@PathVariable long isbn) {
		return bookService.getBookAuthors(isbn);
	}
	
	@DeleteMapping("/author/{author}")
	public AuthorDto removeAuthor(@PathVariable String author) {
		return bookService.removeAuthor(author);
	}

}