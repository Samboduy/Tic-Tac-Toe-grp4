import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayingField extends JPanel {
    // GridLayout
    ArrayList<Square> squares = new ArrayList<Square>();
char nuvarandeSpelare= 'X';
    //Here we want to make a grid layout, and which from an array list of 9 instances of Square, adds all to a grid layout.

    PlayingField(Window window) {
        this.setLayout(new GridLayout(3, 3));
        //Add the initial 9 squares
        for (int i = 0; i < 9; i++) {
            squares.add(new Square());
        }
        //Using the existing list of square instances, add square.
        for (int i = 0; i < squares.size(); i++) {
            Square square = squares.get(i);

            this.add(square);
            System.out.println(i);
        }
        //window.setVisible(true);

    }
    public void bytaSpelare(){
        if (nuvarandeSpelare == 'X'){
            nuvarandeSpelare = 'O';
        } else {
            nuvarandeSpelare = 'X';
        }
    }
}
