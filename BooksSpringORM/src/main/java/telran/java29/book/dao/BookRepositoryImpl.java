package telran.java29.book.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import telran.java29.book.model.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Book> findByAuthorsName(String name) {
		TypedQuery<Long> query = em.createQuery("select b.isbn from Book b join b.authors a where a.name=?1", Long.class);
		query.setParameter(1, name);
		//List<String> bookNames = query.getResultList();
		return query.getResultList().stream().map(isbn -> em.find(Book.class, isbn)).collect(Collectors.toList());
		
	}

	@Override
	public Stream<Book> findByPublisherName(String publisherName) {
		TypedQuery<Long> query = em.createQuery("select b.isbn from Book b join b.publisher p where p.publisherName=?1", Long.class);
		query.setParameter(1, publisherName);
		return query.getResultStream().map(isbn -> em.find(Book.class, isbn));
	}

	@Override
	public boolean existsById(Long isbn) {
		return em.find(Book.class, isbn) != null;
	}

	@Override
	public Optional<Book> findById(long isbn) {
		Book book = em.find(Book.class, isbn);
		return Optional.ofNullable(book);
	}

	@Override
	@Transactional
	public Book save(Book book) {
		em.persist(book);
		return book;
	}

	@Override
	public void delete(Book book) {
		em.remove(book);
	}

}