Electrómetro
==============

Initial Steps
--------------

For avoiding committing unnecessary files to the CVS, some initial steps must be taken to get a working environment.

- **node.js Installation for npm**

    Download and follow instructions from http://nodejs.org/

- **Global Cordova Installation**.

    *npm install -g cordova*

- **Global Ionic Installation**.

    *npm install -g ionic*

- **Global Bower Installation**

    *npm install -g bower*

- **Global Gulp Installation**

    *npm install -g gulp*
    
- **Global Coffeescript Installation**

    *npm install -g coffee-script*

- **Project dependencies update for npm and bower**. Should create dependencies dir for both.

    *npm update*   
    *bower update*

- **Platforms**. Initially /platforms dir should be empty. Directories for android and ios should be created:

    *ionic platform ios*   
    *ionic platform android*

    After this steps, command:
    
    *ionic platform list*  

    Should show:

    *Installed platforms: android 3.5.0, ios 3.5.0*   
    *Available platforms: amazon-fireos, blackberry10, firefoxos*

- **Plugins**. Initially /plugins dir should be empty. A directory for every plugin should be created:

    *ionic plugin add de.appplant.cordova.plugin.local-notification*   
    *ionic plugin add org.apache.cordova.network-information*
    *ionic plugin add https://github.com/apla/me.apla.cordova.app-preferences*

    After this steps, command:

    *ionic plugin list*    

    Should show:
    
    *com.ionic.keyboard 1.0.3 "Keyboard"*   
    *de.appplant.cordova.plugin.local-notification 0.7.6 "LocalNotification"*  
    *me.apla.cordova.app-preferences 0.4.2 "AppPreferences"*
    *org.apache.cordova.console 0.2.11 "Console"*   
    *org.apache.cordova.device 0.2.12 "Device"*   
    *org.apache.cordova.network-information 0.2.12 "Network Information"*
