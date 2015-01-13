package org.leggy.eveapi.resources;

import java.text.NumberFormat;

public class MissionSummationEntry implements Comparable<MissionSummationEntry> {
    private String name;
    private double value;

    public MissionSummationEntry(String name, double value) {
        this.name = name;
        this.value = value;

    }

    @Override
    public int compareTo(MissionSummationEntry e) {
        if (e.value > this.value) {
            return 1;
        } else if (e.value < this.value) {
            return -1;
        }
        return 0;
    }

    public String toString() {
        return "[td]" + name + "[/td][td]" + NumberFormat.getIntegerInstance().format(this.value) + "[/td][/tr]";
    }
}
