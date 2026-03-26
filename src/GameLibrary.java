package src;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Kelas utama yang mengelola seluruh koleksi game milik user.
 * Menerapkan Encapsulation dan Composition.
 *
 * Fitur:
 * - Tambah / hapus game dari library
 * - Filter dengan berbagai kriteria (Strategy Pattern via FilterGame)
 * - Statistik & ringkasan koleksi
 * - Rekomendasi "game berikutnya" berdasar skor & status
 */
public class GameLibrary {
    private String namaPemilik;
    private List<Game> koleksi;

    public GameLibrary(String namaPemilik) {
        this.namaPemilik = namaPemilik;
        this.koleksi = new ArrayList<>();
    }

    // ── Manajemen Koleksi ─────────────────────────────────────────────────────

    public void tambahGame(Game game) {
        koleksi.add(game);
        System.out.printf("  [+] \"%s\" ditambahkan ke library %s.%n",
            game.getJudul(), namaPemilik);
    }

    public boolean hapusGame(int id) {
        boolean dihapus = koleksi.removeIf(g -> g.getId() == id);
        if (dihapus) System.out.println("  [-] Game #" + id + " dihapus dari library.");
        return dihapus;
    }

    public Game cariById(int id) {
        return koleksi.stream().filter(g -> g.getId() == id).findFirst().orElse(null);
    }

    // ── Filter ─────────────────────────────────────────────────────────────────

    /**
     * Menerapkan filter dan mengembalikan daftar game yang lolos.
     * Polymorphism: FilterGame bisa berupa FilterByStatus, FilterByGenre, dll.
     */
    public List<Game> filter(FilterGame kriteria) {
        return koleksi.stream()
            .filter(kriteria::lolos)
            .collect(Collectors.toList());
    }

    public void tampilkanHasilFilter(FilterGame kriteria) {
        List<Game> hasil = filter(kriteria);
        System.out.printf("%n  🔍 Filter: %s — %d game ditemukan%n",
            kriteria.getDeskripsiFilter(), hasil.size());
        if (hasil.isEmpty()) {
            System.out.println("  (tidak ada hasil)");
        } else {
            hasil.forEach(System.out::println);
        }
    }

    // ── Rekomendasi ─────────────────────────────────────────────────────────

    /**
     * Merekomendasikan game berikutnya yang sebaiknya dimainkan.
     * Logika: game BELUM_DIMULAI atau DITINGGALKAN dengan skor potensi tertinggi
     * (diestimasi dari genre + jam biasanya).
     */
    public void rekomendasiBerikutnya() {
        List<Game> kandidat = koleksi.stream()
            .filter(g -> g.getStatus() == StatusMain.BELUM_DIMULAI
                      || g.getStatus() == StatusMain.DITINGGALKAN)
            .sorted(Comparator.comparingDouble(Game::getHargaBeli).reversed())
            .collect(Collectors.toList());

        System.out.println("\n  🎯 Rekomendasi game berikutnya:");
        if (kandidat.isEmpty()) {
            System.out.println("  Semua game sudah dimainkan atau tamat!");
        } else {
            kandidat.stream().limit(3).forEach(g ->
                System.out.printf("  → \"%s\" (%s) — %s%n",
                    g.getJudul(), g.getGenre(), g.getStatus())
            );
        }
    }

    // ── Statistik ─────────────────────────────────────────────────────────────

    public void cetakStatistik() {
        int total        = koleksi.size();
        int belumMain    = (int) koleksi.stream().filter(g -> g.getStatus() == StatusMain.BELUM_DIMULAI).count();
        int sedangMain   = (int) koleksi.stream().filter(g -> g.getStatus() == StatusMain.SEDANG_DIMAINKAN).count();
        int tamat        = (int) koleksi.stream().filter(g -> g.getStatus() == StatusMain.TAMAT).count();
        int platinum     = (int) koleksi.stream().filter(g -> g.getStatus() == StatusMain.PLATINUM).count();
        int ditinggalkan = (int) koleksi.stream().filter(g -> g.getStatus() == StatusMain.DITINGGALKAN).count();
        int totalJam     = koleksi.stream().mapToInt(Game::getTotalJamMain).sum();
        int totalXP      = koleksi.stream().mapToInt(Game::getTotalXP).sum();
        double rataaSkor = koleksi.stream()
            .filter(g -> g.getTotalJamMain() > 0)
            .mapToInt(Game::hitungSkorReview)
            .average().orElse(0);

        String garis = "═".repeat(48);
        System.out.println("\n" + garis);
        System.out.printf("  📚 LIBRARY MILIK %s%n", namaPemilik.toUpperCase());
        System.out.println(garis);
        System.out.printf("  Total game          : %d%n", total);
        System.out.printf("  ⬜ Belum dimulai    : %d%n", belumMain);
        System.out.printf("  ▶  Sedang dimainkan : %d%n", sedangMain);
        System.out.printf("  ✅ Tamat            : %d%n", tamat);
        System.out.printf("  🏆 Platinum         : %d%n", platinum);
        System.out.printf("  💤 Ditinggalkan     : %d%n", ditinggalkan);
        System.out.println("  ─────────────────────────────────────");
        System.out.printf("  Total jam main      : %d jam%n", totalJam);
        System.out.printf("  Total XP terkumpul  : %d XP%n", totalXP);
        System.out.printf("  Rata-rata skor      : %.1f/100%n", rataaSkor);
        System.out.println(garis);
    }

    public void tampilkanSemuaGame() {
        System.out.printf("%n  📋 Semua game di library %s (%d game):%n",
            namaPemilik, koleksi.size());
        String header = String.format("  %-3s %-28s | %-18s | %-22s | %8s | %s",
            "#", "Judul", "Genre", "Status", "Jam", "Skor");
        System.out.println("  " + "─".repeat(100));
        System.out.println(header);
        System.out.println("  " + "─".repeat(100));
        koleksi.forEach(System.out::println);
        System.out.println("  " + "─".repeat(100));
    }

    public List<Game> getKoleksi() { return koleksi; }
    public String getNamaPemilik() { return namaPemilik; }
}
