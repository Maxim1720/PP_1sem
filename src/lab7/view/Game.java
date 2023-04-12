package lab7.view;

import lab7.exception.IncorrectNumberIntervalException;
import lab7.logic.NumberGenerator;
import lab7.logic.NumberGeneratorImpl;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Game extends JFrame {
    private NumberGenerator numberGenerator;
    private AnswerComponent answerComponent;
    private MinMaxComponent minMaxComponent;

    private ThemeChangeComponent themeChangeComponent;

    private int guessNumber;
    public Game() {
        super();

        numberGenerator = new NumberGeneratorImpl();
        answerComponent = new AnswerView();
        minMaxComponent = new MinMaxView();


        Arrays.stream(answerComponent.getComponents()).forEach(c -> c.setEnabled(false));
        minMaxComponent.guess().addActionListener(l -> {
            try {
                guessNumber = numberGenerator.generate(
                        Integer.parseInt(minMaxComponent.min().getText()),
                        Integer.parseInt(minMaxComponent.max().getText())
                );
                answerComponent.answerLabel().setText(String.valueOf(guessNumber));
                Arrays.stream(answerComponent.getComponents()).forEach(c -> c.setEnabled(true));
                Arrays.stream(minMaxComponent.getComponents()).forEach(c -> c.setEnabled(false));
            }catch (IncorrectNumberIntervalException | NumberFormatException e){
                showError("error", e.getMessage());
            }

        });



        answerComponent.higher().addActionListener(l -> {
            try {
                guessNumber = numberGenerator.generate(guessNumber+1, numberGenerator.getMax());
                answerComponent.answerLabel().setText(String.valueOf(guessNumber));
            } catch (IncorrectNumberIntervalException e){
                if(restartGame("you are not playing by the rules", "retry?")){
                    answerComponent.answerLabel().setText("");
                    minMaxComponent.max().setText("");
                    minMaxComponent.min().setText("");
                    Arrays.stream(answerComponent.getComponents()).forEach(c-> c.setEnabled(false));
                    Arrays.stream(minMaxComponent.getComponents()).forEach(c-> c.setEnabled(true));
                }{

                }
            }
        });

        answerComponent.lower().addActionListener(l -> {
            try {
                guessNumber = numberGenerator.generate(numberGenerator.getMin(), guessNumber);
                answerComponent.answerLabel().setText(String.valueOf(guessNumber));
            }
            catch (IncorrectNumberIntervalException e){
                if(restartGame("you are not playing by the rules", "retry?")){
                    answerComponent.answerLabel().setText("");
                    minMaxComponent.max().setText("");
                    minMaxComponent.min().setText("");
                    Arrays.stream(answerComponent.getComponents()).forEach(c-> c.setEnabled(false));
                    Arrays.stream(minMaxComponent.getComponents()).forEach(c-> c.setEnabled(true));
                }
            }

        });

        answerComponent.guessed().addActionListener(l -> {
            Arrays.stream(minMaxComponent.getComponents()).forEach(c-> c.setEnabled(true));
            Arrays.stream(answerComponent.getComponents()).forEach(c -> c.setEnabled(false));
            showError("Cool", "WIN!");
        });


        add(minMaxComponent.view());
        add(answerComponent.view());


        setLayout(new GridLayout(3, 1));
        setVisible(true);
        setSize(300, 400);
        setResizable(false);
    }


    private void showError(String title, String error){
        JOptionPane.showMessageDialog(this, error, title, JOptionPane.ERROR_MESSAGE);
    }

    private boolean restartGame(String title, String message){
        int answer = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION);
        return answer == JOptionPane.YES_OPTION;
    }
}
