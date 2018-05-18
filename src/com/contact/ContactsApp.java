package com.contact;

import com.contact.util.Input;
import com.contact.util.File;
import java.util.List;

public class ContactsApp {
    private static ContactsManager contacts = new ContactsManager();
    public static File contactsFile = new File("data","contacts.txt");
    private static boolean exitApp = false;
    private static Input input = new Input();

    public static void main(String[] args) {

        contacts.add(contactsFile.read());
        System.out.println("Loaded " + contacts.getTotal() + " contacts");
        // Import existing file or create a new one
        // load file to contacts manager
        // Application loop cycle
        do {
           selection(menu());
        }
        while(!exitApp);

    }

    private static void selection(int choice){
    switch(choice) {
        case 1:
            System.out.println("You chose to view the contacts\n"); viewContacts();
            break;
        case 2:
            System.out.println("You chose to add a new contact\n"); addContact();
            break;
        case 3:
            System.out.println("You chose to search\n"); searchContacts();
            break;
        case 4:
            System.out.println("you chose to delete\n"); deleteContact();
            break;
        case 5:
            System.out.println("you chose to exit\n"); exit();
            break;
    }


    }
    private static int menu() {
        System.out.println("\n\n1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n");
        return input.getInt("Enter a number between 1 and 5: ", 1, 5);
    }
    private static void viewContacts() {
        System.out.println(String.format("%1$5s","ID")  + " | " +
                String.format("%1$5s","FIRST ")  + " | " +
                String.format("%1$5s","LAST ")  + " | " +
                String.format("%1$5s","PHONE NUMBER ")  + " | " +
                String.format("%1$5s","EMAIL ")  + " | " );

        for (Integer id : contacts.getIds()){
           printContact(id);
        }

    }

    // pass specific contacts to view
    private static void viewContacts(List<Integer> ids) {
        for (Integer id  : ids){
           printContact(id);
        }
    }

    private static void printContact(Integer id) {
        System.out.println(
                String.format("%1$5s",id)  + " | " +
                        contacts.getById(id).toString());
    }

    private static void addContact(){
       String firstName = input.getString("Enter your first name: ");
       String lastName = input.getString("Enter your last name: ");
       String phoneNumber = input.getString("Enter your phone number: ");
       String email = input.getString("Enter your email ");
        Integer result = contacts.findByName(firstName, lastName);
        if(result != null){
            boolean overwrite = input.yesNo("Contact exists. Would you like to overwrite? (yes/no)");
            if(overwrite){
                contacts.update(result, new Contact(firstName,lastName,phoneNumber,email) );
                System.out.println("Contact updated");
            } else {
                return;
            }
        } else {
            contacts.add(new Contact(firstName, lastName, phoneNumber, email));
        }
            contactsFile.overwrite(contacts.toCSV());

    }
    private static void searchContacts(){

        // take some input from user
        String search = input.getString("Please search by name:");

        //return values that match input
        List<Integer> searchList = contacts.searchIds(search);

        // display, use viewContacts somehow~~
        viewContacts(searchList);

    }
    private static void deleteContact(){

        Integer id = input.getInt("Please input an id to delete: ");

            contacts.remove(id);
            contactsFile.overwrite(contacts.toCSV());
            System.out.println("If contact existed with this ID it is deleted\n");

    }
    private static void exit(){
       exitApp = true;
        // SAVE file

        contactsFile.overwrite(contacts.toCSV());
        // CLOSE
    }
}
