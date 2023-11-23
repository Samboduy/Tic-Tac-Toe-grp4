import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class Square extends JButton {
    private char marker = ' ';
    private boolean occupied;
    public void setOccupied(boolean newUpptagen){
        occupied =newUpptagen;
    }
    public boolean getOccupied(){
        return occupied;
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
        setStateText(String.valueOf(playingField.getCurrentPlayer()));

        setOccupied(false);
        setFont(new Font("Arial", Font.PLAIN, 40));
        addActionListener(e ->
        {
            if (!getOccupied()){
                marker = playingField.getCurrentPlayer();

                setOccupied(true);
                setForeground(Color.BLACK); //Override hover

                if (playingField.checkWinner()) {
                    //Visa vinnarmeddelande
                    System.out.println("Player " + marker + " wins!");
                }

                setText(Character.toString(marker));
                playingField.switchPlayer();
                setStateText(String.valueOf(playingField.getCurrentPlayer()));
                stateText.setText("Player " + getStateText() + "'s turn");
                //Checka efter vinnare efter varje drag

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


