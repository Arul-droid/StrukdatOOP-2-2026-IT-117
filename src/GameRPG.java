package src;

/**
 * Subclass untuk game RPG (JRPG / Open World RPG / Action RPG).
 * Menerapkan Inheritance - mewarisi semua fitur Game.
 * Menerapkan Polymorphism - override hitungSkorReview() dan getInfoSpesifik().
 *
 * Keunikan: punya tracker jumlah chapter dan flag New Game+ yang
 * memengaruhi skor review.
 */
public class GameRPG extends Game {
    private int totalChapter;
    private int chapterSelesai;
    private boolean adaNewGamePlus;
    private boolean sudahNGPlus;   // apakah user sudah main NG+

    public GameRPG(String judul, String developer, int tahunRilis,
                   Genre genre, double hargaBeli,
                   int totalChapter, boolean adaNewGamePlus) {
        super(judul, developer, tahunRilis, genre, hargaBeli);
        this.totalChapter    = totalChapter;
        this.chapterSelesai  = 0;
        this.adaNewGamePlus  = adaNewGamePlus;
        this.sudahNGPlus     = false;
    }

    public int getTotalChapter()     { return totalChapter; }
    public int getChapterSelesai()   { return chapterSelesai; }
    public boolean isAdaNewGamePlus(){ return adaNewGamePlus; }
    public boolean isSudahNGPlus()   { return sudahNGPlus; }

    public void selesaikanChapter() {
        if (chapterSelesai < totalChapter) {
            chapterSelesai++;
            System.out.printf("  [[BOOK]] Chapter %d/%d selesai di \"%s\"%n",
                chapterSelesai, totalChapter, getJudul());
            if (chapterSelesai == totalChapter) {
                System.out.println("  [] Semua chapter selesai! Tandai tamat? Gunakan tandaiTamat().");
            }
        } else {
            System.out.println("  [!] Semua chapter sudah selesai.");
        }
    }

    public void mainNGPlus() {
        if (!adaNewGamePlus) {
            System.out.println("  [!] Game ini tidak memiliki New Game+.");
            return;
        }
        if (getStatus() != StatusMain.TAMAT && getStatus() != StatusMain.PLATINUM) {
            System.out.println("  [!] Harus tamat dulu sebelum NG+.");
            return;
        }
        sudahNGPlus = true;
        chapterSelesai = 0; // reset chapter untuk NG+
        mulaiMain();        // set status kembali ke playing
        System.out.printf("  [] New Game+ dimulai untuk \"%s\"%n", getJudul());
    }

    /**
     * Skor RPG mempertimbangkan:
     * - Progres chapter (40 poin)
     * - Achievement (30 poin)
     * - Jam main relatif terhadap panjang game (20 poin)
     * - Bonus NG+ (10 poin)
     */
    @Override
    public int hitungSkorReview() {
        if (getTotalJamMain() == 0) return 0;

        double skorChapter = totalChapter > 0
            ? (chapterSelesai * 40.0 / totalChapter) : 0;
        double skorAchievement = hitungPersenAchievement() * 0.30;
        // Jam optimal RPG dianggap 60 jam; lebih dari itu tetap penuh
        double skorJam = Math.min(getTotalJamMain() / 60.0, 1.0) * 20;
        int bonusNG = sudahNGPlus ? 10 : 0;

        return (int) Math.min(skorChapter + skorAchievement + skorJam + bonusNG, 100);
    }

    @Override
    public String getInfoSpesifik() {
        String ng = adaNewGamePlus ? (sudahNGPlus ? " | NG+ v" : " | NG+ tersedia") : "";
        return String.format("Chapter: %d/%d%s", chapterSelesai, totalChapter, ng);
    }
}
