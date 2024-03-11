package main;

import java.util.Objects;

public final class Movie {
    private final String title;
    private final String code;

    public Movie(String title) {
        this.title = title;
        if (title == null) {
            this.code = null;
        } else {
            this.code = switch (title) {
                case "Matrix", "You've Got Mail" -> "regular";
                case "Cars" -> "childrens";
                case "Fast & Furious X" -> "new";
                default -> null;
            };
        }
    }

    public String title() {
        return title;
    }

    public String code() {
        return code;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Movie) obj;
        return Objects.equals(this.title, that.title) &&
                Objects.equals(this.code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, code);
    }

    @Override
    public String toString() {
        return "Movie[" +
                "title=" + title + ", " +
                "code=" + code + ']';
    }

}
