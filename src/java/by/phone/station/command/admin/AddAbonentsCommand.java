/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.command.admin;
import by.phone.station.command.ActionCommand;
import by.phone.station.dao.AbonentDAO;
import by.phone.station.dao.pool.ConnectionPool;
import by.phone.station.model.Abonent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author user
 */
public class AddAbonentsCommand implements ActionCommand {
    
    @Override
    public String execute(HttpServletRequest request) {
        String page= null;
        Connection conn;
        try {
            String firstname = request.getParameter("firstname");
            String middlename = request.getParameter("middlename");
            String lastname = request.getParameter("lastname");
            String password = request.getParameter("passwordclient");
            String homephonenumber = request.getParameter("homephonenumber");
            String city = request.getParameter("city");
            String phonenumber = request.getParameter("phonenumber");
            String street = request.getParameter("street");
            String numberpasport = request.getParameter("numpasport");
            String login = request.getParameter("numclient");
            String dateBirthDay = request.getParameter("datebirthday");
            Abonent abonent = new Abonent();
            abonent.setFirstName(firstname);
            abonent.setMiddleName(middlename);
            abonent.setLastName(lastname);
            abonent.setCity(city);
            abonent.setHomePhoneNumber(homephonenumber);
            abonent.setMobPhoneNumber(phonenumber);
            abonent.setNumberPassport(numberpasport);
            abonent.setStreet(street);
            abonent.setPassword(password);
            abonent.setLogin(login);
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Calendar cal = Calendar.getInstance();
            abonent.setDateRegistr(cal.getTime());
            Date date;
            try {
                date = format.parse(dateBirthDay);
                abonent.setDateOfBirthday(date);
            } catch (ParseException ex) {
                Logger.getLogger(AddAbonentsCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
            conn = ConnectionPool.getConnection();
            AbonentDAO ab = new AbonentDAO(conn);
            ab.create(abonent);
            ConnectionPool.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(AddAbonentsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        GetAbonentsCommand get = new GetAbonentsCommand();
        page = get.execute(request);
        return page;
    }
}
