package calc.interfaces;

public interface IFactoryControl {
    IControl create(IView view, IFactoryOperand fo_d, IFactoryOperator fo_r, IExpression fe);
}
