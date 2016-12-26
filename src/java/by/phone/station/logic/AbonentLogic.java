/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.logic;

import by.phone.station.dao.AbonentDAO;
import by.phone.station.dao.pool.ConnectionPool;
import by.phone.station.model.Abonent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class AbonentLogic {
    
    //метод для подсчета количества новых абонентов
    public static int countNewAbonent() {
        int count = 0;
        try {
            //Создание соединения с бд
            Connection conn = ConnectionPool.getConnection();
            // создание DAO для сущности абонент
            AbonentDAO abonentDAO = new AbonentDAO(conn);
            //получения списка всех абонентов систмы
            List<Abonent> abonents = abonentDAO.findAll();
            //получение текущей даты
            Calendar call = Calendar.getInstance();
            Date currentDate = call.getTime();
            //проход по списку всех абонентов
            for (Abonent abonent : abonents) {
                //если дата регистрации не равна нулю
                if (abonent.getDateRegistr() != null) {
                    //получаем даут регистрации абонента
                    Date abonDate = abonent.getDateRegistr();
                    //если дата регистрации равна текущей дате, то наращиваем счетчик
                    if (currentDate.getDate() == abonDate.getDate() && currentDate.getMonth() == abonDate.getMonth() && currentDate.getYear() == abonDate.getYear())
                        count++;
                }
            }
            //закрываем соединение
            ConnectionPool.closeConnection(conn);
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(AbonentLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
}
