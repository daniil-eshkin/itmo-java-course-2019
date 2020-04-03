package expression.tokenizer;

import expression.exceptions.UndefinedTokenException;

import java.awt.image.TileObserver;
import java.io.*;
import java.util.Map;

public class Tokenizer {
    private static String[] OPERATORS = {"**", "//", "pow2", "log2", "x", "y", "z", "(", ")", "+", "-", "*", "/"};
    private static Token[] TOKENS = {Token.POW, Token.LOG, Token.POW2, Token.LOG2, Token.VAR, Token.VAR, Token.VAR,
        Token.OPEN, Token.CLOSE, Token.PLUS, Token.MINUS, Token.MUL, Token.DIV};

    private String expression;
    private int ptr, prevptr;
    private Token curr;
    private String value;

    public Tokenizer(String expression) {
        this.expression = expression;
        ptr = prevptr = 0;
        curr = Token.UNDEF;
        value = "";
        getToken();
    }

    private void skipWhitespaces() {
        while (ptr < expression.length() && Character.isWhitespace(expression.charAt(ptr))) {
            ptr++;
        }
    }

    public Token curToken() {
        return curr;
    }

    public String value() {
        String result = this.value;
        getToken();
        return result;
    }

    public String prefix() {
        return expression.substring(0, prevptr) + " _ ";
    }

    public Token getToken() throws UndefinedTokenException {
        prevptr = ptr;
        skipWhitespaces();
        if (ptr == expression.length()) {
            value = "";
            curr = Token.END;
        } else if (Character.isDigit(expression.charAt(ptr))) {
            StringBuilder s = new StringBuilder();
            for (; ptr < expression.length() && Character.isDigit(expression.charAt(ptr)); ptr++) {
                s.append(expression.charAt(ptr));
            }
            value = s.toString();
            curr = Token.NUM;
        } else {
            curr = Token.UNDEF;
            for (int i = 0; i < OPERATORS.length; i++) {
                if (expression.startsWith(OPERATORS[i], ptr)) {
                    if (TOKENS[i] == Token.LOG2 || TOKENS[i] == Token.POW2) {
                        int j = ptr + OPERATORS[i].length();
                        if (Character.isDigit(expression.charAt(j)) || Character.isLetter(expression.charAt(j))) {
                            throw new UndefinedTokenException("Cannot find any valid token at " + prefix());
                        }
                    }
                    value = OPERATORS[i];
                    curr = TOKENS[i];
                    ptr += OPERATORS[i].length();
                    break;
                }
            }
            if (curr == Token.UNDEF) {
                throw new UndefinedTokenException("Cannot find any valid token at " + prefix());
            }
        }

        return curr;
    }
}
