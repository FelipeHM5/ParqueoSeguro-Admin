package com.mycompany.parqueoseguroadminapp.controller;

import com.mycompany.parqueoseguroadminapp.App;
import javafx.fxml.FXML;


public class AdminDashboardController {
    
    @FXML
    private void handleGestionParqueaderos() {
        System.out.println("Ir a gestion de parqueaderos....");
    }
    
    @FXML
    private void handleVerUsuarios() {
        System.out.println("Ir a lista de usuarios");
    
    }
    
    @FXML
    private void handleVerReportes() {
        System.out.println("Ir a reportes");
    
    }
    
    @FXML 
    private void handleCerrarSesion() {
        App.showScreen("InicioAdmin.fxml");
        
    
    }
    
}
