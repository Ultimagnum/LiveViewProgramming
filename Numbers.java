import java.util.function.Supplier;

import javax.naming.OperationNotSupportedException;


public sealed interface Numbers permits NonZero, Zero {
    
    default boolean isZero() {
        return switch (this) {
            case Zero z -> true;
            case NonZero nz -> false;
            default -> false;
        };
    }

    default Numbers addOne() {
        return new NonZero(this);
    }

    default Numbers subOne() throws OperationNotSupportedException {
        return switch (this) {
            case NonZero(Numbers pred) -> pred;
            default -> throw new OperationNotSupportedException();
        };
    }


    default Numbers add(Numbers n) throws OperationNotSupportedException {
        if (isZero()) return n;
        if (n.isZero()) return this;
        Numbers myNumber = this;

        while (!n.isZero()) {
            myNumber = myNumber.addOne();
            n = n.subOne();
        }

        return myNumber;
    }

    default Numbers sub(Numbers n) {
        if (isZero()) throw new OperationNotSupportedException;
        if ()
    }



    default String asString() throws OperationNotSupportedException {
        if (isZero()) return "N";
        String myString = "";
        Numbers myNumber = this;

        while (!myNumber.isZero()) {
            myString += "I";
            myNumber = myNumber.subOne();
        }

        myString = myString.replace("IIIII", "V");
        myString = myString.replace("IIII", "IV");
        myString = myString.replace("VV", "X");
        myString = myString.replace("VIV", "IX");
        myString = myString.replace("XXXXX", "L");
        myString = myString.replace("XXXX", "XL");
        myString = myString.replace("LL", "C");
        myString = myString.replace("LXL", "XC");
        myString = myString.replace("CCCCC", "D");
        myString = myString.replace("CCCC", "CD");
        myString = myString.replace("DD", "M");
        myString = myString.replace("DCD", "CM");

        return myString;
    }
}

public record Zero() implements Numbers {
    
}

public record NonZero(Numbers pred) implements Numbers {
    
}
