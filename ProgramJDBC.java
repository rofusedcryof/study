package com.mycompany.modul8pbol;

import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author dzx
 */
public class ProgramJDBC {
    public static void main(String[] args) {
        System.out.println("PROGRAM LATIHAN JDBC");
        System.out.println("====================");
        
        System.out.println("Mencoba membuat koneksi ke database");
        ProgramJDBC p = new ProgramJDBC();
        Connection conn = p.getConnection();
        
        try {
            conn.close();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("MENU");
        System.out.println("1. Lihat isi tabel Mahasiswa");
        System.out.println("2. Tambah data");
        System.out.println("3. Hapus data");
        System.out.println("4. Update data");
        System.out.println("9. Keluar");
        
        int input = 0;
        while (input != 5){
            System.out.println("Pilih Menu? ");
            input = sc.nextInt();
            switch (input) {
                case 1:
                    p.showData();
                    break;
                case 2 :
                    p.inputData();
                    break;
                case 3 :
                    p.deleteData();
                    break;
                case 4 :
                    p.updateData();
                    break;
                default:
                    break;
            }
        }
    }
    
    
    
    public Connection getConnection(){
        String host = "172.23.9.183";
        String port = "1521";
        String db = "orcl";
        String user = "mhs235314023";
        String password = "mhs235314023";
        Connection conn = null;
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }catch (ClassNotFoundException ex){
            System.out.println("Driver tidak ditemukan");
            System.out.println("message: " + ex.getMessage());
        }
        
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@" + host
             + ":" + port + ":" + db, user, password);        
        }catch(SQLException ex){
            System.out.println("Koneksi Gagal");
            System.out.println("Message: " + ex.getMessage());
        }
        
        if (conn != null) {
            System.out.println("Koneksi Berhasil");
        }else{
            System.out.println("Koneksi Gagal");
        }
        return conn;
    }
    
    public void showData(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        
        conn = this.getConnection();
        try {
            st = conn.createStatement();
            String query = "Select * from MAHASISWA";
            rs = st.executeQuery(query);
            
            System.out.printf("%-10s", "NIM");
            System.out.printf("%-30s", "Nama");
            System.out.printf("%-5s", "IPK");
            System.out.println("");
            while (rs.next()){
                System.out.printf("%-10s", rs.getString(1));
                System.out.printf("%-30s", rs.getString(2));
                System.out.printf("%-5s", rs.getString(3));
                System.out.println("");
            }
        }catch(SQLException ex){
            System.out.println("message: " + ex.getMessage());
        }finally{
            try{
                rs.close();
                st.close();
                conn.close();
            } catch(SQLException ex){
                System.out.println("message: " + ex.getMessage());
            }
        }
    }
    
    public void inputData(){
        Connection conn = null;
        PreparedStatement ps = null;
        
        conn = this.getConnection();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Data Mahasiswa");
        System.out.print("Masukkan NIM : ");
        String nim = sc.next();
        System.out.print("Masukkan NAMA : ");
        String nama = sc.next();
        System.out.print("Masukkan IPK : ");
        String ipk = sc.next();
        
        try{
            ps = conn.prepareStatement("Insert into mahasiswa values (?,?,?)");
            ps.setString(1, nim);
            ps.setString(2, nama);
            ps.setString(3, ipk);
            ps.executeUpdate();
            conn.commit();
            System.out.println("Data berhasil ditambahkan!");
            
        }catch(SQLException ex){
            System.out.println("Message: " + ex.getMessage());
        }finally{
            try {
                ps.close();
                conn.close();
            } catch(SQLException ex){
                System.out.println("message: " + ex.getMessage());
            }
        }
    }
    public void deleteData() {
        Connection conn = null;
        PreparedStatement ps = null;

        conn = this.getConnection();

        Scanner sc = new Scanner(System.in);
        System.out.println("Menghapus Data Mahasiswa");
        System.out.print("Masukkan NIM yang akan dihapus : ");
        String nim = sc.next();

        try {
            ps = conn.prepareStatement("delete from mahasiswa where nim = ?");
            ps.setString(1, nim);
            ps.executeUpdate();
            conn.commit();
            System.out.println("Data berhasil dihapus!");
        } catch (SQLException ex) {
            System.out.println("message: " + ex.getMessage());
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("message: " + ex.getMessage());
            }
        }
    }
    public void updateData() {
        Connection conn = null;
        PreparedStatement ps = null;

        conn = this.getConnection();

        Scanner sc = new Scanner(System.in);
        System.out.println("Update Data Mahasiswa");
        System.out.print("Masukkan NIM yang akan diupdate : ");
        String nim = sc.next();
        System.out.print("Masukkan Nama : ");
        String nama = sc.next();
        System.out.print("Masukkan IPK : ");
        String ipk = sc.next();

        try {
            ps = conn.prepareStatement("update mahasiswa set nama = ?, "
                                       + "ipk = ? where nim = ?");
            ps.setString(1, nama);
            ps.setString(2, ipk);
            ps.setString(3, nim);
            ps.executeUpdate();
            conn.commit();
            System.out.println("Data berhasil diubah!");
        } catch (SQLException ex) {
            System.out.println("message: " + ex.getMessage());
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("message: " + ex.getMessage());
            }
        }
    }   
}