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

    public void add(List<String> contacts) {
        for(String contactString: contacts) {
            String[] contact = contactString.split(",");
            Contact newContact = new Contact(contact[0], contact[1], contact[2], contact[3]);
            add(newContact);
        }
    }

    public void update(Integer id, Contact contact) {
        contacts.put(id , contact);
    }

    public static void remove(Integer id) {
        contacts.remove(id);
        total--;
    }

    public static List<Contact> getAllContacts() {
        List<Contact> contactsList = new ArrayList<>();

        for(Map.Entry<Integer, Contact> entry : contacts.entrySet()) {
            contactsList.add(entry.getValue());
        }
        return contactsList;


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

   public Integer findByName(String firstName, String lastName){
       for(Map.Entry<Integer, Contact> entry : contacts.entrySet()) {
           Integer id = entry.getKey();
           Contact contact = entry.getValue();
           if(contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
               return id;
           }
       }
       return null;
    }


    public List<String> toCSV() {
       List<String> csv = new ArrayList<>();
       for(Contact contact : getAllContacts()) {
           csv.add(contact.toCSV());
       }
       return csv;
    }



}
