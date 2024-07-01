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
        Numbers sum = this;
        Numbers summand = n;

        while (!summand.isZero()) {
            sum = sum.addOne();
            summand = summand.subOne();
        }

        return sum;
    }

    default Numbers sub(Numbers n) throws OperationNotSupportedException {
        if (isZero()) throw new OperationNotSupportedException();
        if (n.isZero()) return this;
        Numbers difference = this;
        Numbers minuend = n;

        while (!minuend.isZero()) {
            difference = difference.subOne();
            minuend = minuend.subOne();
        }

        return difference;
    }

    default Numbers mul(Numbers n) throws OperationNotSupportedException {
        if (isZero() || n.isZero()) return new Zero();

        Numbers product = new Zero();
        Numbers factor1 = this;
        Numbers factor2 = n;

        while (!factor2.isZero()) {
            product = product.add(factor1);
            factor2 = factor2.subOne();
        }

        return product;
    }

    default Numbers div(Numbers n) throws OperationNotSupportedException {
        if (isZero()) return new Zero();
        if (n.isZero()) throw new OperationNotSupportedException();

        Numbers quotient = new Zero();
        Numbers dividend = this;
        Numbers divisor = n;

        while (!dividend.isZero()) {
            try {
                dividend = dividend.sub(divisor);
            } catch (Exception e) {
                break;
            }
            quotient = quotient.addOne();
        }

        return quotient;
    }

    default Numbers mod(Numbers n) throws OperationNotSupportedException {
        if (isZero()) return new Zero();
        if (n.isZero()) throw new OperationNotSupportedException();

        Numbers dividend = this;
        Numbers divisor = n;

        while (!dividend.isZero()) {
            try {
                dividend = dividend.sub(divisor);
            } catch (Exception e) {
                break;
            }
        }

        return dividend;
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