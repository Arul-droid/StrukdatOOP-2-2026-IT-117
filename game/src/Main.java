public class Main {
    public static void main(String[] args) {

        Library library = new Library("Arul");

        Game game1 = new Game("Hollow Knight", "Team Cherry", 42.5, 100.0);
        Game game2 = new Game("Stardew Valley", "ConcernedApe", 60.0, 30.0);
        Game game3 = new Game("Hades", "Supergiant Games", 25.5, 100.0);

        library.addGame(game1);
        library.addGame(game2);
        library.addGame(game3);

        library.showAllGames();
        library.showSummary();
    }
}
