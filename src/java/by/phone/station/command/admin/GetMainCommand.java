/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.command.admin;
import by.phone.station.command.ActionCommand;
import by.phone.station.dao.AbonentDAO;
import by.phone.station.dao.InvoiceDAO;
import by.phone.station.dao.pool.ConnectionPool;
import by.phone.station.logic.AbonentLogic;
import by.phone.station.logic.ServiceLogic;
import by.phone.station.resource.ConfigurationManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author user
 */
public class GetMainCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        try {
            Connection conn = ConnectionPool.getConnection();
            AbonentDAO abonentDAO = new AbonentDAO(conn);
            int s = abonentDAO.findAll().size();
            InvoiceDAO invoiceDAO = new InvoiceDAO(conn);
            request.setAttribute("countAbonent", s);
            request.setAttribute("countService", invoiceDAO.count());
            request.setAttribute("countNewAbonent", AbonentLogic.countNewAbonent());
            request.setAttribute("countByMonth", ServiceLogic.countByMonth());
            ConnectionPool.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(GetMainCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ConfigurationManager.getProperty("path.page.admin.main");
    }
    
}
