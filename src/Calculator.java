import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Calculator extends JFrame implements ActionListener {
    private final JTextField display;

    private double currentA = 0;
    private double currentB = 0;
    private int solveStatus = 0;
    private String currentOperator = "";
    private final Map<String, Operation> operations;

    public Calculator() {
        setTitle("Calculator");
        setSize(540, 540);
        ImageIcon icon = new ImageIcon("calc.png");
        Image iconImage = icon.getImage();
        setIconImage(iconImage);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        operations = new HashMap<>();
        operations.put("+", new Addition());
        operations.put("-", new Subtraction());
        operations.put("*", new Multiplication());
        operations.put("/", new Division());

        display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 48));

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
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            panel.add(button);
        }

        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("C")) {
            currentA = 0;
            currentB = 0;
            currentOperator = "";
            solveStatus = 0;
            display.setText("0");
        } else if (command.equals("=")) {
            if (solveStatus >= 2) {
                try {
                    currentA = operations.get(currentOperator).apply(currentA, currentB);
                    display.setText(String.valueOf(currentA));
                    solveStatus = 3;
                } catch (Exception ex) {
                    display.setText("Error");
                    solveStatus = 0;
                    currentA = 0;
                    currentB = 0;
                    currentOperator = "";
                }
            }
        } else if (operations.containsKey(command)) {
            currentOperator = command;
            if (solveStatus == 3) {
                currentB = 0;
            }
            solveStatus = 2;
        } else {
            if (solveStatus <= 1 && currentOperator.isEmpty()) {
                currentA = addNewNumber(currentA, command);
                display.setText("" + currentA);
                solveStatus = 1;
            } else if (!currentOperator.isEmpty() && solveStatus == 2) {
                currentB = addNewNumber(currentB, command);
                display.setText("" + currentB);
                solveStatus = 2;
            } else if (solveStatus == 3) {
                currentA = Double.parseDouble(command);
                currentB = 0;
                currentOperator = "";
                display.setText("" + currentA);
                solveStatus = 1;
            }
        }

        System.out.println(currentA + " " + currentOperator + " " + currentB + " | " + solveStatus);
    }


    public double addNewNumber(double currentNr, String newNumber) {
        double parsedNewNumber = Double.parseDouble(newNumber);
        currentNr *= 10;
        currentNr += parsedNewNumber;
        return currentNr;
    }

}
