package com.mycompany.parqueoseguroadminapp;

import com.mycompany.parqueoseguroadminapp.firebase.FirebaseConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

//import java.io.IOException;



public class App extends Application {
    
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        FirebaseConfig.initializeFirebase();
        
        showScreen("InicioAdmin.fxml");
        
    }
    
    public static void showScreen(String fxml) {
        
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Panel administracion - ParqueoSeguro");
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}