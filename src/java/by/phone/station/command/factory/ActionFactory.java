/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.command.factory;

import by.phone.station.command.ActionCommand;
import by.phone.station.command.client.EmptyCommand;
import by.phone.station.resource.MessageManager;
import by.phone.station.resource.ResourceManager;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author user
 */

//класс для опеределение прищедшей команды от клиента
public class ActionFactory {
    
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        //чтение параметра action из запроса
        String action = request.getParameter("action");
        
        ResourceManager manager = ResourceManager.INSTANCE;
        //если action пустой, то возвращаем текущую страницу
        if(action == null|| action.isEmpty()) {
            return current;
        }
        try {
            //если пришла команда на смену языка системы
            if (action.compareTo("changelang") == 0) {
                //чтение параметра l
                String l = request.getParameter("l");
                //если надо сменить на русский
                if (l.compareTo("ru") == 0) {
                    manager.changeResource(new Locale("ru","RU"));
                    action="gethomepageabonent";
                }
                //если надо сменить на английский
                if (l.compareTo("en") == 0) {
                    manager.changeResource(new Locale("en","US"));
                    request.getSession().setAttribute("manager", manager);
                    action="gethomepageabonent";
                }
            }
            //полуаем из перечисления команд, требуемую команду
            CommandEnum currentEnum =CommandEnum.valueOf(action.toUpperCase());
            //возвращаем класс, который будет обрабатывать пришедшую команду
            current = currentEnum.getCurrentCommand();
           
        } catch(IllegalArgumentException e) {
            request.setAttribute("wrongAction", MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }   
}
