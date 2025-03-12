public class ImgMatrix {

    private Img[][] matrix;

    public ImgMatrix() {
        matrix = new Img[3][5];
        for(int i=0;i<3;i++){
            for(int j=0;j<5;j++){
                matrix[i][j]=new Img(j*200,i*200);
            }
        }
    }

    public Img[][] getImage(){
        return matrix;
    }


    public Img[] getColumn(int j){
        Img[] Column=new Img[3];
        for(int i=0;i<3;i++){
            Column[i]=matrix[i][j];
        }
        return Column;
    }


    public Img getImg(int i, int j) {
        return matrix[i][j];
    }

    public IntMatrix getRealValue(){
        IntMatrix realValues=new IntMatrix();
        for(int i=0;i<3;i++){
            for(int j=0;j<5;j++){
                realValues.setIndexof(i,j,matrix[i][j].getRealValue());
            }
        }
        return realValues;
    }



}
