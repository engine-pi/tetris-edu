# Tetris

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

Quelle: https://www.baeldung.com/java-packages#1-naming-conventions

## Importe von Java-Klassen aus Paketen

Java verfügt über unzählige vorgefertigte Klassen und Schnittstellen. Thematisch zusammengehörende Klassen und
Schnittstellen werden zu einem Paket (*package*) zusammengefasst. Die so entstehende Java-Bibliothek ist riesig und
enthält tausende verschiedener Klassen mit unterschiedlichsten Methoden. Um sich einer dieser Klassen bedienen
zu können, muss man sie in das gewünschte Projekt importieren. In Java funktioniert das mit dem Schlüsselwort
`import`.

__Syntax__

`import <paketname>.<klassenname>;` Importiert nur die gewünschte Klasse des angesprochenen Paketes.

`import <paketname>.*;` Importiert sämtliche Klassen des angesprochenen Paketes.

__Beispiel__

`import java.util.Random;` Importiert die Klasse Random des Paketes java.util.

`import java.util.*;` Importiert das vollständige Paket java.util.

Quelle: Klett, Informatik 2, 2021, Seite 275