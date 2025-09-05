package huffprojc.Vista;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kailo
 */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainView {
    public TextArea inputArea;
    public Button buildTreeBtn, compressBtn, decompressBtn, uploadFileBtn;
    public Pane treePane;

    public void start(Stage stage) {
        
        inputArea = new TextArea();
        inputArea.setPromptText("✍️ Escribe texto aquí...");
        inputArea.setWrapText(true);
        inputArea.getStyleClass().add("input-area");

        // Botones
        buildTreeBtn = new Button("🌳 Generar Árbol");
        compressBtn = new Button("📦 Comprimir");
        decompressBtn = new Button("📂 Descomprimir");
        uploadFileBtn = new Button("⬆️ Subir archivo");

        buildTreeBtn.getStyleClass().add("main-button");
        compressBtn.getStyleClass().add("main-button");
        decompressBtn.getStyleClass().add("main-button");
        uploadFileBtn.getStyleClass().add("main-button");

        HBox buttons = new HBox(20, buildTreeBtn, compressBtn, decompressBtn, uploadFileBtn);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(10));

        // Área del árbol
        treePane = new Pane();
        treePane.setMinHeight(400);
        treePane.getStyleClass().add("tree-pane");

        // Título elegante
        Label title = new Label("⚡ Compresor Huffman");
        title.getStyleClass().add("title");

        // Layout principal
        VBox root = new VBox(20, title, inputArea, buttons, treePane);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.getStyleClass().add("root");

        Scene scene = new Scene(root, 950, 650);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setTitle("Compresor Huffman");
        stage.setScene(scene);
        stage.show();
    }
}