import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

public class GameBoard extends JFrame {
    public int SIZE = 8;
    private JPanel[][] squares = new JPanel[SIZE][SIZE];
    private ArrayList<ArrayList<String>> piecesArray;

    public GameBoard() {
        setTitle("Chessboard");
        setSize(750, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                squares[row][col] = new JPanel();
                squares[row][col].setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);
                add(squares[row][col]);
            }
        }

        loadPieces();
        mergeSort(piecesArray, 0, piecesArray.size() - 1);
        populateBoard();
    }

    private void loadPieces() {
        piecesArray = new ArrayList<>();
        // Load Black Pieces
        for (int i = 0; i < 8; i++) {
            piecesArray.add(new ArrayList<>(Arrays.asList("B_Pawn.png", "" + (8 * 6 + i))));
        }
        piecesArray.add(new ArrayList<>(Arrays.asList("B_Rook.png", "0")));
        piecesArray.add(new ArrayList<>(Arrays.asList("B_Rook.png", "7")));
        piecesArray.add(new ArrayList<>(Arrays.asList("B_Knight.png", "1")));
        piecesArray.add(new ArrayList<>(Arrays.asList("B_Knight.png", "6")));
        piecesArray.add(new ArrayList<>(Arrays.asList("B_Bishop.png", "2")));
        piecesArray.add(new ArrayList<>(Arrays.asList("B_Bishop.png", "5")));
        piecesArray.add(new ArrayList<>(Arrays.asList("B_Queen.png", "3")));
        piecesArray.add(new ArrayList<>(Arrays.asList("B_King.png", "4")));

        // Load White Pieces
        for (int i = 0; i < 8; i++) {
            piecesArray.add(new ArrayList<>(Arrays.asList("W_Pawn.png", "" + (8 * 1 + i))));
        }
        piecesArray.add(new ArrayList<>(Arrays.asList("W_Rook.png", "56")));
        piecesArray.add(new ArrayList<>(Arrays.asList("W_Rook.png", "63")));
        piecesArray.add(new ArrayList<>(Arrays.asList("W_Knight.png", "57")));
        piecesArray.add(new ArrayList<>(Arrays.asList("W_Knight.png", "62")));
        piecesArray.add(new ArrayList<>(Arrays.asList("W_Bishop.png", "58")));
        piecesArray.add(new ArrayList<>(Arrays.asList("W_Bishop.png", "61")));
        piecesArray.add(new ArrayList<>(Arrays.asList("W_Queen.png", "59")));
        piecesArray.add(new ArrayList<>(Arrays.asList("W_King.png", "60")));
    }

    private void mergeSort(ArrayList<ArrayList<String>> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }

    private void merge(ArrayList<ArrayList<String>> list, int left, int mid, int right) {
        ArrayList<ArrayList<String>> temp = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            temp.add(new ArrayList<>(list.get(i)));
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp.get(i - left).get(0).compareTo(temp.get(j - left).get(0)) <= 0) {
                list.set(k, temp.get(i - left));
                i++;
            } else {
                list.set(k, temp.get(j - left));
                j++;
            }
            k++;
        }

        while (i <= mid) {
            list.set(k, temp.get(i - left));
            i++;
            k++;
        }

        while (j <= right) {
            list.set(k, temp.get(j - left));
            j++;
            k++;
        }
    }

    private void populateBoard() {
        for (ArrayList<String> piece : piecesArray) {
            int position = Integer.parseInt(piece.get(1));
            int row = position / SIZE;
            int col = position % SIZE;

            ImageIcon icon = new ImageIcon(getClass().getResource("/" + piece.get(0)));
            JLabel label = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH)));
            squares[row][col].add(label);
        }

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard board = new GameBoard();
            board.setVisible(true);
        });
    }
}
