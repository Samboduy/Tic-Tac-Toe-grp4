import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Window extends JFrame {
    //BorderLayout
    //En JPanel
    //Sätta text i NORTH med JLabel
    JButton restart;
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

        restart = new JButton("Starta om");
        //adds an action listener to the restart button that calls on the restartPressed method
        restart.addActionListener(e -> restartPressed());
        bottomPanel.add(restart);

        add(new PlayingField(this), BorderLayout.CENTER);
        setVisible(true);

    }
    // Made a method that sends a message when you press the restart button
    //Testar att man kan göra så att en knapp inte är klickbar

    public void restartPressed(){
        System.out.println("Nu börjar vi om");
        restart.setEnabled(false);
    }
}
