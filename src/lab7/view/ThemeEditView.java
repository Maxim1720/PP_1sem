package lab7.view;

import lab7.view.component.ThemeChangeComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class ThemeEditView extends JPanel implements ThemeChangeComponent {

    UIManager.LookAndFeelInfo[] themes;

    public ThemeEditView(){

        JLabel label = new JLabel("Themes:");


        themes = Arrays.stream(UIManager.getInstalledLookAndFeels()).toList().toArray(new UIManager.LookAndFeelInfo[]{});
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.CENTER;
        add(label,c);

        c.gridy=1;
        c.gridx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;

        ButtonGroup buttonGroup = new ButtonGroup();
        for (UIManager.LookAndFeelInfo l : themes){
            JRadioButton radioButton = new JRadioButton(l.getName());
            radioButton.addActionListener(this);
            buttonGroup.add(radioButton);
            if(UIManager.getLookAndFeel().getName().equals(l.getName())){
                radioButton.setSelected(true);
            }
            add(radioButton,c);
            c.gridy= c.gridy+1;
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        JRadioButton radioButton = (JRadioButton) e.getSource();
        try {
            String className = Arrays.stream(themes)
                    .filter(t-> t.getName().equals(radioButton.getText())).toList().get(0).getClassName();
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this.getParent());

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Component view() {
        return this;
    }
}
