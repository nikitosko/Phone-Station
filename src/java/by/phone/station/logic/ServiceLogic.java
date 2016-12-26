/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.logic;

import by.phone.station.dao.InvoiceDAO;
import by.phone.station.dao.pool.ConnectionPool;
import by.phone.station.model.Invoice;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ServiceLogic {
    
    
    //подсчет количства подключенных услуг по месяцам
    public static int [] countByMonth() {
        //масииив на 12 месяцев
        int[] count = new int[12];
        Connection conn;
        try {
            conn = ConnectionPool.getConnection(); //открытие соединения
            InvoiceDAO serviceDAO = new InvoiceDAO(conn);
            //получение списка всех подключенных услуг
            List<Invoice> invoices = serviceDAO.findAll();
            for (Invoice invoice : invoices) {
                //получение месяца подключения услуги
                int month = invoice.getDateStart().getMonth();
                //увеличиваем счетчик в массиве для месяца
                count[month] = count[month] + 1;
            }
            ConnectionPool.closeConnection(conn); //закрытие соединения
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count;
    }
}
