# Taschenrechner:

## Benötigte Dateien

Folgende Dateien sind notwending, um das Programm zu starten.

lvp.java ist für die Erzeugung der View nötig.

Zero.java, PositiveNonZero.java und NegativeNonZero.java enthalten die benötigten Records für die Erstellung von Numbers-Objekten.
Numbers.java enthält das Interface mit Methoden, um Rechenoperationen auf Numbers-Objekte anzuwenden.
StringToNumbers.java enthält die Methode stringZoNumbers(String string), um Strings in Numbers-Objekte zu übersetzen.
Write.java enthält Methoden zur Darstellung in der View.

Taschenrechner.java enthält den Inhalt von Zero.java, PositiveNonZero.java, NegativeNonZero.java, Numbers.java und StringToNumbers.java und kann alternativ zur Ausführung der Anwendung benutzt werden.

rechnerLVP.java erklärt die Funktionen der Anwendung und demonstriert die Funktionalität anhand der Szenarien über die View.

## Reihenfolge für Ausführung

Für die Ausführung wird die jshell mit folgenden Parametern gestartet:

```shell
jshell -R-ea --enable-preview
```

Anschließend werden die Dateien in folgender Reihenfolge geladen:
```shell
jshell> /o lvp.java
jshell> /o Taschenrechner.java
```

alternativ:
```shell
jshell> /o lvp.java
jshell> /o Zero.java
jshell> /o PositiveNonZero.java
jshell> /o NegativeNonZero.java
jshell> /o Numbers.java
jshell> /o StringToNumbers.java
jshell> /o Write.java
```

Danach kann kann die View gestartet werden.

```shell
jshell> Clerk.view()
jshell> /o rechnerLVP.java
```