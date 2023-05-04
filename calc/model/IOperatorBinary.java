package calc.model;

import calc.interfaces.IOperand;
import calc.interfaces.IOperator;
import calc.util.ExceptionProg;
import calc.util.ExceptionUser;

public interface IOperatorBinary extends IOperator {
    IOperand execute(IOperand o1, IOperand o2) throws ExceptionProg, ExceptionUser;
}
