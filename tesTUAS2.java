// Andhika Pratama Putra
// 235150707111046
// Tugas Akhir ASD

import java.util.Scanner;

class Mahasiswa {

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";

    private String nama;
    private String nim;
    private double nilai;

    public Mahasiswa(String nama, String nim, double nilai) {
        this.nama = nama;
        this.nim = nim;
        this.nilai = nilai;
    }

    public String getNama() {
        return this.nama;
    }

    public String getNim() {
        return this.nim;
    }

    public double getNilai() {
        return this.nilai;
    }

    @Override
    public String toString() {
        return GREEN + "Mahasiswa " +
                "Nama : " + RESET + nama + GREEN +
                "\t\tNIM : " + RESET + nim + GREEN +
                "\t\tNilai : " + RESET + nilai;
    }
}

public class tesTUAS2 {
    // warna sout
    private static final String RESET = "\u001B[0m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Mahasiswa[] mahasiswaArray = {
                new Mahasiswa("Andhi", "235150707111046", 85.5),
                new Mahasiswa("Bagus", "235150701111033", 82.0),
                new Mahasiswa("Cici", "235150707111056", 90.0),
                new Mahasiswa("Dimas", "235150701111022", 82.0),
                new Mahasiswa("Eka", "235150707111035", 88.0),
                new Mahasiswa("Rafif", "235150701111046", 86.0)
        };

        System.out.println();
        System.out.println(YELLOW + "Selamat datang di Aplikasi Sorting dan Pengelolaan Mahasiswa" + RESET);

        while (true) {
            try {
                System.out.println();
                System.out.println(YELLOW + "Pilih opsi? (1/2/3/4/5/6): " + RESET);
                System.out.println("1. Tambah Mahasiswa Berdasarkan Nama");
                System.out.println("2. Hapus Mahasiswa Berdasarkan Nama");
                System.out.println("3. Urutkan Berdasarkan Nama (Selection sort)");
                System.out.println("4. Urutkan Berdasarkan NIM (Merge sort)");
                System.out.println("5. Urutkan Berdasarkan Nilai (Bubble sort)");
                System.out.println("6. Keluar");
                System.out.print("Pilihan Anda: ");

                int pilih = in.nextInt();
                in.nextLine(); // Membersihkan buffer

                if (pilih == 6) {
                    System.out.println(YELLOW + "Terima kasih telah menggunakan aplikasi ini." + RESET);
                    break;
                }

                switch (pilih) {
                    case 1:
                        // Tambah Mahasiswa
                        System.out.print("Masukkan Nama: ");
                        String nama = in.nextLine();
                        System.out.print("Masukkan NIM: ");
                        String nim = in.nextLine();
                        System.out.print("Masukkan Nilai: ");
                        double nilai = in.nextDouble();
                        in.nextLine(); // Membersihkan buffer
                        mahasiswaArray = tambahMahasiswa(mahasiswaArray, new Mahasiswa(nama, nim, nilai));
                        System.out.println("Mahasiswa berhasil ditambahkan!");
                        break;

                    case 2:
                        // Hapus Mahasiswa
                        System.out.print("Masukkan Nama Mahasiswa yang ingin dihapus: ");
                        String namaHapus = in.nextLine();
                        mahasiswaArray = hapusMahasiswa(mahasiswaArray, namaHapus);
                        break;

                    case 3:
                        // Urutkan Berdasarkan Nama
                        System.out.print("Urutkan secara ascending? (true/false): ");
                        boolean ascendingNama = in.nextBoolean();
                        in.nextLine(); // Membersihkan buffer
                        SelectionSort(mahasiswaArray, ascendingNama);
                        System.out.println(YELLOW + "\nHasil pengurutan berdasarkan Nama:" + RESET);
                        tampilkanMahasiswa(mahasiswaArray);
                        break;

                    case 4:
                        // Urutkan Berdasarkan NIM
                        System.out.print("Urutkan secara ascending? (true/false): ");
                        boolean ascendingNIM = in.nextBoolean();
                        in.nextLine(); // Membersihkan buffer
                        MergeSort(mahasiswaArray, ascendingNIM);
                        System.out.println(YELLOW + "\nHasil pengurutan berdasarkan NIM:" + RESET);
                        tampilkanMahasiswa(mahasiswaArray);
                        break;

                    case 5:
                        // Urutkan Berdasarkan Nilai
                        System.out.print("Urutkan secara ascending? (true/false): ");
                        boolean ascendingNilai = in.nextBoolean();
                        in.nextLine(); // Membersihkan buffer
                        BubbleSort(mahasiswaArray, ascendingNilai);
                        System.out.println(YELLOW + "\nHasil pengurutan berdasarkan Nilai:" + RESET);
                        tampilkanMahasiswa(mahasiswaArray);
                        break;

                    default:
                        System.out.println("Pilihan tidak valid. Silakan pilih antara 1 hingga 6.");
                }
            } catch (Exception e) {
                System.out.println("Input tidak valid. Silakan coba lagi.");
                in.nextLine(); // membersihkan buffer scanner
            }
        }
        in.close();
    }

    public static Mahasiswa[] tambahMahasiswa(Mahasiswa[] array, Mahasiswa mahasiswaBaru) {
        Mahasiswa[] arrayBaru = new Mahasiswa[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            arrayBaru[i] = array[i];
        }
        arrayBaru[array.length] = mahasiswaBaru; // tambahkan mahasiswa baru di indeks terakhir
        return arrayBaru;
    }

    public static Mahasiswa[] hapusMahasiswa(Mahasiswa[] array, String nama) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i].getNama().equalsIgnoreCase(nama)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Mahasiswa dengan nama \"" + GREEN + nama + RESET + "\" tidak ditemukan!");
            return array; // tidak ada perubahan jika nama tidak ditemukan
        }

        Mahasiswa[] arrayBaru = new Mahasiswa[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i == index) {
                System.out.println("Mahasiswa dengan nama \"" + GREEN + nama + RESET + "\" berhasil dihapus!");
                continue; // ;ewati elemen yang dihapus
            }
            arrayBaru[j] = array[i];
            j++;
        }
        return arrayBaru;
    }

    public static void tampilkanMahasiswa(Mahasiswa[] array) {
        for (Mahasiswa m : array) {
            System.out.println(m);
        }
        System.out.println();
    }

    // (mergesort) mengurutkan berdasarkan nim
    public static void MergeSort(Mahasiswa[] array, boolean ascending){
        
        int length = array.length;
        if (length <= 1) return;
        
        // membagi array
        int middle = length / 2;
        Mahasiswa[] leftArray = new Mahasiswa[middle];
        Mahasiswa[] rightArray = new Mahasiswa[length - middle];
        
        int i = 0;
        int j = 0;
        
        // membagi element ke array kiri dan kanan
        for (; i < length; i++) {
            if (i < middle) {
                leftArray[i] = array[i];
            } else {
                rightArray[j] = array[i];
                j++;
            }
        }

        // rekursif membagi lebih kecil
        MergeSort(leftArray, ascending);
        MergeSort(rightArray, ascending);

        // menggabungkan kembali
        merge(leftArray, rightArray, array, ascending);
        
    }
    
    // untuk mengurutkan array
    private static void merge(Mahasiswa[] leftArray, Mahasiswa[] rightArray, Mahasiswa[] array, boolean ascending){
        
        int leftSize = leftArray.length;
        int rightSize = rightArray.length;
        int i = 0, l = 0, r = 0;
        
        // menggabungkan element dari array kiri dan kanan ke array utama
        while (l < leftSize && r < rightSize) {
            long nimLeft = Long.parseLong(leftArray[l].getNim());
            long nimRight = Long.parseLong(rightArray[r].getNim());

            if (ascending) {
                if (nimLeft < nimRight) {
                    array[i] = leftArray[l];
                    i++;
                    l++;
                } else {
                    array[i] = rightArray[r];
                    i++;
                    r++;
                }
            } else { // descending
                if (nimLeft > nimRight) {
                    array[i] = leftArray[l];
                    i++;
                    l++;
                } else {
                    array[i] = rightArray[r];
                    i++;
                    r++;
                }
            }
            
        }
        
        // menyalin element yang tersisa di array
        while (l < leftSize) {
            array[i] = leftArray[l];
            i++;
            l++;
        }
    
        while (r < rightSize) {
            array[i] = rightArray[r];
            i++;
            r++;
        }
    }

    // (selectionsort) mengurutkan sesuai nama
    public static void SelectionSort(Mahasiswa[] array, boolean ascending) {
        for (int i = 0; i < array.length; i++) {
            int min = i;

            if (ascending) {
                for (int j = i + 1; j < array.length; j++) {
                    if (array[min].getNama().compareToIgnoreCase(array[j].getNama()) > 0) {
                        min = j;                    
                    }
                }
            } else {
                for (int j = i + 1; j < array.length; j++) {
                    if (array[min].getNama().compareToIgnoreCase(array[j].getNama()) < 0) {
                        min = j;                    
                    }
                }
            }
            Mahasiswa temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }

    // (bubblesort) berdasarkan nilai 
    public static void BubbleSort(Mahasiswa array[], boolean ascending){
        if (ascending) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j].getNilai() > array[j+1].getNilai()) {
                        Mahasiswa temp = array[j];
                        array[j] = array[j+1];
                        array[j+1] = temp;
                    }
                }
            }    
        } else {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j].getNilai() < array[j+1].getNilai()) {
                        Mahasiswa temp = array[j];
                        array[j] = array[j+1];
                        array[j+1] = temp;
                    }
                }
            }
        }
        
    }
}
