/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg192102233.davidmahendra;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mahasiswa C-03
 */
public class getConnection {
    private static Connection getConnection() {
    try {
        String url = "jdbc:mysql://localhost:3306/mysql";
        String userName  = "root";
        String password = "";
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url,userName,password);
    } catch(ClassNotFoundException ex) {
        System.out.println("Class com.mysql.jdbc.Driver tidak ditemukan");
        System.exit(0);
    } catch(SQLException ex) {
        System.out.println("Gagal melakukan koneksi ke mysql");
        System.exit(0);
    }
    return null;
    }
    private static boolean deleteDatabase() {
    Connection cn = getConnection();
    try{
    PrepareStatement ps = cn.prepareStatement("DROP DATABASE dbMahasiwaJava");
    ps.executeUpdate();
    return true;
    } catch (SQLException ex) {
        System.out.println("Gagal menghapus database lama");
        return false;
    }
    }
    
   private static boolean createDatabase(){
       Connection cn = getConnection();
       try {
           PreparedStatement ps = cn.prepareStatement("CREATE DATABASE dbMahasiswaJava");
           ps.executeUpdate();
           return true;
       } catch (SQLException ex) {
           System.out.println("Gagal menciprtakan database baru");
           return false;
       }
   }
   
   private static boolean createTable(){
     Connection cn = getConnection();
     try {
         PreparedStatement ps = cn.prepareStatement("USE dbmahasiswa");
         ps.executeUpdate();
         
         String sql = "CREATE TABLE tblmahasiswa(nim char(15) not null, nama char(100),";
         sql+="alamat char(100), telp char(50), primary key(nim);";
         ps = cn.prepareStatement(sql);
         ps.executeUpdate();
         return true;
     } catch (SQLException ex) {
         System.out.println("Gagal menciptakan tabel mahasiswa");
         return false;
     }
   }
  
 public static void main(String[] args) {
     //HAPUS DATABASE LAMA, JIKA ADA
     deleteDatabase();
     //BUAT DATABASE BARU
     if (!createDatabase())
         System.exit(0);
     System.out.println("Database berhasil diciptakan");
     //BUAT TABEL BARU
     if (!createTable())
         System.exit(0);
     System.out.println("Tabel berhasil diciptakan");
 }
     
     
     
}
