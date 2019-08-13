package util;

import model.User;
import org.hibernate.cfg.Configuration;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbHelper {

    private static DbHelper instance;
    private Properties properties;
    private Connection connection;

    private DbHelper(){
        properties = new Properties();
        try {
            properties.load(new FileReader("resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DbHelper getInstance(){
        if(instance == null){
            instance = new DbHelper();
        }
        return instance;
    }

    public Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", properties.getProperty("db_url"));
        configuration.setProperty("hibernate.connection.username", properties.getProperty("db_user"));
        configuration.setProperty("hibernate.connection.password", properties.getProperty("db_password"));
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(properties.getProperty("db_url"), properties.getProperty("db_user"),
                        properties.getProperty("db_password"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public Properties getProperties() {
        return properties;
    }
}
