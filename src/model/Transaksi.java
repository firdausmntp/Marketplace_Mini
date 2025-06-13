package model;

import java.util.*;

public class Transaksi {
    private String idTransaksi;
    private String idPembeli;
    private Date tanggalTransaksi;
    private double totalBayar;
    private List<Produk> daftarProduk;

    public Transaksi(String idTransaksi, String idPembeli, List<Produk> daftarProduk) {
        this.idTransaksi = idTransaksi;
        this.idPembeli = idPembeli;
        this.tanggalTransaksi = new Date();
        this.daftarProduk = new ArrayList<>(daftarProduk);
        this.totalBayar = hitungTotal();
    }

    public double hitungTotal() {
        double total = 0;
        for (Produk p : daftarProduk) {
            total += p.getHarga();
        }
        return total;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public String getIdPembeli() {
        return idPembeli;
    }

    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public double getTotalBayar() {
        return totalBayar;
    }

    public List<Produk> getDaftarProduk() {
        return daftarProduk;
    }
}


