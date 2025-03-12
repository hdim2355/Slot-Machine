import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.Random;

public class EastPanel extends JPanel {
    private JButton button;

    private JButton doble;
    private JButton quadra;
    private JLabel doblelabel;
    private JLabel quadralabel;

    private Icon icon;
    private Icon moreIcon;


    private JLabel bonuslabel;

    public EastPanel() {
        ImageIcon originalIcon = new ImageIcon("BackGround/Free.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);

        ImageIcon original2Icon = new ImageIcon("BackGround/Tobbszorozo.png");
        Image scaled2Image = original2Icon.getImage().getScaledInstance(200, 120, Image.SCALE_SMOOTH);
        moreIcon = new ImageIcon(scaled2Image);

        setPreferredSize(new Dimension(172, 600));
        setBackground(new Color(48, 73, 216));

        button = new JButton(icon);
        button.setBackground(new Color(48, 73, 216));
        button.setPreferredSize(new Dimension(120, 120));
        button.setFocusable(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setBorderPainted(false);
        add(button);

        doble = new JButton(moreIcon);
        doble.setBackground(new Color(48, 73, 216));
        doble.setPreferredSize(new Dimension(200, 120));
        doble.setFocusable(false);
        doble.setContentAreaFilled(false);
        doble.setOpaque(true);
        doble.setBorder(BorderFactory.createEtchedBorder());
        doble.setBorderPainted(false);

        doblelabel = new JLabel("1000");
        doblelabel.setFont(new Font("Arial", Font.BOLD, 15));
        doblelabel.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));
        doble.add(doblelabel);

        quadra = new JButton(moreIcon);
        quadra.setBackground(new Color(48, 73, 216));
        quadra.setPreferredSize(new Dimension(200, 120));
        quadra.setFocusable(false);
        quadra.setContentAreaFilled(false);
        quadra.setOpaque(true);
        quadra.setBorder(BorderFactory.createEtchedBorder());
        quadra.setBorderPainted(false);

        quadralabel = new JLabel("1000");
        quadralabel.setFont(new Font("Arial", Font.BOLD, 15));
        quadralabel.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));
        quadra.add(quadralabel);

        bonuslabel = new JLabel("0");
        bonuslabel.setPreferredSize(new Dimension(60, 60));
        bonuslabel.setBackground(Color.BLACK);
        bonuslabel.setOpaque(true);
        bonuslabel.setForeground(Color.WHITE);
        bonuslabel.setHorizontalAlignment(0);
        bonuslabel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red,
                Color.blue));

        add(doble);
        add(quadra);
        add(bonuslabel);
    }

    public JButton getButton() {
        return button;
    }

    public JButton getDoble() {
        return doble;
    }

    public JButton getQuadra() {
        return quadra;
    }

    public JLabel getDoblelabel() {
        return doblelabel;
    }

    public JLabel getQuadralabel() {
        return quadralabel;
    }

    public JLabel getBonuslabel() {
        return bonuslabel;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Random rand = new Random();
        g.setColor(Color.WHITE);
        for (int i = 0; i < 100; i++) {
            int ho = rand.nextInt(18);
            g.fillOval(rand.nextInt(160), rand.nextInt(600), ho, ho);
        }
    }

}

