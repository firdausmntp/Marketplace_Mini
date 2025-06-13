package app;

import model.*;
import java.util.*;

public class MarketplaceApp {
    private static List<User> users = new ArrayList<>();
    private static List<Produk> daftarProdukGlobal = new ArrayList<>();
    private static List<Transaksi> daftarTransaksi = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Selamat datang di Marketplace Mini!");
        seedData(); // Tambahkan beberapa data awal

        int pilihan;
        do {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Daftar sebagai Pembeli");
            System.out.println("2. Daftar sebagai Penjual");
            System.out.println("3. Login");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (pilihan) {
                case 1:
                    daftarPembeli();
                    break;
                case 2:
                    daftarPenjual();
                    break;
                case 3:
                    loginUser();
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan Marketplace Mini!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 0);

        scanner.close();
    }

    private static void seedData() {
        // Penjual
        Penjual penjual1 = new Penjual("P001", "Toko A", "tokoa@gmail.com", "password", "Jl. Toko A No. 1", "081234567890");
        Penjual penjual2 = new Penjual("P002", "Toko B", "tokob@gmail.com", "password", "Jl. Toko B No. 2", "081234567891");
        users.add(penjual1);
        users.add(penjual2);
        // Produk
        daftarProdukGlobal.add(new Produk("PR001", "Produk A", 10000, 50, penjual1.getId()));
        daftarProdukGlobal.add(new Produk("PR002", "Produk B", 20000, 30, penjual1.getId()));
        daftarProdukGlobal.add(new Produk("PR003", "Produk C", 15000, 20, penjual2.getId()));

        // Pembeli
        Pembeli pembeli1 = new Pembeli("PB001", "Pembeli A", "pembelia@gmail.com", "password", "Jl. Pembeli A No. 1", "081234567892");
        Pembeli pembeli2 = new Pembeli("PB002", "Pembeli B", "pembelib@gmail.com", "password", "Jl. Pembeli B No. 2", "081234567893");
        users.add(pembeli1);
        users.add(pembeli2);
    }

    private static void daftarPembeli() {
        System.out.println("\n--- Pendaftaran Pembeli ---");
        System.out.print("ID Pembeli: ");
        String id = scanner.nextLine();
        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Alamat: ");
        String alamat = scanner.nextLine();
        System.out.print("No. Telepon: ");
        String noTelp = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Pembeli newPembeli = new Pembeli(id, nama, email, password, alamat, noTelp);
        users.add(newPembeli);
        System.out.println("Pembeli " + nama + " berhasil didaftarkan.");
    }

    private static void daftarPenjual() {
        System.out.println("\n--- Pendaftaran Penjual ---");
        System.out.print("ID Penjual: ");
        String id = scanner.nextLine();
        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Alamat: ");
        String alamat = scanner.nextLine();
        System.out.print("No. Telepon: ");
        String noTelp = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Penjual newPenjual = new Penjual(id, nama, email, password, alamat, noTelp);
        users.add(newPenjual);
        System.out.println("Penjual " + nama + " berhasil didaftarkan.");
    }

    private static void loginUser() {
        System.out.println("\n--- Login ---");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User loggedInUser = null;
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                loggedInUser = user;
                break;
            }
        }

        if (loggedInUser != null) {
            loggedInUser.login();
            if (loggedInUser instanceof Pembeli) {
                menuPembeli((Pembeli) loggedInUser);
            } else if (loggedInUser instanceof Penjual) {
                menuPenjual((Penjual) loggedInUser);
            }
            loggedInUser.logout();
        } else {
            System.out.println("Email atau password salah.");
        }
    }

    private static void menuPembeli(Pembeli pembeli) {
        Keranjang keranjang = new Keranjang(pembeli.getId());
        int pilihan;
        do {
            System.out.println("\nMenu Pembeli:");
            System.out.println("1. Lihat Daftar Produk");
            System.out.println("2. Tambah Produk ke Keranjang");
            System.out.println("3. Lihat Keranjang Belanja");
            System.out.println("4. Checkout dan Bayar");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (pilihan) {
                case 1:
                    lihatDaftarProduk();
                    break;
                case 2:
                    tambahProdukKeKeranjang(keranjang);
                    break;
                case 3:
                    keranjang.lihatKeranjang();
                    break;
                case 4:
                    checkoutDanBayar(pembeli, keranjang);
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 0);
    }

    private static void lihatDaftarProduk() {
        if (daftarProdukGlobal.isEmpty()) {
            System.out.println("Belum ada produk tersedia.");
            return;
        }
        System.out.println("\n--- Daftar Produk ---");
        for (Produk p : daftarProdukGlobal) {
            System.out.println("ID: " + p.getIdProduk() + ", Nama: " + p.getNamaProduk() + ", Harga: Rp " + p.getHarga() + ", Stok: " + p.getStok());
        }
    }

    private static void tambahProdukKeKeranjang(Keranjang keranjang) {
        System.out.print("Masukkan ID Produk yang ingin ditambahkan ke keranjang: ");
        String idProduk = scanner.nextLine();
        System.out.print("Masukkan jumlah: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Produk produkDipilih = null;
        for (Produk p : daftarProdukGlobal) {
            if (p.getIdProduk().equals(idProduk)) {
                produkDipilih = p;
                break;
            }
        }

        if (produkDipilih != null) {
            if (produkDipilih.getStok() >= jumlah) {
                keranjang.tambahProduk(produkDipilih, jumlah);
                produkDipilih.setStok(produkDipilih.getStok() - jumlah);
            } else {
                System.out.println("Stok produk " + produkDipilih.getNamaProduk() + " tidak mencukupi. Stok tersedia: " + produkDipilih.getStok());
            }
        } else {
            System.out.println("Produk dengan ID " + idProduk + " tidak ditemukan.");
        }
    }

    private static void checkoutDanBayar(Pembeli pembeli, Keranjang keranjang) {
        double totalBelanja = keranjang.hitungTotalKeranjang();
        if (totalBelanja == 0) {
            System.out.println("Keranjang belanja kosong. Tidak ada yang perlu dibayar.");
            return;
        }

        System.out.println("\nTotal belanja Anda: Rp " + totalBelanja);
        System.out.print("Lanjutkan pembayaran? (ya/tidak): ");
        String konfirmasi = scanner.nextLine();

        if (konfirmasi.equalsIgnoreCase("ya")) {
            pembeli.prosesPembayaran(totalBelanja);
            // Buat objek Transaksi dan simpan
            List<Produk> produkDibeli = new ArrayList<>();
            for (Map.Entry<Produk, Integer> entry : keranjang.getDaftarProduk().entrySet()) {
                for (int i = 0; i < entry.getValue(); i++) {
                    produkDibeli.add(entry.getKey());
                }
            }
            Transaksi newTransaksi = new Transaksi("TR" + (daftarTransaksi.size() + 1), pembeli.getId(), produkDibeli);
            daftarTransaksi.add(newTransaksi);
            keranjang.clearKeranjang(); // Kosongkan keranjang setelah pembayaran
            System.out.println("Transaksi berhasil! ID Transaksi: " + newTransaksi.getIdTransaksi());
        } else {
            System.out.println("Pembayaran dibatalkan.");
        }
    }

    private static void menuPenjual(Penjual penjual) {
        int pilihan;
        do {
            System.out.println("\nMenu Penjual:");
            System.out.println("1. Tambah Produk Baru");
            System.out.println("2. Edit Produk");
            System.out.println("3. Hapus Produk");
            System.out.println("4. Lihat Produk Saya");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (pilihan) {
                case 1:
                    tambahProdukBaru(penjual);
                    break;
                case 2:
                    editProdukPenjual(penjual);
                    break;
                case 3:
                    hapusProdukPenjual(penjual);
                    break;
                case 4:
                    lihatProdukPenjual(penjual);
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 0);
    }

    private static void tambahProdukBaru(Penjual penjual) {
        System.out.println("\n--- Tambah Produk Baru ---");
        System.out.print("ID Produk: ");
        String idProduk = scanner.nextLine();
        System.out.print("Nama Produk: ");
        String namaProduk = scanner.nextLine();
        System.out.print("Harga: ");
        double harga = scanner.nextDouble();
        System.out.print("Stok: ");
        int stok = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Produk newProduk = new Produk(idProduk, namaProduk, harga, stok, penjual.getId());
        penjual.tambahProduk(newProduk);
        daftarProdukGlobal.add(newProduk);
    }

    private static void editProdukPenjual(Penjual penjual) {
        System.out.println("\n--- Edit Produk ---");
        System.out.print("Masukkan ID Produk yang ingin diedit: ");
        String idProduk = scanner.nextLine();

        Produk produkToEdit = null;
        for (Produk p : penjual.getDaftarProdukDijual()) {
            if (p.getIdProduk().equals(idProduk)) {
                produkToEdit = p;
                break;
            }
        }

        if (produkToEdit != null) {
            System.out.print("Harga baru (masukkan 0 jika tidak diubah): ");
            double hargaBaru = scanner.nextDouble();
            System.out.print("Stok baru (masukkan 0 jika tidak diubah): ");
            int stokBaru = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Note: Produk class needs setters for harga and namaProduk if full edit is desired
            // For now, only stok can be updated directly.
            if (stokBaru > 0) {
                produkToEdit.setStok(stokBaru);
            }
            System.out.println("Produk " + produkToEdit.getNamaProduk() + " berhasil diupdate.");
        } else {
            System.out.println("Produk dengan ID " + idProduk + " tidak ditemukan di daftar produk Anda.");
        }
    }

    private static void hapusProdukPenjual(Penjual penjual) {
        System.out.println("\n--- Hapus Produk ---");
        System.out.print("Masukkan ID Produk yang ingin dihapus: ");
        String idProduk = scanner.nextLine();

        Produk produkToDelete = null;
        for (Produk p : daftarProdukGlobal) {
            if (p.getIdProduk().equals(idProduk) && p.getIdPenjual().equals(penjual.getId())) {
                produkToDelete = p;
                break;
            }
        }

        if (produkToDelete != null) {
            penjual.hapusProduk(idProduk);
            daftarProdukGlobal.remove(produkToDelete);
        } else {
            System.out.println("Produk dengan ID " + idProduk + " tidak ditemukan di daftar produk Anda atau Anda bukan pemiliknya.");
        }
    }

    private static void lihatProdukPenjual(Penjual penjual) {
        List<Produk> produkSaya = penjual.getDaftarProdukDijual();
        if (produkSaya.isEmpty()) {
            System.out.println("Anda belum memiliki produk yang dijual.");
            return;
        }
        System.out.println("\n--- Produk yang Anda Jual ---");
        for (Produk p : produkSaya) {
            System.out.println("ID: " + p.getIdProduk() + ", Nama: " + p.getNamaProduk() + ", Harga: Rp " + p.getHarga() + ", Stok: " + p.getStok());
        }
    }
}


