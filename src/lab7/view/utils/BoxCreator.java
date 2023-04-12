package lab7.view.utils;

import javax.swing.*;

public class BoxCreator {

    public Box create(JComponent ... components){
        Box box = new Box(BoxLayout.X_AXIS);
        box.setVisible(true);
        box.setSize(150,100);

        for (JComponent c : components){
            box.add(c);
        }

        return box;
    }

}
