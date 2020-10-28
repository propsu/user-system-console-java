package com.company.menu;

import com.company.UserSystem;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public class SystemMenu extends Menu{
    public SystemMenu(Scanner scan, UserSystem userSystem){
        super("System użytkowników", scan, userSystem);
        options.add("Rejestracja");
        options.add("Logowanie");
        Collections.reverse(options);
    }
    private void logIn(){
        System.out.println("-------------");
        System.out.println("Okno logowania");
        System.out.println("-------------");
        System.out.println("Wpisz nazwę użytkownika: ");
        String username = scan.nextLine();
        System.out.println("Wpisz hasło: ");
        String password = scan.nextLine();
        System.out.println(userSystem.logIn(username, password));
    }

    private void register(){
        System.out.println("-------------");
        System.out.println("Okno rejestracji");
        System.out.println("-------------");
        System.out.println("Wpisz nazwę użytkownika: ");
        String username = scan.nextLine();
        System.out.println("Wpisz hasło: ");
        String password = scan.nextLine();
        try {
            System.out.println(userSystem.registerUser(username, password));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void action(int index) {
        try{
            String option = options.get(--index);

            switch (option){
                case "Rejestracja":
                    register();
                    break;
                case "Logowanie":
                    logIn();
                    break;
                case "Wyjście":
                    quit();
                    break;
            }
        } catch (IndexOutOfBoundsException e){
            System.out.println("Nie ma takiej opcji");
        }


    }
}
