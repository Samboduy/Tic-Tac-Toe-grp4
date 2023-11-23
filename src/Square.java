import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

public class Square extends JButton {
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
        setContentAreaFilled(false);
        setUpptagen(false);
        setFont(new Font("Arial", Font.PLAIN, 40));
        setVisible(true);
        addActionListener(e ->
        {
            if (!getUpptagen()){
                marker = playingField.getNuvarandeSpelare();

                setUpptagen(true);
                setForeground(Color.BLACK); //Override hover

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
                if (!getUpptagen()) {
                    setText(Character.toString(marker));
                    setForeground(new Color(0,0,0,20));
                }

            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                //setBackground(UIManager.getColor("control"));
                if (!getUpptagen()) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }
        });
    }

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
    public void rensa () {
        marker = ' ';
        setUpptagen(false);
        setText(Character.toString(marker));
        setEnabled(true);
    }

}


