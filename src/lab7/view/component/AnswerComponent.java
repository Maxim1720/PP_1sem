package lab7.view.component;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface AnswerComponent extends ActionListener, ViewComponent {
    JLabel answerLabel();
    JButton lower();
    JButton guessed();
    JButton higher();

}
