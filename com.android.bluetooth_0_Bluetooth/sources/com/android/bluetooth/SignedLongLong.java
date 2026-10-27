package com.android.bluetooth;

import com.android.bluetooth.map.BluetoothMapUtils;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes.dex */
public class SignedLongLong implements Comparable<SignedLongLong> {
    private long mLeastSigBits;
    private long mMostSigBits;

    public SignedLongLong(long leastSigBits, long mostSigBits) {
        this.mMostSigBits = mostSigBits;
        this.mLeastSigBits = leastSigBits;
    }

    public static SignedLongLong fromString(String value) throws UnsupportedEncodingException {
        String lsbStr;
        long msb = 0;
        if (value == null) {
            throw null;
        }
        String value2 = value.trim();
        int valueLength = value2.length();
        if (valueLength == 0 || valueLength > 32) {
            throw new NumberFormatException("invalid string length: " + valueLength);
        }
        if (valueLength <= 16) {
            lsbStr = value2;
        } else {
            lsbStr = value2.substring(valueLength - 16, valueLength);
            String msbStr = value2.substring(0, valueLength - 16);
            msb = BluetoothMapUtils.getLongFromString(msbStr);
        }
        long lsb = BluetoothMapUtils.getLongFromString(lsbStr);
        return new SignedLongLong(lsb, msb);
    }

    @Override // java.lang.Comparable
    public int compareTo(SignedLongLong another) {
        long j = this.mMostSigBits;
        long j2 = another.mMostSigBits;
        if (j != j2) {
            return j < j2 ? -1 : 1;
        }
        long j3 = this.mLeastSigBits;
        long j4 = another.mLeastSigBits;
        if (j3 == j4) {
            return 0;
        }
        return j3 < j4 ? -1 : 1;
    }

    public String toString() {
        return toHexString();
    }

    public String toHexString() {
        return BluetoothMapUtils.getLongLongAsString(this.mLeastSigBits, this.mMostSigBits);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SignedLongLong other = (SignedLongLong) obj;
        if (this.mLeastSigBits == other.mLeastSigBits && this.mMostSigBits == other.mMostSigBits) {
            return true;
        }
        return false;
    }

    public long getMostSignificantBits() {
        return this.mMostSigBits;
    }

    public long getLeastSignificantBits() {
        return this.mLeastSigBits;
    }
}
