----
# Welcome to GALA! #

----
## What is GALA? ##
GALA is a cryptocurrency to make the world a better place.

----
## Get it! ##

  - *dependencies*:
    - *general* - Java SE Development Kit 11.0.15 https://www.oracle.com/java/technologies/downloads/#java11-linux 
    - for windows users https://www.oracle.com/java/technologies/downloads/#java11-windows
    
    - *Ubuntu* - `https://tecadmin.net/install-java-ubuntu-20-04/
    - *Debian* - `https://docs.datastax.com/en/jdk-install/doc/jdk-install/installOpenJdkDeb.html`
    - *FreeBSD* - `pkg install openjdk11`

----
## Run it! ##

  - click on the GALA icon, or start from the command line:
  - Unix: `./run.sh`
  - Window: `run.bat`

  - wait for the JavaFX wallet window to open
  - on platforms without JavaFX, open http://localhost:7003/ in a browser
  - or visit online wallet https://wdc.teststrana.com

----
## Compile it! ##

  - if necessary with: `./compile.sh`
  - you need jdk-11 as well

----
## Troubleshooting the NRS (GALA Reference Software) ##

  - How to Stop the NRS Server?
    - click on GALA Stop icon, or run `./stop.sh`
    - or if started from command line, ctrl+c or close the console window

  - UI Errors or Stacktraces?
    - report on BitBucket

  - Permissions Denied?
    - no spaces and only latin characters in the path to the NRS installation directory
    - known jetty issue

----
## Further Reading ##

  - in this repository:
    - USERS-GUIDE.md
    - DEVELOPERS-GUIDE.md
    - OPERATORS-GUIDE.md
    - In the doc folder

----

## License
* This program is distributed under the terms of the Jelurida Public License version 1.1 for the Ardor Public Blockchain Platform.

