package org.leggy.eveapi.resources;

import java.text.NumberFormat;

public class JournalSummationEntry implements Comparable<JournalSummationEntry> {
    private String name;
    private double value;

    public JournalSummationEntry(String name, double value) {
        this.name = name;
        this.value = value;

    }

    @Override
    public int compareTo(JournalSummationEntry e) {
        if (e.value > this.value) {
            return 1;
        } else if (e.value < this.value) {
            return -1;
        }
        return 0;
    }

    public String toString() {
        return "[td]" + name + "[/td][td]" + NumberFormat.getCurrencyInstance().format(this.value) + "[/td][/tr]";
    }
}
