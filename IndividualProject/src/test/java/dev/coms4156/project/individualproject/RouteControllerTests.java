package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import dev.coms4156.project.individualproject.controller.RouteController;
import dev.coms4156.project.individualproject.model.Book;
import dev.coms4156.project.individualproject.service.MockApiService;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * This class contains tests for the RouteController.
 */
public class RouteControllerTests {

  @Mock
  private MockApiService mockApiService;

  private RouteController routeController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    routeController = new RouteController(mockApiService);
  }

  @Test
  public void getBookReturnsBookWhenFound() {
    List<Book> books = new ArrayList<>();
    Book testBook = new Book("Test Book", 123);
    books.add(testBook);
    when(mockApiService.getBooks()).thenReturn(books);

    ResponseEntity<?> response = routeController.getBook(123);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(testBook, response.getBody());
  }

  @Test
  public void getBookReturnsNotFoundWhenBookNotExists() {
    List<Book> books = new ArrayList<>();
    Book testBook = new Book("Test Book", 123);
    books.add(testBook);
    when(mockApiService.getBooks()).thenReturn(books);

    ResponseEntity<?> response = routeController.getBook(999);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Book not found.", response.getBody());
  }
}
