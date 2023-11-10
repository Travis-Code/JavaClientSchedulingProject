package utilities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *Database Query Class. */
public class dbQuery {
    private static Statement statement;
    public static void setStatement(Connection connection) throws SQLException {
        statement = connection.createStatement();
    }
    public static Statement getStatement(){
        return statement;
    }
}
