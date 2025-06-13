package models;

public class Produk {
     private String idProduk;
    private String namaProduk;
    private double harga;
    private int stok;
    private String idPenjual;

    public Produk(String idProduk, String namaProduk, double harga, int stok, String idPenjual) {
        this.idProduk = idProduk;
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.stok = stok;
        this.idPenjual = idPenjual;
    }

    public String getIdProduk() {
        return idProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getIdPenjual() {
        return idPenjual;
    }
}
