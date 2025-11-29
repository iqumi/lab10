package org.example.lab10;

public class CalculatorLogic {

    private StringBuilder expression = new StringBuilder();

    public void addDigit(String d) {
        expression.append(d);
    }

    public void addOperator(String op) {
        if (expression.length() == 0) return;
        char last = expression.charAt(expression.length() - 1);
        if ("+-*/".indexOf(last) != -1) return;
        expression.append(op);
    }

    public String getExpression() {
        return expression.toString();
    }

    public void clear() {
        expression.setLength(0);
    }

    public String calculate() {
        String exp = expression.toString();

        if (exp.contains("/0")) {
            throw new ArithmeticException("div0");
        }

        try {
            double result = eval(exp);
            expression = new StringBuilder(String.valueOf(result));
            return String.valueOf(result);
        } catch (Exception e) {
            clear();
            return "Ошибка";
        }
    }

    // Простейший последовательный вычислитель
    private double eval(String expr) {
        String[] nums = expr.split("[+\\-*/]");
        char[] ops = expr.replaceAll("[0-9.]+", "").toCharArray();

        double res = Double.parseDouble(nums[0]);
        int index = 1;

        for (char op : ops) {
            double b = Double.parseDouble(nums[index++]);
            switch (op) {
                case '+': res += b; break;
                case '-': res -= b; break;
                case '*': res *= b; break;
                case '/': res /= b; break;
            }
        }
        return res;
    }
}
