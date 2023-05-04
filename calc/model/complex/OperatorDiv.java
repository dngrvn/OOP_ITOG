package calc.model.complex;

import calc.interfaces.IOperand;
import calc.model.IOperatorDiv;
import calc.util.ExceptionUser;

public class OperatorDiv implements IOperatorDiv {
    @Override
    public IOperand execute(IOperand o1, IOperand o2) throws ExceptionUser {
        double a = o1.getRealValue();
        double b = o1.getImaginaryValue();
        double c = o2.getRealValue();
        double d = o2.getImaginaryValue();
        if (c == OperandComplex.ZERO && d == OperandComplex.ZERO)
            throw new ExceptionUser("!!! Ошибка. Деление на ноль.");
        return new OperandComplex(
                (a * c + b * d) / (c * c + d * d)
                , (b * c - a * d) / (c * c + d * d)
        );
    }
}
