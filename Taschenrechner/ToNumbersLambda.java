import java.util.function.Function;

Function<String,Numbers> toNumbers = (Function<String,Numbers>) n -> {if (n == "N") return new Zero();
                                                    String m = n;
                                                    m = m.replace("CM", "DCD");
                                                    m = m.replace("M", "DD");
                                                    m = m.replace("CD", "CCCC");
                                                    m = m.replace("D", "CCCCC");
                                                    m = m.replace("XC", "LXL");
                                                    m = m.replace("C", "LL");
                                                    m = m.replace("XL", "XXXX");
                                                    m = m.replace("L", "XXXXX");
                                                    m = m.replace("IX", "VIV");
                                                    m = m.replace("X", "VV");
                                                    m = m.replace("IV", "IIII");
                                                    m = m.replace("V", "IIIII");

                                                    Numbers myNumber = new Zero();
                                                    for (char c : m.toCharArray()) {
                                                        myNumber = myNumber.addOne();
                                                    }
                                                    return myNumber;
                                                    };