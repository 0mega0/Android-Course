package com.example.calculator;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

public class Calculate {
    String formula = "";
    public Calculate(String formula){
        this.formula = formula;
    }
    public static String cal(String formula){
        String result = "";
        try {
            Symbols s = new Symbols();

            double res = s.eval(formula);
            result = String.valueOf(res);

        } catch (SyntaxException e) {
            result="Error Input\nPress Clear button to continue";
        }
        return result;
    }
}
