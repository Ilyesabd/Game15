import javax.swing.;
import java.awt.;
import java.util.ArrayList;

public class MyFrame extends JFrame {

    MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Game of 15");
        this.setLayout(new GridLayout(4, 4));

        ArrayList<JButton> buttons = new ArrayList<>();

        for (int i = 1; i <= 15; i++) {
            JButton button = new JButton(Integer.toString(i));
            buttons.add(button);
            this.add(button);
        }

        JButton emptyButton = new JButton("");
        buttons.add(emptyButton);
        this.add(emptyButton);

        this.pack();
        this.setLocationRelativeTo(null);
        ImageIcon image = new ImageIcon("src/EA.jpg");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(Color.CYAN);

        this.setVisible(true);
    }
}
