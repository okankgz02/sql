package com.deneme.mainTest.service;

import com.deneme.mainTest.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class BaglantiServisImpl implements BaglantiServis{

    @Autowired
    Data data;

    List<Data> dataList;
    List<Data> predataList;


    private String kullanici_adi = "root";
    private String parola = "";
    private String db_ismi = "demo";
    private String host = "localhost";
    private int port = 3306;

    private Connection con = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    public   List<Data> preparedCalisanlariGetir(int id) {

        String sorgu = "Select * From calisanlar where id > ?";
        predataList = new ArrayList<>();


        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setInt(1, id);


            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String email = rs.getString("email");

                System.out.println("Ad : " + ad + " Soyad : " + soyad + " Email : " + email);


                data.setId(id);
                data.setAd(ad);
                data.setSoyad(soyad);
                data.setEmail(email);
                System.out.println("Id : " + id + " Ad: " + ad + "Soyad : " + soyad + " Email : " + email);
                predataList.add(data);
            }


        } catch (SQLException ex) {
            Logger.getLogger(BaglantiServisImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    return predataList;
    }

    public List<Data> calisanlariGetir(String parametre) {

        dataList = new ArrayList<>();

        String sorgu = "Select * From calisanlar WHERE  id > " + parametre;

        try {
            statement = con.createStatement();

            ResultSet rs = statement.executeQuery(sorgu);

            while (rs.next()) {

                int id = rs.getInt("id");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String email = rs.getString("email");

                data.setId(id);
                data.setAd(ad);
                data.setSoyad(soyad);
                data.setEmail(email);

                System.out.println("Id : " + id + " Ad: " + ad + "Soyad : " + soyad + " Email : " + email);
                dataList.add(data);
            }


        } catch (SQLException ex) {
            Logger.getLogger(BaglantiServisImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dataList;
    }



    public void calisanEkle() {


        try {
            statement = con.createStatement();
            String ad = "Semih";
            String soyad = "Aktaş";
            String email = "semihaktas@gmail.com";
            // Insert Into calisanlar (ad,soyad,email) VALUES('Yusuf','Çetinkaya','mucahit@gmail.com')
            String sorgu = "Insert Into calisanlar (ad,soyad,email) VALUES(" + "'" + ad + "'," + "'" + soyad + "'," + "'" + email + "')";

            statement.executeUpdate(sorgu);


        } catch (SQLException ex) {
            Logger.getLogger(BaglantiServisImpl.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void calisanSil() {

        try {
            statement = con.createStatement();

            String sorgu = "Delete from calisanlar where id > 3";

            int deger = statement.executeUpdate(sorgu);
            System.out.println(deger + " kadar veri etkilendi...");


        } catch (SQLException ex) {
            Logger.getLogger(BaglantiServisImpl.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void calisanGuncelle() {


        try {
            statement = con.createStatement();

            String sorgu = "Update calisanlar Set email = 'example@gmail.com' where id > 3";

            statement.executeUpdate(sorgu);

        } catch (SQLException ex) {
            Logger.getLogger(BaglantiServisImpl.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


    public BaglantiServisImpl() {

        // "jbdc:mysql://localhost:3306/demo" 
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db_ismi + "?useUnicode=true&characterEncoding=utf8";

        //JDBC RAR I BULAMAZSA
        try {

            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadı....");
        }


        try {

            con = DriverManager.getConnection(url, kullanici_adi, parola);
            System.out.println("Bağlantı Başarılı...");


        } catch (SQLException ex) {
            System.out.println("Bağlantı Başarısız...");
            //ex.printStackTrace();
        }

    }


}
