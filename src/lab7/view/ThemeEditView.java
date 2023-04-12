package lab7.view;

import lab7.view.component.ThemeChangeComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class ThemeEditView extends JPanel implements ThemeChangeComponent {

    UIManager.LookAndFeelInfo[] themes;

    public ThemeEditView(){
        themes = Arrays.stream(UIManager.getInstalledLookAndFeels()).toList().toArray(new UIManager.LookAndFeelInfo[]{});
        setLayout(new GridLayout(themes.length, 1));
        ButtonGroup buttonGroup = new ButtonGroup();

        for (UIManager.LookAndFeelInfo l : themes){
            JRadioButton radioButton = new JRadioButton(l.getName());
            radioButton.addActionListener(this);
            buttonGroup.add(radioButton);
            add(radioButton);
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        JRadioButton radioButton = (JRadioButton) e.getSource();
        try {
            String className = Arrays.stream(themes)
                    .filter(t-> t.getName().equals(radioButton.getText())).toList().get(0).getClassName();

            Class<?> clazz = Class.forName(className);

            UIManager.setLookAndFeel(className);

            SwingUtilities.updateComponentTreeUI(this.getParent());

        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (InstantiationException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        } catch (UnsupportedLookAndFeelException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Component view() {
        return this;
    }
}
