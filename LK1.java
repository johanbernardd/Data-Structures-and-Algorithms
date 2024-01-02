import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class GerbongKereta {
    private String namaGerbong;

    public GerbongKereta(String namaGerbong) {
        this.namaGerbong = namaGerbong;
    }

    public String getNamaGerbong() {
        return namaGerbong;
    }
}

public class LK1 {
    public static void main(String[] args) {
        /*
         * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
         * class should be named Solution.
         */
        LinkedList<GerbongKereta> jenisGerbong = new LinkedList<>();
        jenisGerbong.add(new GerbongKereta("Eksekutif"));
        jenisGerbong.add(new GerbongKereta("Bisnis"));
        jenisGerbong.add(new GerbongKereta("Ekonomi"));
        jenisGerbong.add(new GerbongKereta("Makanan"));

        LinkedList<GerbongKereta> jenisRangkaian = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        int loop = 0;
        while (loop == 0) {
            String inputG = sc.nextLine();
            String[] kode = inputG.split(" ");
            if (kode[0].equals("INSERT")) {
                switch (kode[1]) {
                    case "G1":
                        jenisRangkaian.add(jenisGerbong.get(0));
                        System.out.println("Gerbong " + jenisGerbong.get(0).getNamaGerbong() + " berhasil ditambahkan");
                        break;

                    case "G2":
                        jenisRangkaian.add(jenisGerbong.get(1));
                        System.out.println("Gerbong " + jenisGerbong.get(1).getNamaGerbong() + " berhasil ditambahkan");
                        break;

                    case "G3":
                        jenisRangkaian.add(jenisGerbong.get(2));
                        System.out.println("Gerbong " + jenisGerbong.get(2).getNamaGerbong() + " berhasil ditambahkan");
                        break;

                    case "GM":
                        jenisRangkaian.add(jenisGerbong.get(3));
                        System.out.println("Gerbong " + jenisGerbong.get(3).getNamaGerbong() + " berhasil ditambahkan");
                        break;
                }
            } else if (kode[0].equals("SISIP")) {
                int index = Integer.parseInt(kode[1]);
                if (index >= 0 && index <= jenisRangkaian.size()) {
                    switch (kode[2]) {
                        case "G1":
                            jenisRangkaian.add(Integer.parseInt(kode[1]), jenisGerbong.get(0));
                            System.out.println("Gerbong " + jenisGerbong.get(0).getNamaGerbong()
                                    + " berhasil ditambahkan pada index ke-" + Integer.parseInt(kode[1]));
                            break;

                        case "G2":
                            jenisRangkaian.add(Integer.parseInt(kode[1]), jenisGerbong.get(1));
                            System.out.println("Gerbong " + jenisGerbong.get(1).getNamaGerbong()
                                    + " berhasil ditambahkan pada index ke-" + Integer.parseInt(kode[1]));
                            break;

                        case "G3":
                            jenisRangkaian.add(Integer.parseInt(kode[1]), jenisGerbong.get(2));
                            System.out.println("Gerbong " + jenisGerbong.get(2).getNamaGerbong()
                                    + " berhasil ditambahkan pada index ke-" + Integer.parseInt(kode[1]));
                            break;

                        case "GM":
                            jenisRangkaian.add(Integer.parseInt(kode[1]), jenisGerbong.get(3));
                            System.out.println("Gerbong " + jenisGerbong.get(3).getNamaGerbong()
                                    + " berhasil ditambahkan pada index ke-" + Integer.parseInt(kode[1]));
                            break;

                    }
                } else {
                    System.out.println("Index melebihi batas");
                }
            } else if (kode[0].equals("LEPAS")) {
                if (jenisRangkaian.size() != 0) {
                    System.out.println("Gerbong " + jenisRangkaian.getLast().getNamaGerbong() + " berhasil dilepas");
                    jenisRangkaian.removeLast();
                } else {
                    System.out.println("Jumlah gerbong kosong");
                }
            } else if (kode[0].equals("CETAK")) {
                if (jenisRangkaian.size() != 0) {
                    System.out.print("Start -");
                    int i = 0;
                    while (i < jenisRangkaian.size()) {
                        System.out.print(" Gerbong " + jenisRangkaian.get(i).getNamaGerbong() + " -");
                        if (i == (jenisRangkaian.size() - 1)) {
                            System.out.println(" End");
                        }
                        i++;
                    }
                } else {
                    System.out.println("Kereta tidak memiliki gerbong");
                }
            } else if (kode[0].equals("SELESAI")) {
                loop = 1;
            }
        }
    }
}