

// bu 3 satir hep sabit, ne yaparsiniz? metod olusturup claslardan onu cagirirsiniz!
// burasi Javanin cöplügü, Java ve sql de en ufak bir syntax hatasi kodu calistirmaz.

//execute() , executeQuery() , executeUpdate() methodlari arasindaki farklar neler ??


import java.sql.*;

public class JDBC02_Query02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");
        Statement st = con.createStatement();



        /*======================================================================
       	 ORNEK1: Id'si 1'den buyuk firmalarin ismini ve iletisim_isim'ini isim
       	 ters sirali yazdirin.
      	========================================================================*/

        /*
        CREATE TABLE firmalar
        (
        id INT,
        isim VARCHAR(20),
        iletisim_isim VARCHAR(20),
        CONSTRAINT firmalar_pk PRIMARY KEY (id, isim)
        );
        INSERT INTO firmalar VALUES
        (1, 'ACB', 'Ali Can'),
        (2, 'RDB', 'Veli Gul'),
        (3, 'KMN', 'Ayse Gulmez');
         */
        System.out.println("======================= ORNEK 1 ===========================");

        String selectQuery = "SELECT isim , iletisim_isim " + //sondaki boslugu eklemezsen hata veriyor!
                            "FROM firmalar " +                //You have an error in your SQL syntax
                            "WHERE id>1 " +
                            "ORDER BY isim DESC";


        //yukaridaki yazim sekline alternatif selectquery2
        //String selectquery2 = "SELECT isim, iletisim_isim FROM firmalar WHERE id>1 ORDER BY isim DESC";

        ResultSet data = st.executeQuery(selectQuery);
        //burasi bir set ve setin icine girebilmek icin iterationa ihtiyacimiz var.
        while(data.next()){
            System.out.println(data.getString("isim")+ " " +
                               data.getString("iletisim_isim")+ " ");
        }


        /*=======================================================================
       	 ORNEK2: Iletisim isminde 'li' iceren firmalarin id'lerini ve isim'ini
       	  id sirali yazdirin.
      	========================================================================*/

        System.out.println("======================= ORNEK 2 ===========================");

        String selectQuery3 = "SELECT id, isim " +
                              "FROM firmalar " +
                              "WHERE iletisim_isim LIKE '%li%' " +  //sql 'X' kullanir dikkat
                              "ORDER BY id ";

        //alternatif yazim sekli
       // String selectQuery3 = "SELECT isim, id FROM firmalar WHERE iletisim_isim LIKE '%li%' ORDER BY id";
        ResultSet data2 = st.executeQuery(selectQuery3);

        // while(data2.next()){
        //     System.out.println(data2.getInt("id") + " " +
        //             data2.getString("isim"));
        // }

        //sorgudaki 1. indextir bu! aslolan sorgudaki siradir.
        while(data2.next()){
            System.out.println(data2.getString(1) + " " +
                    data2.getString(2));
        }
        con.close();
        st.close();
        data.close();
        data2.close();
    }
}
