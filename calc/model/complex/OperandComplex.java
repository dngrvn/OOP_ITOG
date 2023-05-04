package calc.model.complex;

import calc.interfaces.IOperand;

public class OperandComplex  implements IOperand {
    static final public double ZERO = 0.0;
    private double realValue;
    private double imaginaryValue;

    public OperandComplex(double realValue, double imaginaryValue) {
        this.realValue = realValue;
        this.imaginaryValue = imaginaryValue;
    }
    @Override
    public double getRealValue() {
        return realValue;
    }
    @Override
    public double getImaginaryValue() {
        return imaginaryValue;
    }

    @Override
    public String valueToString() {
        StringBuilder sb = new StringBuilder();
        if(realValue == ZERO) {
            sb.append(imaginaryValue);
            if (imaginaryValue != ZERO)
                sb.append("i");
        } else {
            sb.append(realValue);
            if (imaginaryValue < ZERO)
                sb.append(imaginaryValue).append("i");
            else if (imaginaryValue > ZERO)
                sb.append("+").append(imaginaryValue).append("i");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return valueToString();
    }
}
