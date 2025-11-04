package se.yrgo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import se.yrgo.domain.Book;

import java.time.LocalDate;
import java.util.*;

@Controller
public class BookController {
    private List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book("The Serpent and the Wings of Night", "Carissa Broadbent", "Fantasy"));
        books.add(new Book("The Luminaries", "Susan Dennard", "Fantasy"));
        books.add(new Book("The Monster of Elendhaven", "Jennifer Giesbrecht", "Horror"));
        books.add(new Book("Mistborn: The Final Empire", "Brandon Sanderson","Fantasy"));
        books.add(new Book("Gone Girl", "Gillian Flynn", "Thriller"));
        books.add(new Book("The Girl with the Dragon Tattoo", "Stieg Larsson", "Thriller"));
        books.add(new Book("It","Stephen King", "Horror"));
        books.add(new Book("Bird Box","Josh Malerman", "Horror"));
    }

    @GetMapping("/home")
    public ModelAndView showHome() {
        LocalDate date = LocalDate.now();
        return new ModelAndView("home", "date", date);
    }

    @GetMapping("/books")
    public ModelAndView showBookList() {
        return new ModelAndView("booklist", "books", books);
    }

    @GetMapping("/genre")
    public ModelAndView showGenre(@RequestParam(required = false) String type) {
        List<Book> sortedBooks = new ArrayList<>();
        if(type != null) {
            for(Book b : books) {
                if(b.getGenre().equalsIgnoreCase(type)) {
                    sortedBooks.add(b);
                }
            }
            return new ModelAndView("genre", "genre", sortedBooks);
        }else {
            Set<String> setGenre = new TreeSet<>();
            for(Book b : books) {
                setGenre.add(b.getGenre());
            }
            return new ModelAndView("genre", "books", setGenre);
        }
    }
}
