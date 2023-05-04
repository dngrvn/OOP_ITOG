package calc.model;

public interface IOperatorMul extends IOperatorBinary {
    String symbol = "*";

    @Override
    default String getSymbol() {
        return symbol;
    }
}
