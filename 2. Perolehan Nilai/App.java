import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int bobotPA = scanner.nextInt();
        int bobotT = scanner.nextInt();
        int bobotK = scanner.nextInt();
        int bobotP = scanner.nextInt();
        int bobotUTS = scanner.nextInt();
        int bobotUAS = scanner.nextInt();
        
        int totalBobot = bobotPA + bobotT + bobotK + bobotP + bobotUTS + bobotUAS;

        if (totalBobot != 100) {
            System.out.println("Total bobot harus 100");
            return; 
        }

        double totalPA = 0;
        double totalT = 0;
        double totalK = 0;
        double totalP = 0;
        double totalUTS = 0;
        double totalUAS = 0;
        double perolehanPA = 0;
        double perolehanT = 0;
        double perolehanK = 0;
        double perolehanP = 0;
        double perolehanUTS = 0;
        double perolehanUAS = 0;

        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("---")) {
                break;
            }
            String[] bagian = input.split("\\|");
            if (bagian.length != 3) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                continue;
            }
            String simbol = bagian[0].trim();
            String bobotStr = bagian[1].trim();
            String perolehanStr = bagian[2].trim();
            double bobot;
            double perolehan;
            try {
                bobot = Double.parseDouble(bobotStr);
                perolehan = Double.parseDouble(perolehanStr);
            } catch (NumberFormatException e) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                continue;
            }
            if (!simbol.equals("PA") && !simbol.equals("T") && !simbol.equals("K")
                    && !simbol.equals("P") && !simbol.equals("UTS") && !simbol.equals("UAS")) {
                System.out.println("Simbol tidak dikenal");
                continue;
            }

            perolehan = Math.max(0, Math.min(perolehan, bobot));
            switch (simbol) {
                case "PA":
                    totalPA += bobot;
                    perolehanPA += perolehan;
                    break;
                case "T":
                    totalT += bobot;
                    perolehanT += perolehan;
                    break;
                case "K":
                    totalK += bobot;
                    perolehanK += perolehan;
                    break;
                case "P":
                    totalP += bobot;
                    perolehanP += perolehan;
                    break;
                case "UTS":
                    totalUTS += bobot;
                    perolehanUTS += perolehan;
                    break;
                case "UAS":
                    totalUAS += bobot;
                    perolehanUAS += perolehan;
                    break;
            }
        }

        int persentasePA = percentage(perolehanPA, totalPA);
        int persentaseT = percentage(perolehanT, totalT);
        int persentaseK = percentage(perolehanK, totalK);
        int persentaseP = percentage(perolehanP, totalP);
        int persentaseUTS = percentage(perolehanUTS, totalUTS);
        int persentaseUAS = percentage(perolehanUAS, totalUAS);

        double nilaiPA = persentasePA / 100.0 * bobotPA;
        double nilaiT = persentaseT / 100.0 * bobotT;
        double nilaiK = persentaseK / 100.0 * bobotK;
        double nilaiP = persentaseP / 100.0 * bobotP;
        double nilaiUTS = persentaseUTS / 100.0 * bobotUTS;
        double nilaiUAS = persentaseUAS / 100.0 * bobotUAS;

        double nilaiAkhir = nilaiPA + nilaiT + nilaiK + nilaiP + nilaiUTS + nilaiUAS;
        nilaiAkhir = Math.round(nilaiAkhir * 100.0) / 100.0;

        System.out.println("Perolehan Nilai:");
        printComponent("Partisipatif", persentasePA, nilaiPA, bobotPA);
        printComponent("Tugas", persentaseT, nilaiT, bobotT);
        printComponent("Kuis", persentaseK, nilaiK, bobotK);
        printComponent("Proyek", persentaseP, nilaiP, bobotP);
        printComponent("UTS", persentaseUTS, nilaiUTS, bobotUTS);
        printComponent("UAS", persentaseUAS, nilaiUAS, bobotUAS);
        System.out.println();
        System.out.printf(Locale.US, ">> Nilai Akhir: %.2f%n", nilaiAkhir);
        System.out.println(">> Grade: " + grade(nilaiAkhir));
    }

    private static int percentage(double perolehan, double total) {
        return total == 0 ? 0 : (int) (perolehan / total * 100 + 1e-9);
    }

    private static void printComponent(String nama, int persentase, double perolehan, double total) {
        System.out.printf(Locale.US, ">> %s: %d/100 (%.2f/%.0f)%n", nama, persentase, perolehan, total);
    }

    private static String grade(double nilaiAkhir) {
        double epsilon = 1e-9;
        if (nilaiAkhir + epsilon >= 79.5) {
            return "A";
        } else if (nilaiAkhir + epsilon >= 72) {
            return "AB";
        } else if (nilaiAkhir + epsilon >= 64.5) {
            return "B";
        } else if (nilaiAkhir + epsilon >= 57) {
            return "BC";
        } else if (nilaiAkhir + epsilon >= 49.5) {
            return "C";
        } else if (nilaiAkhir + epsilon >= 34) {
            return "D";
        }
        return "E";
    }
}
