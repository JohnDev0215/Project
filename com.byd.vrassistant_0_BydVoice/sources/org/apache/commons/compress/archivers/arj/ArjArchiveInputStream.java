package org.apache.commons.compress.archivers.arj;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.CRC32;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.utils.BoundedInputStream;
import org.apache.commons.compress.utils.CRC32VerifyingInputStream;
import org.apache.commons.compress.utils.IOUtils;

/* JADX INFO: loaded from: classes4.dex */
public class ArjArchiveInputStream extends ArchiveInputStream {
    private static final int ARJ_MAGIC_1 = 96;
    private static final int ARJ_MAGIC_2 = 234;
    private final String charsetName;
    private InputStream currentInputStream;
    private LocalFileHeader currentLocalFileHeader;
    private final DataInputStream in;
    private final MainHeader mainHeader;

    public ArjArchiveInputStream(InputStream inputStream, String str) throws ArchiveException {
        this.in = new DataInputStream(inputStream);
        this.charsetName = str;
        try {
            MainHeader mainHeader = readMainHeader();
            this.mainHeader = mainHeader;
            if ((mainHeader.arjFlags & 1) != 0) {
                throw new ArchiveException("Encrypted ARJ files are unsupported");
            }
            if ((mainHeader.arjFlags & 4) != 0) {
                throw new ArchiveException("Multi-volume ARJ files are unsupported");
            }
        } catch (IOException e) {
            throw new ArchiveException(e.getMessage(), e);
        }
    }

    public ArjArchiveInputStream(InputStream inputStream) throws ArchiveException {
        this(inputStream, "CP437");
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }

    private int read8(DataInputStream dataInputStream) throws IOException {
        int unsignedByte = dataInputStream.readUnsignedByte();
        count(1);
        return unsignedByte;
    }

    private int read16(DataInputStream dataInputStream) throws IOException {
        int unsignedShort = dataInputStream.readUnsignedShort();
        count(2);
        return Integer.reverseBytes(unsignedShort) >>> 16;
    }

    private int read32(DataInputStream dataInputStream) throws IOException {
        int i = dataInputStream.readInt();
        count(4);
        return Integer.reverseBytes(i);
    }

    private String readString(DataInputStream dataInputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                int unsignedByte = dataInputStream.readUnsignedByte();
                if (unsignedByte == 0) {
                    break;
                }
                byteArrayOutputStream.write(unsignedByte);
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
        String str = this.charsetName;
        if (str != null) {
            String string = byteArrayOutputStream.toString(str);
            byteArrayOutputStream.close();
            return string;
        }
        String string2 = byteArrayOutputStream.toString();
        byteArrayOutputStream.close();
        return string2;
    }

    private byte[] readRange(InputStream inputStream, int i) throws IOException {
        byte[] range = IOUtils.readRange(inputStream, i);
        count(range.length);
        if (range.length >= i) {
            return range;
        }
        throw new EOFException();
    }

    private byte[] readHeader() throws IOException {
        boolean z = false;
        byte[] range = null;
        do {
            int i = read8(this.in);
            while (true) {
                int i2 = read8(this.in);
                if (i == 96 || i2 == 234) {
                    break;
                }
                i = i2;
            }
            int i3 = read16(this.in);
            if (i3 == 0) {
                return null;
            }
            if (i3 <= 2600) {
                range = readRange(this.in, i3);
                long j = ((long) read32(this.in)) & 4294967295L;
                CRC32 crc32 = new CRC32();
                crc32.update(range);
                if (j == crc32.getValue()) {
                    z = true;
                }
            }
        } while (!z);
        return range;
    }

    private MainHeader readMainHeader() throws IOException {
        byte[] header = readHeader();
        if (header == null) {
            throw new IOException("Archive ends without any headers");
        }
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(header));
        int unsignedByte = dataInputStream.readUnsignedByte();
        byte[] range = readRange(dataInputStream, unsignedByte - 1);
        pushedBackBytes(range.length);
        DataInputStream dataInputStream2 = new DataInputStream(new ByteArrayInputStream(range));
        MainHeader mainHeader = new MainHeader();
        mainHeader.archiverVersionNumber = dataInputStream2.readUnsignedByte();
        mainHeader.minVersionToExtract = dataInputStream2.readUnsignedByte();
        mainHeader.hostOS = dataInputStream2.readUnsignedByte();
        mainHeader.arjFlags = dataInputStream2.readUnsignedByte();
        mainHeader.securityVersion = dataInputStream2.readUnsignedByte();
        mainHeader.fileType = dataInputStream2.readUnsignedByte();
        mainHeader.reserved = dataInputStream2.readUnsignedByte();
        mainHeader.dateTimeCreated = read32(dataInputStream2);
        mainHeader.dateTimeModified = read32(dataInputStream2);
        mainHeader.archiveSize = ((long) read32(dataInputStream2)) & 4294967295L;
        mainHeader.securityEnvelopeFilePosition = read32(dataInputStream2);
        mainHeader.fileSpecPosition = read16(dataInputStream2);
        mainHeader.securityEnvelopeLength = read16(dataInputStream2);
        pushedBackBytes(20L);
        mainHeader.encryptionVersion = dataInputStream2.readUnsignedByte();
        mainHeader.lastChapter = dataInputStream2.readUnsignedByte();
        if (unsignedByte >= 33) {
            mainHeader.arjProtectionFactor = dataInputStream2.readUnsignedByte();
            mainHeader.arjFlags2 = dataInputStream2.readUnsignedByte();
            dataInputStream2.readUnsignedByte();
            dataInputStream2.readUnsignedByte();
        }
        mainHeader.name = readString(dataInputStream);
        mainHeader.comment = readString(dataInputStream);
        int i = read16(this.in);
        if (i > 0) {
            mainHeader.extendedHeaderBytes = readRange(this.in, i);
            long j = ((long) read32(this.in)) & 4294967295L;
            CRC32 crc32 = new CRC32();
            crc32.update(mainHeader.extendedHeaderBytes);
            if (j != crc32.getValue()) {
                throw new IOException("Extended header CRC32 verification failure");
            }
        }
        return mainHeader;
    }

    private LocalFileHeader readLocalFileHeader() throws IOException {
        byte[] header = readHeader();
        if (header == null) {
            return null;
        }
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(header));
        try {
            int unsignedByte = dataInputStream.readUnsignedByte();
            byte[] range = readRange(dataInputStream, unsignedByte - 1);
            pushedBackBytes(range.length);
            DataInputStream dataInputStream2 = new DataInputStream(new ByteArrayInputStream(range));
            try {
                LocalFileHeader localFileHeader = new LocalFileHeader();
                localFileHeader.archiverVersionNumber = dataInputStream2.readUnsignedByte();
                localFileHeader.minVersionToExtract = dataInputStream2.readUnsignedByte();
                localFileHeader.hostOS = dataInputStream2.readUnsignedByte();
                localFileHeader.arjFlags = dataInputStream2.readUnsignedByte();
                localFileHeader.method = dataInputStream2.readUnsignedByte();
                localFileHeader.fileType = dataInputStream2.readUnsignedByte();
                localFileHeader.reserved = dataInputStream2.readUnsignedByte();
                localFileHeader.dateTimeModified = read32(dataInputStream2);
                localFileHeader.compressedSize = ((long) read32(dataInputStream2)) & 4294967295L;
                localFileHeader.originalSize = ((long) read32(dataInputStream2)) & 4294967295L;
                localFileHeader.originalCrc32 = ((long) read32(dataInputStream2)) & 4294967295L;
                localFileHeader.fileSpecPosition = read16(dataInputStream2);
                localFileHeader.fileAccessMode = read16(dataInputStream2);
                pushedBackBytes(20L);
                localFileHeader.firstChapter = dataInputStream2.readUnsignedByte();
                localFileHeader.lastChapter = dataInputStream2.readUnsignedByte();
                readExtraData(unsignedByte, dataInputStream2, localFileHeader);
                localFileHeader.name = readString(dataInputStream);
                localFileHeader.comment = readString(dataInputStream);
                ArrayList arrayList = new ArrayList();
                while (true) {
                    int i = read16(this.in);
                    if (i > 0) {
                        byte[] range2 = readRange(this.in, i);
                        long j = ((long) read32(this.in)) & 4294967295L;
                        CRC32 crc32 = new CRC32();
                        crc32.update(range2);
                        if (j != crc32.getValue()) {
                            throw new IOException("Extended header CRC32 verification failure");
                        }
                        arrayList.add(range2);
                    } else {
                        localFileHeader.extendedHeaders = (byte[][]) arrayList.toArray(new byte[0][]);
                        dataInputStream2.close();
                        dataInputStream.close();
                        return localFileHeader;
                    }
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    dataInputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    private void readExtraData(int i, DataInputStream dataInputStream, LocalFileHeader localFileHeader) throws IOException {
        if (i >= 33) {
            localFileHeader.extendedFilePosition = read32(dataInputStream);
            if (i >= 45) {
                localFileHeader.dateTimeAccessed = read32(dataInputStream);
                localFileHeader.dateTimeCreated = read32(dataInputStream);
                localFileHeader.originalSizeEvenForVolumes = read32(dataInputStream);
                pushedBackBytes(12L);
            }
            pushedBackBytes(4L);
        }
    }

    public static boolean matches(byte[] bArr, int i) {
        return i >= 2 && (bArr[0] & 255) == 96 && (bArr[1] & 255) == 234;
    }

    public String getArchiveName() {
        return this.mainHeader.name;
    }

    public String getArchiveComment() {
        return this.mainHeader.comment;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public ArjArchiveEntry getNextEntry() throws IOException {
        InputStream inputStream = this.currentInputStream;
        if (inputStream != null) {
            IOUtils.skip(inputStream, Long.MAX_VALUE);
            this.currentInputStream.close();
            this.currentLocalFileHeader = null;
            this.currentInputStream = null;
        }
        LocalFileHeader localFileHeader = readLocalFileHeader();
        this.currentLocalFileHeader = localFileHeader;
        if (localFileHeader != null) {
            this.currentInputStream = new BoundedInputStream(this.in, this.currentLocalFileHeader.compressedSize);
            if (this.currentLocalFileHeader.method == 0) {
                this.currentInputStream = new CRC32VerifyingInputStream(this.currentInputStream, this.currentLocalFileHeader.originalSize, this.currentLocalFileHeader.originalCrc32);
            }
            return new ArjArchiveEntry(this.currentLocalFileHeader);
        }
        this.currentInputStream = null;
        return null;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public boolean canReadEntryData(ArchiveEntry archiveEntry) {
        return (archiveEntry instanceof ArjArchiveEntry) && ((ArjArchiveEntry) archiveEntry).getMethod() == 0;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        LocalFileHeader localFileHeader = this.currentLocalFileHeader;
        if (localFileHeader == null) {
            throw new IllegalStateException("No current arj entry");
        }
        if (localFileHeader.method != 0) {
            throw new IOException("Unsupported compression method " + this.currentLocalFileHeader.method);
        }
        return this.currentInputStream.read(bArr, i, i2);
    }
}
