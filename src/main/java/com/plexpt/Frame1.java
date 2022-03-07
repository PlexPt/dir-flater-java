
package com.plexpt;

import com.plexpt.config.I18N;
import java.awt.FlowLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import org.apache.log4j.Logger;

/**
 * Frame1 class.
 *
 * @author Nafaa Friaa (nafaa.friaa@isetjb.rnu.tn)
 */
public class Frame1 extends JInternalFrame
{
    final static Logger log = Logger.getLogger(Frame1.class);

    JButton jButton1 = new JButton(" Test >> ");

    /**
     * Constructor.
     */
    public Frame1()
    {
        log.debug("START constructor...");

        setTitle(I18N.lang("frame1.title"));
        setLocation(new Random().nextInt(120) + 10, new Random().nextInt(120) + 10);
        setSize(550, 350);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(false);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        //add compnent to the frame :
        getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
        getContentPane().add(jButton1);

        setVisible(false);

        log.debug("End of constructor.");
    }
}
