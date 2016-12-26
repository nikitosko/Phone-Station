/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.command.admin;
import by.phone.station.command.ActionCommand;
import by.phone.station.dao.ServiceDAO;
import by.phone.station.dao.pool.ConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author user
 */
public class DeleteServiceCommand implements ActionCommand {
    
    @Override
    public String execute(HttpServletRequest request) {
        String page= null;
        Connection conn;
        try {
            conn = ConnectionPool.getConnection();
            String id = request.getParameter("id");
            ServiceDAO ab = new ServiceDAO(conn);
            ab.delete(Integer.parseInt(id));
            ConnectionPool.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(AddAbonentsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        GetServicesCommand get = new GetServicesCommand();
        page = get.execute(request);
        return page;
    }
}
