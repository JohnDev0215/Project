package org.tukaani.xz.lzma;

import org.tukaani.xz.ArrayCache;
import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.lz.LZEncoder;
import org.tukaani.xz.lz.Matches;
import org.tukaani.xz.rangecoder.RangeEncoder;

/* JADX INFO: loaded from: classes2.dex */
final class LZMAEncoderNormal extends LZMAEncoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int EXTRA_SIZE_AFTER = 4096;
    private static final int EXTRA_SIZE_BEFORE = 4096;
    private static final int OPTS = 4096;
    private Matches matches;
    private final State nextState;
    private int optCur;
    private int optEnd;
    private final Optimum[] opts;
    private final int[] repLens;

    static int getMemoryUsage(int dictSize, int extraSizeBefore, int mf) {
        return LZEncoder.getMemoryUsage(dictSize, Math.max(extraSizeBefore, 4096), 4096, LZMA2Options.NICE_LEN_MAX, mf) + 256;
    }

    LZMAEncoderNormal(RangeEncoder rc, int lc, int lp, int pb, int dictSize, int extraSizeBefore, int niceLen, int mf, int depthLimit, ArrayCache arrayCache) {
        super(rc, LZEncoder.getInstance(dictSize, Math.max(extraSizeBefore, 4096), 4096, niceLen, LZMA2Options.NICE_LEN_MAX, mf, depthLimit, arrayCache), lc, lp, pb, dictSize, niceLen);
        this.opts = new Optimum[4096];
        this.optCur = 0;
        this.optEnd = 0;
        this.repLens = new int[4];
        this.nextState = new State();
        for (int i = 0; i < 4096; i++) {
            this.opts[i] = new Optimum();
        }
    }

    @Override // org.tukaani.xz.lzma.LZMAEncoder, org.tukaani.xz.lzma.LZMACoder
    public void reset() {
        this.optCur = 0;
        this.optEnd = 0;
        super.reset();
    }

    private int convertOpts() {
        int i = this.optCur;
        this.optEnd = i;
        int optPrev = this.opts[i].optPrev;
        while (true) {
            Optimum opt = this.opts[this.optCur];
            if (opt.prev1IsLiteral) {
                this.opts[optPrev].optPrev = this.optCur;
                this.opts[optPrev].backPrev = -1;
                int optPrev2 = optPrev - 1;
                this.optCur = optPrev;
                if (!opt.hasPrev2) {
                    optPrev = optPrev2;
                } else {
                    this.opts[optPrev2].optPrev = optPrev2 + 1;
                    this.opts[optPrev2].backPrev = opt.backPrev2;
                    this.optCur = optPrev2;
                    optPrev = opt.optPrev2;
                }
            }
            int temp = this.opts[optPrev].optPrev;
            this.opts[optPrev].optPrev = this.optCur;
            this.optCur = optPrev;
            if (optPrev > 0) {
                optPrev = temp;
            } else {
                int i2 = this.opts[0].optPrev;
                this.optCur = i2;
                this.back = this.opts[i2].backPrev;
                return this.optCur;
            }
        }
    }

    @Override // org.tukaani.xz.lzma.LZMAEncoder
    int getNextSymbol() {
        int mainLen;
        int anyMatchPrice;
        int matchByte;
        int curByte;
        int avail;
        int avail2;
        int shortRepPrice;
        int i = this.optCur;
        if (i >= this.optEnd) {
            this.optCur = 0;
            this.optEnd = 0;
            this.back = -1;
            if (this.readAhead == -1) {
                this.matches = getMatches();
            }
            int avail3 = Math.min(this.lz.getAvail(), LZMA2Options.NICE_LEN_MAX);
            int i2 = 2;
            if (avail3 < 2) {
                return 1;
            }
            int repBest = 0;
            for (int rep = 0; rep < 4; rep++) {
                this.repLens[rep] = this.lz.getMatchLen(this.reps[rep], avail3);
                int[] iArr = this.repLens;
                if (iArr[rep] < 2) {
                    iArr[rep] = 0;
                } else if (iArr[rep] > iArr[repBest]) {
                    repBest = rep;
                }
            }
            if (this.repLens[repBest] >= this.niceLen) {
                this.back = repBest;
                skip(this.repLens[repBest] - 1);
                return this.repLens[repBest];
            }
            if (this.matches.count > 0) {
                int mainLen2 = this.matches.len[this.matches.count - 1];
                int mainDist = this.matches.dist[this.matches.count - 1];
                if (mainLen2 >= this.niceLen) {
                    this.back = mainDist + 4;
                    skip(mainLen2 - 1);
                    return mainLen2;
                }
                mainLen = mainLen2;
            } else {
                mainLen = 0;
            }
            int curByte2 = this.lz.getByte(0);
            int matchByte2 = this.lz.getByte(this.reps[0] + 1);
            if (mainLen < 2 && curByte2 != matchByte2 && this.repLens[repBest] < 2) {
                return 1;
            }
            int pos = this.lz.getPos();
            int posState = pos & this.posMask;
            int prevByte = this.lz.getByte(1);
            int literalPrice = this.literalEncoder.getPrice(curByte2, matchByte2, prevByte, pos, this.state);
            this.opts[1].set1(literalPrice, 0, -1);
            int anyMatchPrice2 = getAnyMatchPrice(this.state, posState);
            int anyRepPrice = getAnyRepPrice(anyMatchPrice2, this.state);
            if (matchByte2 == curByte2 && (shortRepPrice = getShortRepPrice(anyRepPrice, this.state, posState)) < this.opts[1].price) {
                this.opts[1].set1(shortRepPrice, 0, 0);
            }
            int iMax = Math.max(mainLen, this.repLens[repBest]);
            this.optEnd = iMax;
            if (iMax < 2) {
                this.back = this.opts[1].backPrev;
                return 1;
            }
            updatePrices();
            this.opts[0].state.set(this.state);
            System.arraycopy(this.reps, 0, this.opts[0].reps, 0, 4);
            for (int i3 = this.optEnd; i3 >= 2; i3--) {
                this.opts[i3].reset();
            }
            int rep2 = 0;
            while (rep2 < 4) {
                int repLen = this.repLens[rep2];
                if (repLen >= i2) {
                    int longRepPrice = getLongRepPrice(anyRepPrice, rep2, this.state, posState);
                    do {
                        int price = this.repLenEncoder.getPrice(repLen, posState) + longRepPrice;
                        if (price < this.opts[repLen].price) {
                            this.opts[repLen].set1(price, 0, rep2);
                        }
                        repLen--;
                    } while (repLen >= 2);
                }
                rep2++;
                i2 = 2;
            }
            int len = Math.max(this.repLens[0] + 1, 2);
            if (len <= mainLen) {
                int normalMatchPrice = getNormalMatchPrice(anyMatchPrice2, this.state);
                int i4 = 0;
                while (len > this.matches.len[i4]) {
                    i4++;
                }
                while (true) {
                    int dist = this.matches.dist[i4];
                    int price2 = getMatchAndLenPrice(normalMatchPrice, dist, len, posState);
                    if (price2 >= this.opts[len].price) {
                        anyMatchPrice = anyMatchPrice2;
                        avail = avail3;
                        avail2 = 0;
                    } else {
                        anyMatchPrice = anyMatchPrice2;
                        avail = avail3;
                        avail2 = 0;
                        this.opts[len].set1(price2, 0, dist + 4);
                    }
                    if (len == this.matches.len[i4] && (i4 = i4 + 1) == this.matches.count) {
                        break;
                    }
                    len++;
                    anyMatchPrice2 = anyMatchPrice;
                    avail3 = avail;
                }
            } else {
                anyMatchPrice = anyMatchPrice2;
            }
            int avail4 = Math.min(this.lz.getAvail(), 4095);
            while (true) {
                int i5 = this.optCur + 1;
                this.optCur = i5;
                if (i5 >= this.optEnd) {
                    break;
                }
                Matches matches = getMatches();
                this.matches = matches;
                if (matches.count > 0 && this.matches.len[this.matches.count - 1] >= this.niceLen) {
                    break;
                }
                int avail5 = avail4 - 1;
                int pos2 = pos + 1;
                int posState2 = pos2 & this.posMask;
                updateOptStateAndReps();
                int anyMatchPrice3 = this.opts[this.optCur].price + getAnyMatchPrice(this.opts[this.optCur].state, posState2);
                int anyRepPrice2 = getAnyRepPrice(anyMatchPrice3, this.opts[this.optCur].state);
                calc1BytePrices(pos2, posState2, avail5, anyRepPrice2);
                if (avail5 >= 2) {
                    int startLen = calcLongRepPrices(pos2, posState2, avail5, anyRepPrice2);
                    if (this.matches.count > 0) {
                        matchByte = matchByte2;
                        curByte = curByte2;
                        calcNormalMatchPrices(pos2, posState2, avail5, anyMatchPrice3, startLen);
                    } else {
                        matchByte = matchByte2;
                        curByte = curByte2;
                    }
                } else {
                    matchByte = matchByte2;
                    curByte = curByte2;
                }
                avail4 = avail5;
                pos = pos2;
                matchByte2 = matchByte;
                curByte2 = curByte;
            }
            return convertOpts();
        }
        int i6 = this.opts[i].optPrev;
        int i7 = this.optCur;
        int len2 = i6 - i7;
        int i8 = this.opts[i7].optPrev;
        this.optCur = i8;
        this.back = this.opts[i8].backPrev;
        return len2;
    }

    private void updateOptStateAndReps() {
        int back;
        int optPrev = this.opts[this.optCur].optPrev;
        if (this.opts[this.optCur].prev1IsLiteral) {
            optPrev--;
            if (this.opts[this.optCur].hasPrev2) {
                State state = this.opts[this.optCur].state;
                Optimum[] optimumArr = this.opts;
                state.set(optimumArr[optimumArr[this.optCur].optPrev2].state);
                if (this.opts[this.optCur].backPrev2 < 4) {
                    this.opts[this.optCur].state.updateLongRep();
                } else {
                    this.opts[this.optCur].state.updateMatch();
                }
            } else {
                this.opts[this.optCur].state.set(this.opts[optPrev].state);
            }
            this.opts[this.optCur].state.updateLiteral();
        } else {
            this.opts[this.optCur].state.set(this.opts[optPrev].state);
        }
        int i = this.optCur;
        if (optPrev == i - 1) {
            if (this.opts[i].backPrev == 0) {
                this.opts[this.optCur].state.updateShortRep();
            } else {
                this.opts[this.optCur].state.updateLiteral();
            }
            System.arraycopy(this.opts[optPrev].reps, 0, this.opts[this.optCur].reps, 0, 4);
            return;
        }
        if (this.opts[i].prev1IsLiteral && this.opts[this.optCur].hasPrev2) {
            optPrev = this.opts[this.optCur].optPrev2;
            back = this.opts[this.optCur].backPrev2;
            this.opts[this.optCur].state.updateLongRep();
        } else {
            back = this.opts[this.optCur].backPrev;
            if (back < 4) {
                this.opts[this.optCur].state.updateLongRep();
            } else {
                this.opts[this.optCur].state.updateMatch();
            }
        }
        if (back < 4) {
            this.opts[this.optCur].reps[0] = this.opts[optPrev].reps[back];
            int rep = 1;
            while (rep <= back) {
                this.opts[this.optCur].reps[rep] = this.opts[optPrev].reps[rep - 1];
                rep++;
            }
            while (rep < 4) {
                this.opts[this.optCur].reps[rep] = this.opts[optPrev].reps[rep];
                rep++;
            }
            return;
        }
        this.opts[this.optCur].reps[0] = back - 4;
        System.arraycopy(this.opts[optPrev].reps, 0, this.opts[this.optCur].reps, 1, 3);
    }

    private void calc1BytePrices(int pos, int posState, int avail, int anyRepPrice) {
        int shortRepPrice;
        boolean nextIsByte = $assertionsDisabled;
        int curByte = this.lz.getByte(0);
        int matchByte = this.lz.getByte(this.opts[this.optCur].reps[0] + 1);
        int literalPrice = this.opts[this.optCur].price + this.literalEncoder.getPrice(curByte, matchByte, this.lz.getByte(1), pos, this.opts[this.optCur].state);
        if (literalPrice < this.opts[this.optCur + 1].price) {
            Optimum[] optimumArr = this.opts;
            int i = this.optCur;
            optimumArr[i + 1].set1(literalPrice, i, -1);
            nextIsByte = true;
        }
        if (matchByte == curByte) {
            int i2 = this.opts[this.optCur + 1].optPrev;
            int i3 = this.optCur;
            if ((i2 == i3 || this.opts[i3 + 1].backPrev != 0) && (shortRepPrice = getShortRepPrice(anyRepPrice, this.opts[this.optCur].state, posState)) <= this.opts[this.optCur + 1].price) {
                Optimum[] optimumArr2 = this.opts;
                int i4 = this.optCur;
                optimumArr2[i4 + 1].set1(shortRepPrice, i4, 0);
                nextIsByte = true;
            }
        }
        if (!nextIsByte && matchByte != curByte && avail > 2) {
            int lenLimit = Math.min(this.niceLen, avail - 1);
            int len = this.lz.getMatchLen(1, this.opts[this.optCur].reps[0], lenLimit);
            if (len >= 2) {
                this.nextState.set(this.opts[this.optCur].state);
                this.nextState.updateLiteral();
                int nextPosState = (pos + 1) & this.posMask;
                int price = getLongRepAndLenPrice(0, len, this.nextState, nextPosState) + literalPrice;
                int i5 = this.optCur + 1 + len;
                while (true) {
                    int i6 = this.optEnd;
                    if (i6 >= i5) {
                        break;
                    }
                    Optimum[] optimumArr3 = this.opts;
                    int i7 = i6 + 1;
                    this.optEnd = i7;
                    optimumArr3[i7].reset();
                }
                if (price < this.opts[i5].price) {
                    this.opts[i5].set2(price, this.optCur, 0);
                }
            }
        }
    }

    private int calcLongRepPrices(int pos, int posState, int avail, int anyRepPrice) {
        int i;
        int startLen = 2;
        int lenLimit = Math.min(avail, this.niceLen);
        for (int rep = 0; rep < 4; rep++) {
            int len = this.lz.getMatchLen(this.opts[this.optCur].reps[rep], lenLimit);
            if (len >= 2) {
                while (true) {
                    int i2 = this.optEnd;
                    i = this.optCur;
                    if (i2 >= i + len) {
                        break;
                    }
                    Optimum[] optimumArr = this.opts;
                    int i3 = i2 + 1;
                    this.optEnd = i3;
                    optimumArr[i3].reset();
                }
                int longRepPrice = getLongRepPrice(anyRepPrice, rep, this.opts[i].state, posState);
                for (int i4 = len; i4 >= 2; i4--) {
                    int price = this.repLenEncoder.getPrice(i4, posState) + longRepPrice;
                    if (price < this.opts[this.optCur + i4].price) {
                        Optimum[] optimumArr2 = this.opts;
                        int i5 = this.optCur;
                        optimumArr2[i5 + i4].set1(price, i5, rep);
                    }
                }
                if (rep == 0) {
                    startLen = len + 1;
                }
                int len2Limit = Math.min(this.niceLen, (avail - len) - 1);
                int len2 = this.lz.getMatchLen(len + 1, this.opts[this.optCur].reps[rep], len2Limit);
                if (len2 >= 2) {
                    int price2 = this.repLenEncoder.getPrice(len, posState) + longRepPrice;
                    this.nextState.set(this.opts[this.optCur].state);
                    this.nextState.updateLongRep();
                    int curByte = this.lz.getByte(len, 0);
                    int matchByte = this.lz.getByte(0);
                    int prevByte = this.lz.getByte(len, 1);
                    int price3 = price2 + this.literalEncoder.getPrice(curByte, matchByte, prevByte, pos + len, this.nextState);
                    this.nextState.updateLiteral();
                    int nextPosState = this.posMask & (pos + len + 1);
                    int price4 = price3 + getLongRepAndLenPrice(0, len2, this.nextState, nextPosState);
                    int price5 = this.optCur;
                    int i6 = price5 + len + 1 + len2;
                    while (true) {
                        int i7 = this.optEnd;
                        if (i7 >= i6) {
                            break;
                        }
                        Optimum[] optimumArr3 = this.opts;
                        int i8 = i7 + 1;
                        this.optEnd = i8;
                        optimumArr3[i8].reset();
                    }
                    if (price4 < this.opts[i6].price) {
                        int i9 = rep;
                        this.opts[i6].set3(price4, this.optCur, i9, len, 0);
                    }
                }
            }
        }
        return startLen;
    }

    private void calcNormalMatchPrices(int pos, int posState, int avail, int anyMatchPrice, int startLen) {
        int i;
        int i2;
        int i3 = avail;
        int i4 = 1;
        int nextPosState = 0;
        if (this.matches.len[this.matches.count - 1] > i3) {
            this.matches.count = 0;
            while (this.matches.len[this.matches.count] < i3) {
                this.matches.count++;
            }
            int[] iArr = this.matches.len;
            Matches matches = this.matches;
            int i5 = matches.count;
            matches.count = i5 + 1;
            iArr[i5] = i3;
        }
        if (this.matches.len[this.matches.count - 1] >= startLen) {
            while (this.optEnd < this.optCur + this.matches.len[this.matches.count - 1]) {
                Optimum[] optimumArr = this.opts;
                int i6 = this.optEnd + 1;
                this.optEnd = i6;
                optimumArr[i6].reset();
            }
            int normalMatchPrice = getNormalMatchPrice(anyMatchPrice, this.opts[this.optCur].state);
            int match = 0;
            while (startLen > this.matches.len[match]) {
                match++;
            }
            int len = startLen;
            while (true) {
                int dist = this.matches.dist[match];
                int matchAndLenPrice = getMatchAndLenPrice(normalMatchPrice, dist, len, posState);
                if (matchAndLenPrice < this.opts[this.optCur + len].price) {
                    Optimum[] optimumArr2 = this.opts;
                    int i7 = this.optCur;
                    optimumArr2[i7 + len].set1(matchAndLenPrice, i7, dist + 4);
                }
                if (len != this.matches.len[match]) {
                    i = nextPosState;
                    i2 = i4;
                } else {
                    int len2Limit = Math.min(this.niceLen, (i3 - len) - i4);
                    int len2 = this.lz.getMatchLen(len + 1, dist, len2Limit);
                    if (len2 >= 2) {
                        this.nextState.set(this.opts[this.optCur].state);
                        this.nextState.updateMatch();
                        int curByte = this.lz.getByte(len, nextPosState);
                        int matchByte = this.lz.getByte(nextPosState);
                        int prevByte = this.lz.getByte(len, 1);
                        int price = this.literalEncoder.getPrice(curByte, matchByte, prevByte, pos + len, this.nextState) + matchAndLenPrice;
                        this.nextState.updateLiteral();
                        i2 = 1;
                        int nextPosState2 = this.posMask & (pos + len + 1);
                        int price2 = getLongRepAndLenPrice(0, len2, this.nextState, nextPosState2) + price;
                        int price3 = this.optCur;
                        int i8 = price3 + len + 1 + len2;
                        while (true) {
                            int i9 = this.optEnd;
                            if (i9 >= i8) {
                                break;
                            }
                            Optimum[] optimumArr3 = this.opts;
                            int i10 = i9 + 1;
                            this.optEnd = i10;
                            optimumArr3[i10].reset();
                        }
                        if (price2 < this.opts[i8].price) {
                            i = 0;
                            this.opts[i8].set3(price2, this.optCur, dist + 4, len, 0);
                        } else {
                            i = 0;
                        }
                    } else {
                        i = nextPosState;
                        i2 = i4;
                    }
                    match++;
                    if (match == this.matches.count) {
                        return;
                    }
                }
                len++;
                i3 = avail;
                i4 = i2;
                nextPosState = i;
            }
        }
    }
}
