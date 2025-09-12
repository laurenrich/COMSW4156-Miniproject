package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.coms4156.project.individualproject.model.Book;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This class contains the unit tests for the Book class.
 */
@SpringBootTest
public class BookUnitTests {

  public static Book book;

  @BeforeAll
  public static void setUpBookForTesting() {
    book = new Book("When Breath Becomes Air", 0);
  }

  @Test
  public void equalsBothAreTheSameTest() {
    Book cmpBook = book;
    assertEquals(cmpBook, book);
  }

  @Test
  public void hasMultipleAuthorsWhenNoAuthors() {
    book.setAuthors(new ArrayList<>());
    assertFalse(book.hasMultipleAuthors());
  }

  @Test
  public void hasMultipleAuthorsWhenOneAuthor() {
    List<String> authors = new ArrayList<>();
    authors.add("John Doe");
    book.setAuthors(authors);
    assertFalse(book.hasMultipleAuthors());
  }

  @Test
  public void hasMultipleAuthorsWhenMultipleAuthors() {
    List<String> authors = new ArrayList<>();
    authors.add("John Doe");
    authors.add("Jane Doe");
    book.setAuthors(authors);
    assertTrue(book.hasMultipleAuthors());
  }

  @Test 
  public void deleteCopyWhenCopiesAvailable() {
    Book testBook = new Book("Test", 1);
    testBook.setTotalCopies(2);
    assertTrue(testBook.deleteCopy());
    assertEquals(1, testBook.getTotalCopies());
    assertEquals(0, testBook.getCopiesAvailable());
  }

  @Test
  public void deleteCopyWhenNoCopiesAvailable() {
    Book testBook = new Book("Test", 1);
    testBook.setTotalCopies(5);
    testBook.checkoutCopy();
    assertFalse(testBook.deleteCopy());
    assertEquals(5, testBook.getTotalCopies());
    assertEquals(0, testBook.getCopiesAvailable());
  }

  @Test
  public void deleteCopyWhenNoTotalCopies() {
    Book testBook = new Book("Test", 1);
    testBook.setTotalCopies(0);
    assertFalse(testBook.deleteCopy());
    assertEquals(0, testBook.getTotalCopies());
  }

  @Test
  public void checkoutCopyWhenCopiesAvailable() {
    Book testBook = new Book("Test", 1);
    String dueDate = testBook.checkoutCopy();
    assertNotNull(dueDate);
  }

  @Test
  public void checkoutCopyWhenNoCopiesAvailable() {
    Book testBook = new Book("Test", 1);
    testBook.checkoutCopy();
    String dueDate = testBook.checkoutCopy();
    assertNull(dueDate);
  }



  
}
