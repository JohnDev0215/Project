package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public final class EqualizerUpdate {
    final byte bandIndex;
    final short gainMillibels;

    public EqualizerUpdate(short s, byte b) {
        this.gainMillibels = s;
        this.bandIndex = b;
    }

    public short getGainMillibels() {
        return this.gainMillibels;
    }

    public byte getBandIndex() {
        return this.bandIndex;
    }

    public String toString() {
        return "EqualizerUpdate{gainMillibels=" + ((int) this.gainMillibels) + ",bandIndex=" + ((int) this.bandIndex) + "}";
    }
}
