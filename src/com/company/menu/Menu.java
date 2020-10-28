package com.company.menu;

import com.company.UserSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Menu implements Action{
    protected List<String> options = new ArrayList<>();
    protected String name;
    protected Scanner scan;
    protected UserSystem userSystem;

    public Menu(String name, Scanner scan, UserSystem userSystem) {
        this.name = name;
        this.scan = scan;
        this.userSystem = userSystem;
        options.add("Wyjście");
    }

    public void display(){
        int index = 1;
        System.out.println("-------------");
        if(name == null && userSystem.getCurrentUser() != null){
            System.out.println("Witaj " + userSystem.getCurrentUser().getUsername());
        } else
            System.out.println(name);
        System.out.println("-------------");
        for(String option : options) {
            System.out.println(index + ". " + option);
            index++;
        }
        System.out.println("-------------");

    }

    public void quit(){
        scan.close();
        System.exit(1);
    }
}
