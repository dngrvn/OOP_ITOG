package calc;

import calc.control.FactoryControlExpression;
import calc.interfaces.*;
import calc.model.FactoryExpression;
import calc.model.FactoryOperator;
import calc.model.OperatorEqual;
import calc.model.complex.*;
import calc.util.ExceptionProg;
import calc.util.MyLog;
import calc.view.FactoryView;

import java.util.logging.Logger;

public class Main {

    private final static String DZ = """
            Домашнее задание:
            Создать проект калькулятора комплексных чисел (достаточно сделать сложение, умножение и деление). 
            Применить при создании программы архитектурные паттерны, добавить логирование калькулятора. 
            Соблюдать принципы SOLID, паттерны проектирования.            
            """;
    private final static String title = """
            Программа калькулятор для комплексных чисел.
            Вычисления производятся с итогом по каждому оператору.
            """;
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws ExceptionProg {
        MyLog.loggerInit(MyLog.LOG_FILE);
        logger.warning(DZ);
        logger.warning("\n");

        IFactoryView fv = new FactoryView();
        IView view = fv.create(title, MyLog.CHARSET_CONSOLE, new char[]{'Q','q'});
        IFactoryOperand fo_d = new FactoryOperand();
        OperatorEqual operatorEqual = new OperatorEqual();
        IOperator[] gr = {new OperatorAdd(), new OperatorSub(), new OperatorMul(), new OperatorDiv(), operatorEqual};
        IFactoryOperator fo_r = new FactoryOperator(gr);
        IFactoryControl fc = new FactoryControlExpression();
        IFactoryExpression fe = new FactoryExpression();
        IExpression expression = fe.create(operatorEqual);
        IControl control = fc.create(view, fo_d, fo_r, expression);
        control.run();
    }
}