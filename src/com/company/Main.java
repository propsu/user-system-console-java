package com.company;

import com.company.menu.SystemMenu;
import com.company.menu.UserMenu;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        UserSystem userSystem = new UserSystem();

        UserMenu userMenu = new UserMenu(scan, userSystem);
        SystemMenu systemMenu = new SystemMenu(scan, userSystem);

        while (true) {
            boolean logged = userSystem.getCurrentUser() != null;
            if(!logged)
                systemMenu.display();
            else
                userMenu.display();

            try{
                int option = scan.nextInt();
                scan.nextLine();

                if(!logged)
                    systemMenu.action(option);
                else
                    userMenu.action(option);

            } catch (InputMismatchException e){
                System.out.println("Nie ma takiej opcji");
                scan.next();
            }


        }
    }
}