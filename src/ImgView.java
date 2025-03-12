import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class ImgView extends JPanel {
    private ImgMatrix image;
    BufferedImage BackGroundImage;
    FireAnimation fire;
    private boolean didWewon = false;
    private ArrayList<Integer> winList;
    private data[] lines;
    private Random rand;

    public void setWinList(ArrayList<Integer> winList) {
        this.winList = winList;
    }

    public ImgView(ImgMatrix img) {
        rand = new Random();
        readlines();
        image = img;
        this.setBackground(new Color(101, 5, 104, 255));
        this.setPreferredSize(new Dimension(950, 600));
        try {
            BackGroundImage = ImageIO.read(new File("BackGround/BackGround.png"));
        } catch (Exception e) {
            System.out.println("BackGroundRead hiba");
        }
    }

    public void readlines() {
        try {
            lines = Files.lines(Paths.get("lines.txt"))
                    .map(line -> {
                        String[] parts = line.split(" ");
                        int row = Integer.parseInt(parts[0]);
                        int column = Integer.parseInt(parts[1]);
                        return new data(row, column);
                    })
                    .toArray(data[]::new);
        } catch (Exception e) {
            System.out.println("Olvasas hiba");
        }
    }

    public void setDidWewon(boolean didWewon) {
        this.didWewon = didWewon;
    }

    public void method() {
        fire = new FireAnimation(image, this);
        this.add(fire);
        fire.FireThread(winList, lines);
        fire.repaint();
    }

    public FireAnimation getFire() {
        return fire;
    }

    public void method2() {
        remove(fire);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BackGroundImage, 0, 0, 950, 600, null);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (image.getImg(i, j).getY() <= 500) {
                    g.drawImage(image.getImg(i, j).getImage(), image.getImg(i, j).getX(), image.getImg(i, j).getY(), 150, 150, null);
                }
            }
        }

        if (didWewon) {
            Graphics2D g2 = (Graphics2D) g;
            int n = 5;
            for (int i = 0; i < winList.size(); i += 2) {
                g2.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
                g2.setStroke(new BasicStroke(8 + i));
                int lin = winList.get(i);
                int count = winList.get(i + 1);
                for (int j = 0; j < count; j++) {
                    g2.drawRect(lines[lin * n + j].getColumn() * 200, lines[lin * n + j].getRow() * 200, 150, 150);
                }
            }

        }

    }
}