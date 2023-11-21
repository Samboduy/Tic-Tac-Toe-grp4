import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayingField extends JPanel {
    // GridLayout
    ArrayList<Square> squares = new ArrayList<Square>();

    //Here we want to make a grid layout, and which from an array list of 9 instances of Square, adds all to a grid layout.

    PlayingField() {
        this.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < squares.size(); i++) {
            Square square = squares.get(i);
            System.out.println(i);
        }

    }
}
