package src;

/**
 * Merepresentasikan satu achievement/trophy dalam sebuah game.
 * Menerapkan Encapsulation - semua field private.
 */
public class Achievement {
    private String kode;
    private String nama;
    private String deskripsi;
    private int xpReward;
    private boolean terbuka;

    public Achievement(String kode, String nama, String deskripsi, int xpReward) {
        this.kode = kode;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.xpReward = xpReward;
        this.terbuka = false;
    }

    public String getKode()       { return kode; }
    public String getNama()       { return nama; }
    public String getDeskripsi()  { return deskripsi; }
    public int    getXpReward()   { return xpReward; }
    public boolean isTerbuka()    { return terbuka; }

    /**
     * Membuka achievement. Sekali terbuka tidak bisa ditutup lagi.
     * @return true jika baru saja dibuka, false jika sudah terbuka sebelumnya.
     */
    public boolean buka() {
        if (terbuka) return false;
        terbuka = true;
        return true;
    }

    @Override
    public String toString() {
        String status = terbuka ? "[UNLOCKED]" : "[LOCKED]";
        return String.format("  %s [%s] %-28s +%d XP  - %s",
            status, kode, nama, xpReward, deskripsi);
    }
}
