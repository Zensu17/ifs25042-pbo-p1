import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class App {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<Long, Integer> freq = new TreeMap<>();

        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.equals("---")) break;
            if (line.isEmpty()) continue;
            freq.merge(Long.parseLong(line), 1, Integer::sum);
        }

        if (freq.isEmpty()) return;

        long tertinggi = freq.lastKey();
        long terendah = freq.firstKey();

        long banyakV = 0, sedikitV = 0, jmlTinggiV = 0, jmlRendahV = 0;
        int banyakF = Integer.MIN_VALUE, sedikitF = Integer.MAX_VALUE;
        long jmlTinggi = Long.MIN_VALUE, jmlRendah = Long.MAX_VALUE;

        // iterasi ascending: nilai kecil dulu
        for (Map.Entry<Long, Integer> e : freq.entrySet()) {
            long v = e.getKey();
            int f = e.getValue();
            long prod = v * f;

            if (f >= banyakF)  { banyakF = f;  banyakV = v; }       // seri -> nilai lebih besar menimpa
            if (f < sedikitF)  { sedikitF = f; sedikitV = v; }      // seri -> nilai lebih kecil dipertahankan
            if (prod >= jmlTinggi) { jmlTinggi = prod; jmlTinggiV = v; }
            if (prod < jmlRendah)  { jmlRendah = prod; jmlRendahV = v; }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Tertinggi: ").append(tertinggi).append('\n');
        sb.append("Terendah: ").append(terendah).append('\n');
        sb.append("Terbanyak: ").append(banyakV).append(" (").append(banyakF).append("x)\n");
        sb.append("Tersedikit: ").append(sedikitV).append(" (").append(sedikitF).append("x)\n");
        sb.append("Jumlah Tertinggi: ").append(jmlTinggiV).append(" * ").append(freq.get(jmlTinggiV))
          .append(" = ").append(jmlTinggi).append('\n');
        sb.append("Jumlah Terendah: ").append(jmlRendahV).append(" * ").append(freq.get(jmlRendahV))
          .append(" = ").append(jmlRendah).append('\n');
        System.out.print(sb);
    }
}