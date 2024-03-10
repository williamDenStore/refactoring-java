package tests;

import main.Customer;
import main.MovieRental;
import main.RentalInfo;
import org.junit.Test;

import java.util.List;
import static org.junit.Assert.assertEquals;
public class IntegrationTests {
    @Test
    public void matrixAndMail(){
        String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";
        List<MovieRental> rentedMovies = List.of(new MovieRental("You've Got Mail", 3), new MovieRental("Matrix", 1));
        Customer customer = new Customer("C. U. Stomer", rentedMovies);
        String result = new RentalInfo().statement(customer);
        assertEquals(expected, result);
    }
    @Test
    public void allMovies(){
        String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\n\tCars\t1.5\n\tFast & Furious X\t9.0\nAmount owed is 16.0\nYou earned 5 frequent points\n";
        List<MovieRental> rentedMovies = List.of(new MovieRental("You've Got Mail", 3),
                new MovieRental("Matrix", 1)
                ,new MovieRental("Cars", 2)
                ,new MovieRental("Fast & Furious X", 3));
        Customer customer = new Customer("C. U. Stomer", rentedMovies);
        String result = new RentalInfo().statement(customer);
        assertEquals(expected, result);
    }
    @Test
    public void noMovies(){
        String expected = "Rental Record for C. U. Stomer\nAmount owed is 0.0\nYou earned 0 frequent points\n";
        List<MovieRental> rentedMovies = List.of();
        Customer customer = new Customer("C. U. Stomer", rentedMovies);
        String result = new RentalInfo().statement(customer);
        assertEquals(expected, result);
    }
    @Test
    public void invalidMovieName(){
        String expected = "Rental Record for C. U. Stomer\nAmount owed is 0.0\nYou earned 0 frequent points\n";
        List<MovieRental> rentedMovies = List.of(new MovieRental("a",3));
        Customer customer = new Customer("C. U. Stomer", rentedMovies);
        String result = new RentalInfo().statement(customer);
        assertEquals(expected, result);
    }
}
