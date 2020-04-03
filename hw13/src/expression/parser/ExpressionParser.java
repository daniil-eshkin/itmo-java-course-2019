package expression.parser;

import expression.*;
import expression.exceptions.*;
import expression.tokenizer.*;

import java.io.IOException;

public class ExpressionParser implements Parser {
    Tokenizer tokenizer;

    private CommonExpression expr() throws IOException {
        CommonExpression a = item();
        while (true) {
            if (tokenizer.curToken() == Token.PLUS) {
                tokenizer.getToken();
                a = new CheckedAdd(a, item());
            } else if (tokenizer.curToken() == Token.MINUS) {
                tokenizer.getToken();
                a = new CheckedSubtract(a, item());
            } else {
                break;
            }
        }
        return a;
    }

    private CommonExpression item() throws IOException {
        CommonExpression a = unary();
        while (true) {
            if (tokenizer.curToken() == Token.MUL) {
                tokenizer.getToken();
                a = new CheckedMultiply(a, unary());
            } else if (tokenizer.curToken() == Token.DIV) {
                tokenizer.getToken();
                a = new CheckedDivide(a, unary());
            } else {
                break;
            }
        }
        return a;
    }

    private CommonExpression unary() throws IOException {
        CommonExpression a;
        switch (tokenizer.curToken()) {
            case VAR:
                a = new Variable(tokenizer.value());
                break;
            case NUM:
                a = new Const(Integer.parseInt(tokenizer.value()));
                break;
            case OPEN:
                tokenizer.getToken();
                a = expr();
                if (tokenizer.curToken() != Token.CLOSE) {
                    throw new IOException();
                }
                break;
            case MINUS:
                if (tokenizer.getToken() == Token.NUM) {
                    a = new Const(Integer.parseInt("-" + tokenizer.value()));
                } else {
                    a = new CheckedNegate(unary());
                    return a;
                }
                break;
            default:
                throw new IOException();
        }
        tokenizer.getToken();
        return a;
    }

    @Override
    public CommonExpression parse(String expression) {
        tokenizer = new Tokenizer(expression);
        CommonExpression a = null;
        try {
            tokenizer.getToken();
            a = expr();
            if (tokenizer.curToken() != Token.END) {
                throw new IOException();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return a;
    }
}
