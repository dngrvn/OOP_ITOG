package calc.model;

import calc.interfaces.IOperator;

public class OperatorEqual implements IOperator {
    private final String symbol = "=";

    @Override
    public String getSymbol() {
        return symbol;
    }

}
