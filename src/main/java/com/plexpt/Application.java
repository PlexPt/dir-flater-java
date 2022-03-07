package com.plexpt;

import com.plexpt.config.I18N;
import com.plexpt.config.PROP;

import org.apache.log4j.Logger;

/**
 * Application class.
 *
 * @author Nafaa Friaa (nafaa.friaa@isetjb.rnu.tn)
 */
public class Application {
    final static Logger log = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        log.info("Initializing the application...");

        PROP.init();
        I18N.init();
        macosConfig();

        log.info("Starting " + PROP.getProperty("app.finalName") + " Application...");

        // display the desktop frame :
        new Desktop();

        log.info("Application " + PROP.getProperty("app.finalName") + " started.");
    }

    /**
     * Special settting for macOS.
     */
    public static void macosConfig() {
        if (System.getProperty("os.name").contains("Mac")) {
            log.debug("Special settings for macOS users...");

            // take the menu bar off the jframe :
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }
    }
}
