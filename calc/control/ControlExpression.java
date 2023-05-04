package calc.control;

import calc.interfaces.*;
import calc.util.ExceptionProg;
import calc.util.ExceptionUser;
import calc.view.ExceptionExit;
import calc.interfaces.IView;

public class ControlExpression implements IControl {
    protected String messageFinal = "Работа программы завершена.";
    IView view;
    IFactoryOperand fo_d;
    IFactoryOperator fo_r;
    IExpression expression;

    public ControlExpression(IView view, IFactoryOperand fo_d, IFactoryOperator fo_r, IExpression expression) {
        this.view = view;
        this.fo_d = fo_d;
        this.fo_r = fo_r;
        this.expression = expression;
    }

    @Override
    public void run() throws ExceptionProg {
        view.viewText("---------------------------");
        view.viewText("Дополнительная информация");
        view.viewText("\t"+fo_d.description());
        view.viewText("\t"+fo_r.description());
        view.viewText("---------------------------");
        view.viewText("Новое выражение\n");
        while (true) {
            if(expression.isFinish()) {
                view.viewText("\n---------------------------");
                view.viewText("Результат: "+expression.getResult());
                view.viewText(expression.toString());
                expression.begin();
                view.viewText("\n---------------------------");
                view.viewText("Новое выражение\n");
            } else {
                String result = expression.getResult();
                if(result != null && !result.isBlank())
                    view.viewText("\tПромежуточный результат: "+expression.getResult());
            }
            try {
                if (expression.getNextExpect() == IExpression.EXPECT.OPERAND) {
                    IOperand operand;
                    try {
                        operand = getOperand();
                    } catch (ExceptionUser e) {
                        continue;
                    }
                    try {
                        expression.add(operand);
                    } catch (ExceptionUser e) {
                        view.viewText(e.getMessage());
                        continue;
                    } catch (ExceptionProg e) {
                        view.viewText(e.getMessage());
                        view.viewText(messageFinal);
                        throw e;
                    }
                } else if (expression.getNextExpect() == IExpression.EXPECT.OPERATOR) {
                    IOperator operator;
                    try {
                        operator = getOperator();
                    } catch (ExceptionUser e) {
                        continue;
                    }
                    try {
                        expression.add(operator);
                    } catch (ExceptionUser e) {
                        view.viewText(e.getMessage());
                        continue;
                    } catch (ExceptionProg e) {
                        view.viewText(e.getMessage());
                        view.viewText(messageFinal);
                        throw e;
                    }
                } else {
                    view.viewText("!!! Ошибка. Не известный тип данных.");
                    view.viewText("" + expression.getNextExpect());
                    throw new ExceptionProg("!!! Ошибка. Не известный тип данных." + expression.getNextExpect());
                }
            } catch (ExceptionExit e) {
                view.viewText(messageFinal);
                return;
            }
        }

    }

    protected IOperand getOperand() throws ExceptionExit, ExceptionUser {
        IOperand operand = null;
        while (true) {
            String str = view.request(fo_d.shortDescription());
            try {
                operand = fo_d.create(str);
                return operand;
            } catch (ExceptionUser e) {
                view.viewText(e.getMessage());
                throw e;
            }
        }
    }

    protected IOperator getOperator() throws ExceptionExit, ExceptionUser {
        IOperator operator = null;
        while (true) {
            String str = view.request(fo_r.shortDescription());
            try {
                operator = fo_r.create(str);
                return operator;
            } catch (ExceptionUser e) {
                view.viewText(e.getMessage());
                throw e;
            }
        }
    }

}
