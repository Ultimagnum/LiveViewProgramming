import java.util.function.BiFunction;

import javax.naming.OperationNotSupportedException;

public enum Operator implements BiFunction<Numbers, Numbers, Numbers> {
    ADD { public Numbers apply(Numbers a, Numbers b) { try {
        return a.add(b);
    } catch (OperationNotSupportedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return new Zero(); }},

    SUB { public Numbers apply(Numbers a, Numbers b) { try {
        return a.sub(b);
    } catch (OperationNotSupportedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return new Zero(); }},

    MUL { public Numbers apply(Numbers a, Numbers b) { try {
        return a.mul(b);
    } catch (OperationNotSupportedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return new Zero(); }},

    DIV { public Numbers apply(Numbers a, Numbers b) { try {
        return a.div(b);
    } catch (OperationNotSupportedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return new Zero(); }},

    MOD { public Numbers apply(Numbers a, Numbers b) { try {
        return a.mod(b);
    } catch (OperationNotSupportedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return new Zero(); }}
}
