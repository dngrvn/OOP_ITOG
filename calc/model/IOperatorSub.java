package calc.model;

public interface IOperatorSub  extends IOperatorBinary {
    String symbol = "-";
    @Override
    default String getSymbol() {
        return symbol;
    }
}
