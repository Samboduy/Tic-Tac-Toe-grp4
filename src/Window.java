import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    //BorderLayout
    //En JPanel
    //Sätta text i NORTH med JLabel

Window () {
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1000, 750);
    setVisible(true);
}

}
