package lab7.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface MinMaxComponent extends ActionListener, ViewComponent {
    JButton guess();

    JTextField min();
    JTextField max();
}
