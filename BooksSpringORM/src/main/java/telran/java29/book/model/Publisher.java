package telran.java29.book.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"publisherName"})
public class Publisher{
	
	@Id
	String publisherName;
	@OneToMany (mappedBy = "publisher", cascade = CascadeType.ALL)
	Set<Book> books;
	
	public Publisher(String publisherName) {
		this.publisherName = publisherName;
	}

	
	
	
	

}
