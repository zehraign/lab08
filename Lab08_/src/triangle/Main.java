package triangle;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
    	
        JFrame frame = new JFrame();
    

      
        Dimension triangleSize = new Dimension(400, 400);

     
        Triangle triangle = new Triangle();

        
        Image triangleImage = triangle.getImage(triangleSize);
        ImageIcon icon = new ImageIcon(triangleImage);
        JLabel label = new JLabel(icon);

  
        frame.getContentPane().add(label, BorderLayout.CENTER);

     
        frame.pack();
        frame.setVisible(true);
    }
}
