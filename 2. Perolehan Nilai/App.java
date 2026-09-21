import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {
    private static final String[] SIMBOL = {"PA", "T", "K", "P", "UTS", "UAS"};
    private static final String[] NAMA = {"Partisipatif", "Tugas", "Kuis", "Proyek", "UTS", "UAS"};
    private static final String PESAN_FORMAT =
            "Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai";
    // Double.parseDouble juga menerima "NaN", "1e3", "5d", dan heks; hanya desimal biasa yang valid.
    private static final Pattern ANGKA = Pattern.compile("[+-]?(\\d+\\.?\\d*|\\.\\d+)");

    // NaN bila teks bukan angka desimal biasa atau nilainya di luar jangkauan double.
    private static double parseAngka(String teks) {
        if (!ANGKA.matcher(teks).matches()) return Double.NaN;
        double nilai = Double.parseDouble(teks);
        return Double.isInfinite(nilai) ? Double.NaN : nilai;
    }

    private static int indeksSimbol(String simbol) {
        for (int i = 0; i < SIMBOL.length; i++) {
            if (SIMBOL[i].equals(simbol)) return i;
        }
        return -1;
    }

    // null bila ada bobot yang bukan bilangan bulat atau jumlahnya kurang dari enam.
    private static int[] bacaBobot(Scanner scanner) {
        int[] bobot = new int[SIMBOL.length];
        for (int i = 0; i < bobot.length; i++) {
            if (!scanner.hasNextInt()) return null;
            bobot[i] = scanner.nextInt();
        }
        return bobot;
    }

    private static void bacaPerolehan(Scanner scanner, double[] total, double[] perolehan) {
        while (scanner.hasNextLine()) {
            String baris = scanner.nextLine();
            if (baris.equals("---")) break;

            String[] bagian = baris.split("\\|");
            if (bagian.length != 3) {
                System.out.println(PESAN_FORMAT);
                continue;
            }
            double bobot = parseAngka(bagian[1].trim());
            double nilai = parseAngka(bagian[2].trim());
            if (Double.isNaN(bobot) || Double.isNaN(nilai)) {
                System.out.println(PESAN_FORMAT);
                continue;
            }
            int indeks = indeksSimbol(bagian[0].trim());
            if (indeks < 0) {
                System.out.println("Simbol tidak dikenal");
                continue;
            }

            total[indeks] += bobot;
            perolehan[indeks] += Math.max(0, Math.min(nilai, bobot));
        }
    }

    private static int persentase(double perolehan, double total) {
        return total == 0 ? 0 : (int) (perolehan / total * 100 + 1e-9);
    }

    private static String grade(double nilaiAkhir) {
        double n = nilaiAkhir + 1e-9; // toleransi galat floating point pada batas grade
        if (n >= 79.5) return "A";
        if (n >= 72) return "AB";
        if (n >= 64.5) return "B";
        if (n >= 57) return "BC";
        if (n >= 49.5) return "C";
        if (n >= 34) return "D";
        return "E";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] bobot = bacaBobot(scanner);
        if (bobot == null) {
            System.out.println("Bobot tidak valid");
            return;
        }
        int totalBobot = 0;
        for (int b : bobot) totalBobot += b;
        if (totalBobot != 100) {
            System.out.println("Total bobot harus 100");
            return;
        }
        if (scanner.hasNextLine()) scanner.nextLine(); // sisa baris setelah bobot terakhir

        double[] total = new double[SIMBOL.length];
        double[] perolehan = new double[SIMBOL.length];
        bacaPerolehan(scanner, total, perolehan);

        double nilaiAkhir = 0;
        System.out.println("Perolehan Nilai:");
        for (int i = 0; i < SIMBOL.length; i++) {
            int persen = persentase(perolehan[i], total[i]);
            double nilai = persen / 100.0 * bobot[i];
            nilaiAkhir += nilai;
            System.out.printf(Locale.US, ">> %s: %d/100 (%.2f/%d)%n", NAMA[i], persen, nilai, bobot[i]);
        }
        nilaiAkhir = Math.round(nilaiAkhir * 100.0) / 100.0;

        System.out.println();
        System.out.printf(Locale.US, ">> Nilai Akhir: %.2f%n", nilaiAkhir);
        System.out.println(">> Grade: " + grade(nilaiAkhir));
    }
}
