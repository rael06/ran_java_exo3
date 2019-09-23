package edu.ran_java.eval;

import javax.swing.*;
import java.awt.*;

class JaugePanel extends JPanel {

    private int min;
    private int max;
    private int result;

    public void paint(Graphics g) {
        //echelle verticale
        g.drawLine(10, 0, 10, 200);

        //top
        g.drawLine(5, 0, 15, 0);

        //bottom
        g.drawLine(5, 200, 15, 200);

        //graduation
        for (int i = 0; i < 200; i += 50)
            g.drawLine(8, i, 12, i);

        //marqueur result
        if (max - min != 0) {
            g.setColor(Color.red);
            g.drawLine(3, (max-result) * 200/(max-min), 17, (max-result) * 200/(max-min));
            System.out.println((max-result) * 200/(max-min));
        }
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "JaugePanel{" +
                "min=" + min +
                ", max=" + max +
                ", result=" + result +
                '}';
    }
}
