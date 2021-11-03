package com.GitHub;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class GitHub {

    final String LOGIN = "CuentaTestOB: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";
    ArrayList<String> repositories = new ArrayList<>();
    ArrayList<String> commits = new ArrayList<>();

    public void getRepositories(String user) throws Exception{

        try {

            boolean isJson = false;

            //Recibe el fichero json de la API Git Hub
            Runtime run = Runtime.getRuntime();
            Process process = run.exec("curl -i -u " + LOGIN
                    + " https://api.github.com/users/" + user + "/repos");
            process.waitFor();

            //Escribe el resultado en el fichero fileRepos.json
            BufferedReader buffer = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            FileWriter fileRepos = new FileWriter("fileRepos.json");

            while ((line = buffer.readLine()) != null){
                //Empiezo de aqui pues el resto del texto es el header http
                if (line.equalsIgnoreCase("[")){
                    isJson = true;
                }

                if (isJson) {
                    fileRepos.write(line + '\n');
                }
            }
            fileRepos.close();

            // Creamos el inputstream desde un fichero
            InputStream is = new FileInputStream("fileRepos.json");

            // Creamos un tokenizador que leera desde nuestro IS
            JSONTokener tokener = new JSONTokener(is);

            // Y se lo pasamos a una instancia de la clase JSONObject
            JSONObject obj = new JSONObject(tokener);

            // Sacamos los datos del usuario:
            JSONObject userGit = obj.getJSONObject("0");

            String repositoryName = userGit.getString("name");
            System.out.println("Usuario " + repositoryName);
        } catch (Exception e){
            System.out.println("Error in getRepositories(): " + e.getMessage());
        }
    }
}
