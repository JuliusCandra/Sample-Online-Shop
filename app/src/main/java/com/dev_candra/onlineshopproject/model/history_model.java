package com.dev_candra.onlineshopproject.model;

public class history_model {
    private int gambar_barang;
    private String judul_barang;
    private String harga_barang;
    private String rating;
    private String jumlah_rating;
    private String tanggal_pembelian;
    private String status_pembelian;

    public history_model(int gambar_barang,String judul_barang, String harga_barang, String rating, String jumlah_rating, String tanggal_pembelian,String status_pemebelian) {
        this.judul_barang = judul_barang;
        this.gambar_barang = gambar_barang;
        this.harga_barang = harga_barang;
        this.rating = rating;
        this.jumlah_rating = jumlah_rating;
        this.tanggal_pembelian = tanggal_pembelian;
        this.status_pembelian = status_pemebelian;
    }

    public String getStatus_pembelian() {
        return status_pembelian;
    }

    public void setStatus_pembelian(String status_pembelian) {
        this.status_pembelian = status_pembelian;
    }

    public String getJudul_barang() {
        return judul_barang;
    }

    public void setJudul_barang(String judul_barang) {
        this.judul_barang = judul_barang;
    }

    public String getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(String harga_barang) {
        this.harga_barang = harga_barang;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getJumlah_rating() {
        return jumlah_rating;
    }

    public void setJumlah_rating(String jumlah_rating) {
        this.jumlah_rating = jumlah_rating;
    }

    public String getTanggal_pembelian() {
        return tanggal_pembelian;
    }

    public void setTanggal_pembelian(String tanggal_pembelian) {
        this.tanggal_pembelian = tanggal_pembelian;
    }

    public void setGambar_barang(int gambar_barang) {
        this.gambar_barang = gambar_barang;
    }

    public int getGambar_barang() {
        return gambar_barang;
    }
}
