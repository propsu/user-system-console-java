package com.company.menu;

import com.company.UserSystem;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public class UserMenu extends Menu{
    public UserMenu(Scanner scan, UserSystem userSystem){
        super(null, scan, userSystem);
        options.add("Wylogowanie");
        options.add("Usuwanie konta");
        options.add("Zmiana hasła");
        Collections.reverse(options);
    }

    public void logOut(){
        String username = userSystem.getCurrentUser().getUsername();
        userSystem.logOut();
        System.out.println("Wylogowano z konta: " + username);
    }

    public void changePassword() throws IOException {
        System.out.println("-------------");
        System.out.println("Okno zmiany hasła");
        System.out.println("-------------");
        System.out.println("Wpisz nowe hasło: ");
        String newPassword = scan.nextLine();
        System.out.println(userSystem.changePassword(newPassword));
    }

    public void removeAccount() throws IOException {
        System.out.println("-------------");
        System.out.println("Okno usuwania konta");
        System.out.println("-------------");
        System.out.println("Na pewno usunąć konto?");
        System.out.println("1. Nie");
        System.out.println("2. Tak");
        String confirmed = scan.nextLine();

        if(confirmed.equals("2"))
            System.out.println(userSystem.removeAccount());
    }

    @Override
    public void action(int index) {
        try {
            String option = options.get(--index);

            switch (option) {
                case "Wylogowanie":
                    logOut();
                    break;
                case "Zmiana hasła":
                    changePassword();
                    break;
                case "Usuwanie konta":
                    removeAccount();
                    break;
                case "Wyjście":
                    quit();
                    break;
            }
        } catch (IndexOutOfBoundsException | IOException e){
            System.out.println("Nie ma takiej opcji");
        }
    }
}
