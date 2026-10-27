package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public final class FrequencySettings {
    final short gainLowerLimit;
    final short gainMillibel;
    final short gainUpperLimit;

    public FrequencySettings(short s, short s2, short s3) {
        this.gainMillibel = s;
        this.gainLowerLimit = s2;
        this.gainUpperLimit = s3;
    }

    public short getGainMillibel() {
        return this.gainMillibel;
    }

    public short getGainLowerLimit() {
        return this.gainLowerLimit;
    }

    public short getGainUpperLimit() {
        return this.gainUpperLimit;
    }

    public String toString() {
        return "FrequencySettings{gainMillibel=" + ((int) this.gainMillibel) + ",gainLowerLimit=" + ((int) this.gainLowerLimit) + ",gainUpperLimit=" + ((int) this.gainUpperLimit) + "}";
    }
}
