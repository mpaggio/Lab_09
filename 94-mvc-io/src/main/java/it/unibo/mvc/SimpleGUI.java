package it.unibo.mvc;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("Simple Graphical User Interface");

    public SimpleGUI(Controller controller){
        final JPanel canvas = new JPanel();
        final JPanel southCanvas = new JPanel();
        final JTextField textField = new JTextField();
        final JTextArea textArea = new JTextArea();
        final JButton print = new JButton("Print");
        final JButton history = new JButton("Show history");
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField.setBackground(Color.lightGray);

        canvas.setLayout(new BorderLayout());
        southCanvas.setLayout(new BoxLayout(southCanvas, BoxLayout.LINE_AXIS));
        southCanvas.add(print);
        southCanvas.add(history);
        canvas.add(textArea, BorderLayout.CENTER);
        canvas.add(textField, BorderLayout.NORTH);
        canvas.add(southCanvas, BorderLayout.SOUTH);
        
        frame.setContentPane(canvas);
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);

        print.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setNextStringToPrint(textField.getText());
                controller.printString();
            }
            
        });

        history.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final List<String> stringHistory = controller.getHistoryOfPrintedString();
                if(stringHistory.isEmpty()){
                    textArea.setText("");
                }
                else{
                    for(final String string : controller.getHistoryOfPrintedString()){
                    textArea.append(string);
                    textArea.append("\n");
                    }
                }
                textArea.append("-----------------------------------------------------");
                textArea.append("\n");
            }
            
        });
    }

    private void display(){
        frame.setVisible(true);
    }

    public static void main(String[] args){
        final SimpleGUI simpleGUI = new SimpleGUI(new SimpleController());
        simpleGUI.display();
    }

}
