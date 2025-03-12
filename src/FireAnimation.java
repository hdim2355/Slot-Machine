import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FireAnimation extends ImgView {
    private static final int FIRE_COUNT = 35;
    private final FireInstance[] fires;
    private ImgMatrix img;
    private ImgView view;
    private boolean initialized = false;



    public FireAnimation(ImgMatrix img, ImgView view) {
        super(img);
        this.fires = new FireInstance[FIRE_COUNT];
        this.img = img;
        this.view = view;

    }

    public void setBigwin(boolean bigwin) {
        try {
            for (FireInstance fire : fires) {
                if (fire != null) {
                    fire.setBigwin(bigwin);
                }
            }
        } catch (Exception e) {
            System.out.println("Hiba");
        }
    }

    public void FireThread(ArrayList<Integer> winList, data[] lines) {

        new Thread(() -> {
            List<Thread> threads = new ArrayList<>();
            int index = 0;
            int n = 5;

            for (int i = 0; i < winList.size(); i += 2) {
                int lin = winList.get(i);
                int count = winList.get(i + 1);
                for (int j = 0; j < count; j++) {
                    fires[index] = new FireInstance(lines[lin * n + j].getColumn() * 200, lines[lin * n + j].getRow() * 200, view, img);
                    Thread thread = new Thread(fires[index]);
                    threads.add(thread);
                    thread.start();
                    index++;
                }
            }

            initialized = true;


            Timer repaintTimer = new Timer(30, e -> repaint());
            repaintTimer.start();

            SwingUtilities.invokeLater(this::repaint);


            new Thread(() -> {
                for (Thread thread : threads) {
                    try {
                        thread.join();
                    } catch (Exception e) {
                        System.out.println("Szal hiba");
                    }
                }
                SwingUtilities.invokeLater(() -> view.method2());
            }).start();
        }).start();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!initialized) return;

        int db = 0;
        for (FireInstance fire : fires) {
            if (fire != null) {
                fire.draw(g);
                db++;
            }
        }
        //System.out.println(db);
    }
}