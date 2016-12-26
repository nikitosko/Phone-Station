/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.dao;

import by.phone.station.model.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */

//Класс DAO для работы с таблицей services
public class ServiceDAO extends AbstractDAO<Service> {

    //запросы к бд, использующееся в данном DAO
    private final String SQL_ALL_SERVICES = "Select * from services";
    private final String SQL_ADD_SERVICE = "Insert into services (name, description, price) values(?,?,?)";
    private final String SQL_UPDATE_SERVICE = "UPDATE services set name=?, description=?, price=? where id=?";
    private final String SQL_DELETE_SERVICE = "Delete from services where id=?";

    public ServiceDAO(Connection connection) {
        super(connection);
    }
    
    //получение списка всех услуг из бд
    @Override
    public List<Service> findAll() {
        List<Service> list = new ArrayList<Service>();
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_ALL_SERVICES);
            while(resultSet.next()) {
                Service ab = new Service();
                ab.setId(resultSet.getInt("id"));
                ab.setName(resultSet.getString("name"));
                ab.setDescription(resultSet.getString("description"));
                ab.setPrice(resultSet.getDouble("price"));
                list.add(ab);
            }
        } catch(SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }     
        return list;
    }

    //получение информации об услуге по id из бд
    @Override
    public Service findEntityById(int id) {
        Service service = new Service();
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from services where id=" + id);
            rs.next();
            service.setName(rs.getString("name"));
            service.setId(id);
            service.setDescription(rs.getString("description"));
            service.setPrice(rs.getDouble("price"));
        } catch (SQLException ex) {
            Logger.getLogger(AbonentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(st);
        }    
        return service;
    }

    //удаление услуги по id из бд
    @Override
    public boolean delete(int id) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_DELETE_SERVICE);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AbonentDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            close(st);
        } 
        return true;
    }

    @Override
    public boolean delete(Service entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //добавление услуги в бд
    @Override
    public boolean create(Service entity) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_ADD_SERVICE);
            st.setString(1, entity.getName());
            st.setString(2, entity.getDescription());
            st.setDouble(3, entity.getPrice());
            st.executeUpdate();
        } catch(SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }     
        return true;
    }
    
//обновление услуги в бд
    @Override
    public Service update(Service entity) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_UPDATE_SERVICE); 
            st.setString(1, entity.getName());
            st.setString(2, entity.getDescription());
            st.setDouble(3, entity.getPrice());
            st.setInt(4, entity.getId());
            st.executeUpdate();
        } catch(SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }     
        return entity;
    }
    
}
