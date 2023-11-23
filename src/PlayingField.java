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

    //Just nu är det så att den första spelaren alltid är 'X'
    static char nuvarandeSpelare;

    static char startingPlayer;
    //Here we want to make a grid layout, and which from an array list of 9 instances of Square, adds all to a grid layout.
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
    public int getCount(){
        return count;
    }
    Square square;
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
            System.out.println(i);
        }
        //window.setVisible(true);
        randomPlayer();
        setStateText(String.valueOf(nuvarandeSpelare));
        stateText.setText("Player " + getStateText() + "'s turn");
    }

    public boolean checkWinner() {

        //Vinnande kombinationer - horisontella, vertikala och diagonala
        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //Horisontella
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //Vertikala
                {0, 4, 8}, {2, 4, 6} //Diagonala
        };

        for (int[] combination : winningCombinations) {
            int first = combination[0]; //0,3,6 (horinsontellt), 0,1,2 (vertikalt), 0,2 (diagonalt)
            int second = combination[1]; //1,4,7 (horinsontellt), 0,4,5 (vertikalt), 4,4 (diagonalt)
            int third = combination[2]; //2,5,8 (horinsontellt), 6,7,8 (vertikalt), 8,6 (diagonalt)

            Square square1 = squares.get(first);
            Square square2 = squares.get(second);
            Square square3 = squares.get(third);

            if (square1.getMarker() == square2.getMarker() &&
                    square2.getMarker() == square3.getMarker() &&
                    square1.getMarker() != ' ') {
                //Tre markörer i rad hittade - vinnare
                /*The foor loop goes through every button and changes so that you're unable to engage with it
                and changes so it's like someone have clicked on each one of the buttons*/
                for (count = 0; count<9;){
                    squares.get(count).setEnabled(false);
                    squares.get(count).setUpptagen(true);
                    setCount(count+1);
                }
                //a popup that tells who won the game
                if (nuvarandeSpelare=='O'){
                    JOptionPane.showMessageDialog(null,"Congratulations! Player O won the game!",
                            "Victory",JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Congratulations! Player X won the game!",
                            "Victory",JOptionPane.PLAIN_MESSAGE);
                }

                return true;
            }

        }

        return false;
    }



   /* public boolean checkWinner (){
        char [][] board = new char[3][3];
        int index = 0;

        for (int i = 0; i < 3; i++){
            for (int x = 0; x < 3; x++){
                board[i][x] = squares.get(index++).getMarker();
            }
        }

        for (int i = 0; i < 3; i++) {

            //Vinnare i rad i
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return true;
            }
            //Vinnare i kolumn i
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                return true;
            }
        }
        //Kontrollera diagonaler för en vinnare
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ')
                || (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')) {
            return true; // Vinnare i diagonal
        }

        return false; // Inga vinnare hittades
    } */




    //En if-sats för att avgöra vem ska sin lägga sin marker, kallas på när man trycker på knappen
    public void bytaSpelare(){
        if (nuvarandeSpelare == 'X'){
            nuvarandeSpelare = 'O';
        } else {
            nuvarandeSpelare = 'X';
        }

        if (window.againstAI) {
            //Make an AI move
            AIMove();
        }
    }

    public void AIMove() {
        if (nuvarandeSpelare != startingPlayer) {
            List<Square> emptySquares = (List<Square>) squares.stream().filter(square -> square.getMarker() == ' ').collect(Collectors.toList());
            Collections.shuffle(emptySquares); //Shuffle array

            Square randomSquare = emptySquares.get(0);
            randomSquare.doClick();
        };

    }
    //Kallas från window när reset klickas.
    //Gör en loop över en arrayen med squares, och
    //kallas på rensa funktionen som finns i klassen Square
    public void reset(JLabel status) {
        for (Square square : squares) {
            square.rensa();
        }
        randomPlayer();
        setStateText(String.valueOf(nuvarandeSpelare));
        status.setText("Player " + getStateText() + "'s turn");
    }
    public void randomPlayer(){
        //takes a number that will be randomized between, the player to begin is then decided if it's one or two
        setNumber(2);
        if (getNumber()==1){
            nuvarandeSpelare='X';
            startingPlayer = 'X';
        }
        else{
            nuvarandeSpelare='O';
            startingPlayer = 'O';
        }
    }
    public char getNuvarandeSpelare() {
        return nuvarandeSpelare;
    }

}
