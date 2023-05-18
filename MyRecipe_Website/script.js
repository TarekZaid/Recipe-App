document.getElementById("searchForm").addEventListener("submit", function(event) {
  event.preventDefault(); // Prevent form submission

  // Get the search query values from the form
  var searchType = document.getElementById("searchForm").elements["searchType"].value;
  var query = document.getElementById("searchForm").elements["query"].value;

  // Construct the API request URL based on the selected search type
  var apiUrl = "https://api.spoonacular.com/recipes/";
  var apiKey = "aa18e54ba6804a7cb1180fe1df954d7d";
  var requestUrl = "";

  if (searchType === "ingredient") {
      requestUrl = apiUrl + "findByIngredients?apiKey=" + apiKey + "&ingredients=" + query;
  } else if (searchType === "recipe") {
      requestUrl = apiUrl + "complexSearch?apiKey=" + apiKey + "&query=" + query;
  } else if (searchType === "recipeInfo") {
      requestUrl = apiUrl + query + "/information?apiKey=" + apiKey;
  }

  // Send an HTTP GET request to the API
  fetch(requestUrl)
      .then(function(response) {
          return response.json();
      })
      .then(function(data) {
          // Process the API response and populate the table
          var recipeTable = document.getElementById("recipeTable").getElementsByTagName('tbody')[0];

          // Clear existing table rows
          recipeTable.innerHTML = "";

          // Iterate over the recipe data and add rows to the table
          data.forEach(function(recipe) {
              var row = recipeTable.insertRow();

              var titleCell = row.insertCell();
              titleCell.textContent = recipe.title;

              var prepTimeCell = row.insertCell();
              prepTimeCell.textContent = recipe.readyInMinutes;
          });
      })
      .catch(function(error) {
          console.log("An error occurred:", error);
      });
});

document.getElementById("randomRecipeBtn").addEventListener("click", function() {
  var apiUrl = "https://api.spoonacular.com/recipes/random";
  var apiKey = "aa18e54ba6804a7cb1180fe1df954d7d";
  var number = 1; // Number of random recipes to retrieve

  var requestUrl = apiUrl + "?apiKey=" + apiKey + "&number=" + number;

  fetch(requestUrl)
      .then(function(response) {
          return response.json();
      })
      .then(function(data) {
          // Process the API response for random recipe
          var randomRecipe = data.recipes[0];

          // Display the random recipe information as desired
          console.log("Random Recipe:", randomRecipe);
      })
      .catch(function(error) {
          console.log("An error occurred:", error);
      });
});