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
public class GetContactsCommand implements ActionCommand{

    //класс для обработки команды отображения странцы контакты для абонента
    @Override
    public String execute(HttpServletRequest request) {
        //получение пути к странице jsp контакты
        String page = ConfigurationManager.getProperty("path.page.abonent.contacts");
        return page;
    }
    
}
