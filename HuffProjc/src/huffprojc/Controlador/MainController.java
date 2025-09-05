package huffprojc.Controlador;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kailo
 */
import huffprojc.Modelo.*;
import huffprojc.Vista.MainView;
import javafx.stage.FileChooser;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.util.Duration;

import java.io.File;

public class MainController {

    private MainView view;
    private HuffmanTree tree;
    private File uploadedFile; 

    public MainController(MainView view) {
        this.view = view;
        initHandlers();
    }

    private void initHandlers() {
        view.buildTreeBtn.setOnAction(e -> buildTree());
        view.uploadFileBtn.setOnAction(e -> uploadFile());
        view.compressBtn.setOnAction(e -> compressFile());
        view.decompressBtn.setOnAction(e -> decompressFile());
    }

    private void uploadFile() {
        FileChooser fc = new FileChooser();
        uploadedFile = fc.showOpenDialog(null);   
        if (uploadedFile != null) {
            view.inputArea.setText("Archivo cargado: " + uploadedFile.getName());
        }
    }

    private void compressFile() {
        if (uploadedFile == null) {
            System.out.println("Primero debes subir un archivo.");
            return;
        }

        FileChooser fc = new FileChooser();
        fc.setTitle("Guardar archivo comprimido");
        File saveFile = fc.showSaveDialog(null);

        if (saveFile != null) {
            try {
                HuffmanCompressor.compressFile(uploadedFile, saveFile);
                System.out.println("Archivo comprimido en: " + saveFile.getAbsolutePath());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void decompressFile() {
    FileChooser fc = new FileChooser();
    fc.setTitle("Seleccionar archivo comprimido");
    File file = fc.showOpenDialog(null);
    if (file != null) {
        javafx.stage.DirectoryChooser dirChooser = new javafx.stage.DirectoryChooser();
        dirChooser.setTitle("Selecciona carpeta para guardar archivo descomprimido");
        File dir = dirChooser.showDialog(null);
        if (dir != null) {
            try {
                HuffmanCompressor.decompressFile(file, dir);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

    private void buildTree() {
        String text = view.inputArea.getText();
        if (text.isEmpty()) {
            return;
        }

        tree = new HuffmanTree(text);
        view.treePane.getChildren().clear();
        drawTree(tree.getRoot(), 400, 40, 200);
    }

 private void drawTree(HuffmanNode node, double x, double y, double offset) {
    if (node == null) {
        return;
    }

    
    if (node.left != null) {
        QuadCurve curve = new QuadCurve(
                x, y,
                x - offset / 2, y + 35,
                x - offset, y + 70
        );
        curve.setStroke(Color.rgb(150, 170, 200, 0.7));
        curve.setStrokeWidth(2.2);
        curve.setEffect(new DropShadow(5, Color.rgb(90, 130, 255, 0.3)));
        curve.setFill(Color.TRANSPARENT);

        view.treePane.getChildren().add(curve);
        drawTree(node.left, x - offset, y + 70, offset / 1.6);
    }

    if (node.right != null) {
        QuadCurve curve = new QuadCurve(
                x, y,
                x + offset / 2, y + 35,
                x + offset, y + 70
        );
        curve.setStroke(Color.rgb(150, 170, 200, 0.7));
        curve.setStrokeWidth(2.2);
        curve.setEffect(new DropShadow(5, Color.rgb(90, 130, 255, 0.3)));
        curve.setFill(Color.TRANSPARENT);

        view.treePane.getChildren().add(curve);
        drawTree(node.right, x + offset, y + 70, offset / 1.6);
    }

    
    Circle circle = new Circle(x, y, 24);
    circle.setFill(new RadialGradient(
            0, 0,
            x - 6, y - 6,
            38,
            false, CycleMethod.NO_CYCLE,
            new Stop(0, Color.web("#e0e7ff")),
            new Stop(1, Color.web("#6a82fb"))  // degradado violeta-azulado
    ));
    circle.setStroke(Color.web("#1e1e2f"));
    circle.setStrokeWidth(1.2);

    
    DropShadow shadow = new DropShadow();
    shadow.setColor(Color.rgb(60, 80, 140, 0.4));
    shadow.setRadius(8);
    circle.setEffect(shadow);

    
    circle.setOnMouseEntered(e -> circle.setEffect(new DropShadow(15, Color.web("#9face6"))));
    circle.setOnMouseExited(e -> circle.setEffect(shadow));

    
    String label = node.isLeaf() ? node.ch + "(" + node.freq + ")" : String.valueOf(node.freq);
    Text text = new Text(label);
    text.setFill(Color.web("#f4f4f9"));
    text.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 13));

    
    text.setX(x - text.getLayoutBounds().getWidth() / 2);
    text.setY(y + text.getLayoutBounds().getHeight() / 4);

    
    FadeTransition ft = new FadeTransition(Duration.millis(450), circle);
    ft.setFromValue(0);
    ft.setToValue(1);
    ft.play();

    ScaleTransition st = new ScaleTransition(Duration.millis(450), circle);
    st.setFromX(0.7);
    st.setFromY(0.7);
    st.setToX(1);
    st.setToY(1);
    st.play();

    view.treePane.getChildren().addAll(circle, text);
}
}