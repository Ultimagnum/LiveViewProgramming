package Taschenrechner;
import java.util.function.BiFunction;

import javax.naming.OperationNotSupportedException;

public enum Operator implements BiFunction<Numbers, Numbers, Numbers> {
    ADD ((x,y) -> {
        try {
            return x.add(y);
        } catch (OperationNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }),
    SUB ((x,y) -> {
        try {
            return x.sub(y);
        } catch (OperationNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }),
    MUL ((x,y) -> {
        try {
            return x.mul(y);
        } catch (OperationNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }),
    DIV ((x,y) -> {
        try {
            return x.div(y);
        } catch (OperationNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }),
    MOD ((x,y) -> {
        try {
            return x.mod(y);
        } catch (OperationNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    });
    final BiFunction<Numbers, Numbers, Numbers> op;
    Operator(BiFunction<Numbers, Numbers, Numbers> op) {this.op = op;}
    @Override
    public Numbers apply(Numbers x, Numbers y) {
        return op.apply(x, y);
    }
}
