/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.controller;
import by.phone.station.command.ActionCommand;
import by.phone.station.command.factory.ActionFactory;
import by.phone.station.resource.ConfigurationManager;
import by.phone.station.resource.MessageManager;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//сервлет или контроллер, обрабатывает все запросы поступающие от клиента
public class Controller extends HttpServlet {

    //метода для обработки запроса
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
      
        request.setCharacterEncoding("UTF-8");
        String page= null;
        ActionFactory client = new ActionFactory();
        //определение команды пришедшей от клиента
        ActionCommand command = client.defineCommand(request);
        //получени отображаемой страницы
        page=command.execute(request);
        if(page != null) 
        {
            //переход на страницу полученной командой execute
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);         
        } 
        else
        {
            //если такой странцы нету, то выводим соответсвтующее сообщение
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
