package org.tukaani.xz.rangecoder;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class RangeEncoder extends RangeCoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int BIT_PRICE_SHIFT_BITS = 4;
    private static final int MOVE_REDUCING_BITS = 4;
    private static final int[] prices = new int[128];
    private byte cache;
    long cacheSize;
    private long low;
    private int range;

    abstract void writeByte(int i) throws IOException;

    static {
        for (int i = 8; i < 2048; i += 16) {
            int w = i;
            int bitCount = 0;
            for (int j = 0; j < 4; j++) {
                w *= w;
                bitCount <<= 1;
                while (((-65536) & w) != 0) {
                    w >>>= 1;
                    bitCount++;
                }
            }
            prices[i >> 4] = 161 - bitCount;
        }
    }

    public void reset() {
        this.low = 0L;
        this.range = -1;
        this.cache = (byte) 0;
        this.cacheSize = 1L;
    }

    public int getPendingSize() {
        throw new Error();
    }

    public int finish() throws IOException {
        for (int i = 0; i < 5; i++) {
            shiftLow();
        }
        return -1;
    }

    private void shiftLow() throws IOException {
        long j;
        long j2 = this.low;
        int lowHi = (int) (j2 >>> 32);
        if (lowHi != 0 || j2 < 4278190080L) {
            int temp = this.cache;
            do {
                writeByte(temp + lowHi);
                temp = 255;
                j = this.cacheSize - 1;
                this.cacheSize = j;
            } while (j != 0);
            this.cache = (byte) (this.low >>> 24);
        }
        this.cacheSize++;
        this.low = (this.low & 16777215) << 8;
    }

    public void encodeBit(short[] probs, int index, int bit) throws IOException {
        short s = probs[index];
        int i = this.range;
        int bound = (i >>> 11) * s;
        if (bit == 0) {
            this.range = bound;
            probs[index] = (short) (((2048 - s) >>> 5) + s);
        } else {
            this.low += ((long) bound) & 4294967295L;
            this.range = i - bound;
            probs[index] = (short) (s - (s >>> 5));
        }
        int i2 = this.range;
        if (((-16777216) & i2) == 0) {
            this.range = i2 << 8;
            shiftLow();
        }
    }

    public static int getBitPrice(int prob, int bit) {
        return prices[(((-bit) & 2047) ^ prob) >>> 4];
    }

    public void encodeBitTree(short[] probs, int symbol) throws IOException {
        int index = 1;
        int mask = probs.length;
        do {
            mask >>>= 1;
            int bit = symbol & mask;
            encodeBit(probs, index, bit);
            index <<= 1;
            if (bit != 0) {
                index |= 1;
            }
        } while (mask != 1);
    }

    public static int getBitTreePrice(short[] probs, int symbol) {
        int price = 0;
        int symbol2 = symbol | probs.length;
        do {
            int bit = symbol2 & 1;
            symbol2 >>>= 1;
            price += getBitPrice(probs[symbol2], bit);
        } while (symbol2 != 1);
        return price;
    }

    public void encodeReverseBitTree(short[] probs, int symbol) throws IOException {
        int index = 1;
        int symbol2 = symbol | probs.length;
        do {
            int bit = symbol2 & 1;
            symbol2 >>>= 1;
            encodeBit(probs, index, bit);
            index = (index << 1) | bit;
        } while (symbol2 != 1);
    }

    public static int getReverseBitTreePrice(short[] probs, int symbol) {
        int price = 0;
        int index = 1;
        int symbol2 = symbol | probs.length;
        do {
            int bit = symbol2 & 1;
            symbol2 >>>= 1;
            price += getBitPrice(probs[index], bit);
            index = (index << 1) | bit;
        } while (symbol2 != 1);
        return price;
    }

    public void encodeDirectBits(int value, int count) throws IOException {
        do {
            int i = this.range >>> 1;
            this.range = i;
            count--;
            this.low += (long) ((0 - ((value >>> count) & 1)) & i);
            if (((-16777216) & i) == 0) {
                this.range = i << 8;
                shiftLow();
            }
        } while (count != 0);
    }

    public static int getDirectBitsPrice(int count) {
        return count << 4;
    }
}
