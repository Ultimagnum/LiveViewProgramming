import javax.naming.OperationNotSupportedException;

import java.util.Arrays;
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



    for (char c : revString.toCharArray()) {
        number = number.add(charToNumbers.get(c).mul(ten.exp(exponent)));
        exponent = exponent.addOne();
    }

    //Arrays.asList(string.toCharArray()).stream().mapToObj((i) -> List.of());

    if (negative) return number.neg();
    return number;
}
