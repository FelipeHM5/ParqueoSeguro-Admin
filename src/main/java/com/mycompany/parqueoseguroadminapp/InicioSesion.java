package com.mycompany.parqueoseguroadminapp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.MediaType;
import okhttp3.Response;

import org.json.JSONObject;


public class InicioSesion {
    
    private static final String API_KEY = "AIzaSyCOBFSxbw5j-t0E-AMNf8nzyfAOg4T_NWk";
    private static final String LOGIN_URL = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY;
    
    public static boolean iniciarSesion(String correo, String contrasena) {
        try{
            OkHttpClient client = new OkHttpClient();
            
            JSONObject json = new JSONObject();
            json.put("email", correo);
            json.put("password", contrasena);
            json.put("returnSecureToken", true);
            
            RequestBody body = RequestBody.create(
                    json.toString(),
                    MediaType.get("application/json")  
            );
            
            Request request = new Request.Builder()
                    .url(LOGIN_URL)
                    .post(body)
                    .build();
            
            Response response = client.newCall(request).execute();
            
            if(response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONObject responseJson = new JSONObject(responseBody);
                System.out.println("Bienvenido: " + responseJson.getString("email"));
                return true;           
            } else {
                System.out.println("Inicio de seion fallido: " + response.body().string());
                return false;
            }      
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        
        }
    
    }
    
}
