package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("My first java graphical interface");

    public SimpleGUI(final Controller controller){
        final JPanel canvas = new JPanel();
        final JTextArea textArea = new JTextArea();
        final JButton save = new JButton("Save");
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas.setLayout(new BorderLayout());
        canvas.add(save, BorderLayout.SOUTH);
        canvas.add(textArea, BorderLayout.CENTER);
        frame.setContentPane(canvas);

        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    controller.saveContentOnFile(textArea.getText());
                }catch(IOException e1){
                    JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
    }

    private void display(){
        frame.setVisible(true);
    }

    public static void main(String[] args){
        final SimpleGUI simpleGUI = new SimpleGUI(new Controller());
        simpleGUI.display();
    }

}
