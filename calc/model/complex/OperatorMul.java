package calc.model.complex;

import calc.interfaces.IOperand;
import calc.model.IOperatorMul;

public class OperatorMul implements IOperatorMul {
    @Override
    public IOperand execute(IOperand o1, IOperand o2) {
        double a = o1.getRealValue();
        double b = o1.getImaginaryValue();
        double c = o2.getRealValue();
        double d = o2.getImaginaryValue();
        return new OperandComplex(a * c - b * d, b * c + a * d);
    }

}