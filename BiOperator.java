import java.util.function.BiFunction;

import javax.naming.OperationNotSupportedException;

import Taschenrechner.Numbers;

public enum BiOperator implements BiFunction<Numbers, Numbers, Numbers> {
    ADD ((x,y) -> x.add(y)),
    SUB ((x,y) -> x.sub(y)),
    MUL ((x,y) -> x.mul(y)),
    EXP ((x,y) -> {
        try {
            return x.exp(y);
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
    BiOperator(BiFunction<Numbers, Numbers, Numbers> op) {this.op = op;}
    @Override
    public Numbers apply(Numbers x, Numbers y) {
        return op.apply(x, y);
    }
}
