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

//класс для работы с файлом config.properties, содержащим пути к страницам
public class ConfigurationManager {
    
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("by.phone.station.properties.config");
    
    private ConfigurationManager() { } 
    
    
    //метод для получения свойства из файла config.properties
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}