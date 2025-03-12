import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ConfirmPanel extends JPanel {
    private JButton yesButton;
    private JButton noButton;
    private BufferedImage BackGroundImage;
    private JLabel label;


    public ConfirmPanel() {
        try {
            BackGroundImage = ImageIO.read(new File("BackGround/BackGround.png"));
        } catch (Exception e) {
            System.out.println("kartya hiba");
        }
        setLayout(new FlowLayout(FlowLayout.CENTER,50,50));
        label=new JLabel("Are you sure,that you want to buy X free spins for Y:");
        label.setBackground(Color.black);
        label.setForeground(Color.white);
        label.setOpaque(true);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setPreferredSize(new Dimension(800, 50));
        label.setBorder(BorderFactory.createEtchedBorder());
        label.setBorder(new BevelBorder(BevelBorder.RAISED,Color.red,Color.blue));

        yesButton = new JButton("Yes");
        yesButton.setForeground(Color.BLACK);
        yesButton.setPreferredSize(new Dimension(80, 50));
        yesButton.setBorder(null);
        yesButton.setFocusable(false);
        yesButton.setContentAreaFilled(false);
        yesButton.setOpaque(true);

        noButton = new JButton("No");
        noButton.setForeground(Color.BLACK);
        noButton.setPreferredSize(new Dimension(80, 50));
        noButton.setBorder(null);
        noButton.setFocusable(false);
        noButton.setContentAreaFilled(false);
        noButton.setOpaque(true);


        add(label);
        add(yesButton);
        add(noButton);

        setPreferredSize(new Dimension(250,250));
        setBackground(new Color(6, 23, 74));
        setForeground(Color.WHITE);

    }

    public JLabel getLabel() {
        return label;
    }

    public JButton getYesButton() {
        return yesButton;
    }

    public JButton getNoButton() {
        return noButton;
    }
}
