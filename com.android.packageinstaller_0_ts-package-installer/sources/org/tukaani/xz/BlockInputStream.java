package org.tukaani.xz;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.DecoderUtil;

/* JADX INFO: loaded from: classes2.dex */
class BlockInputStream extends InputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Check check;
    private long compressedSizeInHeader;
    private long compressedSizeLimit;
    private InputStream filterChain;
    private final int headerSize;
    private final CountingInputStream inCounted;
    private final DataInputStream inData;
    private long uncompressedSizeInHeader;
    private final boolean verifyCheck;
    private long uncompressedSize = 0;
    private boolean endReached = false;
    private final byte[] tempBuf = new byte[1];

    public BlockInputStream(InputStream in, Check check, boolean verifyCheck, int memoryLimit, long unpaddedSizeInIndex, long uncompressedSizeInIndex, ArrayCache arrayCache) throws IndexIndicatorException, IOException {
        String str;
        this.uncompressedSizeInHeader = -1L;
        this.compressedSizeInHeader = -1L;
        this.check = check;
        this.verifyCheck = verifyCheck;
        DataInputStream dataInputStream = new DataInputStream(in);
        this.inData = dataInputStream;
        byte[] buf = new byte[1024];
        dataInputStream.readFully(buf, 0, 1);
        if (buf[0] == 0) {
            throw new IndexIndicatorException();
        }
        int i = ((buf[0] & 255) + 1) * 4;
        this.headerSize = i;
        this.inData.readFully(buf, 1, i - 1);
        int i2 = this.headerSize;
        if (!DecoderUtil.isCRC32Valid(buf, 0, i2 - 4, i2 - 4)) {
            throw new CorruptedInputException("XZ Block Header is corrupt");
        }
        if ((buf[1] & 60) != 0) {
            throw new UnsupportedOptionsException("Unsupported options in XZ Block Header");
        }
        int filterCount = (buf[1] & 3) + 1;
        long[] filterIDs = new long[filterCount];
        byte[][] filterProps = new byte[filterCount][];
        ByteArrayInputStream bufStream = new ByteArrayInputStream(buf, 2, this.headerSize - 6);
        try {
            this.compressedSizeLimit = (9223372036854775804L - ((long) this.headerSize)) - ((long) check.getSize());
            if ((buf[1] & 64) == 0) {
                str = "XZ Block Header is corrupt";
            } else {
                try {
                    long jDecodeVLI = DecoderUtil.decodeVLI(bufStream);
                    this.compressedSizeInHeader = jDecodeVLI;
                    if (jDecodeVLI != 0) {
                        str = "XZ Block Header is corrupt";
                        try {
                            if (jDecodeVLI <= this.compressedSizeLimit) {
                                this.compressedSizeLimit = jDecodeVLI;
                            }
                        } catch (IOException e) {
                            throw new CorruptedInputException(str);
                        }
                    }
                    throw new CorruptedInputException();
                } catch (IOException e2) {
                    str = "XZ Block Header is corrupt";
                }
            }
            try {
                if ((buf[1] & 128) != 0) {
                    this.uncompressedSizeInHeader = DecoderUtil.decodeVLI(bufStream);
                }
                for (int i3 = 0; i3 < filterCount; i3++) {
                    filterIDs[i3] = DecoderUtil.decodeVLI(bufStream);
                    long filterPropsSize = DecoderUtil.decodeVLI(bufStream);
                    if (filterPropsSize > bufStream.available()) {
                        throw new CorruptedInputException();
                    }
                    filterProps[i3] = new byte[(int) filterPropsSize];
                    bufStream.read(filterProps[i3]);
                }
                for (int i4 = bufStream.available(); i4 > 0; i4--) {
                    if (bufStream.read() != 0) {
                        throw new UnsupportedOptionsException("Unsupported options in XZ Block Header");
                    }
                }
                if (unpaddedSizeInIndex != -1) {
                    int headerAndCheckSize = this.headerSize + check.getSize();
                    if (headerAndCheckSize >= unpaddedSizeInIndex) {
                        throw new CorruptedInputException("XZ Index does not match a Block Header");
                    }
                    long compressedSizeFromIndex = unpaddedSizeInIndex - ((long) headerAndCheckSize);
                    if (compressedSizeFromIndex <= this.compressedSizeLimit) {
                        long j = this.compressedSizeInHeader;
                        if (j == -1 || j == compressedSizeFromIndex) {
                            long j2 = this.uncompressedSizeInHeader;
                            if (j2 != -1 && j2 != uncompressedSizeInIndex) {
                                throw new CorruptedInputException("XZ Index does not match a Block Header");
                            }
                            this.compressedSizeLimit = compressedSizeFromIndex;
                            this.compressedSizeInHeader = compressedSizeFromIndex;
                            this.uncompressedSizeInHeader = uncompressedSizeInIndex;
                        }
                    }
                    throw new CorruptedInputException("XZ Index does not match a Block Header");
                }
                FilterDecoder[] filters = new FilterDecoder[filterIDs.length];
                for (int i5 = 0; i5 < filters.length; i5++) {
                    if (filterIDs[i5] == 33) {
                        filters[i5] = new LZMA2Decoder(filterProps[i5]);
                    } else if (filterIDs[i5] == 3) {
                        filters[i5] = new DeltaDecoder(filterProps[i5]);
                    } else if (BCJDecoder.isBCJFilterID(filterIDs[i5])) {
                        filters[i5] = new BCJDecoder(filterIDs[i5], filterProps[i5]);
                    } else {
                        throw new UnsupportedOptionsException("Unknown Filter ID " + filterIDs[i5]);
                    }
                }
                RawCoder.validate(filters);
                if (memoryLimit >= 0) {
                    int memoryNeeded = 0;
                    for (FilterDecoder filterDecoder : filters) {
                        memoryNeeded += filterDecoder.getMemoryUsage();
                    }
                    if (memoryNeeded > memoryLimit) {
                        throw new MemoryLimitException(memoryNeeded, memoryLimit);
                    }
                }
                CountingInputStream countingInputStream = new CountingInputStream(in);
                this.inCounted = countingInputStream;
                this.filterChain = countingInputStream;
                for (int i6 = filters.length - 1; i6 >= 0; i6--) {
                    this.filterChain = filters[i6].getInputStream(this.filterChain, arrayCache);
                }
            } catch (IOException e3) {
                throw new CorruptedInputException(str);
            }
        } catch (IOException e4) {
            str = "XZ Block Header is corrupt";
        }
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
        if (this.endReached) {
            return -1;
        }
        int ret = this.filterChain.read(buf, off, len);
        if (ret > 0) {
            if (this.verifyCheck) {
                this.check.update(buf, off, ret);
            }
            this.uncompressedSize += (long) ret;
            long compressedSize = this.inCounted.getSize();
            if (compressedSize >= 0 && compressedSize <= this.compressedSizeLimit) {
                long j = this.uncompressedSize;
                if (j >= 0) {
                    long j2 = this.uncompressedSizeInHeader;
                    if (j2 == -1 || j <= j2) {
                        if (ret < len || this.uncompressedSize == this.uncompressedSizeInHeader) {
                            if (this.filterChain.read() != -1) {
                                throw new CorruptedInputException();
                            }
                            validate();
                            this.endReached = true;
                        }
                    }
                }
            }
            throw new CorruptedInputException();
        }
        if (ret == -1) {
            validate();
            this.endReached = true;
        }
        return ret;
    }

    private void validate() throws IOException {
        long compressedSize = this.inCounted.getSize();
        long j = this.compressedSizeInHeader;
        if (j == -1 || j == compressedSize) {
            long j2 = this.uncompressedSizeInHeader;
            if (j2 == -1 || j2 == this.uncompressedSize) {
                while (true) {
                    long compressedSize2 = 1 + compressedSize;
                    if ((compressedSize & 3) != 0) {
                        if (this.inData.readUnsignedByte() != 0) {
                            throw new CorruptedInputException();
                        }
                        compressedSize = compressedSize2;
                    } else {
                        byte[] storedCheck = new byte[this.check.getSize()];
                        this.inData.readFully(storedCheck);
                        if (this.verifyCheck && !Arrays.equals(this.check.finish(), storedCheck)) {
                            throw new CorruptedInputException("Integrity check (" + this.check.getName() + ") does not match");
                        }
                        return;
                    }
                }
            }
        }
        throw new CorruptedInputException();
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.filterChain.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            this.filterChain.close();
        } catch (IOException e) {
        }
        this.filterChain = null;
    }

    public long getUnpaddedSize() {
        return ((long) this.headerSize) + this.inCounted.getSize() + ((long) this.check.getSize());
    }

    public long getUncompressedSize() {
        return this.uncompressedSize;
    }
}
