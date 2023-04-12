package lab7.view;

import lab7.view.component.AnswerComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AnswerView extends JPanel implements AnswerComponent {

    private final JLabel answerLabel;
    private final JButton lowerBtn;
    private final JButton higherBtn;
    private final JButton guessed;

    public AnswerView() {

        setBackground(Color.CYAN);

        answerLabel = new JLabel("",JLabel.CENTER);
        guessed = new JButton("guessed");
        lowerBtn = new JButton("lower");
        higherBtn = new JButton("higher");

        guessed.setBackground(Color.GREEN);
        guessed.setAlignmentX(JButton.CENTER);

        lowerBtn.setAlignmentX(JButton.CENTER);
        lowerBtn.setBackground(Color.RED);
        higherBtn.setAlignmentX(JButton.CENTER);
        higherBtn.setBackground(Color.blue);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.CENTER;
        c.gridy=0;
        c.gridx=0;
        c.gridwidth = 3;
        c.weightx = 0.6;
        add(answerLabel,c);

        c.gridwidth=1;
        c.weightx = 0.2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,5,5,5);
        c.gridy = 1;

        c.gridx=0;
        add(lowerBtn,c);

        c.gridx=1;
        add(guessed,c);

        c.gridx=2;
        add(higherBtn,c);
    }

    @Override
    public JLabel answerLabel() {
        return answerLabel;
    }

    @Override
    public JButton lower() {
        return lowerBtn;
    }

    @Override
    public JButton guessed() {
        return guessed;
    }

    @Override
    public JButton higher() {
        return higherBtn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public Component view() {
        return this;
    }

}
