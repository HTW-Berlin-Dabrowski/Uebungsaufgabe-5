import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecipeTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    Recipe pancake;
    Recipe curry;
    @BeforeEach
    void setup() {
        pancake = new Recipe("Apfelpfannkuchen", 20, 4);
        curry = new Recipe("Gemüsecurry", 30);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testAddIngredient() {
        boolean returnsTrue = pancake.addIngredient("Apfel");
        boolean returnsFalse = !pancake.addIngredient("Apfel");

        assertTrue(returnsTrue && returnsFalse); // Erwartet: erstes Hinzufügen erfolgreich, zweites fehlschlägt
        assertEquals(
                        "Hinzufügen fehlgeschlagen: Zutat Apfel ist bereits vorhanden." + System.lineSeparator(),
                outContent.toString()
        );
    }

    @Test
    void testRemoveIngredient() {
        pancake.addIngredient("Apfel");
        outContent.reset();

        boolean returnTrue = pancake.removeIngredient("Apfel");
        boolean returnFalse = !pancake.removeIngredient("Apfel");

        assertTrue(returnTrue && returnFalse); // Erwartet: erstes Entfernen erfolgreich, zweites fehlschlägt
        assertEquals(
                        "Löschen fehlgeschlagen: Zutat Apfel ist nicht vorhanden." + System.lineSeparator(),
                outContent.toString()
        );
    }

    @Test
    void testNewConstructor() {
        pancake = new Recipe("Pfannkuchen;10");
        curry = new Recipe("Curry;20;4");
        assertEquals("Pfannkuchen", pancake.getName());
        assertEquals(10, pancake.getPrepTime());
        assertEquals("Curry", curry.getName());
        assertEquals(20, curry.getPrepTime());
        assertEquals(4, curry.getRating());
    }

}
