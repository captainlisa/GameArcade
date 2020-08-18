package plakolb;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginGOOEY extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Game Arcade");
        stage.setWidth(900);
        stage.setHeight(628);

        //creating base node:
        VBox rootBox = new VBox();
        rootBox.getStylesheets().add("file:///" + "C:/Users/codersbay/IdeaProjects/GameArcade/src/main/java/plakolb/stylesheets/style.css");
        rootBox.getStyleClass().add("root-box");

        //node for title + styling
        VBox titleBox = new VBox();
        titleBox.getStyleClass().add("title-box");

        Text loginTitle = new Text("Welcome Stranger!");
        loginTitle.setFont(new Font("Bahnschrift", 50));
        loginTitle.setFill(Paint.valueOf("#F7B253"));
        titleBox.getChildren().addAll(loginTitle);

        //node for login elements
        GridPane gridPain = new GridPane();
        gridPain.getStyleClass().add("grid-pane");
        gridPain.setPadding(new Insets(40, 0, 0, 0));

        //login elements + styling
        Text infoText = new Text("Enter your login information.");
        infoText.setFont(new Font("Consolas", 12));
        infoText.setFill(Paint.valueOf("white"));

        Label usernameLabel = new Label("Username:");
        usernameLabel.setTextFill(Paint.valueOf("white"));
        usernameLabel.setFont(new Font("Consolas", 12));

        Label passwordLabel = new Label("Password:");
        passwordLabel.setTextFill(Paint.valueOf("white"));
        passwordLabel.setFont(new Font("Consolas", 12));

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        Button submitButton = new Button("Login!");
        submitButton.setFont(new Font("Consolas", 12));
        submitButton.setOnAction(event -> {
            try {
                loginUser(stage, gridPain, usernameField, passwordField);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        //adding elements to gridPain
        gridPain.add(infoText, 0, 0, 2, 1);
        gridPain.add(usernameLabel, 0, 1);
        gridPain.add(usernameField, 1, 1);
        gridPain.add(passwordLabel, 0, 2);
        gridPain.add(passwordField, 1, 2);
        gridPain.add(submitButton, 1, 3);


        //node for navigation buttons + styling
        GridPane buttonGrid = new GridPane();
        buttonGrid.getStyleClass().add("grid-pane");
        buttonGrid.setPadding(new Insets(10, 0, 0, 0));

        //button elements + styling
        Text registrationHeadline = new Text("No profile yet?");
        registrationHeadline.setFont(new Font("Bahnschrift", 25));
        registrationHeadline.setFill(Paint.valueOf("#F7B253"));

        Text registrationText = new Text("Join the dark side:");
        registrationText.setFont(new Font("Consolas", 14));
        registrationText.setFill(Paint.valueOf("white"));

        Button toRegistration = new Button("To Registration");
        toRegistration.setFont(new Font("Consolas", 12));
        toRegistration.setOnAction(event -> showRegistrationScreen(stage));

        Text backToMenuText = new Text("Run as fast as you can:");
        backToMenuText.setFont(new Font("Consolas", 14));
        backToMenuText.setFill(Paint.valueOf("white"));

        Button goBack = new Button("Back to Menu");
        goBack.setFont(new Font("Consolas", 12));
        goBack.setOnAction(event -> showMainMenu(stage));

        //adding elements to buttonGrid
        buttonGrid.add(registrationHeadline, 0, 0, 3, 1);
        buttonGrid.add(registrationText, 1, 2);
        buttonGrid.add(toRegistration, 1, 3);
        buttonGrid.add(backToMenuText, 1, 4);
        buttonGrid.add(goBack, 1, 5);


        //set scene and ACTION!
        rootBox.getChildren().addAll(titleBox, gridPain, buttonGrid);
        Scene login = new Scene(rootBox);
        stage.setScene(login);
        stage.show();

    }


    private void showRegistrationScreen(Stage stage) {

        //creating base node
        VBox rootBox = new VBox();
        rootBox.getStylesheets().add("file:///" + "C:/Users/codersbay/IdeaProjects/GameArcade/src/main/java/plakolb/stylesheets/style.css");
        rootBox.getStyleClass().add("root-box");

        //node for title
        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getStyleClass().add("title-box");

        Text registrationTitle = new Text("CREATE A NEW ACCOUNT:");
        registrationTitle.setFill(Paint.valueOf("#F7B253"));
        registrationTitle.setFont(new Font("Bahnschrift", 30));

        titleBox.getChildren().addAll(registrationTitle);

        //node for registration elements
        GridPane registrationPane = new GridPane();
        registrationPane.getStyleClass().add("grid-pane");

        //registration elements + styling
        Text registrationText = new Text("Enter your registration information.");
        registrationText.setFont(new Font("Consolas", 12));
        registrationText.setFill(Paint.valueOf("white"));

        Label usernameLabel = new Label("Enter a Username:");
        usernameLabel.setTextFill(Paint.valueOf("white"));
        usernameLabel.setFont(new Font("Consolas", 12));

        Label passwordLabel = new Label("Enter a Password:");
        passwordLabel.setTextFill(Paint.valueOf("white"));
        passwordLabel.setFont(new Font("Consolas", 12));

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        Button submitButton = new Button("Join!");
        submitButton.setFont(new Font("Consolas", 12));
        submitButton.setOnAction(event -> {
            try {
                registerPlayer(stage, registrationPane, usernameField, passwordField);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


        Button backButton = new Button("Back to Menu");
        backButton.setFont(new Font("Consolas", 12));
        backButton.setOnAction(event -> showMainMenu(stage));

        //adding elements to registration pane
        registrationPane.add(registrationText,0, 0, 3, 1);
        registrationPane.add(usernameLabel, 1, 1);
        registrationPane.add(usernameField, 1, 2);
        registrationPane.add(passwordLabel, 1, 3);
        registrationPane.add(passwordField, 1, 4);
        registrationPane.add(submitButton, 1, 6);
        registrationPane.add(backButton, 2, 6);

        //set scene and ACTION!
        rootBox.getChildren().addAll(titleBox, registrationPane);
        Scene registrationScene = new Scene(rootBox);
        stage.setScene(registrationScene);
        stage.show();

    }

    private void showMainMenu(Stage stage) {
        try {
            ArcadeGOOEY arcadeGOOEY = new ArcadeGOOEY();
            arcadeGOOEY.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerPlayer(Stage stage, GridPane registrationPane, TextField usernameField, PasswordField passwordField) throws SQLException {
        if (usernameField.getText() == null || passwordField.getText() == null  || usernameField.getText().equals("") || passwordField.getText().equals("")) {
            Text registrationError = new Text("Huh, I cannot understand what you wrote. \nPlease enter valid data.");
            registrationError.setFont(new Font("Consolas", 12));
            registrationError.setFill(Paint.valueOf("white"));
            registrationPane.add(registrationError, 0, 5, 3, 1);
        } else {
            String inputUsername = usernameField.getText();
            String inputPassword = passwordField.getText();

            PlayerDAO playerDAO = new PlayerDAO();
            ArcadeGOOEY.NEW_PLAYER = new Player(inputUsername, inputPassword);
            ArcadeGOOEY.LOGGED_IN = playerDAO.registerNewPlayer(ArcadeGOOEY.NEW_PLAYER);
            if (ArcadeGOOEY.LOGGED_IN) {
                showProfilePage(stage, ArcadeGOOEY.NEW_PLAYER);
            } else {
                Text registrationError = new Text("Username already exists.");
                registrationError.setFont(new Font("Consolas", 12));
                registrationError.setFill(Paint.valueOf("white"));
                registrationPane.add(registrationError, 0, 5, 3, 1);
            }
        }
    }

    private void loginUser(Stage stage, GridPane gridPain, TextField usernameField, PasswordField passwordField) throws SQLException {
        if (usernameField.getText() == null || passwordField.getText() == null || usernameField.getText().equals("") || passwordField.getText().equals("")) {
            Text loginError = new Text("Huh, I cannot understand what you wrote. \nPlease enter valid data.");
            loginError.setFont(new Font("Consolas", 12));
            loginError.setFill(Paint.valueOf("white"));
            gridPain.add(loginError, 0, 4, 2, 1);
        } else {
            String inputUsername = usernameField.getText();
            String inputPassword = passwordField.getText();

            PlayerDAO playerDAO = new PlayerDAO();
            ArcadeGOOEY.NEW_PLAYER = new Player(inputUsername, inputPassword);
            ArcadeGOOEY.LOGGED_IN = playerDAO.loginPlayer(inputUsername, inputPassword);
            if (ArcadeGOOEY.LOGGED_IN) {
                showProfilePage(stage, ArcadeGOOEY.NEW_PLAYER);
            } else {
                Text loginError = new Text("Username or Password are incorrect.");
                loginError.setFont(new Font("Consolas", 12));
                loginError.setFill(Paint.valueOf("white"));
                gridPain.add(loginError, 0, 4, 2, 1);
            }
        }
    }

    private void showProfilePage(Stage stage, Player player) {
        ProfileGOOEY profileGOOEY = new ProfileGOOEY();
        try {
            profileGOOEY.showUserProfile(stage, player);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
