import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

public class PlayingField extends JPanel {
    ArrayList<Square> squares = new ArrayList<Square>();
    Window window;
    static char currentPlayer;
    static char startingPlayer;
    private Clip clip;
    private Clip victory;
    private String stateText;
    public void setStateText(String newStateText){
        this.stateText=newStateText;
    }
    public String getStateText(){
        return stateText;
    }

    private int number;
    public int getNumber(){
        return number;
    }
    public void setNumber(int between){
        Random rand = new Random();
        number=rand.nextInt(between) + 1;
    }

    private int count = 0;
    public void setCount(int newCount){
        count=newCount;
    }


    //Creating the playingfield, giving it a gridlayout (3x3)
    PlayingField(Window window, JLabel stateText) {
        this.window = window;
        this.setLayout(new GridLayout(3, 3));
        //Add the initial 9 squares
        for (int i = 0; i < 9; i++) {
            squares.add(new Square(this,stateText));
        }
        //Using the existing list of square instances, add square.
        for (int i = 0; i < squares.size(); i++) {
            Square square = squares.get(i);
            this.add(square);
        }
        randomPlayer();
        setStateText(String.valueOf(currentPlayer));
        stateText.setText("Player " + getStateText() + "'s turn");
        loadVictorySound();
        loadSound();
    }

    public boolean checkWinner() {
        //Winning combinations, horizontal, vertical, and diagonal
        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //Horizontal possibilities of winning
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //Vertical possibilities of winning
                {0, 4, 8}, {2, 4, 6} //Diagonal possibilities of winning
        };

        //Goes through each number of choosen squares
        for (int[] combination : winningCombinations) {
            int first = combination[0]; //0,3,6 (Horizontal), 0,1,2 (vertical), 0,2 (diagonal)
            int second = combination[1]; //1,4,7 (Horizontal), 0,4,5 (vertical), 4,4 (diagonal)
            int third = combination[2]; //2,5,8 (Horizontal), 6,7,8 (vertical), 8,6 (diagonal)

            //Fetching the three inputs (squares) choosen
            Square square1 = squares.get(first);
            Square square2 = squares.get(second);
            Square square3 = squares.get(third);

            //searches if the three inputs matches any of the winning combinations
            if (square1.getMarker() == square2.getMarker() &&
                    square2.getMarker() == square3.getMarker() &&
                    square1.getMarker() != ' ') {

                gameOver();
                playVictorySound();
                //a popup that tells who won the game
                if (window.againstAI) {
                    //We are playing against the AI
                    if (currentPlayer==startingPlayer){
                        JOptionPane.showMessageDialog(null,"Congratulations! You won!",
                                "Victory",JOptionPane.PLAIN_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Oh no! The AI won!",
                                "Lost",JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    //Player vs player
                    if (currentPlayer == 'O') {
                        JOptionPane.showMessageDialog(null, "Player O won the game",
                                "Victory", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Player X won the game",
                                "Victory", JOptionPane.PLAIN_MESSAGE);
                    }
                }

                return true;
            }

        }



        return false;
    }
    //Disables buttons and makes it so the hover effect disappears by turning occupied to true
    public void gameOver(){
        for (count = 0; count<9;){
            squares.get(count).setEnabled(false);
            squares.get(count).setOccupied(true);
            setCount(count+1);
        }
    }

    public boolean checkTie() {
        int filledTiles = 0;
        for (Square square : squares) {
            if (square.getOccupied()) {
                filledTiles++;
            }
        }
        boolean isTie = filledTiles == squares.size();
        if (isTie) {
            JOptionPane.showMessageDialog(null, "It's a tie!",
                    "Tie", JOptionPane.PLAIN_MESSAGE);
                    gameOver();
        }
        return isTie;
    }

    //swithPlayer is called on everytime the user puts down an X or an O
    //if the current player is X when putting down his marker then it will switch to O afterward
    public void switchPlayer(){
        if (currentPlayer == 'X'){
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }

        if (window.againstAI) {
            //Make an AI move
            AIMove();
        }
    }

    //Triggered each time a move is made and the game mode is set to AI.
    //When triggered it checks if it's the player or AI's turn
    public void AIMove() {
        if (currentPlayer != startingPlayer) {
            //Filter empty squares to be only the empty tiles, so that we have a list of spots to pick from
            List<Square> emptySquares = (List<Square>)
                    squares.stream().filter(square -> square.getMarker() == ' ').collect(Collectors.toList());

            Collections.shuffle(emptySquares); //Shuffle array, basically randomizes the list
            if(emptySquares.size() > 0) { //To prevent crashes, check so there are more than 0 spots
                Square randomSquare = emptySquares.get(0); //get first in array
                randomSquare.doClick(); //Fake a click
            }

        };
    }
    //Kallas från window när reset klickas.
    //Gör en loop över en arrayen med squares, och
    //kallas på rensa funktionen som finns i klassen Square
    public void reset(JLabel status) {
        for (Square square : squares) {
            square.empty();
        }
        randomPlayer();
        setStateText(String.valueOf(currentPlayer));
        status.setText("Player " + getStateText() + "'s turn");
    }
    //setNumber randomizes a number between 1 and 2,
    // then that nummber is used to decide which player will start
    public void randomPlayer(){
        setNumber(2);
        if (getNumber()==1){
            currentPlayer ='X';
            startingPlayer = 'X';
        }
        else{
            currentPlayer ='O';
            startingPlayer = 'O';
        }
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }
    public void loadSound() { // Creating a method to download the file, doing it in a try/catch so that the game does not crash
        Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path+ "/src/Ball_Drops.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public void playSound() {
        // if so that it only plays if victoryClip has something in it
        if (clip != null) {
            clip.setFramePosition(0); // So the audio always plays from the beggining
            clip.start();
        }
    }
    private void loadVictorySound() {
        Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path+ "/src/Female_Crowd_Celebration.wav"));
            victory = AudioSystem.getClip();
            victory.open(audioInputStream);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public void playVictorySound() {

        if (victory != null) {
            victory.setFramePosition(0);
            victory.start();
        }
    }

    public void loadStartOverSound() {
        Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path+ "/src/Twang.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playStartOverSound() {

        if (clip!= null) {
            clip.setFramePosition(0); // So the audio always plays from the beggining
            clip.start();
        }
    }

}
