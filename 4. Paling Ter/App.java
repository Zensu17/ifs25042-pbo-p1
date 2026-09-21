import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class App {
    private static TreeMap<Long, Integer> bacaFrekuensi(BufferedReader reader) throws IOException {
        TreeMap<Long, Integer> frekuensi = new TreeMap<>();
        String baris;
        while ((baris = reader.readLine()) != null) {
            baris = baris.trim();
            if (baris.equals("---")) break;
            if (baris.isEmpty()) continue;
            try {
                frekuensi.merge(Long.parseLong(baris), 1, Integer::sum);
            } catch (NumberFormatException e) {
                System.out.println("Data tidak valid");
            }
        }
        return frekuensi;
    }

    private static String ringkasan(TreeMap<Long, Integer> frekuensi) {
        long nilaiTerbanyak = 0, nilaiTersedikit = 0, nilaiJumlahTertinggi = 0, nilaiJumlahTerendah = 0;
        int terbanyak = Integer.MIN_VALUE, tersedikit = Integer.MAX_VALUE;
        long jumlahTertinggi = Long.MIN_VALUE, jumlahTerendah = Long.MAX_VALUE;

        // Iterasi menaik. Saat seri, ">=" memilih nilai yang lebih besar, "<" mempertahankan yang lebih kecil.
        for (Map.Entry<Long, Integer> entri : frekuensi.entrySet()) {
            long nilai = entri.getKey();
            int banyak = entri.getValue();
            long jumlah = nilai * banyak;

            if (banyak >= terbanyak) { terbanyak = banyak; nilaiTerbanyak = nilai; }
            if (banyak < tersedikit) { tersedikit = banyak; nilaiTersedikit = nilai; }
            if (jumlah >= jumlahTertinggi) { jumlahTertinggi = jumlah; nilaiJumlahTertinggi = nilai; }
            if (jumlah < jumlahTerendah) { jumlahTerendah = jumlah; nilaiJumlahTerendah = nilai; }
        }

        return "Tertinggi: " + frekuensi.lastKey() + "\n"
                + "Terendah: " + frekuensi.firstKey() + "\n"
                + "Terbanyak: " + nilaiTerbanyak + " (" + terbanyak + "x)\n"
                + "Tersedikit: " + nilaiTersedikit + " (" + tersedikit + "x)\n"
                + "Jumlah Tertinggi: " + nilaiJumlahTertinggi + " * " + frekuensi.get(nilaiJumlahTertinggi)
                + " = " + jumlahTertinggi + "\n"
                + "Jumlah Terendah: " + nilaiJumlahTerendah + " * " + frekuensi.get(nilaiJumlahTerendah)
                + " = " + jumlahTerendah + "\n";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<Long, Integer> frekuensi = bacaFrekuensi(reader);
        if (frekuensi.isEmpty()) return;
        System.out.print(ringkasan(frekuensi));
    }
}
