package calc.model;

public interface IOperatorAdd extends IOperatorBinary {
    String symbol = "+";
    @Override
    default String getSymbol() {
        return symbol;
    }
}
