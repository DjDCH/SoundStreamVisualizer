SoundStreamVisualizer
=====================

Program that visually display the sound stream of your default input sound card.

## Install the Minim jars file into your local Maven repository

    mvn install:install-file -Dfile=lib/minim.jar -DgroupId=ddf.minim -DartifactId=minim -Dversion=2.2.0 -Dpackaging=jar
    mvn install:install-file -Dfile=lib/jsminim.jar -DgroupId=ddf.minim.javasound -DartifactId=jsminim -Dversion=2.2.0 -Dpackaging=jar
