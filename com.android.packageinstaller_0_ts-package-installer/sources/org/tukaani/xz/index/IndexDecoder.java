package org.tukaani.xz.index;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import org.tukaani.xz.CorruptedInputException;
import org.tukaani.xz.MemoryLimitException;
import org.tukaani.xz.SeekableInputStream;
import org.tukaani.xz.UnsupportedOptionsException;
import org.tukaani.xz.common.DecoderUtil;
import org.tukaani.xz.common.StreamFlags;

/* JADX INFO: loaded from: classes2.dex */
public class IndexDecoder extends IndexBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private long compressedOffset;
    private long largestBlockSize;
    private final int memoryUsage;
    private int recordOffset;
    private final StreamFlags streamFlags;
    private final long streamPadding;
    private final long[] uncompressed;
    private long uncompressedOffset;
    private final long[] unpadded;

    @Override // org.tukaani.xz.index.IndexBase
    public /* bridge */ /* synthetic */ long getIndexSize() {
        return super.getIndexSize();
    }

    @Override // org.tukaani.xz.index.IndexBase
    public /* bridge */ /* synthetic */ long getStreamSize() {
        return super.getStreamSize();
    }

    public IndexDecoder(SeekableInputStream in, StreamFlags streamFooterFlags, long streamPadding, int memoryLimit) throws IOException {
        super(new CorruptedInputException("XZ Index is corrupt"));
        this.largestBlockSize = 0L;
        this.recordOffset = 0;
        this.compressedOffset = 0L;
        this.uncompressedOffset = 0L;
        this.streamFlags = streamFooterFlags;
        this.streamPadding = streamPadding;
        long endPos = (in.position() + streamFooterFlags.backwardSize) - 4;
        CRC32 crc32 = new CRC32();
        CheckedInputStream inChecked = new CheckedInputStream(in, crc32);
        if (inChecked.read() != 0) {
            throw new CorruptedInputException("XZ Index is corrupt");
        }
        try {
            long count = DecoderUtil.decodeVLI(inChecked);
            try {
                if (count >= streamFooterFlags.backwardSize / 2) {
                    throw new CorruptedInputException("XZ Index is corrupt");
                }
                if (count > 2147483647L) {
                    throw new UnsupportedOptionsException("XZ Index has over 2147483647 Records");
                }
                int i = ((int) (((16 * count) + 1023) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID)) + 1;
                this.memoryUsage = i;
                if (memoryLimit < 0 || i <= memoryLimit) {
                    this.unpadded = new long[(int) count];
                    this.uncompressed = new long[(int) count];
                    int record = 0;
                    int i2 = (int) count;
                    while (i2 > 0) {
                        long unpaddedSize = DecoderUtil.decodeVLI(inChecked);
                        long uncompressedSize = DecoderUtil.decodeVLI(inChecked);
                        if (in.position() > endPos) {
                            throw new CorruptedInputException("XZ Index is corrupt");
                        }
                        this.unpadded[record] = this.blocksSum + unpaddedSize;
                        long count2 = count;
                        CRC32 crc322 = crc32;
                        this.uncompressed[record] = this.uncompressedSum + uncompressedSize;
                        record++;
                        super.add(unpaddedSize, uncompressedSize);
                        if (this.largestBlockSize < uncompressedSize) {
                            this.largestBlockSize = uncompressedSize;
                        }
                        i2--;
                        crc32 = crc322;
                        count = count2;
                    }
                    CRC32 crc323 = crc32;
                    int indexPaddingSize = getIndexPaddingSize();
                    if (in.position() + ((long) indexPaddingSize) != endPos) {
                        throw new CorruptedInputException("XZ Index is corrupt");
                    }
                    while (true) {
                        int indexPaddingSize2 = indexPaddingSize - 1;
                        if (indexPaddingSize > 0) {
                            if (inChecked.read() != 0) {
                                throw new CorruptedInputException("XZ Index is corrupt");
                            }
                            indexPaddingSize = indexPaddingSize2;
                        } else {
                            long value = crc323.getValue();
                            for (int i3 = 0; i3 < 4; i3++) {
                                if (((value >>> (i3 * 8)) & 255) != in.read()) {
                                    throw new CorruptedInputException("XZ Index is corrupt");
                                }
                            }
                            return;
                        }
                    }
                } else {
                    try {
                        throw new MemoryLimitException(this.memoryUsage, memoryLimit);
                    } catch (EOFException e) {
                    }
                }
            } catch (EOFException e2) {
            }
        } catch (EOFException e3) {
        }
        throw new CorruptedInputException("XZ Index is corrupt");
    }

    public void setOffsets(IndexDecoder prev) {
        this.recordOffset = prev.recordOffset + ((int) prev.recordCount);
        this.compressedOffset = prev.compressedOffset + prev.getStreamSize() + prev.streamPadding;
        this.uncompressedOffset = prev.uncompressedOffset + prev.uncompressedSum;
    }

    public int getMemoryUsage() {
        return this.memoryUsage;
    }

    public StreamFlags getStreamFlags() {
        return this.streamFlags;
    }

    public int getRecordCount() {
        return (int) this.recordCount;
    }

    public long getUncompressedSize() {
        return this.uncompressedSum;
    }

    public long getLargestBlockSize() {
        return this.largestBlockSize;
    }

    public boolean hasUncompressedOffset(long pos) {
        long j = this.uncompressedOffset;
        return pos >= j && pos < j + this.uncompressedSum;
    }

    public boolean hasRecord(int blockNumber) {
        int i = this.recordOffset;
        return blockNumber >= i && ((long) blockNumber) < ((long) i) + this.recordCount;
    }

    public void locateBlock(BlockInfo info, long target) {
        long target2 = target - this.uncompressedOffset;
        int left = 0;
        int right = this.unpadded.length - 1;
        while (left < right) {
            int i = ((right - left) / 2) + left;
            if (this.uncompressed[i] <= target2) {
                left = i + 1;
            } else {
                right = i;
            }
        }
        setBlockInfo(info, this.recordOffset + left);
    }

    public void setBlockInfo(BlockInfo info, int blockNumber) {
        info.index = this;
        info.blockNumber = blockNumber;
        int pos = blockNumber - this.recordOffset;
        if (pos == 0) {
            info.compressedOffset = 0L;
            info.uncompressedOffset = 0L;
        } else {
            info.compressedOffset = (this.unpadded[pos - 1] + 3) & (-4);
            info.uncompressedOffset = this.uncompressed[pos - 1];
        }
        info.unpaddedSize = this.unpadded[pos] - info.compressedOffset;
        info.uncompressedSize = this.uncompressed[pos] - info.uncompressedOffset;
        info.compressedOffset += this.compressedOffset + 12;
        info.uncompressedOffset += this.uncompressedOffset;
    }
}
