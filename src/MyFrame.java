import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame{

        MyFrame () {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("New Game");

        this.setTitle("Game of 15");
        this.add(button);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
        ImageIcon image = new ImageIcon("src/EA.jpg");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(Color.CYAN);




    }


    }




