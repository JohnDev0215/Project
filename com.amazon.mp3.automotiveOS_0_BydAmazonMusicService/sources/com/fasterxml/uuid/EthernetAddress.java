package com.fasterxml.uuid;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;

/* JADX INFO: loaded from: classes6.dex */
public class EthernetAddress implements Serializable, Cloneable, Comparable<EthernetAddress> {
    private static final char[] HEX_CHARS = "0123456789abcdefABCDEF".toCharArray();
    protected static Random _rnd;
    protected final long _address;
    private volatile String _asString;

    public EthernetAddress(byte[] bArr) throws NumberFormatException {
        if (bArr.length != 6) {
            throw new NumberFormatException("Ethernet address has to consist of 6 bytes");
        }
        long j = bArr[0] & 255;
        for (int i = 1; i < 6; i++) {
            j = (j << 8) | ((long) (bArr[i] & 255));
        }
        this._address = j;
    }

    public EthernetAddress(long j) {
        this._address = j;
    }

    public Object clone() {
        return new EthernetAddress(this._address);
    }

    public static EthernetAddress constructMulticastAddress() {
        return constructMulticastAddress(_randomNumberGenerator());
    }

    public static EthernetAddress constructMulticastAddress(Random random) {
        byte[] bArr = new byte[6];
        synchronized (random) {
            random.nextBytes(bArr);
        }
        bArr[0] = (byte) (bArr[0] | 1);
        return new EthernetAddress(bArr);
    }

    public void toByteArray(byte[] bArr, int i) {
        if (i < 0 || i + 6 > bArr.length) {
            throw new IllegalArgumentException("Illegal offset (" + i + "), need room for 6 bytes");
        }
        long j = this._address;
        int i2 = (int) (j >> 32);
        int i3 = i + 1;
        bArr[i] = (byte) (i2 >> 8);
        int i4 = i3 + 1;
        bArr[i3] = (byte) i2;
        int i5 = (int) j;
        int i6 = i4 + 1;
        bArr[i4] = (byte) (i5 >> 24);
        int i7 = i6 + 1;
        bArr[i6] = (byte) (i5 >> 16);
        bArr[i7] = (byte) (i5 >> 8);
        bArr[i7 + 1] = (byte) i5;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && obj.getClass() == getClass() && ((EthernetAddress) obj)._address == this._address;
    }

    @Override // java.lang.Comparable
    public int compareTo(EthernetAddress ethernetAddress) {
        long j = this._address - ethernetAddress._address;
        if (j < 0) {
            return -1;
        }
        return j == 0 ? 0 : 1;
    }

    public String toString() {
        String str = this._asString;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder(17);
        long j = this._address;
        int i = (int) (j >> 32);
        int i2 = (int) j;
        _appendHex(sb, i >> 8);
        sb.append(':');
        _appendHex(sb, i);
        sb.append(':');
        _appendHex(sb, i2 >> 24);
        sb.append(':');
        _appendHex(sb, i2 >> 16);
        sb.append(':');
        _appendHex(sb, i2 >> 8);
        sb.append(':');
        _appendHex(sb, i2);
        String string = sb.toString();
        this._asString = string;
        return string;
    }

    protected static synchronized Random _randomNumberGenerator() {
        if (_rnd == null) {
            _rnd = new SecureRandom();
        }
        return _rnd;
    }

    private final void _appendHex(StringBuilder sb, int i) {
        char[] cArr = HEX_CHARS;
        sb.append(cArr[(i >> 4) & 15]);
        sb.append(cArr[i & 15]);
    }
}
