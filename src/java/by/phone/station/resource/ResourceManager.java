/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.resource;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author user
 */

//класс для работы с файлом text.properties, содержащим сообщения текст в локализованном в виде
public enum ResourceManager {
    INSTANCE;
    private  ResourceBundle resourceBundle;
    private final String resourceName = "by.phone.station.properties.text";
    private ResourceManager() {
        resourceBundle = ResourceBundle.getBundle(resourceName, Locale.getDefault());
    }
    
    //изменение языка системы
    public  void changeResource(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(resourceName, locale);
    }
    
    //получения свойсва из файла
    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}
