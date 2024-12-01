import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> nilaiMahasiswa = new HashMap<>();

        // Input data awal 5 siswa
        System.out.println("Masukkan data untuk 5 Mahasiswa:");
        for (int i = 1; i <= 5; i++) {
            System.out.print( + i + "Nama : ");
            String nama = scanner.nextLine();
            int nilai = 0;

            // Validasi input nilai dengan exception handling
            while (true) {
                System.out.print( + i + "Nilai : ");
                try {
                    nilai = Integer.parseInt(scanner.nextLine());
                    if (nilai < 0 || nilai > 100) {
                        System.out.println("Nilai harus antara 0 dan 100. Coba lagi.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input harus berupa angka. Coba lagi.");
                }
            }

            nilaiMahasiswa.put(nama, nilai);
        }

        // Loop untuk menu utama
        boolean selesai = false;
        while (!selesai) {
            System.out.println("\nPilih aksi:");
            System.out.println("1. Ubah nilai Mahasiswa");
            System.out.println("2. Tampilkan data Mahasiswa");
            System.out.println("3. Cari nilai Mahasiswa");
            System.out.println("4. Urutkan data Mahasiswa");
            System.out.println("5. Hapus data Mahasiswa");
            System.out.println("6. Keluar");
            System.out.print("Pilihan Anda: ");

            int pilihan = 0;

            // Validasi input untuk memastikan hanya angka yang diterima
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka 1, 2, 3, 4, 5, atau 6.");
                continue; // Kembali ke awal loop
            }

            switch (pilihan) {
                case 1:
                    // Mengubah nilai siswa
                    System.out.print("Masukkan nama Mahasiswa yang ingin diubah nilainya: ");
                    String namaUbah = scanner.nextLine();
                    if (nilaiMahasiswa.containsKey(namaUbah)) {
                        int nilaiBaru = 0;
                        while (true) {
                            System.out.print("Masukkan nilai baru untuk " + namaUbah + ": ");
                            try {
                                nilaiBaru = Integer.parseInt(scanner.nextLine());
                                if (nilaiBaru < 0 || nilaiBaru > 100) {
                                    System.out.println("Nilai harus antara 0 dan 100. Coba lagi.");
                                } else {
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Input harus berupa angka. Coba lagi.");
                            }
                        }
                        nilaiMahasiswa.put(namaUbah, nilaiBaru);
                        System.out.println("Nilai " + namaUbah + " berhasil diubah.");
                    } else {
                        System.out.println("Nama Mahasiswa tidak ditemukan.");
                    }
                    break;

                case 2:
                    // Menampilkan data siswa
                    System.out.println("\nData Mahasiswa saat ini:");
                    tampilkanData(nilaiMahasiswa);
                    break;

                case 3:
                    // Mencari nilai siswa
                    System.out.print("Masukkan nama Mahasiswa yang ingin dicari: ");
                    String namaCari = scanner.nextLine();
                    if (nilaiMahasiswa.containsKey(namaCari)) {
                        System.out.println("Nilai " + namaCari + ": " + nilaiMahasiswa.get(namaCari));
                    } else {
                        System.out.println("Nama Mahasiswa tidak ditemukan.");
                    }
                    break;

                case 4:
                    // Mengurutkan data siswa
                    System.out.println("\nPilih cara pengurutan:");
                    System.out.println("1. Berdasarkan nama (abjad)");
                    System.out.println("2. Berdasarkan nilai (ascending)");
                    System.out.println("3. Berdasarkan nilai (descending)");
                    System.out.print("Pilihan Anda: ");
                    int opsiSort = Integer.parseInt(scanner.nextLine());

                    List<Map.Entry<String, Integer>> dataList = new ArrayList<>(nilaiMahasiswa.entrySet());

                    switch (opsiSort) {
                        case 1:
                            dataList.sort(Map.Entry.comparingByKey());
                            break;
                        case 2:
                            dataList.sort(Map.Entry.comparingByValue());
                            break;
                        case 3:
                            dataList.sort(Map.Entry.<String, Integer>comparingByValue().reversed());
                            break;
                        default:
                            System.out.println("Pilihan tidak valid. Tidak ada perubahan.");
                            continue;
                    }

                    System.out.println("\nData yang diurutkan:");
                    for (Map.Entry<String, Integer> entry : dataList) {
                        System.out.println("Nama: " + entry.getKey() + ", Nilai: " + entry.getValue());
                    }
                    break;

                case 5:
                    // Menghapus data siswa
                    System.out.print("Masukkan Mahanama siswa yang ingin dihapus: ");
                    String namaHapus = scanner.nextLine();
                    if (nilaiMahasiswa.containsKey(namaHapus)) {
                        nilaiMahasiswa.remove(namaHapus);
                        System.out.println("Data Mahasiswa " + namaHapus + " berhasil dihapus.");
                    } else {
                        System.out.println("Nama Mahasiswa tidak ditemukan.");
                    }
                    break;

                case 6:
                    // Keluar
                    selesai = true;
                    System.out.println("Program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Masukkan angka 1, 2, 3, 4, 5, atau 6.");
            }
        }

        scanner.close();
    }

    // Method untuk menampilkan data
    public static void tampilkanData(HashMap<String, Integer> map) {
        if (map.isEmpty()) {
            System.out.println("Tidak ada data yang tersedia.");
        } else {
            for (String nama : map.keySet()) {
                System.out.println("Nama: " + nama + ", Nilai: " + map.get(nama));
            }
        }
    }
}