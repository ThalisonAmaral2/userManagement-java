package database;

import User.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String filePath = "database_users.db";
    private List<User> userList;
    public Database() {
        userList = setUserList(); // Initialize the userList
    }
    public List<User> setUserList(){
        List<User> userList_base = new ArrayList<>();
        File file = new File(filePath);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Error creating the file: " + e.getMessage());
            }
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while((line = reader.readLine()) != null){
                String[] userArray = line.split(",");
                String firstName = userArray[0];
                String lastName = userArray[1];
                int age = Integer.parseInt(userArray[2].trim());
                String email = userArray[3];
                userList_base.add(new User(firstName, lastName, age, email));

            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userList_base;
    }
    public void addUser(User user){
        userList.add(user);
    }
    public void removeUser(String searchName){
        User user = getUserByName(searchName);
        userList.remove(user);
        save();
    }
    public void registerUser(String firstName, String lastName, int age, String email){
        userList.add(new User(firstName, lastName, age, email));
        save();
    }
    public User getUserByName(String searchName){
        for(User user : userList){
            if(user.getFirstName().toLowerCase().contains(searchName.toLowerCase())){
                return user;
            }else if(user.getLastName().toLowerCase().contains(searchName.toLowerCase())){
                return user;
            }
        }
        return null;
    }
    public void displayUser(String searchName){
        User user = getUserByName(searchName);
        if(user != null){
            System.out.println("Name: "+user.getFirstName()+" "+user.getLastName());
            System.out.println("Age: "+user.getAge());
            System.out.println("Email: "+user.getEmail()+"\n");
        }else{
            System.out.println("Couldn't find a user.");
        }

    }
    public void displayList(){
        for (User user : userList) {
            System.out.println("Name: "+user.getFirstName()+" "+user.getLastName());
            System.out.println("Age: "+user.getAge());
            System.out.println("Email: "+user.getEmail()+"\n");
        }
    }
    public List<User> getUserList() {
        return userList;
    }
    public void save(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for(User user : userList){
                writer.write(user.getFirstName()+","+user.getLastName()+","+user.getAge()+","+user.getEmail()+"\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}