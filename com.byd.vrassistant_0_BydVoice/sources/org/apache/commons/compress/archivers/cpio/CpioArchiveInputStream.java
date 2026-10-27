package org.apache.commons.compress.archivers.cpio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.CharsetNames;
import org.apache.commons.compress.utils.IOUtils;

/* JADX INFO: loaded from: classes4.dex */
public class CpioArchiveInputStream extends ArchiveInputStream implements CpioConstants {
    private final int blockSize;
    private boolean closed;
    private long crc;
    final String encoding;
    private CpioArchiveEntry entry;
    private long entryBytesRead;
    private boolean entryEOF;
    private final byte[] fourBytesBuf;
    private final InputStream in;
    private final byte[] sixBytesBuf;
    private final byte[] tmpbuf;
    private final byte[] twoBytesBuf;
    private final ZipEncoding zipEncoding;

    public CpioArchiveInputStream(InputStream inputStream) {
        this(inputStream, 512, CharsetNames.US_ASCII);
    }

    public CpioArchiveInputStream(InputStream inputStream, String str) {
        this(inputStream, 512, str);
    }

    public CpioArchiveInputStream(InputStream inputStream, int i) {
        this(inputStream, i, CharsetNames.US_ASCII);
    }

    public CpioArchiveInputStream(InputStream inputStream, int i, String str) {
        this.tmpbuf = new byte[4096];
        this.twoBytesBuf = new byte[2];
        this.fourBytesBuf = new byte[4];
        this.sixBytesBuf = new byte[6];
        this.in = inputStream;
        if (i <= 0) {
            throw new IllegalArgumentException("blockSize must be bigger than 0");
        }
        this.blockSize = i;
        this.encoding = str;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(str);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        ensureOpen();
        return this.entryEOF ? 0 : 1;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.in.close();
        this.closed = true;
    }

    private void closeEntry() throws IOException {
        while (skip(2147483647L) == 2147483647L) {
        }
    }

    private void ensureOpen() throws IOException {
        if (this.closed) {
            throw new IOException("Stream closed");
        }
    }

    public CpioArchiveEntry getNextCPIOEntry() throws IOException {
        String asciiString;
        ensureOpen();
        if (this.entry != null) {
            closeEntry();
        }
        byte[] bArr = this.twoBytesBuf;
        readFully(bArr, 0, bArr.length);
        if (CpioUtil.byteArray2long(this.twoBytesBuf, false) == 29127) {
            this.entry = readOldBinaryEntry(false);
        } else if (CpioUtil.byteArray2long(this.twoBytesBuf, true) == 29127) {
            this.entry = readOldBinaryEntry(true);
        } else {
            byte[] bArr2 = this.twoBytesBuf;
            System.arraycopy(bArr2, 0, this.sixBytesBuf, 0, bArr2.length);
            readFully(this.sixBytesBuf, this.twoBytesBuf.length, this.fourBytesBuf.length);
            asciiString = ArchiveUtils.toAsciiString(this.sixBytesBuf);
            asciiString.hashCode();
            switch (asciiString) {
                case "070701":
                    this.entry = readNewEntry(false);
                    break;
                case "070702":
                    this.entry = readNewEntry(true);
                    break;
                case "070707":
                    this.entry = readOldAsciiEntry();
                    break;
                default:
                    throw new IOException("Unknown magic [" + asciiString + "]. Occurred at byte: " + getBytesRead());
            }
        }
        this.entryBytesRead = 0L;
        this.entryEOF = false;
        this.crc = 0L;
        if (this.entry.getName().equals(CpioConstants.CPIO_TRAILER)) {
            this.entryEOF = true;
            skipRemainderOfLastBlock();
            return null;
        }
        return this.entry;
    }

    private void skip(int i) throws IOException {
        if (i > 0) {
            readFully(this.fourBytesBuf, 0, i);
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        ensureOpen();
        if (i < 0 || i2 < 0 || i > bArr.length - i2) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return 0;
        }
        CpioArchiveEntry cpioArchiveEntry = this.entry;
        if (cpioArchiveEntry == null || this.entryEOF) {
            return -1;
        }
        if (this.entryBytesRead == cpioArchiveEntry.getSize()) {
            skip(this.entry.getDataPadCount());
            this.entryEOF = true;
            if (this.entry.getFormat() != 2 || this.crc == this.entry.getChksum()) {
                return -1;
            }
            throw new IOException("CRC Error. Occurred at byte: " + getBytesRead());
        }
        int iMin = (int) Math.min(i2, this.entry.getSize() - this.entryBytesRead);
        if (iMin < 0) {
            return -1;
        }
        int fully = readFully(bArr, i, iMin);
        if (this.entry.getFormat() == 2) {
            for (int i3 = 0; i3 < fully; i3++) {
                this.crc = (this.crc + ((long) (bArr[i3] & 255))) & 4294967295L;
            }
        }
        if (fully > 0) {
            this.entryBytesRead += (long) fully;
        }
        return fully;
    }

    private final int readFully(byte[] bArr, int i, int i2) throws IOException {
        int fully = IOUtils.readFully(this.in, bArr, i, i2);
        count(fully);
        if (fully >= i2) {
            return fully;
        }
        throw new EOFException();
    }

    private final byte[] readRange(int i) throws IOException {
        byte[] range = IOUtils.readRange(this.in, i);
        count(range.length);
        if (range.length >= i) {
            return range;
        }
        throw new EOFException();
    }

    private long readBinaryLong(int i, boolean z) throws IOException {
        return CpioUtil.byteArray2long(readRange(i), z);
    }

    private long readAsciiLong(int i, int i2) throws IOException {
        return Long.parseLong(ArchiveUtils.toAsciiString(readRange(i)), i2);
    }

    private CpioArchiveEntry readNewEntry(boolean z) throws IOException {
        CpioArchiveEntry cpioArchiveEntry;
        if (z) {
            cpioArchiveEntry = new CpioArchiveEntry((short) 2);
        } else {
            cpioArchiveEntry = new CpioArchiveEntry((short) 1);
        }
        cpioArchiveEntry.setInode(readAsciiLong(8, 16));
        long asciiLong = readAsciiLong(8, 16);
        if (CpioUtil.fileType(asciiLong) != 0) {
            cpioArchiveEntry.setMode(asciiLong);
        }
        cpioArchiveEntry.setUID(readAsciiLong(8, 16));
        cpioArchiveEntry.setGID(readAsciiLong(8, 16));
        cpioArchiveEntry.setNumberOfLinks(readAsciiLong(8, 16));
        cpioArchiveEntry.setTime(readAsciiLong(8, 16));
        cpioArchiveEntry.setSize(readAsciiLong(8, 16));
        if (cpioArchiveEntry.getSize() < 0) {
            throw new IOException("Found illegal entry with negative length");
        }
        cpioArchiveEntry.setDeviceMaj(readAsciiLong(8, 16));
        cpioArchiveEntry.setDeviceMin(readAsciiLong(8, 16));
        cpioArchiveEntry.setRemoteDeviceMaj(readAsciiLong(8, 16));
        cpioArchiveEntry.setRemoteDeviceMin(readAsciiLong(8, 16));
        long asciiLong2 = readAsciiLong(8, 16);
        if (asciiLong2 < 0) {
            throw new IOException("Found illegal entry with negative name length");
        }
        cpioArchiveEntry.setChksum(readAsciiLong(8, 16));
        String cString = readCString((int) asciiLong2);
        cpioArchiveEntry.setName(cString);
        if (CpioUtil.fileType(asciiLong) == 0 && !cString.equals(CpioConstants.CPIO_TRAILER)) {
            throw new IOException("Mode 0 only allowed in the trailer. Found entry name: " + ArchiveUtils.sanitize(cString) + " Occurred at byte: " + getBytesRead());
        }
        skip(cpioArchiveEntry.getHeaderPadCount(asciiLong2 - 1));
        return cpioArchiveEntry;
    }

    private CpioArchiveEntry readOldAsciiEntry() throws IOException {
        CpioArchiveEntry cpioArchiveEntry = new CpioArchiveEntry((short) 4);
        cpioArchiveEntry.setDevice(readAsciiLong(6, 8));
        cpioArchiveEntry.setInode(readAsciiLong(6, 8));
        long asciiLong = readAsciiLong(6, 8);
        if (CpioUtil.fileType(asciiLong) != 0) {
            cpioArchiveEntry.setMode(asciiLong);
        }
        cpioArchiveEntry.setUID(readAsciiLong(6, 8));
        cpioArchiveEntry.setGID(readAsciiLong(6, 8));
        cpioArchiveEntry.setNumberOfLinks(readAsciiLong(6, 8));
        cpioArchiveEntry.setRemoteDevice(readAsciiLong(6, 8));
        cpioArchiveEntry.setTime(readAsciiLong(11, 8));
        long asciiLong2 = readAsciiLong(6, 8);
        if (asciiLong2 < 0) {
            throw new IOException("Found illegal entry with negative name length");
        }
        cpioArchiveEntry.setSize(readAsciiLong(11, 8));
        if (cpioArchiveEntry.getSize() < 0) {
            throw new IOException("Found illegal entry with negative length");
        }
        String cString = readCString((int) asciiLong2);
        cpioArchiveEntry.setName(cString);
        if (CpioUtil.fileType(asciiLong) != 0 || cString.equals(CpioConstants.CPIO_TRAILER)) {
            return cpioArchiveEntry;
        }
        throw new IOException("Mode 0 only allowed in the trailer. Found entry: " + ArchiveUtils.sanitize(cString) + " Occurred at byte: " + getBytesRead());
    }

    private CpioArchiveEntry readOldBinaryEntry(boolean z) throws IOException {
        CpioArchiveEntry cpioArchiveEntry = new CpioArchiveEntry((short) 8);
        cpioArchiveEntry.setDevice(readBinaryLong(2, z));
        cpioArchiveEntry.setInode(readBinaryLong(2, z));
        long binaryLong = readBinaryLong(2, z);
        if (CpioUtil.fileType(binaryLong) != 0) {
            cpioArchiveEntry.setMode(binaryLong);
        }
        cpioArchiveEntry.setUID(readBinaryLong(2, z));
        cpioArchiveEntry.setGID(readBinaryLong(2, z));
        cpioArchiveEntry.setNumberOfLinks(readBinaryLong(2, z));
        cpioArchiveEntry.setRemoteDevice(readBinaryLong(2, z));
        cpioArchiveEntry.setTime(readBinaryLong(4, z));
        long binaryLong2 = readBinaryLong(2, z);
        if (binaryLong2 < 0) {
            throw new IOException("Found illegal entry with negative name length");
        }
        cpioArchiveEntry.setSize(readBinaryLong(4, z));
        if (cpioArchiveEntry.getSize() < 0) {
            throw new IOException("Found illegal entry with negative length");
        }
        String cString = readCString((int) binaryLong2);
        cpioArchiveEntry.setName(cString);
        if (CpioUtil.fileType(binaryLong) == 0 && !cString.equals(CpioConstants.CPIO_TRAILER)) {
            throw new IOException("Mode 0 only allowed in the trailer. Found entry: " + ArchiveUtils.sanitize(cString) + "Occurred at byte: " + getBytesRead());
        }
        skip(cpioArchiveEntry.getHeaderPadCount(binaryLong2 - 1));
        return cpioArchiveEntry;
    }

    private String readCString(int i) throws IOException {
        byte[] range = readRange(i - 1);
        if (this.in.read() == -1) {
            throw new EOFException();
        }
        return this.zipEncoding.decode(range);
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("Negative skip length");
        }
        ensureOpen();
        int iMin = (int) Math.min(j, 2147483647L);
        int i = 0;
        while (true) {
            if (i >= iMin) {
                break;
            }
            int length = iMin - i;
            byte[] bArr = this.tmpbuf;
            if (length > bArr.length) {
                length = bArr.length;
            }
            int i2 = read(bArr, 0, length);
            if (i2 == -1) {
                this.entryEOF = true;
                break;
            }
            i += i2;
        }
        return i;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public ArchiveEntry getNextEntry() throws IOException {
        return getNextCPIOEntry();
    }

    private void skipRemainderOfLastBlock() throws IOException {
        long bytesRead = getBytesRead();
        int i = this.blockSize;
        long j = bytesRead % ((long) i);
        long j2 = j == 0 ? 0L : ((long) i) - j;
        while (j2 > 0) {
            long jSkip = skip(((long) this.blockSize) - j);
            if (jSkip <= 0) {
                return;
            } else {
                j2 -= jSkip;
            }
        }
    }

    public static boolean matches(byte[] bArr, int i) {
        if (i < 6) {
            return false;
        }
        byte b = bArr[0];
        if (b == 113 && (bArr[1] & 255) == 199) {
            return true;
        }
        byte b2 = bArr[1];
        if (b2 == 113 && (b & 255) == 199) {
            return true;
        }
        if (b != 48 || b2 != 55 || bArr[2] != 48 || bArr[3] != 55 || bArr[4] != 48) {
            return false;
        }
        byte b3 = bArr[5];
        return b3 == 49 || b3 == 50 || b3 == 55;
    }
}
