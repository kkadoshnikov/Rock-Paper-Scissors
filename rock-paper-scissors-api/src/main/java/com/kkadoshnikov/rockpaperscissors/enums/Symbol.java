package com.kkadoshnikov.rockpaperscissors.enums;

/**
 * Game's symbols enumeration.
 */
public enum Symbol {

    ROCK("SCISSORS", "PAPER"),
    PAPER("ROCK", "SCISSORS"),
    SCISSORS("PAPER", "ROCK");

    private final String beat;
    private final String beatenBy;

    /**
     * Constructor.
     * @param beat - name of symbol which is beaten by this symbol.
     * @param beatenBy - name of symbol which beats this symbol.
     */
    Symbol(final String beat, String beatenBy) {
        this.beat = beat;
        this.beatenBy = beatenBy;
    }

    /**
     * @return symbol which is beaten by this symbol.
     */
    public Symbol beat() {
        return Symbol.valueOf(beat);
    }

    /**
     * @return symbol which beats this symbol.
     */
    public Symbol beatenBy() {
        return Symbol.valueOf(beatenBy);
    }
}
