/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.model;

/**
 *
 * @author user
 */

//класс является родителем всех классов модели
public class Entity {
    private int id;

    public Entity() {
    }

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
