import java.util.LinkedList;
import java.util.Scanner;

class Gerbong {
    int defaultKapasitas;
    int kapasitas;
    int penumpang;
    int gerbongTerpakai;
    int gerbongAda;
    String tipeGerbong;

    Gerbong(String tipeGerbong) {
        this.tipeGerbong = tipeGerbong;
        init();
    }

    void tambahKapasitas() {
        if (kapasitas == 0)
            kapasitas = 1;
        switch (tipeGerbong) {
            case "Eksekutif" -> {
                kapasitas += 10;
            }
            case "Bisnis" -> {
                kapasitas += 15;
            }
            case "Ekonomi" -> {
                kapasitas += 20;
            }
        }
    }

    void tambahPenumpang(int tiket) {
        boolean overload = false, kelebihan = false;

        gerbongAda = kapasitas / defaultKapasitas;
        gerbongTerpakai = (int) Math.ceil(tiket / (double) defaultKapasitas);
        if (tiket == 0) {
            System.out.printf("Mengurangi %d gerbong %s\n", gerbongAda, tipeGerbong);
            gerbongAda = gerbongTerpakai = 0;
            return;
        }
        if (gerbongTerpakai > gerbongAda) {
            overload = true;
        }
        if (gerbongAda > gerbongTerpakai) {
            kelebihan = true;
        }
        for (int i = 0; i < gerbongAda; i++) {
            if (tiket <= 0)
                break;
            if (tiket < defaultKapasitas && tiket % defaultKapasitas != 0) {
                System.out.printf("Gerbong %s ke-%d terisi %d penumpang\n", tipeGerbong, i + 1, tiket);
            } else {
                System.out.printf("Gerbong %s ke-%d terisi %d penumpang\n", tipeGerbong, i + 1, defaultKapasitas);
            }

            tiket -= defaultKapasitas;
        }
        if (overload) {
            System.out.printf("Sisa penumpang %s berjumlah %d\n", tipeGerbong, tiket);
            return;
        }
        if (kelebihan) {
            if (kapasitas == 0)
                return;
            System.out.printf("Mengurangi %d gerbong %s\n", gerbongAda - gerbongTerpakai, tipeGerbong);
            gerbongAda = gerbongTerpakai;
        }
    }

    private void init() {
        switch (tipeGerbong) {
            case "Eksekutif" -> {
                defaultKapasitas = 10;
            }
            case "Bisnis" -> {
                defaultKapasitas = 15;
            }
            case "Ekonomi" -> {
                defaultKapasitas = 20;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Gerbong))
            return false;
        Gerbong g = (Gerbong) o;
        return g.tipeGerbong.equals(this.tipeGerbong);
    }
}

public class LK2 {
    static LinkedList<Gerbong> list = new LinkedList<>();
    static int counter = 1;
    static int emptyCounter = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        list.add(new Gerbong("Makanan"));
        while (sc.hasNextLine()) {
            String query = sc.nextLine();
            if (query.contains("INSERT")) {
                handleInsert(query);
                continue;
            }
            if (query.contains("TIKET")) {
                for (int i = 0; i < 3; i++) {
                    query = sc.nextLine();
                    handlePenumpang(query);
                }
            }

        }
        if (emptyCounter == 3) {
            sc.close();
            return;
        }
        list.add(new Gerbong("Makanan"));
        System.out.println("Start");
        System.out.println("Gerbong Makanan");
        String[] tipe = { "Eksekutif", "Bisnis", "Ekonomi" };
        for (int i = 0; i < 3; i++) {
            int index = list.indexOf(new Gerbong(tipe[i]));
            if (index < 0)
                continue;
            Gerbong g = list.get(index);
            for (int j = 0; j < g.gerbongAda; j++) {
                System.out.printf("Gerbong %s\n", g.tipeGerbong);
            }
        }
        System.out.println("Gerbong Makanan");
        System.out.println("End");
        sc.close();
    }

    static void handleInsert(String query) {
        String[] parser = query.split(" ");
        String tipeGerbong = "";
        switch (parser[1]) {
            case "G1" -> {
                tipeGerbong = "Eksekutif";
            }
            case "G2" -> {
                tipeGerbong = "Bisnis";
            }
            case "G3" -> {
                tipeGerbong = "Ekonomi";
            }
        }
        // System.out.println(tipeGerbong);
        Gerbong g = new Gerbong(tipeGerbong);
        if (!list.contains(g)) {
            // System.out.println("Buat baru");
            g.tambahKapasitas();
            list.add(g);
            return;
        }
        // System.out.println("Insert yang ada");
        int indexList = list.indexOf(g);

        list.get(indexList).tambahKapasitas();
    }

    static void handlePenumpang(String query) {
        Gerbong g = null;
        int indexList, tiket = Integer.parseInt(query);
        if (counter == 1) {
            g = new Gerbong("Eksekutif");
        }
        if (counter == 2) {
            g = new Gerbong("Bisnis");
        }
        if (counter == 3) {
            g = new Gerbong("Ekonomi");
        }
        indexList = list.indexOf(g);
        if (indexList < 0) {
            System.out.printf("Kereta tidak memiliki gerbong %s\n", g.tipeGerbong);
            emptyCounter++;
            counter++;
            if (emptyCounter == 3) {
                System.out.println("Kereta tidak memiliki gerbong");
            }
            return;
        }
        list.get(indexList).tambahPenumpang(tiket);
        counter++;
    }
}