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
public class UpdateServiceCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        String page= null;
        Connection conn;
        try {
            conn = ConnectionPool.getConnection();
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String price = request.getParameter("price");
            Service service = new Service();
            service.setDescription(description);
            service.setName(name);
            service.setPrice(Double.parseDouble(price));
            service.setId(Integer.parseInt(id));
            request.setAttribute("id", id);
            request.setAttribute("flag", "1");
            ServiceDAO ab = new ServiceDAO(conn);
            ab.update(service);
            ConnectionPool.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(AddAbonentsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        DetailServiceCommand get = new DetailServiceCommand();
        page = get.execute(request);
        return page;
    }
    
}
