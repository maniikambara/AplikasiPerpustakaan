/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author manii
 */

import java.sql.*;

public class KoneksiDatabase {
    static Connection conn;

    public static Connection getKoneksi() {
        try {
            String URL = "jdbc:mysql://localhost:3306/perpustakaan";
            String USER = "root";
            String PASSWORD = "root";
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Terjadi Error");
        }
        return conn;
    }
    
    public static void main(String args[]) {
        getKoneksi();
    }
    
    // Tambah data
    public void tambahBuku(String kode_buku, String judul_buku, String pengarang, String penerbit) {
        String query = "INSERT INTO buku (kode_buku, judul_buku, pengarang, penerbit) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, kode_buku);
            ps.setString(2, judul_buku);
            ps.setString(3, pengarang);
            ps.setString(4, penerbit);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    // Ubah data
    public void ubahBuku(String kode_buku, String judul_buku, String pengarang, String penerbit) {
        String query = "UPDATE buku SET judul_buku = ?, pengarang = ?, penerbit = ? WHERE kode_buku = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(4, kode_buku);
            ps.setString(1, judul_buku);
            ps.setString(2, pengarang);
            ps.setString(3, penerbit);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    // Hapus data
    public void hapusBuku(String kode_buku) {
        String query = "DELETE FROM buku WHERE kode_buku = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, kode_buku);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
}
