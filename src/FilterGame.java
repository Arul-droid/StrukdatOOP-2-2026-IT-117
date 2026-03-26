package src;

/**
 * Interface untuk strategi filter pencarian game di library.
 * Menerapkan Abstraction - setiap filter punya logika sendiri.
 *
 * Memungkinkan penambahan kriteria filter baru tanpa mengubah kelas Library
 * (Open/Closed Principle).
 */
public interface FilterGame {
    /**
     * Menentukan apakah sebuah game memenuhi kriteria filter.
     * @param game game yang dievaluasi
     * @return true jika game lolos filter
     */
    boolean lolos(Game game);

    /** Deskripsi singkat kriteria filter (untuk ditampilkan ke user). */
    String getDeskripsiFilter();
}
