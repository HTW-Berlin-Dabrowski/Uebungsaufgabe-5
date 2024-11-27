import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeBookTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private RecipeBook recipeBook;
    private Recipe pancake;
    private Recipe curry;

    @BeforeEach
    void setup() {
        recipeBook = new RecipeBook();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testAddRecipe() {
        pancake = new Recipe("Pfannkuchen", 10);
        curry = new Recipe("Curry", 20);
        boolean returnsTrue = recipeBook.addRecipe(pancake);
        boolean returnsFalse = recipeBook.addRecipe(pancake);
        assertTrue(returnsTrue && !returnsFalse);
    }

    @Test
    void testAddRecipeOverCapacity() {
        pancake = new Recipe("Pfannkuchen", 10);
        curry = new Recipe("Curry", 20);
        recipeBook.addRecipe(pancake);
        recipeBook.addRecipe(curry);
        assertTrue(recipeBook.addRecipe(new Recipe("Salat", 10)));
        assertTrue(recipeBook.addRecipe(new Recipe("Suppe", 20)));
    }

    @Test
    void testRemoveRecipe() {
        pancake = new Recipe("Pfannkuchen", 10);
        curry = new Recipe("Curry", 20);
        recipeBook.addRecipe(pancake);
        boolean returnsTrue = recipeBook.removeRecipe(pancake);
        boolean returnsFalse = recipeBook.removeRecipe(pancake);
        assertTrue(returnsTrue && !returnsFalse);
    }

    @Test
    void testRecipeyByCatecory() {
        pancake = new Recipe("Pfannkuchen", 10);
        curry = new Recipe("Curry", 20);
        pancake.setCategory("vegetarisch");
        curry.setCategory("vegan");
        recipeBook.addRecipe(pancake);
        recipeBook.addRecipe(curry);
        ArrayList<Recipe> veganRec = new ArrayList<>();
        veganRec.add(curry);
        ArrayList<Recipe> vegetRec = new ArrayList<>();
        vegetRec.add(pancake);
        assertEquals(vegetRec, recipeBook.recipeByCategory("vegetarisch"));
        assertEquals(veganRec, recipeBook.recipeByCategory("vegan"));
    }

    @Test
    void testPrintRecipes() {
        pancake = new Recipe("Pfannkuchen", 10);
        curry = new Recipe("Curry", 20);
        recipeBook.addRecipe(pancake);
        pancake.setCategory("vegetarisch");
        pancake.setRating(4);
        recipeBook.addRecipe(curry);
        recipeBook.printAllRecipes();
        assertEquals("Name: Pfannkuchen, Zubereitungszeit: 10, Kategorie: vegetarisch, Bewertung: 4" + System.lineSeparator() +
                "Name: Curry, Zubereitungszeit: 20" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void testGetRecipes() {
        Object recipes = recipeBook.getRecipes();
        assertTrue(recipes instanceof ArrayList);
    }
    @Test
    void testPrintByCategory() {
        pancake = new Recipe("Pfannkuchen", 10);
        curry = new Recipe("Curry", 20);
        pancake.setCategory("vegetarisch");
        curry.setCategory("vegan");
        recipeBook.addRecipe(pancake);
        recipeBook.addRecipe(curry);
        recipeBook.printByCategory("vegan");
        assertEquals("Name: " + curry.getName() + ", Zubereitungszeit: " + curry.getPrepTime()+ ", Kategorie: vegan" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void testAddPrepTime() {
        pancake = new Recipe("Pfannkuchen;10");
        curry = new Recipe("Curry;20;4");
        HashMap<String, Integer> testMap = new HashMap<>();
        testMap.put("Pfannkuchen", 10);
        testMap.put("Curry", 20);
        recipeBook.addPrepTime(curry);
        recipeBook.addPrepTime(pancake);
        assertEquals(testMap, recipeBook.getRecipeMap());
        assertFalse(recipeBook.addPrepTime(pancake));
    }

    @Test
    void testGetRecipesByTime() {
        pancake = new Recipe("Pfannkuchen;10");
        curry = new Recipe("Curry;20;4");
        Recipe salad = new Recipe("Salat;10");
        HashMap<String, Integer> testMap = new HashMap<>();
        recipeBook.addPrepTime(curry);
        recipeBook.addPrepTime(pancake);
        recipeBook.addPrepTime(salad);
        assertEquals("Curry", recipeBook.getRecipesByTime(20));
        assertEquals("Pfannkuchen, Salat", recipeBook.getRecipesByTime(10));
    }
}
