package com.mycompany.parqueoseguroadminapp.controller;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.mycompany.parqueoseguroadminapp.App;
import com.mycompany.parqueoseguroadminapp.firebase.FirebaseConfig;

import java.util.HashMap;
import java.util.Map;



public class RegistroAdminController {
    
    @FXML
    private TextField nombreField;
    
    @FXML
    private TextField correoField;
    
    @FXML
    private PasswordField contrasenaField;
    
    private Firestore db;
    
    public RegistroAdminController() {
        db = FirebaseConfig.getFirestore();
    }
    
    @FXML
    private void registrarAdmin() {
        String nombre = nombreField.getText();
        String correo = correoField.getText();
        String contrasena = contrasenaField.getText();
        
        if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            showAlert("Error","Todos los campos son obligatorios.");
            return;
        }
        
        //CONEXION FIREBASE
        try {
            CreateRequest request = new CreateRequest()
                    .setEmail(correo)
                    .setPassword(contrasena)
                    .setDisplayName(nombre);
            
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            System.out.println("Usuario creado con UID" + userRecord.getUid());
            
            
            String uid = userRecord.getUid();
            
            Map<String, Object> nuevoAdmin = new HashMap<>();
            nuevoAdmin.put("uid", uid);
            nuevoAdmin.put("correo", correo);
            nuevoAdmin.put("contrasena", contrasena);
            
            ApiFuture<DocumentReference> resultado = db.collection("admins").add(nuevoAdmin);       
            showAlert("Exito", "Administrador registrado correctamente.");
            cleanFields();         
        } catch (FirebaseAuthException e) {
            showAlert("Error", "No se pudo registrar el administrador.");
        }
    }
    
    public static boolean iniciarSesion (String email, String contrasena) {
        System.out.println("Firebase Admin SDK no permite iniciar sesion");
        return false;
    
    }
    
    @FXML
    private void toLogin() {
        App.showScreen("InicioAdmin.fxml");
    
    }
    
    private void showAlert(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    private void cleanFields() {
        nombreField.clear();
        correoField.clear();
        contrasenaField.clear();
    
    }

}