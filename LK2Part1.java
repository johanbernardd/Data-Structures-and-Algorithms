import java.util.*;

class GerbongKereta {
    private String namaGerbong;
    private int kapasitas;
    private int penumpang;

    public GerbongKereta(String namaGerbong, int kapasitas) {
        this.namaGerbong = namaGerbong;
        this.kapasitas = kapasitas;
        this.penumpang = 0;
    }

    public String getNamaGerbong() {
        return namaGerbong;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public int getPenumpang() {
        return penumpang;
    }

    public boolean tambahPenumpang(int jumlahPenumpang) {
        if (penumpang + jumlahPenumpang <= kapasitas) {
            penumpang += jumlahPenumpang;
            return true;
        }
        return false;
    }

    public void kurangiGerbong() {
        penumpang = 0;
    }
}

public class LK2Part1 {
    public static void main(String[] args) {
        LinkedList<GerbongKereta> jenisGerbong = new LinkedList<>();
        jenisGerbong.add(new GerbongKereta("Eksekutif", 10));
        jenisGerbong.add(new GerbongKereta("Bisnis", 15));
        jenisGerbong.add(new GerbongKereta("Ekonomi", 20));
        jenisGerbong.add(new GerbongKereta("Makanan", 0)); // Capacity 0 for Makanan

        Scanner sc = new Scanner(System.in);

        // Process the commands
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("TIKET")) {
                handleTicketInput(sc, jenisGerbong);
                break;
            } else if (input.startsWith("INSERT")) {
                handleInsert(input, jenisGerbong);
            }
        }

        // Print the train cars
        printTrainCars(jenisGerbong);
    }

    private static void handleInsert(String input, LinkedList<GerbongKereta> jenisGerbong) {
        String[] kode = input.split(" ");
        if (kode.length < 2)
            return;

        switch (kode[1]) {
            case "G1":
                jenisGerbong.get(0).tambahPenumpang(10);
                break;

            case "G2":
                jenisGerbong.get(1).tambahPenumpang(15);
                break;

            case "G3":
                jenisGerbong.get(2).tambahPenumpang(20);
                break;
        }
    }

    private static void handleTicketInput(Scanner sc, LinkedList<GerbongKereta> jenisGerbong) {
        int eksekutif = sc.nextInt();
        int bisnis = sc.nextInt();
        int ekonomi = sc.nextInt();

        jenisGerbong.get(0).tambahPenumpang(eksekutif);
        jenisGerbong.get(1).tambahPenumpang(bisnis);
        jenisGerbong.get(2).tambahPenumpang(ekonomi);
    }

    private static void printTrainCars(LinkedList<GerbongKereta> jenisGerbong) {
        System.out.println("Start");

        for (GerbongKereta gerbong : jenisGerbong) {
            if (gerbong.getKapasitas() > 0) {
                System.out.println("Gerbong " + gerbong.getNamaGerbong() + " ke-1 terisi " + gerbong.getPenumpang()
                        + " penumpang");
                if (gerbong.getPenumpang() > 0)
                    System.out.println("Mengurangi " + gerbong.getNamaGerbong());
            }
        }

        System.out.println("Gerbong Makanan");

        // Print the train cars again for Makanan
        for (GerbongKereta gerbong : jenisGerbong) {
            if (gerbong.getKapasitas() == 0) {
                System.out.println("Gerbong Makanan");
                break;
            }
        }

        System.out.println("End");
    }
}
