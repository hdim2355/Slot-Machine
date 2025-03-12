import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import static java.lang.Thread.sleep;

public class DoublePanel extends JPanel {
    private BufferedImage blackImg;
    private BufferedImage redImg;
    private BufferedImage BackGroundImage;
    private JButton blackButton;
    private JButton redButton;
    private JButton stopButton;
    private JLabel label;
    private boolean black;
    private int win;

    public DoublePanel() {
        setPreferredSize(new Dimension(400,400));
        setBackground(new Color(6, 23, 74));
        blackButton=new JButton("Black");
        redButton=new JButton("Red");
        stopButton=new JButton("Stop");
        label=new JLabel();

        label.setText("Current Win:100");
        label.setFont(new Font("Arial", Font.BOLD, 40));
        label.setForeground(Color.BLACK);
        label.setPreferredSize(new Dimension(950,400));
        label.setBorder(BorderFactory.createEmptyBorder(320, 350, 0, 0));

        blackButton.setForeground(Color.BLACK);
        blackButton.setBackground(Color.BLACK);
        blackButton.setPreferredSize(new Dimension(80,50));
        blackButton.setBorder(null);
        blackButton.setFocusable(false);
        blackButton.setContentAreaFilled(false);
        blackButton.setOpaque(true);

        redButton.setForeground(Color.RED);
        redButton.setBackground(Color.RED);
        redButton.setPreferredSize(new Dimension(80,50));
        redButton.setBorder(null);
        redButton.setFocusable(false);
        redButton.setContentAreaFilled(false);
        redButton.setOpaque(true);

        stopButton.setForeground(Color.BLACK);
        stopButton.setPreferredSize(new Dimension(80,50));
        stopButton.setBorder(null);
        stopButton.setFocusable(false);
        stopButton.setContentAreaFilled(false);
        stopButton.setOpaque(true);

        add(label);
        add(redButton);
        add(blackButton);
        add(stopButton);

        try {
            blackImg = ImageIO.read(new File("double/black.png"));
            redImg = ImageIO.read(new File("double/red.png"));
            BackGroundImage = ImageIO.read(new File("BackGround/BackGround.png"));
        } catch (Exception e) {
            System.out.println("kartya hiba");
        }

        //redButton.addActionListener(e->redCheck(win));

    }

    public void setWin(int win){
        this.win=win;
    }

    public int getWin(){
        return win;
    }

    public void redCheck(SouthPanel panel,CardLayout layout,JPanel CenterPanel){

        System.out.println("kezdo:"+win);
        //new Thread(() -> {
            for (int i = 0; i < 25; i++) {
                repaint();
                try {
                    sleep(30);
                } catch (Exception exception) {
                    System.out.println("Hiba");
                }
            }
            if (!isBlack()) {
                win *= 2;
                panel.getWinLabel().setText("Win:"+win);
            } else {
                try {
                    sleep(800);
                } catch (Exception exception) {
                    System.out.println("Hiba");
                }
                win = 0;
                panel.getWinLabel().setText("Win:"+win);
                layout.show(CenterPanel, "P1");
            }
        //}).start();
        System.out.println("vegso:"+win);
    }

    public void blackCheck(SouthPanel panel,CardLayout layout,JPanel CenterPanel) {
        //new Thread(() -> {
            for (int i = 0; i < 25; i++) {
                repaint();
                try {
                    sleep(30);
                } catch (Exception exception) {
                    System.out.println("Hiba");
                }
            }
            if (isBlack()) {
                win *= 2;
                panel.getWinLabel().setText("Win:" + win);
            } else {
                try {
                    sleep(800);
                } catch (Exception exception) {
                    System.out.println("Hiba");
                }
                win = 0;
                panel.getWinLabel().setText("Win:" + win);
                layout.show(CenterPanel, "P1");
            }
        //}).start();
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public JButton getRedButton() {
        return redButton;
    }

    public JButton getBlackButton() {
        return blackButton;
    }

    public JLabel getLabel() {
        return label;
    }

    public boolean isBlack() {
        return black;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Random rand=new Random();
        g.drawImage(BackGroundImage, 0, 0, 950, 600, null);
        if (rand.nextInt(2) == 1) {
            black=true;
            g.drawImage(blackImg,380,100,200,200,null);
        } else {
            black=false;
            g.drawImage(redImg,380,100,200,200,null);
        }
    }

}
