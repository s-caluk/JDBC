
//tabloyu olusturuyor , islem yapiyor , siliyor.

import java.sql.*;

public class Ubung02 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");
        Statement st = con.createStatement();



        // table ismi Heros olan bir tablo olusturun yili  isim  alani

        String tabe1Olusturma = "create table Heros (Yil int,Isim varchar(15),Alan varchar(10))";




        if (!st.execute(tabe1Olusturma)){
            System.out.println("Tablo olusturuldu");
        }


        // Heros tablamuza veri ekleyin.----------------------------------------------

        String insertveri="insert into Heros values (1915,'Seyid Onbasi','Savas')";


        System.out.println("Eklenen satir sayisi  "+ st.executeUpdate(insertveri));


        // tablomuza coklu veri ekleyelim-----------------------------------------------

        String [] veriler ={"insert into Heros values (1915,'Mehmet Akif','Edebiyat')",
                "insert into Heros values (1453,'Ulubatli Hasab','Savas')",
                "insert into Heros values (680,'Tarik Bin Ziyad','Savas')",
                "insert into Heros values (1938,'Mustafa Kemal','Yonetim')"};

        int count=0;
        for (String each:veriler){

            count+= st.executeUpdate(each);
        }

        System.out.println("Eklenen satir sayisi "+ count);



        // savas alaninda kahrmanlik yapanlari listeleyin------------------------------------------

        String selectalan="select * from Heros where alan='savas'";

        ResultSet tablo1=st.executeQuery(selectalan);

        while (tablo1.next()){

            System.out.println(tablo1.getInt(1)+" "+tablo1.getString(2)+" "+tablo1.getString(3));
        }

        System.out.println("=-=-=-=--=-=-=-=-=-=-=-=--==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==");

        // 1453 yili kaydini Ulubatli Hasan olarak guncelleyiniz------------------------------------------

        String update1="update Heros set isim='Ulubatli Hasan' where yil=1453";

        st.executeUpdate(update1);

        //

        String selectalan1="select * from Heros";

        ResultSet tablo2=st.executeQuery(selectalan1);

        while (tablo2.next()){

            System.out.println(tablo2.getInt(1)+" "+tablo2.getString(2)+" "+tablo2.getString(3));
        }

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");


        // yili 1915 olan ve kahramanlik alani savas olan kayidi siliniz.

        String delete1="delete from Heros where yil=1915 and alan='savas'";


        st.execute(delete1);

        String selectalan11="select * from Heros";

        ResultSet tablo12=st.executeQuery(selectalan11);

        while (tablo12.next()){

            System.out.println(tablo12.getInt(1)+" "+tablo12.getString(2)+" "+tablo12.getString(3));
        }
        // heros tablosunu siliniz

        String dropst="drop table Heros";


        if (!st.execute(dropst)){
            System.out.println("Tablo silindi");
        }

        con.close();           // Connection
        st.close();            // Statement
        tablo1.close();        // ResultSet
        tablo2.close();        // ResultSet
        tablo12.close();       // ResultSet

    }
}
