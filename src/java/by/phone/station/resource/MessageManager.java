/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.resource;

import java.util.ResourceBundle;

/**
 *
 * @author user
 */

//класс для работы с файлом message.properties, содержащим сообщения системы
public class MessageManager {
    
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("by.phone.station.properties.messages");
    
    private MessageManager() { }
    
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}