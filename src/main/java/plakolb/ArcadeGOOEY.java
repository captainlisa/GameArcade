package plakolb;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ArcadeGOOEY extends Application {

    public static boolean LOGGED_IN = false;
    public static Player NEW_PLAYER;

    @Override
    public void start(Stage stage) throws Exception {

        //stage styling
        stage.setTitle("Game Arcade");
        stage.setWidth(900);
        stage.setHeight(628);

        //root node/parent node + styling
        VBox rootBox = new VBox();
        rootBox.getStylesheets().add("file:///" + "C:/Users/codersbay/IdeaProjects/GameArcade/src/main/java/plakolb/stylesheets/style.css");
        rootBox.getStyleClass().add("root-box");

        //scene + styling
        Scene menu = new Scene(rootBox);
        stage.setScene(menu);

        //nodes for title + styling
        VBox titleBox = new VBox();
        titleBox.getStyleClass().add("title-box");

        Text title = new Text("GAME ARCADE");
        title.setId("headline");
        title.setFill(Paint.valueOf("#F7B253"));
        title.setFont(new Font("Bahnschrift", 50));
        titleBox.getChildren().add(title);

        //node for buttons + styling
        VBox buttonBox = new VBox();
        buttonBox.getStyleClass().add("button-box");

        Button loginButton = new Button("Login");
        loginButton.setFont(new Font("Consolas", 15));
        loginButton.setOnAction(event -> {
            LoginGOOEY loginGOOEY = new LoginGOOEY();
            try {
                loginGOOEY.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Button gameButton = new Button("Start Game"); //later: Enter the Arcade
        gameButton.setFont(new Font("Consolas", 15));
        gameButton.setOnAction(event -> {
            Snake snake = new Snake();
            try {
                snake.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        if (ArcadeGOOEY.LOGGED_IN) {
            Button toProfilePage = new Button("Go to your Profile");
            toProfilePage.setOnAction(event -> {
                ProfileGOOEY profileGOOEY = new ProfileGOOEY();
                profileGOOEY.showUserProfile(stage, ArcadeGOOEY.NEW_PLAYER);
            });
            buttonBox.getChildren().addAll(loginButton, gameButton, toProfilePage);
        } else {
            buttonBox.getChildren().addAll(loginButton, gameButton);
        }


        //set scene and ACTION!
        rootBox.getChildren().addAll(titleBox, buttonBox);
        stage.show();

    }

}
