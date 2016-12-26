/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.command.client;

import by.phone.station.command.admin.LoginCommand;
import by.phone.station.model.Abonent;
import by.phone.station.logic.LoginLogic;
import by.phone.station.resource.ConfigurationManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import by.phone.station.command.ActionCommand;
/**
 *
 * @author user
 */

//класс для обработки входа в систему для абонента
public class LoginAbonentCommand implements ActionCommand{

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    
    @Override
    public String execute(HttpServletRequest request) {
        String page= null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        HttpSession session = request.getSession();
        Abonent abonent;
        try {
            //проверка существаония в бд абонента с паролем и логином
            abonent = LoginLogic.checkLoginAbonent(login, pass);
            //получение статус блокировки абоента
            if (abonent.isBlock()) {
                request.setAttribute("block", true);
            }
            else
                request.setAttribute("block", false);
            
            if(abonent.getId() != 0) {
                //установка в сессии параметров
                session.setAttribute("abonent", abonent);
                //установка в сессии параметра auth, если true значит авторизован
                session.setAttribute("auth", true); 
                session.setAttribute("error", false);
                page = ConfigurationManager.getProperty("path.page.abonent.index");
            } else {
                session.setAttribute("auth", false);
                session.setAttribute("error", true);
                page = ConfigurationManager.getProperty("path.page.abonent.index");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return page;
    }
}
