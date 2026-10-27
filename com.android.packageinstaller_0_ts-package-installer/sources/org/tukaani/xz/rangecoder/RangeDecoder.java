package org.tukaani.xz.rangecoder;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class RangeDecoder extends RangeCoder {
    int range = 0;
    int code = 0;

    public abstract void normalize() throws IOException;

    public int decodeBit(short[] probs, int index) throws IOException {
        normalize();
        short s = probs[index];
        int bit = this.range;
        int bound = (bit >>> 11) * s;
        int i = this.code;
        if ((i ^ Integer.MIN_VALUE) < (Integer.MIN_VALUE ^ bound)) {
            this.range = bound;
            probs[index] = (short) (((2048 - s) >>> 5) + s);
            return 0;
        }
        this.range = bit - bound;
        this.code = i - bound;
        probs[index] = (short) (s - (s >>> 5));
        return 1;
    }

    public int decodeBitTree(short[] probs) throws IOException {
        int symbol = 1;
        do {
            symbol = (symbol << 1) | decodeBit(probs, symbol);
        } while (symbol < probs.length);
        return symbol - probs.length;
    }

    public int decodeReverseBitTree(short[] probs) throws IOException {
        int symbol = 1;
        int i = 0;
        int result = 0;
        while (true) {
            int bit = decodeBit(probs, symbol);
            symbol = (symbol << 1) | bit;
            int i2 = i + 1;
            result |= bit << i;
            if (symbol < probs.length) {
                i = i2;
            } else {
                return result;
            }
        }
    }

    public int decodeDirectBits(int count) throws IOException {
        int result = 0;
        do {
            normalize();
            int i = this.range >>> 1;
            this.range = i;
            int i2 = this.code;
            int t = (i2 - i) >>> 31;
            this.code = i2 - (i & (t - 1));
            result = (result << 1) | (1 - t);
            count--;
        } while (count != 0);
        return result;
    }
}
