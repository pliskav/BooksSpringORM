package telran.java29.book.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.java29.book.dao.AuthorRepository;
import telran.java29.book.dao.BookRepository;
import telran.java29.book.dao.PublisherRepository;
import telran.java29.book.dto.AuthorDto;
import telran.java29.book.dto.BookDto;
import telran.java29.book.model.Author;
import telran.java29.book.model.Book;
import telran.java29.book.model.Publisher;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	PublisherRepository publisherRepository;

	@Override
	@Transactional
	public boolean addBook(BookDto bookDto) {
		if(bookRepository.existsById(bookDto.getIsbn())) {
			return false;
		}
		//Publisher
		String publisherName = bookDto.getPublisher();
		Publisher publisher = 
				publisherRepository.findById(publisherName)
				.orElseGet(() -> publisherRepository.save(new Publisher(publisherName)));
		//Set<Author>
		Set<AuthorDto> authorDtos = bookDto.getAuthors();
		Set<Author> authors = authorDtos.stream()
				.map(a -> authorRepository.findById(a.getName())
						.orElseGet(() -> authorRepository.save(new Author(a.getName(), LocalDate.parse(a.getBirthDate())))))
				.collect(Collectors.toSet());		
		Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors, publisher);
		bookRepository.save(book);
		return true;
	}

	@Override
	@Transactional
	public BookDto removeBook(long isbn) {
		Book book = bookRepository.findById(isbn).orElse(null);
		if (book == null) {
			return null;
		}
		bookRepository.delete(book);
		return bookToBookDto(book);
	}

	private BookDto bookToBookDto(Book book) {
		Set<AuthorDto> authors = book.getAuthors().stream()
				.map(this::authorToAuthorDto)
				.collect(Collectors.toSet());
		return new BookDto(book.getIsbn(), book.getTitle(), authors, book.getPublisher().getPublisherName());
	}
	
	private AuthorDto authorToAuthorDto(Author author) {
		return new AuthorDto(author.getName(), author.getBirthDate().toString());
	}

	@Override
	public BookDto getBookByIsbn(long isbn) {
		Book book = bookRepository.findById(isbn).orElse(null);
		if (book == null) {
			return null;
		}
		return bookToBookDto(book);
	}

	@Override
	public Iterable<BookDto> getBooksByAuthor(String authorName) {
		Author author = authorRepository.findById(authorName).orElse(null);
		if (author == null) {
			return new ArrayList<>();
		}
		return author.getBooks().stream()
				.map(this::bookToBookDto)
				.collect(Collectors.toList());
//		return bookRepository.findByAuthorsName(authorName)
//				.stream()
//				.map(this::bookToBookDto)
//				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<BookDto> getBooksByPublisher(String publisherName) {
		return bookRepository.findByPublisherName(publisherName)
				.map(this::bookToBookDto)
				.collect(Collectors.toSet());
	}

	@Override
	public Iterable<AuthorDto> getBookAuthors(long isbn) {
		Book book = bookRepository.findById(isbn).orElse(null);
		if (book == null) {
			return null;
		}
		return book.getAuthors().stream()
				.map(this::authorToAuthorDto)
				.collect(Collectors.toSet());
	}

	@Override
	public Iterable<String> getPublishersByAuthor(String authorName) {
		return publisherRepository.findPublisherByAuthor(authorName);
	}

	@Override
	@Transactional
	public AuthorDto removeAuthor(String authorName) {
		Author author = authorRepository.findById(authorName).orElse(null); 
		if (author == null) {
			return null;
		}
		authorRepository.deleteById(authorName);
		return authorToAuthorDto(author);
	}

}