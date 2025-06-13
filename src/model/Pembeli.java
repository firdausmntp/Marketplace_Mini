package model;

import interfaces.Bayar;
import java.io.*;
import java.util.*;

public class Pembeli extends User implements Bayar {
    private List<Produk> keranjangBelanja;
    public Pembeli(String id, String nama, String email, String password, String alamat, String noTelp) {
        super(id, nama, email, password, alamat, noTelp);
    this.keranjangBelanja = new ArrayList<>();
    }

    @Override
    public void login() {
        System.out.println("Pembeli " + nama + " berhasil login.");
    }

    @Override
    public void logout() {
        System.out.println("Pembeli " + nama + " berhasil logout.");
    }

    public void tambahKeKeranjang(Produk produk) {
        this.keranjangBelanja.add(produk);
        System.out.println(produk.getNamaProduk() + " ditambahkan ke keranjang.");
    }

    public void lihatKeranjang() {
        if (keranjangBelanja.isEmpty()) {
            System.out.println("Keranjang belanja kosong.");
            return;
        }
        System.out.println("Isi Keranjang Belanja:");
        for (Produk p : keranjangBelanja) {
            System.out.println("- " + p.getNamaProduk() + " (Rp " + p.getHarga() + ")");
        }
    }

    public double hitungTotalKeranjang() {
        double total = 0;
        for (Produk p : keranjangBelanja) {
            total += p.getHarga();
        }
        return total;
    }

    @Override
    public void prosesPembayaran(double jumlah) {
        System.out.println("Pembayaran sebesar Rp " + jumlah + " oleh Pembeli " + nama + " berhasil diproses.");
        keranjangBelanja.clear(); // Kosongkan keranjang setelah pembayaran
    }

    
}
