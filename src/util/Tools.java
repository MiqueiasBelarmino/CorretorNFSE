package util;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author Miquéias
 */
public class Tools {
    
    public static Properties getPropertiesFile(){
        
        Properties props = new Properties();
        FileInputStream file = null;
        try {
            file = new FileInputStream("property\\config.properties");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            props.load(file);
        } catch (IOException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
        return props;
    }

    public static boolean numberInputVerifier(Component j, KeyEvent evt, String key) {
        String value = "";
        JTextField txt;

        if ((j instanceof JTextField) || (j instanceof JTextField)) {
            txt = (JTextField) j;
            value = txt.getText();
        }

        if (key.equals("") || key.equals(" ") || key == null) {
            return (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9')
                    || (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                    || (evt.getKeyCode() == KeyEvent.VK_DELETE);
        } else {
            char c = key.charAt(0);
            return (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9')
                    || (evt.getKeyChar() == c)
                    || (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                    || (evt.getKeyCode() == KeyEvent.VK_DELETE);
        }
    }
}
