import java.util.Scanner;

public class App {
    private static final String TIDAK_ADA = "Tidak Ada";

    // null bila ukuran bukan bilangan bulat positif atau elemennya kurang / bukan bilangan bulat.
    private static long[][] bacaMatriks(Scanner scanner) {
        if (!scanner.hasNextInt()) return null;
        int n = scanner.nextInt();
        if (n < 1) return null;

        long[][] matriks = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!scanner.hasNextLong()) return null;
                matriks[i][j] = scanner.nextLong();
            }
        }
        return matriks;
    }

    // Ganjil: sel tengah. Genap: jumlah empat sel di tengah.
    private static long nilaiTengah(long[][] m) {
        int n = m.length;
        if (n % 2 == 1) return m[n / 2][n / 2];
        int a = n / 2 - 1;
        int b = n / 2;
        return m[a][a] + m[a][b] + m[b][a] + m[b][b];
    }

    // Kolom pertama ditambah baris terakhir tanpa sudut kiri-bawah yang sudah terhitung.
    private static long nilaiL(long[][] m) {
        int n = m.length;
        long jumlah = 0;
        for (int i = 0; i < n; i++) jumlah += m[i][0];
        for (int j = 1; j < n - 1; j++) jumlah += m[n - 1][j];
        return jumlah;
    }

    // Kolom terakhir ditambah baris pertama tanpa sudut kanan-atas yang sudah terhitung.
    private static long nilaiKebalikanL(long[][] m) {
        int n = m.length;
        long jumlah = 0;
        for (int i = 0; i < n; i++) jumlah += m[i][n - 1];
        for (int j = 1; j < n - 1; j++) jumlah += m[0][j];
        return jumlah;
    }

    private static void cetak(String l, String kebalikanL, long tengah, String perbedaan, long dominan) {
        System.out.println("Nilai L: " + l);
        System.out.println("Nilai Kebalikan L: " + kebalikanL);
        System.out.println("Nilai Tengah: " + tengah);
        System.out.println("Perbedaan: " + perbedaan);
        System.out.println("Dominan: " + dominan);
    }

    public static void main(String[] args) {
        long[][] matriks = bacaMatriks(new Scanner(System.in));
        if (matriks == null) {
            System.out.println("Input matriks tidak valid");
            return;
        }

        long tengah = nilaiTengah(matriks);
        if (matriks.length <= 2) {
            cetak(TIDAK_ADA, TIDAK_ADA, tengah, TIDAK_ADA, tengah);
            return;
        }

        long l = nilaiL(matriks);
        long kebalikanL = nilaiKebalikanL(matriks);
        long perbedaan = Math.abs(l - kebalikanL);
        long dominan = perbedaan == 0 ? tengah : Math.max(l, kebalikanL);
        cetak(String.valueOf(l), String.valueOf(kebalikanL), tengah, String.valueOf(perbedaan), dominan);
    }
}
