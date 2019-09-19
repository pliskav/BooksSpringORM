package telran.java29.book.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.java29.book.model.Book;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherDto {
	String publisherName;
	//@JsonIgnore
	//Set<BookDto> books;
	
	Set<Book> books;
	
	
	

}
