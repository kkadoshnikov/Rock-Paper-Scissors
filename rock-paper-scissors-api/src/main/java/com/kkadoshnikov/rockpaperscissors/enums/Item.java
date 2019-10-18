package com.kkadoshnikov.rockpaperscissors.enums;

/**
 * Game's items enumeration.
 */
public enum Item {

    ROCK("SCISSORS", "PAPER"),
    PAPER("ROCK", "SCISSORS"),
    SCISSORS("PAPER", "ROCK");

    private final String beat;
    private final String beatenBy;

    /**
     * Constructor.
     * @param beat - name of item which is beaten by this item.
     * @param beatenBy - name of item which beats this item.
     */
    Item(final String beat, String beatenBy) {
        this.beat = beat;
        this.beatenBy = beatenBy;
    }

    /**
     * @return item which is beaten by this item.
     */
    public Item beat() {
        return Item.valueOf(beat);
    }

    /**
     * @return item which beats this item.
     */
    public Item beatenBy() {
        return Item.valueOf(beatenBy);
    }
}
