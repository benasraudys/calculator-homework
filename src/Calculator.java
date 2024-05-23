import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private double currentResult = 0;
    private String currentOperator = "";
    private Map<String, Operation> operations;

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 400);
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
            panel.add(button);
        }

        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("C")) {
            currentResult = 0;
            currentOperator = "";
            display.setText("");
        } else if (command.equals("=")) {
            if (!currentOperator.isEmpty()) {
                double secondOperand = Double.parseDouble(display.getText());
                currentResult = operations.get(currentOperator).apply(currentResult, secondOperand);
                display.setText(String.valueOf(currentResult));
                currentOperator = "";
            }
        } else if (operations.containsKey(command)) {
            currentOperator = command;
            currentResult = Double.parseDouble(display.getText());
            display.setText("");
        } else {
            display.setText(display.getText() + command);
        }
    }
}
