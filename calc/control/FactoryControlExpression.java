package calc.control;

import calc.interfaces.*;

public class FactoryControlExpression implements IFactoryControl {
    @Override
    public IControl create(IView view, IFactoryOperand fo_d, IFactoryOperator fo_r, IExpression expression) {
        return new ControlExpression(view, fo_d, fo_r, expression);
    }
}
