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

    setVisible(true);

}

}
