/*
 * Burns Hill
 * HW 05
 */

import java.awt.*;
import javax.swing.*;

public class SierpinskisTriangle extends Canvas {

    public static void main(String[] args) {
        // Create the JFrame window
        JFrame frame = new JFrame("Sierpinski's Triangle");
        frame.setSize(900, 900); // Set the size of the frame
        SierpinskisTriangle triangle = new SierpinskisTriangle();
        frame.add(triangle); // Add the canvas to the frame
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit program when window is closed
    }

    @Override
    public void paint(Graphics g) {
        // Draw the initial large triangle
        int width = getSize().width;
        int height = getSize().height;
        int[] xPoints = {width / 2, 50, width - 50}; // Triangle vertices x-coordinates
        int[] yPoints = {50, height - 50, height - 50}; // Triangle vertices y-coordinates
        drawTriangle(xPoints, yPoints, g, 4); // Call recursive method with pixel limit of 4
    }

    
    public void drawTriangle(int[] xPoints, int[] yPoints, Graphics g, int limit) {
        // Stop recursion when triangle side length is below the pixel limit
        if (Math.abs(xPoints[1] - xPoints[0]) < limit) {
            return;
        }

        // Draw the current triangle
        g.setColor(Color.BLUE);
        g.fillPolygon(xPoints, yPoints, 3);

        // Calculate midpoints of the triangle sides
        int midX1 = (xPoints[0] + xPoints[1]) / 2;
        int midY1 = (yPoints[0] + yPoints[1]) / 2;
        int midX2 = (xPoints[1] + xPoints[2]) / 2;
        int midY2 = (yPoints[1] + yPoints[2]) / 2;
        int midX3 = (xPoints[2] + xPoints[0]) / 2;
        int midY3 = (yPoints[2] + yPoints[0]) / 2;

        // Draw the inverted triangle in the middle
        int[] innerX = {midX1, midX2, midX3};
        int[] innerY = {midY1, midY2, midY3};
        g.setColor(Color.WHITE);
        g.fillPolygon(innerX, innerY, 3);

        // Recursive calls for the three outer triangles
        drawTriangle(new int[]{xPoints[0], midX1, midX3}, new int[]{yPoints[0], midY1, midY3}, g, limit);
        drawTriangle(new int[]{midX1, xPoints[1], midX2}, new int[]{midY1, yPoints[1], midY2}, g, limit);
        drawTriangle(new int[]{midX3, midX2, xPoints[2]}, new int[]{midY3, midY2, yPoints[2]}, g, limit);
    }
}