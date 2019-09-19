package telran.java29.book.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto {
	
	String name;
	String birthDate;
	@JsonIgnore
	Set<BookDto> books;
	public AuthorDto(String name, String birthDate) {
		super();
		this.name = name;
		this.birthDate = birthDate;
	}
	
}
