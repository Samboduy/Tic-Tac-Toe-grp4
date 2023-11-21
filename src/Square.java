import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Square extends JButton {
    private char marker;
    private boolean upptagen;
    Square() {
        upptagen = false;
        marker = ' ';
        setFont(new Font("Arial", Font.PLAIN, 40));
        addActionListener(e ->
        {
            if (!upptagen){
                marker = nuvarandeSpelareMarker();
                setText(Character.toString(marker));
                upptagen = true;
            }
        });
    }

    private char nuvarandeSpelareMarker(){
        return 'X';
    }
}


