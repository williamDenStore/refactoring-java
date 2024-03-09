
public class RentalInfo {

  public String statement(Customer customer) {
    double totalAmount = 0;
    int frequentEnterPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for " + customer.name() + "\n");
    for (MovieRental movieRental : customer.rentals()) {
      double thisAmount;
      // determine amount for each movie
      thisAmount = movieRental.calculatePrice();
      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (movieRental.movie().code().equals("new") && movieRental.days() > 2){
        frequentEnterPoints++;
      }
      //print figures for this rental
      result.append("\t").append(movieRental.movie().title()).append("\t").append(thisAmount).append("\n");
      totalAmount += thisAmount;
    }
    // add footer lines
    result.append("Amount owed is ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");
    return result.toString();
  }
}
