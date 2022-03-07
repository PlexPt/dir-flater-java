
package com.plexpt;

import com.plexpt.config.I18N;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.plexpt.config.PROP;
import org.apache.log4j.Logger;

/**
 * FrameAbout class.
 *
 * @author Nafaa Friaa (nafaa.friaa@isetjb.rnu.tn)
 */
public class FrameAbout extends JInternalFrame
{
    final static Logger log = Logger.getLogger(FrameAbout.class);

    JLabel jLabelLogo;
    JLabel jLabelHeader = new JLabel("<html><h3>" + PROP.getProperty("app.artifactId") + "-" + PROP.getProperty("app.version") + "</h3></html>");
    JTextArea jTextArea1 = new JTextArea(11, 21);
    JButton jButtonOk = new JButton(I18N.lang("frameabout.jButtonOk"));

    /**
     * Constructor.
     */
    public FrameAbout()
    {
        log.debug("START constructor...");

        setTitle(I18N.lang("frameabout.title"));
        setLocation(new Random().nextInt(140) + 50, new Random().nextInt(150) + 50);
        setSize(320, 400);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(false);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        // data :
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/logo.png"));
        //ImageIcon resizedIcon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("images/logo.png")).getImage().getScaledInstance(200, 50, Image.SCALE_DEFAULT));
        jLabelLogo = new JLabel(icon);

        jTextArea1.setText(I18N.lang("frameabout.jTextArea1"));
        jTextArea1.setEditable(false);
        jTextArea1.setEnabled(false);

        //add compnent to the frame :
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(jLabelHeader);
        getContentPane().add(jLabelLogo);
        getContentPane().add(new JScrollPane(jTextArea1));
        getContentPane().add(jButtonOk);

        // actions / events :
        jButtonOk.addActionListener((ActionEvent ev) ->
        {
            log.debug("ActionEvent on " + ev.getActionCommand());

            setVisible(false);
        });

        setVisible(false);

        log.debug("End of constructor.");
    }
}
