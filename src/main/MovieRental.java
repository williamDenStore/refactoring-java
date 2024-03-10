package main;

import java.util.Objects;

public final class MovieRental {
    private final Movie movie;
    private final int days;

    public MovieRental(String movieName, int days) {
        if (movieName==null){
            this.movie = null;
        }
        else {
            this.movie = switch (movieName) {
                case "Matrix", "You've Got Mail" -> new Movie(movieName, "regular");
                case "Cars" -> new Movie(movieName, "childrens");
                case "Fast & Furious X" -> new Movie(movieName, "new");
                default -> null;
            };
        }
        this.days = days;
    }
    public Movie movie() {
        return movie;
    }

    public int days() {
        return days;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (MovieRental) obj;
        return Objects.equals(this.movie, that.movie) &&
                this.days == that.days;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, days);
    }

    @Override
    public String toString() {
        return "main.MovieRental[" +
                "movie=" + movie + ", " +
                "days=" + days + ']';
    }
    double calculatePrice(){
        assert this.movie() != null;
        return switch (this.movie().code()) {
            case "regular" -> regularMoviePrice();
            case "new" -> newMoviePrice();
            case "childrens" -> childrenMoviePrice();
            default -> 0;
        };
    }
    private int newMoviePrice() {
        return days() * 3;
    }

    private double regularMoviePrice() {
        double thisAmount;
        thisAmount = 2;
        if (days() > 2) {
            thisAmount = ((days() - 2) * 1.5) + thisAmount;
        }
        return thisAmount;
    }

    private double childrenMoviePrice() {
        double thisAmount;
        thisAmount = 1.5;
        if (days() > 3) {
            thisAmount = ((days() - 3) * 1.5) + thisAmount;
        }
        return thisAmount;
    }


}
