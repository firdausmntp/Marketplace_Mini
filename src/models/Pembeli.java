package models;

import interfaces.Bayar;
import java.util.*;

public class Pembeli extends User implements Bayar {
    private Keranjang keranjang;

    public Pembeli(String id, String nama, String email, String password, String alamat, String noTelp) {
        super(id, nama, email, password, alamat, noTelp);
        this.keranjang = new Keranjang(id);
    }

    @Override
    public void login() {
        System.out.println("Pembeli " + nama + " berhasil login.");
    }

    @Override
    public void logout() {
        System.out.println("Pembeli " + nama + " berhasil logout.");
    }

    public Keranjang getKeranjang() {
        return keranjang;
    }

    @Override
    public void prosesPembayaran(double jumlah) {
        System.out.println("Pembayaran sebesar Rp " + jumlah + " oleh Pembeli " + nama + " berhasil diproses.");
        keranjang.clearKeranjang(); // Kosongkan keranjang setelah pembayaran
    }
}