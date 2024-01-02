import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class LK8_HeapTree {

    static class MinHeap {
        int[] heapTree;
        int banyakHeapTree;

        public MinHeap(int banyakInput) {
            heapTree = new int[banyakInput];
        }

        void tambahElemen(int elemen) {
            if (kapasitasPenuh()) {
                int[] heapObjek = new int[heapTree.length * 2];
                System.arraycopy(heapTree, 0, heapObjek, 0, heapTree.length);
                heapTree = heapObjek;
            }
            heapTree[banyakHeapTree++] = elemen;
            aturHeapNaik(banyakHeapTree - 1);
        }

        boolean kapasitasPenuh() {
            return banyakHeapTree == heapTree.length;
        }

        void hapusElemen(int elemen) {
            int indeksHapusElemen = cariIndeks(elemen);
            banyakHeapTree--;
            geserKiri(indeksHapusElemen);
            aturHeapTurun(indeksHapusElemen);
        }

        void geserKiri(int start) {
            for (int i = start; i < banyakHeapTree; i++) {
                heapTree[i] = heapTree[i + 1];
            }
        }

        public int cariIndeks(int elemen) {
            int indeks = 0;
            for (int i = 0; i < banyakHeapTree; i++) {
                if (heapTree[i] == elemen)
                    indeks = i;
            }
            return indeks;
        }

        public int cariRoot() {
            if (banyakHeapTree > 0) {
                return heapTree[0];
            } else {
                return -1;
            }
        }

        void aturHeapNaik(int posisi) {
            int indeksRootElemen = cariIndeksRootElemen(posisi);
            if (indeksRootElemen < 0) {
                return;
            }
            if (heapTree[indeksRootElemen] > heapTree[posisi]) {
                tukar(indeksRootElemen, posisi);
            }
            if (posisi != indeksRootElemen) {
                aturHeapNaik(indeksRootElemen);
            }
        }

        public int cariIndeksRootElemen(int indeks) {
            return (indeks - 1) / 2;
        }

        void aturHeapTurun(int cekAwal) {
            int anakKiri = cariAnakKiri(cekAwal);
            int anakKanan = cariAnakKanan(cekAwal);
            int sekarang = cekAwal;

            if (anakKiri >= banyakHeapTree) {
                return;
            }
            if (anakKiri < banyakHeapTree && heapTree[anakKiri] < heapTree[sekarang]) {
                sekarang = anakKiri;
            }
            if (anakKanan < banyakHeapTree && heapTree[anakKanan] < heapTree[sekarang]) {
                sekarang = anakKanan;
            }
            if (sekarang != cekAwal) {
                tukar(sekarang, cekAwal);
                aturHeapTurun(sekarang);
            }
        }

        public int cariAnakKiri(int indeks) {
            return 2 * indeks + 1;
        }

        public int cariAnakKanan(int indeks) {
            return 2 * indeks + 2;
        }

        void tukar(int kondisiAwal, int kondisiAkhir) {
            int tampung = heapTree[kondisiAwal];
            heapTree[kondisiAwal] = heapTree[kondisiAkhir];
            heapTree[kondisiAkhir] = tampung;
        }
    }

    public static void main(String[] args) {
        /*
         * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
         * class should be named Solution.
         */
        Scanner sc = new Scanner(System.in);
        MinHeap tryHeap = new MinHeap(100);
        int var = sc.nextInt();

        for (int i = 0; i < var; i++) {
            int variabel = sc.nextInt();
            if (variabel == 3) {
                System.out.println(tryHeap.cariRoot());
            } else {
                int elemen = sc.nextInt();
                if (variabel == 1) {
                    tryHeap.tambahElemen(elemen);
                } else {
                    tryHeap.hapusElemen(elemen);
                }
            }
        }
    }
}