package calc.interfaces;

import calc.util.ExceptionUser;

public interface IFactoryOperand {
    IOperand create(String strOperand) throws ExceptionUser;
    String description();
    String shortDescription();
}
