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
    #id: String
    #nama: String
    #email: String
    #password: String
    #alamat: String
    #noTelp: String
    +login()
    +logout()
    +checkout()
    +bayar()
}
class Penjual {
    #id: String
    #nama: String
    #email: String
    #password: String
    #alamat: String
    #noTelp: String
    +login()
    +logout()
    +kelolaProduk()
}
class Bayar {
    <<interface>>
    +bayar()
}
class KelolaProduk {
    <<interface>>
    +tambahProduk()
    +hapusProduk()
}
class Produk {
    -idProduk: String
    -namaProduk: String
    -harga: double
    -stok: int
    -idPenjual: String
}
class Transaksi {
    +id: String
    +total: Float
    +tanggal: Date
    +bayar()
}
class Keranjang {
    +tambahProduk(p: Produk)
    +hapusProduk(p: Produk)
    +getTotal(): Float
}
User <|-- Pembeli
User <|-- Penjual
Pembeli ..|> Bayar
Penjual ..|> KelolaProduk
Pembeli --> Keranjang
Keranjang --> Produk
Transaksi --> Pembeli
Transaksi --> Produk

```

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
