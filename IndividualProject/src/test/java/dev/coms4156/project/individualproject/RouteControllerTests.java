package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import dev.coms4156.project.individualproject.controller.RouteController;
import dev.coms4156.project.individualproject.model.Book;
import dev.coms4156.project.individualproject.service.MockApiService;
import java.util.ArrayList;
import java.util.List;
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

  @Test
  public void testAddCopySuccess() {
    List<Book> books = new ArrayList<>();
    Book testBook = new Book("Test Book", 123);
    books.add(testBook);
    when(mockApiService.getBooks()).thenReturn(books);
    
    ResponseEntity<?> response = routeController.addCopy(123);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(testBook, response.getBody());
  }


  @Test
  public void getRecommendationsSuccess() {
    MockApiService service = new MockApiService();
    RouteController controller = new RouteController(service);

    ResponseEntity<?> response = controller.getRecommendation();
    assertEquals(HttpStatus.OK, response.getStatusCode());

    List<Book> recommendedBooks = (List<Book>) response.getBody();
    assertEquals(10, recommendedBooks.size());
  }

  @Test
  public void testCheckoutBookSuccess() {
    MockApiService service = new MockApiService();
    RouteController controller = new RouteController(service);

    ResponseEntity<?> response = controller.checkoutBook(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Book checkoutBook = (Book) response.getBody();
    assertEquals(2, checkoutBook.getAmountOfTimesCheckedOut());
    assertEquals(0, checkoutBook.getCopiesAvailable());
    assertEquals(1, checkoutBook.getReturnDates().size());
  }

  @Test
  public void testCheckoutBookNotFound() {
    MockApiService service = new MockApiService();
    RouteController controller = new RouteController(service);
    ResponseEntity<?> response = controller.checkoutBook(99);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Book not found", response.getBody());
  }

  @Test
  public void testCheckoutBookNoCopiesAvailable() {
    MockApiService service = new MockApiService();
    RouteController controller = new RouteController(service);
    ResponseEntity<?> response = controller.checkoutBook(45);
    Book firstCheckout = (Book) response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());

    assertEquals(0, firstCheckout.getCopiesAvailable());
    assertEquals(2, firstCheckout.getAmountOfTimesCheckedOut());
    assertEquals(4, firstCheckout.getReturnDates().size()); 
    ResponseEntity<?> secondCheckout = controller.checkoutBook(45);
    assertEquals(HttpStatus.BAD_REQUEST, secondCheckout.getStatusCode());
    assertEquals("Cannot checkout book", secondCheckout.getBody());
  }
}