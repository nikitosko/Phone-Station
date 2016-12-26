/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.command.client;
import by.phone.station.command.ActionCommand;
import by.phone.station.resource.ConfigurationManager;
import by.phone.station.resource.ResourceManager;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author user
 */

//класс для обработки выхода из системы абонента
public class LogoutAbonentCommand implements ActionCommand {
    
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.abonent.index");
        request.getSession().invalidate();
        ResourceManager manager = ResourceManager.INSTANCE;
        request.getSession().setAttribute("manager", manager);
        return page;
    }
}
