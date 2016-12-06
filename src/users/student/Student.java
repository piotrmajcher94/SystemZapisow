package users.student;
import users.User;
import sample.Main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by piotrek on 06.12.16.
 */
class Student extends User {

    /*Konstruktor klasy Student - tworzy obiekt użytkownika
     *na podstawie danych z pliku. Znajduje odpowiednie dane
     *na podstawie loginu, pozyskanego przy logowaniu*/
    Student(String _login){
        this.login = _login;
        String studentData = "";
        String fileName = Main.studentDataFilename;
        File file = new File(fileName);
        Scanner input;
        boolean found = false;

        try {
            input = new Scanner(file);
        }catch(IOException ex){
            Main.closeOnError("FATAL ERROR - database disappeared");
            return;
        }

        while(input.hasNextLine()){
            String temp = input.findInLine(login);
            if(temp.equals(login)){
                found = true;
                studentData = input.nextLine();
                break;
            }
        }
        if(found)
            input = new Scanner(studentData);
        else{
            Main.closeOnError("FATAL ERROR - Student not found after login");
            return;
        }

        input.useDelimiter(" ");

        password = input.next();
        name = input.next();
        surname = input.next();
        email = input.next();
    }

    @Override
    public String toString(){
        String result = "";
        result += login + " ";
        result += password + " ";
        result += name + " ";
        result += surname + " ";
        result += email + " ";

        return result;
    }
}
