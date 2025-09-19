package dev.coms4156.project.individualproject.controller;

import dev.coms4156.project.individualproject.model.Book;
import dev.coms4156.project.individualproject.service.MockApiService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class defines the controller for the API. It defines the endpoints for the API.
 */
@RestController
public class RouteController {

  private final MockApiService mockApiService;

  public RouteController(MockApiService mockApiService) {
    this.mockApiService = mockApiService;
  }

  @GetMapping({"/", "/index"})
  public String index() {
    return "Welcome to the home page! In order to make an API call direct your browser"
        + "or Postman to an endpoint.";
  }

  /**
   * Returns the details of the specified book.
   *
   * @param id An {@code int} representing the unique identifier of the book to retrieve.
   *
   * @return A {@code ResponseEntity} containing either the matching {@code Book} object with an
   *         HTTP 200 response, or a message indicating that the book was not
   *         found with an HTTP 404 response.
   */
  @GetMapping({"/book/{id}"})
  public ResponseEntity<?> getBook(@PathVariable int id) {
    for (Book book : mockApiService.getBooks()) {
      if (book.getId() == id) {
        return new ResponseEntity<>(book, HttpStatus.OK);
      }
    }

    return new ResponseEntity<>("Book not found.", HttpStatus.NOT_FOUND);
  }

  /**
   * Get and return a list of all the books with available copies.
   *
   * @return A {@code ResponseEntity} containing a list of available {@code Book} objects with an
   *         HTTP 200 response if successful, or a message indicating an error occurred with an
   *         HTTP 500 response.
   */
  @PutMapping({"/books/available"})
  public ResponseEntity<?> getAvailableBooks() {
    try {
      List<Book> availableBooks = new ArrayList<>();

      for (Book book : mockApiService.getBooks()) {
        if (book.hasCopies()) {
          availableBooks.add(book);
        }
      }

      return new ResponseEntity<>(availableBooks, HttpStatus.OK);
    } catch (Exception e) {
      System.err.println(e);
      return new ResponseEntity<>("Error occurred when getting all available books",
              HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Adds a copy to the {@code} Book object if it exists.
   *
   * @param bookId An {@code Integer} representing the unique id of the book.
   * @return A {@code ResponseEntity} containing the updated {@code Book} object with an
   *         HTTP 200 response if successful or HTTP 404 if the book is not found,
   *         or a message indicating an error occurred with an HTTP 500 code.
   */
  @PatchMapping({"/book/{bookId}/add"})
  public ResponseEntity<?> addCopy(@PathVariable Integer bookId) {
    try {
      for (Book book : mockApiService.getBooks()) {
        if (bookId.equals(book.getId())) {
          book.addCopy();
          return new ResponseEntity<>(book, HttpStatus.OK);
        }
      }

      return new ResponseEntity<>("Book not found.", HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      System.err.println(e);
      return new ResponseEntity<>("Error occurred when adding a copy to the book",
              HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  /**
   * Get and return a list of 10 recommended books.
   * Half are the most popular books (highest checkout count).
   * Half are randomly selected books.
   *
   * @return A {@code ResponseEntity} containing a list of recommended {@code Book} objects with an
   *         HTTP 200 response if successful, 
   *         an HTTP 400 response if there are not enough recommended books, 
   *         and an HTTP 500 response if an error occurs
   */
  @GetMapping({"books/recommendation"}) 
  public ResponseEntity<?> getRecommendation() {
    try {
      List<Book> allBooks = mockApiService.getBooks();
      if (allBooks.size() < 10) {
        return new ResponseEntity<>("Not enough recommendedbooks", HttpStatus.BAD_REQUEST);
      }
      List<Book> books = new ArrayList<>(allBooks);
      Collections.sort(books, (a, b) -> 
          Integer.compare(b.getAmountOfTimesCheckedOut(), a.getAmountOfTimesCheckedOut()));
      
      List<Book> popularBooks = books.subList(0, 5);

      List<Book> remainingBooks = new ArrayList<>();
      for (Book book : books) {
        if (!popularBooks.contains(book)) {
          remainingBooks.add(book);
        }
      }

      Collections.shuffle(remainingBooks);
      List<Book> randomBooks = new ArrayList<>();
      for (int i = 0; i < 5; i++) {
        randomBooks.add(remainingBooks.get(i));
      }

      List<Book> recommendedBooks = new ArrayList<>();
      recommendedBooks.addAll(popularBooks);
      recommendedBooks.addAll(randomBooks);

      return new ResponseEntity<>(recommendedBooks, HttpStatus.OK);
    } catch (Exception e) {
      System.err.println(e);
      return new ResponseEntity<>("Error occurred when getting recommendations", 
                                  HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Check out a book by its ID.
   *
   * @param id The ID of the book to check out
   * @return A {@code ResponseEntity} containing the checked out book {@code Book} object with an
   *         HTTP 200 response if successful, an HTTP 400 response if the book cannot 
   *         be checked out, and HTTP 404 response if the book is not found or an HTTP 500 response 
   *         if an error occurs
   */  
  @PatchMapping("/checkout")
  public ResponseEntity<?> checkoutBook(@RequestParam Integer id) {
    try {
      Book checkoutBook = null;
      for (Book book : mockApiService.getBooks()) {
        if (id.equals(book.getId())) {
          checkoutBook = book;
          break;
        }
      }
      if (checkoutBook == null) {
        return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
      }

      String dueDate = checkoutBook.checkoutCopy();
      if (dueDate == null) {
        return new ResponseEntity<>("Cannot checkout book", HttpStatus.BAD_REQUEST);
      }
    
      return new ResponseEntity<>(checkoutBook, HttpStatus.OK);
    } catch (Exception e) {
      System.err.println(e);
      return new ResponseEntity<>("Error occurred when checking out book", 
                                  HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}



