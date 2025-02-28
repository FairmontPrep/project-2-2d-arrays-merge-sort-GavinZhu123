import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

public class GameBoard extends JFrame {
    public int SIZE = 8;
    private JPanel[][] squares = new JPanel[SIZE][SIZE]; // 2D array for board
    private String[][] piecesArray; //2D array = image::HP::board position

    public GameBoard() {
        setTitle("Chessboard");
        setSize(750, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE)); // Use GridLayout for the board layout

        // Initialize the 2D array of panels
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                squares[row][col] = new JPanel();
                if ((row + col) % 2 == 0) {
                    squares[row][col].setBackground(Color.WHITE); // Color black for even squares
                } else {
                    squares[row][col].setBackground(Color.BLACK); // Color dark gray for odd squares
                }
                add(squares[row][col]); // Add e    ach square to the board
            }
        }

        // Initialize Pokémon pieces array
        piecesArray = new String[32][3];  //your array should be at least 2 columns
        loadPieces();
        for (int i = 0; i < piecesArray.length; i++) {
            for (int j = 0; j < piecesArray[i].length; j++) {
                System.out.println("piecesArray[" + i + "][" + j + "] = " + piecesArray[i][j]);
            }
        }

        // Initially populate the board with pieces
        populateBoard();
    }

    public JPanel[][] sortImages(JPanel[][] finalPositions) {
        Arrays.sort(piecesArray, new Comparator<String[]>() {
            @Override
           
            public int compare(String[] piece1, String[] piece2) {
                return Integer.compare(Integer.parseInt(piece1[2]), Integer.parseInt(piece2[2]));
            }
        });
       
        return finalPositions;
    }

    private void populateBoard() {
        int pieceRow = 0; //keeps track of number of images by row should be minimum of 32
        int squareName =0; //all squares are numbered 1-64 top left is 1, bottom right is 64
       
        squares=sortImages(squares);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int pokePosition = Integer.parseInt(piecesArray[pieceRow][2]);
           
                if(squareName == pokePosition){
                    String imagePath = piecesArray[pieceRow][0];  //save the file name to a string variable
                    String hpText = piecesArray[pieceRow][1]; //save the hp value to a string variable

                    ImageIcon icon = new ImageIcon(imagePath);
                    Image scaledImage = icon.getImage().getScaledInstance(40, 42, Image.SCALE_SMOOTH);

                    JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
                    JLabel textLabel = new JLabel(hpText, SwingConstants.CENTER);
                    textLabel.setForeground(Color.BLACK); // Make text black and center bottom
                    System.out.println("adding 1 piece:" + hpText);
                    JPanel piecePanel = new JPanel(new BorderLayout());
                    piecePanel.setOpaque(false); // false sets img bg transparent, image must already be a transparent png
                    piecePanel.add(pieceLabel, BorderLayout.CENTER);
                    piecePanel.add(textLabel, BorderLayout.SOUTH);

                    squares[row][col].setLayout(new BorderLayout());
                    squares[row][col].add(piecePanel, BorderLayout.CENTER);
               
               
                pieceRow++;
                }
                squareName++;
            }
        }

        revalidate(); // Ensure layout updates
        repaint(); // Refresh view
    }

    private void loadPieces() {
        piecesArray[0][0] = "squirtle.png"; piecesArray[0][1] = ""; piecesArray[0][2]="1";
        piecesArray[1][0] = "squirtle.png"; piecesArray[1][1] = ""; piecesArray[1][2]="9";
        piecesArray[2][0] = "squirtle.png"; piecesArray[2][1] = ""; piecesArray[2][2]="17";
        piecesArray[3][0] = "squirtle.png"; piecesArray[3][1] = ""; piecesArray[3][2]="25";
        piecesArray[4][0] = "squirtle.png"; piecesArray[4][1] = ""; piecesArray[4][2]="33";
        piecesArray[5][0] = "squirtle.png"; piecesArray[5][1] = ""; piecesArray[5][2]="41";
        piecesArray[6][0] = "eevee.png"; piecesArray[6][1] = ""; piecesArray[6][2]="2";
        piecesArray[7][0] = "vaporeon.png"; piecesArray[7][1] = ""; piecesArray[7][2]="3";
        piecesArray[8][0] = "espeon.png"; piecesArray[8][1] = ""; piecesArray[8][2]="11";
        piecesArray[9][0] = "umbreon.png"; piecesArray[9][1] = ""; piecesArray[9][2]="19";
        piecesArray[10][0] = "jolteon.png"; piecesArray[10][1] = ""; piecesArray[10][2]="27";
        piecesArray[11][0] = "flareon.png"; piecesArray[11][1] = ""; piecesArray[11][2]="36";
        piecesArray[12][0] = "leafeon.png"; piecesArray[12][1] = ""; piecesArray[12][2]="49";
        piecesArray[13][0] = "sylveon.png"; piecesArray[13][1] = ""; piecesArray[13][2]="54";
        piecesArray[14][0] = "glaceon.png"; piecesArray[14][1] = ""; piecesArray[14][2]="62";
        piecesArray[15][0] = "ice-stone.png"; piecesArray[15][1] = ""; piecesArray[15][2]="63";
        piecesArray[16][0] = "water-stone.png"; piecesArray[16][1] = ""; piecesArray[16][2]="4";
        piecesArray[17][0] = "fire-stone.png"; piecesArray[17][1] = ""; piecesArray[17][2]="37";
        piecesArray[18][0] = "thunder-stone.png"; piecesArray[18][1] = ""; piecesArray[18][2]="28";
        piecesArray[19][0] = "shiny-stone.png"; piecesArray[19][1] = ""; piecesArray[19][2]="55";
        piecesArray[20][0] = "dawn-stone.png"; piecesArray[20][1] = ""; piecesArray[20][2]="12";
        piecesArray[21][0] = "dusk-stone.png"; piecesArray[21][1] = ""; piecesArray[21][2]="20";
        piecesArray[22][0] = "leaf-stone.png"; piecesArray[22][1] = ""; piecesArray[22][2]="50";
        piecesArray[23][0] = "cool.png"; piecesArray[23][1] = ""; piecesArray[23][2]="10";
        piecesArray[24][0] = "wartortle.png"; piecesArray[24][1] = ""; piecesArray[24][2]="8";
        piecesArray[25][0] = "wartortle.png"; piecesArray[25][1] = ""; piecesArray[25][2]="16";
        piecesArray[26][0] = "wartortle.png"; piecesArray[26][1] = ""; piecesArray[26][2]="24";
        piecesArray[27][0] = "wartortle.png"; piecesArray[27][1] = ""; piecesArray[27][2]="32";
        piecesArray[28][0] = "wartortle.png"; piecesArray[28][1] = ""; piecesArray[28][2]="40";
        piecesArray[29][0] = "wartortle.png"; piecesArray[29][1] = ""; piecesArray[29][2]="48";
        piecesArray[30][0] = "pokeball.png"; piecesArray[30][1] = ""; piecesArray[30][2]="52";
        piecesArray[31][0] = "pokeball.png"; piecesArray[31][1] = ""; piecesArray[31][2]="51";
       
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard board = new GameBoard();
            board.setVisible(true);
        });
    }
}