Numbers charToNumbers(char c) {
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
    }
}

Numbers stringToNumbers(String string) {
    if (string.length() == 1) return charToNumbers(string.charAt(0));
    boolean negative = string.charAt(0) == '-';
    string.replace("-", "");

    Numbers number = new Zero();
    Numbers exponent = new Zero();
    String revString = "";

    for (char c : string.toCharArray()) {
        revString = c + revString;
    }

    for (char c : string.toCharArray()) {
        number = number.add(charToNumbers(c).mul(OneToTen.TEN.value.exp(exponent)));
        exponent = exponent.addOne();
    }

    if (negative) return number.neg();
    return number;
}
