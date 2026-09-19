import java.util.Scanner;

public class App
 {
    static boolean semuaDigit(String s) {
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') return false;
        }
        return true;
    }

    // return total menit, atau -1 kalau tidak valid
    static int parseJam(String s) {
        String[] p = s.split(":", -1);
        if (p.length != 2) return -1;
        int h, m;
        try {
            h = Integer.parseInt(p[0].trim());
            m = Integer.parseInt(p[1].trim());
        } catch (NumberFormatException e) {
            return -1;
        }
        if (h < 0 || h > 23 || m < 0 || m > 59) return -1;
        return h * 60 + m;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            System.out.println("Jam tidak valid");
            return;
        }
        int mulai = parseJam(sc.nextLine().trim());
        if (mulai < 0) {
            System.out.println("Jam tidak valid");
            return;
        }

        long cur = mulai, total = 0, hari = 0;

        while (sc.hasNextLine()) {
            String cmd = sc.nextLine().trim();
            if (cmd.equals("---")) break;

            char tanda = cmd.isEmpty() ? ' ' : cmd.charAt(0);
            if ((tanda != '+' && tanda != '-') || !semuaDigit(cmd.substring(1))) {
                System.out.println("Perintah tidak valid");
                continue;
            }
            long d;
            try {
                d = Long.parseLong(cmd.substring(1));
            } catch (NumberFormatException e) {
                System.out.println("Perintah tidak valid");
                continue;
            }
            if (tanda == '-') d = -d;

            total += d;
            cur += d;
            hari += Math.abs(Math.floorDiv(cur, 1440L)); // jumlah lintas batas 24 jam
            cur = Math.floorMod(cur, 1440L);             // normalisasi 0..1439
        }

        System.out.println(String.format("Jam Awal: %02d:%02d", mulai / 60, mulai % 60));
        System.out.println(String.format("Jam Akhir: %02d:%02d", cur / 60, cur % 60));
        System.out.println("Total Menit: " + (total > 0 ? "+" + total : String.valueOf(total)));
        System.out.println("Pergantian Hari: " + hari);
    }
}