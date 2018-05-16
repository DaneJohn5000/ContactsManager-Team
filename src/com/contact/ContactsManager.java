package com.contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactsManager {
    private static HashMap<Integer, Contact> contacts;
    private static Integer lastId = 0;
    private static Integer total= 0;

    public ContactsManager() {
        this.contacts = new HashMap<>();
    }


    public static HashMap<Integer, Contact> getContacts() {
        return contacts;
    }

    public static void setContacts(HashMap<Integer, Contact> contacts) {
        ContactsManager.contacts = contacts;
    }

    public static Integer getLastId() {
        return lastId;
    }

    public static Integer getTotal() {
        return total;
    }

    public static void add(Contact contact) {
        contacts.put(lastId, contact);
        lastId++;
        total++;

    }

    public static void remove(Integer id) {
        contacts.remove(id);
        total--;
    }

    public static Contact getById(Integer id) {
        return contacts.get(id);
    }
    // Returns list of Id's
    List<Integer> getIds() {
        List<Integer> ids = new ArrayList<>();
        for(Map.Entry<Integer, Contact> entry : contacts.entrySet()) {
            ids.add(entry.getKey());
        }
        return ids;
    }

    // Returns list of Id's matching search results.
    public List<Integer> searchIds(String search) {

        List<Integer> ids = new ArrayList<>();

        for(Map.Entry<Integer, Contact> entry : contacts.entrySet()) {
            Integer id = entry.getKey();
            Contact contact = entry.getValue();
            if(contact.getFullName().contains(search)) {
                ids.add(id);
            }
        }
        return ids;
    }
}
