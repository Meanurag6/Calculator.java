import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculator {
    private JFrame frame;
    private JTextField textField;
    private String operator;
    private double firstNum, secondNum, result;
    
    public Calculator() {
        frame = new JFrame("Java Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());
        
        textField = new JTextField();
        frame.add(textField, BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };
        
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }
        
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                textField.setText(textField.getText() + command);
            } else if (command.equals("C")) {
                textField.setText("");
                firstNum = secondNum = result = 0;
            } else if (command.equals("=")) {
                try {
                    secondNum = Double.parseDouble(textField.getText());
                    switch (operator) {
                        case "+": result = firstNum + secondNum; break;
                        case "-": result = firstNum - secondNum; break;
                        case "*": result = firstNum * secondNum; break;
                        case "/": 
                            if (secondNum == 0) {
                                JOptionPane.showMessageDialog(frame, "Cannot divide by zero!");
                                textField.setText("");
                                return;
                            }
                            result = firstNum / secondNum; 
                            break;
                    }
                    textField.setText(String.valueOf(result));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid Input");
                    textField.setText("");
                }
            } else {
                firstNum = Double.parseDouble(textField.getText());
                operator = command;
                textField.setText("");
            }
        }
    }
    
    public static void main(String[] args) {
        new Calculator();
    }
}
