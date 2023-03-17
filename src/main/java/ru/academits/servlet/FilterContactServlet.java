package ru.academits.servlet;

import ru.academits.PhoneBook;
import ru.academits.model.Contact;
import ru.academits.service.ContactService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FilterContactServlet extends HttpServlet {
    private final ContactService contactService = PhoneBook.contactService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String filterStringParams = req.getReader().lines().collect(Collectors.joining(System.lineSeparator())).substring(2);
        List<Contact> filteredContacts = contactService.getContactsByFilter(filterStringParams);

        req.setAttribute("contacts", filteredContacts);
        resp.sendRedirect("/phonebook");
    }
}
