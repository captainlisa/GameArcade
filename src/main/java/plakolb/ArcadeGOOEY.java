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

    @Override
    public void start(Stage stage) throws Exception {

        //stage styling
        stage.setTitle("Game Arcade");
        stage.setWidth(900);
        stage.setHeight(675);

        //root node/parent node + styling
        VBox rootBox = new VBox();
        rootBox.getStylesheets().add("file:///" + "C:/Users/codersbay/IdeaProjects/GameArcade/src/main/java/plakolb/stylesheets/style.css");
        rootBox.getStyleClass().add("root-box");
        rootBox.setPrefSize(900, 675);
//        rootBox.setBackground(new Background(new BackgroundImage(
//                new Image("C:\\Users\\codersbay\\IdeaProjects\\samples\\HelloFX\\Gradle\\ArcadeGame\\src\\img\\arcade.jpg"),
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.CENTER,
//                BackgroundSize.DEFAULT)));

        //scene + styling
        Scene menu = new Scene(rootBox);
        stage.setScene(menu);

        //nodes for title + styling
        VBox titleBox = new VBox();
        titleBox.getStyleClass().add("title-box");

        Text title = new Text("GAME ARCADE");
        title.setId("headline");
        title.setFill(Paint.valueOf("#F29D52"));
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

        buttonBox.getChildren().addAll(loginButton, gameButton);

        //set scene and ACTION!
        rootBox.getChildren().addAll(titleBox, buttonBox);
        stage.centerOnScreen();
        stage.show();

    }

}
