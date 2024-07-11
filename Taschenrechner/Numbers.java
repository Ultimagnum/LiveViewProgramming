import java.util.Map;

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
        if (equalZero() && n.equalZero()) throw new OperationNotSupportedException();
        if (equalZero()) return new Zero();
        if (n.lessZero()) throw new OperationNotSupportedException();
        if (n.equalZero()) return new Zero().addOne();
        if (isEqual(new Zero().addOne())) return new Zero().addOne();
        if (n.isEqual(new Zero().addOne())) return this;

        Numbers power = this;

        while (n.subOne().greaterZero()) {
            power = power.mul(this);
            n = n.subOne();
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

    Map<Numbers,Character> digitToChar = Map.of(
        new Zero(), '0',
        new Zero().addOne(), '1',
        new Zero().addOne().addOne(), '2',
        new Zero().addOne().addOne().addOne(),'3',
        new Zero().addOne().addOne().addOne().addOne(),'4',
        new Zero().addOne().addOne().addOne().addOne().addOne(),'5',
        new Zero().addOne().addOne().addOne().addOne().addOne().addOne(),'6',
        new Zero().addOne().addOne().addOne().addOne().addOne().addOne().addOne(),'7',
        new Zero().addOne().addOne().addOne().addOne().addOne().addOne().addOne().addOne(),'8',
        new Zero().addOne().addOne().addOne().addOne().addOne().addOne().addOne().addOne().addOne(),'9'
    );

    default String asString() throws OperationNotSupportedException {
        if (this.equalZero()) return "0";

        boolean negative = this.lessZero();
        Numbers number;
        if (negative) number = this.neg();
        else number = this;
        String string = "";

        while (!number.equalZero()) {

            Numbers digit = number.mod(OneToTen.TEN.value);
            
            string = digitToChar.get(digit) + string;

            number = number.div(OneToTen.TEN.value);
        }

        if (negative) return "-" + string;
        return string;
    }
}
