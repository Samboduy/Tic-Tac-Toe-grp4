import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Square extends JButton {
    private char marker;
    private boolean upptagen;
    private PlayingField playingField;
    private String stateText;
    public void setStateText(String newStateText){
        this.stateText=newStateText;
    }
    public String getStateText(){
        return stateText;
    }


    Square(PlayingField playingField, JLabel stateText) {
        this.playingField = playingField;
        setStateText(String.valueOf(playingField.getNuvarandeSpelare()));
        stateText.setText("Player " + getStateText() + " turn");
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
                setStateText(String.valueOf(playingField.getNuvarandeSpelare()));
                stateText.setText("Player " + getStateText() + " turn");
            }
        });}

    public char getMarker() {
        return marker;
    }

    //Anropas från PlayingField. Rensar variabler och texten i square.
    public void rensa () {
        marker = ' ';
        upptagen = false;
        setText(Character.toString(marker));
    }

}


