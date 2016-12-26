/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.dao;

import by.phone.station.model.Entity;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user
 */

//класс родитель для всех классов DAO
public abstract class AbstractDAO <T extends Entity> {
    
    protected Connection connection;
    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }
    //обязательные методы для реализации в конкретных DAO
    public abstract List<T> findAll();
    public abstract T findEntityById(int id);
    public abstract boolean delete(int id);
    public abstract boolean delete(T entity);
    public abstract boolean create(T entity);
    public abstract T update(T entity);
    public void close(Statement st) {
        try {
            if(st != null) {
                  st.close();
            }
           
        } catch(SQLException e) {

        }
    }
}