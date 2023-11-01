import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyFrame extends JFrame {

    private JButton[][] buttons;
    private int emptyRow, emptyCol;

    MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Skapa en panel för titeln och New Game-knappen med FlowLayout
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("Game of 15");
        titlePanel.add(titleLabel);

        JButton newGame = new JButton("New Game");
        titlePanel.add(newGame);

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shufflePuzzle();
            }
        });

        this.add(titlePanel, BorderLayout.NORTH);

        JPanel puzzlePanel = new JPanel();
        puzzlePanel.setLayout(new GridLayout(4, 4));
        buttons = new JButton[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JButton button = new JButton("");
                buttons[i][j] = button;
                puzzlePanel.add(button);
                final int row = i;
                final int col = j;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        moveButton(row, col);
                        checkWin();
                    }
                });
            }
        }

        emptyRow = 3;
        emptyCol = 3;

        this.add(puzzlePanel, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null);
        ImageIcon image = new ImageIcon("src/EA.jpg");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(Color.CYAN);
        this.setVisible(true);
        shufflePuzzle();
    }

    private void shufflePuzzle() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == emptyRow && j == emptyCol) {
                    buttons[i][j].setText("");
                } else {
                    buttons[i][j].setText(Integer.toString(numbers.get(count)));
                    count++;
                }
            }
        }
    }

    private void moveButton(int row, int col) {
        int rowDistance = Math.abs(row - emptyRow);
        int colDistance = Math.abs(col - emptyCol);

        if ((rowDistance == 1 && colDistance == 0) || (rowDistance == 0 && colDistance == 1)) {
            buttons[emptyRow][emptyCol].setText(buttons[row][col].getText());
            buttons[row][col].setText("");
            emptyRow = row;
            emptyCol = col;
        }
    }

    private void checkWin() {
        int num = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ((i == 0 && j == 0 && buttons[i][j].getText().equals("")) || (i == 3 && j == 3 && buttons[i][j].getText().equals(""))) {
                    continue; // Tom rutan på index 0,0 och 3,3 är tillåtet
                }
                if (!buttons[i][j].getText().equals(Integer.toString(num))) {
                    return; // Spelet är inte klart
                }
                num++;
            }
        }

        JOptionPane.showMessageDialog(this, "Grattis! Du vann!");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MyFrame puzzleGame = new MyFrame();
        });
    }
}
