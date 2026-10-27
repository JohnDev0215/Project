package SevenZip.Compression.RangeCoder;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class Encoder {
    private static int[] ProbPrices = new int[512];
    static final int kBitModelTotal = 2048;
    static final int kNumBitModelTotalBits = 11;
    public static final int kNumBitPriceShiftBits = 6;
    static final int kNumMoveBits = 5;
    static final int kNumMoveReducingBits = 2;
    static final int kTopMask = -16777216;
    long Low;
    int Range;
    OutputStream Stream;
    int _cache;
    int _cacheSize;
    long _position;

    public void SetStream(OutputStream outputStream) {
        this.Stream = outputStream;
    }

    public void ReleaseStream() {
        this.Stream = null;
    }

    public void Init() {
        this._position = 0L;
        this.Low = 0L;
        this.Range = -1;
        this._cacheSize = 1;
        this._cache = 0;
    }

    public void FlushData() throws IOException {
        for (int i = 0; i < 5; i++) {
            ShiftLow();
        }
    }

    public void FlushStream() throws IOException {
        this.Stream.flush();
    }

    public void ShiftLow() throws IOException {
        long j = this.Low;
        int i = (int) (j >>> 32);
        if (i != 0 || j < 4278190080L) {
            this._position += (long) this._cacheSize;
            int i2 = this._cache;
            while (true) {
                this.Stream.write(i2 + i);
                int i3 = this._cacheSize - 1;
                this._cacheSize = i3;
                if (i3 == 0) {
                    break;
                } else {
                    i2 = 255;
                }
            }
            this._cache = ((int) this.Low) >>> 24;
        }
        this._cacheSize++;
        this.Low = (this.Low & 16777215) << 8;
    }

    public void EncodeDirectBits(int i, int i2) throws IOException {
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            int i4 = this.Range >>> 1;
            this.Range = i4;
            if (((i >>> i3) & 1) == 1) {
                this.Low += (long) i4;
            }
            if (((-16777216) & i4) == 0) {
                this.Range = i4 << 8;
                ShiftLow();
            }
        }
    }

    public long GetProcessedSizeAdd() {
        return ((long) this._cacheSize) + this._position + 4;
    }

    public static void InitBitModels(short[] sArr) {
        for (int i = 0; i < sArr.length; i++) {
            sArr[i] = 1024;
        }
    }

    public void Encode(short[] sArr, int i, int i2) throws IOException {
        short s = sArr[i];
        int i3 = this.Range;
        int i4 = (i3 >>> 11) * s;
        if (i2 == 0) {
            this.Range = i4;
            sArr[i] = (short) (s + ((2048 - s) >>> 5));
        } else {
            this.Low += ((long) i4) & 4294967295L;
            this.Range = i3 - i4;
            sArr[i] = (short) (s - (s >>> 5));
        }
        int i5 = this.Range;
        if (((-16777216) & i5) == 0) {
            this.Range = i5 << 8;
            ShiftLow();
        }
    }

    static {
        for (int i = 8; i >= 0; i--) {
            int i2 = 9 - i;
            int i3 = i2 - 1;
            int i4 = 1 << i2;
            for (int i5 = 1 << i3; i5 < i4; i5++) {
                ProbPrices[i5] = (i << 6) + (((i4 - i5) << 6) >>> i3);
            }
        }
    }

    public static int GetPrice(int i, int i2) {
        return ProbPrices[(((i - i2) ^ (-i2)) & 2047) >>> 2];
    }

    public static int GetPrice0(int i) {
        return ProbPrices[i >>> 2];
    }

    public static int GetPrice1(int i) {
        return ProbPrices[(2048 - i) >>> 2];
    }
}
