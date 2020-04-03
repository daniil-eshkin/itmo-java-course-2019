package expression.exceptions;

import expression.*;
import expression.tokenizer.*;

public class ExpressionParser implements Parser {
    private Tokenizer tokenizer;

    private boolean test(Token token) throws UndefinedTokenException {
        boolean result = (tokenizer.curToken() == token);
        if (result) {
            tokenizer.getToken();
        }
        return result;
    }

    private CommonExpression expr() throws ParseException {
        CommonExpression a = mulDiv();
        while (true) {
            if (test(Token.PLUS)) {
                a = new CheckedAdd(a, mulDiv());
            } else if (test(Token.MINUS)) {
                a = new CheckedSubtract(a, mulDiv());
            } else {
                return a;
            }
        }
    }

    private CommonExpression mulDiv() throws ParseException {
        CommonExpression a = powLog();
        while (true) {
            if (test(Token.MUL)) {
                a = new CheckedMultiply(a, powLog());
            } else if (test(Token.DIV)) {
                a = new CheckedDivide(a, powLog());
            } else {
                return a;
            }
        }
    }

    private CommonExpression powLog() throws ParseException {
        CommonExpression a = unary();
        while (true) {
            if (test(Token.POW)) {
                a = new CheckedPow(a, unary());
            } else if (test(Token.LOG)) {
                a = new CheckedLog(a, unary());
            } else {
                return a;
            }
        }
    }

    private Const createConst(String value) throws ParseOverflowException {
        try {
            return new Const(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            throw new ParseOverflowException(e.getMessage());
        }
    }

    private CommonExpression unary() throws ParseException {
        switch (tokenizer.curToken()) {
            case VAR:
                return Variable.valueOf(tokenizer.value());
            case NUM:
                return createConst(tokenizer.value());
            case OPEN:
                tokenizer.getToken();
                CommonExpression a = expr();
                if (!test(Token.CLOSE)) {
                    throw new UnexpectedTokenException("Expected operator or closing parenthesis at " +
                            tokenizer.prefix() + "Found: " + tokenizer.value());
                }
                return a;
            case MINUS:
                if (tokenizer.getToken() == Token.NUM) {
                    return createConst("-" + tokenizer.value());
                } else {
                    return new CheckedNegate(unary());
                }
            case POW2:
                tokenizer.getToken();
                return new CheckedPow2(unary());
            case LOG2:
                tokenizer.getToken();
                return new CheckedLog2(unary());
            default:
                throw new UnexpectedTokenException("Expected unary operator, constant, variable or opening parenthesis at " +
                        tokenizer.prefix() + "Found: " + tokenizer.value());
        }
    }

    @Override
    public CommonExpression parse(String expression) throws ParseException {
        tokenizer = new Tokenizer(expression);
        CommonExpression a = expr();
        if (tokenizer.curToken() != Token.END) {
            throw new UnexpectedTokenException("Expected operator or the end of expression at " +
                    tokenizer.prefix() + "Found: " + tokenizer.value());
        }
        return a;
    }
}
