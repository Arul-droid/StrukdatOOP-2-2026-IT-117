package src;

/**
 * ═══════════════════════════════════════════════════════════════
 *  SISTEM GAME LIBRARY PRIBADI
 *  Studi Kasus OOP: Memilih & Mengelola Koleksi Game
 * ═══════════════════════════════════════════════════════════════
 *  Prinsip OOP yang diterapkan:
 *   1. Encapsulation  — semua field private, akses via getter/setter
 *   2. Inheritance    — GameRPG & GameAction extends Game (abstract)
 *   3. Polymorphism   — hitungSkorReview() berbeda tiap subclass
 *   4. Abstraction    — Game abstract, FilterGame interface
 * ═══════════════════════════════════════════════════════════════
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║        SISTEM GAME LIBRARY PRIBADI — OOP         ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        // ── 1. Buat Library ───────────────────────────────────────────────────
        GameLibrary library = new GameLibrary("Arul");

        // ── 2. Tambah Game RPG ────────────────────────────────────────────────
        System.out.println("\n══════════ MENAMBAH GAME KE LIBRARY ══════════");

        GameRPG eldenRing = new GameRPG(
            "Elden Ring", "FromSoftware", 2022,
            Genre.OPEN_WORLD_RPG, 850, 12, false);
        eldenRing.tambahAchievement(new Achievement("ER01", "Elden Lord",       "Tamatkan game",          500));
        eldenRing.tambahAchievement(new Achievement("ER02", "Dragonslayer",      "Kalahkan Godrick",        200));
        eldenRing.tambahAchievement(new Achievement("ER03", "Walking Mausoleum", "Cari semua lore item",    300));

        GameRPG persona5 = new GameRPG(
            "Persona 5 Royal", "Atlus", 2020,
            Genre.JRPG, 600, 3, true);
        persona5.tambahAchievement(new Achievement("P501", "Phantom Thief",  "Selesaikan Palais 1",    100));
        persona5.tambahAchievement(new Achievement("P502", "Master Chef",    "Buat 30 resep",           150));
        persona5.tambahAchievement(new Achievement("P503", "True Ending",    "Raih ending true",        500));

        GameAction devilMayCry = new GameAction(
            "Devil May Cry 5", "Capcom", 2019,
            Genre.HACK_AND_SLASH, 450, 10, "Normal");
        devilMayCry.tambahAchievement(new Achievement("DMC01", "Smokin' Sick Style", "Capai SSS rank",   300));
        devilMayCry.tambahAchievement(new Achievement("DMC02", "Dante Must Die",     "Selesaikan DMD",   500));

        GameAction sekiro = new GameAction(
            "Sekiro: Shadows Die Twice", "FromSoftware", 2019,
            Genre.SOULS_LIKE, 700, 8, "Hard");
        sekiro.tambahAchievement(new Achievement("SK01", "Shinobi Prosthetic", "Upgrade semua prosthetic", 200));
        sekiro.tambahAchievement(new Achievement("SK02", "Immortal Severance",  "Raih ending utama",        400));
        sekiro.tambahAchievement(new Achievement("SK03", "Height of Technique", "Kuasai semua combat art",  300));

        GameAction godOfWar = new GameAction(
            "God of War Ragnarok", "Santa Monica Studio", 2022,
            Genre.ACTION_ADVENTURE, 950, 7, "Normal");
        godOfWar.tambahAchievement(new Achievement("GOW01", "Father and Son", "Selesaikan main story", 300));
        godOfWar.tambahAchievement(new Achievement("GOW02", "Grave Robber",   "Buka semua chest",      200));

        library.tambahGame(eldenRing);
        library.tambahGame(persona5);
        library.tambahGame(devilMayCry);
        library.tambahGame(sekiro);
        library.tambahGame(godOfWar);

        // ── 3. Simulasi: Main Elden Ring ──────────────────────────────────────
        System.out.println("\n══════════ SESI MAIN: ELDEN RING ══════════");
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

        // ── 4. Simulasi: Main Persona 5 ───────────────────────────────────────
        System.out.println("\n══════════ SESI MAIN: PERSONA 5 ROYAL ══════════");
        persona5.tambahJamMain(40);
        persona5.bukaAchievement("P501");
        persona5.bukaAchievement("P502");
        persona5.selesaikanChapter();
        persona5.selesaikanChapter();
        persona5.selesaikanChapter();
        persona5.bukaAchievement("P503");
        persona5.tandaiTamat();

        // Coba platinum tapi achievement belum semua terbuka
        System.out.println("\n  [TEST] Coba platinum padahal achievement belum semua terbuka...");
        eldenRing.raihPlatinum();

        // ── 5. Simulasi: Main DMC5 ────────────────────────────────────────────
        System.out.println("\n══════════ SESI MAIN: DEVIL MAY CRY 5 ══════════");
        devilMayCry.mulaiMain();
        devilMayCry.tambahJamMain(12);
        for (int i = 0; i < 10; i++) devilMayCry.kalahkanBoss();
        devilMayCry.bukaAchievement("DMC01");
        devilMayCry.tandaiTamat();
        devilMayCry.bukaAchievement("DMC02");
        devilMayCry.selesaikanModeChallenge();

        // ── 6. Simulasi: Sekiro ditinggalkan ──────────────────────────────────
        System.out.println("\n══════════ SESI MAIN: SEKIRO (DITINGGALKAN) ══════════");
        sekiro.tambahJamMain(8);
        sekiro.kalahkanBoss();
        sekiro.kalahkanBoss();
        sekiro.tinggalkan();

        // ── 7. NG+ Persona 5 ─────────────────────────────────────────────────
        System.out.println("\n══════════ NEW GAME+ PERSONA 5 ══════════");
        persona5.mainNGPlus();
        persona5.tambahJamMain(15);

        // ── 8. Cetak detail individual ────────────────────────────────────────
        System.out.println("\n══════════ DETAIL GAME ══════════");
        eldenRing.cetakDetail();
        devilMayCry.cetakDetail();

        // ── 9. Tampilkan semua + statistik ───────────────────────────────────
        library.tampilkanSemuaGame();
        library.cetakStatistik();

        // ── 10. Demo Filter (Strategy Pattern via FilterGame interface) ───────
        System.out.println("\n══════════ DEMO FILTER LIBRARY ══════════");
        library.tampilkanHasilFilter(new FilterByStatus(StatusMain.TAMAT));
        library.tampilkanHasilFilter(new FilterByGenre(Genre.SOULS_LIKE));
        library.tampilkanHasilFilter(new FilterByJamMin(30));
        library.tampilkanHasilFilter(new FilterBySkorMin(70));

        // ── 11. Rekomendasi berikutnya ────────────────────────────────────────
        library.rekomendasiBerikutnya();

        // ── 12. Edge case: tambah jam ke game belum dimulai ───────────────────
        System.out.println("\n══════════ EDGE CASE ══════════");
        System.out.println("  [TEST] Tambah jam ke game yang belum dimulai → otomatis mulai...");
        godOfWar.tambahJamMain(5);
        System.out.println("  Status sekarang: " + godOfWar.getStatus());

        System.out.println("\n  [TEST] Coba NG+ tanpa tamat dulu...");
        GameRPG testGame = new GameRPG("Test Game", "Dev", 2024, Genre.ACTION_RPG, 100, 5, true);
        testGame.mainNGPlus();
    }
}
