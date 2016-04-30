JAXB Beispiele
==============

Diese Repository enthält einige Beispiele zur Nutzung von JAXB. Damit alles
Funktioniert, müssen die Klassen der Reihenfolge nach ausgeführt werden.

1. Java Objekt in einer XML Datei speichern
2. Java Objekt aus einer XML Datei laden
3. Marshallen eines etwas komplizierteren Objekts
4. Unmarshallen eines etwas komplizierteren Objekts mit Schemavalidierung
5. Generieren von Java Klassen aus einer XSD
6. Generieren einer XSD aus Java Klassen

Um die Sourcen zu generieren folgendes ausführen:
````
mvn generate-resources
````

# Einführung #

JAXB v2.2.11 / April 2016

## Was macht JAXB? ##

* Umwandlung Object -> XML (marshallen)
* Unwandlung XML -> Objekt (unmarshallen)
* Gedacht für Objekte mit fester Struktur
    * wie "Customer", "PurchaseOrder", ...
    * z.B. nicht für JDBC ResultSets
        * Artikel unter "ResultSet XML" bei Google

## Ein paar Zahlen ##

* JAXB 1: JSR-31
* JAXB 2: JSR-222
* seit Java SE 6 in Java enthalten
    * es werden ab dieser Version also keine weiteren Abhängigkeiten benötigt
* auch als externe Abhängigkeit einbindbar
    * JAXB 1 erfordert Java 1.3 oder höher
    * JAXB 2 erfordert Java 5 oder höher

## Werkzeuge ##

* Binding Compiler „xjc“
    * generiert aus XSDs Java Klassen
* Schemagenerator „schemagen“
    * generiert aus Java Klassen Schema Dateien
* 3rd Party Tools
    * Maven Plugins
    * Ant Tasks
    * xjc Plugin

# Nutzung #

* Generelle Nutzung sehr einfach: [_01_ObjectToFile](src/main/java/de/tse/jaxb/examples/_01_ObjectToFile.java)
* Erstellung eines JAXBContext mit allen benötigten Klassen
    * Erstellung von Marshaller / Unmarshaller
    * Konfiguration des Marshallers:
        * Encoding (Default: UTF-8)
        * Formatting
        * Fragment

* Vorsicht bei der Nutzung
    * JAXBContext ist thread-safe
    * Marshaller / Unmarshaller sind NICHT thread-safe
        * Spring bietet Wrapper Klassen, die nach Bedarf
            neue Marshaller / Unmarshaller erstellen
    * Performanceeinbrüche beim Lesen vieler kleiner Dokumente möglich
        * Mögliche Lösung: Pooling
        * Marshaller / Unmarshaller dürfen nur nicht zur gleichen Zeit
            von unterschiedlichen Threads genutzt werden
    * BufferedOutputStream nicht nötig, JAXB kümmert sich selbst drum
    * Default Werte (@XmlElement(defaultValue=...)) beim Unmarshallen,
        ziehen nur wenn Elemente vorhanden, aber leer sind:
        `<abc/>` und `<abc></abc>`

## Hinweise zur Entwicklung ##

* Trennen von generierten Code und 'normalen' Code
    * generierte Code kann aus Versionsverwaltung ausgeschlossen werden
* Resource Folder: in Maven unter `<build><resources>`
* Source Folder: mit Plugin 'build-helper-maven-plugin'
* Zwei verschieden Maven Plugins um Code zu generieren
    * org.jvnet.jaxb2.maven2:maven-jaxb2-plugin
    * org.codehaus.mojo:jaxb2-maven-plugin
* das Beispiel nutzt das jaxb2-maven-plugin

## Möglichkeiten ##

Nur einige Möglichkeiten:

* CatalogResolver: Mapping von Namespaces auf lokale Resourcen
* Mapping von Namespaces in Java Packages
* Anpassung von Datentypen
    * in XSD: `<javaType name=“java.util.Date“ xmlType=“xs:date“ parseMethod=“...“, printMethod=“...“/>`
    * XmlJavaTypeAdapter
    * Java 8 Date and Time API wird nicht von Haus aus unterstützt
* Anpassungen im Schema möglich oder in separater Binding Datei

## Einschränkungen ##

* HashMaps können von JAXB geschrieben werden, nur nicht als Root
    Elemente
    * https://java.net/jira/browse/JAXB-223
* Bei schemagen keine Möglichkeit Facets (min, max, lenght, pattern)
    zu definieren

## Zyklische Abhängigkeiten ##

* Können von JAXB nicht aufgelöst werden -> Fehler

### Parent Child Beziehung ###

* Parent mit @XmlTransient annotieren
* `public void afterUnmarshal(Unmarshaller u, Object parent)`
    implementieren und den Parent setzen
    * wird von JAXB nach unmarshallen aufgerufen

### Many to Many Beziehung ###

* ID mit @XmlID annotieren
    * muss in XML eindeutig sein
* Referenz auf Objekte mit @XmlIDREF annotieren
* Objekt muss als "contained" gemarshallt werden, sonst gelangt es
    nicht in die XML

## Weiterentwicklung von Java Klassen ##

* Mögliche Kompatibiliätsprobleme bei Weiterentwicklung
* Unterscheidung
    * forward compatible (v1 kann lesen, was v2 geschrieben hat)
    * backward compatible (v2 kann lesen, was v1 geschrieben hat)
* Per Default ist JAXB „semi-compatible“
    * nicht bekannte Elemente und Attribute werden ignoriert
* Nicht annotierte Elemente einer Klasse werden nicht beachtet
* Wird zu einer Klasse eine Superklasse hinzugefügt, ist das
    * backward compatible
    * foward semi-compatible
* Wird von einer Klasse die Superklasse entfernt, ist das
    * foward compatible
    * backward semi-compatbile
* bleibt die XML Struktur gleich, sind die Namen der Klassen /
    Attribute egal
* Änderungen von Datentypen sind in der Regel nicht kompatibel
    * Integer zu String geht, aber int zu boolean z.B. nicht.

# JAXB Reference Implementation Features #

* Anpassungen sind nicht Teil der Spezifikation!
* Können sich ändern

## JAXB Anpassungen ##

* Definition des Zeichens für die Einrückung im XML
* Custom EscapeHandling
* JAXB Annotation Control: einge Accessor Implementierung möglich
* XML Declaration Control
    * wurde als `Marshaller.JAXB_FRAGMENT` in Spezifikation übernommen
    * erlaubt schreiben von Segmenten
        * keine Präambel (`<?xml...>`)
        * kein `startDocument` und `endDocument`
* Namespace Prefix Mapping

## xjc Anpassungen ##

* Anpassungen nur möglich, wenn im extension Modus
    * `-extension`
* SCD: Schema Component Designator
    * einfacheres verweisen auf Schema Elemente als mit XPath
* generierte Klassen können von definierter Klasse erben / Interface
    implementieren
* „simple & better binding mode“ (experimentell)
    * plural für sich wiederholende Elemente
    * ...

# Unterschiede JAXB 1 / JAXB 2 #

* JAXB 1 ist restriktiver als JAXB 2
* JAXB 1 generiert standardmäßig Interfaces + Implementierungen
* JAXB 1 lässt Objekte nur über ObjectFactory instanziieren
* JAXB 2 Binding ist rückwärtskompatibel zu JAXB 1
    * einiges was in JAXB 1 erlaubt ist, lässt JAXB 2 nicht mehr zu
        (betrifft Anpassung von Klassen)

# Links #
* [JAXB Doku](https://jaxb.java.net/2.2.11/docs/)
* JAXB adapters for Java 8 Date and Time API (JSR-310) types
  from [migesok](https://github.com/migesok/jaxb-java-time-adapters)
* [JAXB-2 Maven Plugin](http://www.mojohaus.org/jaxb2-maven-plugin/Documentation/v2.2/schemagen-mojo.html)
* [Basic Examples - XML Schema Generation](http://www.mojohaus.org/jaxb2-maven-plugin/Documentation/v2.2/example_schemagen_basic.html)
