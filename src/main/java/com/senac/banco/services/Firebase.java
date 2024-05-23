package com.senac.banco.services;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase {	
	private static FirebaseDatabase db_instance = null;
	
	protected Firebase() throws IOException {
	    // make sure Firebase Class is initialized only once
		if (db_instance != null) {
			return;
		}

		FileInputStream serviceAccount = new FileInputStream("config/firebase-adminsdk.json");
		
		FirebaseOptions options = FirebaseOptions.builder()
			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
			.setDatabaseUrl("https://shared-database-senac-default-rtdb.firebaseio.com")
			.build();

		
		FirebaseApp app = null;
	    try {
	        app = FirebaseApp.initializeApp(options);
	    } catch (Exception e) {
	        System.err.println("Falha ao inicializar FirebaseApp: " + e.getMessage());
	        return;
	    }

	    if (app == null) {
	        System.err.println("Falha ao inicializar FirebaseApp: instância é nula.");
	        return;
	    }
	    
	    String projectId = options.getProjectId();
        if (projectId == null) {
            String databaseUrl = options.getDatabaseUrl();
            projectId = databaseUrl.split("//")[1].split("\\.")[0];
            System.out.println(projectId);
        }

        if (projectId != null) {
            System.out.println("Nome do projeto Firebase: " + projectId);
        } else {
            System.err.println("Não foi possível obter o nome do projeto Firebase.");
        }
		
		db_instance = FirebaseDatabase.getInstance();
	}
	
	public static FirebaseDatabase getDatabaseInstance() {
		if (db_instance == null) {
		    try {
		        new Firebase();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	    
	    return db_instance;
	}
	
	public static void TerminateFirebaseApp() {
	    if (db_instance == null) {
	        return;
	    }
	    
	    FirebaseApp app = db_instance.getApp();
	    
		db_instance = null;
		
		app.delete();
	}
}
