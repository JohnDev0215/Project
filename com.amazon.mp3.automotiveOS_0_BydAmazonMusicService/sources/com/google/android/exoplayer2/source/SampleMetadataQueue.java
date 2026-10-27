package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

/* JADX INFO: loaded from: classes6.dex */
final class SampleMetadataQueue {
    private int absoluteStartIndex;
    private int length;
    private int readPosition;
    private int relativeStartIndex;
    private Format upstreamFormat;
    private int upstreamSourceId;
    private int capacity = 1000;
    private int[] sourceIds = new int[1000];
    private long[] offsets = new long[1000];
    private long[] timesUs = new long[1000];
    private int[] flags = new int[1000];
    private int[] sizes = new int[1000];
    private TrackOutput.CryptoData[] cryptoDatas = new TrackOutput.CryptoData[1000];
    private Format[] formats = new Format[1000];
    private long largestDiscardedTimestampUs = Long.MIN_VALUE;
    private long largestQueuedTimestampUs = Long.MIN_VALUE;
    private boolean upstreamFormatRequired = true;
    private boolean upstreamKeyframeRequired = true;

    public static final class SampleExtrasHolder {
        public TrackOutput.CryptoData cryptoData;
        public long offset;
        public int size;
    }

    public void reset(boolean z) {
        this.length = 0;
        this.absoluteStartIndex = 0;
        this.relativeStartIndex = 0;
        this.readPosition = 0;
        this.upstreamKeyframeRequired = true;
        this.largestDiscardedTimestampUs = Long.MIN_VALUE;
        this.largestQueuedTimestampUs = Long.MIN_VALUE;
        if (z) {
            this.upstreamFormat = null;
            this.upstreamFormatRequired = true;
        }
    }

    public int getWriteIndex() {
        return this.absoluteStartIndex + this.length;
    }

    public long discardUpstreamSamples(int i) {
        int writeIndex = getWriteIndex() - i;
        Assertions.checkArgument(writeIndex >= 0 && writeIndex <= this.length - this.readPosition);
        int i2 = this.length - writeIndex;
        this.length = i2;
        this.largestQueuedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(i2));
        int i3 = this.length;
        if (i3 == 0) {
            return 0L;
        }
        int relativeIndex = getRelativeIndex(i3 - 1);
        return this.offsets[relativeIndex] + ((long) this.sizes[relativeIndex]);
    }

    public void sourceId(int i) {
        this.upstreamSourceId = i;
    }

    public int getReadIndex() {
        return this.absoluteStartIndex + this.readPosition;
    }

    public int peekSourceId() {
        return hasNextSample() ? this.sourceIds[getRelativeIndex(this.readPosition)] : this.upstreamSourceId;
    }

    public synchronized boolean hasNextSample() {
        return this.readPosition != this.length;
    }

    public synchronized Format getUpstreamFormat() {
        return this.upstreamFormatRequired ? null : this.upstreamFormat;
    }

    public synchronized long getLargestQueuedTimestampUs() {
        return this.largestQueuedTimestampUs;
    }

    public synchronized void rewind() {
        this.readPosition = 0;
    }

    public synchronized int read(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z, boolean z2, Format format, SampleExtrasHolder sampleExtrasHolder) {
        if (!hasNextSample()) {
            if (z2) {
                decoderInputBuffer.setFlags(4);
                return -4;
            }
            Format format2 = this.upstreamFormat;
            if (format2 == null || (!z && format2 == format)) {
                return -3;
            }
            formatHolder.format = format2;
            return -5;
        }
        int relativeIndex = getRelativeIndex(this.readPosition);
        if (!z && this.formats[relativeIndex] == format) {
            if (decoderInputBuffer.isFlagsOnly()) {
                return -3;
            }
            decoderInputBuffer.timeUs = this.timesUs[relativeIndex];
            decoderInputBuffer.setFlags(this.flags[relativeIndex]);
            sampleExtrasHolder.size = this.sizes[relativeIndex];
            sampleExtrasHolder.offset = this.offsets[relativeIndex];
            sampleExtrasHolder.cryptoData = this.cryptoDatas[relativeIndex];
            this.readPosition++;
            return -4;
        }
        formatHolder.format = this.formats[relativeIndex];
        return -5;
    }

    public synchronized boolean advanceTo(long j, boolean z, boolean z2) {
        int relativeIndex = getRelativeIndex(this.readPosition);
        if (hasNextSample() && j >= this.timesUs[relativeIndex] && (j <= this.largestQueuedTimestampUs || z2)) {
            int iFindSampleBefore = findSampleBefore(relativeIndex, this.length - this.readPosition, j, z);
            if (iFindSampleBefore == -1) {
                return false;
            }
            this.readPosition += iFindSampleBefore;
            return true;
        }
        return false;
    }

    public synchronized void advanceToEnd() {
        if (hasNextSample()) {
            this.readPosition = this.length;
        }
    }

    public synchronized long discardTo(long j, boolean z, boolean z2) {
        int i;
        int i2 = this.length;
        if (i2 != 0) {
            long[] jArr = this.timesUs;
            int i3 = this.relativeStartIndex;
            if (j >= jArr[i3]) {
                if (z2 && (i = this.readPosition) != i2) {
                    i2 = i + 1;
                }
                int iFindSampleBefore = findSampleBefore(i3, i2, j, z);
                if (iFindSampleBefore == -1) {
                    return -1L;
                }
                return discardSamples(iFindSampleBefore);
            }
        }
        return -1L;
    }

    public synchronized long discardToRead() {
        int i = this.readPosition;
        if (i == 0) {
            return -1L;
        }
        return discardSamples(i);
    }

    public synchronized long discardToEnd() {
        int i = this.length;
        if (i == 0) {
            return -1L;
        }
        return discardSamples(i);
    }

    public synchronized boolean format(Format format) {
        if (format == null) {
            this.upstreamFormatRequired = true;
            return false;
        }
        this.upstreamFormatRequired = false;
        if (Util.areEqual(format, this.upstreamFormat)) {
            return false;
        }
        this.upstreamFormat = format;
        return true;
    }

    public synchronized void commitSample(long j, int i, long j2, int i2, TrackOutput.CryptoData cryptoData) {
        if (this.upstreamKeyframeRequired) {
            if ((i & 1) == 0) {
                return;
            } else {
                this.upstreamKeyframeRequired = false;
            }
        }
        Assertions.checkState(!this.upstreamFormatRequired);
        commitSampleTimestamp(j);
        int relativeIndex = getRelativeIndex(this.length);
        this.timesUs[relativeIndex] = j;
        long[] jArr = this.offsets;
        jArr[relativeIndex] = j2;
        this.sizes[relativeIndex] = i2;
        this.flags[relativeIndex] = i;
        this.cryptoDatas[relativeIndex] = cryptoData;
        this.formats[relativeIndex] = this.upstreamFormat;
        this.sourceIds[relativeIndex] = this.upstreamSourceId;
        int i3 = this.length + 1;
        this.length = i3;
        int i4 = this.capacity;
        if (i3 == i4) {
            int i5 = i4 + 1000;
            int[] iArr = new int[i5];
            long[] jArr2 = new long[i5];
            long[] jArr3 = new long[i5];
            int[] iArr2 = new int[i5];
            int[] iArr3 = new int[i5];
            TrackOutput.CryptoData[] cryptoDataArr = new TrackOutput.CryptoData[i5];
            Format[] formatArr = new Format[i5];
            int i6 = this.relativeStartIndex;
            int i7 = i4 - i6;
            System.arraycopy(jArr, i6, jArr2, 0, i7);
            System.arraycopy(this.timesUs, this.relativeStartIndex, jArr3, 0, i7);
            System.arraycopy(this.flags, this.relativeStartIndex, iArr2, 0, i7);
            System.arraycopy(this.sizes, this.relativeStartIndex, iArr3, 0, i7);
            System.arraycopy(this.cryptoDatas, this.relativeStartIndex, cryptoDataArr, 0, i7);
            System.arraycopy(this.formats, this.relativeStartIndex, formatArr, 0, i7);
            System.arraycopy(this.sourceIds, this.relativeStartIndex, iArr, 0, i7);
            int i8 = this.relativeStartIndex;
            System.arraycopy(this.offsets, 0, jArr2, i7, i8);
            System.arraycopy(this.timesUs, 0, jArr3, i7, i8);
            System.arraycopy(this.flags, 0, iArr2, i7, i8);
            System.arraycopy(this.sizes, 0, iArr3, i7, i8);
            System.arraycopy(this.cryptoDatas, 0, cryptoDataArr, i7, i8);
            System.arraycopy(this.formats, 0, formatArr, i7, i8);
            System.arraycopy(this.sourceIds, 0, iArr, i7, i8);
            this.offsets = jArr2;
            this.timesUs = jArr3;
            this.flags = iArr2;
            this.sizes = iArr3;
            this.cryptoDatas = cryptoDataArr;
            this.formats = formatArr;
            this.sourceIds = iArr;
            this.relativeStartIndex = 0;
            this.length = this.capacity;
            this.capacity = i5;
        }
    }

    public synchronized void commitSampleTimestamp(long j) {
        this.largestQueuedTimestampUs = Math.max(this.largestQueuedTimestampUs, j);
    }

    public synchronized boolean attemptSplice(long j) {
        if (this.length == 0) {
            return j > this.largestDiscardedTimestampUs;
        }
        if (Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(this.readPosition)) >= j) {
            return false;
        }
        int i = this.length;
        int relativeIndex = getRelativeIndex(i - 1);
        while (i > this.readPosition && this.timesUs[relativeIndex] >= j) {
            i--;
            relativeIndex--;
            if (relativeIndex == -1) {
                relativeIndex = this.capacity - 1;
            }
        }
        discardUpstreamSamples(this.absoluteStartIndex + i);
        return true;
    }

    private int findSampleBefore(int i, int i2, long j, boolean z) {
        int i3 = -1;
        for (int i4 = 0; i4 < i2 && this.timesUs[i] <= j; i4++) {
            if (!z || (this.flags[i] & 1) != 0) {
                i3 = i4;
            }
            i++;
            if (i == this.capacity) {
                i = 0;
            }
        }
        return i3;
    }

    private long discardSamples(int i) {
        this.largestDiscardedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(i));
        int i2 = this.length - i;
        this.length = i2;
        this.absoluteStartIndex += i;
        int i3 = this.relativeStartIndex + i;
        this.relativeStartIndex = i3;
        int i4 = this.capacity;
        if (i3 >= i4) {
            this.relativeStartIndex = i3 - i4;
        }
        int i5 = this.readPosition - i;
        this.readPosition = i5;
        if (i5 < 0) {
            this.readPosition = 0;
        }
        if (i2 == 0) {
            int i6 = this.relativeStartIndex;
            if (i6 != 0) {
                i4 = i6;
            }
            int i7 = i4 - 1;
            return this.offsets[i7] + ((long) this.sizes[i7]);
        }
        return this.offsets[this.relativeStartIndex];
    }

    private long getLargestTimestamp(int i) {
        long jMax = Long.MIN_VALUE;
        if (i == 0) {
            return Long.MIN_VALUE;
        }
        int relativeIndex = getRelativeIndex(i - 1);
        for (int i2 = 0; i2 < i; i2++) {
            jMax = Math.max(jMax, this.timesUs[relativeIndex]);
            if ((this.flags[relativeIndex] & 1) != 0) {
                break;
            }
            relativeIndex--;
            if (relativeIndex == -1) {
                relativeIndex = this.capacity - 1;
            }
        }
        return jMax;
    }

    private int getRelativeIndex(int i) {
        int i2 = this.relativeStartIndex + i;
        int i3 = this.capacity;
        return i2 < i3 ? i2 : i2 - i3;
    }
}
