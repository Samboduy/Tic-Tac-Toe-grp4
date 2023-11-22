import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayingField extends JPanel {
    ArrayList<Square> squares = new ArrayList<Square>();

    //Just nu är det så att den första spelaren alltid är 'X'
    static char nuvarandeSpelare= 'X';
    //Here we want to make a grid layout, and which from an array list of 9 instances of Square, adds all to a grid layout.

    PlayingField(JLabel stateText) {
        this.setLayout(new GridLayout(3, 3));
        //Add the initial 9 squares
        for (int i = 0; i < 9; i++) {
            squares.add(new Square(this,stateText));
        }
        //Using the existing list of square instances, add square.
        for (int i = 0; i < squares.size(); i++) {
            Square square = squares.get(i);

            this.add(square);
            System.out.println(i);
        }
        //window.setVisible(true);

    }

    //En if-sats för att avgöra vem ska sin lägga sin marker, kallas på när man trycker på knappen
    public void bytaSpelare(){
        if (nuvarandeSpelare == 'X'){
            nuvarandeSpelare = 'O';
        } else {
            nuvarandeSpelare = 'X';
        }
    }

    //Kallas från window när reset klickas.
    //Gör en loop över en arrayen med squares, och
    //kallas på rensa funktionen som finns i klassen Square
    public void reset() {
        for (Square square : squares) {
            square.rensa();
        }
    }
    public char getNuvarandeSpelare() {
        return nuvarandeSpelare;
    }
}
