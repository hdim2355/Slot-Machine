import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

public class Img {
    private BufferedImage img;
    private int x;
    private int y;
    private int realValue;


    private static int currentLineIndex = 0;

    public Img(int x1, int y1) {
        try {
            img = ImageIO.read(new File("img/Scatter.png"));
            realValue=10;
            x = x1;
            y = y1;
            try {
                String line = Files.lines(Paths.get("Saves/Saves.txt"))
                        .skip(currentLineIndex)
                        .findFirst()
                        .orElse("");

                if (!line.isEmpty()) {
                    realValue = Integer.parseInt(line.trim());
                    currentLineIndex++;
                }
            } catch (Exception e) {
                System.out.println("Hiba");
            }

        } catch (Exception e) {
            System.out.println("Hiba");
        }
        setImg(realValue);
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public BufferedImage getImage() {
        return img;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRealValue(){
        return this.realValue;
    }

    public void setImg(int value) {
        try {
            switch (value) {
                case 0:
                    img = ImageIO.read(new File("img/Csengo.png"));
                    realValue=0;
                    break;
                case 1:
                    img = ImageIO.read(new File("img/Fa.png"));
                    realValue=1;
                    break;
                case 2:
                    img = ImageIO.read(new File("img/Rudolf.png"));
                    realValue=2;
                    break;
                case 3:
                    img = ImageIO.read(new File("img/Disz.png"));
                    realValue=3;
                    break;
                case 4:
                    img = ImageIO.read(new File("img/Masni.png"));
                    realValue=4;
                    break;
                case 5:
                    img = ImageIO.read(new File("img/Gomb.png"));
                    realValue=5;
                    break;
                case 6:
                    img = ImageIO.read(new File("img/Korcsolya.png"));
                    realValue=6;
                    break;
                case 7:
                    img = ImageIO.read(new File("img/Masni_2.png"));
                    realValue=7;
                    break;
                case 8:
                    img = ImageIO.read(new File("img/Mikulas.png"));
                    realValue=8;
                    break;
                case 9:
                    img = ImageIO.read(new File("img/Ajandek.png"));
                    realValue=9;
                    break;
                case 10:
                    img = ImageIO.read(new File("img/Scatter.png"));
                    realValue=10;
                    break;
                case 11:
                    img= ImageIO.read(new File("img/BonusBomb.png"));
                    realValue=11;
                    break;
            }
        }
        catch (Exception e){
            System.out.println("setImg hiba");
        }
    }
}
