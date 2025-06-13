package app;

import java.util.*;

import models.*;

public class Main {
    private static List<User> users = new ArrayList<>();
    private static List<Produk> daftarProdukGlobal = new ArrayList<>();
    private static List<Transaksi> daftarTransaksi = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        clearScreen();
        printHeader("SELAMAT DATANG DI MARKETPLACE MINI");
        seedData(); // Tambahkan beberapa data awal

        int pilihan;
        do {
            printSeparator();
            System.out.println("[ MENU UTAMA ]");
            printSeparator();
            System.out.println("1. Daftar sebagai Pembeli");
            System.out.println("2. Daftar sebagai Penjual");
            System.out.println("3. Login");
            System.out.println("0. Keluar");
            printSeparator();
            System.out.print("Pilih menu (0-3): ");
            
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (Exception e) {
                scanner.nextLine();
                pilihan = -1;
            }

            switch (pilihan) {
                case 1:
                    clearScreen();
                    daftarPembeli();
                    pauseAndContinue();
                    break;
                case 2:
                    clearScreen();
                    daftarPenjual();
                    pauseAndContinue();
                    break;
                case 3:
                    clearScreen();
                    loginUser();
                    break;
                case 0:
                    clearScreen();
                    printHeader("TERIMA KASIH TELAH MENGGUNAKAN MARKETPLACE MINI!");
                    System.out.println("Sampai jumpa!");
                    break;
                default:
                    System.out.println("[ERROR] Pilihan tidak valid. Silakan pilih 0-3.");
                    pauseAndContinue();
            }
        } while (pilihan != 0);

        scanner.close();
    }

    // Utility methods untuk tampilan
    private static void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;
            if (os.contains("windows")) {
                pb = new ProcessBuilder("cmd", "/c", "cls");
            } else {
                pb = new ProcessBuilder("clear");
            }
            pb.inheritIO().start().waitFor();
        } catch (Exception e) {
            // Jika gagal clear screen, print newlines
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    private static void printSeparator() {
        System.out.println("===============================================");
    }

    private static void printHeader(String title) {
        printSeparator();
        System.out.println("           " + title);
        printSeparator();
    }

    private static void pauseAndContinue() {
        System.out.println("\nTekan Enter untuk melanjutkan...");
        scanner.nextLine();
        clearScreen();
    }

    private static void seedData() {
        // Penjual
        Penjual penjual1 = new Penjual("P001", "Toko A", "tokoa@gmail.com", "password", "Jl. Toko A No. 1", "081234567890");
        Penjual penjual2 = new Penjual("P002", "Toko B", "tokob@gmail.com", "password", "Jl. Toko B No. 2", "081234567891");
        users.add(penjual1);
        users.add(penjual2);
        
        // Produk
        daftarProdukGlobal.add(new Produk("PR001", "Laptop Gaming", 15000000, 5, penjual1.getId()));
        daftarProdukGlobal.add(new Produk("PR002", "Mouse Wireless", 250000, 15, penjual1.getId()));
        daftarProdukGlobal.add(new Produk("PR003", "Keyboard Mechanical", 750000, 8, penjual2.getId()));

        // Pembeli
        Pembeli pembeli1 = new Pembeli("PB001", "Ahmad Rizki", "ahmad@gmail.com", "password", "Jl. Merdeka No. 1", "081234567892");
        Pembeli pembeli2 = new Pembeli("PB002", "Siti Nurhaliza", "siti@gmail.com", "password", "Jl. Sudirman No. 2", "081234567893");
        users.add(pembeli1);
        users.add(pembeli2);
    }

    private static void daftarPembeli() {
        printHeader("PENDAFTARAN PEMBELI");
        System.out.println("Silakan isi data diri Anda:");
        System.out.println();
        
        System.out.print("ID Pembeli        : ");
        String id = scanner.nextLine();
        System.out.print("Nama Lengkap      : ");
        String nama = scanner.nextLine();
        System.out.print("Email             : ");
        String email = scanner.nextLine();
        System.out.print("Alamat            : ");
        String alamat = scanner.nextLine();
        System.out.print("No. Telepon       : ");
        String noTelp = scanner.nextLine();
        System.out.print("Password          : ");
        String password = scanner.nextLine();

        Pembeli newPembeli = new Pembeli(id, nama, email, password, alamat, noTelp);
        users.add(newPembeli);
        
        System.out.println();
        printSeparator();
        System.out.println("[SUCCESS] Pembeli " + nama + " berhasil didaftarkan!");
        printSeparator();
    }

    private static void daftarPenjual() {
        printHeader("PENDAFTARAN PENJUAL");
        System.out.println("Silakan isi data toko Anda:");
        System.out.println();
        
        System.out.print("ID Penjual        : ");
        String id = scanner.nextLine();
        System.out.print("Nama Toko         : ");
        String nama = scanner.nextLine();
        System.out.print("Email             : ");
        String email = scanner.nextLine();
        System.out.print("Alamat Toko       : ");
        String alamat = scanner.nextLine();
        System.out.print("No. Telepon       : ");
        String noTelp = scanner.nextLine();
        System.out.print("Password          : ");
        String password = scanner.nextLine();

        Penjual newPenjual = new Penjual(id, nama, email, password, alamat, noTelp);
        users.add(newPenjual);
        
        System.out.println();
        printSeparator();
        System.out.println("[SUCCESS] Penjual " + nama + " berhasil didaftarkan!");
        printSeparator();
    }

    private static void loginUser() {
        printHeader("LOGIN PENGGUNA");
        System.out.println("Masukkan kredensial Anda:");
        System.out.println();
        
        System.out.print("Email    : ");
        String email = scanner.nextLine();
        System.out.print("Password : ");
        String password = scanner.nextLine();

        User loggedInUser = null;
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                loggedInUser = user;
                break;
            }
        }

        if (loggedInUser != null) {
            clearScreen();
            System.out.println("[SUCCESS] Login berhasil! Selamat datang, " + loggedInUser.getNama() + "!");
            loggedInUser.login();
            
            if (loggedInUser instanceof Pembeli) {
                menuPembeli((Pembeli) loggedInUser);
            } else if (loggedInUser instanceof Penjual) {
                menuPenjual((Penjual) loggedInUser);
            }
            loggedInUser.logout();
            clearScreen();
        } else {
            System.out.println();
            printSeparator();
            System.out.println("[ERROR] Email atau password salah!");
            printSeparator();
            pauseAndContinue();
        }
    }

    private static void menuPembeli(Pembeli pembeli) {
        Keranjang keranjang = new Keranjang(pembeli.getId());
        int pilihan;
        do {
            printHeader("MENU PEMBELI - " + pembeli.getNama());
            System.out.println("1. Lihat Daftar Produk");
            System.out.println("2. Tambah Produk ke Keranjang");
            System.out.println("3. Lihat Keranjang Belanja");
            System.out.println("4. Checkout dan Bayar");
            System.out.println("0. Logout");
            printSeparator();
            System.out.print("Pilih menu (0-4): ");
            
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                pilihan = -1;
            }

            switch (pilihan) {
                case 1:
                    clearScreen();
                    lihatDaftarProduk();
                    pauseAndContinue();
                    break;
                case 2:
                    clearScreen();
                    lihatDaftarProduk();
                    tambahProdukKeKeranjang(keranjang);
                    pauseAndContinue();
                    break;
                case 3:
                    clearScreen();
                    keranjang.lihatKeranjang();
                    pauseAndContinue();
                    break;
                case 4:
                    clearScreen();
                    checkoutDanBayar(pembeli, keranjang);
                    pauseAndContinue();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("[ERROR] Pilihan tidak valid. Silakan pilih 0-4.");
                    pauseAndContinue();
            }
        } while (pilihan != 0);
    }

    private static void lihatDaftarProduk() {
        if (daftarProdukGlobal.isEmpty()) {
            printHeader("DAFTAR PRODUK");
            System.out.println("[INFO] Belum ada produk tersedia.");
            return;
        }
        
        printHeader("DAFTAR PRODUK TERSEDIA");
        System.out.printf("%-8s %-20s %-15s %-8s %-15s%n", "ID", "Nama Produk", "Harga", "Stok", "Penjual");
        printSeparator();
        
        for (Produk p : daftarProdukGlobal) {
            String namaPenjual = "";
            for (User user : users) {
                if (user.getId().equals(p.getIdPenjual())) {
                    namaPenjual = user.getNama();
                    break;
                }
            }
            System.out.printf("%-8s %-20s Rp %-12.0f %-8d %-15s%n", 
                p.getIdProduk(), 
                p.getNamaProduk(), 
                p.getHarga(), 
                p.getStok(),
                namaPenjual);
        }
        printSeparator();
    }

    private static void tambahProdukKeKeranjang(Keranjang keranjang) {
        System.out.println();
        System.out.print("ID Produk yang ingin ditambahkan: ");
        String idProduk = scanner.nextLine();
        System.out.print("Jumlah: ");
        
        int jumlah;
        try {
            jumlah = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("[ERROR] Jumlah harus berupa angka!");
            return;
        }

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
                System.out.println();
                printSeparator();
                System.out.println("[SUCCESS] " + jumlah + "x " + produkDipilih.getNamaProduk() + " berhasil ditambahkan ke keranjang!");
                printSeparator();
            } else {
                System.out.println();
                printSeparator();
                System.out.println("[ERROR] Stok " + produkDipilih.getNamaProduk() + " tidak mencukupi!");
                System.out.println("[INFO] Stok tersedia: " + produkDipilih.getStok());
                printSeparator();
            }
        } else {
            System.out.println();
            printSeparator();
            System.out.println("[ERROR] Produk dengan ID " + idProduk + " tidak ditemukan!");
            printSeparator();
        }
    }

    private static void checkoutDanBayar(Pembeli pembeli, Keranjang keranjang) {
        printHeader("CHECKOUT & PEMBAYARAN");
        
        double totalBelanja = keranjang.hitungTotalKeranjang();
        if (totalBelanja == 0) {
            System.out.println("[INFO] Keranjang belanja kosong. Tidak ada yang perlu dibayar.");
            return;
        }

        keranjang.lihatKeranjang();
        System.out.println();
        printSeparator();
        System.out.printf("Total Belanja: Rp %.0f%n", totalBelanja);
        printSeparator();
        System.out.print("Lanjutkan pembayaran? (ya/tidak): ");
        String konfirmasi = scanner.nextLine();

        if (konfirmasi.equalsIgnoreCase("ya") || konfirmasi.equalsIgnoreCase("y")) {
            pembeli.prosesPembayaran(totalBelanja);
            
            // Buat objek Transaksi dan simpan
            List<Produk> produkDibeli = new ArrayList<>();
            for (Map.Entry<Produk, Integer> entry : keranjang.getDaftarProduk().entrySet()) {
                for (int i = 0; i < entry.getValue(); i++) {
                    produkDibeli.add(entry.getKey());
                }
            }
            Transaksi newTransaksi = new Transaksi("TR" + String.format("%03d", daftarTransaksi.size() + 1), pembeli.getId(), produkDibeli);
            daftarTransaksi.add(newTransaksi);
            keranjang.clearKeranjang();
            
            System.out.println();
            printSeparator();
            System.out.println("[SUCCESS] Transaksi berhasil!");
            System.out.println("ID Transaksi: " + newTransaksi.getIdTransaksi());
            System.out.println("Konfirmasi akan dikirim ke email Anda.");
            printSeparator();
        } else {
            System.out.println();
            printSeparator();
            System.out.println("[INFO] Pembayaran dibatalkan.");
            printSeparator();
        }
    }

    private static void menuPenjual(Penjual penjual) {
        int pilihan;
        do {
            printHeader("MENU PENJUAL - " + penjual.getNama());
            System.out.println("1. Tambah Produk Baru");
            System.out.println("2. Edit Produk");
            System.out.println("3. Hapus Produk");
            System.out.println("4. Lihat Produk Saya");
            System.out.println("0. Logout");
            printSeparator();
            System.out.print("Pilih menu (0-4): ");
            
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                pilihan = -1;
            }

            switch (pilihan) {
                case 1:
                    clearScreen();
                    tambahProdukBaru(penjual);
                    pauseAndContinue();
                    break;
                case 2:
                    clearScreen();
                    lihatProdukPenjual(penjual);
                    editProdukPenjual(penjual);
                    pauseAndContinue();
                    break;
                case 3:
                    clearScreen();
                    lihatProdukPenjual(penjual);
                    hapusProdukPenjual(penjual);
                    pauseAndContinue();
                    break;
                case 4:
                    clearScreen();
                    lihatProdukPenjual(penjual);
                    pauseAndContinue();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("[ERROR] Pilihan tidak valid. Silakan pilih 0-4.");
                    pauseAndContinue();
            }
        } while (pilihan != 0);
    }

    private static void tambahProdukBaru(Penjual penjual) {
        printHeader("TAMBAH PRODUK BARU");
        System.out.println("Masukkan detail produk:");
        System.out.println();
        
        System.out.print("ID Produk     : ");
        String idProduk = scanner.nextLine();
        System.out.print("Nama Produk   : ");
        String namaProduk = scanner.nextLine();
        System.out.print("Harga (Rp)   : ");
        
        double harga;
        int stok;
        try {
            harga = scanner.nextDouble();
            System.out.print("Stok          : ");
            stok = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("[ERROR] Input tidak valid!");
            return;
        }

        Produk newProduk = new Produk(idProduk, namaProduk, harga, stok, penjual.getId());
        penjual.tambahProduk(newProduk);
        daftarProdukGlobal.add(newProduk);
        
        System.out.println();
        printSeparator();
        System.out.println("[SUCCESS] Produk " + namaProduk + " berhasil ditambahkan!");
        printSeparator();
    }

    private static void editProdukPenjual(Penjual penjual) {
        if (penjual.getDaftarProdukDijual().isEmpty()) {
            System.out.println("[INFO] Anda belum memiliki produk untuk diedit.");
            return;
        }
        
        System.out.println();
        printHeader("EDIT PRODUK");
        System.out.print("ID Produk yang ingin diedit: ");
        String idProduk = scanner.nextLine();

        Produk produkToEdit = null;
        for (Produk p : penjual.getDaftarProdukDijual()) {
            if (p.getIdProduk().equals(idProduk)) {
                produkToEdit = p;
                break;
            }
        }

        if (produkToEdit != null) {
            System.out.println("Produk saat ini: " + produkToEdit.getNamaProduk());
            System.out.println("Harga saat ini: Rp " + produkToEdit.getHarga());
            System.out.println("Stok saat ini: " + produkToEdit.getStok());
            System.out.println();
            
            System.out.print("Stok baru (0 = tidak diubah): ");
            try {
                int stokBaru = scanner.nextInt();
                scanner.nextLine();
                
                if (stokBaru > 0) {
                    produkToEdit.setStok(stokBaru);
                    System.out.println();
                    printSeparator();
                    System.out.println("[SUCCESS] Produk " + produkToEdit.getNamaProduk() + " berhasil diupdate!");
                    printSeparator();
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("[ERROR] Input tidak valid!");
            }
        } else {
            System.out.println();
            printSeparator();
            System.out.println("[ERROR] Produk dengan ID " + idProduk + " tidak ditemukan!");
            printSeparator();
        }
    }

    private static void hapusProdukPenjual(Penjual penjual) {
        if (penjual.getDaftarProdukDijual().isEmpty()) {
            System.out.println("[INFO] Anda belum memiliki produk untuk dihapus.");
            return;
        }
        
        System.out.println();
        printHeader("HAPUS PRODUK");
        System.out.print("ID Produk yang ingin dihapus: ");
        String idProduk = scanner.nextLine();

        Produk produkToDelete = null;
        for (Produk p : daftarProdukGlobal) {
            if (p.getIdProduk().equals(idProduk) && p.getIdPenjual().equals(penjual.getId())) {
                produkToDelete = p;
                break;
            }
        }

        if (produkToDelete != null) {
            System.out.println("[WARNING] Anda akan menghapus: " + produkToDelete.getNamaProduk());
            System.out.print("Yakin ingin menghapus? (ya/tidak): ");
            String konfirmasi = scanner.nextLine();
            
            if (konfirmasi.equalsIgnoreCase("ya") || konfirmasi.equalsIgnoreCase("y")) {
                penjual.hapusProduk(idProduk);
                daftarProdukGlobal.remove(produkToDelete);
                System.out.println();
                printSeparator();
                System.out.println("[SUCCESS] Produk berhasil dihapus!");
                printSeparator();
            } else {
                System.out.println("[INFO] Penghapusan dibatalkan.");
            }
        } else {
            System.out.println();
            printSeparator();
            System.out.println("[ERROR] Produk tidak ditemukan atau bukan milik Anda!");
            printSeparator();
        }
    }

    private static void lihatProdukPenjual(Penjual penjual) {
        List<Produk> produkSaya = penjual.getDaftarProdukDijual();
        if (produkSaya.isEmpty()) {
            printHeader("PRODUK SAYA");
            System.out.println("[INFO] Anda belum memiliki produk yang dijual.");
            return;
        }
        
        printHeader("PRODUK YANG ANDA JUAL");
        System.out.printf("%-8s %-20s %-15s %-8s%n", "ID", "Nama Produk", "Harga", "Stok");
        printSeparator();
        
        for (Produk p : produkSaya) {
            System.out.printf("%-8s %-20s Rp %-12.0f %-8d%n", 
                p.getIdProduk(), 
                p.getNamaProduk(), 
                p.getHarga(), 
                p.getStok());
        }
        printSeparator();
    }
}