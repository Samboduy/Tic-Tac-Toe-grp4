import javax.swing.*;
import java.awt.*;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Square extends JButton {
    private char marker;
    private boolean upptagen;
    Square(JLabel status) {
        upptagen = false;
        marker = ' ';
        setFont(new Font("Arial", Font.PLAIN, 50));
        addActionListener(e ->
        {
            if (!upptagen){
                marker = nuvarandeSpelareMarker();
                setText(Character.toString(marker));
                upptagen = true;
                status.setText(String.valueOf("It's " + marker + " Turn"));
            }
        });
    }

    private char nuvarandeSpelareMarker(){
        return 'X';
    }
}


