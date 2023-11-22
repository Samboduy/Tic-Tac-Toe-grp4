import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayingField extends JPanel {
    ArrayList<Square> squares = new ArrayList<Square>();

    //Just nu är det så att den första spelaren alltid är 'X'
    static char nuvarandeSpelare;
    //Here we want to make a grid layout, and which from an array list of 9 instances of Square, adds all to a grid layout.
    private int number;
    public int getNumber(){
        return number;
    }
    public void setNumber(int between){
        Random rand = new Random();
        number=rand.nextInt(between) + 1;
    }
    PlayingField(JLabel stateText) {
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
    }

    public boolean checkWinner() {
        //Vinnande kombinationer - horisontella, vertikala och diagonala
        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //Horisontella
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //Vertikala
                {0, 4, 8}, {2, 4, 6} //Diagonala
        };

        for (int[] combination : winningCombinations) {
            int first = combination[0];
            int second = combination[1];
            int third = combination[2];

            Square square1 = squares.get(first);
            Square square2 = squares.get(second);
            Square square3 = squares.get(third);

            if (square1.getMarker() == square2.getMarker() &&
                    square2.getMarker() == square3.getMarker() &&
                    square1.getMarker() != ' ') {
                //Tre markörer i rad hittade - vinnare
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
    }

    //Kallas från window när reset klickas.
    //Gör en loop över en arrayen med squares, och
    //kallas på rensa funktionen som finns i klassen Square
    public void reset() {
        for (Square square : squares) {
            square.rensa();
        }
        randomPlayer();
    }
    public void randomPlayer(){
        //takes a number that will be randomized between, the player to begin is then decided if it's one or two
        setNumber(2);
        if (getNumber()==1){
            nuvarandeSpelare='X';
        }
        else{
            nuvarandeSpelare='O';
        }
    }
    public char getNuvarandeSpelare() {
        return nuvarandeSpelare;
    }
}
