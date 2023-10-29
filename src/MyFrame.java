import javax.swing.*;


public class MyFrame{

    public static void main(String[] args) {

        JFrame frame = new JFrame("Game of 15");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("New Game");
        frame.add(button);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);



    }


    }




