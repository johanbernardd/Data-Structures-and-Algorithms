import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

enum TipeGerbong {
    G1, G2, G3, GM
}

interface prepareMethodGerbong {
    void insertGerbong(TipeGerbong tipeGerbong);

    void sisipGerbong(TipeGerbong tipeGerbong, int indeks);

    void lepasGerbong();

    void cetakGerbong();

    void lepasGerbongSintaks(String namaGerbong);
}

class GerbongKereta {
    private String namaGerbong;

    public GerbongKereta(String namaGerbong) {
        this.namaGerbong = namaGerbong;
    }

    public String getNamaGerbong() {
        return namaGerbong;
    }
}

class rangkaian implements prepareMethodGerbong {
    private LinkedList<GerbongKereta> jenisRangkaian;

    public rangkaian() {
        jenisRangkaian = new LinkedList<>();
    }

    @Override
    public void insertGerbong(TipeGerbong tipeGerbong) {
        GerbongKereta gerbongKereta = buatGerbong(tipeGerbong);
        jenisRangkaian.add(gerbongKereta);
        System.out.println("Gerbong " + gerbongKereta.getNamaGerbong() + " berhasil ditambahkan");
    }

    @Override
    public void sisipGerbong(TipeGerbong tipeGerbong, int indeks) {
        if (indeks >= 0 && indeks <= jenisRangkaian.size()) {
            GerbongKereta gerbongKereta = buatGerbong(tipeGerbong);
            jenisRangkaian.add(indeks, gerbongKereta);
            System.out.println(
                    "Gerbong " + gerbongKereta.getNamaGerbong() + " berhasil ditambahkan pada index ke-" + indeks);
        } else {
            System.out.println("Index melebihi batas");
        }
    }

    @Override
    public void lepasGerbong() {
        if (!jenisRangkaian.isEmpty()) {
            GerbongKereta lepasKereta = jenisRangkaian.removeLast();
            System.out.println("Gerbong " + lepasKereta.getNamaGerbong() + " berhasil dilepas.");
        } else {
            System.out.println("Jumlah gerbong kosong");
        }
    }

    @Override
    public void lepasGerbongSintaks(String namaGerbong) {
        if (namaGerbong == null) {
            lepasGerbong();
        } else {
            boolean ketemu = false;
            for (GerbongKereta gerbong : jenisRangkaian) {
                if (gerbong.getNamaGerbong().equals(namaGerbong)) {
                    jenisRangkaian.remove(gerbong);
                    System.out.println("Gerbong " + namaGerbong + " berhasil dilepas.");
                    ketemu = true;
                    break;
                }
            }
            if (!ketemu) {
                System.out.println("Jumlah gerbong kosong");
            }
        }
    }

    @Override
    public void cetakGerbong() {
        if (!jenisRangkaian.isEmpty()) {
            System.out.print("Start");
            for (GerbongKereta gerbong : jenisRangkaian) {
                System.out.print(" - Gerbong " + gerbong.getNamaGerbong());
            }
            System.out.print(" - End");
            System.out.println();
        } else {
            System.out.println("Kereta tidak memiliki gerbong");
        }
    }

    private GerbongKereta buatGerbong(TipeGerbong tipeGerbong) {
        switch (tipeGerbong) {
            case G1:
                return new GerbongKereta("Eksekutif");
            case G2:
                return new GerbongKereta("Bisnis");
            case G3:
                return new GerbongKereta("Ekonomi");
            case GM:
                return new GerbongKereta("Makanan");
            default:
                return null;
        }
    }
}

public class LK1_Hackerrank {

    public static void main(String[] args) {
        /*
         * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
         * class should be named Solution.
         */
        prepareMethodGerbong kategoriGerbong = new rangkaian();
        Scanner scanner = new Scanner(System.in);
        int loop = 0;

        while (loop == 0) {
            String inputG = scanner.nextLine();
            String[] kode = inputG.split(" ");

            if (kode.length == 2) {
                if (kode[0].equals("LEPAS") && kode[1].equals("GERBONG")) {
                    kategoriGerbong.lepasGerbongSintaks(null);
                    continue;
                }
            }
            // else if (kode.length == 3) {
            // if (kode[0].equals("LEPAS") && kode[1].equals("GERBONG")) {
            // kategoriGerbong.lepasGerbongSintaks(kode[2]);
            // continue;
            // }
            // }

            if (kode[0].equals("INSERT")) {
                kategoriGerbong.insertGerbong(TipeGerbong.valueOf(kode[1]));
            } else if (kode[0].equals("SISIP")) {
                int indexSisip = Integer.parseInt(kode[1]);
                kategoriGerbong.sisipGerbong(TipeGerbong.valueOf(kode[2]), indexSisip);
            } else if (kode[0].equals("LEPAS ") && kode[1].equals("GERBONG")) {
                System.out.println("Invalid");
            } else if (kode[0].equals("CETAK")) {
                kategoriGerbong.cetakGerbong();
            } else if (kode[0].equals("SELESAI")) {
                loop = 1;
            }
        }
    }
}