/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package huffprojc;

import huffprojc.Controlador.MainController;
import huffprojc.Vista.MainView;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *
 * @author Kailo
 */
public class HuffProjc extends Application {
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