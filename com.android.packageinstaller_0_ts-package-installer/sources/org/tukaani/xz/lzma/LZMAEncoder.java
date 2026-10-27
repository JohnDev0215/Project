package org.tukaani.xz.lzma;

import java.io.IOException;
import java.lang.reflect.Array;
import org.tukaani.xz.ArrayCache;
import org.tukaani.xz.lz.LZEncoder;
import org.tukaani.xz.lz.Matches;
import org.tukaani.xz.lzma.LZMACoder;
import org.tukaani.xz.rangecoder.RangeEncoder;

/* JADX INFO: loaded from: classes2.dex */
public abstract class LZMAEncoder extends LZMACoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ALIGN_PRICE_UPDATE_INTERVAL = 16;
    private static final int DIST_PRICE_UPDATE_INTERVAL = 128;
    private static final int LZMA2_COMPRESSED_LIMIT = 65510;
    private static final int LZMA2_UNCOMPRESSED_LIMIT = 2096879;
    public static final int MODE_FAST = 1;
    public static final int MODE_NORMAL = 2;
    private int alignPriceCount;
    private final int[] alignPrices;
    int back;
    private int distPriceCount;
    private final int[][] distSlotPrices;
    private final int distSlotPricesSize;
    private final int[][] fullDistPrices;
    final LiteralEncoder literalEncoder;
    final LZEncoder lz;
    final LengthEncoder matchLenEncoder;
    final int niceLen;
    private final RangeEncoder rc;
    int readAhead;
    final LengthEncoder repLenEncoder;
    private int uncompressedSize;

    abstract int getNextSymbol();

    public static int getMemoryUsage(int mode, int dictSize, int extraSizeBefore, int mf) {
        if (mode == 1) {
            int m = 80 + LZMAEncoderFast.getMemoryUsage(dictSize, extraSizeBefore, mf);
            return m;
        }
        if (mode == 2) {
            int m2 = 80 + LZMAEncoderNormal.getMemoryUsage(dictSize, extraSizeBefore, mf);
            return m2;
        }
        throw new IllegalArgumentException();
    }

    public static LZMAEncoder getInstance(RangeEncoder rc, int lc, int lp, int pb, int mode, int dictSize, int extraSizeBefore, int niceLen, int mf, int depthLimit, ArrayCache arrayCache) {
        if (mode != 1) {
            if (mode == 2) {
                return new LZMAEncoderNormal(rc, lc, lp, pb, dictSize, extraSizeBefore, niceLen, mf, depthLimit, arrayCache);
            }
            throw new IllegalArgumentException();
        }
        return new LZMAEncoderFast(rc, lc, lp, pb, dictSize, extraSizeBefore, niceLen, mf, depthLimit, arrayCache);
    }

    public void putArraysToCache(ArrayCache arrayCache) {
        this.lz.putArraysToCache(arrayCache);
    }

    public static int getDistSlot(int dist) {
        if (dist <= 4 && dist >= 0) {
            return dist;
        }
        int n = dist;
        int i = 31;
        if (((-65536) & n) == 0) {
            n <<= 16;
            i = 15;
        }
        if (((-16777216) & n) == 0) {
            n <<= 8;
            i -= 8;
        }
        if (((-268435456) & n) == 0) {
            n <<= 4;
            i -= 4;
        }
        if (((-1073741824) & n) == 0) {
            n <<= 2;
            i -= 2;
        }
        if ((Integer.MIN_VALUE & n) == 0) {
            i--;
        }
        return (i << 1) + ((dist >>> (i - 1)) & 1);
    }

    LZMAEncoder(RangeEncoder rc, LZEncoder lz, int lc, int lp, int pb, int dictSize, int niceLen) {
        super(pb);
        this.distPriceCount = 0;
        this.alignPriceCount = 0;
        this.fullDistPrices = (int[][]) Array.newInstance((Class<?>) int.class, 4, 128);
        this.alignPrices = new int[16];
        this.back = 0;
        this.readAhead = -1;
        this.uncompressedSize = 0;
        this.rc = rc;
        this.lz = lz;
        this.niceLen = niceLen;
        this.literalEncoder = new LiteralEncoder(lc, lp);
        this.matchLenEncoder = new LengthEncoder(pb, niceLen);
        this.repLenEncoder = new LengthEncoder(pb, niceLen);
        int distSlot = getDistSlot(dictSize - 1) + 1;
        this.distSlotPricesSize = distSlot;
        this.distSlotPrices = (int[][]) Array.newInstance((Class<?>) int.class, 4, distSlot);
        reset();
    }

    public LZEncoder getLZEncoder() {
        return this.lz;
    }

    @Override // org.tukaani.xz.lzma.LZMACoder
    public void reset() {
        super.reset();
        this.literalEncoder.reset();
        this.matchLenEncoder.reset();
        this.repLenEncoder.reset();
        this.distPriceCount = 0;
        this.alignPriceCount = 0;
        this.uncompressedSize += this.readAhead + 1;
        this.readAhead = -1;
    }

    public int getUncompressedSize() {
        return this.uncompressedSize;
    }

    public void resetUncompressedSize() {
        this.uncompressedSize = 0;
    }

    public void encodeForLZMA1() throws IOException {
        if (!this.lz.isStarted() && !encodeInit()) {
            return;
        }
        while (encodeSymbol()) {
        }
    }

    public void encodeLZMA1EndMarker() throws IOException {
        int posState = (this.lz.getPos() - this.readAhead) & this.posMask;
        this.rc.encodeBit(this.isMatch[this.state.get()], posState, 1);
        this.rc.encodeBit(this.isRep, this.state.get(), 0);
        encodeMatch(-1, 2, posState);
    }

    public boolean encodeForLZMA2() {
        try {
            if (!this.lz.isStarted() && !encodeInit()) {
                return $assertionsDisabled;
            }
            while (this.uncompressedSize <= LZMA2_UNCOMPRESSED_LIMIT && this.rc.getPendingSize() <= LZMA2_COMPRESSED_LIMIT) {
                if (!encodeSymbol()) {
                    return $assertionsDisabled;
                }
            }
            return true;
        } catch (IOException e) {
            throw new Error();
        }
    }

    private boolean encodeInit() throws IOException {
        if (!this.lz.hasEnoughData(0)) {
            return $assertionsDisabled;
        }
        skip(1);
        this.rc.encodeBit(this.isMatch[this.state.get()], 0, 0);
        this.literalEncoder.encodeInit();
        this.readAhead--;
        this.uncompressedSize++;
        return true;
    }

    private boolean encodeSymbol() throws IOException {
        if (!this.lz.hasEnoughData(this.readAhead + 1)) {
            return $assertionsDisabled;
        }
        int len = getNextSymbol();
        int posState = (this.lz.getPos() - this.readAhead) & this.posMask;
        if (this.back == -1) {
            this.rc.encodeBit(this.isMatch[this.state.get()], posState, 0);
            this.literalEncoder.encode();
        } else {
            this.rc.encodeBit(this.isMatch[this.state.get()], posState, 1);
            if (this.back < 4) {
                this.rc.encodeBit(this.isRep, this.state.get(), 1);
                encodeRepMatch(this.back, len, posState);
            } else {
                this.rc.encodeBit(this.isRep, this.state.get(), 0);
                encodeMatch(this.back - 4, len, posState);
            }
        }
        this.readAhead -= len;
        this.uncompressedSize += len;
        return true;
    }

    private void encodeMatch(int dist, int len, int posState) throws IOException {
        this.state.updateMatch();
        this.matchLenEncoder.encode(len, posState);
        int distSlot = getDistSlot(dist);
        this.rc.encodeBitTree(this.distSlots[getDistState(len)], distSlot);
        if (distSlot >= 4) {
            int footerBits = (distSlot >>> 1) - 1;
            int base = ((distSlot & 1) | 2) << footerBits;
            int distReduced = dist - base;
            if (distSlot < 14) {
                this.rc.encodeReverseBitTree(this.distSpecial[distSlot - 4], distReduced);
            } else {
                this.rc.encodeDirectBits(distReduced >>> 4, footerBits - 4);
                this.rc.encodeReverseBitTree(this.distAlign, distReduced & 15);
                this.alignPriceCount--;
            }
        }
        this.reps[3] = this.reps[2];
        this.reps[2] = this.reps[1];
        this.reps[1] = this.reps[0];
        this.reps[0] = dist;
        this.distPriceCount--;
    }

    private void encodeRepMatch(int rep, int len, int posState) throws IOException {
        if (rep == 0) {
            this.rc.encodeBit(this.isRep0, this.state.get(), 0);
            this.rc.encodeBit(this.isRep0Long[this.state.get()], posState, len != 1 ? 1 : 0);
        } else {
            int dist = this.reps[rep];
            this.rc.encodeBit(this.isRep0, this.state.get(), 1);
            if (rep == 1) {
                this.rc.encodeBit(this.isRep1, this.state.get(), 0);
            } else {
                this.rc.encodeBit(this.isRep1, this.state.get(), 1);
                this.rc.encodeBit(this.isRep2, this.state.get(), rep - 2);
                if (rep == 3) {
                    this.reps[3] = this.reps[2];
                }
                this.reps[2] = this.reps[1];
            }
            this.reps[1] = this.reps[0];
            this.reps[0] = dist;
        }
        if (len == 1) {
            this.state.updateShortRep();
        } else {
            this.repLenEncoder.encode(len, posState);
            this.state.updateLongRep();
        }
    }

    Matches getMatches() {
        this.readAhead++;
        Matches matches = this.lz.getMatches();
        return matches;
    }

    void skip(int len) {
        this.readAhead += len;
        this.lz.skip(len);
    }

    int getAnyMatchPrice(State state, int posState) {
        return RangeEncoder.getBitPrice(this.isMatch[state.get()][posState], 1);
    }

    int getNormalMatchPrice(int anyMatchPrice, State state) {
        return RangeEncoder.getBitPrice(this.isRep[state.get()], 0) + anyMatchPrice;
    }

    int getAnyRepPrice(int anyMatchPrice, State state) {
        return RangeEncoder.getBitPrice(this.isRep[state.get()], 1) + anyMatchPrice;
    }

    int getShortRepPrice(int anyRepPrice, State state, int posState) {
        return RangeEncoder.getBitPrice(this.isRep0[state.get()], 0) + anyRepPrice + RangeEncoder.getBitPrice(this.isRep0Long[state.get()][posState], 0);
    }

    int getLongRepPrice(int anyRepPrice, int rep, State state, int posState) {
        if (rep == 0) {
            return anyRepPrice + RangeEncoder.getBitPrice(this.isRep0[state.get()], 0) + RangeEncoder.getBitPrice(this.isRep0Long[state.get()][posState], 1);
        }
        int price = anyRepPrice + RangeEncoder.getBitPrice(this.isRep0[state.get()], 1);
        if (rep == 1) {
            return price + RangeEncoder.getBitPrice(this.isRep1[state.get()], 0);
        }
        return price + RangeEncoder.getBitPrice(this.isRep1[state.get()], 1) + RangeEncoder.getBitPrice(this.isRep2[state.get()], rep - 2);
    }

    int getLongRepAndLenPrice(int rep, int len, State state, int posState) {
        int anyMatchPrice = getAnyMatchPrice(state, posState);
        int anyRepPrice = getAnyRepPrice(anyMatchPrice, state);
        int longRepPrice = getLongRepPrice(anyRepPrice, rep, state, posState);
        return this.repLenEncoder.getPrice(len, posState) + longRepPrice;
    }

    int getMatchAndLenPrice(int normalMatchPrice, int dist, int len, int posState) {
        int price = this.matchLenEncoder.getPrice(len, posState) + normalMatchPrice;
        int distState = getDistState(len);
        if (dist < 128) {
            return price + this.fullDistPrices[distState][dist];
        }
        int distSlot = getDistSlot(dist);
        return price + this.distSlotPrices[distState][distSlot] + this.alignPrices[dist & 15];
    }

    private void updateDistPrices() {
        this.distPriceCount = 128;
        for (int distState = 0; distState < 4; distState++) {
            for (int distSlot = 0; distSlot < this.distSlotPricesSize; distSlot++) {
                this.distSlotPrices[distState][distSlot] = RangeEncoder.getBitTreePrice(this.distSlots[distState], distSlot);
            }
            for (int distSlot2 = 14; distSlot2 < this.distSlotPricesSize; distSlot2++) {
                int count = ((distSlot2 >>> 1) - 1) - 4;
                int[] iArr = this.distSlotPrices[distState];
                iArr[distSlot2] = iArr[distSlot2] + RangeEncoder.getDirectBitsPrice(count);
            }
            for (int dist = 0; dist < 4; dist++) {
                this.fullDistPrices[distState][dist] = this.distSlotPrices[distState][dist];
            }
        }
        int dist2 = 4;
        for (int distSlot3 = 4; distSlot3 < 14; distSlot3++) {
            int footerBits = (distSlot3 >>> 1) - 1;
            int base = ((distSlot3 & 1) | 2) << footerBits;
            int limit = this.distSpecial[distSlot3 - 4].length;
            for (int i = 0; i < limit; i++) {
                int distReduced = dist2 - base;
                int price = RangeEncoder.getReverseBitTreePrice(this.distSpecial[distSlot3 - 4], distReduced);
                for (int distState2 = 0; distState2 < 4; distState2++) {
                    this.fullDistPrices[distState2][dist2] = this.distSlotPrices[distState2][distSlot3] + price;
                }
                dist2++;
            }
        }
    }

    private void updateAlignPrices() {
        this.alignPriceCount = 16;
        for (int i = 0; i < 16; i++) {
            this.alignPrices[i] = RangeEncoder.getReverseBitTreePrice(this.distAlign, i);
        }
    }

    void updatePrices() {
        if (this.distPriceCount <= 0) {
            updateDistPrices();
        }
        if (this.alignPriceCount <= 0) {
            updateAlignPrices();
        }
        this.matchLenEncoder.updatePrices();
        this.repLenEncoder.updatePrices();
    }

    class LiteralEncoder extends LZMACoder.LiteralCoder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final LiteralSubencoder[] subencoders;

        LiteralEncoder(int lc, int lp) {
            super(lc, lp);
            this.subencoders = new LiteralSubencoder[1 << (lc + lp)];
            int i = 0;
            while (true) {
                LiteralSubencoder[] literalSubencoderArr = this.subencoders;
                if (i < literalSubencoderArr.length) {
                    literalSubencoderArr[i] = new LiteralSubencoder();
                    i++;
                } else {
                    return;
                }
            }
        }

        void reset() {
            int i = 0;
            while (true) {
                LiteralSubencoder[] literalSubencoderArr = this.subencoders;
                if (i < literalSubencoderArr.length) {
                    literalSubencoderArr[i].reset();
                    i++;
                } else {
                    return;
                }
            }
        }

        void encodeInit() throws IOException {
            this.subencoders[0].encode();
        }

        void encode() throws IOException {
            int i = getSubcoderIndex(LZMAEncoder.this.lz.getByte(LZMAEncoder.this.readAhead + 1), LZMAEncoder.this.lz.getPos() - LZMAEncoder.this.readAhead);
            this.subencoders[i].encode();
        }

        int getPrice(int curByte, int matchByte, int prevByte, int pos, State state) {
            int matchedPrice;
            int price = RangeEncoder.getBitPrice(LZMAEncoder.this.isMatch[state.get()][LZMAEncoder.this.posMask & pos], 0);
            int i = getSubcoderIndex(prevByte, pos);
            if (state.isLiteral()) {
                matchedPrice = this.subencoders[i].getNormalPrice(curByte);
            } else {
                matchedPrice = this.subencoders[i].getMatchedPrice(curByte, matchByte);
            }
            return price + matchedPrice;
        }

        private class LiteralSubencoder extends LZMACoder.LiteralCoder.LiteralSubcoder {
            private LiteralSubencoder() {
                super();
            }

            void encode() throws IOException {
                int symbol = LZMAEncoder.this.lz.getByte(LZMAEncoder.this.readAhead) | 256;
                if (LZMAEncoder.this.state.isLiteral()) {
                    do {
                        int subencoderIndex = symbol >>> 8;
                        int bit = (symbol >>> 7) & 1;
                        LZMAEncoder.this.rc.encodeBit(this.probs, subencoderIndex, bit);
                        symbol <<= 1;
                    } while (symbol < 65536);
                } else {
                    int matchByte = LZMAEncoder.this.lz.getByte(LZMAEncoder.this.reps[0] + 1 + LZMAEncoder.this.readAhead);
                    int offset = 256;
                    do {
                        matchByte <<= 1;
                        int matchBit = matchByte & offset;
                        int subencoderIndex2 = offset + matchBit + (symbol >>> 8);
                        int bit2 = (symbol >>> 7) & 1;
                        LZMAEncoder.this.rc.encodeBit(this.probs, subencoderIndex2, bit2);
                        symbol <<= 1;
                        offset &= ~(matchByte ^ symbol);
                    } while (symbol < 65536);
                }
                LZMAEncoder.this.state.updateLiteral();
            }

            int getNormalPrice(int symbol) {
                int price = 0;
                int symbol2 = symbol | 256;
                do {
                    int subencoderIndex = symbol2 >>> 8;
                    int bit = (symbol2 >>> 7) & 1;
                    price += RangeEncoder.getBitPrice(this.probs[subencoderIndex], bit);
                    symbol2 <<= 1;
                } while (symbol2 < 65536);
                return price;
            }

            int getMatchedPrice(int symbol, int matchByte) {
                int price = 0;
                int offset = 256;
                int symbol2 = symbol | 256;
                do {
                    matchByte <<= 1;
                    int matchBit = matchByte & offset;
                    int subencoderIndex = offset + matchBit + (symbol2 >>> 8);
                    int bit = (symbol2 >>> 7) & 1;
                    price += RangeEncoder.getBitPrice(this.probs[subencoderIndex], bit);
                    symbol2 <<= 1;
                    offset &= ~(matchByte ^ symbol2);
                } while (symbol2 < 65536);
                return price;
            }
        }
    }

    class LengthEncoder extends LZMACoder.LengthCoder {
        private static final int PRICE_UPDATE_INTERVAL = 32;
        private final int[] counters;
        private final int[][] prices;

        LengthEncoder(int pb, int niceLen) {
            super();
            int posStates = 1 << pb;
            this.counters = new int[posStates];
            int lenSymbols = Math.max((niceLen - 2) + 1, 16);
            this.prices = (int[][]) Array.newInstance((Class<?>) int.class, posStates, lenSymbols);
        }

        @Override // org.tukaani.xz.lzma.LZMACoder.LengthCoder
        void reset() {
            super.reset();
            int i = 0;
            while (true) {
                int[] iArr = this.counters;
                if (i < iArr.length) {
                    iArr[i] = 0;
                    i++;
                } else {
                    return;
                }
            }
        }

        void encode(int len, int posState) throws IOException {
            int len2 = len - 2;
            if (len2 < 8) {
                LZMAEncoder.this.rc.encodeBit(this.choice, 0, 0);
                LZMAEncoder.this.rc.encodeBitTree(this.low[posState], len2);
            } else {
                LZMAEncoder.this.rc.encodeBit(this.choice, 0, 1);
                int len3 = len2 - 8;
                if (len3 < 8) {
                    LZMAEncoder.this.rc.encodeBit(this.choice, 1, 0);
                    LZMAEncoder.this.rc.encodeBitTree(this.mid[posState], len3);
                } else {
                    LZMAEncoder.this.rc.encodeBit(this.choice, 1, 1);
                    LZMAEncoder.this.rc.encodeBitTree(this.high, len3 - 8);
                }
            }
            int[] iArr = this.counters;
            iArr[posState] = iArr[posState] - 1;
        }

        int getPrice(int len, int posState) {
            return this.prices[posState][len - 2];
        }

        void updatePrices() {
            int posState = 0;
            while (true) {
                int[] iArr = this.counters;
                if (posState < iArr.length) {
                    if (iArr[posState] <= 0) {
                        iArr[posState] = 32;
                        updatePrices(posState);
                    }
                    posState++;
                } else {
                    return;
                }
            }
        }

        private void updatePrices(int posState) {
            int choice0Price = RangeEncoder.getBitPrice(this.choice[0], 0);
            int i = 0;
            while (i < 8) {
                this.prices[posState][i] = RangeEncoder.getBitTreePrice(this.low[posState], i) + choice0Price;
                i++;
            }
            int choice0Price2 = RangeEncoder.getBitPrice(this.choice[0], 1);
            int choice1Price = RangeEncoder.getBitPrice(this.choice[1], 0);
            while (i < 16) {
                this.prices[posState][i] = choice0Price2 + choice1Price + RangeEncoder.getBitTreePrice(this.mid[posState], i - 8);
                i++;
            }
            int choice1Price2 = RangeEncoder.getBitPrice(this.choice[1], 1);
            while (true) {
                int[][] iArr = this.prices;
                if (i < iArr[posState].length) {
                    iArr[posState][i] = choice0Price2 + choice1Price2 + RangeEncoder.getBitTreePrice(this.high, (i - 8) - 8);
                    i++;
                } else {
                    return;
                }
            }
        }
    }
}
