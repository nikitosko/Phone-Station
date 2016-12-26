/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.command.client;
import by.phone.station.command.ActionCommand;
import by.phone.station.dao.InvoiceDAO;
import by.phone.station.dao.pool.ConnectionPool;
import by.phone.station.model.Abonent;
import by.phone.station.model.Invoice;
import by.phone.station.model.Service;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author user
 */
//класс для обработки команды добавления услуги для абонента
public class AddServiceForCommand implements ActionCommand
{
    //класс для обработки команды добавления услуги для абонента
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        //получаем из запроса id услуги и id абонента
        int idS = Integer.parseInt(request.getParameter("idS"));
        int idAb = Integer.parseInt(request.getParameter("idA"));
        Connection conn;
        try {
            conn = ConnectionPool.getConnection(); //открываем соединение
            InvoiceDAO invoiceDAO = new InvoiceDAO(conn);
            Invoice invoice = new Invoice();
            Abonent abonent = new Abonent();
            abonent.setId(idAb);
            Service service = new Service();
            service.setId(idS);
            //получаем текущую дату для занесения в бд даты подключения услуги
            Calendar cal = Calendar.getInstance();
            invoice.setAbonent(abonent);
            invoice.setService(service);
            invoice.setPaid(false); // установка статуса оплаты "неопалчен"
            invoice.setDateStart(cal.getTime());
            invoiceDAO.create(invoice); //добавление в бд информации о подключении услуги
            ConnectionPool.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(AddServiceForCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        //вызов команды для отображения всех услуг доступных для абонента
        GetServicesClientCommand get = new GetServicesClientCommand();
        page = get.execute(request);
        return page;
    }
    
}
