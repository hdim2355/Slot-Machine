import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class WestPanel extends JPanel {
    private JButton button;
    private Icon icon;

    private Icon moreIcon;

    private JButton quarter;
    private JButton half;
    private JLabel quarterlabel;
    private JLabel halflabel;

    private Icon onIcon;
    private Icon offIcon;

    private JButton sound;

    //private JComboBox box;

    public WestPanel() {
        ImageIcon originalIcon = new ImageIcon("BackGround/Double.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        ImageIcon original2Icon = new ImageIcon("BackGround/Tobbszorozo.png");
        Image scaled2Image = original2Icon.getImage().getScaledInstance(200, 120, Image.SCALE_SMOOTH);
        moreIcon = new ImageIcon(scaled2Image);


        ImageIcon original3Icon = new ImageIcon("BackGround/onimage.png");
        Image scaled3Image = original3Icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        onIcon = new ImageIcon(scaled3Image);

        ImageIcon original4Icon = new ImageIcon("BackGround/offimage.png");
        Image scaled4Image = original4Icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        offIcon = new ImageIcon(scaled4Image);

        setPreferredSize(new Dimension(160, 600));
        setBackground(new Color(48, 73, 216));

        sound = new JButton();
        sound.setBackground(new Color(48, 73, 216));
        sound.setPreferredSize(new Dimension(80, 80));
        sound.setFocusable(false);
        sound.setContentAreaFilled(false);
        sound.setOpaque(true);
        sound.setBorder(BorderFactory.createEtchedBorder());
        sound.setBorderPainted(false);
        sound.setIcon(onIcon);

        button = new JButton(icon);
        button.setBackground(new Color(48, 73, 216));
        button.setPreferredSize(new Dimension(120, 120));
        button.setFocusable(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setBorderPainted(false);
        add(button);

        half = new JButton(moreIcon);
        half.setBackground(new Color(48, 73, 216));
        half.setPreferredSize(new Dimension(200, 120));
        half.setFocusable(false);
        half.setContentAreaFilled(false);
        half.setOpaque(true);
        half.setBorder(BorderFactory.createEtchedBorder());
        half.setBorderPainted(false);

        halflabel = new JLabel("1000");
        halflabel.setFont(new Font("Arial", Font.BOLD, 15));
        halflabel.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));
        half.add(halflabel);

        quarter = new JButton(moreIcon);
        quarter.setBackground(new Color(48, 73, 216));
        quarter.setPreferredSize(new Dimension(200, 120));
        quarter.setFocusable(false);
        quarter.setContentAreaFilled(false);
        quarter.setOpaque(true);
        quarter.setBorder(BorderFactory.createEtchedBorder());
        quarter.setBorderPainted(false);

        quarterlabel = new JLabel("1000");
        quarterlabel.setFont(new Font("Arial", Font.BOLD, 15));
        quarterlabel.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));
        quarter.add(quarterlabel);

//        String[] numberofSpins={"1","10","20","50","100","1000"};
//        box=new JComboBox<>(numberofSpins);
//        box.setForeground(Color.WHITE);
//        box.setBackground(new Color(48, 73, 216));
//        box.setPreferredSize(new Dimension(80, 30));

        add(quarter);
        add(half);
        add(sound);
        //add(box);
    }

    public JButton getButton() {
        return button;
    }

    public JButton getHalf() {
        return half;
    }

    public JButton getQuarter() {
        return quarter;
    }

    public JLabel getHalflabel() {
        return halflabel;
    }

    public JLabel getQuarterlabel() {
        return quarterlabel;
    }

    public JButton getSound() {
        return sound;
    }

    public void setSoundOn() {
        sound.setIcon(onIcon);
    }

    public void setSoundOff() {
        sound.setIcon(offIcon);
    }

//    public JComboBox<String> getBox() {
//        return box;
//    }

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
