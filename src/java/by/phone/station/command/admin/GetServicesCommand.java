/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.command.admin;
import by.phone.station.command.ActionCommand;
import by.phone.station.dao.ServiceDAO;
import by.phone.station.dao.pool.ConnectionPool;
import by.phone.station.model.Service;
import by.phone.station.resource.ConfigurationManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author user
 */
public class GetServicesCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page= null;
        List<Service> services = null;
        try {
            Connection conn = ConnectionPool.getConnection();
            ServiceDAO serviceDao = new ServiceDAO(conn);
            services = serviceDao.findAll();
            request.setAttribute("services", services);
            page = ConfigurationManager.getProperty("path.page.admin.services");
            ConnectionPool.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(GetAbonentsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return page;
    }
    
}
