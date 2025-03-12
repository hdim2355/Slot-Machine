import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static java.lang.Thread.sleep;

class FireInstance implements Runnable {
    private static final int FIRE_WIDTH = 75, FIRE_HEIGHT = 75;
    private static final int SCALE = 2;
    private final int[][] firePixels = new int[FIRE_WIDTH][FIRE_HEIGHT];
    private final Random random = new Random();
    private final Color[] palette;
    private final int X_CO, Y_CO;
    private ImgView view;
    private ImgMatrix image;
    private boolean bigwin = true;

    public FireInstance(int x, int y, ImgView view, ImgMatrix image) {
        this.X_CO = x;
        this.Y_CO = y;
        this.palette = generateFirePalette();
        initializeFireBase();
        this.view = view;
        this.image = image;
    }

    public void setBigwin(boolean bigwin) {
        this.bigwin = bigwin;
    }

    private Color[] generateFirePalette() {
        Color[] colors = new Color[256];
        for (int i = 0; i < 256; i++) {
            int r = Math.min(50, i / 3);
            int g = Math.min(255, (int) (i * 1.5));
            int b = Math.min(255, i * 4);
            colors[i] = new Color(r, g, b);
        }
        return colors;
    }

    private void initializeFireBase() {
        for (int x = 0; x < FIRE_WIDTH; x++) {
            firePixels[x][FIRE_HEIGHT - 1] = 125;
        }
    }

    private void updateFire() {
        for (int y = 1; y < FIRE_HEIGHT; y++) {
            for (int x = 1; x < FIRE_WIDTH - 1; x++) {
                int decay = random.nextInt(4);
                int newX = x + random.nextInt(3) - 1;
                int newY = Math.max(y - 1, 0);
                firePixels[newX][newY] = Math.max(firePixels[x][y] - decay, 0);
            }
        }
    }

    public void draw(Graphics g) {
        //view.repaint();
        for (int y = 0; y < FIRE_HEIGHT; y++) {
            for (int x = 0; x < FIRE_WIDTH; x++) {
                g.setColor(palette[firePixels[x][y]]);
                g.fillRect(x * SCALE + X_CO, y * SCALE + Y_CO, SCALE, SCALE);
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (image.getImg(i, j).getY() <= 500) {
                    g.drawImage(image.getImg(i, j).getImage(), image.getImg(i, j).getX(), image.getImg(i, j).getY(), 150, 150, null);
                }
            }
        }

    }

    @Override
    public void run() {
        while (bigwin) {
            try {
                sleep(30);
            } catch (Exception e) {
                System.out.println("Error");
            }
            updateFire();
        }
    }
}
