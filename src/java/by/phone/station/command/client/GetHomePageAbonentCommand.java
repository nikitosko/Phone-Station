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
public class GetHomePageAbonentCommand implements ActionCommand{

        //класс для обработки команды отображения главной страницы для абонента
    @Override
    public String execute(HttpServletRequest request) {
        //получение пути главной страницы из файла config.properties
        return ConfigurationManager.getProperty("path.page.abonent.index");
    }
    
}
