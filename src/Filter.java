package src;

/**
 * Implementasi-implementasi FilterGame.
 * Masing-masing adalah strategi filter yang berbeda.
 */

/** Filter berdasarkan status main */
class FilterByStatus implements FilterGame {
    private StatusMain targetStatus;

    public FilterByStatus(StatusMain targetStatus) {
        this.targetStatus = targetStatus;
    }

    @Override
    public boolean lolos(Game game) {
        return game.getStatus() == targetStatus;
    }

    @Override
    public String getDeskripsiFilter() {
        return "Status = " + targetStatus.getLabel();
    }
}

/** Filter berdasarkan genre */
class FilterByGenre implements FilterGame {
    private Genre targetGenre;

    public FilterByGenre(Genre targetGenre) {
        this.targetGenre = targetGenre;
    }

    @Override
    public boolean lolos(Game game) {
        return game.getGenre() == targetGenre;
    }

    @Override
    public String getDeskripsiFilter() {
        return "Genre = " + targetGenre.getLabel();
    }
}

/** Filter game yang sudah melebihi jam main tertentu */
class FilterByJamMin implements FilterGame {
    private int jamMinimum;

    public FilterByJamMin(int jamMinimum) {
        this.jamMinimum = jamMinimum;
    }

    @Override
    public boolean lolos(Game game) {
        return game.getTotalJamMain() >= jamMinimum;
    }

    @Override
    public String getDeskripsiFilter() {
        return "Jam main ≥ " + jamMinimum + " jam";
    }
}

/** Filter game dengan skor review di atas ambang batas */
class FilterBySkorMin implements FilterGame {
    private int skorMinimum;

    public FilterBySkorMin(int skorMinimum) {
        this.skorMinimum = skorMinimum;
    }

    @Override
    public boolean lolos(Game game) {
        return game.hitungSkorReview() >= skorMinimum;
    }

    @Override
    public String getDeskripsiFilter() {
        return "Skor review ≥ " + skorMinimum;
    }
}
