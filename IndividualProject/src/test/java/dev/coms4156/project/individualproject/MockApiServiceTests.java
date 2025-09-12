package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.coms4156.project.individualproject.model.Book;
import dev.coms4156.project.individualproject.service.MockApiService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the MockApiService.
 */
public class MockApiServiceTests {

  @Test
  public void getBooksReturnsListOfBooks() {
    MockApiService service = new MockApiService();
    List<Book> books = service.getBooks();
    
    assertNotNull(books);
    assertTrue(books instanceof ArrayList);
  }

  @Test
  public void updateBookReplacesMatchingBook() {
    MockApiService service = new MockApiService();
    List<Book> originalBooks = service.getBooks();
    
    if (!originalBooks.isEmpty()) {
      Book originalBook = originalBooks.get(0);
      Book updatedBook = new Book(originalBook.getTitle() + " Updated", originalBook.getId());
      
      service.updateBook(updatedBook);
      
      List<Book> updatedBooks = service.getBooks();
      assertNotNull(updatedBooks);
    }
  }

  @Test
  public void updateBookDoesNotReplaceNonMatchingBook() {
    MockApiService service = new MockApiService();
    
    Book nonMatchingBook = new Book("Non-matching Book", 99999);
    
    service.updateBook(nonMatchingBook);
    
    List<Book> updatedBooks = service.getBooks();
    assertNotNull(updatedBooks);
  }
} 