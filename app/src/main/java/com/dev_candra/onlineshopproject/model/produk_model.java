package com.dev_candra.onlineshopproject.model;

public class produk_model {
    private int gambar_prouduk;
    private String nama_produk;
    private String prosesor_produk;
    private String harga_produk;

    public produk_model() {
    }

    public int getGambar_prouduk() {
        return gambar_prouduk;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public String getProsesor_produk() {
        return prosesor_produk;
    }

    public String getHarga_produk() {
        return harga_produk;
    }

    public void setGambar_prouduk(int gambar_prouduk) {
        this.gambar_prouduk = gambar_prouduk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public void setProsesor_produk(String prosesor_produk) {
        this.prosesor_produk = prosesor_produk;
    }

    public void setHarga_produk(String harga_produk) {
        this.harga_produk = harga_produk;
    }
}
