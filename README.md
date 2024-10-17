In dieser Woche ersetzen Sie arrays durch ArrayLists und üben nochmal das Parsen von Strings.

Beachten Sie, dass ArrayLists zwar ähnlich, aber nicht identisch sind wie Arrays! Beispielsweise finden Sie die Länge einer Arraylist mit der Methode `.size()` und nicht mit dem Attribut `.length`.

# Aufgabe 1

Passen Sie die Methode `Note.getNoteEvents()` so an, dass sie anstatt eines `byte[]` eine `ArrayList<Byte>` zurückgibt. Dies gilt nur Übungszwecken - an dieser Stelle ist auch ein `byte[]` an sich vollkommen in Ordnung, da der Code durch die Verwendung der `ArrayList` nicht einfacher wird. Im Gegenteil, Sie benötigen sogar einige Zeilen mehr. Während ein Array sich einfach in einer Zeile initialisieren lässt, sind bei der Initialisierung einer ArrayList einige Dinge zu beachten:
* Sie können die ArrayList initialisieren, indem Sie sie instanziieren und dann mehrmals `.add()` darauf aufrufen. Das ist aber sehr umständlich.
* Sie können - was in diesem Fall vermutlich am einfachsten ist - eine `ArrayList` mit allen Werten eines Arrays initialisieren. Dafür können Sie die Methode `Arrays.asList()` verwenden, beispielsweise:

```java
Byte[] data = new Byte[] {0x05b, 0x12b};
ArrayList<Byte> list = new ArrayList<Byte>(Arrays.asList(data));
```

Beachten Sie in diesem Fall, dass das Array kein `byte[]` mehr ist, sondern ein `Byte[]`.

# Aufgabe 2

Passen Sie die Methode `MIDITrack.Track()` so an, dass sie anstatt eines `byte[]` eine `ArrayList<Byte>` zurückgibt. An dieser Stelle müssten Sie den Vorteil der `ArrayList` - insbesondere der Methode `addAll(ArrayList other)` - sehen.

# Aufgabe 3

Passen Sie die Methode `MIDITrack.getBytes()` so an, dass sie anstatt eines `byte[]` eine `ArrayList<Byte>` zurückgibt.

Nun wird die Klasse `MIDITools` nicht mehr benötigt!

# Aufgabe 4

Passen Sie den Constructor von `MIDITrack` so an, dass er nur noch ein Argument nimmt: Einen `String`, der den ganzen Track im folgenden Format beschreibt: `<Default velocity>;<Dauer einer ganzen Note>;<Instrument>;<Noten>`, beispielsweise `60;120;Acoustic Grand Piano;C,2 G''#2`. Die einzelnen Noten sind also mit Leerzeichen getrennt, davor stehen drei mit Semikolons abgetrennte Informationen. Die ersten drei Informationen sind die default velocity und die Dauer einer ganzen Note (die Sie beide für den Constructor von `Note` benötigen) sowie der Name des Instruments, danach kommen die Noten im gewohnten Format. Sie können sich das Leben mit der Methode `String.split()` hier sehr einfach machen.

Das Instrument soll der Instrumentenname aus der [MIDI-Spezifikation](http://www.music.mcgill.ca/~ich/classes/mumt306/StandardMIDIfileformat.html#BMA1_), Appendix 1.4, sein. Verwenden Sie hierfür nicht 128 if-else! Sie können das einfach mit einer `HashMap` lösen, die Sie im Constructor aus einem String initialisieren, den Sie sich wiederum aus der Tabelle zusammenkopieren. Hier ein Beispiel für die Verwendung einer HashMap in einem ähnlichen Kontext:

```java
public class HashMapExample {
    private HashMap<String, Integer> ages = new HashMap<>();
    private String ageInfo = "Agnes, 10; Mark, 30; Thomas, 12";
    
    public HashMapExample() {
        String[] items = ageInfo.split(";");
        for(String item : items) {
            ageData = item.split(", " );
            String name = ageData[0];
            Integer age = Integer.parseInt(ageData[1]);
            ages.add(name, age);
        }
    }
        
    public String getAge(String name) {
        return ages.get(name);
    }
}
```