package katok.Core.SQL;

import java.util.ArrayList;
import java.sql.*;


public class SqlDataMiner {
    private SqlDataMiner() throws SQLException {
    }
    private static SqlDataMiner dataMiner;

    public static SqlDataMiner getMiner() throws SQLException {
        if(dataMiner==null){
            dataMiner = new SqlDataMiner();
        }
        return dataMiner;
    }
    private ResultSet getDataByQuery() throws SQLException {
        String query = "select * from credentials";
        String url = "jdbc:mysql://localhost:3306/final_project?useSSL=false&serverTimezone=UTC";
        String login = "root";
        String password = "root";
        Connection con = DriverManager.getConnection(url, login, password);
        Statement st = con.createStatement();
        return st.executeQuery(query);
    }

    private ResultSet credentialsSet = getDataByQuery();

    public String getFieldByColumn(String columnName) throws SQLException {
        System.out.println();
        credentialsSet.first();
        return credentialsSet.getString(columnName);
    }
}

