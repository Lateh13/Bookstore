package fi.hakulinen.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.hakulinen.bookstore.domain.Book;
import fi.hakulinen.bookstore.domain.BookRepository;
import fi.hakulinen.bookstore.domain.Category;
import fi.hakulinen.bookstore.domain.CategoryRepository;



@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository) {
		return (args) -> {
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("Fiction"));
			crepository.save(new Category("Novel"));
			
			repository.save(new Book("George R.R. Martin", "A Game of Thrones", 1986, "9780553593716", 19.99, crepository.findByName("Fantasy").get(0)));
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
