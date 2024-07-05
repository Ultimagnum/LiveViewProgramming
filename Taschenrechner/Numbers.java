import javax.naming.OperationNotSupportedException;

public sealed interface Numbers permits NonZero, NegativeNonZero, Zero {
    
    default boolean isZero() {
        return switch (this) {
            case Zero z -> true;
            case NonZero nz -> false;
            case NegativeNonZero nnz -> false;
            default -> false;
        };
    }

    default Numbers addOne() {
        return switch (this) {
            case NonZero(Numbers pred) -> new NonZero(this);
            case Zero() -> new NonZero(this);
            case NegativeNonZero(Numbers succ) -> succ;
        }; 
    }

    default Numbers subOne() {
        return switch (this) {
            case NonZero(Numbers pred) -> pred;
            case Zero() -> new NegativeNonZero(this);
            case NegativeNonZero(Numbers succ) -> new NegativeNonZero(this);
        };
    }

    default Numbers add(Numbers n) {
        if (isZero()) return n;
        if (n.isZero()) return this;
        Numbers sum = this;
        Numbers summand = n;

        while (!summand.isZero()) {
            
            switch (summand) {
                case NonZero nz:
                    sum = sum.addOne();
                    summand = summand.subOne();
                    break;
                case NegativeNonZero nnz:
                    sum = sum.subOne();
                    summand = summand.addOne();
                    break;
                default: break;
            }
        }
        return sum;
    }

    default Numbers sub(Numbers n) {
        if (n.isZero()) return this;
        Numbers difference = this;
        Numbers minuend = n;

        while (!minuend.isZero()) {
            switch (minuend) {
                case NonZero nz:
                    difference = difference.subOne();
                    minuend = minuend.subOne();
                    break;

                case NegativeNonZero nnz:
                    difference = difference.addOne();
                    minuend = minuend.addOne();
                    break;
                
                default: break;
            }
        }

        return difference;
    }

    default Numbers neg() {
        return new Zero().sub(this);
    }

    /* default Numbers mul(Numbers n) {
        if (isZero() || n.isZero()) return new Zero();

        Numbers product = new Zero();
        Numbers factor1 = this;
        Numbers factor2 = n;

        while (!factor2.isZero()) {
            product = product.add(factor1);
            switch (factor2) {
                case NonZero nz -> factor2.subOne();
                case NegativeNonZero nnz -> factor2.addOne();
            }
        }

        return product;
    } */

    /* default Numbers div(Numbers n) throws OperationNotSupportedException {
        if (isZero()) return new Zero();
        if (n.isZero()) throw new OperationNotSupportedException();

        Numbers quotient = new Zero();
        Numbers dividend = this;
        Numbers divisor = n;



        switch (divisor) {
            case NonZero nz:
            dividend = dividend.sub(divisor)    
            break;
        
            case NegativeNonZero nnz:
            break;
        }

        while (!dividend.isZero()) {
            try {
                dividend = dividend.sub(divisor);
            } catch (Exception e) {
                break;
            }
            quotient = quotient.addOne();
        }

        return quotient;
    } */

    /* default Numbers mod(Numbers n) throws OperationNotSupportedException {
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
    } */
    
    default boolean isEqual(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (Zero.class != other.getClass() || NonZero.class != other.getClass() || NegativeNonZero.class != other.getClass()) return false;
        Numbers that = (Numbers) other;
        if (sub(that).isZero()) return true;
        return false;
    }

    /* default String asString() throws OperationNotSupportedException {
        if (isZero()) return "N";
        String myString;
        switch (this) {
            case NonZero nz -> myString = "";
            case NegativeNonZero nnz -> myString = "-";
        }
        Numbers myNumber = this;

        while (!myNumber.isZero()) {
            myString += "I";
            switch (myNumber) {
                case NonZero nz -> myNumber.subOne();
                case NegativeNonZero nnz -> myNumber.addOne();
            }
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
    } */

    /* default Numbers asObject(String myString) {
        if (myString.equals("N")) {
            return new Zero();
        }

        myString = myString.replace("CM", "DCD");
        myString = myString.replace("M", "DD");
        myString = myString.replace("CD", "CCCC");
        myString = myString.replace("D", "CCCCC");
        myString = myString.replace("XC", "LXL");
        myString = myString.replace("C", "LL");
        myString = myString.replace("XL", "XXXX");
        myString = myString.replace("L", "XXXXX");
        myString = myString.replace("IX", "VIV");
        myString = myString.replace("X", "VV");
        myString = myString.replace("IV", "IIII");
        myString = myString.replace("V", "IIIII");

        Numbers myNumber = new Zero();
        for (int i = 0; i < myString.length(); i++) {
            myNumber.addOne();
        }

        return myNumber;
    } */
}
