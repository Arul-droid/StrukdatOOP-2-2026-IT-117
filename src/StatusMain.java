package src;

/**
 * Status kemajuan bermain — fitur unik library ini.
 * Terinspirasi dari PSN / Steam achievement system.
 *
 * Alur valid:
 *   BELUM_DIMULAI → SEDANG_DIMAINKAN → TAMAT → PLATINUM
 *                                     ↓
 *                               DITINGGALKAN
 */
public enum StatusMain {
    BELUM_DIMULAI("⬜ Belum Dimulai"),
    SEDANG_DIMAINKAN("▶  Sedang Dimainkan"),
    TAMAT("✅ Tamat"),
    PLATINUM("🏆 Platinum / 100%"),
    DITINGGALKAN("💤 Ditinggalkan");

    private final String label;

    StatusMain(String label) { this.label = label; }

    public String getLabel() { return label; }

    @Override
    public String toString() { return label; }
}
