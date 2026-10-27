package net.lingala.zip4j.util;

/* JADX INFO: loaded from: classes.dex */
public class BitUtils {
    public static boolean isBitSet(byte b2, int i) {
        return ((1 << i) & ((long) b2)) != 0;
    }

    public static byte setBit(byte b2, int i) {
        return (byte) (b2 | (1 << i));
    }

    public static byte unsetBit(byte b2, int i) {
        return (byte) (b2 & (~(1 << i)));
    }
}
