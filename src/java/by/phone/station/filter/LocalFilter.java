/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.filter;

import by.phone.station.resource.ResourceManager;
import java.io.IOException;
import java.util.Locale;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author
 */

//фильтр для установки начальной локализации системы
public class LocalFilter implements Filter{

    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //создаем менеджер для работы локализацией
        ResourceManager manager = ResourceManager.INSTANCE;
        //добавлем менеджера в результат ответа 
        request.setAttribute("manager", manager);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        filterConfig = null;
     }
   
}
