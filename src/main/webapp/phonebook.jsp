<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page import="ru.academits.model.Contact" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <%
        List<Contact> contactList = (List<Contact>) request.getAttribute("contactList");
        Contact currentContact = (Contact) request.getAttribute("currentContact");
    %>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="css/phonebook.css"/>
    <title>Phone book</title>
</head>
<body>

<div class="delete-dialog"></div>

<div class="alert" title="Нет выбранных контактов"></div>
<div class="content">

    <div>
        <form class="filter-container" method="POST" action="filter">
            <label class="filter-label mb-0 mr-2">
                Введите текст:
                <%String filter = request.getParameter("filter");%>
                <input type="text" name="f" class="form-control input-sm"
                       value='<%=filter == null ? "" : filter%>'/>
            </label>
            <button type="submit" class="btn btn-primary">Отфильтровать</button>
            <button class="btn btn-primary">Сбросить фильтр</button>
        </form>
    </div>

    <table class="table table-bordered contact-table" id="contacts_table">
        <thead>
        <tr>
            <th>
                <label class="select-all-label">
                    <input type="checkbox" id="main_checkbox" title="Выбрать"/>
                </label>
            </th>
            <th>№</th>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Телефон</th>
            <th>Удалить</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="contact" items="${contactList}" varStatus="loop">
            <tr>
                <td>
                    <label class="select-me-label">
                        <input type="checkbox" class="select-me" name="contactId" value="${contact.getId()}"/>
                    </label>
                </td>
                <td>${loop.index + 1}</td>
                <td><c:out value="${contact.getLastName()}"/></td>
                <td><c:out value="${contact.getFirstName()}"/></td>
                <td><c:out value="${contact.getPhone()}"/></td>
                <td>
                    <form action="delete" method="post">
                        <input type="hidden" name="id" value="${contact.getId()}"/>
                        <button type="submit" class='btn btn-primary'>Удалить</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <button type="button" class="btn btn-primary">Удалить выбранные</button>

    <br>
    <label class="server-error-message-container">
        <span class="error-message">
             <c:if test="${not empty contactValidation.globalError}">
                 <c:out value="${contactValidation.globalError}">
                 </c:out>
             </c:if>
        </span>

    </label>
    <form action="add" method="POST">
        <div>
            <label class="form-label">
                <span class="form-field">Фамилия:</span>
                <input type="text" class="ml-1 form-control input-sm form-input" name="lastName"
                       value='<%=currentContact.getLastName() == null ? "" : currentContact.getLastName() %>'/>
                <span class="error-message">
                     <c:if test="${not empty contactValidation.lastNameError}">
                         <c:out value="${contactValidation.lastNameError}">
                         </c:out>
                     </c:if>
                </span>
            </label>
        </div>
        <div>
            <label class="form-label">
                <span class="form-field">Имя:</span>
                <input type="text" class="ml-1 form-control input-sm form-input" name="firstName"
                       value='<%=currentContact.getFirstName() == null ? "" : currentContact.getFirstName() %>'/>
                <span class="error-message">
                    <c:if test="${not empty contactValidation.firstNameError}">
                        <c:out value="${contactValidation.firstNameError}">
                        </c:out>
                    </c:if>
                </span>
            </label>
        </div>
        <div>
            <label class="form-label">
                <span class="form-field">Телефон:</span>
                <input type="number" class="ml-1 form-control input-sm form-input" name="phone"
                       value='<%=currentContact.getPhone() == null ? "" : currentContact.getPhone() %>'/>
                <span class="error-message">
                     <c:if test="${not empty contactValidation.phoneError}">
                         <c:out value="${contactValidation.phoneError}">
                         </c:out>
                     </c:if>
                </span>
            </label>
        </div>
        <button type="submit" class="btn btn-primary">Добавить</button>
    </form>
</div>
</body>
</html>