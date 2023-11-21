import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Square extends JButton {
    private char marker;
    private boolean upptagen;
    private PlayingField playingField;
    Square(PlayingField playingField) {
        this.playingField = playingField;
        upptagen = false;
        marker = ' ';
        setFont(new Font("Arial", Font.PLAIN, 40));
        addActionListener(e ->
        {
            if (!upptagen){
                marker = playingField.getNuvarandeSpelare();
                setText(Character.toString(marker));
                upptagen = true;
                playingField.bytaSpelare();
            }
        });}

    //Anropas från PlayingField. Rensar variabler och texten i square.
    public void rensa () {
        marker = ' ';
        upptagen = false;
        setText(Character.toString(marker));
    }

}


