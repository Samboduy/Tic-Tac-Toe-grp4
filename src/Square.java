import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Square extends JButton {
    private Clip clip;
    private char marker = ' ';
    private boolean occupied;
    public void setOccupied(boolean newUpptagen){
        occupied =newUpptagen;
    }
    public boolean getOccupied(){
        return occupied;
    }
     PlayingField playingField;
    private String stateText;
    public void setStateText(String newStateText){
        this.stateText=newStateText;
    }
    public String getStateText(){
        return stateText;
    }



    Square(PlayingField playingField, JLabel stateText) {
        this.playingField = playingField;
        setStateText(String.valueOf(playingField.getCurrentPlayer()));
        setOccupied(false);
        loadSound();
        setContentAreaFilled(false);
        setFont(new Font("Arial", Font.PLAIN, 40));
        setVisible(true);
        addActionListener(e ->
        {
            if (!getOccupied()){
                marker = playingField.getCurrentPlayer();

                setOccupied(true);
                setForeground(Color.BLACK); //Override hover

                setText(Character.toString(marker));
                boolean won = playingField.checkWinner();
                if (!won) playingField.checkTie();


                playingField.switchPlayer();
                setStateText(String.valueOf(playingField.getCurrentPlayer()));
                stateText.setText("Player " + getStateText() + "'s turn");
            }
        });
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                //setBackground(Color.GREEN);
                char marker = playingField.getCurrentPlayer();
                if (!getOccupied()) {
                    setText(Character.toString(marker));
                    setForeground(new Color(0,0,0,20));
                }

            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                //setBackground(UIManager.getColor("control"));
                if (!getOccupied()) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }
        });
    }

    //Overrides the paint component in button, which is to make the
    //color more consistent across platforms (white)
    @Override
    protected void paintComponent(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(
                new Point(0, 0),
                Color.WHITE,
                new Point(0, getHeight()),
                Color.WHITE));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }


    public char getMarker() {
        return marker;
    }

    //Anropas från PlayingField. Rensar variabler och texten i square.
    public void empty() {
        marker = ' ';
        setOccupied(false);
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


