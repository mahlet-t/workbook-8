public class Film {
    int filmId;
    String title;
    String description;
    int releaseYear;
    int length;

    public Film(int filmId, String title, String description, int releaseYear, int length) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.length = length;
    }

    @Override
    public String toString() {
        return String.format("%-5d %-20s %-100s %-50d %-50d", filmId, title, description, releaseYear, length);


    }
}
