package MainActivity;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

import DataBase.*;

public class Controller {
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;


    public static void GetData() {

        String url= null;
        String user = null;
        String password = null;
        String query = null;

        switch(Main.nameSql){

            case "Postgresql":

                url = Postgresql.url ;
                user = Postgresql.user;
                password = Postgresql.password;
                query ="SELECT table_name\n" +
                        "  FROM information_schema.tables\n" +
                        " WHERE table_schema='public'\n" +
                        "   AND table_type='BASE TABLE';";
                break;
            case "MySQL":
                url = MySQL.url ;
                user = MySQL.user;
                password = MySQL.password;
                query ="SHOW FULL TABLES;";
                break;
            case "Oracle":
                url = Oracle.url ;
                user = Oracle.user;
                password = Oracle.password;
                query = "SELECT owner, table_name\n" +
                        "  FROM all_tables";
                break;
            default:
                System.out.println("Ошибка названия");
        }

        try(FileWriter writer = new FileWriter("data.txt", false))
        {
            try {
                con = DriverManager.getConnection(url, user, password);

                stmt = con.createStatement();

                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String name = rs.getString(1);//
                    writer.write("Table : " + name);
                    writer.append('\n');
                    PreparedStatement ps=con.prepareStatement("select * from "+ name);
                    ResultSet rs=ps.executeQuery();

                    ResultSetMetaData rsmd=rs.getMetaData();

                    writer.write("columns: "+rsmd.getColumnCount());
                    writer.append('\n');
                    for (int i=1; i<=rsmd.getColumnCount();i++) {
                        writer.write("Column Name of " + i + " column: " + rsmd.getColumnName(i));
                        writer.append('\n');
                        writer.write("Column Type Name of " + i + " column: " + rsmd.getColumnTypeName(i));
                        writer.append('\n');
                    }

                }

            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            } finally {
                try { con.close(); } catch(SQLException se) { /*can't do anything */ }
                try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
                try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}


