/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.dao.pool;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author user
 */

//Пул коннектов
public class ConnectionPool {
    
    //строка с названием соединения к базе
    private static final String DATASOURCE_NAME= "jdbc/phonesystem";
    private static DataSource dataSource;
    static {
    try {
        //считывание данных из файла context.xml
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:comp/env");
        //инициализация обьекта класса DataSource
        dataSource = (DataSource) envContext.lookup(DATASOURCE_NAME);
    } catch(NamingException e) {
            e.printStackTrace();
    }
    }
    private ConnectionPool() { }
    
    //получение соединения к бд
    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }
    //закрытие соединения
    public static void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }
}