import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class IntMatrix {
    private int[][] matrix;
    private data[] lines;
    ArrayList<Integer> lista;


    public IntMatrix() {
        lista=new ArrayList<>();
        try {
            lines = Files.lines(Paths.get("lines.txt"))
                    .map(line -> {
                        String[] parts = line.split(" ");
                        int row = Integer.parseInt(parts[0]);
                        int column = Integer.parseInt(parts[1]);
                        return new data(row, column);
                    })
                    .toArray(data[]::new);
        }
        catch (Exception e){
            System.out.println("Olvasas hiba");
        }
        matrix = new int[3][5];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = 1;
            }
        }

    }

    public void setIndexof(int i, int j, int value) {
        matrix[i][j] = value;
    }

    public void print() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int win(int i,int j,int db,int bet){
        if(db<3){
            return 0;
        }
        if(matrix[i][j]==0 || matrix[i][j]==1 || matrix[i][j]==2){
            return (db==3)?bet/5:((db==4)?bet:bet*5);
        }
        if (matrix[i][j]==3 || matrix[i][j]==4){
            return (db==3)?bet/5:((db==4)?bet*2:bet*10);
        }
        if (matrix[i][j]==5){
            return (db==3)?bet/2:((db==4)?bet*4:bet*15);
        }
        if (matrix[i][j]==6){
            return (db==3)?bet:((db==4)?bet*5:bet*20);
        }
        if (matrix[i][j]==7){
            return (db==3)?bet*5:((db==4)?bet*20:bet*50);
        }
        if (matrix[i][j]==8){
            return (db==3)?bet*20:((db==4)?bet*100:bet*1000);
        }
        return 0;
    }

    private int checkRows(int bet){
        lista.clear();
        int n=5;
        int rowI=0;
        int colI=0;
        int balance=0;
        for (int i = 0; i < 7; i++) {
            for(int j=0;j<5;j++){
                if(matrix[lines[i*n+j].getRow()][lines[i*n+j].getColumn()]!=9 && matrix[lines[i*n+j].getRow()][lines[i*n+j].getColumn()]!=10){
                    rowI=lines[i*n+j].getRow();
                    colI=lines[i*n+j].getColumn();
                    break;
                }
            }
            int db=0;
            for(int j=0;j<5;j++){
                if (matrix[lines[i*n+j].getRow()][lines[i*n+j].getColumn()]==matrix[rowI][colI]||
                matrix[lines[i*n+j].getRow()][lines[i*n+j].getColumn()]==9){
                    db++;
                }
                else {
                    break;
                }
            }
            if(win(rowI,colI,db,bet)>0){
                lista.add(i);
                lista.add(db);
                System.out.println(i+" ");
            }
            balance+=win(rowI,colI,db,bet);
        }
        return balance;
    }

    public ArrayList<Integer> getLista() {
        return lista;
    }

    public int checkLines(int bet) {
        return checkRows(bet);
    }

    public int checkFreespins(){
        int spins=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<5;j++){
                if(matrix[i][j] ==10){
                    spins++;
                }
            }
        }
        return spins;
    }

    public int checkBonusGifts(int bet){
        int bonus=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<5;j++){
                if(matrix[i][j] == 11){
                    bonus+=bet*2;
                }
            }
        }
        return bonus;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public data[] getLines() {
        return lines;
    }

    public int getValue(int i, int j){
        return matrix[i][j];
    }

}
