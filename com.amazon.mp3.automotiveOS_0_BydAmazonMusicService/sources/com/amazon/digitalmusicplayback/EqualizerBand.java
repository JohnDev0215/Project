package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public final class EqualizerBand {
    final byte bandIndex;
    final float bandWidth;
    final short frequency;
    final short gain;

    public EqualizerBand(short s, short s2, float f, byte b) {
        this.frequency = s;
        this.gain = s2;
        this.bandWidth = f;
        this.bandIndex = b;
    }

    public short getFrequency() {
        return this.frequency;
    }

    public short getGain() {
        return this.gain;
    }

    public float getBandWidth() {
        return this.bandWidth;
    }

    public byte getBandIndex() {
        return this.bandIndex;
    }

    public String toString() {
        return "EqualizerBand{frequency=" + ((int) this.frequency) + ",gain=" + ((int) this.gain) + ",bandWidth=" + this.bandWidth + ",bandIndex=" + ((int) this.bandIndex) + "}";
    }
}
