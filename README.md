# Tetris auf dem Gameboy

Nachbau des Spiels Tetris auf dem klassischem Gameboy mit Hilfe von Java und
der Engine Alpha.

## Über das Spiel Tetris

### Bildschirmauflösung

Der Bildschirmausschnitt des klassische Gameboys ist `160x144` (Breite `x` Höhe)
Pixel. Ein Block hat die Größe `8x8`. Der Bildschirmausschnitt lässt
sich als mit `20x18` Blöcken ausfüllen.

### Hauptmodi

Es gibt zwei Hauptmodi: _„A-Type“_ und _„B-Type“_.

Im _„A-Type“_-Modus muss eine möglichst hohe Punktzahl erreicht werden, indem
Blockreihen auflöst werden, während die Tetrominos immer schneller fallen.

Im _„B-Type“_-Modus müssen 25 Reihen beseitigt werden, um zu siegen. Der
Schwierigkeitsgrad kann erhöht werden, indem
die Fallgeschwindigkeit heraufsetzt und dadurch mehr Tetrominos
eingestellt werden.[^nintendo]

### Drehung

Die Tetrominos drehen sich nur in eine Richtung, nämlich nach rechts.
Je nach Tetromino müssten unterschiedlich viele Zustände abgebildet werden.

- ein Zustand: `O`
- zwei Zustände: `I`, `Z` und `S`
- vier Zustände: `L`, `J` und `T`

Den begrenzten grafischen Möglichkeiten des originalen Gameboys geschuldet,
drehen sich die Tetrominos nicht geometrisch korrekt, sondern simulieren die
Rotation nur. Bei dieser Rotationssimulation sind je nach Tetromino mehrere
Möglichkeiten realisierbar.
In verschiedenen Internetforen wird vom `right handed` bzw.
`left handed` Rotationssystem gesprochen. Die Gameboy-Variante verwendet
das `left handed` System. Das `I`, `Z` und `S` sind bei der Drehung auf
linken Seite des Rotationsquadrats angesiedelt.
[^strategywiki]

![Blueprint](https://raw.githubusercontent.com/Josef-Friedrich/tetris/main/misc/Blueprint.svg)

### Farben

Der ursprüngliche Game Boy verwendet einen monochromen Bildschirm, der lediglich
vier verschiedene Farbschattierungen anzeigen kann.
Da der Hintergrund des nicht beleuchteten LCD-Displays grünlich ist, führt
dies zu einer „grünstichigen“ Grafikanzeige.

![](https://raw.githubusercontent.com/Josef-Friedrich/tetris/main/misc/Colors.svg)

| deutsch | englisch | grün[^gimp-green]       | grün[^wikipedia-green]   | grau[^mgba-gray]          |
| ------- | -------- | ----------------------- | ------------------------ | ------------------------- |
| weiß    | white    | `#aaaa00` `(170,170,0)` | `#7b8210` `(123,130,16)` | `#ffffff` `(255,255,255)` |
| hell    | light    | `#556633` `(85,102,52)` | `#5a7942` `(90,121,66)`  | `#adadad` `(173,173,173)` |
| dunkel  | dark     | `#335544` `(51,85,68)`  | `#39594a` `(57,89,74)`   | `#525252` `(82,82,82)`    |
| schwarz | black    | `#223322` `(34,51,34)`  | `#294139` `(41,65,57)`   | `#000000` `(0,0,0)`       |

### Start-Positionen

Die Tetrominos erscheinen auf der Koordinate `(4,16)` und als Vorschau auf der Koordinate `(14,3)`.

![Blueprint](https://raw.githubusercontent.com/Josef-Friedrich/tetris/main/misc/Start-Positions.svg)

### Geschwindigkeit

Gameboy läuft mit einer Framerate von `59.73` Bildern pro Sekunde.

![Die Tetris-ROM im Hex-Editor bei Byte `1B06h`](https://raw.githubusercontent.com/Josef-Friedrich/tetris/main/misc/graphics/Level-Speed-Table_1B06.png)

| Level              | 0     | 1     | 2     | 3     | 4     | 5     | 6     | 7     | 8     | 9     | 10    | 11    | 12    | 13    | 14    | 15    | 16    | 17    | 18    | 19    | 20    |
| ------------------ | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- |
| Frames per row     | 53    | 49    | 45    | 41    | 37    | 33    | 28    | 22    | 17    | 11    | 10    | 9     | 8     | 7     | 6     | 6     | 5     | 5     | 4     | 4     | 3     |
| Frames per row - 1 | 52    | 48    | 44    | 40    | 36    | 32    | 27    | 21    | 16    | 10    | 9     | 8     | 7     | 6     | 5     | 5     | 4     | 4     | 3     | 3     | 2     |
| Hexadezimal        | 0x34  | 0x30  | 0x2C  | 0x28  | 0x24  | 0x20  | 0x1B  | 0x15  | 0x10  | 0x0A  | 0x09  | 0x08  | 0x07  | 0x06  | 0x05  | 0x05  | 0x04  | 0x04  | 0x03  | 0x03  | 0x02  |
| Hexadezimal        | 34h   | 30h   | 2Ch   | 28h   | 24h   | 20h   | 1Bh   | 15h   | 10h   | 0Ah   | 09h   | 08h   | 07h   | 06h   | 05h   | 05h   | 04h   | 04h   | 03h   | 03h   | 02h   |
| Sekunden           | 0.887 | 0.820 | 0.753 | 0.686 | 0.619 | 0.552 | 0.469 | 0.368 | 0.285 | 0.184 | 0.167 | 0.151 | 0.134 | 0.117 | 0.100 | 0.100 | 0.084 | 0.084 | 0.067 | 0.067 | 0.050 |

Diese Tabelle befindet sich bei Byte `1B06h` in der ROM; `XXXh` steht für eine
hexadezimale Zahl `XXX`. Eine andere Schreibweise wäre `0xXXX`.
Jeder Eintrag ist um eins
kleiner als die tatsächliche Anzahl der Frames. So wird z. B. bei Level `1` `49`
(= `31h`) Frames als `30h` gespeichert.[^harddrop]

https://github.com/alexsteb/tetris_disassembly/blob/b4bbceb3cc086121ab4fe9bf4dad6752fe956ec0/main.asm#L4558-L4559

https://github.com/osnr/tetris/blob/4ca2f8bf3013a13a4c54d59ee03c929036045f93/tetris.asm#L3845-L3846

### Soft und Hard Drop

Ein **Soft Drop** ist eine Bewegung, bei dem ein Tetromino seine Abwärtsbewegung
beschleunigt. Dieser Zug bringt mehr Punkte, als den Tetromino von selbst fallen
zu lassen, aber weniger als ein Hard Drop.[^fandom]

Bei einem **Hard Drop** erreicht ein Tetromino sofort seine endgültige Position.

### Punkteverteilung / Scoring

| Level | Punkte für 1 Zeile | für 2 Zeilen   | für 3 Zeilen   | für 4 Zeilen    |
| ----- | ------------------ | -------------- | -------------- | --------------- |
| 0     | 40                 | 100            | 300            | 1200            |
| 1     | 80                 | 200            | 600            | 2400            |
| 2     | 120                | 300            | 900            | 3600            |
| ...   |                    |                |                |                 |
| 9     | 400                | 1000           | 3000           | 12000           |
| n     | 40 \* (n + 1)      | 100 \* (n + 1) | 300 \* (n + 1) | 1200 \* (n + 1) |

Neben der Bepunktung für abgebaute vollständige Zeilen, gibt es Punkte für durch
Soft-Drop platzierte Tetrominos. Die Punkteanzahl entspricht dabei der
Zeilenanzahl der kontinuierlichen, d. h. nicht unterbrochenen
Soft-Drop-Bewegung.[^harddrop_scoring] [^gamespot]

[^harddrop_scoring]: https://harddrop.com/wiki/Scoring
[^gamespot]: https://gamefaqs.gamespot.com/gameboy/585960-tetris/faqs/8483


### Animation bei getilgten Zeilen

Folgendes Bild zeigt eine im Programm Gimp geöffnete animierte PNG-Datei. In
dieser Animation ist zu sehen, wie eine Zeile getilgt wird.

![](https://raw.githubusercontent.com/engine-pi/tetris/main/misc/graphics/cleared-row/Screenshot-Gimp_animated-cleared-row.png)

1. ![](https://raw.githubusercontent.com/engine-pi/tetris/main/misc/graphics/cleared-row/01_row-visible.png) zu tilgende Zeile ist sichtbar
2. ![](https://raw.githubusercontent.com/engine-pi/tetris/main/misc/graphics/cleared-row/02_gray-overlay.png) graue Überblendung
3. ![](https://raw.githubusercontent.com/engine-pi/tetris/main/misc/graphics/cleared-row/03_row-visible.png) zu tilgende Zeile ist sichtbar
4. ![](https://raw.githubusercontent.com/engine-pi/tetris/main/misc/graphics/cleared-row/04_gray-overlay.png) graue Überblendung
5. ![](https://raw.githubusercontent.com/engine-pi/tetris/main/misc/graphics/cleared-row/05_row-visible.png) zu tilgende Zeile ist sichtbar
6. ![](https://raw.githubusercontent.com/engine-pi/tetris/main/misc/graphics/cleared-row/06_gray-overlay.png) graue Überblendung
7. ![](https://raw.githubusercontent.com/engine-pi/tetris/main/misc/graphics/cleared-row/07_row-visible.png) zu tilgende Zeile ist sichtbar
8. ![](https://raw.githubusercontent.com/engine-pi/tetris/main/misc/graphics/cleared-row/08_cleared.png) Die Zeile ist getilgt.
9. ![](https://raw.githubusercontent.com/engine-pi/tetris/main/misc/graphics/cleared-row/09_finished.png) Die Zeilen oberhalb sind nach unten gerutscht.

### Sound

[Game Boy Sound System](https://en.wikipedia.org/wiki/Game_Boy_Sound_System)

GBS-Dateien: [ocremix.org](https://ocremix.org/chip/265) [zophar.net](https://www.zophar.net/music/gameboy-gbs/tetris)

[gbsplay](https://github.com/mmitch/gbsplay)

https://github.com/niuhuan/gbc-swing/blob/master/src/main/java/gbc/Speaker.java

https://github.com/trekawek/coffee-gb

### Bildschirme (`scenes`)

`CopyrightScene`

![CopyrightScene](https://raw.githubusercontent.com/Josef-Friedrich/tetris/main/misc/graphics/screenshots/CopyrightScreen.png)

`TitleScene`

![TitleScene](https://raw.githubusercontent.com/Josef-Friedrich/tetris/main/misc/graphics/screenshots/TitleScreen.png)

`MainMenuScene`

![MainMenuScene](https://raw.githubusercontent.com/Josef-Friedrich/tetris/main/misc/graphics/screenshots/MainMenuScreen.png)

`LevelSelectScene`

![LevelSelectScene](https://raw.githubusercontent.com/Josef-Friedrich/tetris/main/misc/graphics/screenshots/LevelSelectScreen.png)

`IngameScene`

![IngameScene](https://raw.githubusercontent.com/Josef-Friedrich/tetris/main/misc/graphics/screenshots/IngameScreen.png)

Das Spielfeld ist `8x18` groß. Der linke Rand ist `2` und der rechte
Rand `10` Blöcke breit.

`GameOverScene`

![GameOverScene](https://raw.githubusercontent.com/Josef-Friedrich/tetris/main/misc/graphics/screenshots/GameOverScreen.png)

`RussianDancersScene`

![RussianDancersScene](https://raw.githubusercontent.com/Josef-Friedrich/tetris/main/misc/graphics/screenshots/RussianDancersScreen.gif)

### Emulation

Es gibt eine Vielzahl sogenannter Emulatoren, mit denen der Gameboy
simuliert werden kann.

#### mGBA

Der Emulator
[mGBA](https://mgba.io/downloads.html) (mini Game Boy Advance) läuft auf
allen gängigen Desktop-Betriebssystemen und auf einigen Spielekonsolen.
_mGBA_ ist freie Software und wird unter der [Mozilla Public License
2.0](https://github.com/mgba-emu/mgba/blob/master/LICENSE)
veröffentlicht. Der Quellcode ist über
[Github](https://github.com/mgba-emu/mgba) abrufbar.

Um das Spiel in einem Emulator laufen zu lassen, ist eine sogenanntes
ROM notwendig, z. B. von
[emulatorgames.net](https://www.emulatorgames.net/roms/gameboy/tetris-jue-v11/)
oder aus diesem
[Repository](https://github.com/Josef-Friedrich/tetris/raw/main/misc/Tetris-ROM.gb).

Die Tastenkürzel wichtigsten Tastenkürzel des mGBA sind:

- A: X
- B: Z
- L: A
- R: S
- Start: Enter
- Select: Backspace

### Weiterführende Informationen

#### Allgemeine Informationen über das Spiel

- [englische Wikipedia](<https://en.wikipedia.org/wiki/Tetris_(Game_Boy_video_game)>)
- [harddrop.com (Tetris Wiki created by Tetris fans for Tetris fans)](https://harddrop.com/wiki/Tetris_%28Game_Boy%29)
- [Video Game Music Preservation Foundation](<http://www.vgmpf.com/Wiki/index.php?title=Tetris_(GB)>)

#### Youtube-Videos

- [Playthrough 16 Minuten, Gameboy-Display wurde abgefilmt](https://www.youtube.com/watch?v=BQwohHgrk2s)
- [Longplay 52 Minuten (schwarz-weiß Emulator)](https://www.youtube.com/watch?v=VNbo1AGqKrI)
- [short](https://www.youtube.com/shorts/30vVpKAMu6g)

#### Klone

- [canvas_tetris (javascript)](https://github.com/andyp123/canvas_tetris)
- [js-tetris](https://github.com/az23/js-tetris) (eventuell nicht fertig gestellt)

## Java

### Java-Entwicklungsumgebung: IDE - Integrated Development Environment (integrierte Entwicklungsumgebung)

Eine integrierte Entwicklungsumgebung (IDE, von englisch integrated development environment) ist
eine Sammlung von Computerprogrammen, mit denen die Aufgaben der Softwareentwicklung
möglichst ohne Medienbrüche bearbeitet werden können.

<small>Quelle: [wikipedia.org](https://de.wikipedia.org/wiki/Integrierte_Entwicklungsumgebung)</small>

#### Bekannte IDEs

- [BlueJ](https://www.bluej.org/): Reduzierte IDE für pädagogische Zwecke
- [Visual Studio Code](https://code.visualstudio.com): von Microsoft entwickelt, für alle Sprachen einsetzbar, wegen vieler Erweiterungen, läuft auf Google Chrome
- [Eclipse](https://www.eclipse.org/downloads)
- [IntelliJ IDEA](https://www.jetbrains.com/de-de/idea): auf Java spezialisiert

Wir setzen die [Community Edition von IntelliJ](https://www.jetbrains.com/de-de/idea/download/other.html) ein.

### Game-Engine

Als Game-Engine kommt die [Engine Alpha](https://engine-alpha.org) zum Einsatz.
Der [Quell-Code](https://github.com/engine-alpha/engine-alpha) ist
auf Github gehostet. Die Engine Alpha wurde und wird von
[Michael Andonie](https://github.com/andonie)
und [Niklas Keller](https://github.com/kelunik) entwickelt.

Wir setzen jedoch einen Fork der Engine Alpha ein,
genannt [Engine Omega](https://github.com/Josef-Friedrich/engine-omega).
Im Gegensatz zur originalen Engine ist die
[Engine Omega](https://central.sonatype.com/artifact/de.pirckheimer_gymnasium.engine_pi/engine-omega)
über das wichtigste Repository für Java-Projekte das sogenannte
[Maven Central Repository](https://central.sonatype.com) abrufbar.

In der Projekt-Datei `pom.xml` ist die Engine Omega als
Abhängigkeit (`dependency`) hinterlegt.

```xml
<project>
  <dependencies>
    <dependency>
      <groupId>de.pirckheimer_gymnasium.engine_pi</groupId>
      <artifactId>engine-omega</artifactId>
      <version>0.2.0</version>
    </dependency>
  </dependencies>
</project>
```

### Java-Paketnamen

Um Pakete mit gleichem Namen zu vermeiden, haben sich in der Java-Welt folgende
Konvention für Paketnamen herausgebildet:

- Paketnamen bestehen nur aus Kleinbuchstaben und Unterstrichen `_` (um sie von Klassen zu unterscheiden).
- Paketnamen sind durch Punkte getrennt.
- Der Anfang des Paketnamens wird durch die Organisation bestimmt, die sie erstellt.

Um den Paketnamen auf der Grundlage einer Organisation zu bestimmen, wird die URL der Organisation umgedreht.
Beispielsweise wird aus der URL

    https://pirckheimer-gymnasium.de/tetris

der Paketname:

    de.pirckheimer_gymnasium.tetris

<small>Quelle: [baeldung.com](https://www.baeldung.com/java-packages#1-naming-conventions)</small>

### Importe von Java-Klassen aus Paketen

Java verfügt über unzählige vorgefertigte Klassen und Schnittstellen. Thematisch zusammengehörende Klassen und
Schnittstellen werden zu einem Paket (_package_) zusammengefasst. Die so entstehende Java-Bibliothek ist riesig und
enthält tausende verschiedener Klassen mit unterschiedlichsten Methoden. Um sich einer dieser Klassen bedienen
zu können, muss man sie in das gewünschte Projekt importieren. In Java funktioniert das mit dem Schlüsselwort
`import`.

**Syntax**

`import <paketname>.<klassenname>;` Importiert nur die gewünschte Klasse des angesprochenen Paketes.<br>
`import <paketname>.*;` Importiert sämtliche Klassen des angesprochenen Paketes.

**Beispiel**

`import java.util.Random;` Importiert die Klasse Random des Paketes java.util.<br>
`import java.util.*;` Importiert das vollständige Paket java.util.

<small>Quelle: Klett, Informatik 2, 2021, Seite 275</small>

### `super`-Schlüsselwort

Das Java-Schlüsselwort `super` hat drei explizite Verwendungsbereiche.

1. Zugriff auf die Attribute der Elternklasse, wenn die Kindklasse ebenfalls Attribute mit demselben Namen hat.
2. Aufruf des Konstruktoren der Elternklasse in der Kindklasse.
3. Aufruf der Methoden der Elternklasse in der Kindklasse, wenn die Kindklasse Methoden überschrieben hat.

<small>Quelle: [codegym.cc](https://codegym.cc/de/groups/posts/super-schlsselwort-in-java)</small>

### Überladen von Methoden

Überladen bedeutet, dass derselbe Methodenname mehrfach in einer Klasse verwendet werden kann.
Damit das Überladen möglich ist, muss wenigstens eine der folgenden Vorraussetzungen erfüllt sein:

1. Der Datentyp mindestens eines Übergabeparameters ist anders als in den übrigen
   gleichnamen Methoden.
2. Die Anzahl der Übergabeparameter ist unterschiedlich.

<small>Quelle: [Java-Tutorial.org ](https://www.java-tutorial.org/ueberladen_von_methoden.html)</small>

### Lambda-Ausdrücken

Mit Lambda-Ausdrücken kann man sich viel Schreibarbeit sparen. Klassen, die eine
sogenannten Funktionale Schnittstelle (Functional Interface) implementieren,
d. h. ein Interface mit genau einer abstrakten Methoden, können auch als
Lambda-Ausdruck formuliert werden.

Klasse, die das Interface/Schnittstelle `Runnable` implementiert.

```java
class MyRunnable implements Runnable
{
    public void run()
    {
        startTitleScene();
    }
}

delay(3, new MyRunnable());
```

Als anonyme Klasse

```java
delay(3, new Runnable()
{
    public void run()
    {
        startTitleScene();
    }
});
```

Als Lambda-Ausdruck (Name stammt vom [Lambda-Kalkül](https://de.wikipedia.org/wiki/Lambda-Kalk%C3%BCl) ab)

```java
delay(3, () -> startTitleScene());
```

### Entwurfsmuster [Schablonenmethode](https://de.wikipedia.org/wiki/Schablonenmethode)

Beim Schablonenmethoden-Entwurfsmuster wird in einer abstrakten Klasse das Skelett eines Algorithmus definiert. Die konkrete Ausformung der einzelnen Schritte wird an Unterklassen delegiert. Dadurch besteht die Möglichkeit, einzelne Schritte des Algorithmus zu verändern oder zu überschreiben, ohne dass die zu Grunde liegende Struktur des Algorithmus modifiziert werden muss. Die Schablonenmethode (engl. template method) ruft abstrakte Methoden auf, die erst in den Unterklassen definiert werden. Diese Methoden werden auch als Einschubmethoden bezeichnet.

Quelle: [Wikipedia](https://de.wikipedia.org/wiki/Schablonenmethode)

### foreach-Schleife

for-each ist eine Art for-Schleife, die du verwendest, wenn du alle Elemente eines Arrays oder einer Collection verarbeiten musst. Allerdings wird der Ausdruck for-each in dieser Schleife eigentlich nicht verwendet. Die Syntax lautet wie folgt:

```java
for (type itVar : array)
{
    // Operations
}
```

Wobei type der Typ der Iterator-Variable ist (der dem Datentyp der Elemente im Array entspricht!), itVar ihr Name und array ein Array (andere Datenstrukturen sind auch erlaubt, z. B. eine Collection, wie ArrayList), d. h. das Objekt, auf dem die Schleife ausgeführt wird. Wie du siehst, wird bei diesem Konstrukt kein Zähler verwendet: Die Iterator-Variable iteriert einfach über die Elemente des Arrays oder der Collection. Wenn eine solche Schleife ausgeführt wird, wird der Iterator-Variable nacheinander der Wert jedes Elements des Arrays oder der Collection zugewiesen, woraufhin der angegebene Anweisungsblock (oder die Anweisung) ausgeführt wird.

Quelle: [codegym.cc](https://codegym.cc/de/groups/posts/1011-die-for-each-schleife-in-java)

## Fortschritt

### Vorgegebene Klassen

- `Tetris.java` (teilweise, noch zu ergänzen)
- `Image.java`
- `HelloWorldScene.java`

Komplettes `color` package

- `color/ColorSchema.java`
- `color/CustomColorSchema.java`
- `color/GrayColorSchema.java`
- `color/GreenColorSchema.java`

### 1. Sitzung

`scenes.SimpleScene.java`

```java
package de.pirckheimer_gymnasium.tetris.scenes;

import de.pirckheimer_gymnasium.tetris.Image;
import de.pirckheimer_gymnasium.engine_pi.Game;
import de.pirckheimer_gymnasium.engine_pi.Scene;

public class SimpleScene extends Scene
{
    public SimpleScene()
    {
        Image image = new Image("fullscreen/title.png");
        getCamera().setFocus(image);
        add(image);
    }

    public static void main(String[] args)
    {
        Scene scene = new SimpleScene();
        Game.start(20 * 8 * 4, 18 * 8 * 4, scene);
    }
}
```

`scenes.BaseScene.java`

```java
package de.pirckheimer_gymnasium.tetris.scenes;

import de.pirckheimer_gymnasium.tetris.Image;
import de.pirckheimer_gymnasium.engine_pi.Scene;

public class BaseScene extends Scene
{
    Image background;

    public BaseScene(String imageFilename)
    {
        background = new Image("fullscreen/" + imageFilename + ".png");
        getCamera().setFocus(background);
        add(background);
    }
}
```

`scenes.IngameScene.java`

Java-Thema: `super()`: Aufruf des Konstruktors der Oberklasse

```java
package de.pirckheimer_gymnasium.tetris.scenes;

import de.pirckheimer_gymnasium.engine_pi.Game;
import de.pirckheimer_gymnasium.engine_pi.Scene;

public class IngameScene extends BaseScene
{
    public IngameScene()
    {
        super("ingame");
    }

    public static void main(String[] args)
    {
        Scene scene = new IngameScene();
        Game.start(20 * 8 * 4, 18 * 8 * 4, scene);
    }
}
```

`scenes.TitleScene.java`

```java
package de.pirckheimer_gymnasium.tetris.scenes;

import de.pirckheimer_gymnasium.engine_pi.Game;
import de.pirckheimer_gymnasium.engine_pi.Scene;

public class TitleScene extends BaseScene
{
    public TitleScene()
    {
        super("title");
    }

    public static void main(String[] args)
    {
        Scene scene = new TitleScene();
        Game.start(20 * 8 * 4, 18 * 8 * 4, scene);
    }
}
```

### 2. Sitzung

- Löschen des `blocks`-Pakets.
- Implementierung der statischen `start()`-Methoden in der `Tetris`-Klasse.

```java
    public static void start(Scene scene, boolean debug)
    {
        scene.getCamera().setZoom(BLOCK_SIZE * SCALE);
        Game.setDebug(debug);
        if (!Game.isRunning())
        {
            Game.start(WIDTH * BLOCK_SIZE * SCALE, HEIGHT * BLOCK_SIZE * SCALE,
                    scene);
        }
        else
        {
            Game.transitionToScene(scene);
        }
    }

    public static void start(Scene scene)
    {
        start(scene, false);
    }

    public static void start()
    {
        start(new TitleScene());
    }

    public static void main(String[] args)
    {
        start();
    }
```

- Löschen der Klassen `HelloWorldScene` und `SimpleScene`.
- Erweiterung der `*Scene`s:
  - Tastensteuerung: siehe -> https://engine-alpha.org/wiki/v4.x/User_Input
  - Zeitsteuerung

```java
package de.pirckheimer_gymnasium.tetris.scenes;

import de.pirckheimer_gymnasium.tetris.Tetris;
import de.pirckheimer_gymnasium.engine_pi.event.KeyListener;

import java.awt.event.KeyEvent;

public class CopyrightScene extends BaseScene implements KeyListener
{
    public CopyrightScene()
    {
        super("copyright");
        // Lambda-Ausdruck = anonyme Funktion () -> {}
        delay(1, () -> startTitleScene());
    }

    public void startTitleScene()
    {
        Tetris.start(new TitleScene());
    }

    public void onKeyDown(KeyEvent e)
    {
        startTitleScene();
    }

    public static void main(String[] args)
    {
        Tetris.start(new CopyrightScene(), true);
    }
}
```

- Initialisierung der `Block`-Klasse

```java
package de.pirckheimer_gymnasium.tetris.tetrominos;

import de.pirckheimer_gymnasium.tetris.Image;

public class Block
{
    private Image image;

    public Block(String name)
    {
        image = new Image("blocks/" + name + ".png");
    }
}
```

### 3. Sitzung

- Löschen des Klassenattributes `Tetris.DEBUG`
- Fertigstellung der `Block`-Klasse

![UML-Klassendiagramm der Klasse Block](https://raw.githubusercontent.com/Josef-Friedrich/tetris/main/misc/UML/Class-Block.drawio.svg)

```java
package de.pirckheimer_gymnasium.tetris.tetrominos;

import de.pirckheimer_gymnasium.tetris.Image;
import de.pirckheimer_gymnasium.engine_pi.Scene;

public class Block
{
    private Scene scene;

    private Image image;

    private int x;

    private int y;

    public Block(Scene scene, String imageName, int x, int y)
    {
        this.scene = scene;
        image = new Image("blocks/" + imageName + ".png");
        image.setPosition(x, y);
        this.scene.add(image);
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void moveBy(int dX, int dY)
    {
        image.moveBy(dX, dY);
        x = x + dX;
        y = y + dY;
    }

    public void moveLeft()
    {
        moveBy(-1, 0);
    }

    public void moveRight()
    {
        moveBy(1, 0);
    }

    public void moveDown()
    {
        moveBy(0, -1);
    }

    public void remove()
    {
        scene.remove(image);
    }
}
```

- Initialisierung der `Tetromino`-Klasse

```java
package de.pirckheimer_gymnasium.tetris.tetrominos;

import de.pirckheimer_gymnasium.engine_pi.Scene;

public class Tetromino
{
    private Scene scene;

    private int x;

    private int y;

    public Tetromino(Scene scene, int x, int y)
    {
        this.scene = scene;
        this.x = x;
        this.y = y;
    }
}
```

Initialisierung aller Tetromino-Unterkassen, z. B. Klasse `I`

```java
public class I extends Tetromino
{
    public I(Scene scene, int x, int y)
    {
        super(scene, x, y);
    }
}
```

### 4. Sitzung

- Update der Engine Omega auf 0.4.0

```diff
--- a/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/I.java
+++ b/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/I.java
@@ -6,6 +6,32 @@ public class I extends Tetromino
 {
     public I(Scene scene, int x, int y)
     {
-        super(scene, x, y);
+        super(scene, "I", x, y);
+        addBlock(0, x, y);
+        addBlock(1, x - 1, y);
+        addBlock(2, x + 1, y);
+        addBlock(3, x + 2, y);
+    }
+
+    protected void doRotation()
+    {
+        switch (rotation)
+        {
+        case 0:
+        case 2:
+            moveBlock(0, 0, 0);
+            moveBlock(1, -1, 1);
+            moveBlock(2, 1, -1);
+            moveBlock(3, 2, -2);
+            break;
+
+        case 1:
+        case 3:
+            moveBlock(0, 0, 0);
+            moveBlock(1, 1, -1);
+            moveBlock(2, -1, 1);
+            moveBlock(3, -2, 2);
+            break;
+        }
     }
 }
diff --git a/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/J.java b/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/J.java
index 95b93d9..97188e5 100644
--- a/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/J.java
+++ b/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/J.java
@@ -6,6 +6,14 @@ public class J extends Tetromino
 {
     public J(Scene scene, int x, int y)
     {
-        super(scene, x, y);
+        super(scene, "J", x, y);
+        addBlock(0, x, y);
+        addBlock(1, x - 1, y);
+        addBlock(2, x + 1, y);
+        addBlock(3, x + 1, y - 1);
+    }
+
+    protected void doRotation()
+    {
     }
 }
diff --git a/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/L.java b/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/L.java
index a864ec3..d7b4b74 100644
--- a/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/L.java
+++ b/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/L.java
@@ -6,6 +6,14 @@ public class L extends Tetromino
 {
     public L(Scene scene, int x, int y)
     {
-        super(scene, x, y);
+        super(scene, "L", x, y);
+        addBlock(0, x, y);
+        addBlock(1, x - 1, y);
+        addBlock(2, x + 1, y);
+        addBlock(3, x - 1, y - 1);
+    }
+
+    protected void doRotation()
+    {
     }
 }
diff --git a/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/O.java b/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/O.java
index 38309f9..7fc6039 100644
--- a/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/O.java
+++ b/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/O.java
@@ -6,6 +6,14 @@ public class O extends Tetromino
 {
     public O(Scene scene, int x, int y)
     {
-        super(scene, x, y);
+        super(scene, "O", x, y);
+        addBlock(0, x, y);
+        addBlock(1, x + 1, y);
+        addBlock(2, x, y - 1);
+        addBlock(3, x + 1, y - 1);
+    }
+
+    protected void doRotation()
+    {
     }
 }
diff --git a/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/S.java b/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/S.java
index b49e4e8..1f3e9c9 100644
--- a/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/S.java
+++ b/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/S.java
@@ -6,6 +6,14 @@ public class S extends Tetromino
 {
     public S(Scene scene, int x, int y)
     {
-        super(scene, x, y);
+        super(scene, "S", x, y);
+        addBlock(0, x, y);
+        addBlock(1, x + 1, y);
+        addBlock(2, x - 1, y - 1);
+        addBlock(3, x, y - 1);
+    }
+
+    protected void doRotation()
+    {
     }
 }
diff --git a/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/T.java b/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/T.java
index d76e780..0097931 100644
--- a/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/T.java
+++ b/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/T.java
@@ -6,6 +6,14 @@ public class T extends Tetromino
 {
     public T(Scene scene, int x, int y)
     {
-        super(scene, x, y);
+        super(scene, "T", x, y);
+        addBlock(0, x, y);
+        addBlock(1, x - 1, y);
+        addBlock(2, x + 1, y);
+        addBlock(3, x, y - 1);
+    }
+
+    protected void doRotation()
+    {
     }
 }
diff --git a/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/Tetromino.java b/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/Tetromino.java
index 1de2c6b..9f0a94c 100644
--- a/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/Tetromino.java
+++ b/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/Tetromino.java
@@ -2,18 +2,93 @@ package de.pirckheimer_gymnasium.tetris.tetrominos;

 import de.pirckheimer_gymnasium.engine_pi.Scene;

-public class Tetromino
+public abstract class Tetromino
 {
     private Scene scene;

+    private String name;
+
     private int x;

     private int y;

-    public Tetromino(Scene scene, int x, int y)
+    private Block[] blocks;
+
+    public int rotation;
+
+    public Tetromino(Scene scene, String name, int x, int y)
     {
         this.scene = scene;
+        this.name = name;
         this.x = x;
         this.y = y;
+        blocks = new Block[4];
+    }
+
+    protected void addBlock(int index, int x, int y)
+    {
+        blocks[index] = new Block(scene, name, x, y);
+    }
+
+    protected void moveBlock(int index, int dX, int dY)
+    {
+        blocks[index].moveBy(dX, dY);
+    }
+
+    public void moveLeft()
+    {
+    }
+
+    public void moveRight()
+    {
+    }
+
+    public void moveDown()
+    {
+    }
+
+    protected abstract void doRotation();
+
+    public void rotate()
+    {
+        if (rotation > 2)
+        {
+            rotation = 0;
+        }
+        else
+        {
+            rotation++;
+        }
+        doRotation();
+    }
+
+    public static Tetromino create(Scene scene, String name, int x, int y)
+    {
+        switch (name)
+        {
+        case "L":
+            return new L(scene, x, y);
+
+        case "J":
+            return new J(scene, x, y);
+
+        case "I":
+            return new I(scene, x, y);
+
+        case "O":
+            return new O(scene, x, y);
+
+        case "Z":
+            return new Z(scene, x, y);
+
+        case "S":
+            return new S(scene, x, y);
+
+        case "T":
+            return new T(scene, x, y);
+
+        default:
+            return new L(scene, x, y);
+        }
     }
 }
diff --git a/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/Z.java b/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/Z.java
index bfa53ba..1e3f517 100644
--- a/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/Z.java
+++ b/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/Z.java
@@ -6,6 +6,14 @@ public class Z extends Tetromino
 {
     public Z(Scene scene, int x, int y)
     {
-        super(scene, x, y);
+        super(scene, "Z", x, y);
+        addBlock(0, x, y);
+        addBlock(1, x - 1, y);
+        addBlock(2, x, y - 1);
+        addBlock(3, x + 1, y - 1);
+    }
+
+    protected void doRotation()
+    {
     }
 }
```

### 5. Sitzung

- Implementierung aller Rotationen

Tetromino `L`:

```java
    protected void doRotation()
    {
        switch (rotation)
        {
        // 0 -> 1
        case 1:
            moveBlock(1, 1, 1);
            moveBlock(2, -1, -1);
            moveBlock(3, 0, 2);
            break;

        // 1 -> 2
        case 2:
            moveBlock(1, 1, -1);
            moveBlock(2, -1, 1);
            moveBlock(3, 2, 0);
            break;

        // 2 -> 3
        case 3:
            moveBlock(1, -1, -1);
            moveBlock(2, 1, 1);
            moveBlock(3, 0, -2);
            break;

        // 3 -> 0
        case 0:
            moveBlock(1, -1, 1);
            moveBlock(2, 1, -1);
            moveBlock(3, -2, 0);
            break;
        }
    }
```

Tetromino `J`:

```java
    protected void doRotation()
    {
        switch (rotation)
        {
        // 0 -> 1
        case 1:
            moveBlock(1, 1, 1);
            moveBlock(2, -1, -1);
            moveBlock(3, -2, 0);
            break;

        // 1 -> 2
        case 2:
            moveBlock(1, 1, -1);
            moveBlock(2, -1, 1);
            moveBlock(3, 0, 2);
            break;

        // 2 -> 3
        case 3:
            moveBlock(1, -1, -1);
            moveBlock(2, 1, 1);
            moveBlock(3, 2, 0);
            break;

        // 3 -> 0
        case 0:
            moveBlock(1, -1, 1);
            moveBlock(2, 1, -1);
            moveBlock(3, 0, -2);
            break;
        }
    }

```

Tetromino `I`:

```java
    protected void doRotation()
    {
        switch (rotation)
        {
        // 3 -> 0
        // 1 -> 2
        case 0:
        case 2:
            moveBlock(0, 0, 0);
            moveBlock(1, -1, 1);
            moveBlock(2, 1, -1);
            moveBlock(3, 2, -2);
            break;

        // 0 -> 1
        // 2 -> 3
        case 1:
        case 3:
            moveBlock(0, 0, 0);
            moveBlock(1, 1, -1);
            moveBlock(2, -1, 1);
            moveBlock(3, -2, 2);
            break;
        }
    }

```

Tetromino `O`:

```java
    protected void doRotation()
    {
        // keine Rotation notwendig bei O
    }
```

Tetromino `Z`:

```java
    protected void doRotation()
    {
        switch (rotation)
        {
        // 0 -> 1
        // 2 -> 3
        case 1:
        case 3:
            moveBlock(2, 0, 2);
            moveBlock(3, -2, 0);
            break;

        // 3 -> 0
        // 1 -> 2
        case 0:
        case 2:
            moveBlock(2, 0, -2);
            moveBlock(3, 2, 0);
            break;
        }
    }
```

Tetromino `S`:

```java
    protected void doRotation()
    {
        switch (rotation)
        {
        // 0 -> 1
        // 2 -> 3
        case 1:
        case 3:
            moveBlock(1, -2, 0);
            moveBlock(2, 0, 2);
            break;

        // 3 -> 0
        // 1 -> 2
        case 0:
        case 2:
            moveBlock(1, 2, 0);
            moveBlock(2, 0, -2);
            break;
        }
    }
```

Tetromino `T`:

```java
    protected void doRotation()
    {
        switch (rotation)
        {
        // 0 -> 1
        case 1:
            moveBlock(2, -1, 1);
            break;

        // 1 -> 2
        case 2:
            moveBlock(3, 1, 1);
            break;

        // 2 -> 3
        case 3:
            moveBlock(1, 1, -1);
            break;

        // 3 -> 0
        case 0:
            moveBlock(1, -1, 1);
            moveBlock(2, 1, -1);
            moveBlock(3, -1, -1);
            break;
        }
    }
```

### 6. Sitzung

- Implementierung der Methoden `moveLeft()`, `moveRight()` und `moveDown()`

Tetromino.java

```java
    public void moveLeft()
    {
        for (Block blocks : blocks)
        {
            blocks.moveLeft();
        }
    }

    public void moveRight()
    {
        for (Block block : blocks)
        {
            block.moveRight();
        }
    }

    public void moveDown()
    {
        for (Block block : blocks)
        {
            block.moveDown();
        }
    }
```

- Implementierung der Klasse Grid

```java
public class Grid
{
    private Block[][] grid;

    public Grid(int width, int height)
    {
        grid = new Block[width][height];
    }

    public int getWidth()
    {
        return grid.length;
    }

    public int getHeight()
    {
        return grid[0].length;
    }

    public void addBlock(Block block)
    {
        grid[block.getX()][block.getY()] = block;
    }

    public void removeBlock(Block block)
    {
        grid[block.getX()][block.getY()] = null;
    }

    public boolean isTaken(int x, int y)
    {
        return x < 0 || x >= getWidth() || y >= getHeight() || y < 0
                || grid[x][y] != null;
    }
}
```

### 7. Sitzung

?

### 8. Sitzung

```diff
diff --git a/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/Tetromino.java b/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/Tetromino.java
index 4750fa5..ec6cdad 100644
--- a/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/Tetromino.java
+++ b/src/main/java/de/pirckheimer_gymnasium/tetris/tetrominos/Tetromino.java
@@ -32,6 +32,16 @@ public abstract class Tetromino
         blocks = new Block[4];
     }

+    public int getX()
+    {
+        return x;
+    }
+
+    public int getY()
+    {
+        return y;
+    }
+
     protected void addBlock(int index, int x, int y)
     {
         Block block;
@@ -180,10 +190,29 @@ public abstract class Tetromino
         return true;
     }

+    private boolean checkRotation()
+    {
+        for (int x = getX() - 1; x <= getX() + 1; x++)
+        {
+            for (int y = getY() - 1; y <= getY() + 1; y++)
+            {
+                if (isGridTaken(x, y))
+                {
+                    return false;
+                }
+            }
+        }
+        return true;
+    }
+
     protected abstract void doRotation();

-    public void rotate()
+    public boolean rotate()
     {
+        if (!checkRotation())
+        {
+            return false;
+        }
         if (rotation > 2)
         {
             rotation = 0;
@@ -192,7 +221,10 @@ public abstract class Tetromino
         {
             rotation++;
         }
+        removeBlocksFromGrid();
         doRotation();
+        addBlocksToGrid();
+        return true;
     }

     public void remove()
```

[^fandom]: https://tetris.fandom.com/wiki/Soft_Drop
[^gimp-green]: Ermittelt mit dem GIMP Color Picker mittels eines Bildschirmfotos des Videos https://www.youtube.com/watch?v=BQwohHgrk2s
[^harddrop]: https://harddrop.com/wiki/Tetris_(Game_Boy)
[^mgba-gray]: mGBA Emulator Settings / Gameboy / Game Boy palette / Grayscale Preset
[^nintendo]: https://www.nintendo.com/de-de/Spiele/Game-Boy/TETRIS--275924.html
[^strategywiki]: https://strategywiki.org/wiki/Tetris/Rotation_systems
[^wikipedia-green]: https://en.wikipedia.org/wiki/List_of_video_game_console_palettes#Game_Boy Original Game Boy Hex / Binary
