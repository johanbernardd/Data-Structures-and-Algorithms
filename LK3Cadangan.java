import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Penumpang {
    private String nama;
    private String tujuanStasiun;

    public Penumpang(String nama, int kodeStasiun) {
        this.nama = nama;
        switch (kodeStasiun) {
            case 1:
                this.tujuanStasiun = "Surabaya";
                break;
            case 2:
                this.tujuanStasiun = "Jogjakarta";
                break;
            case 3:
                this.tujuanStasiun = "Jakarta";
                break;
        }
    }

    public String getNama() {
        return nama;
    }

    public String getTujuanStasiun() {
        return tujuanStasiun;
    }
}

class Kereta {
    private Stack<Penumpang> stasiunkereta;
    private Stack<Penumpang> naik;
    private List<Penumpang> turun;

    public Kereta() {
        stasiunkereta = new Stack<>();
        naik = new Stack<>();
        turun = new LinkedList<>();
    }

    public void tambahPenumpang(Penumpang penumpang) {
        stasiunkereta.push(penumpang);
    }

    public void penumpangTurunKereta(String tujuanStasiun) {
        int cekBerulang;
        int defaultIndex = -1;

        cekBerulang = stasiunkereta.size();
        for (int i = cekBerulang - 1; i > -1; i--) {
            if (stasiunkereta.get(i).getTujuanStasiun().equals(tujuanStasiun)) {
                defaultIndex = i;
            }
        }
        System.out.println("Stasiun " + tujuanStasiun);
        if (defaultIndex > -1) {
            cekBerulang = stasiunkereta.size();
            for (int i = cekBerulang - 1; i > (defaultIndex - 1); i--) {
                if (stasiunkereta.peek().getTujuanStasiun().equals(tujuanStasiun)) {
                    turun.add(stasiunkereta.pop());
                } else {
                    naik.push(stasiunkereta.pop());
                }
            }

            System.out.println("Penumpang yang bertujuan di stasiun ini :");
            for (Penumpang pass : turun) {
                System.out.print(pass.getNama() + " ");
            }
            System.out.println();

            if (tujuanStasiun.equals("Jakarta")) {
                System.out.println("Sampai di stasiun terakhir");
            } else if (!naik.isEmpty()) {
                System.out.println("Penumpang yang kembali ke kereta :");
                cekBerulang = naik.size();
                for (int i = 0; i < cekBerulang; i++) {
                    System.out.print(naik.peek().getNama() + " ");
                    stasiunkereta.push(naik.pop());
                }
            } else {
                System.out.print("Tidak ada penumpang yang keluar kereta di stasiun " + tujuanStasiun);
            }
        } else {
            System.out.print("Tidak ada penumpang yang bertujuan di stasiun " + tujuanStasiun);
        }
        naik.clear();
        turun.clear();
        System.out.println();
        System.out.println();
    }
}

public class LK3Cadangan {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Kereta kereta = new Kereta();

        ulangi: while (true) {
            String command = input.nextLine();
            String[] split = command.split(" ");
            if (command.equals("EXIT")) {
                break ulangi;
            }

            Penumpang penumpang = new Penumpang(split[0], Integer.parseInt(split[1]));
            kereta.tambahPenumpang(penumpang);
        }

        kereta.penumpangTurunKereta("Surabaya");
        kereta.penumpangTurunKereta("Jogjakarta");
        kereta.penumpangTurunKereta("Jakarta");
    }
}