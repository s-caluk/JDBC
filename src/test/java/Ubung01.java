
//tabloyu 1 kez olusturup veri ekledikten sonra yoruma alin, yoksa her run da tekrar dener
//kodu yazdiktan sonra close yapmayi unutma

import java.sql.*;

public class Ubung01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");
        Statement st = con.createStatement();


        // table ismi Kahramanlar olan bir tablo olusturun.----------------------------------------
        // yil, isim, alani -> fieltleri olsun

        /*
        String tableOlusturma = "create table Kahramanlar (yil int, isim varchar(15), alani varchar(10))";


        //boolean ifade dönüyor, execute yapilmissa false dönüyormus.
        // tablo 1 kez olusturulduktan sonra execute kismi yoruma alinmali,
        //yoksa her calistiginda tekrar olusturmayi dener

        st.execute(tableOlusturma);


        if (!st.execute(tableOlusturma)){       //!false -> true oldugundan sout calisir.
            System.out.println("Tablo olusturuldu");
        }


        //kahramanlar tablomuza tekli veri ekleyelim.-----------------------------------

        String insertVeri = "insert into Kahramanlar values (1915, 'Seyit Onbasi', 'Savas')";
        System.out.println("eklenen satir sayisi "  + st.executeUpdate(insertVeri));


        //tablomuza coklu veri ekleyelim-------------------------------------------

        String [] veriler = {
                "insert into Kahramanlar values (1915, 'Seyit Onbasi', 'Savas')",
                "insert into Kahramanlar values (1453, 'Ulubatli Hasan', 'Savas')",
                "insert into Kahramanlar values (680, 'Tarik bin ziyad', 'Savas')",
                "insert into Kahramanlar values (1938, 'Mustafa Kemal', 'Savas')"
        };

        int count = 0 ;
        for (String each: veriler){
                count += st.executeUpdate(each);
        }
        System.out.println("Kahramanlar tablosuna Eklenen satir sayisi " + count);


        */

        //kahramanlari listeleyiniz---------------------------------------------

        String selectAlan = "select * from Kahramanlar";
        ResultSet tablo1 = st.executeQuery(selectAlan);

        while(tablo1.next()) {
            System.out.println(tablo1.getInt(1) + "  " +
                                tablo1.getString(2) + "  " +
                                tablo1.getString(3) );
        }
        System.out.println("++++++++");

        // 1453 yili kaydini "Ulubatli Ali" diye güncelle-----------------------------------

        String update1 ="update Kahramanlar set isim ='Ulubatli Ali' where yil = 1453";
        st.executeUpdate(update1);

        //yili 680 olan kaydi siliniz.-------------------------------------------------------

        String delete1 = "delete from Kahramanlar where yil = '680'";
        st.execute(delete1);


        con.close();
        st.close();
        tablo1.close();



    }
}