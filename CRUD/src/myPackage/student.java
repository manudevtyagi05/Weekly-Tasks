package myPackage;

import java.sql.*;

public class student {

    public void createDatabase() {
        try {
            String url ="jdbc:mysql://localhost:3306/";
            String userName = "root";
            String password = "Manu@2003";

            Connection conn = DriverManager.getConnection(url,userName, password);
            Statement stm = conn.createStatement();

            String query = "create database student";
            stm.execute(query);
            System.out.println("Database created successfully");
            conn.close();
        } catch (Exception e){
            System.out.println(e);
        }

    }

    public void createTable() {
        try {
            String url ="jdbc:mysql://localhost:3306/student";
            String userName = "root";
            String password = "Manu@2003";

            Connection conn = DriverManager.getConnection(url,userName, password);
            Statement stm = conn.createStatement();

            String query = "create table student(roll int (3), name varchar (50) , marks int)";
            stm.execute(query);
            System.out.println("Table created successfully");
            conn.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void createData() {
        try {
            String url ="jdbc:mysql://localhost:3306/";
            String db = "student";
            String userName = "root";
            String password = "Manu@2003";

            Connection conn = DriverManager.getConnection(url+db,userName, password);
            Statement stm = conn.createStatement();

            String query = "INSERT into student VALUES (2,'dev', 99)";
            stm.execute(query);
            System.out.println("Data inserted successfully");
            conn.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void readData() {
        try {
            String url ="jdbc:mysql://localhost:3306/";
            String db = "student";
            String userName = "root";
            String password = "Manu@2003";

            Connection conn = DriverManager.getConnection(url+db,userName, password);
            Statement stm = conn.createStatement();

            String query = "select * from student";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                System.out.println("id = "+rs.getInt(1));
                System.out.println("Name = "+rs.getString(2));
                System.out.println("Marks = "+rs.getInt(3));
            }
            System.out.println("Read successfully");
            conn.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void updateData() {
        try {
            String url ="jdbc:mysql://localhost:3306/";
            String db = "student";
            String userName = "root";
            String password = "Manu@2003";

            Connection conn = DriverManager.getConnection(url+db,userName, password);
            String query = "UPDATE student SET name = ? where roll = ? ";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1,"Dev");
            pstm.setInt(2,2);

            pstm.executeUpdate();
            System.out.println("Data updated successfully");
            conn.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void deleteData() {
        try {
            String url ="jdbc:mysql://localhost:3306/student";
            String userName = "root";
            String password = "Manu@2003";

            Connection conn = DriverManager.getConnection(url,userName, password);
            String query = "DELETE FROM student WHERE roll = ?";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1,2);
            pstm.executeUpdate();
            System.out.println("student deleted successfully");
            conn.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
