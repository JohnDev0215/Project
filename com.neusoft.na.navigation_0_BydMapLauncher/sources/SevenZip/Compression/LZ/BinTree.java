package SevenZip.Compression.LZ;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class BinTree extends InWindow {
    private static final int[] CrcTable = new int[256];
    static final int kBT2HashSize = 65536;
    static final int kEmptyHashValue = 0;
    static final int kHash2Size = 1024;
    static final int kHash3Offset = 1024;
    static final int kHash3Size = 65536;
    static final int kMaxValForNormalize = 1073741823;
    static final int kStartMaxLen = 1;
    int _cyclicBufferPos;
    int[] _hash;
    int _hashMask;
    int _matchMaxLen;
    int[] _son;
    int _cyclicBufferSize = 0;
    int _cutValue = 255;
    int _hashSizeSum = 0;
    boolean HASH_ARRAY = true;
    int kNumHashDirectBytes = 0;
    int kMinMatchCheck = 4;
    int kFixHashSize = 66560;

    public void SetType(int i) {
        boolean z = i > 2;
        this.HASH_ARRAY = z;
        if (z) {
            this.kNumHashDirectBytes = 0;
            this.kMinMatchCheck = 4;
            this.kFixHashSize = 66560;
        } else {
            this.kNumHashDirectBytes = 2;
            this.kMinMatchCheck = 3;
            this.kFixHashSize = 0;
        }
    }

    @Override // SevenZip.Compression.LZ.InWindow
    public void Init() throws IOException {
        super.Init();
        for (int i = 0; i < this._hashSizeSum; i++) {
            this._hash[i] = 0;
        }
        this._cyclicBufferPos = 0;
        ReduceOffsets(-1);
    }

    @Override // SevenZip.Compression.LZ.InWindow
    public void MovePos() throws IOException {
        int i = this._cyclicBufferPos + 1;
        this._cyclicBufferPos = i;
        if (i >= this._cyclicBufferSize) {
            this._cyclicBufferPos = 0;
        }
        super.MovePos();
        if (this._pos == 1073741823) {
            Normalize();
        }
    }

    public boolean Create(int i, int i2, int i3, int i4) {
        int i5;
        if (i > 1073741567) {
            return false;
        }
        this._cutValue = (i3 >> 1) + 16;
        int i6 = i2 + i;
        super.Create(i6, i4 + i3, (((i6 + i3) + i4) / 2) + 256);
        this._matchMaxLen = i3;
        int i7 = i + 1;
        if (this._cyclicBufferSize != i7) {
            this._cyclicBufferSize = i7;
            this._son = new int[i7 * 2];
        }
        if (this.HASH_ARRAY) {
            int i8 = i - 1;
            int i9 = i8 | (i8 >> 1);
            int i10 = i9 | (i9 >> 2);
            int i11 = i10 | (i10 >> 4);
            int i12 = ((i11 | (i11 >> 8)) >> 1) | 65535;
            if (i12 > 16777216) {
                i12 >>= 1;
            }
            this._hashMask = i12;
            i5 = i12 + 1 + this.kFixHashSize;
        } else {
            i5 = 65536;
        }
        if (i5 != this._hashSizeSum) {
            this._hashSizeSum = i5;
            this._hash = new int[i5];
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0119  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int GetMatches(int[] r20) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 454
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: SevenZip.Compression.LZ.BinTree.GetMatches(int[]):int");
    }

    public void Skip(int i) throws IOException {
        int i2;
        int i3;
        int i4 = i;
        do {
            if (this._pos + this._matchMaxLen <= this._streamPos) {
                i2 = this._matchMaxLen;
            } else {
                i2 = this._streamPos - this._pos;
                if (i2 < this.kMinMatchCheck) {
                    MovePos();
                }
                i4--;
            }
            int i5 = this._pos > this._cyclicBufferSize ? this._pos - this._cyclicBufferSize : 0;
            int i6 = this._bufferOffset + this._pos;
            if (this.HASH_ARRAY) {
                int[] iArr = CrcTable;
                int i7 = iArr[this._bufferBase[i6] & 255] ^ (this._bufferBase[i6 + 1] & 255);
                this._hash[i7 & 1023] = this._pos;
                int i8 = i7 ^ ((this._bufferBase[i6 + 2] & 255) << 8);
                this._hash[(65535 & i8) + 1024] = this._pos;
                i3 = ((iArr[this._bufferBase[i6 + 3] & 255] << 5) ^ i8) & this._hashMask;
            } else {
                i3 = (this._bufferBase[i6] & 255) ^ ((this._bufferBase[i6 + 1] & 255) << 8);
            }
            int[] iArr2 = this._hash;
            int i9 = this.kFixHashSize;
            int i10 = iArr2[i9 + i3];
            iArr2[i9 + i3] = this._pos;
            int i11 = this._cyclicBufferPos;
            int i12 = (i11 << 1) + 1;
            int i13 = i11 << 1;
            int i14 = this.kNumHashDirectBytes;
            int i15 = this._cutValue;
            int i16 = i10;
            int i17 = i14;
            while (i16 > i5) {
                int i18 = i15 - 1;
                if (i15 == 0) {
                    break;
                }
                int i19 = this._pos - i16;
                int i20 = this._cyclicBufferPos;
                int i21 = (i19 <= i20 ? i20 - i19 : (i20 - i19) + this._cyclicBufferSize) << 1;
                int i22 = this._bufferOffset + i16;
                int iMin = Math.min(i14, i17);
                if (this._bufferBase[i22 + iMin] == this._bufferBase[i6 + iMin]) {
                    do {
                        iMin++;
                        if (iMin == i2) {
                            break;
                        }
                    } while (this._bufferBase[i22 + iMin] == this._bufferBase[i6 + iMin]);
                    if (iMin == i2) {
                        int[] iArr3 = this._son;
                        iArr3[i13] = iArr3[i21];
                        iArr3[i12] = iArr3[i21 + 1];
                        break;
                    }
                }
                if ((this._bufferBase[i22 + iMin] & 255) < (this._bufferBase[i6 + iMin] & 255)) {
                    int[] iArr4 = this._son;
                    iArr4[i13] = i16;
                    int i23 = i21 + 1;
                    i16 = iArr4[i23];
                    i13 = i23;
                    i17 = iMin;
                } else {
                    int[] iArr5 = this._son;
                    iArr5[i12] = i16;
                    i16 = iArr5[i21];
                    i12 = i21;
                    i14 = iMin;
                }
                i15 = i18;
            }
            int[] iArr6 = this._son;
            iArr6[i13] = 0;
            iArr6[i12] = 0;
            MovePos();
            i4--;
        } while (i4 != 0);
    }

    void NormalizeLinks(int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr[i3];
            iArr[i3] = i4 <= i2 ? 0 : i4 - i2;
        }
    }

    void Normalize() {
        int i = this._pos;
        int i2 = this._cyclicBufferSize;
        int i3 = i - i2;
        NormalizeLinks(this._son, i2 * 2, i3);
        NormalizeLinks(this._hash, this._hashSizeSum, i3);
        ReduceOffsets(i3);
    }

    public void SetCutValue(int i) {
        this._cutValue = i;
    }

    static {
        for (int i = 0; i < 256; i++) {
            int i2 = i;
            for (int i3 = 0; i3 < 8; i3++) {
                i2 = (i2 & 1) != 0 ? (i2 >>> 1) ^ (-306674912) : i2 >>> 1;
            }
            CrcTable[i] = i2;
        }
    }
}
