package com.contact;

import com.contact.util.Input;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ContactsApp {
//    public static ContactsManager contacts = new ContactsManager();
    public static File contactsFile;
    public static boolean exitApp = false;
    private static Input input = new Input();

       private static List<Contact> contacts = new ArrayList<>();
    public static void main(String[] args) {
        contacts.add(new Contact("Arnold","Stallone","555-555-5555","arnoldStallone@gmail.com"));
        contacts.add(new Contact("Arnold","Stallone","555-555-5555","arnoldStallone@gmail.com"));
        contacts.add(new Contact("Arnold","Stallone","555-555-5555","arnoldStallone@gmail.com"));
        contacts.add(new Contact("Arnold","Stallone","555-555-5555","arnoldStallone@gmail.com"));
        do {
           selection( menu());
        }
    while(!exitApp);


    }


    private static void selection(int choice){

    switch(choice) {
        case 1:
            System.out.println("you chose to view the contacts"); viewContacts();
            break;
        case 2:
            System.out.println("You chose to add a new contact"); addContact();
            break;
        case 3:
            System.out.println("you chose to search");menu();
            break;
        case 4:
            System.out.println("you chose to delete"); menu();
            break;
        case 5:
            System.out.println("you chose to exit"); exit();
            break;
    }

    }
    private static int menu() {
        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.");
        return input.getInt("Enter a number between 1 and 5: ", 1, 5);
    }
    private static void viewContacts() {
        for (Contact contact : contacts){
            System.out.println(contact.toString());
        }

    }
    private static void addContact(){
       String firstName = input.getString("Enter your first name: ");
       String lastName = input.getString("Enter your last name: ");
       String phoneNumber = input.getString("Enter your phone number: ");
       String email = input.getString("Enter your email ");

       contacts.add(new Contact(firstName,lastName,phoneNumber,email));


    }
    private static void searchContacts(){

    }
    private static void deleteContact(){

    }
    private static void exit(){
        exitApp = true;
    }
}