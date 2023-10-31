package com.example.bookstore.service;

import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

        public List<Book> getAllBooks() {
            return bookRepository.findAll();
        }

        public Book getBookById(Long id) {
            Optional<Book> book = bookRepository.findById(id);
            return book.orElse(null); // Return null if book is not found
        }

        public Book createBook(Book book) {
            return bookRepository.save(book);
        }

        public Book updateBook(Long id, Book updatedBook) {
            if (bookRepository.existsById(id)) {
                updatedBook.setId(id); // Ensure the ID is set correctly
                return bookRepository.save(updatedBook);
            } else {
                return null; // Handle the case where the book doesn't exist
            }
        }

        public void deleteBook(Long id) {
            bookRepository.deleteById(id);
        }
    }

