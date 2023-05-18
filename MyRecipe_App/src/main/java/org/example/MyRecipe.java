package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class MyRecipe {
    public static void main(String[] args) {

        //building connection to database
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipe_app", "root", "");

            Statement statement = connection.createStatement();

            //search query for DB ---> need to be variable
            ResultSet resultSet = statement.executeQuery("select * from user");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("firstName"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
/*
        // search queries for the API
        //Search by Ingredients
        String ingredients = "ingredient1,ingredient2"; // Specify the ingredients you want to search for
        String apiUrl = "https://api.spoonacular.com/recipes/complexSearch";
        String apiKey = "YOUR_API_KEY";

        String requestUrl = apiUrl + "?apiKey=" + apiKey + "&ingredients=" + ingredients;

        HttpGet httpGet = new HttpGet(requestUrl);
        // Execute the HTTP request and process the response
        // ...

        // getting Recipe Info
        int recipeId = 12345; // Replace with the actual recipe ID you want to retrieve
        String apiUrl = "https://api.spoonacular.com/recipes/" + recipeId + "/information";
        String apiKey = "YOUR_API_KEY";

        String requestUrl = apiUrl + "?apiKey=" + apiKey;

        HttpGet httpGet = new HttpGet(requestUrl);
        // Execute the HTTP request and process the response
        // ...

        // Search Similiar Recipes
        int recipeId = 12345; // Replace with the actual recipe ID you want to find similar recipes for
        String apiUrl = "https://api.spoonacular.com/recipes/" + recipeId + "/similar";
        String apiKey = "YOUR_API_KEY";

        String requestUrl = apiUrl + "?apiKey=" + apiKey;

        HttpGet httpGet = new HttpGet(requestUrl);
        // Execute the HTTP request and process the response
        // ...

        // Random Search
        int number = 5; // Specify the number of random recipes you want to retrieve
        String apiUrl = "https://api.spoonacular.com/recipes/random";
        String apiKey = "YOUR_API_KEY";

        String requestUrl = apiUrl + "?apiKey=" + apiKey + "&number=" + number;

        HttpGet httpGet = new HttpGet(requestUrl);
        // Execute the HTTP request and process the response
        // ...

 */
        HttpClient httpClient = HttpClientBuilder.create().build();

        String apiKey = "aa18e54ba6804a7cb1180fe1df954d7d";
        String apiUrl = "https://api.spoonacular.com";
        String endpoint = "/recipes/complexSearch"; // Replace with the actual API endpoint you want to call
        String requestUrl = apiUrl + endpoint + "?apiKey=" + apiKey; // search term

        HttpGet httpGet = new HttpGet(requestUrl);

        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity);

            // Process the response string as needed
            System.out.println(responseString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpGet.releaseConnection();
        }
    }
}