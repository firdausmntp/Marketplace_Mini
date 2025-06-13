package models;

import java.util.*;
import interfaces.KelolaProduk;

public class Penjual extends User implements KelolaProduk {
    private List<Produk> daftarProdukDijual;
    public Penjual(String id, String nama, String email, String password, String alamat, String noTelp) {
        super(id, nama, email, password, alamat, noTelp);
        this.daftarProdukDijual = new ArrayList<>();
    }

    @Override
    public void login() {
        System.out.println("Penjual " + nama + " berhasil login.");
    }

    @Override
    public void logout() {
        System.out.println("Penjual " + nama + " berhasil logout.");
    }

    @Override
    public void tambahProduk(Produk produk) {
        this.daftarProdukDijual.add(produk);
        System.out.println("Produk " + produk.getNamaProduk() + " berhasil ditambahkan.");
    }

    @Override
    public void editProduk(Produk produkUpdate) {
        for (int i = 0; i < daftarProdukDijual.size(); i++) {
            if (daftarProdukDijual.get(i).getIdProduk().equals(produkUpdate.getIdProduk())) {
                daftarProdukDijual.set(i, produkUpdate);
                System.out.println("Produk " + produkUpdate.getNamaProduk() + " berhasil diupdate.");
                return;
            }
        }
        System.out.println("Produk dengan ID " + produkUpdate.getIdProduk() + " tidak ditemukan.");
    }

    @Override
    public void hapusProduk(String idProduk) {
        Iterator<Produk> iterator = daftarProdukDijual.iterator();
        while (iterator.hasNext()) {
            Produk produk = iterator.next();
            if (produk.getIdProduk().equals(idProduk)) {
                iterator.remove();
                System.out.println("Produk dengan ID " + idProduk + " berhasil dihapus.");
                return;
            }
        }
        System.out.println("Produk dengan ID " + idProduk + " tidak ditemukan.");
    }

    public List<Produk> getDaftarProdukDijual() {
        return daftarProdukDijual;
    }


}
