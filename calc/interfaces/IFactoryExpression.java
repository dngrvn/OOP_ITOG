package calc.interfaces;

import calc.model.OperatorEqual;

public interface IFactoryExpression {
    IExpression create(OperatorEqual operatorEqual);
}
