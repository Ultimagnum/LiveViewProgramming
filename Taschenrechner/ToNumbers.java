import javax.naming.OperationNotSupportedException;
import java.util.Map;


Map<Character, Numbers> charToNumbers = Map.of(
    '0', new Zero(),
    '1', new Zero().addOne(),
    '2', new Zero().addOne().addOne(),
    '3', new Zero().addOne().addOne().addOne(),
    '4', new Zero().addOne().addOne().addOne().addOne(),
    '5', new Zero().addOne().addOne().addOne().addOne().addOne(),
    '6', new Zero().addOne().addOne().addOne().addOne().addOne().addOne(),
    '7', new Zero().addOne().addOne().addOne().addOne().addOne().addOne().addOne(),
    '8', new Zero().addOne().addOne().addOne().addOne().addOne().addOne().addOne().addOne(),
    '9', new Zero().addOne().addOne().addOne().addOne().addOne().addOne().addOne().addOne().addOne()

);

/* Numbers charToNumbers(char c) {
    return switch(c) {
        case '0' -> new Zero();
        case '1' -> OneToTen.ONE.value;
        case '2' -> OneToTen.TWO.value;
        case '3' -> OneToTen.THREE.value;
        case '4' -> OneToTen.FOUR.value;
        case '5' -> OneToTen.FIVE.value;
        case '6' -> OneToTen.SIX.value;
        case '7' -> OneToTen.SEVEN.value;
        case '8' -> OneToTen.EIGHT.value;
        case '9' -> OneToTen.NINE.value;
        default -> new Zero();
    };
} */

Numbers stringToNumbers(String string) throws OperationNotSupportedException {
    if (string.length() == 1) return charToNumbers.get(string.charAt(0));
    boolean negative = string.charAt(0) == '-';
    string.replace("-", "");

    Numbers number = new Zero();
    Numbers ten = new Zero().addOne().addOne().addOne().addOne().addOne().addOne().addOne().addOne().addOne().addOne();
    Numbers exponent = new Zero();
    String revString = "";

    for (char c : string.toCharArray()) {
        revString = c + revString;
    }

    System.out.println(string);
    System.out.println(revString);


    for (char c : revString.toCharArray()) {
        number = number.add(charToNumbers.get(c).mul(ten.exp(exponent)));
        exponent = exponent.addOne();
    }

    if (negative) return number.neg();
    return number;
}
