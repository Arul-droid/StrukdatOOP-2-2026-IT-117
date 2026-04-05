import java.util.ArrayList;

public class Library {
    private String pemilik;
    private ArrayList<Game> listGame;

    public Library(String pemilik) {
        this.pemilik = pemilik;
        this.listGame = new ArrayList<>();
    }

    public void addGame(Game game) {
        listGame.add(game);
    }

    public void showAllGames() {
        System.out.println("==============================");
        System.out.println("  Perpustakaan Game Milik: " + pemilik);
        System.out.println("  Total Game: " + listGame.size());
        System.out.println("==============================");
        for (int i = 0; i < listGame.size(); i++) {
            System.out.println("\n[Game " + (i + 1) + "]");
            listGame.get(i).displayInfo();
            System.out.println("------------------------------");
        }
    }

    public void showSummary() {
        double totalHours = 0;
        int selesai = 0;

        for (Game game : listGame) {
            totalHours += game.getplayTime();
            if (game.getpercentKomplit() == 100.0) {
                selesai++;
            }
        }

        System.out.println("\n====== Ringkasan ======");
        System.out.println("Total waktu main  : " + totalHours + " jam");
        System.out.println("Game selesai      : " + selesai + " dari " + listGame.size());
        System.out.println("=======================");
    }
}
