# Tetris

## IDE - Integrated Development Environment (integrierte Entwicklungsumgebung)

Eine integrierte Entwicklungsumgebung (IDE, von englisch integrated development environment) ist 
eine Sammlung von Computerprogrammen, mit denen die Aufgaben der Softwareentwicklung 
möglichst ohne Medienbrüche bearbeitet werden können.

<small>Quelle: [wikipedia.org](https://de.wikipedia.org/wiki/Integrierte_Entwicklungsumgebung)</small>

### Bekannte IDEs 

* [BlueJ](https://www.bluej.org/): Reduzierte IDE für pädagogische Zwecke
* [Visual Studio Code](https://code.visualstudio.com): von Microsoft entwickelt, für alle Sprachen einsetzbar, wegen vieler Erweiterungen, läuft auf Google Chrome
* [Eclipse](https://www.eclipse.org/downloads) 
* [IntelliJ IDEA](https://www.jetbrains.com/de-de/idea): auf Java spezialisiert

Wir setzen die [Community Edition von IntelliJ](https://www.jetbrains.com/de-de/idea/download/other.html) ein. 

## Java-Paketnamen

Um Pakete mit gleichem Namen zu vermeiden, haben sich in der Java-Welt folgende 
Konvention für Paketnamen herausgebildet:

* Paketnamen bestehen nur aus Kleinbuchstaben und Unterstrichen `_` (um sie von Klassen zu unterscheiden).
* Paketnamen sind durch Punkte getrennt.
* Der Anfang des Paketnames wird durch die Organisation bestimmt, die sie erstellt.

Um den Paketnamen auf der Grundlage einer Organisation zu bestimmen, wird die URL der Organisation umgedreht.
Beispielsweise wird aus der URL 

    https://pirckheimer-gymnasium.de/tetris

der Paketname:

    de.pirckheimer_gymnasium.tetris

<small>Quelle: [baeldung.com](https://www.baeldung.com/java-packages#1-naming-conventions)</small>

## Importe von Java-Klassen aus Paketen

Java verfügt über unzählige vorgefertigte Klassen und Schnittstellen. Thematisch zusammengehörende Klassen und
Schnittstellen werden zu einem Paket (*package*) zusammengefasst. Die so entstehende Java-Bibliothek ist riesig und
enthält tausende verschiedener Klassen mit unterschiedlichsten Methoden. Um sich einer dieser Klassen bedienen
zu können, muss man sie in das gewünschte Projekt importieren. In Java funktioniert das mit dem Schlüsselwort
`import`.

__Syntax__

`import <paketname>.<klassenname>;` Importiert nur die gewünschte Klasse des angesprochenen Paketes.<br>
`import <paketname>.*;` Importiert sämtliche Klassen des angesprochenen Paketes.

__Beispiel__

`import java.util.Random;` Importiert die Klasse Random des Paketes java.util.<br>
`import java.util.*;` Importiert das vollständige Paket java.util.

<small>Quelle: Klett, Informatik 2, 2021, Seite 275</small>