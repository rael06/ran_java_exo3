package edu.ran_java.eval;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class JaugeInterface extends JFrame implements ActionListener {
    private JaugePanel jaugePanel = new JaugePanel();
    private JFrame window = new JFrame();
    private JLabel vmin;
    private JLabel vmax;
    private JLabel jauge;
    private JLabel result;
    private JLabel gBottom;
    private JLabel gTop;
    private JLabel gResult;

    private int min;
    private int max;
    private int jaugeValue;

    private TextField fmin;
    private TextField fmax;

    private JButton minus;
    private JButton plus;

    private JTextArea errors;

    JaugeInterface() {
        setLabels();
        setFields();
        setButtons();
        setJTextArea();
        setJPanels();
        setWindow();
    }

    private void setLabels() {
        vmin = new JLabel("Valeur minimale");
        vmin.setBounds(20, 20, 100, 20);
        vmin.setBackground(Color.BLUE);

        vmax = new JLabel("Valeur maximale");
        vmax.setBounds(20, 50, 100, 20);
        vmax.setBackground(Color.BLUE);

        jauge = new JLabel("Jauge : ");
        jauge.setBounds(20, 80, 100, 20);
        jauge.setBackground(Color.BLUE);

        result = new JLabel(String.valueOf(min));
        result.setBounds(240, 80, 100, 20);
        result.setBackground(Color.RED);

        gBottom = new JLabel(String.valueOf(min));
        gBottom.setBounds(130, 340, 100, 20);

        gTop = new JLabel(String.valueOf(max));
        gTop.setBounds(130, 140, 100, 20);

        gResult = new JLabel();
        gResult.setForeground(Color.red);
    }

    private void setFields() {
        fmin = new TextField();
        fmin.setBounds(150, 20, 200, 20);

        fmax = new TextField();
        fmax.setBounds(150, 50, 200, 20);
    }

    private void setButtons() {
        minus = new JButton("-");
        minus.setBounds(150, 80, 50, 20);
        minus.addActionListener(this);

        plus = new JButton("+");
        plus.setBounds(300, 80, 50, 20);
        plus.addActionListener(this);
    }

    private void setJTextArea() {
        errors = new JTextArea();
        errors.setBounds(50, 110, 300, 20);
    }

    private void setJPanels() {
        jaugePanel.setBounds(100, 150, 20, 205);
    }

    private void setWindow() {
        window.setBounds(500, 200, 400, 420);
        window.setVisible(true);
        window.setLayout(null);

        window.add(vmin);
        window.add(vmax);
        window.add(jauge);
        window.add(result);
        window.add(gBottom);
        window.add(gTop);
        window.add(gResult);

        window.add(fmin);
        window.add(fmax);

        window.add(minus);
        window.add(plus);

        window.add(errors);
        window.add(jaugePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String errorsStr = "";
        errors.setText(errorsStr);
        try {
            min = Integer.parseInt(fmin.getText());
            max = Integer.parseInt(fmax.getText());
            jaugeValue = Integer.parseInt(result.getText());
            if (min < max) {
                gBottom.setText(String.valueOf(min));
                gTop.setText(String.valueOf(max));
                if (jaugeValue < min) {
                    result.setText(String.valueOf(min));
                }

                if (jaugeValue > max) {
                    result.setText(String.valueOf(max));
                }
                gResult.setText(String.valueOf(result));

                jaugePanel.setMin(min);
                jaugePanel.setMax(max);

                window.revalidate();
                window.repaint();

            } else {
                errorsStr += "Veuillez entrer une valeur minimale inférieure à la valeur maximale \n";
                errors.setText(errorsStr);
                window.revalidate();
            }

        } catch (Exception error) {
            errorsStr += "Veuillez remplir les champs de valeur avec un nombre \n";
            errors.setText(errorsStr);
            window.revalidate();
        }

        if (!fmin.getText().isEmpty() && !fmax.getText().isEmpty()) {
            if (e.getSource().equals(minus)) {
                if (Integer.parseInt(result.getText()) > min) {
                    result.setText(String.valueOf(Integer.parseInt(result.getText()) - 1));
                } else {
                    result.setText(String.valueOf(min));
                }
            }

            if (e.getSource().equals(plus)) {
                if (Integer.parseInt(result.getText()) < max) {
                    result.setText(String.valueOf(Integer.parseInt(result.getText()) + 1));
                } else {
                    result.setText(String.valueOf(max));
                }
            }

            jaugeValue = Integer.parseInt(result.getText());
            jaugePanel.setResult(jaugeValue);
            gResult.setBounds(155, (max-jaugeValue) * 200/(max-min) + 140,100,20);
            gResult.setText(result.getText());
            window.revalidate();
        }

    }
}
