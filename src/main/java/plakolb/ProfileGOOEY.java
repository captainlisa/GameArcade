package plakolb;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProfileGOOEY extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        //stage styling
        stage.setTitle("Game Arcade");
        stage.setWidth(600);
        stage.setHeight(700);

        //root node and styling
        VBox rootBox = new VBox();
        rootBox.setAlignment(Pos.CENTER);

        //set scene and ACTION!
        Scene profile = new Scene(rootBox);
        stage.setScene(profile);
        stage.show();

    }

    public void showUserProfile(Stage stage, Player player) {

        //stage styling
        stage.setTitle("Game Arcade");
        stage.setWidth(900);
        stage.setHeight(675);

        //root node plus styling
        VBox rootBox = new VBox();
        rootBox.setAlignment(Pos.CENTER);
        rootBox.setSpacing(10);
        rootBox.setPrefSize(900, 675);
        rootBox.getStylesheets().add("file:///" + "C:/Users/codersbay/IdeaProjects/GameArcade/src/main/java/plakolb/stylesheets/style.css");

        //SCOREBOARD

        //profile page elements
        Text profileTitle = new Text("Greetings, " + player.getUsername());
        profileTitle.setFill(Paint.valueOf("#F29D52"));
        profileTitle.setFont(new Font("Bahnschrift", 25));

        Button backToMenu = new Button("Back to Menu");
        backToMenu.setFont(new Font("Consolas", 12));
        backToMenu.setOnAction(event -> {
            try {
                ArcadeGOOEY arcadeGOOEY = new ArcadeGOOEY();
                arcadeGOOEY.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //set scene and ACTION!
        rootBox.getChildren().addAll(profileTitle, backToMenu);
        Scene profile = new Scene(rootBox);
        stage.setScene(profile);
        stage.centerOnScreen();
        stage.show();

    }

}
