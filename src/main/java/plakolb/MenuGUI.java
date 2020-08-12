package plakolb;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.stream.Collectors;

public class MenuGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        //stage styling
        stage.setTitle("Game Arcade");
        stage.setWidth(600);
        stage.setHeight(700);

        //root node/parent node + styling
        VBox rootBox = new VBox();
        rootBox.getStylesheets().add("file:///" + "C:/Users/codersbay/IdeaProjects/samples/HelloFX/Gradle/ArcadeGame/src/main/java/plakolb/stylesheets/style.css");
        rootBox.getStyleClass().add("rootBox");
        rootBox.setAlignment(Pos.CENTER);
        rootBox.setSpacing(40);
//        rootBox.setBackground(new Background(new BackgroundImage(
//                new Image("C:\\Users\\codersbay\\IdeaProjects\\samples\\HelloFX\\Gradle\\ArcadeGame\\src\\img\\arcade.jpg"),
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.CENTER,
//                BackgroundSize.DEFAULT)));

        //scene + styling
        Scene menu = new Scene(rootBox);
        stage.setScene(menu);

        //title + styling
        Label title = new Label("GAME ARCADE"); //child node
        title.setId("headline");
        title.setFont(new Font("Bahnschrift", 30));

        //buttons + styling
        Button loginButton = new Button("Login/Registration");

        Button gameButton = new Button("Enter the Arcade");

        //setting the stage up and displaying it
        rootBox.getChildren().addAll(title, loginButton, gameButton);
        stage.show();

    }
}
