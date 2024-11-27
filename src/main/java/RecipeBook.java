import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecipeBook {

    private ArrayList<Recipe> recipes;
    private HashMap<String, Integer> recipeToPrepTime;

    public RecipeBook() {
        recipes = new ArrayList<>();
        recipeToPrepTime = new HashMap<>();
    }

    public ArrayList<Recipe> getRecipes() {
        return this.recipes;
    }
    public HashMap<String, Integer> getRecipeMap() {
        return recipeToPrepTime;
    }

    public boolean addRecipe(Recipe recipe) {
        String recipeName = recipe.getName();

        // Testet, ob das Rezept schon im Buch ist
        if (containsRecipe(recipeName)) return false;

        return recipes.add(recipe);
    }

    public boolean removeRecipe(Recipe recipe) {
        if (!containsRecipe(recipe.getName())) return false;

        return recipes.remove(recipe);
    }

    private boolean containsRecipe(String recipeName) {
        for (Recipe r : recipes) {
            if (r != null && r.getName().equals(recipeName)) {
                return true;
            }
        }
        return false;
    }

    public boolean addPrepTime(Recipe recipe) {
        if (recipeToPrepTime.containsKey(recipe.getName())) return false;
        else {
            recipeToPrepTime.put(recipe.getName(), recipe.getPrepTime());
            return true;
        }
    }

    public ArrayList<Recipe> recipeByCategory(String category) {
        ArrayList<Recipe> categoryRecipes = new ArrayList<>();

        for (Recipe r : this.recipes) {
            if (r.getCategory().equals(category)) {
                categoryRecipes.add(r);
            }
        }
        return categoryRecipes;
    }

    private void printRecipes(ArrayList<Recipe> recipesToPrint) {
        String result = "";
        for (Recipe rec : recipesToPrint) {
            result += "Name: " + rec.getName() + ", Zubereitungszeit: " + rec.getPrepTime();
            if (rec.getCategory() != null) result += ", Kategorie: " +  rec.getCategory();
            if (rec.getRating() != 0) result += ", Bewertung: " +  rec.getRating();
            result += "\n";
        }
        System.out.print(result);
    }

    public void printByCategory(String category) {
        ArrayList<Recipe> recipesToPrint = recipeByCategory(category);
        printRecipes(recipesToPrint);
    }

    public void printAllRecipes() {
        printRecipes(this.recipes);
    }

    public String getRecipesByTime(int time) {
        ArrayList<String> recipes = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : this.recipeToPrepTime.entrySet()) {
            if (entry.getValue() == time) {
                recipes.add(entry.getKey());
            }
        }
        return String.join(", ", recipes);
    }
}