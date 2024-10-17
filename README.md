In dieser Woche ersetzen Sie arrays durch ArrayLists und üben nochmal das Parsen von Strings.

Beachten Sie, dass ArrayLists zwar ähnlich, aber nicht identisch sind wie Arrays! Beispielsweise finden Sie die Länge einer Arraylist mit der Methode `.size()` und nicht mit dem Attribut `.length`.

# Aufgabe 1

Passen Sie die Metode **getIngredients()** an, sodass sie eine Array Liste zurückgibt. Ändern Sie auch den Datentyp des Zutaten-Arrays zu einer Array Liste. 
Passen Sie auch die Methoden zum Hinzufügen und Entfernen von Zutaten an. Ändern Sie nur den Datentyp, nicht die Logik der Methode (sie soll weiterhin prüfen, ob eine Zutat schon vorhanden ist).

# Aufgabe 2

Ändern Sie auch die Variable Rezepte-Array in der Klasse `RecipeBook` zu einer Array List. Passen Sie außerdem die Methoden entsprechend an.  

# Aufgabe 3

Passen Sie den Constructor der Klasse `Recipe` so an, dass sie nur noch einen String als Argument nimmt, in dem alle Informationen übergeben werden. Sie können dann den anderen Konstruktor löschen, achten Sie aber darauf, dass trotzdem bei der Initialisierung nicht unbedingt alle Informationen übergeben werden müssen. 

Der Konstruktor kann nach der Änderung beispielsweise so aufgerufen werden:
```java
Recipe pancakes = new Recipe("Apfelpfannkuchen;20;vegetarisch;4");
```
oder auch so:
```java
Recipe pancakes = new Recipe("Apfelpfannkuchen;20;vegetarisch");
```
Hinweis: Nutzen Sie die Methode `String.split()`, um die einzelnen Argumente voneinander zu trennen. 

# Aufgabe 4

Fügen Sie in der Klasse `RecipeBook` eine Hash Map hinzu (`HashMap<String, Integer>`), in der die Namen der Rezepte als Keys gespeichert sind und die zugehörige Zubereitungszeit als Values. Implementieren Sie eine Methode `public boolean addPrepTime(String recipeName, int preperationTime)`, die ein neues Rezept mit Zubereitungszeit zur Hash Map hinzufügt. Die Methode gibt True zurück, wenn es geklappt hat und False, wenn nicht. Überprüfen Sie vor dem Hinzufügen, ob ein Rezept mit dem gleichen Namen bereits vorhanden ist.
Beispiel: `addPrepTime("Pfannkuchen", 15)` fügt das Rezept Pfannkuchen mit einer Zubereitungszeit von 15 Minuten hinzu. `addPrepTime("Pfannkuchen", 10)` gibt false zurück, wenn "Pfannkuchen" schon existiert. 

# Aufgabe 5

Fügen Sie der Klasse RecipeBook eine Methode `pubic String getRecipesByTime(int preparationTime)` hinzu, die eine Zahl entgegennimmt und als Rückgabewert einen String liefert. Dieser String soll eine kommagetrennte Liste aller Rezepte enthalten, die genau die angegebene Zubereitungszeit haben. Wenn keine Rezepte für die gegebene Zubereitungszeit gefunden werden, soll die Methode einen leeren String zurückgeben.