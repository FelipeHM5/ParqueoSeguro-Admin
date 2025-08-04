
package com.mycompany.parqueoseguroadminapp.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.io.InputStream;
import java.io.IOException;

public class FirebaseConfig {
    
    public static void initializeFirebase() {
        
        if (!FirebaseApp.getApps().isEmpty()) {
            System.out.println("Firebase ya ha sido inicializado.");
            return;
        }
        
        try {
            InputStream serviceAccount = FirebaseConfig.class.getClassLoader().getResourceAsStream("firebase-config.json");
            
            if (serviceAccount == null) {
                throw new IOException("No se pudo encontrar la llave");
            
            }
            
            
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            
            FirebaseApp.initializeApp(options);
            System.out.println("Firebase se ha inicializado correctamente. ");

            
        } catch (IOException e) {
            System.out.println("Error al inicializar Firebase: " + e.getMessage());
            e.printStackTrace();
        
        }
        
    }
    public static Firestore getFirestore() {
        return FirestoreClient.getFirestore();

    }
}


