import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    //BorderLayout
    //En JPanel
    //Sätta text i NORTH med JLabel

Window () {
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH);

    // Create a panel at the top to place the titel and "status"
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    add(topPanel, BorderLayout.NORTH);

    JPanel topPanelTitle = new JPanel();
    topPanel.add(topPanelTitle, BorderLayout.NORTH);

    JLabel title = new JLabel("Tic Tac Toe!");
    topPanelTitle.add(title);

    JPanel topPanelStatus = new JPanel();
    topPanel.add(topPanelStatus, BorderLayout.SOUTH);

    JLabel status = new JLabel("status");
    topPanelStatus.add(status);


    // Create a bottomPanel to place the "restart" button
    JPanel bottomPanel = new JPanel();
    add(bottomPanel, BorderLayout.SOUTH);

    JButton restart = new JButton("Starta om");
    bottomPanel.add(restart);
    setVisible(true);

}

}
