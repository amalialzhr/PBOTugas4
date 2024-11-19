import java.util.Scanner;

// Kelas induk untuk Faktur
class Faktur {
    protected String noFaktur;
    protected String kodeBarang;
    protected String namaBarang;
    protected double hargaBarang;
    protected int jumlahBeli;
    protected double total;

    // Constructor
    public Faktur(String noFaktur, String kodeBarang, String namaBarang, double hargaBarang, int jumlahBeli) {
        this.noFaktur = noFaktur;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.jumlahBeli = jumlahBeli;
        this.total = hargaBarang * jumlahBeli; // Menghitung total
    }

    // Method untuk menampilkan informasi faktur
    public void tampilkanInfo() {
        System.out.println("No Faktur: " + noFaktur);
        System.out.println("Kode Barang: " + kodeBarang);
        System.out.println("Nama Barang: " + namaBarang);
        System.out.println("Harga Barang: " + hargaBarang);
        System.out.println("Jumlah Beli: " + jumlahBeli);
        System.out.println("Total: " + total);
    }
}

// Kelas turunan untuk FakturPenjualan
class FakturPenjualan extends Faktur {
    // Constructor
    public FakturPenjualan(String noFaktur, String kodeBarang, String namaBarang, double hargaBarang, int jumlahBeli) {
        super(noFaktur, kodeBarang, namaBarang, hargaBarang, jumlahBeli);
    }
}

// Kelas utama
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Input dari pengguna
            String noFaktur = mintaInput(scanner, "Masukkan No Faktur (harus angka): ", true);
            String kodeBarang = mintaInput(scanner, "Masukkan Kode Barang: ", false);
            String namaBarang = mintaInput(scanner, "Masukkan Nama Barang: ", false);
            double hargaBarang = Double.parseDouble(mintaInput(scanner, "Masukkan Harga Barang: ", false));
            int jumlahBeli = Integer.parseInt(mintaInput(scanner, "Masukkan Jumlah Beli (harus > 0): ", true));

            // Membuat objek FakturPenjualan
            FakturPenjualan faktur = new FakturPenjualan(noFaktur, kodeBarang, namaBarang, hargaBarang, jumlahBeli);

            // Menampilkan informasi faktur
            faktur.tampilkanInfo();
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Pastikan harga barang dan jumlah beli adalah angka.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        } finally {
            scanner.close(); // Menutup scanner
        }
    }

    // Method untuk meminta input dari pengguna dengan validasi
    public static String mintaInput(Scanner scanner, String prompt, boolean isNumeric) {
        String inputan;
        while (true) {
            System.out.print(prompt);
            inputan = scanner.nextLine().trim(); // Menghapus spasi di awal dan akhir
            if (validasiInputan(inputan, isNumeric)) {
                return inputan;
            } else {
                System.out.println("Input tidak valid. Silakan coba lagi.");
            }
        }
    }

    // Method untuk memvalidasi inputan
    public static boolean validasiInputan(String inputan, boolean isNumeric) {
        // Memastikan inputan tidak kosong
        if (inputan == null || inputan.isEmpty()) {
            return false;
        }
        // Jika isNumeric adalah true, pastikan inputan adalah angka
        if (isNumeric) {
            try {
                // Jika input adalah No Faktur, pastikan itu adalah angka
                if (inputan.equals("0")) {
                    return false; // No faktur tidak boleh 0
                }
                Integer.parseInt(inputan);
                return true;
            } catch (NumberFormatException e) {
                return false; // Jika bukan angka
            }
        }
        return true; // Jika bukan input numerik, dianggap valid
    }
}