package calc.model;

import calc.interfaces.IExpression;
import calc.interfaces.IOperand;
import calc.interfaces.IOperator;
import calc.util.ExceptionProg;
import calc.util.ExceptionUser;

import java.util.ArrayList;
import java.util.List;

public class Expression implements IExpression {
    protected List exp;
    protected OperatorEqual operatorEqual;
    protected IOperator lastExecOperator;
    protected IOperand result;
    protected EXPECT expect;
    protected boolean finish;

    public Expression(OperatorEqual operatorEqual) {
        this.operatorEqual = operatorEqual;
        exp = new ArrayList();
        begin();
    }

    public EXPECT begin() {
        exp.clear();
        result = null;
        finish = false;
        lastExecOperator = null;
        expect = EXPECT.OPERAND;
        return expect;
    }

    @Override
    public EXPECT getNextExpect() {
        return expect;
    }

    @Override
    public String getResult() {
        if (result == null)
            return "Нет данных.";
        return result.valueToString();
    }

    @Override
    public void add(IOperand operand) throws ExceptionProg, ExceptionUser {
        if (expect != EXPECT.OPERAND)
            throw new ExceptionProg("!!! Ошибка. Ожидал получить операнд.");
        if (result != null && lastExecOperator != null)
            if (lastExecOperator instanceof IOperatorBinary ob) {
                result = ob.execute(result, operand);
                exp.add(operand);
                exp.add(operatorEqual);
                exp.add(result);
            } else throw new ExceptionProg("!!! Ошибка. Не известный тип оператора.");
        else {
            exp.add(operand);
            result = operand;
        }
        expect = EXPECT.OPERATOR;
    }

    @Override
    public void add(IOperator operator) throws ExceptionProg, ExceptionUser {
        if (expect != EXPECT.OPERATOR)
            throw new ExceptionProg("!!! Ошибка. Ожидал получить оператор.");
        if (operator instanceof OperatorEqual) {
            finish = true;
            lastExecOperator = null;
        } else {
            exp.add(operator);
            lastExecOperator = operator;
        }
        expect = EXPECT.OPERAND;
    }

    @Override
    public boolean isFinish() {
        return finish;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (result != null) {
            for (Object ob : exp) {
                if (ob instanceof IOperand o_d)
                    sb.append(o_d.valueToString());
                else if (ob instanceof IOperator o_r)
                    sb.append(o_r.getSymbol());
                sb.append(" ");
            }
        } else sb.append("Выражение отсутствует.");
        return sb.toString();
    }
}
