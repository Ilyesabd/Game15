import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class MyFrame extends JFrame implements ActionListener {

    private JButton[][] buttons;
    private int emptyButtonX, emptyButtonY;

    MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Game of 15");

        this.setLayout(new BorderLayout());

        JPanel newGamePanel = new JPanel(new BorderLayout());
        JButton newGameButton = new JButton("New Game");
        newGameButton.setBackground(new Color(59832));
        newGameButton.addActionListener(e -> shuffleTiles());
        newGamePanel.add(newGameButton, BorderLayout.NORTH);
        this.add(newGamePanel, BorderLayout.NORTH);
        ImageIcon image = new ImageIcon("src/EA.jpg");
        this.setIconImage(image.getImage());


        JPanel gamePanel = new JPanel(new GridLayout(4, 4));


        buttons = new JButton[4][4];


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j] = new JButton(Integer.toString(i * 4 + j + 1));
                buttons[i][j].addActionListener(this);
                gamePanel.add(buttons[i][j]);
            }
        }

        emptyButtonX = 3;
        emptyButtonY = 3;
        buttons[emptyButtonX][emptyButtonY].setVisible(false);
        shuffleTiles();

        this.add(gamePanel, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (buttons[i][j] == clickedButton) {
                    if (isValidMove(i, j)) {
                        swapButtons(i, j);
                        if (isGameFinished()) {
                            JOptionPane.showMessageDialog(this, "Grattis, du har klarat det!");
                        }
                    }
                }
            }
        }
    }

    private boolean isValidMove(int x, int y) {
        return (Math.abs(emptyButtonX - x) + Math.abs(emptyButtonY - y) == 1);
    }

    private void swapButtons(int x, int y) {
        buttons[emptyButtonX][emptyButtonY].setText(buttons[x][y].getText());
        buttons[x][y].setVisible(false);
        buttons[emptyButtonX][emptyButtonY].setVisible(true);
        emptyButtonX = x;
        emptyButtonY = y;
    }


   private void shuffleTiles() {
        ArrayList<Integer> values = new ArrayList<>();

        for (int i = 1; i <= 15; i++) {
            values.add(i);
        }

        Collections.shuffle(values);

        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i != 3 || j != 3) {
                    buttons[i][j].setText(Integer.toString(values.get(index++)));
                    buttons[i][j].setVisible(true);
                } else {
                    buttons[i][j].setVisible(false);
                    emptyButtonX = i;
                    emptyButtonY = j;
                }
            }
        }
    }

    private boolean isGameFinished() {
        int count = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 3 && j == 3 || i == 0 && j == 0) {
                    if (buttons[i][j].isVisible()) {
                        return false;
                    }
                } else {
                    String buttonText = buttons[i][j].getText();
                    int buttonValue = Integer.parseInt(buttonText);
                    if (buttonValue != count) {
                        return false;
                    }
                    count++;
                }
            }
        }
        return true;
    }


}
