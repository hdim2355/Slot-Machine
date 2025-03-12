import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.Random;


public class SouthPanel extends JPanel {
    private JLabel label;
    private SouthButton lowerButton;
    private SouthButton spinButton;
    private SouthButton upperButton;
    private JLabel balanceLabel;
    private JLabel winLabel;
    private JLabel spinLabel;

    public SouthPanel(Integer bet) {
        setPreferredSize(new Dimension(1250, 135));
        setBackground(new Color(48, 73, 216));
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));

        balanceLabel = new JLabel("Balance:");
        balanceLabel.setBackground(Color.black);
        balanceLabel.setForeground(Color.white);
        balanceLabel.setOpaque(true);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        balanceLabel.setPreferredSize(new Dimension(200, 50));
        balanceLabel.setBorder(BorderFactory.createEtchedBorder());
        balanceLabel.setBorder(new BevelBorder(BevelBorder.RAISED,Color.red,Color.blue));
        add(balanceLabel);

        label = new JLabel("Bet:" + bet);
        label.setBackground(Color.black);
        label.setForeground(Color.white);
        label.setOpaque(true);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setPreferredSize(new Dimension(150, 50));
        label.setBorder(BorderFactory.createEtchedBorder());
        label.setBorder(new BevelBorder(BevelBorder.RAISED,Color.red,Color.blue));
        add(label);

        ImageIcon originalIcon = new ImageIcon("BackGround/Minusz.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        Icon lowIcon = new ImageIcon(scaledImage);
        ImageIcon original2Icon = new ImageIcon("BackGround/Plusz.png");
        Image scaled2Image = original2Icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        Icon moreIcon = new ImageIcon(scaled2Image);

        lowerButton = new SouthButton("-");
        lowerButton.getButton().setPreferredSize(new Dimension(60, 60));
        lowerButton.setIcon(lowIcon);
        lowerButton.setText("");

        spinButton = new SouthButton("Spin");
        spinButton.getButton().setFont(new Font("Arial", Font.BOLD, 30));
        spinButton.getButton().setPreferredSize(new Dimension(70, 50));
        spinButton.setBackground(Color.BLACK);
        spinButton.setBorder(new BevelBorder(BevelBorder.RAISED,Color.red,Color.blue));

        upperButton = new SouthButton("+");
        upperButton.setText("");
        upperButton.getButton().setPreferredSize(new Dimension(60, 60));
        upperButton.setIcon(moreIcon);




        winLabel=new JLabel("Win:");
        winLabel.setBackground(Color.black);
        winLabel.setForeground(Color.white);
        winLabel.setOpaque(true);
        winLabel.setFont(new Font("Arial", Font.BOLD, 20));
        winLabel.setPreferredSize(new Dimension(150, 50));
        winLabel.setBorder(BorderFactory.createEtchedBorder());
        winLabel.setBorder(new BevelBorder(BevelBorder.RAISED,Color.red,Color.blue));

        spinLabel=new JLabel("Spins left:");
        spinLabel.setBackground(Color.black);
        spinLabel.setForeground(Color.white);
        spinLabel.setOpaque(true);
        spinLabel.setFont(new Font("Arial", Font.BOLD, 20));
        spinLabel.setPreferredSize(new Dimension(400, 50));
        spinLabel.setBorder(BorderFactory.createEtchedBorder());
        spinLabel.setBorder(new BevelBorder(BevelBorder.RAISED,Color.red,Color.blue));

        add(spinLabel);
        add(winLabel);
        add(lowerButton);
        add(spinButton);
        add(upperButton);


    }

    public SouthButton getLowerButton() {
        return lowerButton;
    }

    public SouthButton getUpperButton() {
        return upperButton;
    }

    public SouthButton getSpinButton() {
        return spinButton;
    }

    public JLabel getLabel() {
        return label;
    }

    public JLabel getBalanceLabel() {
        return balanceLabel;
    }

    public JLabel getWinLabel(){
        return winLabel;
    }

    public JLabel getSpinLabel(){
        return spinLabel;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Random rand = new Random();
        g.setColor(Color.WHITE);
        for (int i = 0; i < 200; i++) {
            int ho = rand.nextInt(18);
            g.fillOval(rand.nextInt(1250), rand.nextInt(160), ho, ho);
        }

    }
}
