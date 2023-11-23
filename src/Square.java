import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Objects;

public class Square extends JButton {
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
        setContentAreaFilled(false);
        setFont(new Font("Arial", Font.PLAIN, 40));
        setVisible(true);
        addActionListener(e ->
        {
            if (!getOccupied()){
                marker = playingField.getCurrentPlayer();

                setOccupied(true);
                setForeground(Color.BLACK); //Override hover
                playingField.playSound();

                setText(Character.toString(marker));
                boolean won = playingField.checkWinner();
                if (!won) playingField.checkTie();
                if (won) playingField.playVictorySound();


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

}


