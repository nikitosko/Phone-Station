/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.command.client;

import by.phone.station.command.ActionCommand;
import by.phone.station.dao.AbonentDAO;
import by.phone.station.dao.pool.ConnectionPool;
import by.phone.station.model.Abonent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author user
 */

//класс для обработки команды смены пароля для абонента
public class ChangePasswordCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        
        //получение id абонента и нового пароля
        String id = request.getParameter("id");
        String newPassword = request.getParameter("newpassword");
        Abonent abonent = new Abonent();
        try {
            
            //полчение соединения с бд
            Connection conn = ConnectionPool.getConnection();
            AbonentDAO abonentDAO = new AbonentDAO(conn);
            abonent.setPassword(newPassword);
            abonent.setId(Integer.parseInt(id));
            //обнолвение пароля в бд
            abonentDAO.updatePassword(abonent);
        } catch (SQLException ex) {
            Logger.getLogger(ChangePasswordCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        LogoutAbonentCommand comm  = new LogoutAbonentCommand();
        return comm.execute(request);
    }
    
}
