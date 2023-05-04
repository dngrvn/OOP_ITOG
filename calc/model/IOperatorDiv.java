package calc.model;

public interface IOperatorDiv extends IOperatorBinary {
    String symbol = "/";

    @Override
    default String getSymbol() {
        return symbol;
    }

}
