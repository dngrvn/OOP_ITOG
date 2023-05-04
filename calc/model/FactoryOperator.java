package calc.model;

import calc.interfaces.IFactoryOperator;
import calc.interfaces.IOperator;
import calc.util.ExceptionUser;

public class FactoryOperator implements IFactoryOperator {

    protected IOperator[] operators;
    public FactoryOperator(IOperator[] operators) {
        this.operators = operators;
    }

    @Override
    public IOperator create(String strOperator) throws ExceptionUser {
        for(IOperator o: operators) {
            if (o.getSymbol().equals(strOperator))
                return o;
        }
        throw new ExceptionUser("!!! Ошибка. Неизвестная операция.");
    }

    @Override
    public String description() {
        return shortDescription();
    }

    @Override
    public String shortDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Возможные операторы: [ ");
        for(IOperator o: operators)
            sb.append(o.getSymbol()).append(" ");
        sb.append("]");
        return sb.toString();
    }
}
