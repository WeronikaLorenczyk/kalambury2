package javaclasses;


import java.sql.*;

public class DatabaseHandler {
    private static final String url = "jdbc:postgresql://localhost/postgres";
    private static final String user = "postgres";
    private static final String password="postgres";

     static DatabaseHandler db=new DatabaseHandler();

    static PreparedStatement addUser;
    static PreparedStatement getUser;

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            addUser=conn.prepareStatement("INSERT INTO players (login, password,age) VALUES (?,?,?);");
            getUser=conn.prepareStatement("SELECT * FROM players WHERE login=?");
           /* getId=conn.prepareStatement("SELECT customer_id,password FROM customers WHERE login=?");
            getShopId=conn.prepareStatement("SELECT shop_id,password FROM shops WHERE login=?");
            newUser=conn.prepareStatement("INSERT INTO customers (login, password, age, location) VALUES (?,?,?,?);");
            newShopRating =conn.prepareStatement("INSERT INTO product_customer (product_id, customer_id,rating) VALUES (?,?,?);");
            newShop=conn.prepareStatement("INSERT INTO shops (name,location,login,password) VALUES (?,?,?,?);");
            search=conn.prepareStatement("SELECT name, product_id, item_rating(product_id) from products p where is_of_cat((select name from categories c where c.category_id=p.category_id),?) and coalesce(item_rating(product_id),0)>=? " +
                    " and ? in (select value from attribute_product where product_id=p.product_id and attribute_id=(select attribute_id from attributes where name=?)) order by ?;");
            newProduct=conn.prepareStatement("INSERT INTO products(name,description,category_id) VALUES (?,?,?) RETURNING product_id;");
            newShopProduct=conn.prepareStatement("INSERT INTO shop_product (shop_id, product_id, price) VALUES (?,?,?);");
            newAttribute=conn.prepareStatement("INSERT INTO attribute_product (product_id, attribute_id, value) VALUES (?, ?, ?);");*/
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    Connection connection;
    Statement stmt;

    private DatabaseHandler(){
        connection=connect();
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            stmt.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean addUser(UserInfo ui) {
        if (ui.getAge() < 0)
            return false;
        try {
            db.addUser.setString(1,ui.login);
            db.addUser.setString(2,ui.password);
            db.addUser.setInt(3,ui.age);
            db.addUser.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static UserInfo getUser(String login) {
        try {
            db.getUser.setString(1,login);
            ResultSet rs = db.getUser.executeQuery();
            UserInfo ui=new UserInfo();
            ui.setLogin(rs.getString("login"));
            ui.setAge(rs.getInt("age"));
            ui.setPassword(rs.getString("password"));
            System.out.println("zwrocilem ui\n");
            return ui;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static boolean userExist(String login, String password) {

        try {
            db.getUser.setString(1,login);
            ResultSet rs = db.getUser.executeQuery();
            String psw=rs.getString("password");
            if (psw != null && psw.equals(password))
                return true;
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


}
