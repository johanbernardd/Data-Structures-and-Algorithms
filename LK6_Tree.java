import java.util.*;

class Silsilah {
    String nama;
    Silsilah kepala;
    LinkedList<Silsilah> keturunan;

    public Silsilah(String nama, Silsilah kepala) {
        this.nama = nama;
        this.kepala = kepala;
        this.keturunan = new LinkedList<>();
    }

    public void tambahKeturunan(String namaAnak) {
        keturunan.add(new Silsilah(namaAnak, this));
    }
}

public class LK6_Tree {
    private Silsilah root;

    public LK6_Tree(String asalRoot) {
        this.root = new Silsilah(asalRoot, null);
    }

    public void tambahAnggota(String namaInduk, String namaAnak) {
        Silsilah nodeInduk = cari(root, namaInduk);
        if (nodeInduk != null) {
            nodeInduk.tambahKeturunan(namaAnak);
        } else {
            System.out.println("TIDAK ADA " + namaInduk + " DI KELUARGA " + root.nama);
        }
    }

    public void cetakSilsilah() {
        System.out.println("KELUARGA " + root.nama + " :");
        cetakSilsilah(root, 0);
    }

    private void cetakSilsilah(Silsilah curr, int tingkatan) {
        StringBuilder space = new StringBuilder();
        for (int i = 1; i < tingkatan; i++) {
            space.append("  ");
        }
        if (tingkatan > 0) {
            space.append("-- ");
        }
        System.out.println(space.toString() + curr.nama);

        for (Silsilah silsilah : curr.keturunan) {
            cetakSilsilah(silsilah, tingkatan + 1);
        }
    }

    private Silsilah cari(Silsilah curr, String nama) {
        if (curr == null || curr.nama.equals(nama)) {
            return curr;
        }

        for (Silsilah silsilah : curr.keturunan) {
            Silsilah hasil = cari(silsilah, nama);
            if (hasil != null) {
                return hasil;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        /*
         * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
         * class should be named Solution.
         */
        Scanner sc = new Scanner(System.in);
        LK6_Tree orangTua = new LK6_Tree("AHMAD");

        while (true) {
            String command = sc.nextLine();

            if (command.equals("END")) {
                break;
            }

            String[] split = command.split(" ");
            orangTua.tambahAnggota(split[0], split[1]);
        }
        orangTua.cetakSilsilah();
    }
}