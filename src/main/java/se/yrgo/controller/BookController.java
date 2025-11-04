package se.yrgo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import se.yrgo.domain.Book;

import java.util.*;

@Controller
public class BookController {
    private List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book("The Serpent and the Wings of Night", "Carissa Broadbent", "Fantasy"));
        books.add(new Book("The Luminaries", "Susan Dennard", "Fantasy"));
        books.add(new Book("The Monster of Elendhaven", "Jennifer Giesbrecht", "Horror"));
    }

    @GetMapping("/home")
    public ModelAndView showHome() {
        Date dateNow = new Date();
        return new ModelAndView("home", "date", dateNow);
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
                if(b.getGenre().equals(type)) {
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
