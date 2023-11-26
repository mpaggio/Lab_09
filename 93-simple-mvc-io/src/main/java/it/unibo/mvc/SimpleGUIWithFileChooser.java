package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame("My second java graphical interface");

    public SimpleGUIWithFileChooser(final Controller controller){
        final JPanel canvas = new JPanel();
        final JPanel northCanvas = new JPanel();
        final JTextArea textArea = new JTextArea();
        final JTextField textField = new JTextField(controller.getPath());
        final JButton save = new JButton("Save");
        final JButton browse = new JButton("Browse...");
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField.setEditable(false);

        canvas.setLayout(new BorderLayout());
        northCanvas.setLayout(new BorderLayout());
        canvas.add(save, BorderLayout.SOUTH);
        canvas.add(textArea, BorderLayout.CENTER);
        northCanvas.add(textField, BorderLayout.CENTER);
        northCanvas.add(browse, BorderLayout.LINE_END);
        canvas.add(northCanvas, BorderLayout.NORTH);

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

        browse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("Choose file to save in");
                final int result = fileChooser.showSaveDialog(frame);
                if(result == JFileChooser.APPROVE_OPTION){
                    final File newCurrentFile = fileChooser.getSelectedFile();
                    controller.setCurrentFile(newCurrentFile);
                    textField.setText(newCurrentFile.getPath());
                }
                else if(result != JFileChooser.CANCEL_OPTION){
                    JOptionPane.showMessageDialog(null, result, "Error occurred", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
    }

    private void display(){
        frame.setVisible(true);
    }

    public static void main(String[] args){
        final SimpleGUIWithFileChooser simpleGUIWithFileChooser = new SimpleGUIWithFileChooser(new Controller());
        simpleGUIWithFileChooser.display();
    }
}
