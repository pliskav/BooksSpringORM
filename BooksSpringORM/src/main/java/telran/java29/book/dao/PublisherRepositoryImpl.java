package telran.java29.book.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import telran.java29.book.model.Publisher;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<String> findPublisherByAuthor(String author) {
		TypedQuery<String> query = em.createQuery("select distinct p.publisherName from Book b join b.publisher p join b.authors a where a.name=?1", String.class);
		query.setParameter(1, author);
		return query.getResultList();
	}

	@Override
	public Optional<Publisher> findById(String publisherName) {	
		return Optional.ofNullable(em.find(Publisher.class, publisherName));
	}

	@Override
	@Transactional
	public Publisher save(Publisher publisher) {
		em.persist(publisher);
		return publisher;
	}

}