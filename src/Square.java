import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Square extends JButton {
    private Clip clip;
    private char marker = ' ';
    private boolean upptagen;
    public void setUpptagen(boolean newUpptagen){
        upptagen=newUpptagen;
    }
    public boolean getUpptagen(){
        return upptagen;
    }
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

        loadSound();

        upptagen = false;
        setFont(new Font("Arial", Font.PLAIN, 40));
        addActionListener(e ->
        {
            if (!upptagen){
                marker = playingField.getNuvarandeSpelare();

                upptagen = true;
                setForeground(Color.BLACK); //Override hover

                playSound();

                if (playingField.checkWinner()) {
                    //Visa vinnarmeddelande
                    System.out.println("Player " + marker + " wins!");
                }

                setText(Character.toString(marker));
                playingField.bytaSpelare();
                setStateText(String.valueOf(playingField.getNuvarandeSpelare()));
                stateText.setText("Player " + getStateText() + "'s turn");
                //Checka efter vinnare efter varje drag

            }
        });
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                //setBackground(Color.GREEN);
                char marker = playingField.getNuvarandeSpelare();
                if (!upptagen) {
                    setText(Character.toString(marker));
                    setForeground(new Color(0,0,0,20));
                }

            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                //setBackground(UIManager.getColor("control"));
                if (!upptagen) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }
        });
    }


    public char getMarker() {
        return marker;
    }

    //Anropas från PlayingField. Rensar variabler och texten i square.
    public void rensa () {
        marker = ' ';
        upptagen = false;
        setText(Character.toString(marker));
        setEnabled(true);
    }
    private void loadSound() { // Creating a method to download the file, doing it in a try/catch so that the game does not crash
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:/Users/olive/OneDrive/Dokument/GitHub/Tic-Tac-Toe-grp4/src/Ball_Drops.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    private void playSound() {
        // if so that it only plays if victoryClip has something in it
        if (clip != null) {
            clip.setFramePosition(0); // So the audio always plays from the beggining
            clip.start();
        }
    }

}


