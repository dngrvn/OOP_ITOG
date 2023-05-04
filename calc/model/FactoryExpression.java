package calc.model;

import calc.interfaces.IExpression;
import calc.interfaces.IFactoryExpression;

public class FactoryExpression implements IFactoryExpression {
    @Override
    public IExpression create(OperatorEqual operatorEqual) {
        return new Expression(operatorEqual);
    }
}
