package org.apache.commons.lang3;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class BitField {
    private final int _mask;
    private final int _shift_count;

    public BitField(int i) {
        this._mask = i;
        this._shift_count = i == 0 ? 0 : Integer.numberOfTrailingZeros(i);
    }

    public int clear(int i) {
        return (~this._mask) & i;
    }

    public byte clearByte(byte b2) {
        return (byte) clear(b2);
    }

    public short clearShort(short s) {
        return (short) clear(s);
    }

    public int getRawValue(int i) {
        return this._mask & i;
    }

    public short getShortRawValue(short s) {
        return (short) getRawValue(s);
    }

    public short getShortValue(short s) {
        return (short) getValue(s);
    }

    public int getValue(int i) {
        return getRawValue(i) >> this._shift_count;
    }

    public boolean isAllSet(int i) {
        int i2 = this._mask;
        return (i & i2) == i2;
    }

    public boolean isSet(int i) {
        return (this._mask & i) != 0;
    }

    public int set(int i) {
        return this._mask | i;
    }

    public int setBoolean(int i, boolean z) {
        return z ? set(i) : clear(i);
    }

    public byte setByte(byte b2) {
        return (byte) set(b2);
    }

    public byte setByteBoolean(byte b2, boolean z) {
        return z ? setByte(b2) : clearByte(b2);
    }

    public short setShort(short s) {
        return (short) set(s);
    }

    public short setShortBoolean(short s, boolean z) {
        return z ? setShort(s) : clearShort(s);
    }

    public short setShortValue(short s, short s2) {
        return (short) setValue(s, s2);
    }

    public int setValue(int i, int i2) {
        int i3 = this._mask;
        return ((i2 << this._shift_count) & i3) | (i & (~i3));
    }
}
