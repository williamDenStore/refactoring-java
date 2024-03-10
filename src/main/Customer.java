package main;

import java.util.List;

public record Customer (String name, List<MovieRental> rentals){}
