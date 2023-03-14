package ru.academits.coverter;

import ru.academits.model.Contact;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactConverter {

    public Contact convertFormStringParam(String contactParams) throws UnsupportedEncodingException {
        Map<String, String> map = splitQuery(contactParams);
        Contact contact = new Contact();
        contact.setLastName(map.get("lastName"));
        contact.setFirstName(map.get("firstName"));
        contact.setPhone(map.get("phone"));

        return contact;
    }

    private static Map<String, String> splitQuery(String params) throws UnsupportedEncodingException {
        Map<String, String> queryPairs = new HashMap<>();
        String[] pairs = params.split("&");

        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            queryPairs.put(URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8), URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8));
        }

        return queryPairs;
    }
}
