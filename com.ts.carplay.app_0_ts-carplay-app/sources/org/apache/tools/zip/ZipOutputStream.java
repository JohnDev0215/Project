package org.apache.tools.zip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import java.util.zip.ZipException;

/* JADX INFO: loaded from: classes.dex */
public class ZipOutputStream extends FilterOutputStream {
    public static final int DEFLATED = 8;
    public static final int STORED = 0;
    protected byte[] buf;
    private ZipLong cdLength;
    private ZipLong cdOffset;
    private String comment;
    private CRC32 crc;
    private long dataStart;
    protected Deflater def;
    private String encoding;
    private Vector entries;
    private ZipEntry entry;
    private boolean hasCompressionLevelChanged;
    private int level;
    private long localDataStart;
    private int method;
    private Hashtable offsets;
    private RandomAccessFile raf;
    private long written;
    private static final byte[] ZERO = {0, 0};
    private static final byte[] LZERO = {0, 0, 0, 0};
    protected static final ZipLong LFH_SIG = new ZipLong(67324752);
    protected static final ZipLong DD_SIG = new ZipLong(134695760);
    protected static final ZipLong CFH_SIG = new ZipLong(33639248);
    protected static final ZipLong EOCD_SIG = new ZipLong(101010256);
    private static final ZipLong DOS_TIME_MIN = new ZipLong(8448);

    public ZipOutputStream(OutputStream out) {
        super(out);
        this.comment = "";
        this.level = -1;
        this.hasCompressionLevelChanged = false;
        this.method = 8;
        this.entries = new Vector();
        this.crc = new CRC32();
        this.written = 0L;
        this.dataStart = 0L;
        this.localDataStart = 0L;
        this.cdOffset = new ZipLong(0L);
        this.cdLength = new ZipLong(0L);
        this.offsets = new Hashtable();
        this.encoding = null;
        this.def = new Deflater(-1, true);
        this.buf = new byte[512];
        this.raf = null;
    }

    public ZipOutputStream(File file) throws IOException {
        super(null);
        this.comment = "";
        this.level = -1;
        this.hasCompressionLevelChanged = false;
        this.method = 8;
        this.entries = new Vector();
        this.crc = new CRC32();
        this.written = 0L;
        this.dataStart = 0L;
        this.localDataStart = 0L;
        this.cdOffset = new ZipLong(0L);
        this.cdLength = new ZipLong(0L);
        this.offsets = new Hashtable();
        this.encoding = null;
        this.def = new Deflater(-1, true);
        this.buf = new byte[512];
        this.raf = null;
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            this.raf = randomAccessFile;
            randomAccessFile.setLength(0L);
        } catch (IOException e) {
            RandomAccessFile randomAccessFile2 = this.raf;
            if (randomAccessFile2 != null) {
                try {
                    randomAccessFile2.close();
                } catch (IOException e2) {
                }
                this.raf = null;
            }
            this.out = new FileOutputStream(file);
        }
    }

    public boolean isSeekable() {
        return this.raf != null;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void finish() throws IOException {
        closeEntry();
        this.cdOffset = new ZipLong(this.written);
        for (int i = 0; i < this.entries.size(); i++) {
            writeCentralFileHeader((ZipEntry) this.entries.elementAt(i));
        }
        this.cdLength = new ZipLong(this.written - this.cdOffset.getValue());
        writeCentralDirectoryEnd();
        this.offsets.clear();
        this.entries.removeAllElements();
    }

    public void closeEntry() throws IOException {
        if (this.entry == null) {
            return;
        }
        long realCrc = this.crc.getValue();
        this.crc.reset();
        if (this.entry.getMethod() == 8) {
            this.def.finish();
            while (!this.def.finished()) {
                deflate();
            }
            this.entry.setSize(adjustToLong(this.def.getTotalIn()));
            this.entry.setComprSize(adjustToLong(this.def.getTotalOut()));
            this.entry.setCrc(realCrc);
            this.def.reset();
            this.written += this.entry.getCompressedSize();
        } else if (this.raf != null) {
            long size = this.written - this.dataStart;
            this.entry.setSize(size);
            this.entry.setComprSize(size);
            this.entry.setCrc(realCrc);
        } else {
            if (this.entry.getCrc() != realCrc) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("bad CRC checksum for entry ");
                stringBuffer.append(this.entry.getName());
                stringBuffer.append(": ");
                stringBuffer.append(Long.toHexString(this.entry.getCrc()));
                stringBuffer.append(" instead of ");
                stringBuffer.append(Long.toHexString(realCrc));
                throw new ZipException(stringBuffer.toString());
            }
            if (this.entry.getSize() != this.written - this.dataStart) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("bad size for entry ");
                stringBuffer2.append(this.entry.getName());
                stringBuffer2.append(": ");
                stringBuffer2.append(this.entry.getSize());
                stringBuffer2.append(" instead of ");
                stringBuffer2.append(this.written - this.dataStart);
                throw new ZipException(stringBuffer2.toString());
            }
        }
        RandomAccessFile randomAccessFile = this.raf;
        if (randomAccessFile != null) {
            long save = randomAccessFile.getFilePointer();
            this.raf.seek(this.localDataStart);
            writeOut(new ZipLong(this.entry.getCrc()).getBytes());
            writeOut(new ZipLong(this.entry.getCompressedSize()).getBytes());
            writeOut(new ZipLong(this.entry.getSize()).getBytes());
            this.raf.seek(save);
        }
        writeDataDescriptor(this.entry);
        this.entry = null;
    }

    public void putNextEntry(ZipEntry ze) throws IOException {
        closeEntry();
        this.entry = ze;
        this.entries.addElement(ze);
        if (this.entry.getMethod() == -1) {
            this.entry.setMethod(this.method);
        }
        if (this.entry.getTime() == -1) {
            this.entry.setTime(System.currentTimeMillis());
        }
        if (this.entry.getMethod() == 0 && this.raf == null) {
            if (this.entry.getSize() == -1) {
                throw new ZipException("uncompressed size is required for STORED method when not writing to a file");
            }
            if (this.entry.getCrc() == -1) {
                throw new ZipException("crc checksum is required for STORED method when not writing to a file");
            }
            ZipEntry zipEntry = this.entry;
            zipEntry.setComprSize(zipEntry.getSize());
        }
        if (this.entry.getMethod() == 8 && this.hasCompressionLevelChanged) {
            this.def.setLevel(this.level);
            this.hasCompressionLevelChanged = false;
        }
        writeLocalFileHeader(this.entry);
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setLevel(int level) {
        this.hasCompressionLevelChanged = this.level != level;
        this.level = level;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] b, int offset, int length) throws IOException {
        if (this.entry.getMethod() == 8) {
            if (length > 0 && !this.def.finished()) {
                this.def.setInput(b, offset, length);
                while (!this.def.needsInput()) {
                    deflate();
                }
            }
        } else {
            writeOut(b, offset, length);
            this.written += (long) length;
        }
        this.crc.update(b, offset, length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int b) throws IOException {
        byte[] buf = {(byte) (b & 255)};
        write(buf, 0, 1);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        finish();
        RandomAccessFile randomAccessFile = this.raf;
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
        if (this.out != null) {
            this.out.close();
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        if (this.out != null) {
            this.out.flush();
        }
    }

    protected final void deflate() throws IOException {
        Deflater deflater = this.def;
        byte[] bArr = this.buf;
        int len = deflater.deflate(bArr, 0, bArr.length);
        if (len > 0) {
            writeOut(this.buf, 0, len);
        }
    }

    protected void writeLocalFileHeader(ZipEntry ze) throws IOException {
        this.offsets.put(ze, new ZipLong(this.written));
        writeOut(LFH_SIG.getBytes());
        this.written += 4;
        if (ze.getMethod() == 8 && this.raf == null) {
            writeOut(new ZipShort(20).getBytes());
            writeOut(new ZipShort(8).getBytes());
        } else {
            writeOut(new ZipShort(10).getBytes());
            writeOut(ZERO);
        }
        this.written += 4;
        writeOut(new ZipShort(ze.getMethod()).getBytes());
        this.written += 2;
        writeOut(toDosTime(new Date(ze.getTime())).getBytes());
        long j = this.written + 4;
        this.written = j;
        this.localDataStart = j;
        if (ze.getMethod() == 8 || this.raf != null) {
            writeOut(LZERO);
            writeOut(LZERO);
            writeOut(LZERO);
        } else {
            writeOut(new ZipLong(ze.getCrc()).getBytes());
            writeOut(new ZipLong(ze.getSize()).getBytes());
            writeOut(new ZipLong(ze.getSize()).getBytes());
        }
        this.written += 12;
        byte[] name = getBytes(ze.getName());
        writeOut(new ZipShort(name.length).getBytes());
        this.written += 2;
        byte[] extra = ze.getLocalFileDataExtra();
        writeOut(new ZipShort(extra.length).getBytes());
        this.written += 2;
        writeOut(name);
        this.written += (long) name.length;
        writeOut(extra);
        long length = this.written + ((long) extra.length);
        this.written = length;
        this.dataStart = length;
    }

    protected void writeDataDescriptor(ZipEntry ze) throws IOException {
        if (ze.getMethod() != 8 || this.raf != null) {
            return;
        }
        writeOut(DD_SIG.getBytes());
        writeOut(new ZipLong(this.entry.getCrc()).getBytes());
        writeOut(new ZipLong(this.entry.getCompressedSize()).getBytes());
        writeOut(new ZipLong(this.entry.getSize()).getBytes());
        this.written += 16;
    }

    protected void writeCentralFileHeader(ZipEntry ze) throws IOException {
        writeOut(CFH_SIG.getBytes());
        this.written += 4;
        writeOut(new ZipShort((ze.getPlatform() << 8) | 20).getBytes());
        this.written += 2;
        if (ze.getMethod() == 8 && this.raf == null) {
            writeOut(new ZipShort(20).getBytes());
            writeOut(new ZipShort(8).getBytes());
        } else {
            writeOut(new ZipShort(10).getBytes());
            writeOut(ZERO);
        }
        this.written += 4;
        writeOut(new ZipShort(ze.getMethod()).getBytes());
        this.written += 2;
        writeOut(toDosTime(new Date(ze.getTime())).getBytes());
        this.written += 4;
        writeOut(new ZipLong(ze.getCrc()).getBytes());
        writeOut(new ZipLong(ze.getCompressedSize()).getBytes());
        writeOut(new ZipLong(ze.getSize()).getBytes());
        this.written += 12;
        byte[] name = getBytes(ze.getName());
        writeOut(new ZipShort(name.length).getBytes());
        this.written += 2;
        byte[] extra = ze.getCentralDirectoryExtra();
        writeOut(new ZipShort(extra.length).getBytes());
        this.written += 2;
        String comm = ze.getComment();
        if (comm == null) {
            comm = "";
        }
        byte[] comment = getBytes(comm);
        writeOut(new ZipShort(comment.length).getBytes());
        this.written += 2;
        writeOut(ZERO);
        this.written += 2;
        writeOut(new ZipShort(ze.getInternalAttributes()).getBytes());
        this.written += 2;
        writeOut(new ZipLong(ze.getExternalAttributes()).getBytes());
        this.written += 4;
        writeOut(((ZipLong) this.offsets.get(ze)).getBytes());
        this.written += 4;
        writeOut(name);
        this.written += (long) name.length;
        writeOut(extra);
        this.written += (long) extra.length;
        writeOut(comment);
        this.written += (long) comment.length;
    }

    protected void writeCentralDirectoryEnd() throws IOException {
        writeOut(EOCD_SIG.getBytes());
        writeOut(ZERO);
        writeOut(ZERO);
        byte[] num = new ZipShort(this.entries.size()).getBytes();
        writeOut(num);
        writeOut(num);
        writeOut(this.cdLength.getBytes());
        writeOut(this.cdOffset.getBytes());
        byte[] data = getBytes(this.comment);
        writeOut(new ZipShort(data.length).getBytes());
        writeOut(data);
    }

    protected static ZipLong toDosTime(Date time) {
        int year = time.getYear() + 1900;
        int month = time.getMonth() + 1;
        if (year < 1980) {
            return DOS_TIME_MIN;
        }
        long value = ((year - 1980) << 25) | (month << 21) | (time.getDate() << 16) | (time.getHours() << 11) | (time.getMinutes() << 5) | (time.getSeconds() >> 1);
        byte[] result = {(byte) (255 & value), (byte) ((65280 & value) >> 8), (byte) ((16711680 & value) >> 16), (byte) ((4278190080L & value) >> 24)};
        return new ZipLong(result);
    }

    protected byte[] getBytes(String name) throws ZipException {
        String str = this.encoding;
        if (str == null) {
            return name.getBytes();
        }
        try {
            return name.getBytes(str);
        } catch (UnsupportedEncodingException uee) {
            throw new ZipException(uee.getMessage());
        }
    }

    protected final void writeOut(byte[] data) throws IOException {
        writeOut(data, 0, data.length);
    }

    protected final void writeOut(byte[] data, int offset, int length) throws IOException {
        RandomAccessFile randomAccessFile = this.raf;
        if (randomAccessFile != null) {
            randomAccessFile.write(data, offset, length);
        } else {
            this.out.write(data, offset, length);
        }
    }

    protected static long adjustToLong(int i) {
        if (i < 0) {
            return ((long) i) + 4294967296L;
        }
        return i;
    }
}
