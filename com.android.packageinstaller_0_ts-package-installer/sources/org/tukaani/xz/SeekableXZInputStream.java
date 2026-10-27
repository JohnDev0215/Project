package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.DecoderUtil;
import org.tukaani.xz.common.StreamFlags;
import org.tukaani.xz.index.BlockInfo;
import org.tukaani.xz.index.IndexDecoder;

/* JADX INFO: loaded from: classes2.dex */
public class SeekableXZInputStream extends SeekableInputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final ArrayCache arrayCache;
    private int blockCount;
    private BlockInputStream blockDecoder;
    private Check check;
    private int checkTypes;
    private final BlockInfo curBlockInfo;
    private long curPos;
    private boolean endReached;
    private IOException exception;
    private SeekableInputStream in;
    private int indexMemoryUsage;
    private long largestBlockSize;
    private final int memoryLimit;
    private final BlockInfo queriedBlockInfo;
    private boolean seekNeeded;
    private long seekPos;
    private final ArrayList<IndexDecoder> streams;
    private final byte[] tempBuf;
    private long uncompressedSize;
    private final boolean verifyCheck;

    public SeekableXZInputStream(SeekableInputStream in) throws IOException {
        this(in, -1);
    }

    public SeekableXZInputStream(SeekableInputStream in, ArrayCache arrayCache) throws IOException {
        this(in, -1, arrayCache);
    }

    public SeekableXZInputStream(SeekableInputStream in, int memoryLimit) throws IOException {
        this(in, memoryLimit, true);
    }

    public SeekableXZInputStream(SeekableInputStream in, int memoryLimit, ArrayCache arrayCache) throws IOException {
        this(in, memoryLimit, true, arrayCache);
    }

    public SeekableXZInputStream(SeekableInputStream in, int memoryLimit, boolean verifyCheck) throws IOException {
        this(in, memoryLimit, verifyCheck, ArrayCache.getDefaultCache());
    }

    public SeekableXZInputStream(SeekableInputStream in, int memoryLimit, boolean verifyCheck, ArrayCache arrayCache) throws IOException {
        this.indexMemoryUsage = 0;
        this.streams = new ArrayList<>();
        this.checkTypes = 0;
        long j = 0;
        this.uncompressedSize = 0L;
        this.largestBlockSize = 0L;
        this.blockCount = 0;
        this.blockDecoder = null;
        this.curPos = 0L;
        this.seekNeeded = false;
        this.endReached = false;
        this.exception = null;
        this.tempBuf = new byte[1];
        this.arrayCache = arrayCache;
        this.verifyCheck = verifyCheck;
        this.in = in;
        DataInputStream inData = new DataInputStream(in);
        in.seek(0L);
        byte[] buf = new byte[XZ.HEADER_MAGIC.length];
        inData.readFully(buf);
        if (!Arrays.equals(buf, XZ.HEADER_MAGIC)) {
            throw new XZFormatException();
        }
        long pos = in.length();
        if ((3 & pos) != 0) {
            throw new CorruptedInputException("XZ file size is not a multiple of 4 bytes");
        }
        byte[] buf2 = new byte[12];
        int memoryLimit2 = memoryLimit;
        long streamPadding = 0;
        while (pos > j) {
            if (pos < 12) {
                throw new CorruptedInputException();
            }
            in.seek(pos - 12);
            inData.readFully(buf2);
            if (buf2[8] == 0 && buf2[9] == 0 && buf2[10] == 0 && buf2[11] == 0) {
                streamPadding += 4;
                pos -= 4;
            } else {
                long pos2 = pos - 12;
                StreamFlags streamFooter = DecoderUtil.decodeStreamFooter(buf2);
                if (streamFooter.backwardSize < pos2) {
                    this.check = Check.getInstance(streamFooter.checkType);
                    this.checkTypes |= 1 << streamFooter.checkType;
                    in.seek(pos2 - streamFooter.backwardSize);
                    try {
                    } catch (MemoryLimitException e) {
                        e = e;
                    }
                    try {
                        IndexDecoder index = new IndexDecoder(in, streamFooter, streamPadding, memoryLimit2);
                        this.indexMemoryUsage += index.getMemoryUsage();
                        memoryLimit2 = memoryLimit2 >= 0 ? memoryLimit2 - index.getMemoryUsage() : memoryLimit2;
                        if (this.largestBlockSize < index.getLargestBlockSize()) {
                            this.largestBlockSize = index.getLargestBlockSize();
                        }
                        long off = index.getStreamSize() - 12;
                        if (pos2 < off) {
                            throw new CorruptedInputException("XZ Index indicates too big compressed size for the XZ Stream");
                        }
                        long pos3 = pos2 - off;
                        in.seek(pos3);
                        inData.readFully(buf2);
                        StreamFlags streamHeader = DecoderUtil.decodeStreamHeader(buf2);
                        if (!DecoderUtil.areStreamFlagsEqual(streamHeader, streamFooter)) {
                            throw new CorruptedInputException("XZ Stream Footer does not match Stream Header");
                        }
                        long uncompressedSize = this.uncompressedSize + index.getUncompressedSize();
                        this.uncompressedSize = uncompressedSize;
                        if (uncompressedSize < 0) {
                            throw new UnsupportedOptionsException("XZ file is too big");
                        }
                        int recordCount = this.blockCount + index.getRecordCount();
                        this.blockCount = recordCount;
                        if (recordCount < 0) {
                            throw new UnsupportedOptionsException("XZ file has over 2147483647 Blocks");
                        }
                        this.streams.add(index);
                        streamPadding = 0;
                        pos = pos3;
                        j = 0;
                    } catch (MemoryLimitException e2) {
                        e = e2;
                        int memoryNeeded = e.getMemoryNeeded();
                        int i = this.indexMemoryUsage;
                        throw new MemoryLimitException(memoryNeeded + i, i + memoryLimit2);
                    }
                } else {
                    throw new CorruptedInputException("Backward Size in XZ Stream Footer is too big");
                }
            }
        }
        this.memoryLimit = memoryLimit2;
        ArrayList<IndexDecoder> arrayList = this.streams;
        IndexDecoder prev = arrayList.get(arrayList.size() - 1);
        for (int i2 = this.streams.size() - 2; i2 >= 0; i2--) {
            IndexDecoder cur = this.streams.get(i2);
            cur.setOffsets(prev);
            prev = cur;
        }
        ArrayList<IndexDecoder> arrayList2 = this.streams;
        IndexDecoder first = arrayList2.get(arrayList2.size() - 1);
        this.curBlockInfo = new BlockInfo(first);
        this.queriedBlockInfo = new BlockInfo(first);
    }

    public int getCheckTypes() {
        return this.checkTypes;
    }

    public int getIndexMemoryUsage() {
        return this.indexMemoryUsage;
    }

    public long getLargestBlockSize() {
        return this.largestBlockSize;
    }

    public int getStreamCount() {
        return this.streams.size();
    }

    public int getBlockCount() {
        return this.blockCount;
    }

    public long getBlockPos(int blockNumber) {
        locateBlockByNumber(this.queriedBlockInfo, blockNumber);
        return this.queriedBlockInfo.uncompressedOffset;
    }

    public long getBlockSize(int blockNumber) {
        locateBlockByNumber(this.queriedBlockInfo, blockNumber);
        return this.queriedBlockInfo.uncompressedSize;
    }

    public long getBlockCompPos(int blockNumber) {
        locateBlockByNumber(this.queriedBlockInfo, blockNumber);
        return this.queriedBlockInfo.compressedOffset;
    }

    public long getBlockCompSize(int blockNumber) {
        locateBlockByNumber(this.queriedBlockInfo, blockNumber);
        return (this.queriedBlockInfo.unpaddedSize + 3) & (-4);
    }

    public int getBlockCheckType(int blockNumber) {
        locateBlockByNumber(this.queriedBlockInfo, blockNumber);
        return this.queriedBlockInfo.getCheckType();
    }

    public int getBlockNumber(long pos) {
        locateBlockByPos(this.queriedBlockInfo, pos);
        return this.queriedBlockInfo.blockNumber;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.tempBuf, 0, 1) == -1) {
            return -1;
        }
        return this.tempBuf[0] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] buf, int off, int len) throws IOException {
        if (off < 0 || len < 0 || off + len < 0 || off + len > buf.length) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return 0;
        }
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        int size = 0;
        try {
            if (this.seekNeeded) {
                seek();
            }
            if (this.endReached) {
                return -1;
            }
            while (len > 0) {
                if (this.blockDecoder == null) {
                    seek();
                    if (this.endReached) {
                        break;
                    }
                }
                int ret = this.blockDecoder.read(buf, off, len);
                if (ret > 0) {
                    this.curPos += (long) ret;
                    size += ret;
                    off += ret;
                    len -= ret;
                } else if (ret == -1) {
                    this.blockDecoder = null;
                }
            }
        } catch (IOException e) {
            e = e;
            if (e instanceof EOFException) {
                e = new CorruptedInputException();
            }
            this.exception = e;
            if (size == 0) {
                throw e;
            }
        }
        return size;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        BlockInputStream blockInputStream;
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        if (this.endReached || this.seekNeeded || (blockInputStream = this.blockDecoder) == null) {
            return 0;
        }
        return blockInputStream.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        close(true);
    }

    public void close(boolean closeInput) throws IOException {
        if (this.in != null) {
            BlockInputStream blockInputStream = this.blockDecoder;
            if (blockInputStream != null) {
                blockInputStream.close();
                this.blockDecoder = null;
            }
            if (closeInput) {
                try {
                    this.in.close();
                } finally {
                    this.in = null;
                }
            }
        }
    }

    @Override // org.tukaani.xz.SeekableInputStream
    public long length() {
        return this.uncompressedSize;
    }

    @Override // org.tukaani.xz.SeekableInputStream
    public long position() throws IOException {
        if (this.in != null) {
            return this.seekNeeded ? this.seekPos : this.curPos;
        }
        throw new XZIOException("Stream closed");
    }

    @Override // org.tukaani.xz.SeekableInputStream
    public void seek(long pos) throws IOException {
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        if (pos < 0) {
            throw new XZIOException("Negative seek position: " + pos);
        }
        this.seekPos = pos;
        this.seekNeeded = true;
    }

    public void seekToBlock(int blockNumber) throws IOException {
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        if (blockNumber < 0 || blockNumber >= this.blockCount) {
            throw new XZIOException("Invalid XZ Block number: " + blockNumber);
        }
        this.seekPos = getBlockPos(blockNumber);
        this.seekNeeded = true;
    }

    private void seek() throws IOException {
        if (!this.seekNeeded) {
            if (this.curBlockInfo.hasNext()) {
                this.curBlockInfo.setNext();
                initBlockDecoder();
                return;
            }
            this.seekPos = this.curPos;
        }
        this.seekNeeded = false;
        long j = this.seekPos;
        if (j >= this.uncompressedSize) {
            this.curPos = j;
            BlockInputStream blockInputStream = this.blockDecoder;
            if (blockInputStream != null) {
                blockInputStream.close();
                this.blockDecoder = null;
            }
            this.endReached = true;
            return;
        }
        this.endReached = false;
        locateBlockByPos(this.curBlockInfo, j);
        if (this.curPos <= this.curBlockInfo.uncompressedOffset || this.curPos > this.seekPos) {
            this.in.seek(this.curBlockInfo.compressedOffset);
            this.check = Check.getInstance(this.curBlockInfo.getCheckType());
            initBlockDecoder();
            this.curPos = this.curBlockInfo.uncompressedOffset;
        }
        long j2 = this.seekPos;
        long j3 = this.curPos;
        if (j2 > j3) {
            long skipAmount = j2 - j3;
            if (this.blockDecoder.skip(skipAmount) != skipAmount) {
                throw new CorruptedInputException();
            }
            this.curPos = this.seekPos;
        }
    }

    private void locateBlockByPos(BlockInfo info, long pos) {
        if (pos < 0 || pos >= this.uncompressedSize) {
            throw new IndexOutOfBoundsException("Invalid uncompressed position: " + pos);
        }
        int i = 0;
        while (true) {
            IndexDecoder index = this.streams.get(i);
            if (!index.hasUncompressedOffset(pos)) {
                i++;
            } else {
                index.locateBlock(info, pos);
                return;
            }
        }
    }

    private void locateBlockByNumber(BlockInfo info, int blockNumber) {
        if (blockNumber < 0 || blockNumber >= this.blockCount) {
            throw new IndexOutOfBoundsException("Invalid XZ Block number: " + blockNumber);
        }
        if (info.blockNumber == blockNumber) {
            return;
        }
        int i = 0;
        while (true) {
            IndexDecoder index = this.streams.get(i);
            if (!index.hasRecord(blockNumber)) {
                i++;
            } else {
                index.setBlockInfo(info, blockNumber);
                return;
            }
        }
    }

    private void initBlockDecoder() throws IOException {
        try {
            if (this.blockDecoder != null) {
                this.blockDecoder.close();
                this.blockDecoder = null;
            }
            this.blockDecoder = new BlockInputStream(this.in, this.check, this.verifyCheck, this.memoryLimit, this.curBlockInfo.unpaddedSize, this.curBlockInfo.uncompressedSize, this.arrayCache);
        } catch (IndexIndicatorException e) {
            throw new CorruptedInputException();
        } catch (MemoryLimitException e2) {
            int memoryNeeded = e2.getMemoryNeeded();
            int i = this.indexMemoryUsage;
            throw new MemoryLimitException(memoryNeeded + i, this.memoryLimit + i);
        }
    }
}
