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
public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.login.admin");
        return page;
    }
}