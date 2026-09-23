import java.util.Scanner;

public class App {
    private static final int PANJANG_NIM = 8;

    private static String namaProdi(String kode) {
        switch (kode) {
            case "11S": return "Sarjana Informatika";
            case "12S": return "Sarjana Sistem Informasi";
            case "13S": return "Sarjana Teknik Elektro";
            case "21S": return "Sarjana Manajemen Rekayasa";
            case "22S": return "Sarjana Teknik Metalurgi";
            case "31S": return "Sarjana Teknik Bioproses";
            case "32S": return "Sarjana Bioteknologi";
            case "114": return "Diploma 4 Teknologi Rekayasa Perangkat Lunak";
            case "113": return "Diploma 3 Teknologi Informasi";
            case "133": return "Diploma 3 Teknologi Komputer";
            default: return null;
        }
    }

    private static boolean semuaDigit(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') return false;
        }
        return !s.isEmpty();
    }

    private static void cetakInformasi(String nim, String namaProdi, String angkatan, String urutan) {
        System.out.println("Informasi NIM " + nim + ": ");
        System.out.println(">> Program Studi: " + namaProdi);
        System.out.println(">> Angkatan: " + Integer.parseInt("20" + angkatan));
        System.out.println(">> Urutan: " + Integer.parseInt(urutan));
    }

    // Mengorkestrasi alur: validasi -> cetak. Tidak melakukan I/O sendiri.
    private static void proses(String nim) {
        if (nim.length() != PANJANG_NIM) {
            System.out.println("NIM harus 8 karakter");
            return;
        }

        String namaProdi = namaProdi(nim.substring(0, 3));
        if (namaProdi == null) {
            System.out.println("Kode tidak tersedia");
            return;
        }

        String angkatan = nim.substring(3, 5);
        String urutan = nim.substring(5, 8);
        if (!semuaDigit(angkatan) || !semuaDigit(urutan)) {
            System.out.println("Angkatan dan urutan NIM harus berupa angka");
            return;
        }

        cetakInformasi(nim, namaProdi, angkatan, urutan);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            proses(scanner.hasNextLine() ? scanner.nextLine() : "");
        } catch (RuntimeException e) {
            System.out.println("Terjadi kesalahan saat memproses input");
        }
    }
}
