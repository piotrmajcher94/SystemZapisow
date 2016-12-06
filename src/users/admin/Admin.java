package users.admin;
import sample.ConfirmationBox;
import javafx.geometry.Pos;
import users.GeneralUser;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Klasa administrator w systemie. Posiada metody do wyświetlnia
 * interfejsu użytkownika oraz do edycji danych w systemie.
 * Created by piotrek on 06.12.16.
 */
public class Admin extends GeneralUser {


    public Admin(Stage _parentWindow, Scene _previousScene){
        /*Przypisanie okna i sceny rodzica*/
        this.parentWindow = _parentWindow;
        this.parentScene = _previousScene;
    }

    @Override
    public void displayMainMenu(){
        /*Inicjalizacja szkieletu GUI*/
        initInterfaceFrame("Panel administracyjny");

        VBox mainMenuLayout = new VBox();
        mainMenuLayout.setPadding(new Insets(50,50,50,50));
        mainMenuLayout.setSpacing(40);
        mainMenuLayout.setAlignment(Pos.CENTER);

        mainMenuLayout.getChildren().addAll(buttonProfile, buttonCourses, buttonLogout);
        Scene mainMenuScene = new Scene(mainMenuLayout);
        this.parentWindow.setScene(mainMenuScene);
    }

    @Override
    public void showProfile(){
        System.out.println("show profile");
    }

    @Override
    public void manageCourses() {
        System.out.println("manage courses");
    }

    @Override
    public void manageGroups() {
        System.out.println("manage courses");
    }


}














