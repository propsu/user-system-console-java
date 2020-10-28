package com.company;

import com.company.resources.FileController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserSystem {
    private List<User> users = new ArrayList<User>();
    private User currentUser = null;
    private final String regex = "^[a-zA-Z0-9]*$";
    private FileController fileController;

    public UserSystem() throws IOException {
        fileController = new FileController("users.txt");

        for (String line : fileController.getLines()){
            users.add(new User(line));
        }
    }

    private User findUser(String username){
        for(User user : users){
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public String logIn(String username, String password) {
        User user = findUser(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return "Pomyślnie zalogowano";
        } else if (user == null) {
            return "Nie ma takiego użytkownika";
        } else {
            return "Błędne hasło";
        }

    }

    public void logOut(){
        currentUser = null;
    }

    public String registerUser(String username, String password) throws IOException {
        User user = findUser(username);

        if(user == null){
            String passwordError = validatePassword(password);
            if(passwordError != null)
                return passwordError;

            String usernameError = validateUsername(username);
            if(usernameError != null)
                return usernameError;

            users.add(new User(username, password));
            fileController.addLine(username+":"+password);
            return "Pomyślnie dodano użytkownika " + username;
        }

        return "Użytkownik istnieje";
    }

    public String changePassword(String newPassword) throws IOException {
        String passwordError = validatePassword(newPassword);
        if(passwordError != null)
            return passwordError;

        String oldLine = currentUser.toString();
        currentUser.setPassword(newPassword);
        String newLine = currentUser.toString();

        fileController.replaceLine(oldLine, newLine);
        return "Pomyślnie zmieniono hasło";
    }

    public String removeAccount() throws IOException {
        fileController.deleteLine(currentUser.toString());
        users.remove(findUser(currentUser.getUsername()));
        logOut();
        return "Usunięto konto";
    }

    private String validatePassword(String password){
        if(!password.matches(regex))
            return  "Hasło może zawierać tylko znaki alfanumeryczne";

        if(password.length() < 6)
            return "Hasło musi mieć co najmniej 6 znaków";

        if(password.length() > 16)
            return "Hasło może mieć maksymalnie 16 znaków";

        return null;
    }

    private String validateUsername(String username){
        if(!username.matches(regex))
            return  "Nazwa użytkownika może zawierać tylko znaki alfanumeryczne";

        if(username.length() < 4)
            return "Nazwa użytkownika musi mieć co najmniej 4 znaki";

        if(username.length() > 10)
            return "Nazwa użytkownika może mieć maksymalnie 10 znaków";

        return null;
    }

    public User getCurrentUser() { return currentUser; }
}
