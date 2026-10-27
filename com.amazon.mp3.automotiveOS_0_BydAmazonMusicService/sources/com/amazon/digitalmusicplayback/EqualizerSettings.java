package com.amazon.digitalmusicplayback;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class EqualizerSettings {
    final ArrayList<EqualizerBand> equalizerBandList;
    final EqualizerFormat equalizerFormat;
    final short gainLowerLimit;
    final short gainUpperLimit;
    final String name;

    public EqualizerSettings(ArrayList<EqualizerBand> arrayList, EqualizerFormat equalizerFormat, short s, short s2, String str) {
        this.equalizerBandList = arrayList;
        this.equalizerFormat = equalizerFormat;
        this.gainLowerLimit = s;
        this.gainUpperLimit = s2;
        this.name = str;
    }

    public ArrayList<EqualizerBand> getEqualizerBandList() {
        return this.equalizerBandList;
    }

    public EqualizerFormat getEqualizerFormat() {
        return this.equalizerFormat;
    }

    public short getGainLowerLimit() {
        return this.gainLowerLimit;
    }

    public short getGainUpperLimit() {
        return this.gainUpperLimit;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "EqualizerSettings{equalizerBandList=" + this.equalizerBandList + ",equalizerFormat=" + this.equalizerFormat + ",gainLowerLimit=" + ((int) this.gainLowerLimit) + ",gainUpperLimit=" + ((int) this.gainUpperLimit) + ",name=" + this.name + "}";
    }
}
