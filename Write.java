void writeNumbers(Numbers n) throws OperationNotSupportedException {
    Clerk.markdown(n.asString());
}

void writeIsEqual(Numbers n, Numbers m) throws OperationNotSupportedException  {
    if(n.isEqual(m)) Clerk.markdown("Beide Zahlen haben den Wert: " + n.asString());
    else Clerk.markdown("Die Zahlen haben unterschiedliche Werte: " + n.asString() + " und "+ m.asString());
}

void writeIsGreater(Numbers n, Numbers m) throws OperationNotSupportedException  {
    if (n.isGreater(m)) Clerk.markdown(n.asString() + " ist größer als " + m.asString());
    else Clerk.markdown(n.asString() + " ist nicht größer als " + m.asString());
}

void writeIsLesser(Numbers n, Numbers m) throws OperationNotSupportedException  {
    if (n.isLess(m)) Clerk.markdown(n.asString() + " ist kleiner als " + m.asString());
    else Clerk.markdown(n.asString() + " ist nicht kleiner als " + m.asString());
}

void writeAdd(Numbers n, Numbers m) throws OperationNotSupportedException {
    String a = n.asString();
    String b = m.asString();
    if (n.lessZero()) a = "(" + a + ")";
    if (m.lessZero()) b = "(" + b + ")";
    Clerk.markdown(a + " + " + b + " = " + n.add(m).asString());
}

void writeSub(Numbers n, Numbers m) throws OperationNotSupportedException {
    String a = n.asString();
    String b = m.asString();
    if (n.lessZero()) a = "(" + a + ")";
    if (m.lessZero()) b = "(" + b + ")";
    Clerk.markdown(a + " - " + b + " = " + n.sub(m).asString());
}


void writeMul(Numbers n, Numbers m) throws OperationNotSupportedException {
    String a = n.asString();
    String b = m.asString();
    if (n.lessZero()) a = "(" + a + ")";
    if (m.lessZero()) b = "(" + b + ")";
    Clerk.markdown(a + " * " + b + " = " + n.mul(m).asString());
}

void writeExp(Numbers n, Numbers m) throws OperationNotSupportedException {
    String a = n.asString();
    String b = m.asString();
    if (n.lessZero()) a = "(" + a + ")";
    if (m.lessZero()) b = "(" + b + ")";
    Clerk.markdown(a + " ^ " + b + " = " + n.exp(m).asString());
}

void writeDiv(Numbers n, Numbers m) throws OperationNotSupportedException {
    String a = n.asString();
    String b = m.asString();
    if (n.lessZero()) a = "(" + a + ")";
    if (m.lessZero()) b = "(" + b + ")";
    Clerk.markdown(a + " / " + b + " = " + n.div(m).asString());
}

void writeMod(Numbers n, Numbers m) throws OperationNotSupportedException {
    String a = n.asString();
    String b = m.asString();
    if (n.lessZero()) a = "(" + a + ")";
    if (m.lessZero()) b = "(" + b + ")";
    Clerk.markdown(a + " % " + b + " = " + n.mod(m).asString());
}
