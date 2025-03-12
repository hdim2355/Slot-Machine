import java.util.Random;

import static java.lang.Thread.sleep;

public class ImgController implements Runnable {
    private ImgView view;
    private Img[] column;
    private double[][] X=new double[2][12];

    public ImgController(Img[] column, ImgView view) {
        this.view = view;
        this.column = column;
        for(int i=0;i<12;i++){
            X[0][i]=i;
        }
        X[1][0]=0.15;
        X[1][1]=0.15;
        X[1][2]=0.15;
        X[1][3]=0.10;
        X[1][4]=0.10;
        X[1][5]=0.10;
        X[1][6]=0.05;
        X[1][7]=0.04;
        X[1][8]=0.03;
        X[1][9]=0.02;
        X[1][10]=0.01;
        X[1][11]=0.10;

//        X[1][0]=0.30;
//        X[1][1]=0.30;
//        X[1][2]=0.30;
//        X[1][3]=0.10;
        //X[1][11]=0.10;
        //X[1][0]=1.00;
    }

    @Override
    public void run() {
        for (int k = 0; k < 500; k++) {
            for (int i = 0; i < 3; i++) {
                if (column[i].getY() >= 600) {
                    column[i].setY(0);
                    //column[i].setImg(rand.nextInt(11));
                    double value=InversionBySequentialSearch(X);
                    column[i].setImg((int)value);
                }
                column[i].setY(column[i].getY() + 10);
            }
            try {
                //int value=k>35?k:0;
                //sleep(40+value);
                sleep(5);
            } catch (Exception e) {
                System.out.println("Hiba");
            }
            view.repaint();
        }
        for (int i = 0; i < 3; i++) {
            column[i].setY(i * 200);
        }
        view.repaint();
    }

    private static double InversionBySequentialSearch(double[][] X) {

        int row_count = X.length;
        int column_count = X[0].length;
        if (row_count != 2) {
            System.out.println("Wrong input discrete random variable!");
            return -1;
        }


        try {
            for (int j = 0; j < column_count; j++) {
                if (X[1][j] < 0.0 || X[1][j] > 1.0) {
                    System.out.println("Wrong input discrete random variable!");
                    return -2;
                }
            }
        } catch (Exception e) {
            System.out.println("Out of bounds!");
            return -3;
        }


        double total_probability = 0.0;
        try {
            for (int i = 0; i < X[1].length; i++) {
                total_probability += X[1][i];
            }
        } catch (Exception e) {
            System.out.println("Out of bounds!");
            return -4;
        }


        if (total_probability > 1.0001) {
            System.out.println("Wrong input discrete random variable!");
            return -5;
        }

        try {
            if (total_probability < 1.0) {
                X[1][column_count-1] =  1 - (total_probability - X[1][column_count - 1]);;
            }
        }
        catch (Exception e){
            System.out.println("Out of bounds!");
            return -7;
        }

        double[] cumulative_probabilities = new double[column_count];

        cumulative_probabilities[0] = X[1][0];
        for (int i = 1; i < cumulative_probabilities.length; i++) {
            cumulative_probabilities[i] = cumulative_probabilities[i - 1] + X[1][i];
        }

        Random rand = new Random();
        double u = rand.nextDouble();

        int i = 0;

        while (u > cumulative_probabilities[i]) {
            i++;
        }

        return X[0][i];
    }

}
