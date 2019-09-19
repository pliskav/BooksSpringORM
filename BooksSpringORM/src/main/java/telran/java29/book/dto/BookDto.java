package telran.java29.book.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
	long isbn;
	String title;
	Set<AuthorDto> authors;
	String publisher;


}
