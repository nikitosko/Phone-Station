/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.command.admin;
import by.phone.station.command.ActionCommand;
import by.phone.station.dao.ServiceDAO;
import by.phone.station.dao.pool.ConnectionPool;
import by.phone.station.model.Service;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author user
 */
public class AddServiceCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        String page= null;
        Connection conn;
        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String price = request.getParameter("price");
            Service service = new Service();
            service.setDescription(description);
            service.setName(name);
            service.setPrice(Double.parseDouble(price));
            conn = ConnectionPool.getConnection();
            ServiceDAO ab = new ServiceDAO(conn);
            ab.create(service);
            ConnectionPool.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(AddAbonentsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        GetServicesCommand get = new GetServicesCommand();
        page = get.execute(request);
        return page;
    }
    
}
