import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

public class Square extends JButton {
    private char marker;
    private boolean upptagen;
    private PlayingField playingField;
    Square(PlayingField playingField) {
        this.playingField = playingField;
        upptagen = false;
        marker = playingField.getNuvarandeSpelare();
        setFont(new Font("Arial", Font.PLAIN, 40));
        addActionListener(e ->
        {
            if (!upptagen){
                setText(Character.toString(marker));
                upptagen = true;
                playingField.bytaSpelare();

                marker = playingField.getNuvarandeSpelare();
                setForeground(new Color(0,0,0,100)); //Override hover
            }
        });
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                //setBackground(Color.GREEN);

                if (!upptagen) {
                    setText(Character.toString(marker));
                    setForeground(new Color(0,0,0,20));
                }

            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                //setBackground(UIManager.getColor("control"));
                if (!upptagen) {
                    setText("");
                    setForeground(new Color(0,0,0,100));
                }
            }
        });
    }


    //Anropas från PlayingField. Rensar variabler och texten i square.
    public void rensa () {
        marker = ' ';
        upptagen = false;
        setText(Character.toString(marker));
    }

}


