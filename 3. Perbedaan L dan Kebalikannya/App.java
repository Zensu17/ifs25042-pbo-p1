import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[][] m = new long[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                m[i][j] = sc.nextLong();

        if (n == 1) {
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + m[0][0]);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + m[0][0]);
            return;
        }

        long tengah;
        if (n % 2 == 1) {
            tengah = m[n / 2][n / 2];
        } else {
            int a = n / 2 - 1, b = n / 2;
            tengah = m[a][a] + m[a][b] + m[b][a] + m[b][b];
        }

        if (n == 2) {
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + tengah);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + tengah);
            return;
        }

        long nilaiL = 0, kebalikanL = 0;
        for (int i = 0; i < n; i++) {
            nilaiL += m[i][0];   
            kebalikanL += m[i][n - 1]; 
        }
        for (int j = 1; j < n - 1; j++) {
            nilaiL += m[n - 1][j];
            kebalikanL += m[0][j];
        }

        long perbedaan = Math.abs(nilaiL - kebalikanL);
        long dominan = (perbedaan == 0) ? tengah : Math.max(nilaiL, kebalikanL);

        System.out.println("Nilai L: " + nilaiL);
        System.out.println("Nilai Kebalikan L: " + kebalikanL);
        System.out.println("Nilai Tengah: " + tengah);
        System.out.println("Perbedaan: " + perbedaan);
        System.out.println("Dominan: " + dominan);
    }
}