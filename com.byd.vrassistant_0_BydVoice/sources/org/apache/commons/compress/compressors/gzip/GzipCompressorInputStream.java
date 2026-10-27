package org.apache.commons.compress.compressors.gzip;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;

/* JADX INFO: loaded from: classes4.dex */
public class GzipCompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int FCOMMENT = 16;
    private static final int FEXTRA = 4;
    private static final int FHCRC = 2;
    private static final int FNAME = 8;
    private static final int FRESERVED = 224;
    private final byte[] buf;
    private int bufUsed;
    private final CountingInputStream countingStream;
    private final CRC32 crc;
    private final boolean decompressConcatenated;
    private boolean endReached;
    private final InputStream in;
    private Inflater inf;
    private final byte[] oneByte;
    private final GzipParameters parameters;

    public GzipCompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, false);
    }

    public GzipCompressorInputStream(InputStream inputStream, boolean z) throws IOException {
        this.buf = new byte[8192];
        this.inf = new Inflater(true);
        this.crc = new CRC32();
        this.oneByte = new byte[1];
        this.parameters = new GzipParameters();
        CountingInputStream countingInputStream = new CountingInputStream(inputStream);
        this.countingStream = countingInputStream;
        if (countingInputStream.markSupported()) {
            this.in = countingInputStream;
        } else {
            this.in = new BufferedInputStream(countingInputStream);
        }
        this.decompressConcatenated = z;
        init(true);
    }

    public GzipParameters getMetaData() {
        return this.parameters;
    }

    private boolean init(boolean z) throws IOException {
        int i = this.in.read();
        if (i == -1 && !z) {
            return false;
        }
        if (i != 31 || this.in.read() != 139) {
            throw new IOException(z ? "Input is not in the .gz format" : "Garbage after a valid .gz stream");
        }
        DataInputStream dataInputStream = new DataInputStream(this.in);
        int unsignedByte = dataInputStream.readUnsignedByte();
        if (unsignedByte != 8) {
            throw new IOException("Unsupported compression method " + unsignedByte + " in the .gz header");
        }
        int unsignedByte2 = dataInputStream.readUnsignedByte();
        if ((unsignedByte2 & 224) != 0) {
            throw new IOException("Reserved flags are set in the .gz header");
        }
        this.parameters.setModificationTime(ByteUtils.fromLittleEndian((DataInput) dataInputStream, 4) * 1000);
        int unsignedByte3 = dataInputStream.readUnsignedByte();
        if (unsignedByte3 == 2) {
            this.parameters.setCompressionLevel(9);
        } else if (unsignedByte3 == 4) {
            this.parameters.setCompressionLevel(1);
        }
        this.parameters.setOperatingSystem(dataInputStream.readUnsignedByte());
        if ((unsignedByte2 & 4) != 0) {
            int unsignedByte4 = (dataInputStream.readUnsignedByte() << 8) | dataInputStream.readUnsignedByte();
            while (true) {
                int i2 = unsignedByte4 - 1;
                if (unsignedByte4 <= 0) {
                    break;
                }
                dataInputStream.readUnsignedByte();
                unsignedByte4 = i2;
            }
        }
        if ((unsignedByte2 & 8) != 0) {
            this.parameters.setFilename(new String(readToNull(dataInputStream), StandardCharsets.ISO_8859_1));
        }
        if ((unsignedByte2 & 16) != 0) {
            this.parameters.setComment(new String(readToNull(dataInputStream), StandardCharsets.ISO_8859_1));
        }
        if ((unsignedByte2 & 2) != 0) {
            dataInputStream.readShort();
        }
        this.inf.reset();
        this.crc.reset();
        return true;
    }

    private static byte[] readToNull(DataInput dataInput) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                int unsignedByte = dataInput.readUnsignedByte();
                if (unsignedByte != 0) {
                    byteArrayOutputStream.write(unsignedByte);
                } else {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    return byteArray;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.oneByte, 0, 1) == -1) {
            return -1;
        }
        return this.oneByte[0] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        if (this.endReached) {
            return -1;
        }
        int i3 = 0;
        while (i2 > 0) {
            if (this.inf.needsInput()) {
                this.in.mark(this.buf.length);
                int i4 = this.in.read(this.buf);
                this.bufUsed = i4;
                if (i4 == -1) {
                    throw new EOFException();
                }
                this.inf.setInput(this.buf, 0, i4);
            }
            try {
                int iInflate = this.inf.inflate(bArr, i, i2);
                this.crc.update(bArr, i, iInflate);
                i += iInflate;
                i2 -= iInflate;
                i3 += iInflate;
                count(iInflate);
                if (this.inf.finished()) {
                    this.in.reset();
                    long remaining = this.bufUsed - this.inf.getRemaining();
                    if (IOUtils.skip(this.in, remaining) != remaining) {
                        throw new IOException();
                    }
                    this.bufUsed = 0;
                    DataInputStream dataInputStream = new DataInputStream(this.in);
                    if (ByteUtils.fromLittleEndian((DataInput) dataInputStream, 4) != this.crc.getValue()) {
                        throw new IOException("Gzip-compressed data is corrupt (CRC32 error)");
                    }
                    if (ByteUtils.fromLittleEndian((DataInput) dataInputStream, 4) != (this.inf.getBytesWritten() & 4294967295L)) {
                        throw new IOException("Gzip-compressed data is corrupt(uncompressed size mismatch)");
                    }
                    if (!this.decompressConcatenated || !init(false)) {
                        this.inf.end();
                        this.inf = null;
                        this.endReached = true;
                        if (i3 == 0) {
                            return -1;
                        }
                        return i3;
                    }
                }
            } catch (DataFormatException unused) {
                throw new IOException("Gzip-compressed data is corrupt");
            }
        }
        return i3;
    }

    public static boolean matches(byte[] bArr, int i) {
        return i >= 2 && bArr[0] == 31 && bArr[1] == -117;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Inflater inflater = this.inf;
        if (inflater != null) {
            inflater.end();
            this.inf = null;
        }
        if (this.in != System.in) {
            this.in.close();
        }
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        return this.countingStream.getBytesRead();
    }
}
