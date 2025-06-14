
# 🛒 Marketplace Mini

Marketplace Mini adalah aplikasi sederhana berbasis Java yang memungkinkan pengguna untuk:

- Mendaftar sebagai **Pembeli** atau **Penjual**
- Menambahkan dan mengelola **produk**
- Melakukan **pembelian dan pembayaran**
- Melihat dan mengelola **keranjang belanja**

Proyek ini merupakan simulasi sistem e-commerce minimalis dengan fokus pada struktur OOP (Object-Oriented Programming) dan penerapan interface di Java.

## 📸 Demo Video

[![Demo Video](https://i.ytimg.com/vi/teP3F0WMPvU/hqdefault.jpg?sqp=-oaymwEcCNACELwBSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAz-Mu4UeqTCKY4WnIJSzxRIhCf7A)](https://www.youtube.com/watch?v=teP3F0WMPvU)

---

## 🧩 Class Diagram

```mermaid
---
config:
  layout: elk
  theme: forest
  look: handDrawn
---
classDiagram
class User {
    <<abstract>>
    #id: String
    #nama: String
    #email: String
    #password: String
    #alamat: String
    #noTelp: String
    +login()
    +logout()
}
class Pembeli {
    +login()
    +logout()
    +getKeranjang(): Keranjang
}
class Penjual {
    +login()
    +logout()
}
class Bayar {
    <<interface>>
    +prosesPembayaran(jumlah: double)
}
class KelolaProduk {
    <<interface>>
    +tambahProduk(produk: Produk)
    +editProduk(produkUpdate: Produk)
    +hapusProduk(idProduk: String)
}
class Produk {
    -idProduk: String
    -namaProduk: String
    -harga: double
    -stok: int
    -idPenjual: String
}
class Transaksi {
    -idTransaksi: String
    -idPembeli: String
    -tanggalTransaksi: Date
    -totalBayar: double
    -daftarProduk: List<Produk>
    +hitungTotal(): double
}
class Keranjang {
    -idPembeli: String
    -daftarProduk: Map<Produk, Integer>
    +tambahProduk(produk: Produk, jumlah: int)
    +hapusProduk(produk: Produk)
    +lihatKeranjang()
    +hitungTotalKeranjang(): double
    +clearKeranjang()
}
User <|-- Pembeli
User <|-- Penjual
Pembeli ..|> Bayar
Penjual ..|> KelolaProduk
Penjual "1" *-- "*" Produk
Pembeli "1" *-- "1" Keranjang
Keranjang "1" *-- "*" Produk
Transaksi "1" *-- "*" Produk
Transaksi --> Pembeli
```

---

## 🧾 Penjelasan Class Diagram

### 📦 Kelas & Interface

| Nama | Tipe | Deskripsi | Atribut | Metode |
|------|------|-----------|---------|--------|
| `User` | Abstract Class | Kelas dasar semua pengguna | `id`, `nama`, `email`, `password`, `alamat`, `noTelp` | `login()`, `logout()` |
| `Pembeli` | Subclass `User` | Pengguna yang membeli | - | `getKeranjang()`, `login()`, `logout()` |
| `Penjual` | Subclass `User` | Pengguna yang menjual produk | - | `login()`, `logout()` |
| `Bayar` | Interface | Kontrak pembayaran | - | `prosesPembayaran(jumlah)` |
| `KelolaProduk` | Interface | Kontrak pengelolaan produk | - | `tambahProduk()`, `editProduk()`, `hapusProduk()` |
| `Produk` | Class | Barang yang dijual | `idProduk`, `namaProduk`, `harga`, `stok`, `idPenjual` | - |
| `Keranjang` | Class | Keranjang belanja pembeli | `idPembeli`, `daftarProduk: Map<Produk, Integer>` | `tambahProduk()`, `hapusProduk()`, `lihatKeranjang()`, `hitungTotalKeranjang()`, `clearKeranjang()` |
| `Transaksi` | Class | Pembelian oleh pembeli | `idTransaksi`, `idPembeli`, `tanggalTransaksi`, `totalBayar`, `daftarProduk` | `hitungTotal()` |

---

### 🔗 Relasi Antar Kelas

| Relasi | Dari | Ke | Jenis | Deskripsi |
|--------|------|----|-------|-----------|
| Pewarisan | `Pembeli` | `User` | extends | Pembeli mewarisi User |
| Pewarisan | `Penjual` | `User` | extends | Penjual mewarisi User |
| Implementasi | `Pembeli` | `Bayar` | implements | Pembeli bisa melakukan pembayaran |
| Implementasi | `Penjual` | `KelolaProduk` | implements | Penjual bisa kelola produk |
| Komposisi | `Penjual` | `Produk` | 1-to-many | Penjual punya banyak produk |
| Komposisi | `Pembeli` | `Keranjang` | 1-to-1 | Pembeli punya 1 keranjang |
| Komposisi | `Keranjang` | `Produk` | 1-to-many | Keranjang berisi banyak produk |
| Komposisi | `Transaksi` | `Produk` | 1-to-many | Transaksi memuat banyak produk |
| Asosiasi | `Transaksi` | `Pembeli` | many-to-1 | Transaksi dilakukan oleh pembeli |

---

## 🚀 Cara Menjalankan

1. Pastikan kamu sudah menginstall **JDK 22** atau versi yang kompatibel.
2. Compile semua file:

```bash
javac -d bin src/**/*.java
```

3. Jalankan program utama:

```bash
java -cp bin app.Main
```

---

## 🧪 Fitur

- Login / Logout
- Pembeli: Lihat produk, tambah ke keranjang, checkout
- Penjual: Tambah / hapus produk
- Transaksi dengan interface `Bayar`
- Total belanja dihitung otomatis di `Keranjang`
- OOP terstruktur dengan pewarisan dan interface

---

## 📓 Changelog

### v1.0.3 - 2025-06-14

- 🚀 Rilis pertama
- ✅ Struktur OOP lengkap
- 🛒 Sistem marketplace dasar
- 🧮 Perhitungan otomatis total keranjang
- 💳 Interface `Bayar` dan `KelolaProduk` diimplementasikan

---

## 📂 Struktur Folder

```
src/
├── app/
│   └── Main.java
├── interfaces/
│   ├── Bayar.java
│   └── KelolaProduk.java
└── models/
    ├── User.java
    ├── Pembeli.java
    ├── Penjual.java
    ├── Produk.java
    ├── Transaksi.java
    └── Keranjang.java
```

---

## 🧠 Konsep OOP yang Digunakan

- Abstraksi: `User` adalah abstract class
- Pewarisan: `Pembeli` dan `Penjual` mewarisi `User`
- Interface: `Bayar`, `KelolaProduk`
- Komposisi: `Keranjang` berisi banyak `Produk`

---

## 🧑‍💻 Author

KELOMPOK 5

---

## 📃 Lisensi

Proyek ini bersifat open-source dan menggunakan lisensi [MIT](LICENSE).
