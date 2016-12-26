/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.command.admin;
import by.phone.station.command.ActionCommand;
import by.phone.station.dao.AbonentDAO;
import by.phone.station.dao.InvoiceDAO;
import by.phone.station.dao.pool.ConnectionPool;
import by.phone.station.model.Abonent;
import by.phone.station.resource.ConfigurationManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author user
 */
public class DetailUserCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String id;
        if (request.getAttribute("id") == null)
            id = request.getParameter("id");
        else
            id = request.getAttribute("id").toString();
        String page = null;
        Abonent ab = null;
        String role; 
        if (request.getAttribute("role") == null)
            role = request.getParameter("role");
        else
            role = request.getAttribute("role").toString();
        try {
           Connection conn = ConnectionPool.getConnection();
           AbonentDAO abDAO = new AbonentDAO(conn);
           ab = abDAO.findEntityById(Integer.parseInt(id));
           request.setAttribute("abonent", ab);
           DateFormat format = new SimpleDateFormat("dd-MM-yyyy");

           request.setAttribute("dateBirthday", format.format(ab.getDateOfBirthday()));
           InvoiceDAO invocieDAO = new InvoiceDAO(conn);
           request.setAttribute("invoices", invocieDAO.findByIdAbonent(Integer.parseInt(id)));
           ConnectionPool.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DetailUserCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(role.compareTo("adm") == 0)
            page = ConfigurationManager.getProperty("path.page.admin.detailabonents");
        if(role.compareTo("cl") == 0)
            page = ConfigurationManager.getProperty("path.page.abonent.detail");
        return page;
    }
    
}
