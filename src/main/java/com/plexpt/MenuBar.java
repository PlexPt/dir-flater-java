package com.plexpt;

import com.plexpt.config.I18N;

import org.apache.log4j.Logger;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * MenuBar class.
 *
 * @author Nafaa Friaa (nafaa.friaa@isetjb.rnu.tn)
 */
public class MenuBar extends JMenuBar {
    final static Logger log = Logger.getLogger(MenuBar.class);

    // file :
    JMenu jMenuFile = new JMenu(I18N.lang("menubar.jMenuFile"));
    JMenuItem jMenuItemFrame1 = new JMenuItem(I18N.lang("menubar.jMenuItemFrame1"));
    JMenuItem jMenuItemQuit = new JMenuItem(I18N.lang("menubar.jMenuItemQuit"));

    // help :
    JMenu jMenuHelp = new JMenu(I18N.lang("menubar.jMenuHelp"));
    JMenuItem jMenuItemFrameAbout = new JMenuItem(I18N.lang("menubar.jMenuItemFrameAbout"));

    /**
     * Constructor.
     */
    public MenuBar() {
        log.debug("START constructor...");

        // file :
        add(jMenuFile);
        jMenuFile.setMnemonic(KeyEvent.VK_F);

        jMenuItemFrame1.setAccelerator(KeyStroke.getKeyStroke('R',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jMenuFile.add(jMenuItemFrame1);

        jMenuFile.addSeparator();

        jMenuItemQuit.setAccelerator(KeyStroke.getKeyStroke('Q',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jMenuFile.add(jMenuItemQuit);

        // help :
        add(jMenuHelp);
        jMenuHelp.setMnemonic(KeyEvent.VK_H);

        jMenuItemFrameAbout.setAccelerator(KeyStroke.getKeyStroke('A',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jMenuHelp.add(jMenuItemFrameAbout);

        log.debug("End of constructor.");
    }
}
