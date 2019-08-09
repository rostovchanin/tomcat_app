package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("web4?").          //db name
                    append("useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");

            try {
                connection = DriverManager.getConnection(url.toString(), "admin", "Qwerty12345678");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return connection;
    }
}
