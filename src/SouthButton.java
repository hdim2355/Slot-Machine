import javax.swing.*;
import java.awt.*;

public class SouthButton extends JButton {

    public SouthButton(String text){
        setText(text);
        setFont(new Font("Arial",Font.BOLD,20));
        setPreferredSize(new Dimension(135,50));
        setBorder(null);
        setForeground(Color.white);
        setBackground(new Color(48, 73, 216));
        setFocusable(false);
        setContentAreaFilled(false);
        setOpaque(true);
    }

    public JButton getButton(){
        return this;
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }

}

