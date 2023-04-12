package lab7.view;

import lab7.view.component.MinMaxComponent;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MinMaxView extends JPanel implements MinMaxComponent {


    private final JLabel minLabel;
    private final JLabel maxLabel;
    private final JTextField minField;
    private final JTextField maxField;
    private final JButton guessBtn;


    public MinMaxView() {

        minLabel = new JLabel("min:", JLabel.LEFT);
        maxLabel = new JLabel("max:", JLabel.LEFT);
        maxField = new JTextField();
        minField = new JTextField();

        guessBtn = new JButton("guess");
        guessBtn.addActionListener(this);
        add(guessBtn, JButton.CENTER);
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setBackground(Color.GREEN);
        setVisible(true);


        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 30, 10, 30);

        gridBagConstraints.gridy = 0;

        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.gridx = 0;
        add(minLabel, gridBagConstraints);

        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.gridx = 1;
        add(minField, gridBagConstraints);

        gridBagConstraints.gridy = 1;

        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.gridx = 0;
        add(maxLabel, gridBagConstraints);

        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.gridx = 1;
        add(maxField, gridBagConstraints);


        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;

        gridBagConstraints.fill = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10,0,0,0);

        add(guessBtn, gridBagConstraints);
    }

    @Override
    public Container view() {
        return this;
    }

    @Override
    public JButton guess() {
        return guessBtn;
    }

    @Override
    public JTextField min() {
        return minField;
    }

    @Override
    public JTextField max() {
        return maxField;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
