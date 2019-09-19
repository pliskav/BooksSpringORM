package telran.java29.book.dao;

import java.util.List;
import java.util.Optional;

import telran.java29.book.model.Publisher;

public interface PublisherRepository{
	List<String> findPublisherByAuthor(String author);

	Optional<Publisher> findById(String publisherName);

	Publisher save(Publisher publisher);
}