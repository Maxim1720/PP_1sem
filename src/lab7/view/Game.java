package lab7.view;

import lab7.exception.IncorrectNumberIntervalException;
import lab7.logic.NumberGenerator;
import lab7.logic.NumberGeneratorImpl;
import lab7.view.component.AnswerComponent;
import lab7.view.component.MinMaxComponent;
import lab7.view.component.ThemeChangeComponent;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Game extends JFrame {
    private final NumberGenerator numberGenerator;
    private final AnswerComponent answerComponent;
    private final MinMaxComponent minMaxComponent;
    private final ThemeChangeComponent themeChangeComponent;

    private int guessNumber;
    public Game() {
        super();

        numberGenerator = new NumberGeneratorImpl();
        answerComponent = new AnswerView();
        minMaxComponent = new MinMaxView();
        themeChangeComponent = new ThemeEditView();

        Arrays.stream(answerComponent.getComponents()).forEach(c -> c.setEnabled(false));
        minMaxComponent.guess().addActionListener(l -> {
            try {
                tryGetGuess();
                switchToGetAnswer();
            } catch (IncorrectNumberIntervalException | NumberFormatException e) {
                showError("error", e.getMessage());
            }
        });
        answerComponent.higher().addActionListener(l -> setRange(guessNumber + 1, numberGenerator.getMax()));
        answerComponent.lower().addActionListener(l -> setRange(numberGenerator.getMin(), guessNumber - 1));

        answerComponent.guessed().addActionListener(l -> {
            Arrays.stream(minMaxComponent.getComponents()).forEach(c -> c.setEnabled(true));
            Arrays.stream(answerComponent.getComponents()).forEach(c -> c.setEnabled(false));
            showOfferToRestart("Cool", "WIN! Retry game?");
        });


        add(minMaxComponent.view());
        add(answerComponent.view());
        add(themeChangeComponent.view());


        setLayout(new GridLayout(3, 1));
        setVisible(true);
        setSize(300, 500);
        setResizable(false);
    }

    private void tryGetGuess(){
        guessNumber = numberGenerator.generate(
                Integer.parseInt(minMaxComponent.min().getText()),
                Integer.parseInt(minMaxComponent.max().getText())
        );
    }

    private void switchToGetAnswer(){
        answerComponent.answerLabel().setText(String.valueOf(guessNumber));
        Arrays.stream(answerComponent.getComponents()).forEach(c -> c.setEnabled(true));
        Arrays.stream(minMaxComponent.getComponents()).forEach(c -> c.setEnabled(false));
    }

    private void setRange(int min, int max){
        try {
            guessNumber = numberGenerator.generate(min, max);
            answerComponent.answerLabel().setText(String.valueOf(guessNumber));
        }
        catch (IncorrectNumberIntervalException e){
            showNotPlayingByTheRules();
        }
    }


    private void showNotPlayingByTheRules(){
        showOfferToRestart("you are not playing by the rules", "retry?");
    }
    private void showOfferToRestart(String title, String message){
        if(needRestart(title, message)){
            restartGame();
        }else {
            this.dispose();
        }
    }
    private void onGuessed(){

    }

    private void restartGame(){
        answerComponent.answerLabel().setText("");
        minMaxComponent.max().setText("");
        minMaxComponent.min().setText("");
        Arrays.stream(answerComponent.getComponents()).forEach(c-> c.setEnabled(false));
        Arrays.stream(minMaxComponent.getComponents()).forEach(c-> c.setEnabled(true));
    }
    private void showError(String title, String error){
        JOptionPane.showMessageDialog(this, error, title, JOptionPane.ERROR_MESSAGE);
    }

    private boolean needRestart(String title, String message){
        int answer = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION);
        return answer == JOptionPane.YES_OPTION;
    }
}
