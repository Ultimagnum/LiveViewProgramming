public enum OneToTen {
    ONE  (new Zero().addOne()),
    TWO  (new Zero().addOne().addOne()),
    THREE(new Zero().addOne().addOne().addOne()),
    FOUR (new Zero().addOne().addOne().addOne().addOne()),
    FIVE (new Zero().addOne().addOne().addOne().addOne().addOne()),
    SIX  (new Zero().addOne().addOne().addOne().addOne().addOne().addOne()),
    SEVEN(new Zero().addOne().addOne().addOne().addOne().addOne().addOne().addOne()),
    EIGHT(new Zero().addOne().addOne().addOne().addOne().addOne().addOne().addOne().addOne()),
    NINE (new Zero().addOne().addOne().addOne().addOne().addOne().addOne().addOne().addOne().addOne()),
    TEN  (new Zero().addOne().addOne().addOne().addOne().addOne().addOne().addOne().addOne().addOne().addOne());

    Numbers value;

    OneToTen(Numbers value) {
        this.value = value;
    }
}
