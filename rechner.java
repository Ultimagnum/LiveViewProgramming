Clerk.markdown(STR.
"""
# Taschenrechner mit Peanozahlen

Oliver Johannes Krüger

Bei der Peanonotation von Zahlen werden anhand von Nachfolgern und Vorgängers definiert. Je nachdem wie viele Vorgänger (Nachfolger bei negativen Zahlen) eine Zahl besitzt

Der Taschenrechner kennt die folgenden Kommandos:

Befehl | Bedeutung
-------|----------
`equalZero()`               | Bestimmt ob eine Zahl gleich "0" ist
`greaterZero()`             | Bestimmt ob eine Zahl größer als "0" ist
`lessZero()`                | Bestimmt ob eine Zahl gleich "0" ist
`addOne()`                  | Gibt den Nachfoger einer Zahl zurück
`subOne()`                  | Gibt den Vorgänger einer Zahl zurück
`add(Numbers n)`            | Gibt die Summe der Zahl und n zurück
`sub(Numbers n)`            | Gibt die Differenz der Zahl mit dem Minuend n zurück 
`neg()`                     | Gibt den negierten Wert einer Zahl um 
`mul(Numbers n)`            | Gibt das Produkt der Zahl und n zurück
`exp(Numbers n)`            | Gibt den Potenzwert der Zahl mit dem Exponent n zurück
`div(Numbers n)`            | Gibt den Quotient der Zahl a mit dem Divisor n zurück
`mod(Numbers n)`            | Gibt den Modulo der Zahl a mit dem Divisor n zurück
`isEqual(Object other)`     | Vergleicht zwei Objekte, ob sie Numbers mit dem gleichen Wert sind
`isGreater(Numbers other)`  | Löscht Zeichenfläche, Schildkröte in Bildmitte
`isLess(Numbers other)`     | Löscht Zeichenfläche, Schildkröte in Bildmitte
`asString()`                | Gibt Zahl als String zurück
`write()`                   | Löscht Zeichenfläche, Schildkröte in Bildmitte
`writeIsGreater(Numbers n)` | Löscht Zeichenfläche, Schildkröte in Bildmitte
`writeIsLesser(Numbers n)`  | Löscht Zeichenfläche, Schildkröte in Bildmitte
`writeIsEqual(Numbers n)`   | Löscht Zeichenfläche, Schildkröte in Bildmitte
`writeAdd(Numbers n)`       | Löscht Zeichenfläche, Schildkröte in Bildmitte
`writeSub(Numbers n)`       | Löscht Zeichenfläche, Schildkröte in Bildmitte
`writeMul(Numbers n)`       | Löscht Zeichenfläche, Schildkröte in Bildmitte
`writeExp(Numbers n)`       | Löscht Zeichenfläche, Schildkröte in Bildmitte
`writeSub(Numbers n)`       | Löscht Zeichenfläche, Schildkröte in Bildmitte
`writeDiv(Numbers n)`       | Löscht Zeichenfläche, Schildkröte in Bildmitte

Mit diesen Kommandos wird die Schildkröte über die Zeichenfläche geschickt und das Zeichnen gesteuert. Wenn man Abfolgen von diesen Kommandos programmiert, kann man teils mit sehr wenig Code interessante Zeichnungen erstellen.

> Wenn man die Befehle in der JShell zur Verfügung hat, benötigt man kein weiteres Wissen zu Logo. Man kann mit den Sprachkonstrukten von Java arbeiten.

## Beispiel 1: Ein Quadrat aus Pfeilen

Mit `new Turtle(300,300)` wird eine neue Schildkröte mittig auf eine Zeichenfläche der angegebenen Größe (Breite, Höhe) gesetzt. In den Grundeinstellungen sind die Breite und die Höhe auf 500 gesetzt. 

Die folgende Logo-Anwendung demonstriert, wie man mittels Methoden schrittweise graphische Einheiten erstellen und zusammensetzen kann.

```java
\{Text.cutOut("./logo.java", "// myFirstTurtle")}
```

Das Ergebnis sieht dann so aus: ein Quadrat aus Pfeilen, wobei absichtlich kleine Zwischenräume gelassen wurden.
""");

// myFirstTurtle
Turtle myFirstTurtle = new Turtle(300, 300);

Turtle arrowhead(Turtle t) {
    return t.right(30).backward(10).forward(10).
             left(60).backward(10).forward(10).right(30);
}
Turtle arrow(Turtle t, double length) {
    return arrowhead(t.forward(length));
}
Turtle edge(Turtle t, double length, double space) {
    return arrow(t, length).penUp().forward(space).penDown();
}
myFirstTurtle = edge(myFirstTurtle, 100, 5).right(90);
myFirstTurtle = edge(myFirstTurtle, 100, 5).right(90);
myFirstTurtle = edge(myFirstTurtle, 100, 5).right(90);
myFirstTurtle = edge(myFirstTurtle, 100, 5).right(90);
// myFirstTurtle

Clerk.markdown(STR.
"""
## Beispiel 2: Umsetzung eines Logo-Programms in Java

Die Programmiersprache Logo ist nicht so schwer zu verstehen, wie das nachstehende Beispiel zeigt, das von dieser [Webseite](https://calormen.com/jslogo/) stammt. Auch wenn man kein Logo spricht, der Code ist leicht in Java umzusetzen.

```logo
TO tree :size
   if :size < 5 [forward :size back :size stop]
   forward :size/3
   left 30 tree :size*2/3 right 30
   forward :size/6
   right 25 tree :size/2 left 25
   forward :size/3
   right 25 tree :size/2 left 25
   forward :size/6
   back :size
END
clearscreen
tree 150
```

Die Java-Methode `tree` bildet das obige Logo-Programm nach; lediglich aus praktischen Überlegungen lasse ich den Rekursionsabbruch etwas früher greifen.

```java
\{Text.cutOut("./logo.java", "// turtle tree")}
```

Der Aufruf der Methode `tree` erzeugt etwas, was einem "Baum" ähnelt.

```java
\{Text.cutOut("./logo.java", "// tree")}
```

""");

// turtle tree
Turtle turtle = new Turtle().left(90);

void tree(Turtle turtle, double size) {
    if (size < 10) {
        turtle.forward(size).backward(size);
        return;
    }
    turtle.forward(size / 3).left(30);
    tree(turtle, size * 2.0 / 3.0);
    turtle.right(30);

    turtle.forward(size / 6).right(25);
    tree(turtle, size / 2.0);
    turtle.left(25);

    turtle.forward(size / 3).right(25);
    tree(turtle, size / 2.0);
    turtle.left(25);

    turtle.forward(size / 6).backward(size);
}
// turtle tree

// tree
tree(turtle, 150);
// tree

Clerk.markdown(STR."""
## Beispiel 3: Es kommt Farbe ins Spiel

Mit Farbe wird die Welt bunter und interessanter, und die Strichstärke kann man ebenfalls für Effekte einsetzen. Im nachfolgenden Beispiel verblasst die Farbe zunehmend und die Strichstärke lässt allmählich nach.

```java
\{Text.cutOut("./logo.java", "// triangles")}
```
""");

// triangles
Turtle turtle = new Turtle(300,350);

void triangle(Turtle turtle, double size) {
    turtle.forward(size).right(60).backward(size).right(60).forward(size).right(60 + 180);
}

void drawing(Turtle turtle, double size) {
    for (int i = 1; i <= 36; i++) {
        turtle.color(255,i * 256 / 37, i * 256 / 37);
        turtle.lineWidth(1.0 - 1.0 / 36.0 * i);
        triangle(turtle, size + 1 - 2 * i);
        turtle.left(10).forward(10);
    }
}

drawing(turtle, 100);
// triangles

Clerk.markdown(STR."""
## Beispiel 4: Interaktivität mit Slider (Preview-Feature, _unstable_)

Es ist auch möglich, eine Turtle-Grafik mit einem Slider-Clerk zu koppeln – und es entsteht eine interaktive Anwendung.

```java
\{Text.cutOut("./logo.java", "// interactivity")}
```

Das macht noch mehr Spaß! Die Zeichnungen werden auf Seiten des Java-Programms mit jeder Änderung am Slider neu erzeugt.

> Nutzt man die Entwicklertools im Chrome-Browser, um die internen Abläufe zu verfolgen, treten beim "Sliden" sofort `onerror`-Events auf. Der Browser erholt sich zwar davon, aber der Interaktionseffekt ist dahin. Ohne Entwicklertools läuft auf einem MacBook Air mit M1-Prozessor meist alles flüssig und unproblematisch. Allerdings gibt es auch hier gelegentlich Aussetzer. Es scheint nötig zu sein, während der Abarbeitung des `input`-Events vom Slider weitere Folgeevents zu unterbinden. Wie gesagt, ein Preview-Feature, wo es noch hakt.
""");

// interactivity
turtle = new Turtle(300, 350);

drawing(turtle, (200.0 + 10.0) / 2.0);

Slider slider = new Slider(Clerk.view(), 10, 200);
slider.attachTo(response -> {
    double size = Double.parseDouble(response);
    turtle.reset();
    drawing(turtle, size);
});
// interactivity


Clerk.markdown(STR."""
Soviel möge als Demo vorerst genügen! _More features to come_ 😉
""");
