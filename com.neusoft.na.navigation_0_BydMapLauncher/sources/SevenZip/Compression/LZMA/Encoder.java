package SevenZip.Compression.LZMA;

import SevenZip.Compression.LZ.BinTree;
import SevenZip.Compression.RangeCoder.BitTreeEncoder;
import SevenZip.ICodeProgress;
import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class Encoder {
    public static final int EMatchFinderTypeBT2 = 0;
    public static final int EMatchFinderTypeBT4 = 1;
    static byte[] g_FastPos = null;
    static final int kDefaultDictionaryLogSize = 22;
    static final int kIfinityPrice = 268435455;
    static final int kNumFastBytesDefault = 32;
    public static final int kNumLenSpecSymbols = 16;
    static final int kNumOpts = 4096;
    public static final int kPropSize = 5;
    int _additionalOffset;
    int _alignPriceCount;
    boolean _finished;
    InputStream _inStream;
    int _longestMatchLength;
    boolean _longestMatchWasFound;
    int _matchPriceCount;
    int _numDistancePairs;
    int _optimumCurrentIndex;
    int _optimumEndIndex;
    byte _previousByte;
    int backRes;
    long nowPos64;
    int _state = Base.StateInit();
    int[] _repDistances = new int[4];
    Optimal[] _optimum = new Optimal[4096];
    BinTree _matchFinder = null;
    SevenZip.Compression.RangeCoder.Encoder _rangeEncoder = new SevenZip.Compression.RangeCoder.Encoder();
    short[] _isMatch = new short[192];
    short[] _isRep = new short[12];
    short[] _isRepG0 = new short[12];
    short[] _isRepG1 = new short[12];
    short[] _isRepG2 = new short[12];
    short[] _isRep0Long = new short[192];
    BitTreeEncoder[] _posSlotEncoder = new BitTreeEncoder[4];
    short[] _posEncoders = new short[114];
    BitTreeEncoder _posAlignEncoder = new BitTreeEncoder(4);
    LenPriceTableEncoder _lenEncoder = new LenPriceTableEncoder();
    LenPriceTableEncoder _repMatchLenEncoder = new LenPriceTableEncoder();
    LiteralEncoder _literalEncoder = new LiteralEncoder();
    int[] _matchDistances = new int[548];
    int _numFastBytes = 32;
    int[] _posSlotPrices = new int[256];
    int[] _distancesPrices = new int[512];
    int[] _alignPrices = new int[16];
    int _distTableSize = 44;
    int _posStateBits = 2;
    int _posStateMask = 3;
    int _numLiteralPosStateBits = 0;
    int _numLiteralContextBits = 3;
    int _dictionarySize = 4194304;
    int _dictionarySizePrev = -1;
    int _numFastBytesPrev = -1;
    int _matchFinderType = 1;
    boolean _writeEndMark = false;
    boolean _needReleaseMFStream = false;
    int[] reps = new int[4];
    int[] repLens = new int[4];
    long[] processedInSize = new long[1];
    long[] processedOutSize = new long[1];
    boolean[] finished = new boolean[1];
    byte[] properties = new byte[5];
    int[] tempPrices = new int[128];

    boolean ChangePair(int i, int i2) {
        return i < 33554432 && i2 >= (i << 7);
    }

    public boolean SetAlgorithm(int i) {
        return true;
    }

    static {
        byte[] bArr = new byte[2048];
        g_FastPos = bArr;
        bArr[0] = 0;
        bArr[1] = 1;
        int i = 2;
        for (int i2 = 2; i2 < 22; i2++) {
            int i3 = 1 << ((i2 >> 1) - 1);
            int i4 = 0;
            while (i4 < i3) {
                g_FastPos[i] = (byte) i2;
                i4++;
                i++;
            }
        }
    }

    static int GetPosSlot(int i) {
        if (i < 2048) {
            return g_FastPos[i];
        }
        if (i < 2097152) {
            return g_FastPos[i >> 10] + 20;
        }
        return g_FastPos[i >> 20] + 40;
    }

    static int GetPosSlot2(int i) {
        if (i < 131072) {
            return g_FastPos[i >> 6] + 12;
        }
        if (i < 134217728) {
            return g_FastPos[i >> 16] + 32;
        }
        return g_FastPos[i >> 26] + 52;
    }

    void BaseInit() {
        this._state = Base.StateInit();
        this._previousByte = (byte) 0;
        for (int i = 0; i < 4; i++) {
            this._repDistances[i] = 0;
        }
    }

    class LiteralEncoder {
        Encoder2[] m_Coders;
        int m_NumPosBits;
        int m_NumPrevBits;
        int m_PosMask;

        LiteralEncoder() {
        }

        class Encoder2 {
            short[] m_Encoders = new short[768];

            Encoder2() {
            }

            public void Init() {
                SevenZip.Compression.RangeCoder.Encoder.InitBitModels(this.m_Encoders);
            }

            public void Encode(SevenZip.Compression.RangeCoder.Encoder encoder, byte b) throws IOException {
                int i = 1;
                for (int i2 = 7; i2 >= 0; i2--) {
                    int i3 = (b >> i2) & 1;
                    encoder.Encode(this.m_Encoders, i, i3);
                    i = (i << 1) | i3;
                }
            }

            public void EncodeMatched(SevenZip.Compression.RangeCoder.Encoder encoder, byte b, byte b2) throws IOException {
                int i;
                int i2 = 1;
                boolean z = true;
                for (int i3 = 7; i3 >= 0; i3--) {
                    int i4 = (b2 >> i3) & 1;
                    if (z) {
                        int i5 = (b >> i3) & 1;
                        i = ((i5 + 1) << 8) + i2;
                        z = i5 == i4;
                    } else {
                        i = i2;
                    }
                    encoder.Encode(this.m_Encoders, i, i4);
                    i2 = (i2 << 1) | i4;
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:12:0x0028  */
            /* JADX WARN: Removed duplicated region for block: B:14:0x0039 A[RETURN] */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0028 -> B:13:0x0036). Please report as a decompilation issue!!! */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public int GetPrice(boolean r8, byte r9, byte r10) {
                /*
                    r7 = this;
                    r0 = 0
                    r1 = 1
                    r2 = 7
                    if (r8 == 0) goto L25
                    r8 = r1
                L6:
                    if (r2 < 0) goto L26
                    int r3 = r9 >> r2
                    r3 = r3 & r1
                    int r4 = r10 >> r2
                    r4 = r4 & r1
                    short[] r5 = r7.m_Encoders
                    int r6 = r3 + 1
                    int r6 = r6 << 8
                    int r6 = r6 + r8
                    short r5 = r5[r6]
                    int r5 = SevenZip.Compression.RangeCoder.Encoder.GetPrice(r5, r4)
                    int r0 = r0 + r5
                    int r8 = r8 << 1
                    r8 = r8 | r4
                    if (r3 == r4) goto L22
                    goto L36
                L22:
                    int r2 = r2 + (-1)
                    goto L6
                L25:
                    r8 = r1
                L26:
                    if (r2 < 0) goto L39
                    int r9 = r10 >> r2
                    r9 = r9 & r1
                    short[] r3 = r7.m_Encoders
                    short r3 = r3[r8]
                    int r3 = SevenZip.Compression.RangeCoder.Encoder.GetPrice(r3, r9)
                    int r0 = r0 + r3
                    int r8 = r8 << r1
                    r8 = r8 | r9
                L36:
                    int r2 = r2 + (-1)
                    goto L26
                L39:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: SevenZip.Compression.LZMA.Encoder.LiteralEncoder.Encoder2.GetPrice(boolean, byte, byte):int");
            }
        }

        public void Create(int i, int i2) {
            if (this.m_Coders != null && this.m_NumPrevBits == i2 && this.m_NumPosBits == i) {
                return;
            }
            this.m_NumPosBits = i;
            this.m_PosMask = (1 << i) - 1;
            this.m_NumPrevBits = i2;
            int i3 = 1 << (i2 + i);
            this.m_Coders = new Encoder2[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                this.m_Coders[i4] = new Encoder2();
            }
        }

        public void Init() {
            int i = 1 << (this.m_NumPrevBits + this.m_NumPosBits);
            for (int i2 = 0; i2 < i; i2++) {
                this.m_Coders[i2].Init();
            }
        }

        public Encoder2 GetSubCoder(int i, byte b) {
            Encoder2[] encoder2Arr = this.m_Coders;
            int i2 = i & this.m_PosMask;
            int i3 = this.m_NumPrevBits;
            return encoder2Arr[(i2 << i3) + ((b & 255) >>> (8 - i3))];
        }
    }

    class LenEncoder {
        short[] _choice = new short[2];
        BitTreeEncoder[] _lowCoder = new BitTreeEncoder[16];
        BitTreeEncoder[] _midCoder = new BitTreeEncoder[16];
        BitTreeEncoder _highCoder = new BitTreeEncoder(8);

        public LenEncoder() {
            for (int i = 0; i < 16; i++) {
                this._lowCoder[i] = new BitTreeEncoder(3);
                this._midCoder[i] = new BitTreeEncoder(3);
            }
        }

        public void Init(int i) {
            SevenZip.Compression.RangeCoder.Encoder.InitBitModels(this._choice);
            for (int i2 = 0; i2 < i; i2++) {
                this._lowCoder[i2].Init();
                this._midCoder[i2].Init();
            }
            this._highCoder.Init();
        }

        public void Encode(SevenZip.Compression.RangeCoder.Encoder encoder, int i, int i2) throws IOException {
            if (i < 8) {
                encoder.Encode(this._choice, 0, 0);
                this._lowCoder[i2].Encode(encoder, i);
                return;
            }
            int i3 = i - 8;
            encoder.Encode(this._choice, 0, 1);
            if (i3 < 8) {
                encoder.Encode(this._choice, 1, 0);
                this._midCoder[i2].Encode(encoder, i3);
            } else {
                encoder.Encode(this._choice, 1, 1);
                this._highCoder.Encode(encoder, i3 - 8);
            }
        }

        public void SetPrices(int i, int i2, int[] iArr, int i3) {
            int i4 = 0;
            int iGetPrice0 = SevenZip.Compression.RangeCoder.Encoder.GetPrice0(this._choice[0]);
            int iGetPrice1 = SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._choice[0]);
            int iGetPrice02 = SevenZip.Compression.RangeCoder.Encoder.GetPrice0(this._choice[1]) + iGetPrice1;
            int iGetPrice12 = iGetPrice1 + SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._choice[1]);
            while (i4 < 8) {
                if (i4 >= i2) {
                    return;
                }
                iArr[i3 + i4] = this._lowCoder[i].GetPrice(i4) + iGetPrice0;
                i4++;
            }
            while (i4 < 16) {
                if (i4 >= i2) {
                    return;
                }
                iArr[i3 + i4] = this._midCoder[i].GetPrice(i4 - 8) + iGetPrice02;
                i4++;
            }
            while (i4 < i2) {
                iArr[i3 + i4] = this._highCoder.GetPrice((i4 - 8) - 8) + iGetPrice12;
                i4++;
            }
        }
    }

    class LenPriceTableEncoder extends LenEncoder {
        int[] _counters;
        int[] _prices;
        int _tableSize;

        LenPriceTableEncoder() {
            super();
            this._prices = new int[4352];
            this._counters = new int[16];
        }

        public void SetTableSize(int i) {
            this._tableSize = i;
        }

        public int GetPrice(int i, int i2) {
            return this._prices[(i2 * 272) + i];
        }

        void UpdateTable(int i) {
            SetPrices(i, this._tableSize, this._prices, i * 272);
            this._counters[i] = this._tableSize;
        }

        public void UpdateTables(int i) {
            for (int i2 = 0; i2 < i; i2++) {
                UpdateTable(i2);
            }
        }

        @Override // SevenZip.Compression.LZMA.Encoder.LenEncoder
        public void Encode(SevenZip.Compression.RangeCoder.Encoder encoder, int i, int i2) throws IOException {
            super.Encode(encoder, i, i2);
            int[] iArr = this._counters;
            int i3 = iArr[i2] - 1;
            iArr[i2] = i3;
            if (i3 == 0) {
                UpdateTable(i2);
            }
        }
    }

    class Optimal {
        public int BackPrev;
        public int BackPrev2;
        public int Backs0;
        public int Backs1;
        public int Backs2;
        public int Backs3;
        public int PosPrev;
        public int PosPrev2;
        public boolean Prev1IsChar;
        public boolean Prev2;
        public int Price;
        public int State;

        Optimal() {
        }

        public void MakeAsChar() {
            this.BackPrev = -1;
            this.Prev1IsChar = false;
        }

        public void MakeAsShortRep() {
            this.BackPrev = 0;
            this.Prev1IsChar = false;
        }

        public boolean IsShortRep() {
            return this.BackPrev == 0;
        }
    }

    void Create() {
        if (this._matchFinder == null) {
            BinTree binTree = new BinTree();
            binTree.SetType(this._matchFinderType == 0 ? 2 : 4);
            this._matchFinder = binTree;
        }
        this._literalEncoder.Create(this._numLiteralPosStateBits, this._numLiteralContextBits);
        int i = this._dictionarySize;
        if (i == this._dictionarySizePrev && this._numFastBytesPrev == this._numFastBytes) {
            return;
        }
        this._matchFinder.Create(i, 4096, this._numFastBytes, 274);
        this._dictionarySizePrev = this._dictionarySize;
        this._numFastBytesPrev = this._numFastBytes;
    }

    public Encoder() {
        for (int i = 0; i < 4096; i++) {
            this._optimum[i] = new Optimal();
        }
        for (int i2 = 0; i2 < 4; i2++) {
            this._posSlotEncoder[i2] = new BitTreeEncoder(6);
        }
    }

    void SetWriteEndMarkerMode(boolean z) {
        this._writeEndMark = z;
    }

    void Init() {
        BaseInit();
        this._rangeEncoder.Init();
        SevenZip.Compression.RangeCoder.Encoder.InitBitModels(this._isMatch);
        SevenZip.Compression.RangeCoder.Encoder.InitBitModels(this._isRep0Long);
        SevenZip.Compression.RangeCoder.Encoder.InitBitModels(this._isRep);
        SevenZip.Compression.RangeCoder.Encoder.InitBitModels(this._isRepG0);
        SevenZip.Compression.RangeCoder.Encoder.InitBitModels(this._isRepG1);
        SevenZip.Compression.RangeCoder.Encoder.InitBitModels(this._isRepG2);
        SevenZip.Compression.RangeCoder.Encoder.InitBitModels(this._posEncoders);
        this._literalEncoder.Init();
        for (int i = 0; i < 4; i++) {
            this._posSlotEncoder[i].Init();
        }
        this._lenEncoder.Init(1 << this._posStateBits);
        this._repMatchLenEncoder.Init(1 << this._posStateBits);
        this._posAlignEncoder.Init();
        this._longestMatchWasFound = false;
        this._optimumEndIndex = 0;
        this._optimumCurrentIndex = 0;
        this._additionalOffset = 0;
    }

    int ReadMatchDistances() throws IOException {
        int iGetMatchLen;
        int iGetMatches = this._matchFinder.GetMatches(this._matchDistances);
        this._numDistancePairs = iGetMatches;
        if (iGetMatches > 0) {
            int[] iArr = this._matchDistances;
            iGetMatchLen = iArr[iGetMatches - 2];
            if (iGetMatchLen == this._numFastBytes) {
                iGetMatchLen += this._matchFinder.GetMatchLen(iGetMatchLen - 1, iArr[iGetMatches - 1], 273 - iGetMatchLen);
            }
        } else {
            iGetMatchLen = 0;
        }
        this._additionalOffset++;
        return iGetMatchLen;
    }

    void MovePos(int i) throws IOException {
        if (i > 0) {
            this._matchFinder.Skip(i);
            this._additionalOffset += i;
        }
    }

    int GetRepLen1Price(int i, int i2) {
        return SevenZip.Compression.RangeCoder.Encoder.GetPrice0(this._isRepG0[i]) + SevenZip.Compression.RangeCoder.Encoder.GetPrice0(this._isRep0Long[(i << 4) + i2]);
    }

    int GetPureRepPrice(int i, int i2, int i3) {
        int iGetPrice;
        if (i == 0) {
            return SevenZip.Compression.RangeCoder.Encoder.GetPrice0(this._isRepG0[i2]) + SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._isRep0Long[(i2 << 4) + i3]);
        }
        int iGetPrice1 = SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._isRepG0[i2]);
        if (i == 1) {
            iGetPrice = SevenZip.Compression.RangeCoder.Encoder.GetPrice0(this._isRepG1[i2]);
        } else {
            iGetPrice1 += SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._isRepG1[i2]);
            iGetPrice = SevenZip.Compression.RangeCoder.Encoder.GetPrice(this._isRepG2[i2], i - 2);
        }
        return iGetPrice + iGetPrice1;
    }

    int GetRepPrice(int i, int i2, int i3, int i4) {
        return this._repMatchLenEncoder.GetPrice(i2 - 2, i4) + GetPureRepPrice(i, i3, i4);
    }

    int GetPosLenPrice(int i, int i2, int i3) {
        int i4;
        int iGetLenToPosState = Base.GetLenToPosState(i2);
        if (i < 128) {
            i4 = this._distancesPrices[(iGetLenToPosState * 128) + i];
        } else {
            i4 = this._alignPrices[i & 15] + this._posSlotPrices[(iGetLenToPosState << 6) + GetPosSlot2(i)];
        }
        return i4 + this._lenEncoder.GetPrice(i2 - 2, i3);
    }

    int Backward(int i) {
        this._optimumEndIndex = i;
        int i2 = this._optimum[i].PosPrev;
        int i3 = this._optimum[i].BackPrev;
        while (true) {
            if (this._optimum[i].Prev1IsChar) {
                this._optimum[i2].MakeAsChar();
                int i4 = i2 - 1;
                this._optimum[i2].PosPrev = i4;
                if (this._optimum[i].Prev2) {
                    this._optimum[i4].Prev1IsChar = false;
                    Optimal[] optimalArr = this._optimum;
                    optimalArr[i4].PosPrev = optimalArr[i].PosPrev2;
                    Optimal[] optimalArr2 = this._optimum;
                    optimalArr2[i4].BackPrev = optimalArr2[i].BackPrev2;
                }
            }
            int i5 = this._optimum[i2].BackPrev;
            int i6 = this._optimum[i2].PosPrev;
            this._optimum[i2].BackPrev = i3;
            this._optimum[i2].PosPrev = i;
            if (i2 <= 0) {
                this.backRes = this._optimum[0].BackPrev;
                int i7 = this._optimum[0].PosPrev;
                this._optimumCurrentIndex = i7;
                return i7;
            }
            i = i2;
            i3 = i5;
            i2 = i6;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:159:0x047e  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x058c  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x06e4 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v2, types: [boolean, int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    int GetOptimum(int r24) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1792
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: SevenZip.Compression.LZMA.Encoder.GetOptimum(int):int");
    }

    void WriteEndMarker(int i) throws IOException {
        if (this._writeEndMark) {
            this._rangeEncoder.Encode(this._isMatch, (this._state << 4) + i, 1);
            this._rangeEncoder.Encode(this._isRep, this._state, 0);
            this._state = Base.StateUpdateMatch(this._state);
            this._lenEncoder.Encode(this._rangeEncoder, 0, i);
            this._posSlotEncoder[Base.GetLenToPosState(2)].Encode(this._rangeEncoder, 63);
            this._rangeEncoder.EncodeDirectBits(67108863, 26);
            this._posAlignEncoder.ReverseEncode(this._rangeEncoder, 15);
        }
    }

    void Flush(int i) throws IOException {
        ReleaseMFStream();
        WriteEndMarker(i & this._posStateMask);
        this._rangeEncoder.FlushData();
        this._rangeEncoder.FlushStream();
    }

    public void CodeOneBlock(long[] jArr, long[] jArr2, boolean[] zArr) throws IOException {
        jArr[0] = 0;
        jArr2[0] = 0;
        zArr[0] = true;
        InputStream inputStream = this._inStream;
        if (inputStream != null) {
            this._matchFinder.SetStream(inputStream);
            this._matchFinder.Init();
            this._needReleaseMFStream = true;
            this._inStream = null;
        }
        if (this._finished) {
            return;
        }
        this._finished = true;
        long j = this.nowPos64;
        if (j == 0) {
            if (this._matchFinder.GetNumAvailableBytes() == 0) {
                Flush((int) this.nowPos64);
                return;
            }
            ReadMatchDistances();
            this._rangeEncoder.Encode(this._isMatch, (this._state << 4) + (((int) this.nowPos64) & this._posStateMask), 0);
            this._state = Base.StateUpdateChar(this._state);
            byte bGetIndexByte = this._matchFinder.GetIndexByte(0 - this._additionalOffset);
            this._literalEncoder.GetSubCoder((int) this.nowPos64, this._previousByte).Encode(this._rangeEncoder, bGetIndexByte);
            this._previousByte = bGetIndexByte;
            this._additionalOffset--;
            this.nowPos64++;
        }
        if (this._matchFinder.GetNumAvailableBytes() == 0) {
            Flush((int) this.nowPos64);
            return;
        }
        while (true) {
            int iGetOptimum = GetOptimum((int) this.nowPos64);
            int i = this.backRes;
            int i2 = ((int) this.nowPos64) & this._posStateMask;
            int i3 = (this._state << 4) + i2;
            if (iGetOptimum == 1 && i == -1) {
                this._rangeEncoder.Encode(this._isMatch, i3, 0);
                byte bGetIndexByte2 = this._matchFinder.GetIndexByte(0 - this._additionalOffset);
                LiteralEncoder.Encoder2 encoder2GetSubCoder = this._literalEncoder.GetSubCoder((int) this.nowPos64, this._previousByte);
                if (!Base.StateIsCharState(this._state)) {
                    encoder2GetSubCoder.EncodeMatched(this._rangeEncoder, this._matchFinder.GetIndexByte(((0 - this._repDistances[0]) - 1) - this._additionalOffset), bGetIndexByte2);
                } else {
                    encoder2GetSubCoder.Encode(this._rangeEncoder, bGetIndexByte2);
                }
                this._previousByte = bGetIndexByte2;
                this._state = Base.StateUpdateChar(this._state);
            } else {
                this._rangeEncoder.Encode(this._isMatch, i3, 1);
                if (i < 4) {
                    this._rangeEncoder.Encode(this._isRep, this._state, 1);
                    if (i == 0) {
                        this._rangeEncoder.Encode(this._isRepG0, this._state, 0);
                        if (iGetOptimum == 1) {
                            this._rangeEncoder.Encode(this._isRep0Long, i3, 0);
                        } else {
                            this._rangeEncoder.Encode(this._isRep0Long, i3, 1);
                        }
                    } else {
                        this._rangeEncoder.Encode(this._isRepG0, this._state, 1);
                        if (i == 1) {
                            this._rangeEncoder.Encode(this._isRepG1, this._state, 0);
                        } else {
                            this._rangeEncoder.Encode(this._isRepG1, this._state, 1);
                            this._rangeEncoder.Encode(this._isRepG2, this._state, i - 2);
                        }
                    }
                    if (iGetOptimum == 1) {
                        this._state = Base.StateUpdateShortRep(this._state);
                    } else {
                        this._repMatchLenEncoder.Encode(this._rangeEncoder, iGetOptimum - 2, i2);
                        this._state = Base.StateUpdateRep(this._state);
                    }
                    int i4 = this._repDistances[i];
                    if (i != 0) {
                        while (i >= 1) {
                            int[] iArr = this._repDistances;
                            iArr[i] = iArr[i - 1];
                            i--;
                        }
                        this._repDistances[0] = i4;
                    }
                } else {
                    this._rangeEncoder.Encode(this._isRep, this._state, 0);
                    this._state = Base.StateUpdateMatch(this._state);
                    this._lenEncoder.Encode(this._rangeEncoder, iGetOptimum - 2, i2);
                    int i5 = i - 4;
                    int iGetPosSlot = GetPosSlot(i5);
                    this._posSlotEncoder[Base.GetLenToPosState(iGetOptimum)].Encode(this._rangeEncoder, iGetPosSlot);
                    if (iGetPosSlot >= 4) {
                        int i6 = (iGetPosSlot >> 1) - 1;
                        int i7 = ((iGetPosSlot & 1) | 2) << i6;
                        int i8 = i5 - i7;
                        if (iGetPosSlot < 14) {
                            BitTreeEncoder.ReverseEncode(this._posEncoders, (i7 - iGetPosSlot) - 1, this._rangeEncoder, i6, i8);
                        } else {
                            this._rangeEncoder.EncodeDirectBits(i8 >> 4, i6 - 4);
                            this._posAlignEncoder.ReverseEncode(this._rangeEncoder, i8 & 15);
                            this._alignPriceCount++;
                        }
                    }
                    for (int i9 = 3; i9 >= 1; i9--) {
                        int[] iArr2 = this._repDistances;
                        iArr2[i9] = iArr2[i9 - 1];
                    }
                    this._repDistances[0] = i5;
                    this._matchPriceCount++;
                }
                this._previousByte = this._matchFinder.GetIndexByte((iGetOptimum - 1) - this._additionalOffset);
            }
            int i10 = this._additionalOffset - iGetOptimum;
            this._additionalOffset = i10;
            this.nowPos64 += (long) iGetOptimum;
            if (i10 == 0) {
                if (this._matchPriceCount >= 128) {
                    FillDistancesPrices();
                }
                if (this._alignPriceCount >= 16) {
                    FillAlignPrices();
                }
                jArr[0] = this.nowPos64;
                jArr2[0] = this._rangeEncoder.GetProcessedSizeAdd();
                if (this._matchFinder.GetNumAvailableBytes() == 0) {
                    Flush((int) this.nowPos64);
                    return;
                } else if (this.nowPos64 - j >= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
                    this._finished = false;
                    zArr[0] = false;
                    return;
                }
            }
        }
    }

    void ReleaseMFStream() {
        BinTree binTree = this._matchFinder;
        if (binTree == null || !this._needReleaseMFStream) {
            return;
        }
        binTree.ReleaseStream();
        this._needReleaseMFStream = false;
    }

    void SetOutStream(OutputStream outputStream) {
        this._rangeEncoder.SetStream(outputStream);
    }

    void ReleaseOutStream() {
        this._rangeEncoder.ReleaseStream();
    }

    void ReleaseStreams() {
        ReleaseMFStream();
        ReleaseOutStream();
    }

    void SetStreams(InputStream inputStream, OutputStream outputStream, long j, long j2) {
        this._inStream = inputStream;
        this._finished = false;
        Create();
        SetOutStream(outputStream);
        Init();
        FillDistancesPrices();
        FillAlignPrices();
        this._lenEncoder.SetTableSize((this._numFastBytes + 1) - 2);
        this._lenEncoder.UpdateTables(1 << this._posStateBits);
        this._repMatchLenEncoder.SetTableSize((this._numFastBytes + 1) - 2);
        this._repMatchLenEncoder.UpdateTables(1 << this._posStateBits);
        this.nowPos64 = 0L;
    }

    public void Code(InputStream inputStream, OutputStream outputStream, long j, long j2, ICodeProgress iCodeProgress) throws IOException {
        this._needReleaseMFStream = false;
        try {
            SetStreams(inputStream, outputStream, j, j2);
            while (true) {
                CodeOneBlock(this.processedInSize, this.processedOutSize, this.finished);
                if (this.finished[0]) {
                    return;
                }
                if (iCodeProgress != null) {
                    iCodeProgress.SetProgress(this.processedInSize[0], this.processedOutSize[0]);
                }
            }
        } finally {
            ReleaseStreams();
        }
    }

    public void WriteCoderProperties(OutputStream outputStream) throws IOException {
        this.properties[0] = (byte) ((((this._posStateBits * 5) + this._numLiteralPosStateBits) * 9) + this._numLiteralContextBits);
        int i = 0;
        while (i < 4) {
            int i2 = i + 1;
            this.properties[i2] = (byte) (this._dictionarySize >> (i * 8));
            i = i2;
        }
        outputStream.write(this.properties, 0, 5);
    }

    void FillDistancesPrices() {
        for (int i = 4; i < 128; i++) {
            int iGetPosSlot = GetPosSlot(i);
            int i2 = (iGetPosSlot >> 1) - 1;
            this.tempPrices[i] = BitTreeEncoder.ReverseGetPrice(this._posEncoders, (r4 - iGetPosSlot) - 1, i2, i - (((iGetPosSlot & 1) | 2) << i2));
        }
        for (int i3 = 0; i3 < 4; i3++) {
            BitTreeEncoder bitTreeEncoder = this._posSlotEncoder[i3];
            int i4 = i3 << 6;
            for (int i5 = 0; i5 < this._distTableSize; i5++) {
                this._posSlotPrices[i4 + i5] = bitTreeEncoder.GetPrice(i5);
            }
            for (int i6 = 14; i6 < this._distTableSize; i6++) {
                int[] iArr = this._posSlotPrices;
                int i7 = i4 + i6;
                iArr[i7] = iArr[i7] + ((((i6 >> 1) - 1) - 4) << 6);
            }
            int i8 = i3 * 128;
            int i9 = 0;
            while (i9 < 4) {
                this._distancesPrices[i8 + i9] = this._posSlotPrices[i4 + i9];
                i9++;
            }
            while (i9 < 128) {
                this._distancesPrices[i8 + i9] = this._posSlotPrices[GetPosSlot(i9) + i4] + this.tempPrices[i9];
                i9++;
            }
        }
        this._matchPriceCount = 0;
    }

    void FillAlignPrices() {
        for (int i = 0; i < 16; i++) {
            this._alignPrices[i] = this._posAlignEncoder.ReverseGetPrice(i);
        }
        this._alignPriceCount = 0;
    }

    public boolean SetDictionarySize(int i) {
        int i2 = 0;
        if (i < 1 || i > 536870912) {
            return false;
        }
        this._dictionarySize = i;
        while (i > (1 << i2)) {
            i2++;
        }
        this._distTableSize = i2 * 2;
        return true;
    }

    public boolean SetNumFastBytes(int i) {
        if (i < 5 || i > 273) {
            return false;
        }
        this._numFastBytes = i;
        return true;
    }

    public boolean SetMatchFinder(int i) {
        if (i < 0 || i > 2) {
            return false;
        }
        int i2 = this._matchFinderType;
        this._matchFinderType = i;
        if (this._matchFinder == null || i2 == i) {
            return true;
        }
        this._dictionarySizePrev = -1;
        this._matchFinder = null;
        return true;
    }

    public boolean SetLcLpPb(int i, int i2, int i3) {
        if (i2 < 0 || i2 > 4 || i < 0 || i > 8 || i3 < 0 || i3 > 4) {
            return false;
        }
        this._numLiteralPosStateBits = i2;
        this._numLiteralContextBits = i;
        this._posStateBits = i3;
        this._posStateMask = (1 << i3) - 1;
        return true;
    }

    public void SetEndMarkerMode(boolean z) {
        this._writeEndMark = z;
    }
}
