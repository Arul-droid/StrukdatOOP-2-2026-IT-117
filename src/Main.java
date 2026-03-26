package src;

/**
 * ===============================================================
 *  SISTEM GAME LIBRARY PRIBADI
 *  Studi Kasus OOP: Memilih & Mengelola Koleksi Game
 * ===============================================================
 *  Prinsip OOP yang diterapkan:
 *   1. Encapsulation  - semua field private, akses via getter/setter
 *   2. Inheritance    - GameRPG & GameAction extends Game (abstract)
 *   3. Polymorphism   - hitungSkorReview() berbeda tiap subclass
 *   4. Abstraction    - Game abstract, FilterGame interface
 * ===============================================================
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("+==================================================+");
        System.out.println("|        SISTEM GAME LIBRARY PRIBADI - OOP         |");
        System.out.println("+==================================================+");

        // -- 1. Buat Library ---------------------------------------------------
        GameLibrary library = new GameLibrary("Arul");

        // -- 2. Tambah Game RPG ------------------------------------------------
        System.out.println("\n========== MENAMBAH GAME KE LIBRARY ==========");

        GameRPG eldenRing = new GameRPG(
            "Elden Ring", "FromSoftware", 2022,
            Genre.OPEN_WORLD_RPG, 850, 12, false);
        eldenRing.tambahAchievement(new Achievement("ER01", "Elden Lord",       "Tamatkan game",          500));
        eldenRing.tambahAchievement(new Achievement("ER02", "Dragonslayer",      "Kalahkan Godrick",        200));
        eldenRing.tambahAchievement(new Achievement("ER03", "Walking Mausoleum", "Cari semua lore item",    300));

        GameAction sekiro = new GameAction(
            "Sekiro: Shadows Die Twice", "FromSoftware", 2019,
            Genre.SOULS_LIKE, 700, 8, "Hard");
        sekiro.tambahAchievement(new Achievement("SK01", "Shinobi Prosthetic", "Upgrade semua prosthetic", 200));
        sekiro.tambahAchievement(new Achievement("SK02", "Immortal Severance",  "Raih ending utama",        400));
        sekiro.tambahAchievement(new Achievement("SK03", "Height of Technique", "Kuasai semua combat art",  300));

        library.tambahGame(eldenRing);
        library.tambahGame(sekiro);

        // -- 3. Simulasi: Main Elden Ring --------------------------------------
        System.out.println("\n========== SESI MAIN: ELDEN RING ==========");
        eldenRing.tambahJamMain(15);
        eldenRing.bukaAchievement("ER02");   // kalahkan Godrick
        eldenRing.selesaikanChapter();
        eldenRing.selesaikanChapter();
        eldenRing.selesaikanChapter();
        eldenRing.tambahJamMain(25);
        eldenRing.bukaAchievement("ER03");
        eldenRing.selesaikanChapter();
        // selesaikan semua chapter
        for (int i = 0; i < 8; i++) eldenRing.selesaikanChapter();
        eldenRing.tambahJamMain(20);
        eldenRing.bukaAchievement("ER01");
        eldenRing.tandaiTamat();

        // Coba platinum tapi achievement belum semua terbuka
        System.out.println("\n  [TEST] Coba platinum padahal achievement belum semua terbuka...");
        eldenRing.raihPlatinum();


        // -- 6. Simulasi: Sekiro ditinggalkan ----------------------------------
        System.out.println("\n========== SESI MAIN: SEKIRO (DITINGGALKAN) ==========");
        sekiro.tambahJamMain(8);
        sekiro.kalahkanBoss();
        sekiro.kalahkanBoss();
        sekiro.tinggalkan();


        // -- 8. Cetak detail individual ----------------------------------------
        System.out.println("\n========== DETAIL GAME ==========");
        eldenRing.cetakDetail();

        // -- 9. Tampilkan semua + statistik -----------------------------------
        library.tampilkanSemuaGame();
        library.cetakStatistik();

        // -- 10. Demo Filter (Strategy Pattern via FilterGame interface) -------
        System.out.println("\n========== DEMO FILTER LIBRARY ==========");
        library.tampilkanHasilFilter(new FilterByStatus(StatusMain.TAMAT));
        library.tampilkanHasilFilter(new FilterByGenre(Genre.SOULS_LIKE));
        library.tampilkanHasilFilter(new FilterByJamMin(30));
        library.tampilkanHasilFilter(new FilterBySkorMin(70));

        // -- 11. Rekomendasi berikutnya ----------------------------------------
        library.rekomendasiBerikutnya();

        // -- 12. Edge case: tambah jam ke game belum dimulai -------------------
        System.out.println("\n========== EDGE CASE ==========");
        System.out.println("  [TEST] Tambah jam ke game yang belum dimulai -> otomatis mulai...");

        System.out.println("\n  [TEST] Coba NG+ tanpa tamat dulu...");
        GameRPG testGame = new GameRPG("Test Game", "Dev", 2024, Genre.ACTION_RPG, 100, 5, true);
        testGame.mainNGPlus();
    }
}
