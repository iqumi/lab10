package org.example.lab10;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CalculatorController {

    @FXML
    private Label display;

    private final CalculatorLogic logic = new CalculatorLogic();

    @FXML
    private void onDigitClick(javafx.event.ActionEvent event) {
        String digit = ((javafx.scene.control.Button) event.getSource()).getText();
        logic.addDigit(digit);
        display.setText(logic.getExpression());
    }

    @FXML
    private void onOperatorClick(javafx.event.ActionEvent event) {
        String op = ((javafx.scene.control.Button) event.getSource()).getText();
        logic.addOperator(op);
        display.setText(logic.getExpression());
    }

    @FXML
    private void onClear() {
        logic.clear();
        display.setText("");
    }

    @FXML
    private void onEqual() {
        try {
            String result = logic.calculate();
            display.setText(result);
        } catch (ArithmeticException ex) {
            display.setText("Ошибка: деление на 0");
            logic.clear();
        }
    }
}
