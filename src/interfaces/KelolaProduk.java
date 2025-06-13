package interfaces;

import model.Produk;

public interface KelolaProduk {
    void tambahProduk(Produk produk);
    void editProduk(Produk produk);
    void hapusProduk(String idProduk);
}
