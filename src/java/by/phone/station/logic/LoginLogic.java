/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.logic;

import by.phone.station.dao.AbonentDAO;
import by.phone.station.dao.AdminDAO;
import by.phone.station.dao.pool.ConnectionPool;
import by.phone.station.model.Abonent;
import by.phone.station.model.Admin;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class LoginLogic {

    //проверка существования админа с логином и паролем
    public static Admin checkLogin(String enterLogin, String enterPass) throws SQLException {
        Connection conn = ConnectionPool.getConnection(); //открытие соединения
        conn.setAutoCommit(false);
        AdminDAO adminDAO = new AdminDAO(conn);
        Admin adminCheck = adminDAO.checkAdmin(enterLogin, enterPass); //провекрка в базе админа с логином enterLogin и паролем enterPass
        ConnectionPool.closeConnection(conn); //закрытие соединения
        return adminCheck; 
    }

     //проверка существования абонента с логином enterLogin и паролем enterPass
    public static Abonent checkLoginAbonent(String enterLogin, String enterPass) throws SQLException {
        Connection conn = ConnectionPool.getConnection();//открытие соединения
        conn.setAutoCommit(false);
        AbonentDAO adminDAO = new AbonentDAO(conn);
        //поиск абонента по логину и паролю
        Abonent abonent = adminDAO.findByLoginAndPassword(enterLogin, enterPass);
        ConnectionPool.closeConnection(conn);//закрытие соединения
        return abonent;
    }
}
