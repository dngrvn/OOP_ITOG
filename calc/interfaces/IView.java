package calc.interfaces;

import calc.view.ExceptionExit;

public interface IView {
    String request(String description) throws ExceptionExit;
    void viewText(String text);
}
