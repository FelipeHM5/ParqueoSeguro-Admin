package com.mycompany.parqueoseguroadminapp.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.mycompany.parqueoseguroadminapp.App;
import com.mycompany.parqueoseguroadminapp.firebase.FirebaseConfig;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.util.concurrent.ExecutionException;

public class InicioController {
    
    @FXML
    private TextField correoField;
    
    @FXML
    private PasswordField contrasenaField;
    
    @FXML
    private Button btnToRegister;
    
    private Firestore db;
    
    public InicioController() {
        db = FirebaseConfig.getFirestore();
    }
    
    @FXML
    private void initialize() {
    
    }
    
    @FXML
    private void toRegister() {
        App.showScreen("RegistroAdmin.fxml");
    }
    
    @FXML
    private void iniciarSesion() {
        String identificador = correoField.getText().trim();
        String contrasena = contrasenaField.getText().trim();
        
        if (identificador.isEmpty() || contrasena.isEmpty()) {
            showAlert("Campos vacios", "Llena todos los campos");
            return;
               
        } 
        
        try {
            Query query = db.collection("admins")
                    .whereEqualTo("correo", identificador)
                    .limit(1);
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            QuerySnapshot docs = querySnapshot.get();
            
            if (docs.isEmpty()) {
                //Buscar por UID
                query = db.collection("admins")
                        .whereEqualTo("uid", identificador)
                        .limit(1);
                querySnapshot = query.get();
                docs = querySnapshot.get();
            }
            
            if (docs.isEmpty()) {
                showAlert("Usuario no encontrado", "No se hallo administrador");
                return;
            }
            
            DocumentSnapshot doc = docs.getDocuments().get(0);
            String contrasenaGuardada = doc.getString("contrasena");
            
            if (contrasena.equals(contrasenaGuardada)) {
                System.out.println("Inicio de sesion exitoso.");
                App.showScreen("AdminDashboard.fxml");           
            } else {
                showAlert("Contraseña incorrecta", "La contraseña no coincide");
            }
            
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            showAlert("Error", "Ocurrio un error al iniciar sesion.");
        
        }
            
    }
    
    private void showAlert(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
        
    }
    
}