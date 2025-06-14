package models;

import java.util.*;

public class Keranjang {
    private String idPembeli;
    private Map<Produk, Integer> daftarProduk;

    public Keranjang(String idPembeli) {
        this.idPembeli = idPembeli;
        this.daftarProduk = new HashMap<>();
    }

    public void tambahProduk(Produk produk, int jumlah) {
        daftarProduk.put(produk, daftarProduk.getOrDefault(produk, 0) + jumlah);
        System.out.println(jumlah + " unit " + produk.getNamaProduk() + " ditambahkan ke keranjang.");
    }

    public void hapusProduk(Produk produk) {
        if (daftarProduk.containsKey(produk)) {
            daftarProduk.remove(produk);
            System.out.println(produk.getNamaProduk() + " dihapus dari keranjang.");
        } else {
            System.out.println(produk.getNamaProduk() + " tidak ada di keranjang.");
        }
    }

    public void lihatKeranjang() {
        if (daftarProduk.isEmpty()) {
            System.out.println("Keranjang belanja kosong.");
            return;
        }
        System.out.println("Isi Keranjang Belanja:");
        for (Map.Entry<Produk, Integer> entry : daftarProduk.entrySet()) {
            Produk produk = entry.getKey();
            Integer jumlah = entry.getValue();
            System.out.println("- " + produk.getNamaProduk() + " (Rp " + produk.getHarga() + ") x " + jumlah);
        }
        System.out.printf("\n\nTotal Belanja: Rp %.0f%n", hitungTotalKeranjang());
    }

    public double hitungTotalKeranjang() {
        double total = 0;
        for (Map.Entry<Produk, Integer> entry : daftarProduk.entrySet()) {
            total += entry.getKey().getHarga() * entry.getValue();
        }
        return total;
    }

    public Map<Produk, Integer> getDaftarProduk() {
        return daftarProduk;
    }

    public String getIdPembeli() {
        return idPembeli;
    }

    public void clearKeranjang() {
        daftarProduk.clear();
    }
}
