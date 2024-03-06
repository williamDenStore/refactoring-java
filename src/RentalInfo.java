import java.util.HashMap;

public class RentalInfo {

  public String statement(Customer customer) {
    HashMap<String, Movie> movies = generateMovieMap();

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for " + customer.name() + "\n");
    for (MovieRental movieRental : customer.rentals()) {
      double thisAmount = 0;
      String currentMovie = movieRental.movieId();
      // determine amount for each movie
      thisAmount = switch (movies.get(currentMovie).code()) {
        case "regular" -> regularMoviePrice(movieRental);
        case "new" -> newMoviePrice(movieRental);
        case "childrens" -> childrensMoviePrice(movieRental);
        default -> thisAmount;
      };
      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (movies.get(currentMovie).code().equals("new") && movieRental.days() > 2){
        frequentEnterPoints++;
      }
      //print figures for this rental
      result.append("\t").append(movies.get(currentMovie).title()).append("\t").append(thisAmount).append("\n");
      totalAmount += thisAmount;
    }
    // add footer lines
    result.append("Amount owed is ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

    return result.toString();
  }

  private static double childrensMoviePrice(MovieRental movieRental) {
    double thisAmount;
    thisAmount = 1.5;
    if (movieRental.days() > 3) {
      thisAmount = ((movieRental.days() - 3) * 1.5) + thisAmount;
    }
    return thisAmount;
  }

  private static double newMoviePrice(MovieRental movieRental) {
    double thisAmount;
    thisAmount = movieRental.days() * 3;
    return thisAmount;
  }

  private static double regularMoviePrice(MovieRental movieRental) {
    double thisAmount;
    thisAmount = 2;
    if (movieRental.days() > 2) {
      thisAmount = ((movieRental.days() - 2) * 1.5) + thisAmount;
    }
    return thisAmount;
  }

  private static HashMap<String, Movie> generateMovieMap() {
    HashMap<String, Movie> movies = new HashMap<>();
    movies.put("F001", new Movie("You've Got Mail", "regular"));
    movies.put("F002", new Movie("Matrix", "regular"));
    movies.put("F003", new Movie("Cars", "childrens"));
    movies.put("F004", new Movie("Fast & Furious X", "new"));
    return movies;
  }
}
