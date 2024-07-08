import javax.naming.OperationNotSupportedException;

public sealed interface Numbers permits NonZero, NegativeNonZero, Zero {
    
    default boolean equalZero() {
        return switch (this) {
            case Zero z -> true;
            case NonZero nz -> false;
            case NegativeNonZero nnz -> false;
            default -> false;
        };
    }

    default boolean notZero() {
        return switch (this) {
            case Zero z -> false;
            case NonZero nz -> true;
            case NegativeNonZero nnz -> true;
            default -> false;
        };
    }

    default boolean greaterZero() {
        return switch (this) {
            case Zero z -> false;
            case NonZero nz -> true;
            case NegativeNonZero nnz -> false;
            default -> false;
        };
    }

    default boolean greaterEqualZero() {
        return switch (this) {
            case Zero z -> true;
            case NonZero nz -> true;
            case NegativeNonZero nnz -> false;
            default -> false;
        };
    }

    default boolean lessZero() {
        return switch (this) {
            case Zero z -> false;
            case NonZero nz -> false;
            case NegativeNonZero nnz -> true;
            default -> false;
        };
    }

    default boolean lessEqualZero() {
        return switch (this) {
            case Zero z -> true;
            case NonZero nz -> false;
            case NegativeNonZero nnz -> true;
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
        if (equalZero()) return n;
        if (n.equalZero()) return this;
        Numbers sum = this;
        Numbers summand = n;

        while (!summand.equalZero()) {
            
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
        if (n.equalZero()) return this;
        Numbers difference = this;
        Numbers minuend = n;

        while (!minuend.equalZero()) {
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

    default Numbers mul(Numbers n) {
        if (equalZero() || n.equalZero()) return new Zero();

        Numbers product = new Zero();
        Numbers factor1 = this;
        Numbers factor2 = n;

        while (!factor2.equalZero()) {

            
            product = product.add(factor1);
            
            switch (factor2) {
                case NonZero nz:
                factor2 = factor2.subOne();
                break;

                case NegativeNonZero nnz:
                factor2 = factor2.addOne();
                break;

                default:break;
            }
        }
        
        if (n.lessZero()) return product.neg();
        return product;
    }

    default Numbers div(Numbers n) throws OperationNotSupportedException {
        if (equalZero()) return new Zero();
        if (n.equalZero()) throw new OperationNotSupportedException();

        Numbers dividend;
        Numbers divisor;
        Numbers quotient = new Zero();

        if (this.greaterZero()) dividend = this;
        else dividend = this.neg();

        if (n.greaterZero()) divisor = n;
        else divisor = n.neg();

        while (!dividend.sub(divisor).lessZero()) {
            dividend = dividend.sub(divisor);
            quotient = quotient.addOne();
        }

        if (this.getClass() == n.getClass()) return quotient;
        return quotient.neg();
    }

    default Numbers mod(Numbers n) throws OperationNotSupportedException {
        if (equalZero()) return new Zero();
        if (n.equalZero()) throw new OperationNotSupportedException();

        Numbers dividend;
        Numbers divisor;

        if (this.greaterZero()) dividend = this;
        else dividend = this.neg();

        if (n.greaterZero()) divisor = n;
        else divisor = n.neg();

        while (!dividend.sub(divisor).lessZero()) {
            dividend = dividend.sub(divisor);
        }

        if (this.getClass() == n.getClass()) return dividend;
        return dividend.neg();
    }
    
    default boolean isEqual(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (getClass() != other.getClass()) return false;
        Numbers that = (Numbers) other;
        if (sub(that).equalZero()) return true;
        return false;
    }

    /* default String asString() throws OperationNotSupportedException {
        if (equalZero()) return "N";
        String myString;
        switch (this) {
            case NonZero nz -> myString = "";
            case NegativeNonZero nnz -> myString = "-";
        }
        Numbers myNumber = this;

        while (!myNumber.equalZero()) {
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
