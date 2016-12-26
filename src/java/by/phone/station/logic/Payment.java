/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.logic;

import by.phone.station.dao.AbonentDAO;
import by.phone.station.dao.InvoiceDAO;
import by.phone.station.dao.ServiceDAO;
import by.phone.station.dao.pool.ConnectionPool;
import by.phone.station.model.Abonent;
import by.phone.station.model.Invoice;
import by.phone.station.model.Service;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class Payment {
    
    //метод для оплаты услуги с id
    public static boolean paymentInvoice(int id) throws SQLException {
        Abonent abonent = new Abonent();
        Connection conn = ConnectionPool.getConnection();//открытие соединения
        //создание необходимых DAO
        AbonentDAO abonentDAO = new AbonentDAO(conn);
        InvoiceDAO invoiceDAO = new InvoiceDAO(conn);
        ServiceDAO serviceDAO = new ServiceDAO(conn);
        //Поиск выставленного счета по Id
        Invoice invoice = invoiceDAO.findEntityById(id);
        //поиск абонента по Id
        abonent = abonentDAO.findEntityById(invoice.getAbonent().getId());
        //Получение текущего баланса абонента
        double currentMooney = abonent.getCountMooney();
        Service service = new Service();
        //Поиск услуги по Id
        service = serviceDAO.findEntityById(invoice.getService().getId());
        //Получение стоимости услуги
        double paymentMooney = service.getPrice();
        //если текущий баланс абанента больше стоимости услуги
        if (currentMooney > paymentMooney){
            BigDecimal bigCurrentMooney = new BigDecimal(currentMooney);
            BigDecimal bigPaymentMooney= new BigDecimal(paymentMooney);
            //вычитаем из текущего баланса стоимость услуги
            BigDecimal mooney = bigCurrentMooney.subtract(bigPaymentMooney , MathContext.DECIMAL32);
           //обновляем баланс абонента
            abonent.setCountMooney(mooney.doubleValue());
            //обновляем статус оплаты услуги
            invoice.setPaid(true);
            invoiceDAO.update(invoice);
            abonentDAO.updateMooney(abonent);
            ConnectionPool.closeConnection(conn); //закрытие соединения
            return true;
        }
        else {
            //если недостаточно средств на балансе
            ConnectionPool.closeConnection(conn);
            return false;
        }
    }
    
}
