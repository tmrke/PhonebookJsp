package ru.academits.service;

import org.apache.commons.lang.StringUtils;
import ru.academits.PhoneBook;
import ru.academits.dao.ContactDao;
import ru.academits.model.Contact;

import java.util.ArrayList;
import java.util.List;


public class ContactService {
    private ContactDao contactDao = PhoneBook.contactDao;

    private boolean isExistContactWithPhone(String phone) {
        List<Contact> contactList = contactDao.getAllContacts();
        for (Contact contact : contactList) {
            if (contact.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public ContactValidation validateContact(Contact contact, boolean toDelete) {
        ContactValidation contactValidation = new ContactValidation();
        contactValidation.setValid(true);

        if (toDelete) {
            return contactValidation;
        }

        if (StringUtils.isEmpty(contact.getFirstName())) {
            contactValidation.setValid(false);
            contactValidation.setFirstNameError("Поле Имя должно быть заполнено.");
        }

        if (StringUtils.isEmpty(contact.getLastName())) {
            contactValidation.setValid(false);
            contactValidation.setLastNameError("Поле Фамилия должно быть заполнено.");
        }

        if (StringUtils.isEmpty(contact.getPhone())) {
            contactValidation.setValid(false);
            contactValidation.setPhoneError("Поле Телефон должно быть заполнено.");
        }

        if (isExistContactWithPhone(contact.getPhone())) {
            contactValidation.setValid(false);
            contactValidation.setGlobalError("Номер телефона не должен дублировать другие номера в телефонной книге.");
        }

        return contactValidation;
    }

    public ContactValidation addContact(Contact contact) {
        ContactValidation contactValidation = validateContact(contact, false);

        if (contactValidation.isValid()) {
            contactDao.add(contact);
        }

        return contactValidation;
    }

    public void deleteContact(int contactId) {
            contactDao.delete(contactId);
    }

    public List<Contact> getContactsByFilter(String filterString) {
        return contactDao.filter(filterString);
    }

    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }

    public void saveLastContact(Contact contact) {
        contactDao.saveLastContact(contact);
    }

    public Contact getLastContact() {
        return contactDao.getLastContact();
    }

    public void saveLastContactValidation(ContactValidation contactValidation) {
        contactDao.saveLastContactValidation(contactValidation);
    }

    public ContactValidation getLastContactValidation() {
        return contactDao.getLastContactValidation();
    }
}
