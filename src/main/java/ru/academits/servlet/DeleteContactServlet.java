package ru.academits.servlet;

import ru.academits.PhoneBook;
import ru.academits.coverter.ContactConverter;
import ru.academits.service.ContactService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class DeleteContactServlet extends HttpServlet {
    private ContactService contactService = PhoneBook.contactService;
    private ContactConverter contactConverter = PhoneBook.contactConverter;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String contactParams = req.getReader().lines().collect(Collectors.joining(System.lineSeparator())).substring(3);
        int contactId = Integer.parseInt(contactParams);

        contactService.deleteContact(contactId);

        resp.sendRedirect("/phonebook");
    }
}
