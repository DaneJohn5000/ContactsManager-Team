package com.contact;

import com.contact.util.Input;

import java.io.File;

public class Main {
    public static ContactsManager contacts = new ContactsManager();
    public static File contactsFile;
    public static boolean exitApp = false;
    private static Input input = new Input();

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

