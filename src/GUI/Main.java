/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.application.Application;

import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Christian
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent pf = (Parent) loader.load();
        MainController controller = (MainController) loader.getController();

        double width = stage.getWidth();
        double height = stage.getHeight();

        Scene fgk = new Scene(pf);
        stage.setScene(fgk);

        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
