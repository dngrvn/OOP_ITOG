package calc.model.complex;

import calc.interfaces.IFactoryOperand;
import calc.interfaces.IOperand;
import calc.util.ExceptionUser;

import java.util.logging.Logger;

public class FactoryOperand implements IFactoryOperand {
    private static final Logger logger = Logger.getLogger(FactoryOperand.class.getName());


    @Override
    public IOperand create(String strOperand) throws ExceptionUser {
        String strRe = "";
        String strIm = "";
        String s = strOperand.replace(" ","");
        s = s.strip();
        if (!s.contains("i"))
            strRe = s;
        else {
            String temp = s;
            if(temp.contains("e") || temp.contains("E")) {
                temp = temp.replace("e-","em");
                temp = temp.replace("e+","ep");
                temp = temp.replace("E-","Em");
                temp = temp.replace("E+","Ep");
            }
            int index = temp.lastIndexOf("-"); // поищу знак между Re и Im
            if (index <= 0)
                index = temp.lastIndexOf("+"); // поищу знак между Re и Im
            if (index <= 0) { // вещественная часть 0
                strIm = s.substring(0, s.length());
                if (strIm.matches(".*[0-9]+.*")) // если цифры у Im
                    strIm = strIm.substring(0, strIm.length() - 1);
                else strIm = strIm.replace('i', '1');
            } else {
                strRe = s.substring(0, index);
                strIm = s.substring(index, s.length());
                if (strIm.matches(".*[0-9]+.*")) // если цифры у Im
                    strIm = strIm.substring(0, strIm.length() - 1);
                else strIm = strIm.replace('i', '1');
            }
        }
        if(strRe.isEmpty()) strRe = "0";
        if(strIm.isEmpty()) strIm = "0";
        //logger.info(strOperand+" => Re="+strRe+" Im="+strIm);

        Double realValue = null;
        Double imaginaryValue = null;
        try {
            realValue = Double.valueOf(strRe);
            imaginaryValue = Double.valueOf(strIm);
        } catch (NumberFormatException e) {
            throw new ExceptionUser("!!! Ошибка данных.");
        }
        if (realValue == null || realValue.isNaN())
            throw new ExceptionUser("!!! Ошибка данных.");
        logger.info("Распознано: Re="+realValue+" Im="+imaginaryValue);
        return new OperandComplex(realValue, imaginaryValue);
    }

    @Override
    public String description() {
        return "Операнд - Комплексное число в алгебраической форме а+bi (например: 2-3i, -1, 0, 1, 1.2, -0.1+i, 1.52E-2).";
    }
    @Override
    public String shortDescription() {
        return "Комплексное число.";
    }
}
