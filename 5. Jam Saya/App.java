import java.util.Locale;
import java.util.Scanner;

public class App {
    private static final long MENIT_PER_HARI = 24 * 60;

    // Total menit sejak 00:00, atau -1 bila bukan "J:M" / "JJ:MM" yang valid (8:30 diterima sesuai modul).
    static int parseJam(String teks) {
        if (!teks.matches("\\d{1,2}:\\d{1,2}")) return -1;
        String[] bagian = teks.split(":");
        int jam = Integer.parseInt(bagian[0]);
        int menit = Integer.parseInt(bagian[1]);
        return (jam > 23 || menit > 59) ? -1 : jam * 60 + menit;
    }

    // Selisih menit dari perintah "+N" atau "-N", atau null bila format salah.
    static Long parsePerintah(String perintah) {
        if (!perintah.matches("[+-]\\d+")) return null;
        try {
            long menit = Long.parseLong(perintah.substring(1));
            return perintah.charAt(0) == '-' ? -menit : menit;
        } catch (NumberFormatException e) {
            return null; // lebih besar dari jangkauan long
        }
    }

    // Menerapkan seluruh perintah pergeseran jam, mengembalikan {posisiAkhir, totalMenit, pergantianHari}.
    private static long[] terapkanPerintah(Scanner scanner, int jamAwal) {
        long posisi = jamAwal;
        long totalMenit = 0;
        long pergantianHari = 0;

        while (scanner.hasNextLine()) {
            String baris = scanner.nextLine().trim();
            if (baris.equals("---")) break;

            Long selisih = parsePerintah(baris);
            if (selisih == null) {
                System.out.println("Perintah tidak valid");
                continue;
            }

            totalMenit += selisih;
            posisi += selisih;
            pergantianHari += Math.abs(Math.floorDiv(posisi, MENIT_PER_HARI));
            posisi = Math.floorMod(posisi, MENIT_PER_HARI);
        }
        return new long[]{posisi, totalMenit, pergantianHari};
    }

    private static void cetakHasil(int jamAwal, long posisi, long totalMenit, long pergantianHari) {
        System.out.printf(Locale.US, "Jam Awal: %02d:%02d%n", jamAwal / 60, jamAwal % 60);
        System.out.printf(Locale.US, "Jam Akhir: %02d:%02d%n", posisi / 60, posisi % 60);
        System.out.println("Total Menit: " + (totalMenit > 0 ? "+" + totalMenit : String.valueOf(totalMenit)));
        System.out.println("Pergantian Hari: " + pergantianHari);
    }

    // Mengorkestrasi alur: validasi jam awal -> proses perintah -> cetak. Tidak melakukan I/O sendiri.
    private static void proses(Scanner scanner, int jamAwal) {
        if (jamAwal < 0) {
            System.out.println("Jam tidak valid");
            return;
        }
        long[] hasil = terapkanPerintah(scanner, jamAwal);
        cetakHasil(jamAwal, hasil[0], hasil[1], hasil[2]);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int jamAwal = scanner.hasNextLine() ? parseJam(scanner.nextLine().trim()) : -1;
            proses(scanner, jamAwal);
        } catch (RuntimeException e) {
            System.out.println("Terjadi kesalahan saat memproses input");
        }
    }
}
