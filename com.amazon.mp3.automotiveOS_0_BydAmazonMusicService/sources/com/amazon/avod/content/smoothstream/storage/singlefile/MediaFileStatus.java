package com.amazon.avod.content.smoothstream.storage.singlefile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class MediaFileStatus {

    @JsonProperty("bitrate")
    private final int mBitrate;

    @JsonProperty("size")
    private final long mFileSize;

    @JsonProperty("ranges")
    private final List<ByteRange> mSortedByteRanges;

    public MediaFileStatus(@Nonnegative int i, @Nonnegative long j) {
        this(Lists.newArrayList(), i, j);
    }

    @JsonCreator
    MediaFileStatus(@JsonProperty("ranges") @Nonnull List<ByteRange> list, @JsonProperty("bitrate") @Nonnegative int i, @JsonProperty("size") @Nonnegative long j) {
        Preconditions.checkArgument(i >= 0, "bitrate must be non negative");
        Preconditions.checkArgument(j >= 0, "file size must be non negative");
        this.mSortedByteRanges = (List) Preconditions.checkNotNull(list, "sortedByteRanges");
        this.mBitrate = i;
        this.mFileSize = j;
    }

    public void markDownloaded(ByteRange byteRange) {
        synchronized (this.mSortedByteRanges) {
            if (this.mSortedByteRanges.size() == 0) {
                this.mSortedByteRanges.add(byteRange);
                return;
            }
            int size = this.mSortedByteRanges.size() - 1;
            ByteRange byteRange2 = null;
            int i = 0;
            int i2 = 0;
            while (size >= i) {
                i2 = i + ((size - i) / 2);
                byteRange2 = this.mSortedByteRanges.get(i2);
                if (byteRange2.getStart() - 1 <= byteRange.getEnd()) {
                    if (byteRange2.getEnd() + 1 >= byteRange.getStart()) {
                        break;
                    } else {
                        i = i2 + 1;
                    }
                } else {
                    size = i2 - 1;
                }
            }
            if (!byteRange2.intersectOrTouching(byteRange)) {
                if (byteRange2.getStart() - 1 > byteRange.getEnd()) {
                    this.mSortedByteRanges.add(i2, byteRange);
                } else {
                    this.mSortedByteRanges.add(i2 + 1, byteRange);
                }
                return;
            }
            ByteRange byteRangeMerge = byteRange.merge(this.mSortedByteRanges.get(i2));
            int i3 = i2 + 1;
            while (i3 < this.mSortedByteRanges.size()) {
                ByteRange byteRange3 = this.mSortedByteRanges.get(i3);
                if (!byteRangeMerge.intersectOrTouching(byteRange3)) {
                    break;
                }
                byteRangeMerge = byteRangeMerge.merge(byteRange3);
                i3++;
            }
            int i4 = i3 - 1;
            int i5 = i2 - 1;
            while (i5 >= 0) {
                ByteRange byteRange4 = this.mSortedByteRanges.get(i5);
                if (!byteRangeMerge.intersectOrTouching(byteRange4)) {
                    break;
                }
                byteRangeMerge = byteRangeMerge.merge(byteRange4);
                i5--;
            }
            int i6 = i5 + 1;
            this.mSortedByteRanges.subList(i6, i4 + 1).clear();
            this.mSortedByteRanges.add(i6, byteRangeMerge);
        }
    }

    public void markUnDownloaded(ByteRange byteRange) {
        synchronized (this.mSortedByteRanges) {
            if (this.mSortedByteRanges.size() == 0) {
                return;
            }
            int size = this.mSortedByteRanges.size() - 1;
            ByteRange byteRange2 = null;
            int i = 0;
            int i2 = 0;
            while (size >= i) {
                i2 = i + ((size - i) / 2);
                byteRange2 = this.mSortedByteRanges.get(i2);
                if (byteRange2.getStart() <= byteRange.getEnd()) {
                    if (byteRange2.getEnd() >= byteRange.getStart()) {
                        break;
                    } else {
                        i = i2 + 1;
                    }
                } else {
                    size = i2 - 1;
                }
            }
            if (byteRange2.intersect(byteRange)) {
                int i3 = i2 + 1;
                while (i3 < this.mSortedByteRanges.size() && byteRange.intersect(this.mSortedByteRanges.get(i3))) {
                    i3++;
                }
                int i4 = i3 - 1;
                int i5 = i2 - 1;
                while (i5 >= 0 && byteRange.intersect(this.mSortedByteRanges.get(i5))) {
                    i5--;
                }
                int i6 = i5 + 1;
                ByteRange byteRange3 = this.mSortedByteRanges.get(i6);
                ByteRange byteRange4 = this.mSortedByteRanges.get(i4);
                this.mSortedByteRanges.subList(i6, i4 + 1).clear();
                if (byteRange4.getEnd() > byteRange.getEnd()) {
                    this.mSortedByteRanges.add(i6, new ByteRange(byteRange.getEnd() + 1, byteRange4.getEnd()));
                }
                if (byteRange3.getStart() < byteRange.getStart()) {
                    this.mSortedByteRanges.add(i6, new ByteRange(byteRange3.getStart(), byteRange.getStart() - 1));
                }
            }
        }
    }

    public int getBitrate() {
        return this.mBitrate;
    }

    public long getFileSizeBytes() {
        return this.mFileSize;
    }

    public boolean isByteRangeDownloaded(ByteRange byteRange) {
        synchronized (this.mSortedByteRanges) {
            int size = this.mSortedByteRanges.size() - 1;
            int i = 0;
            while (size >= i) {
                int i2 = ((size - i) / 2) + i;
                ByteRange byteRange2 = this.mSortedByteRanges.get(i2);
                if (byteRange2.getEnd() < byteRange.getStart()) {
                    i = i2 + 1;
                } else {
                    if (byteRange2.getStart() <= byteRange.getEnd()) {
                        return byteRange2.contains(byteRange);
                    }
                    size = i2 - 1;
                }
            }
            return false;
        }
    }

    public long getDownloadedSizeBytes() {
        long sizeBytes;
        synchronized (this.mSortedByteRanges) {
            sizeBytes = 0;
            Iterator<ByteRange> it = this.mSortedByteRanges.iterator();
            while (it.hasNext()) {
                sizeBytes += it.next().getSizeBytes();
            }
        }
        return sizeBytes;
    }

    public long getFirstDownloadedChunkSizeInBytes() {
        long sizeBytes;
        synchronized (this.mSortedByteRanges) {
            sizeBytes = this.mSortedByteRanges.size() > 0 ? this.mSortedByteRanges.get(0).getSizeBytes() : 0L;
        }
        return sizeBytes;
    }

    public ImmutableList<ByteRange> getSortedDownloadedByteRanges() {
        ImmutableList<ByteRange> immutableListCopyOf;
        synchronized (this.mSortedByteRanges) {
            immutableListCopyOf = ImmutableList.copyOf((Collection) this.mSortedByteRanges);
        }
        return immutableListCopyOf;
    }
}
