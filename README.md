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
eingestellt werden.[^nintendo.com]

<!-- ### Drehung

- O-Tetrominos verändern ihr Aussehen nicht, wenn sie sich drehen.
- I-Tetrominos have two rotations, favoring the lower half when horizontal, and the right half when vertical.
  J, L, and T pieces have four rotations centered around the middle square of the three square edge.
  While S and Z pieces have four rotations, they always favor the bottom and right sides of their rotation space (hence the "right handed" aspect of this rotation system.)

[^strategywiki.org] https://strategywiki.org/wiki/Tetris/Rotation_systems
https://laroldsjubilantjunkyard.com/tutorial/tetris/ -->

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

![Blueprint](https://raw.githubusercontent.com/Josef-Friedrich/tetris/main/misc/Start-Positions.svg)

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
[Engine Omega](https://central.sonatype.com/artifact/rocks.friedrich.engine_omega/engine-omega)
über das wichtigste Repository für Java-Projekte das sogenannte
[Maven Central Repository](https://central.sonatype.com) abrufbar.

In der Projekt-Datei `pom.xml` ist die Engine Omega als
Abhängigkeit (`dependency`) hinterlegt.

```xml
<project>
  <dependencies>
    <dependency>
      <groupId>rocks.friedrich.engine_omega</groupId>
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

[^nintendo.com]: https://www.nintendo.com/de-de/Spiele/Game-Boy/TETRIS--275924.html
[^gimp-green]: Ermittelt mit dem GIMP Color Picker mittels eines Bildschirmfotos des Videos https://www.youtube.com/watch?v=BQwohHgrk2s
[^strategywiki.org]: https://strategywiki.org/wiki/Tetris/Rotation_systems
[^mgba-gray]: mGBA Emulator Settings / Gameboy / Game Boy palette / Grayscale Preset
[^wikipedia-green]: https://en.wikipedia.org/wiki/List_of_video_game_console_palettes#Game_Boy Original Game Boy Hex / Binary
