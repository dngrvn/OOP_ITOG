package calc.interfaces;

import calc.util.ExceptionProg;
import calc.util.ExceptionUser;

public interface IExpression {
    enum EXPECT {OPERAND, OPERATOR}

    EXPECT begin();
    EXPECT getNextExpect();
    String getResult();

    void add(IOperand operand) throws ExceptionProg, ExceptionUser;

    void add(IOperator operator) throws ExceptionProg, ExceptionUser;
    boolean isFinish();
}
