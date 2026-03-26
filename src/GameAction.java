package src;

/**
 * Subclass untuk game Action (Hack and Slash / Souls-like / Action Adventure).
 * Menerapkan Inheritance dan Polymorphism.
 *
 * Keunikan: punya tracker jumlah boss yang dikalahkan dan tingkat kesulitan,
 * yang keduanya memengaruhi perhitungan skor review.
 */
public class GameAction extends Game {
    private int totalBoss;
    private int bossKalahkan;
    private String tingkatKesulitan; // Easy / Normal / Hard / Nightmare
    private boolean modeChallengeSelesai;

    // Multiplier skor berdasar kesulitan
    private static final double MULT_EASY      = 0.75;
    private static final double MULT_NORMAL    = 1.00;
    private static final double MULT_HARD      = 1.20;
    private static final double MULT_NIGHTMARE = 1.40;

    public GameAction(String judul, String developer, int tahunRilis,
                      Genre genre, double hargaBeli,
                      int totalBoss, String tingkatKesulitan) {
        super(judul, developer, tahunRilis, genre, hargaBeli);
        this.totalBoss             = totalBoss;
        this.bossKalahkan          = 0;
        this.tingkatKesulitan      = tingkatKesulitan;
        this.modeChallengeSelesai  = false;
    }

    public int    getTotalBoss()             { return totalBoss; }
    public int    getBossKalahkan()          { return bossKalahkan; }
    public String getTingkatKesulitan()      { return tingkatKesulitan; }
    public boolean isModeChallengeSelesai()  { return modeChallengeSelesai; }

    public void kalahkanBoss() {
        if (bossKalahkan < totalBoss) {
            bossKalahkan++;
            System.out.printf("  [[FIGHT]] Boss %d/%d dikalahkan di \"%s\" [%s]%n",
                bossKalahkan, totalBoss, getJudul(), tingkatKesulitan);
            if (bossKalahkan == totalBoss) {
                System.out.println("  [[HOT]] Semua boss takluk! Tandai tamat? Gunakan tandaiTamat().");
            }
        } else {
            System.out.println("  [!] Semua boss sudah dikalahkan.");
        }
    }

    public void selesaikanModeChallenge() {
        if (getStatus() == StatusMain.TAMAT || getStatus() == StatusMain.PLATINUM) {
            modeChallengeSelesai = true;
            System.out.printf("  [[MEDAL]] Mode Challenge selesai untuk \"%s\"!%n", getJudul());
        } else {
            System.out.println("  [!] Harus tamat dulu sebelum mode challenge.");
        }
    }

    private double getMultiplierKesulitan() {
        return switch (tingkatKesulitan.toLowerCase()) {
            case "easy"      -> MULT_EASY;
            case "hard"      -> MULT_HARD;
            case "nightmare" -> MULT_NIGHTMARE;
            default          -> MULT_NORMAL;
        };
    }

    /**
     * Skor Action mempertimbangkan:
     * - Progres boss (40 poin dasar)  multiplier kesulitan
     * - Achievement (30 poin)
     * - Jam main relatif 30 jam optimal (20 poin)
     * - Bonus mode challenge (10 poin)
     */
    @Override
    public int hitungSkorReview() {
        if (getTotalJamMain() == 0) return 0;

        double skorBoss = totalBoss > 0
            ? (bossKalahkan * 40.0 / totalBoss) * getMultiplierKesulitan() : 0;
        double skorAchievement = hitungPersenAchievement() * 0.30;
        double skorJam = Math.min(getTotalJamMain() / 30.0, 1.0) * 20;
        int bonusChallenge = modeChallengeSelesai ? 10 : 0;

        return (int) Math.min(skorBoss + skorAchievement + skorJam + bonusChallenge, 100);
    }

    @Override
    public String getInfoSpesifik() {
        String challenge = modeChallengeSelesai ? " | Challenge v" : "";
        return String.format("Boss: %d/%d | Kesulitan: %s%s",
            bossKalahkan, totalBoss, tingkatKesulitan, challenge);
    }
}
