package org.apache.commons.compress.archivers.tar;

import com.google.common.base.Ascii;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.CountingOutputStream;
import org.apache.commons.compress.utils.FixedLengthBlockOutputStream;

/* JADX INFO: loaded from: classes4.dex */
public class TarArchiveOutputStream extends ArchiveOutputStream {
    private static final ZipEncoding ASCII = ZipEncodingHelper.getZipEncoding("ASCII");
    public static final int BIGNUMBER_ERROR = 0;
    public static final int BIGNUMBER_POSIX = 2;
    public static final int BIGNUMBER_STAR = 1;
    private static final int BLOCK_SIZE_UNSPECIFIED = -511;
    public static final int LONGFILE_ERROR = 0;
    public static final int LONGFILE_GNU = 2;
    public static final int LONGFILE_POSIX = 3;
    public static final int LONGFILE_TRUNCATE = 1;
    private static final int RECORD_SIZE = 512;
    private boolean addPaxHeadersForNonAsciiNames;
    private int bigNumberMode;
    private boolean closed;
    private final CountingOutputStream countingOut;
    private long currBytes;
    private String currName;
    private long currSize;
    final String encoding;
    private boolean finished;
    private boolean haveUnclosedEntry;
    private int longFileMode;
    private final FixedLengthBlockOutputStream out;
    private final byte[] recordBuf;
    private final int recordsPerBlock;
    private int recordsWritten;
    private final ZipEncoding zipEncoding;

    private boolean shouldBeReplaced(char c) {
        return c == 0 || c == '/' || c == '\\';
    }

    @Deprecated
    public int getRecordSize() {
        return 512;
    }

    public TarArchiveOutputStream(OutputStream outputStream) {
        this(outputStream, BLOCK_SIZE_UNSPECIFIED);
    }

    public TarArchiveOutputStream(OutputStream outputStream, String str) {
        this(outputStream, BLOCK_SIZE_UNSPECIFIED, str);
    }

    public TarArchiveOutputStream(OutputStream outputStream, int i) {
        this(outputStream, i, (String) null);
    }

    @Deprecated
    public TarArchiveOutputStream(OutputStream outputStream, int i, int i2) {
        this(outputStream, i, i2, null);
    }

    @Deprecated
    public TarArchiveOutputStream(OutputStream outputStream, int i, int i2, String str) {
        this(outputStream, i, str);
        if (i2 != 512) {
            throw new IllegalArgumentException("Tar record size must always be 512 bytes. Attempt to set size of " + i2);
        }
    }

    public TarArchiveOutputStream(OutputStream outputStream, int i, String str) {
        this.longFileMode = 0;
        this.bigNumberMode = 0;
        int i2 = BLOCK_SIZE_UNSPECIFIED == i ? 512 : i;
        if (i2 <= 0 || i2 % 512 != 0) {
            throw new IllegalArgumentException("Block size must be a multiple of 512 bytes. Attempt to use set size of " + i);
        }
        CountingOutputStream countingOutputStream = new CountingOutputStream(outputStream);
        this.countingOut = countingOutputStream;
        this.out = new FixedLengthBlockOutputStream(countingOutputStream, 512);
        this.encoding = str;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(str);
        this.recordBuf = new byte[512];
        this.recordsPerBlock = i2 / 512;
    }

    public void setLongFileMode(int i) {
        this.longFileMode = i;
    }

    public void setBigNumberMode(int i) {
        this.bigNumberMode = i;
    }

    public void setAddPaxHeadersForNonAsciiNames(boolean z) {
        this.addPaxHeadersForNonAsciiNames = z;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    @Deprecated
    public int getCount() {
        return (int) getBytesWritten();
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public long getBytesWritten() {
        return this.countingOut.getBytesWritten();
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public void finish() throws IOException {
        if (this.finished) {
            throw new IOException("This archive has already been finished");
        }
        if (this.haveUnclosedEntry) {
            throw new IOException("This archive contains unclosed entries.");
        }
        writeEOFRecord();
        writeEOFRecord();
        padAsNeeded();
        this.out.flush();
        this.finished = true;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            if (!this.finished) {
                finish();
            }
        } finally {
            if (!this.closed) {
                this.out.close();
                this.closed = true;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ef  */
    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void putArchiveEntry(org.apache.commons.compress.archivers.ArchiveEntry r18) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.tar.TarArchiveOutputStream.putArchiveEntry(org.apache.commons.compress.archivers.ArchiveEntry):void");
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public void closeArchiveEntry() throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        if (!this.haveUnclosedEntry) {
            throw new IOException("No current entry to close");
        }
        this.out.flushBlock();
        long j = this.currBytes;
        long j2 = this.currSize;
        if (j < j2) {
            throw new IOException("Entry '" + this.currName + "' closed at '" + this.currBytes + "' before the '" + this.currSize + "' bytes specified in the header were written");
        }
        int i = (int) (((long) this.recordsWritten) + (j2 / 512));
        this.recordsWritten = i;
        if (0 != j2 % 512) {
            this.recordsWritten = i + 1;
        }
        this.haveUnclosedEntry = false;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (!this.haveUnclosedEntry) {
            throw new IllegalStateException("No current tar entry");
        }
        long j = i2;
        if (this.currBytes + j > this.currSize) {
            throw new IOException("Request to write '" + i2 + "' bytes exceeds size in header of '" + this.currSize + "' bytes for entry '" + this.currName + "'");
        }
        this.out.write(bArr, i, i2);
        this.currBytes += j;
    }

    void writePaxHeaders(TarArchiveEntry tarArchiveEntry, String str, Map<String, String> map) throws IOException {
        String strSubstring = "./PaxHeaders.X/" + stripTo7Bits(str);
        if (strSubstring.length() >= 100) {
            strSubstring = strSubstring.substring(0, 99);
        }
        TarArchiveEntry tarArchiveEntry2 = new TarArchiveEntry(strSubstring, TarConstants.LF_PAX_EXTENDED_HEADER_LC);
        transferModTime(tarArchiveEntry, tarArchiveEntry2);
        byte[] bArrEncodeExtendedPaxHeadersContents = encodeExtendedPaxHeadersContents(map);
        tarArchiveEntry2.setSize(bArrEncodeExtendedPaxHeadersContents.length);
        putArchiveEntry(tarArchiveEntry2);
        write(bArrEncodeExtendedPaxHeadersContents);
        closeArchiveEntry();
    }

    private byte[] encodeExtendedPaxHeadersContents(Map<String, String> map) {
        StringWriter stringWriter = new StringWriter();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            int length = key.length() + value.length() + 3 + 2;
            String str = length + " " + key + "=" + value + "\n";
            int length2 = str.getBytes(StandardCharsets.UTF_8).length;
            while (length != length2) {
                str = length2 + " " + key + "=" + value + "\n";
                int i = length2;
                length2 = str.getBytes(StandardCharsets.UTF_8).length;
                length = i;
            }
            stringWriter.write(str);
        }
        return stringWriter.toString().getBytes(StandardCharsets.UTF_8);
    }

    private String stripTo7Bits(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char cCharAt = (char) (str.charAt(i) & Ascii.MAX);
            if (shouldBeReplaced(cCharAt)) {
                sb.append("_");
            } else {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    private void writeEOFRecord() throws IOException {
        Arrays.fill(this.recordBuf, (byte) 0);
        writeRecord(this.recordBuf);
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public ArchiveEntry createArchiveEntry(File file, String str) throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        return new TarArchiveEntry(file, str);
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public ArchiveEntry createArchiveEntry(Path path, String str, LinkOption... linkOptionArr) throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        return new TarArchiveEntry(path, str, linkOptionArr);
    }

    private void writeRecord(byte[] bArr) throws IOException {
        if (bArr.length != 512) {
            throw new IOException("Record to write has length '" + bArr.length + "' which is not the record size of '512'");
        }
        this.out.write(bArr);
        this.recordsWritten++;
    }

    private void padAsNeeded() throws IOException {
        int i = this.recordsWritten % this.recordsPerBlock;
        if (i != 0) {
            while (i < this.recordsPerBlock) {
                writeEOFRecord();
                i++;
            }
        }
    }

    private void addPaxHeadersForBigNumbers(Map<String, String> map, TarArchiveEntry tarArchiveEntry) {
        addPaxHeaderForBigNumber(map, "size", tarArchiveEntry.getSize(), TarConstants.MAXSIZE);
        addPaxHeaderForBigNumber(map, "gid", tarArchiveEntry.getLongGroupId(), TarConstants.MAXID);
        addPaxHeaderForBigNumber(map, "mtime", tarArchiveEntry.getModTime().getTime() / 1000, TarConstants.MAXSIZE);
        addPaxHeaderForBigNumber(map, "uid", tarArchiveEntry.getLongUserId(), TarConstants.MAXID);
        addPaxHeaderForBigNumber(map, "SCHILY.devmajor", tarArchiveEntry.getDevMajor(), TarConstants.MAXID);
        addPaxHeaderForBigNumber(map, "SCHILY.devminor", tarArchiveEntry.getDevMinor(), TarConstants.MAXID);
        failForBigNumber("mode", tarArchiveEntry.getMode(), TarConstants.MAXID);
    }

    private void addPaxHeaderForBigNumber(Map<String, String> map, String str, long j, long j2) {
        if (j < 0 || j > j2) {
            map.put(str, String.valueOf(j));
        }
    }

    private void failForBigNumbers(TarArchiveEntry tarArchiveEntry) {
        failForBigNumber("entry size", tarArchiveEntry.getSize(), TarConstants.MAXSIZE);
        failForBigNumberWithPosixMessage("group id", tarArchiveEntry.getLongGroupId(), TarConstants.MAXID);
        failForBigNumber("last modification time", tarArchiveEntry.getModTime().getTime() / 1000, TarConstants.MAXSIZE);
        failForBigNumber("user id", tarArchiveEntry.getLongUserId(), TarConstants.MAXID);
        failForBigNumber("mode", tarArchiveEntry.getMode(), TarConstants.MAXID);
        failForBigNumber("major device number", tarArchiveEntry.getDevMajor(), TarConstants.MAXID);
        failForBigNumber("minor device number", tarArchiveEntry.getDevMinor(), TarConstants.MAXID);
    }

    private void failForBigNumber(String str, long j, long j2) {
        failForBigNumber(str, j, j2, "");
    }

    private void failForBigNumberWithPosixMessage(String str, long j, long j2) {
        failForBigNumber(str, j, j2, " Use STAR or POSIX extensions to overcome this limit");
    }

    private void failForBigNumber(String str, long j, long j2, String str2) {
        if (j < 0 || j > j2) {
            throw new IllegalArgumentException(str + " '" + j + "' is too big ( > " + j2 + " )." + str2);
        }
    }

    private boolean handleLongName(TarArchiveEntry tarArchiveEntry, String str, Map<String, String> map, String str2, byte b, String str3) throws IOException {
        ByteBuffer byteBufferEncode = this.zipEncoding.encode(str);
        int iLimit = byteBufferEncode.limit() - byteBufferEncode.position();
        if (iLimit >= 100) {
            int i = this.longFileMode;
            if (i == 3) {
                map.put(str2, str);
                return true;
            }
            if (i == 2) {
                TarArchiveEntry tarArchiveEntry2 = new TarArchiveEntry(TarConstants.GNU_LONGLINK, b);
                tarArchiveEntry2.setSize(((long) iLimit) + 1);
                transferModTime(tarArchiveEntry, tarArchiveEntry2);
                putArchiveEntry(tarArchiveEntry2);
                write(byteBufferEncode.array(), byteBufferEncode.arrayOffset(), iLimit);
                write(0);
                closeArchiveEntry();
            } else if (i != 1) {
                throw new IllegalArgumentException(str3 + " '" + str + "' is too long ( > 100 bytes)");
            }
        }
        return false;
    }

    private void transferModTime(TarArchiveEntry tarArchiveEntry, TarArchiveEntry tarArchiveEntry2) {
        Date modTime = tarArchiveEntry.getModTime();
        long time = modTime.getTime() / 1000;
        if (time < 0 || time > TarConstants.MAXSIZE) {
            modTime = new Date(0L);
        }
        tarArchiveEntry2.setModTime(modTime);
    }
}
