import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nim = scanner.nextLine();

        if (nim.length() != 8) {
            System.out.println("NIM harus 8 karakter");
            return;
        }

        String prefix = nim.substring(0, 3);
        String namaProdi = "";

        if (prefix.equals("11S")) {
            namaProdi = "Sarjana Informatika";
        } else if (prefix.equals("12S")) {
            namaProdi = "Sarjana Sistem Informasi";
        } else if (prefix.equals("13S")) {
            namaProdi = "Sarjana Teknik Elektro";
        } else if (prefix.equals("21S")) {
            namaProdi = "Sarjana Manajemen Rekayasa";
        } else if (prefix.equals("22S")) {
            namaProdi = "Sarjana Teknik Metalurgi";
        } else if (prefix.equals("31S")) {
            namaProdi = "Sarjana Teknik Bioproses";
        } else if (prefix.equals("32S")) {
            namaProdi = "Sarjana Bioteknologi";
        } else if (prefix.equals("114")) {
            namaProdi = "Diploma 4 Teknologi Rekasaya Perangkat Lunak";
        } else if (prefix.equals("113")) {
            namaProdi = "Diploma 3 Teknologi Informasi";
        } else if (prefix.equals("133")) {
            namaProdi = "Diploma 3 Teknologi Komputer";
        } else {
            System.out.println("Kode tidak tersedia");
            return;
        }

        String angkatanString = nim.substring(3, 5);
        int angkatan = Integer.parseInt("20" + angkatanString);
        int urutan = Integer.parseInt(nim.substring(5, 8));

        System.out.println("Inforamsi NIM " + nim + ": ");
        System.out.println(">> Program Studi: " + namaProdi);
        System.out.println(">> Angkatan: " + angkatan);
        System.out.println(">> Urutan: " + urutan);

    }
}