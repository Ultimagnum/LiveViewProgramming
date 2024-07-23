Clerk.markdown(STR.
"""
# Taschenrechner mit Peanozahlen

_Oliver Johannes Krüger_, _5315677_

Bei dieser Anwendung handelt es sich um einen Taschenrechner, der anstatt Zahlentypen Peano-Zahlen verwendet.
Bei der Peanonotation von Zahlen werden anhand von Nachfolgern und Vorgängers definiert. Je nachdem wie viele Vorgänger (Nachfolger bei negativen Zahlen) eine Zahl besitzt, ist deren Wert größer oder kleiner.
Für die Definition werden hier das Interface Numbers und die Records Zero, PositiveNonZero und NegativeNonZero benutzt. Eine Instanz von PositiveNonZero enthält eine Refrenz auf seinen Vorgänger, während eine Instanz von NegativeNonZero eine Refrenz auf seinen Nachfolger.

Mithilfe der Methoden addOne() und subOne() wird jeweils der Nachfolger und Vorgänger einer Zahl zurückgegeben. Wird addOne() oder subOne() auf eine Instanz von Zero angewendet, so wird eine neue Instanz von PositiveNonZero oder NegativeNonZero mit dem Nachfolger/Vorgänger Zero zurückgegeben.
Neue Numbers-Objekte können auch mit der Methode stringToNumbers(String string) erstellt werden. Diese nimmt einen String aus Ziffern und gibt ein Numbers-Objekt mit dem entsprechendem Wert zurück.

Auf Numbers-Objekte können Methoden angewendet werden, die deren Werte mit anderen Numbers-Objekten vergleichen oder arithmetische Operationen durchführen.
Numbers-Objekte kennen die folgenden Methoden:

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
`isGreater(Numbers other)`  | Bestimmt ob die Zahl größer als other ist
`isLess(Numbers other)`     | Bestimmt ob die Zahl kleiner als other ist
`asString()`                | Gibt die Zahl als String zurück


Zusätzlich zu diesen Operationen gibt es noch unabhängige write*()-Methoden. Diese dienen dazu die Werte von Numbers-Objekten, Gleicheit von zwei Numbers-Objekten, sowie Operatorgleichungen mit zwei Operanden auf der View darzustellen.

Befehl | Bedeutung
-------|----------
`writeNumbers(Numbers n)`              | Gibt den Wert eines Numbers-Objekt auf der View zurück
`writeEquality(Numbers n, Numbers m)`  | Gibt auf der View zurück, ob zwei Numbers-Objekte gleich, größer oder kleiner sind
`writeAdd(Numbers n, Numbers m)`       | Gibt eine Additionsgleichung auf der View zurück
`writeSub(Numbers n, Numbers m)`       | Gibt eine Subtraktionsgleichung auf der View zurück
`writeMul(Numbers n, Numbers m)`       | Gibt eine Multiplikationsgleichung auf der View zurück
`writeExp(Numbers n, Numbers m)`       | Gibt eine Potenzgleichung auf der View zurück
`writeSub(Numbers n, Numbers m)`       | Gibt eine Divisionssgleichung auf der View zurück
`writeDiv(Numbers n, Numbers m)`       | Gibt eine Modulogleichung auf der View zurück


## Szenario 1: Erstellung von Zahlen / addOne() und subOne()

Mithilfe der Methode stringToNumbers(String string) lessen sich neue Numbers-Objekte erstellen. Das vereinfacht die Erstellung, anstatt wenn man die Zahlen manuell über die addOne() und subOne() erstellen würde.
Die folgenden Testfälle sollen zeigen, dass stringToNumbers(String string) die richtigen Zahlen zurückgibt.

```java
\{Text.cutOut("./rechnerLVP.java", "// Gleicheit")}
```

Für jeden Fall sollte in der View die Gleichheit der Zahlen bestätigt werden.

""");

// Gleicheit 
writeEquality(stringToNumbers("3"), new Zero().addOne().addOne().addOne());
writeEquality(stringToNumbers("-5"), new Zero().subOne().subOne().subOne().subOne().subOne());
writeEquality(stringToNumbers("2"), new Zero().addOne().addOne().addOne().subOne());
// Gleicheit

Clerk.markdown(STR."""
## Szenario 2: Addition
Zwei Zahlen werden miteinander addiert. Das erfolgt durch mehrere Aufrufe von addOne(). Wird eine negative Zahl addiert, wird stattdessen wiederholt subOne() angewendet.

```java
\{Text.cutOut("./rechnerLVP.java", "// Addition")}
```

Erwartete Gleichungen:

2 + 6 = 8

8 + (-3) = 5

67 + 4 = 71


Erzeugte Gleichungen:
""");

// Addition
writeAdd(stringToNumbers("2"),stringToNumbers("6"));
writeAdd(stringToNumbers("8"),stringToNumbers("-3"));
writeAdd(stringToNumbers("67"),stringToNumbers("4"));
// Addition

Clerk.markdown(STR."""
## Szenario 3: Subtraktion
Von einer Zahl wird eine anderer subtrahiert. Das erfolgt durch mehrere Aufrufe von subOne(). Wird eine negative Zahl subtrahiert, wird stattdessen wiederholt addOne() angewendet.

```java
\{Text.cutOut("./rechnerLVP.java", "// Subtraktion")}
```

Erwartete Gleichungen:

7 - 4 = 3

(-5) - 3 = -8

6 - (-5) = 11


Erzeugte Gleichungen:
""");

// Subtraktion
writeSub(stringToNumbers("7"),stringToNumbers("4"));
writeSub(stringToNumbers("-5"),stringToNumbers("3"));
writeSub(stringToNumbers("6"),stringToNumbers("-5"));
// Subtraktion

Clerk.markdown(STR."""
## Szenario 4: Multiplikation
Eine Zahl wird wiederholt mit sich selbst addiert. Wie oft wird durch eine andere Zahl bestimmt. Wenn genau ein Operand negativ ist, ist auch das Ergebnis negativ.

```java
\{Text.cutOut("./rechnerLVP.java", "// Multiplikation")}
```
Erwartete Gleichungen:

5 * 4 = 20

0 * 7 = 0

7 * (-3) = -21

(-5) * (-6) = 30


Erzeugte Gleichungen:
""");

// Multiplikation
writeMul(stringToNumbers("5"),stringToNumbers("4"));
writeMul(stringToNumbers("0"),stringToNumbers("7"));
writeMul(stringToNumbers("7"),stringToNumbers("-3"));
writeMul(stringToNumbers("-5"),stringToNumbers("-6"));

// Multiplikation

Clerk.markdown(STR."""
## Szenario 5: Potenzierung
Eine Basis wird wiederholt mit sich multipliziert. Wie oft wird durch den Exponenten bestimmt. Sollte der Exponenete "0" seien, ist das Ergebis immer "1".

```java
\{Text.cutOut("./rechnerLVP.java", "// Potenzierung")}
```

Erwartete Gleichungen:

7 ^ 3 = 343

5 ^ 0 = 1

0 ^ 6 = 0



Erzeugte Gleichungen:
""");

// Potenzierung
writeExp(stringToNumbers("7"),stringToNumbers("3"));
writeExp(stringToNumbers("5"),stringToNumbers("0"));
writeExp(stringToNumbers("0"),stringToNumbers("6"));
// Potenzierung

Clerk.markdown(STR."""
## Szenario 6: Division
Es wird bestimmt, wie oft eine Zahl von einer anderen abgezogen werden kann, bis diese einen Wert kleiner oder gleich "0" annimmt. Wenn genau ein Operand negativ ist, ist auch das Ergebnis negativ.

```java
\{Text.cutOut("./rechnerLVP.java", "// Division")}
```
Erwartete Gleichungen:

66 / 2 = 33

18 / 4 = 4

12 / (-5) = -2

(-15) / (-3) = 5



Erzeugte Gleichungen:
""");

// Division
writeDiv(stringToNumbers("66"),stringToNumbers("2"));
writeDiv(stringToNumbers("18"),stringToNumbers("4"));
writeDiv(stringToNumbers("12"),stringToNumbers("-5"));
writeDiv(stringToNumbers("-15"),stringToNumbers("-3"));
// Division

Clerk.markdown(STR."""
## Szenario 7: Modulo

Es wird von einer Zahl solange ein Wert abgezongen, bis diese das Vorzeichen wechselt, oder den Wert "0" annimmt. Der vorletzte Wert wird zurückgegeben. Wenn genau ein Operand negativ ist, ist auch das Ergebnis negativ.

```java
\{Text.cutOut("./rechnerLVP.java", "// Modulo")}
```
Erwartete Gleichungen:

35 % 3 = 2

(-24) % 5 = -4

14 % (-7) = 0

14 % 20 = 14

Erzeugte Gleichungen:
""");

// Modulo
writeMod(stringToNumbers("35"),stringToNumbers("3"));
writeMod(stringToNumbers("-24"),stringToNumbers("5"));
writeMod(stringToNumbers("14"),stringToNumbers("-7"));
writeMod(stringToNumbers("14"),stringToNumbers("20"));
// Modulo

Clerk.markdown(STR."""
## Szenario 8: Verkettete Aufrufe

Unabhängig von den angebotenen Write*()-Methoden lassen sich die Operatoren beliebig kombinieren. Eine Darstellung der Gleichung ist dann aber nicht möglich.

```java
\{Text.cutOut("./rechnerLVP.java", "// Kette")}
```
Erwartete Ergebnisse:

315

-105

117649

Erzeugte Ergenisse:
""");

// Kette
writeNumbers(stringToNumbers("5").mul(stringToNumbers("7")).mul(stringToNumbers("9")));
writeNumbers(stringToNumbers("7").mul(stringToNumbers("-6").sub(stringToNumbers("9"))));
writeNumbers(stringToNumbers("7").exp(stringToNumbers("2")).exp(stringToNumbers("3")));
// Kette

//stringToNumbers("")