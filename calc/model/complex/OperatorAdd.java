package calc.model.complex;

import calc.interfaces.IOperand;
import calc.model.IOperatorAdd;

public class OperatorAdd implements IOperatorAdd {
    @Override
    public IOperand execute(IOperand o1, IOperand o2) {
        return new OperandComplex(
                o1.getRealValue() + o2.getRealValue()
                , o1.getImaginaryValue() + o2.getImaginaryValue());
    }

}
