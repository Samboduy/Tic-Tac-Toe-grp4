import javax.swing.*;
import java.awt.*;
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

                for (count = 0; count<9;){
                    squares.get(count).setEnabled(false);
                    squares.get(count).setOccupied(true);
                    setCount(count+1);
                }
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

    //Chooses who´s to start
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


    public void AIMove() {
        if (currentPlayer != startingPlayer) {
            List<Square> emptySquares = (List<Square>)
                    squares.stream().filter(square -> square.getMarker() == ' ').collect(Collectors.toList());

            Collections.shuffle(emptySquares); //Shuffle array
            if(emptySquares.size() > 0) {
                Square randomSquare = emptySquares.get(0);
                randomSquare.doClick();
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

    public void randomPlayer(){
        //setNumber randomizes a number between 1 and 2,
        // then that nummber is used to decide which player will start
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

}
