import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class FreeSpinPanel extends JPanel {
    private BufferedImage BackGroundImage;

    private ImageIcon Sc3;
    private ImageIcon Sc4;
    private ImageIcon Sc5;
    private ImageIcon RandSc;

    private JButton buttonSc3;
    private JButton buttonSc4;
    private JButton buttonSc5;
    private JButton buttonRandSc;

    private JLabel labelSc3;
    private JLabel labelSc4;
    private JLabel labelSc5;
    private JLabel labelRandSc;

    public FreeSpinPanel(){
        setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20));
        try {
            BackGroundImage = ImageIO.read(new File("BackGround/BackGround.png"));
        } catch (Exception e) {
            System.out.println("kartya hiba");
        }

        ImageIcon originalIcon = new ImageIcon("BackGround/Scatters_Spins_3.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        Sc3 = new ImageIcon(scaledImage);

        ImageIcon original2Icon = new ImageIcon("BackGround/Scatters_Spins_4.png");
        Image scaled2Image = original2Icon.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        Sc4 = new ImageIcon(scaled2Image);

        ImageIcon original3Icon = new ImageIcon("BackGround/Scatters_Spins_5.png");
        Image scaled3Image = original3Icon.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        Sc5 = new ImageIcon(scaled3Image);

        ImageIcon original4Icon = new ImageIcon("BackGround/rand_Scs.png");
        Image scaled4Image = original4Icon.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        RandSc = new ImageIcon(scaled4Image);

        labelSc3=new JLabel("Spins left:");
        labelSc3.setBackground(Color.black);
        labelSc3.setForeground(Color.white);
        labelSc3.setOpaque(true);
        labelSc3.setFont(new Font("Arial", Font.BOLD, 20));
        labelSc3.setPreferredSize(new Dimension(400, 50));
        labelSc3.setBorder(BorderFactory.createEtchedBorder());
        labelSc3.setBorder(new BevelBorder(BevelBorder.RAISED,Color.red,Color.blue));

        labelSc4=new JLabel("Spins left:");
        labelSc4.setBackground(Color.black);
        labelSc4.setForeground(Color.white);
        labelSc4.setOpaque(true);
        labelSc4.setFont(new Font("Arial", Font.BOLD, 20));
        labelSc4.setPreferredSize(new Dimension(400, 50));
        labelSc4.setBorder(BorderFactory.createEtchedBorder());
        labelSc4.setBorder(new BevelBorder(BevelBorder.RAISED,Color.red,Color.blue));

        labelSc5=new JLabel("Spins left:");
        labelSc5.setBackground(Color.black);
        labelSc5.setForeground(Color.white);
        labelSc5.setOpaque(true);
        labelSc5.setFont(new Font("Arial", Font.BOLD, 20));
        labelSc5.setPreferredSize(new Dimension(400, 50));
        labelSc5.setBorder(BorderFactory.createEtchedBorder());
        labelSc5.setBorder(new BevelBorder(BevelBorder.RAISED,Color.red,Color.blue));

        labelRandSc=new JLabel("Spins left:");
        labelRandSc.setBackground(Color.black);
        labelRandSc.setForeground(Color.white);
        labelRandSc.setOpaque(true);
        labelRandSc.setFont(new Font("Arial", Font.BOLD, 20));
        labelRandSc.setPreferredSize(new Dimension(400, 50));
        labelRandSc.setBorder(BorderFactory.createEtchedBorder());
        labelRandSc.setBorder(new BevelBorder(BevelBorder.RAISED,Color.red,Color.blue));


        buttonSc3 = new JButton(Sc3);
        buttonSc3.setBackground(new Color(48, 73, 216));
        buttonSc3.setPreferredSize(new Dimension(400, 200));
        buttonSc3.setFocusable(false);
        buttonSc3.setContentAreaFilled(false);
        buttonSc3.setOpaque(true);
        buttonSc3.setBorder(BorderFactory.createEtchedBorder());
        buttonSc3.setBorderPainted(false);
        add(buttonSc3);

        buttonSc4 = new JButton(Sc4);
        buttonSc4.setBackground(new Color(48, 73, 216));
        buttonSc4.setPreferredSize(new Dimension(400, 200));
        buttonSc4.setFocusable(false);
        buttonSc4.setContentAreaFilled(false);
        buttonSc4.setOpaque(true);
        buttonSc4.setBorder(BorderFactory.createEtchedBorder());
        buttonSc4.setBorderPainted(false);
        add(buttonSc4);

        add(labelSc3);
        add(labelSc4);

        buttonSc5 = new JButton(Sc5);
        buttonSc5.setBackground(new Color(48, 73, 216));
        buttonSc5.setPreferredSize(new Dimension(400, 200));
        buttonSc5.setFocusable(false);
        buttonSc5.setContentAreaFilled(false);
        buttonSc5.setOpaque(true);
        buttonSc5.setBorder(BorderFactory.createEtchedBorder());
        buttonSc5.setBorderPainted(false);
        add(buttonSc5);

        buttonRandSc = new JButton(RandSc);
        buttonRandSc.setBackground(new Color(48, 73, 216));
        buttonRandSc.setPreferredSize(new Dimension(400, 200));
        buttonRandSc.setFocusable(false);
        buttonRandSc.setContentAreaFilled(false);
        buttonRandSc.setOpaque(true);
        buttonRandSc.setBorder(BorderFactory.createEtchedBorder());
        buttonRandSc.setBorderPainted(false);
        add(buttonRandSc);

        add(labelSc5);
        add(labelRandSc);

        setPreferredSize(new Dimension(250,250));
        setBackground(new Color(6, 23, 74));
        setForeground(Color.WHITE);

    }


    public JLabel getLabelSc3() {
        return labelSc3;
    }

    public JLabel getLabelSc4() {
        return labelSc4;
    }

    public JLabel getLabelSc5() {
        return labelSc5;
    }

    public JLabel getLabelRandSc() {
        return labelRandSc;
    }

    public JButton getButtonRandSc() {
        return buttonRandSc;
    }

    public JButton getButtonSc3() {
        return buttonSc3;
    }

    public JButton getButtonSc4() {
        return buttonSc4;
    }

    public JButton getButtonSc5() {
        return buttonSc5;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BackGroundImage, 0, 0, 950, 600, null);
    }

}
