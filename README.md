SoundStreamVisualizer
=====================

Proof of concept of a java application that visually display the sound stream from the default
input sound card. See [SoundStreamAnalyzer](https://github.com/DjDCH/SoundStreamAnalyzer) for
the finish result.

Requirements
------------

* Java SE Development Kit 7 (JDK7) installed
* Maven 3 installed

Compilation
-----------

First, install the Minim jar files into your local Maven repository:

    mvn install:install-file -Dfile=lib/minim.jar -DgroupId=ddf.minim -DartifactId=minim -Dversion=2.2.0 -Dpackaging=jar
    mvn install:install-file -Dfile=lib/jsminim.jar -DgroupId=ddf.minim.javasound -DartifactId=jsminim -Dversion=2.2.0 -Dpackaging=jar

Then, use Maven to build the runnable jar file:

    mvn clean package
