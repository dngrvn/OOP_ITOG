package calc.view;

import calc.interfaces.IFactoryView;
import calc.interfaces.IView;

public class FactoryView implements IFactoryView {
    public IView create(String title, String charset, char[] exitLetters) {
        return new ViewConsole(title, charset, exitLetters);
    }

}
