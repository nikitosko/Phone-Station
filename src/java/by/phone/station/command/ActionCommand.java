/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.command;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author user
 */
public interface ActionCommand {
    
    String execute(HttpServletRequest request);
}

