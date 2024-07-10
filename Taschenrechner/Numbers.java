import javax.naming.OperationNotSupportedException;

public sealed interface Numbers permits PositiveNonZero, NegativeNonZero, Zero {
    
    default boolean equalZero() {
        return switch (this) {
            case Zero z -> true;
            case PositiveNonZero pnz -> false;
            case NegativeNonZero nnz -> false;
            default -> false;
        };
    }

    default boolean greaterZero() {
        return switch (this) {
            case Zero z -> false;
            case PositiveNonZero pnz -> true;
            case NegativeNonZero nnz -> false;
            default -> false;
        };
    }

    default boolean lessZero() {
        return switch (this) {
            case Zero z -> false;
            case PositiveNonZero pnz -> false;
            case NegativeNonZero nnz -> true;
            default -> false;
        };
    }

    default Numbers addOne() {
        return switch (this) {
            case PositiveNonZero(Numbers pred) -> new PositiveNonZero(this);
            case Zero() -> new PositiveNonZero(this);
            case NegativeNonZero(Numbers succ) -> succ;
        }; 
    }

    default Numbers subOne() {
        return switch (this) {
            case PositiveNonZero(Numbers pred) -> pred;
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
                case PositiveNonZero pnz:
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
                case PositiveNonZero pnz:
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
                case PositiveNonZero pnz:
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

    default Numbers exp(Numbers n) throws OperationNotSupportedException {
        if (equalZero()) return new Zero();
        if (n.equalZero()) return new Zero().addOne();
        if (isEqual(new Zero().addOne())) return new Zero().addOne();
        if (n.isEqual(new Zero().addOne())) return this;
        if (n.lessZero()) throw new OperationNotSupportedException();

        Numbers power = this;

        while (n.subOne().greaterZero()) {
            power = power.mul(this);
            n.subOne();
        }

        return power;
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

    default boolean greater(Numbers other) {
        if (other == null) return false;
        if (other == this) return false;
        if (sub(other).greaterZero()) return true;
        return false;
    }

    default boolean less(Numbers other) {
        if (other == null) return false;
        if (other == this) return false;
        if (sub(other).lessZero()) return true;
        return false;
    }

    default String asString() {
        if (this.equalZero()) return "0";
        Numbers number = this;
        String string = "";

        while (!number.equalZero()) {

            Numbers digit = number.mod(OneToTen.TEN.value);
            
            switch (digit) {
                case new Zero() -> string = "0" + string;
                case OneToTen.ONE.value -> string = "1" + string;
                case OneToTen.TWO.value -> string = "2" + string;
                case OneToTen.THREE.value -> string = "3" + string;
                case OneToTen.FOUR.value -> string = "4" + string;
                case OneToTen.FIVE.value -> string = "5" + string;
                case OneToTen.SIX.value -> string = "6" + string;
                case OneToTen.SEVEN.value -> string = "7" + string;
                case OneToTen.EIGHT.value -> string = "8" + string;
                case OneToTen.NINE.value -> string = "9" + string;
                default -> string = "0" + string;
            }

            number = number.div(OneToTen.TEN.value);
        }

        return switch(number) {
            case PositiveNonZero pnz -> string;
            case NegativeNonZero nnz -> "-" + string;
            default -> "0";
        };
    }

    /* default String asString() throws OperationNotSupportedException {
        if (equalZero()) return "N";
        String myString;
        switch (this) {
            case NonZero pnz -> myString = "";
            case NegativeNonZero nnz -> myString = "-";
        }
        Numbers myNumber = this;

        while (!myNumber.equalZero()) {
            myString += "I";
            switch (myNumber) {
                case NonZero pnz -> myNumber.subOne();
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
