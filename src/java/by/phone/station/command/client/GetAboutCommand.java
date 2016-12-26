/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.command.client;
import by.phone.station.command.ActionCommand;
import by.phone.station.resource.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author user
 */
public class GetAboutCommand implements ActionCommand{
    
//класс для обработки команды получения страницы about услуги для абонента
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.abonent.about");
        return page;
    }
    
}
