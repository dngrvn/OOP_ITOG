package calc.interfaces;

import calc.util.ExceptionUser;

public interface IFactoryOperator {
    IOperator create(String strOperator) throws ExceptionUser;
    String description();
    String shortDescription();
}
