package telran.java29.book.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import telran.java29.book.model.Book;

public interface BookRepository{
	List<Book> findByAuthorsName(String name);

	Stream<Book> findByPublisherName(String publisherName);

	boolean existsById(Long isbn);

	Optional<Book> findById(long isbn);

	Book save(Book book);

	void delete(Book book);
}