package triangle;

import resizable.ResizableImage;

import java.awt.*;
import java.awt.image.BufferedImage;

import static resizable.Debug.print;

/**
 * Implement your Sierpinski Triangle here.
 *
 *
 * You only need to change the drawTriangle
 * method!
 *
 *
 * If you want to, you can also adapt the
 * getResizeImage() method to draw a fast
 * preview.
 *
 */
public class Triangle implements ResizableImage {
    int drawTriangle = 0;
    Dimension triangleSize;
    /**
     * change this method to implement the triangle!
     * @param size the outer bounds of the triangle
     * @return an Image containing the Triangle
     */
   
    private BufferedImage drawTriangle(Dimension size) {
    	
       
    	
    	
    	print("drawTriangle: " + ++drawTriangle + " size: " + size);

        BufferedImage bufferedImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gBuffer = (Graphics2D) bufferedImage.getGraphics();
        gBuffer.setColor(Color.black);
        gBuffer.fillRect(0, 0, size.width, size.height);

        gBuffer.setColor(Color.black);

        int border = 2;
        gBuffer.drawRect(border, border, size.width - 2 * border, size.height - 2 * border);
        gBuffer.setColor(Color.darkGray);
        border = 9;
        gBuffer.drawRect(border, border, size.width - 2 * border, size.height - 2 * border);
        gBuffer.drawString("Triangle goes here", border * 2, border * 4);

       
        
        
        
        
        
      // triangle
        double triangleHeight = size.height * Math.sqrt(3) / 2; 

       
        int[] xPoints = {
            size.width / 2,
            size.width,
            0
        };
        int[] yPoints = {
            0,
            (int) triangleHeight,
            (int) triangleHeight
        };

        gBuffer.setColor(Color.blue);
        gBuffer.drawPolygon(xPoints, yPoints, 3);

        // connects the midpoints of each of the lines
        int[] midX = {
            (xPoints[0] + xPoints[1]) / 2,
            (xPoints[1] + xPoints[2]) / 2,
            (xPoints[0] + xPoints[2]) / 2
        };

        int[] midY = {
            (yPoints[0] + yPoints[1]) / 2,
            (yPoints[1] + yPoints[2]) / 2,
            (yPoints[0] + yPoints[2]) / 2
        };

       
        for (int i = 0; i < 3; i++) {
            int[] newXPoints = {midX[i], midX[(i + 1) % 3], midX[(i + 2) % 3]};
            int[] newYPoints = {midY[i], midY[(i + 1) % 3], midY[(i + 2) % 3]};
            gBuffer.setColor(Color.green);
            gBuffer.drawPolygon(newXPoints, newYPoints, 3);
        }

        // Midpoints -> outer triangles
        int px0 = (xPoints[0] + xPoints[1]) / 2;
        int px1 = (xPoints[1] + xPoints[2]) / 2;
        int px2 = (xPoints[0] + xPoints[2]) / 2;

        int py0 = (yPoints[0] + yPoints[1]) / 2;
        int py1 = (yPoints[1] + yPoints[2]) / 2;
        int py2 = (yPoints[0] + yPoints[2]) / 2;

        // 
        int[] midX1 = {
            (px0 + xPoints[1]) / 2,
            (xPoints[1] + px1) / 2,
            (px0 + px1) / 2
        };

        int[] midY1 = {
            (py0 + yPoints[1]) / 2,
            (yPoints[1] + py1) / 2,
            (py0 + py1) / 2
        };

        int[] midX2 = {
            (px2 + px1) / 2,
            (px1 + xPoints[2]) / 2,
            (px2 + xPoints[2]) / 2
        };

        int[] midY2 = {
            (py2 + py1) / 2,
            (py1 + yPoints[2]) / 2,
            (py2 + yPoints[2]) / 2
        };
        
        int [] midX3 = {
        	(xPoints[0] + px0) /2,
        	(px0 + px2) /2,
        	(xPoints[0] + px2) /2
        };
        
        int [] midY3 = 
        	{
        	(yPoints[0] + py0) /2,
        	(py0 + py2) /2,
        	(yPoints[0] + py2) /2
        			
        	};

        int[][] allXPoints = {midX1, midX2, midX3};
        int[][] allYPoints = {midY1, midY2, midY3};

        
        for (int i = 0; i < 3; i++) {
            gBuffer.setColor(Color.orange);
            gBuffer.drawPolygon(allXPoints[i], allYPoints[i], 3);
        }

        return bufferedImage;
    }
    
    
    
    

    BufferedImage bufferedImage;
    Dimension bufferedImageSize;

    @Override
    public Image getImage(Dimension triangleSize) {
        if (triangleSize.equals(bufferedImageSize))
            return bufferedImage;
        bufferedImage = drawTriangle(triangleSize);
        bufferedImageSize = triangleSize;
        return bufferedImage;
    }
    @Override
    public Image getResizeImage(Dimension size) {
        BufferedImage bufferedImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gBuffer = (Graphics2D) bufferedImage.getGraphics();
        gBuffer.setColor(Color.pink);
        int border = 2;
        gBuffer.drawRect(border, border, size.width - 2 * border, size.height - 2 * border);
        return bufferedImage;
    }
}
