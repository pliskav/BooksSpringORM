package telran.java29.book.service;

import telran.java29.book.dto.AuthorDto;
import telran.java29.book.dto.BookDto;

public interface BookService {
	boolean addBook(BookDto bookDto);

	BookDto removeBook(long isbn);

	BookDto getBookByIsbn(long isbn);

	Iterable<BookDto> getBooksByAuthor(String authorName);

	Iterable<BookDto> getBooksByPublisher(String publisherName);

	Iterable<AuthorDto> getBookAuthors(long isbn);

	Iterable<String> getPublishersByAuthor(String authorName);
	
	AuthorDto removeAuthor(String author);
}