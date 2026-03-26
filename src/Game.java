package src;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class untuk semua game dalam library.
 * Menerapkan Abstraction dan Encapsulation.
 *
 * Subclass wajib mengimplementasikan:
 *  - getInfoSpesifik()  : info unik per tipe game
 *  - hitungSkorReview() : formula penilaian berbeda tiap tipe
 */
public abstract class Game {
    private static int autoId = 100;

    private final int id;
    private String judul;
    private String developer;
    private int tahunRilis;
    private Genre genre;
    private double hargaBeli;    // dalam ribuan rupiah
    private StatusMain status;
    private int totalJamMain;    // akumulasi jam bermain
    private List<Achievement> achievements;
    private int totalXP;

    public Game(String judul, String developer, int tahunRilis,
                Genre genre, double hargaBeli) {
        this.id          = ++autoId;
        this.judul       = judul;
        this.developer   = developer;
        this.tahunRilis  = tahunRilis;
        this.genre       = genre;
        this.hargaBeli   = hargaBeli;
        this.status      = StatusMain.BELUM_DIMULAI;
        this.totalJamMain = 0;
        this.achievements = new ArrayList<>();
        this.totalXP      = 0;
    }

    // -- Getters ---------------------------------------------------------------
    public int    getId()          { return id; }
    public String getJudul()       { return judul; }
    public String getDeveloper()   { return developer; }
    public int    getTahunRilis()  { return tahunRilis; }
    public Genre  getGenre()       { return genre; }
    public double getHargaBeli()   { return hargaBeli; }
    public StatusMain getStatus()  { return status; }
    public int    getTotalJamMain(){ return totalJamMain; }
    public int    getTotalXP()     { return totalXP; }
    public List<Achievement> getAchievements() { return achievements; }

    // -- Manajemen Status ------------------------------------------------------
    public void mulaiMain() {
        if (status == StatusMain.BELUM_DIMULAI || status == StatusMain.DITINGGALKAN) {
            status = StatusMain.SEDANG_DIMAINKAN;
            System.out.printf("  [>] Mulai main \"%s\"%n", judul);
        } else {
            System.out.println("  [!] Game sudah dalam status: " + status);
        }
    }

    public void tandaiTamat() {
        if (status == StatusMain.SEDANG_DIMAINKAN) {
            status = StatusMain.TAMAT;
            System.out.printf("  [[OK]] \"%s\" ditandai TAMAT setelah %d jam.%n", judul, totalJamMain);
        } else {
            System.out.println("  [!] Harus sedang dimainkan untuk ditandai tamat.");
        }
    }

    public void raihPlatinum() {
        if (status == StatusMain.TAMAT) {
            // Cek semua achievement sudah terbuka
            long terbuka = achievements.stream().filter(Achievement::isTerbuka).count();
            if (terbuka == achievements.size() && !achievements.isEmpty()) {
                status = StatusMain.PLATINUM;
                System.out.printf("  [] PLATINUM! \"%s\" - semua %d achievement terbuka!%n",
                    judul, achievements.size());
            } else {
                System.out.printf("  [!] Masih ada achievement yang terkunci (%d/%d terbuka).%n",
                    terbuka, achievements.size());
            }
        } else {
            System.out.println("  [!] Game harus berstatus TAMAT dulu.");
        }
    }

    public void tinggalkan() {
        if (status == StatusMain.SEDANG_DIMAINKAN) {
            status = StatusMain.DITINGGALKAN;
            System.out.printf("  [] \"%s\" ditinggalkan.%n", judul);
        }
    }

    // -- Playtime Log ----------------------------------------------------------
    public void tambahJamMain(int jam) {
        if (jam <= 0) throw new IllegalArgumentException("Jam main harus > 0.");
        if (status == StatusMain.BELUM_DIMULAI) {
            mulaiMain();
        }
        totalJamMain += jam;
        System.out.printf("  [] +%d jam -> total %d jam untuk \"%s\"%n",
            jam, totalJamMain, judul);
    }

    // -- Achievement Tracker ---------------------------------------------------
    public void tambahAchievement(Achievement a) {
        achievements.add(a);
    }

    public boolean bukaAchievement(String kode) {
        for (Achievement a : achievements) {
            if (a.getKode().equals(kode)) {
                boolean berhasil = a.buka();
                if (berhasil) {
                    totalXP += a.getXpReward();
                    System.out.printf("  [[UNLOCKED]] Achievement unlocked: \"%s\" +%d XP (total XP: %d)%n",
                        a.getNama(), a.getXpReward(), totalXP);
                    return true;
                } else {
                    System.out.println("  [!] Achievement sudah terbuka sebelumnya.");
                }
                return false;
            }
        }
        System.out.println("  [!] Kode achievement tidak ditemukan: " + kode);
        return false;
    }

    public int hitungPersenAchievement() {
        if (achievements.isEmpty()) return 0;
        long terbuka = achievements.stream().filter(Achievement::isTerbuka).count();
        return (int) (terbuka * 100 / achievements.size());
    }

    // -- Abstract Methods ------------------------------------------------------

    /** Info spesifik per tipe game (misal: jumlah chapter, tipe world, dsb) */
    public abstract String getInfoSpesifik();

    /**
     * Skor review personal (0-100) dihitung berbeda tiap subclass.
     * Mempertimbangkan jam main, achievement, dan faktor unik tiap tipe.
     */
    public abstract int hitungSkorReview();

    // -- Display ---------------------------------------------------------------
    public void cetakDetail() {
        String garis = "-".repeat(56);
        System.out.println("\n" + garis);
        System.out.printf("   #%d  %s  (%d)%n", id, judul, tahunRilis);
        System.out.printf("  Developer   : %s%n", developer);
        System.out.printf("  Genre       : %s%n", genre);
        System.out.printf("  Status      : %s%n", status);
        System.out.printf("  Jam Main    : %d jam%n", totalJamMain);
        System.out.printf("  Achievement : %d/%d (%d%%)%n",
            achievements.stream().filter(Achievement::isTerbuka).count(),
            achievements.size(), hitungPersenAchievement());
        System.out.printf("  Total XP    : %d%n", totalXP);
        System.out.printf("  Skor Review : %d/100%n", hitungSkorReview());
        System.out.printf("  Info        : %s%n", getInfoSpesifik());
        System.out.println(garis);
    }

    @Override
    public String toString() {
        return String.format("  #%d %-28s | %-18s | %-22s | %3d jam | Skor: %d",
            id, judul, genre, status, totalJamMain, hitungSkorReview());
    }
}
