import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Window extends JFrame {
    JButton restart;
    JButton restartVsAI;
    PlayingField playingField;
    JLabel status;

    boolean againstAI = false;

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

        status = new JLabel();
        topPanelStatus.add(status);
        //creates a new playingfield and state text to PlayingField
        playingField = new PlayingField(this, status );
        add(playingField, BorderLayout.CENTER);

        // Create a bottomPanel to place the "restart" button
        JPanel bottomPanel = new JPanel();
        add(bottomPanel, BorderLayout.SOUTH);

        restart = new JButton("Start over");
        //adds an action listener to the restart button that calls on the restartPressed method
        restart.addActionListener(e -> restartPressed());
        bottomPanel.add(restart);

        restartVsAI = new JButton("Starta over (VS AI)");
        //adds an action listener to the restart button that calls on the restartPressed method
        restartVsAI.addActionListener(e -> restartWithAIPressed());
        bottomPanel.add(restartVsAI);

        setVisible(true);

    }
    // Made a method that sends a message when you press the restart button
    //Testar att man kan göra så att en knapp inte är klickbar

    public void restartPressed(){
        againstAI = false;
        playingField.reset(status);
    }
    public void restartWithAIPressed(){
        againstAI = true;
        playingField.reset(status);
    }
}
