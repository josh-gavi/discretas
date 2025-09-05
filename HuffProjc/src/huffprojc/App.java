/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huffprojc;

/**
 *
 * @author Kailo
 */
import huffprojc.Vista.MainView;
import huffprojc.Controlador.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        MainView view = new MainView();
        view.start(stage);

        new MainController(view);
    }

    public static void main(String[] args) {
        launch(args);
    }
}