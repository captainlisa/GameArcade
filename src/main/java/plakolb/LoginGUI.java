package plakolb;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Game Arcade");
        stage.setWidth(600);
        stage.setHeight(700);

        Text infoText = new Text("Enter your login information.");

        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        Button submitButton = new Button("Login!");

        GridPane gridPain = new GridPane();
        gridPain.add(infoText, 0, 0);
        gridPain.add(usernameLabel, 0, 1);
        gridPain.add(usernameField, 0, 2);
        gridPain.add(passwordLabel, 0, 3);
        gridPain.add(passwordField, 0, 4);
        gridPain.add(submitButton, 0, 5);
        //style gridPain
        gridPain.setVgap(10);
        gridPain.setHgap(10);
        gridPain.setPadding(new Insets(100));

        Scene login = new Scene(gridPain);
        stage.setScene(login);
        stage.show();

    }
}
