package plakolb;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static plakolb.ArcadeGOOEY.NEW_PLAYER;

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

        player = NEW_PLAYER;
        //stage styling
        stage.setTitle("Game Arcade");
        stage.setWidth(900);
        stage.setHeight(628);

        //root node plus styling
        VBox rootBox = new VBox();
        rootBox.getStyleClass().add("root-box");
        rootBox.getStylesheets().add("file:///" + "C:/Users/codersbay/IdeaProjects/GameArcade/src/main/java/plakolb/stylesheets/style.css");

        Text profileTitle = new Text("Greetings, " + player.getUsername());
        profileTitle.setFill(Paint.valueOf("#F7B253"));
        profileTitle.setFont(new Font("Bahnschrift", 25));

        //SCOREBOARD
//        VBox scoreBox = new VBox();
//        scoreBox.getStyleClass().add("score-box");

        //button node + elements

        HBox buttonBox = new HBox();
        buttonBox.setPadding(new Insets(20, 0, 0, 0));

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

        Button logoutButton = new Button("Log out");
        logoutButton.setFont(new Font("Consolas", 12));
        logoutButton.setOnAction(event -> logOutUser(stage));

        buttonBox.getChildren().addAll(backToMenu, logoutButton);

        //set scene and ACTION!
        rootBox.getChildren().addAll(profileTitle, buttonBox);
        Scene profile = new Scene(rootBox);
        stage.setScene(profile);
        stage.centerOnScreen();
        stage.show();

    }

    private void logOutUser(Stage stage) {
        ArcadeGOOEY.LOGGED_IN = false;
        NEW_PLAYER = null;
        LoginGOOEY loginGOOEY = new LoginGOOEY();
        try {
            loginGOOEY.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
