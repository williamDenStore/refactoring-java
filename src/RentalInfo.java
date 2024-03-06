import java.util.HashMap;

public class RentalInfo {

  public String statement(Customer customer) {
    HashMap<String, Movie> movies = new HashMap();
    movies.put("F001", new Movie("You've Got Mail", "regular"));
    movies.put("F002", new Movie("Matrix", "regular"));
    movies.put("F003", new Movie("Cars", "childrens"));
    movies.put("F004", new Movie("Fast & Furious X", "new"));

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = "Rental Record for " + customer.name() + "\n";
    for (MovieRental movieRental : customer.rentals()) {
      double thisAmount = 0;
      String currentMovie = movieRental.movieId();
      // determine amount for each movie
      if (movies.get(currentMovie).code().equals("regular")) {
        thisAmount = 2;
        if (movieRental.days() > 2) {
          thisAmount = ((movieRental.days() - 2) * 1.5) + thisAmount;
        }
      }

      if (movies.get(currentMovie).code().equals("new")) {
        thisAmount = movieRental.days() * 3;
      }
      if (movies.get(currentMovie).code().equals("childrens")) {
        thisAmount = 1.5;
        if (movieRental.days() > 3) {
          thisAmount = ((movieRental.days() - 3) * 1.5) + thisAmount;
        }
      }

      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (movies.get(currentMovie).code().equals("new") && movieRental.days() > 2){
        frequentEnterPoints++;
      }


      //print figures for this rental
      result += "\t" + movies.get(currentMovie).title() + "\t" + thisAmount + "\n";
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentEnterPoints + " frequent points\n";

    return result;
  }
}
