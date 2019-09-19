package telran.java29.book.dao;

import java.util.Optional;

import telran.java29.book.model.Author;

public interface AuthorRepository{
	Optional<Author> findById(String authorName);

	Author save(Author author);

	void deleteById(String authorName);

}