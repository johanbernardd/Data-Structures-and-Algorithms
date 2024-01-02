import java.util.*;

class Penumpang {
    private String nama;
    private int tujuanStasiun;

    public Penumpang(String nama, int tujuanStasiun) {
        this.nama = nama;
        this.tujuanStasiun = tujuanStasiun;
    }

    public String getNama() {
        return nama;
    }

    public int getTujuanStasiun() {
        return tujuanStasiun;
    }
}

enum Stasiun {
    SURABAYA("Surabaya"),
    JOGJAKARTA("Jogjakarta"),
    JAKARTA("Jakarta");

    private final String namaStasiun;

    Stasiun(String namaStasiun) {
        this.namaStasiun = namaStasiun;
    }

    public String getNamaStasiun() {
        return namaStasiun;
    }
}

class KonfigurasiStasiunPenumpang {
    private List<Stack<Penumpang>> stasiunkereta;

    public KonfigurasiStasiunPenumpang() {
        stasiunkereta = new ArrayList<>(Stasiun.values().length);
        initStack();
    }

    private void initStack() {
        for (int i = 0; i < Stasiun.values().length; i++) {
            stasiunkereta.add(new Stack<>());
        }
    }

    public void tambahKonfigurasi(String konfigurasi) {
        String[] split = konfigurasi.split(" ");
        String sukuSatu = split[0];
        int kodeStasiun = Integer.parseInt(split[1]);
        Penumpang penumpang = new Penumpang(sukuSatu, kodeStasiun);
        stasiunkereta.get(kodeStasiun).push(penumpang);
    }

    public void outputTampilan() {
        for (int i = 0; i < Stasiun.values().length; i++) {
            Stasiun stasiun = Stasiun.values()[i];
            System.out.println("Stasiun " + stasiun.getNamaStasiun());

            if (!stasiunkereta.get(i).isEmpty()) {
                System.out.println("Penumpang yang bertujuan di stasiun ini:");
                for (Penumpang penumpang : stasiunkereta.get(i)) {
                    System.out.println(penumpang.getNama());
                }
            } else {
                System.out.println("Tidak ada penumpang yang bertujuan di stasiun " + stasiun.getNamaStasiun());
            }

            if (i > 0) {
                Stack<Penumpang> kembaliKereta = stasiunkereta.get(i - 1);

                if (!kembaliKereta.isEmpty()) {
                    System.out.println("Penumpang yang kembali ke kereta:");
                    for (Penumpang penumpang : kembaliKereta) {
                        System.out.println(penumpang.getNama());
                    }
                } else {
                    System.out.println("Tidak ada penumpang yang keluar kereta di stasiun "
                            + Stasiun.values()[i - 1].getNamaStasiun());
                }
            }
            System.out.println();
        }
        System.out.println("Sampai di stasiun terakhir");
    }
}

public class LK3_Stack {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        KonfigurasiStasiunPenumpang konfig = new KonfigurasiStasiunPenumpang();

        while (true) {
            String command = input.nextLine();
            if (command.equals("EXIT")) {
                break;
            }
            konfig.tambahKonfigurasi(command);
        }
        konfig.outputTampilan();
    }
}