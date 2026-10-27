package org.apache.commons.compress.archivers.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;

/* JADX INFO: loaded from: classes4.dex */
public class ZipArchiveInputStream extends ArchiveInputStream implements InputStreamStatistics {
    private static final int CFH_LEN = 46;
    private static final int LFH_LEN = 30;
    private static final long TWO_EXP_32 = 4294967296L;
    private static final String USE_ZIPFILE_INSTEAD_OF_STREAM_DISCLAIMER = " while reading a stored entry using data descriptor. Either the archive is broken or it can not be read using ZipArchiveInputStream and you must use ZipFile. A common cause for this is a ZIP archive containing a ZIP archive. See http://commons.apache.org/proper/commons-compress/zip.html#ZipArchiveInputStream_vs_ZipFile";
    private boolean allowStoredEntriesWithDataDescriptor;
    private final ByteBuffer buf;
    private boolean closed;
    private CurrentEntry current;
    final String encoding;
    private int entriesRead;
    private boolean hitCentralDirectory;
    private final InputStream in;
    private final Inflater inf;
    private ByteArrayInputStream lastStoredEntry;
    private final byte[] lfhBuf;
    private final byte[] shortBuf;
    private final byte[] skipBuf;
    private final boolean skipSplitSig;
    private final byte[] twoDwordBuf;
    private long uncompressedCount;
    private final boolean useUnicodeExtraFields;
    private final byte[] wordBuf;
    private final ZipEncoding zipEncoding;
    private static final byte[] LFH = ZipLong.LFH_SIG.getBytes();
    private static final byte[] CFH = ZipLong.CFH_SIG.getBytes();
    private static final byte[] DD = ZipLong.DD_SIG.getBytes();
    private static final byte[] APK_SIGNING_BLOCK_MAGIC = {65, 80, TarConstants.LF_GNUTYPE_LONGLINK, 32, TarConstants.LF_GNUTYPE_SPARSE, 105, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 32, 66, 108, 111, 99, 107, 32, TarConstants.LF_BLK, TarConstants.LF_SYMLINK};
    private static final BigInteger LONG_MAX = BigInteger.valueOf(Long.MAX_VALUE);

    public ZipArchiveInputStream(InputStream inputStream) {
        this(inputStream, "UTF8");
    }

    public ZipArchiveInputStream(InputStream inputStream, String str) {
        this(inputStream, str, true);
    }

    public ZipArchiveInputStream(InputStream inputStream, String str, boolean z) {
        this(inputStream, str, z, false);
    }

    public ZipArchiveInputStream(InputStream inputStream, String str, boolean z, boolean z2) {
        this(inputStream, str, z, z2, false);
    }

    public ZipArchiveInputStream(InputStream inputStream, String str, boolean z, boolean z2, boolean z3) {
        this.inf = new Inflater(true);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(512);
        this.buf = byteBufferAllocate;
        this.lfhBuf = new byte[30];
        this.skipBuf = new byte[1024];
        this.shortBuf = new byte[2];
        this.wordBuf = new byte[4];
        this.twoDwordBuf = new byte[16];
        this.encoding = str;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(str);
        this.useUnicodeExtraFields = z;
        this.in = new PushbackInputStream(inputStream, byteBufferAllocate.capacity());
        this.allowStoredEntriesWithDataDescriptor = z2;
        this.skipSplitSig = z3;
        byteBufferAllocate.limit(0);
    }

    public ZipArchiveEntry getNextZipEntry() throws IOException {
        boolean z;
        ZipLong zipLong;
        ZipLong zipLong2;
        this.uncompressedCount = 0L;
        AnonymousClass1 anonymousClass1 = null;
        if (!this.closed && !this.hitCentralDirectory) {
            if (this.current != null) {
                closeEntry();
                z = false;
            } else {
                z = true;
            }
            long bytesRead = getBytesRead();
            try {
                if (z) {
                    readFirstLocalFileHeader();
                } else {
                    readFully(this.lfhBuf);
                }
                ZipLong zipLong3 = new ZipLong(this.lfhBuf);
                if (!zipLong3.equals(ZipLong.LFH_SIG)) {
                    if (zipLong3.equals(ZipLong.CFH_SIG) || zipLong3.equals(ZipLong.AED_SIG) || isApkSigningBlock(this.lfhBuf)) {
                        this.hitCentralDirectory = true;
                        skipRemainderOfArchive();
                        return null;
                    }
                    throw new ZipException(String.format("Unexpected record signature: 0X%X", Long.valueOf(zipLong3.getValue())));
                }
                this.current = new CurrentEntry(anonymousClass1);
                this.current.entry.setPlatform((ZipShort.getValue(this.lfhBuf, 4) >> 8) & 15);
                GeneralPurposeBit generalPurposeBit = GeneralPurposeBit.parse(this.lfhBuf, 6);
                boolean zUsesUTF8ForNames = generalPurposeBit.usesUTF8ForNames();
                ZipEncoding zipEncoding = zUsesUTF8ForNames ? ZipEncodingHelper.UTF8_ZIP_ENCODING : this.zipEncoding;
                this.current.hasDataDescriptor = generalPurposeBit.usesDataDescriptor();
                this.current.entry.setGeneralPurposeBit(generalPurposeBit);
                this.current.entry.setMethod(ZipShort.getValue(this.lfhBuf, 8));
                this.current.entry.setTime(ZipUtil.dosToJavaTime(ZipLong.getValue(this.lfhBuf, 10)));
                if (this.current.hasDataDescriptor) {
                    zipLong = null;
                    zipLong2 = null;
                } else {
                    this.current.entry.setCrc(ZipLong.getValue(this.lfhBuf, 14));
                    zipLong = new ZipLong(this.lfhBuf, 18);
                    zipLong2 = new ZipLong(this.lfhBuf, 22);
                }
                int value = ZipShort.getValue(this.lfhBuf, 26);
                int value2 = ZipShort.getValue(this.lfhBuf, 28);
                byte[] range = readRange(value);
                this.current.entry.setName(zipEncoding.decode(range), range);
                if (zUsesUTF8ForNames) {
                    this.current.entry.setNameSource(ZipArchiveEntry.NameSource.NAME_WITH_EFS_FLAG);
                }
                try {
                    this.current.entry.setExtra(readRange(value2));
                    if (!zUsesUTF8ForNames && this.useUnicodeExtraFields) {
                        ZipUtil.setNameAndCommentFromExtraFields(this.current.entry, range, null);
                    }
                    processZip64Extra(zipLong2, zipLong);
                    this.current.entry.setLocalHeaderOffset(bytesRead);
                    this.current.entry.setDataOffset(getBytesRead());
                    this.current.entry.setStreamContiguous(true);
                    ZipMethod methodByCode = ZipMethod.getMethodByCode(this.current.entry.getMethod());
                    if (this.current.entry.getCompressedSize() == -1) {
                        if (methodByCode == ZipMethod.ENHANCED_DEFLATED) {
                            this.current.in = new Deflate64CompressorInputStream(this.in);
                        }
                    } else if (ZipUtil.canHandleEntryData(this.current.entry) && methodByCode != ZipMethod.STORED && methodByCode != ZipMethod.DEFLATED) {
                        BoundedInputStream boundedInputStream = new BoundedInputStream(this.in, this.current.entry.getCompressedSize());
                        int i = AnonymousClass1.$SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[methodByCode.ordinal()];
                        if (i == 1) {
                            this.current.in = new UnshrinkingInputStream(boundedInputStream);
                        } else if (i == 2) {
                            try {
                                this.current.in = new ExplodingInputStream(this.current.entry.getGeneralPurposeBit().getSlidingDictionarySize(), this.current.entry.getGeneralPurposeBit().getNumberOfShannonFanoTrees(), boundedInputStream);
                            } catch (IllegalArgumentException e) {
                                throw new IOException("bad IMPLODE data", e);
                            }
                        } else if (i == 3) {
                            this.current.in = new BZip2CompressorInputStream(boundedInputStream);
                        } else if (i == 4) {
                            this.current.in = new Deflate64CompressorInputStream(boundedInputStream);
                        }
                    }
                    this.entriesRead++;
                    return this.current.entry;
                } catch (RuntimeException e2) {
                    ZipException zipException = new ZipException("Invalid extra data in entry " + this.current.entry.getName());
                    zipException.initCause(e2);
                    throw zipException;
                }
            } catch (EOFException unused) {
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: org.apache.commons.compress.archivers.zip.ZipArchiveInputStream$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod;

        static {
            int[] iArr = new int[ZipMethod.values().length];
            $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod = iArr;
            try {
                iArr[ZipMethod.UNSHRINKING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.IMPLODING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.BZIP2.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.ENHANCED_DEFLATED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void readFirstLocalFileHeader() throws IOException {
        readFully(this.lfhBuf);
        ZipLong zipLong = new ZipLong(this.lfhBuf);
        if (!this.skipSplitSig && zipLong.equals(ZipLong.DD_SIG)) {
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.SPLITTING);
        }
        if (zipLong.equals(ZipLong.SINGLE_SEGMENT_SPLIT_MARKER) || zipLong.equals(ZipLong.DD_SIG)) {
            byte[] bArr = new byte[4];
            readFully(bArr);
            byte[] bArr2 = this.lfhBuf;
            System.arraycopy(bArr2, 4, bArr2, 0, 26);
            System.arraycopy(bArr, 0, this.lfhBuf, 26, 4);
        }
    }

    private void processZip64Extra(ZipLong zipLong, ZipLong zipLong2) throws ZipException {
        ZipExtraField extraField = this.current.entry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
        if (extraField != null && !(extraField instanceof Zip64ExtendedInformationExtraField)) {
            throw new ZipException("archive contains unparseable zip64 extra field");
        }
        Zip64ExtendedInformationExtraField zip64ExtendedInformationExtraField = (Zip64ExtendedInformationExtraField) extraField;
        this.current.usesZip64 = zip64ExtendedInformationExtraField != null;
        if (this.current.hasDataDescriptor) {
            return;
        }
        if (zip64ExtendedInformationExtraField == null || !(ZipLong.ZIP64_MAGIC.equals(zipLong2) || ZipLong.ZIP64_MAGIC.equals(zipLong))) {
            if (zipLong2 == null || zipLong == null) {
                return;
            }
            if (zipLong2.getValue() < 0) {
                throw new ZipException("broken archive, entry with negative compressed size");
            }
            this.current.entry.setCompressedSize(zipLong2.getValue());
            if (zipLong.getValue() < 0) {
                throw new ZipException("broken archive, entry with negative size");
            }
            this.current.entry.setSize(zipLong.getValue());
            return;
        }
        if (zip64ExtendedInformationExtraField.getCompressedSize() == null || zip64ExtendedInformationExtraField.getSize() == null) {
            throw new ZipException("archive contains corrupted zip64 extra field");
        }
        long longValue = zip64ExtendedInformationExtraField.getCompressedSize().getLongValue();
        if (longValue < 0) {
            throw new ZipException("broken archive, entry with negative compressed size");
        }
        this.current.entry.setCompressedSize(longValue);
        long longValue2 = zip64ExtendedInformationExtraField.getSize().getLongValue();
        if (longValue2 < 0) {
            throw new ZipException("broken archive, entry with negative size");
        }
        this.current.entry.setSize(longValue2);
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public ArchiveEntry getNextEntry() throws IOException {
        return getNextZipEntry();
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public boolean canReadEntryData(ArchiveEntry archiveEntry) {
        if (!(archiveEntry instanceof ZipArchiveEntry)) {
            return false;
        }
        ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry) archiveEntry;
        return ZipUtil.canHandleEntryData(zipArchiveEntry) && supportsDataDescriptorFor(zipArchiveEntry) && supportsCompressedSizeFor(zipArchiveEntry);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int deflated;
        if (i2 == 0) {
            return 0;
        }
        if (this.closed) {
            throw new IOException("The stream is closed");
        }
        CurrentEntry currentEntry = this.current;
        if (currentEntry == null) {
            return -1;
        }
        if (i > bArr.length || i2 < 0 || i < 0 || bArr.length - i < i2) {
            throw new ArrayIndexOutOfBoundsException();
        }
        ZipUtil.checkRequestedFeatures(currentEntry.entry);
        if (!supportsDataDescriptorFor(this.current.entry)) {
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.DATA_DESCRIPTOR, this.current.entry);
        }
        if (!supportsCompressedSizeFor(this.current.entry)) {
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.UNKNOWN_COMPRESSED_SIZE, this.current.entry);
        }
        if (this.current.entry.getMethod() == 0) {
            deflated = readStored(bArr, i, i2);
        } else if (this.current.entry.getMethod() == 8) {
            deflated = readDeflated(bArr, i, i2);
        } else {
            if (this.current.entry.getMethod() != ZipMethod.UNSHRINKING.getCode() && this.current.entry.getMethod() != ZipMethod.IMPLODING.getCode() && this.current.entry.getMethod() != ZipMethod.ENHANCED_DEFLATED.getCode() && this.current.entry.getMethod() != ZipMethod.BZIP2.getCode()) {
                throw new UnsupportedZipFeatureException(ZipMethod.getMethodByCode(this.current.entry.getMethod()), this.current.entry);
            }
            deflated = this.current.in.read(bArr, i, i2);
        }
        if (deflated >= 0) {
            this.current.crc.update(bArr, i, deflated);
            this.uncompressedCount += (long) deflated;
        }
        return deflated;
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        if (this.current.entry.getMethod() == 0) {
            return this.current.bytesRead;
        }
        if (this.current.entry.getMethod() == 8) {
            return getBytesInflated();
        }
        if (this.current.entry.getMethod() == ZipMethod.UNSHRINKING.getCode()) {
            return ((UnshrinkingInputStream) this.current.in).getCompressedCount();
        }
        if (this.current.entry.getMethod() == ZipMethod.IMPLODING.getCode()) {
            return ((ExplodingInputStream) this.current.in).getCompressedCount();
        }
        if (this.current.entry.getMethod() == ZipMethod.ENHANCED_DEFLATED.getCode()) {
            return ((Deflate64CompressorInputStream) this.current.in).getCompressedCount();
        }
        if (this.current.entry.getMethod() == ZipMethod.BZIP2.getCode()) {
            return ((BZip2CompressorInputStream) this.current.in).getCompressedCount();
        }
        return -1L;
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getUncompressedCount() {
        return this.uncompressedCount;
    }

    private int readStored(byte[] bArr, int i, int i2) throws IOException {
        if (this.current.hasDataDescriptor) {
            if (this.lastStoredEntry == null) {
                readStoredEntry();
            }
            return this.lastStoredEntry.read(bArr, i, i2);
        }
        long size = this.current.entry.getSize();
        if (this.current.bytesRead >= size) {
            return -1;
        }
        if (this.buf.position() >= this.buf.limit()) {
            this.buf.position(0);
            int i3 = this.in.read(this.buf.array());
            if (i3 == -1) {
                this.buf.limit(0);
                throw new IOException("Truncated ZIP file");
            }
            this.buf.limit(i3);
            count(i3);
            this.current.bytesReadFromStream += (long) i3;
        }
        int iMin = Math.min(this.buf.remaining(), i2);
        if (size - this.current.bytesRead < iMin) {
            iMin = (int) (size - this.current.bytesRead);
        }
        this.buf.get(bArr, i, iMin);
        this.current.bytesRead += (long) iMin;
        return iMin;
    }

    private int readDeflated(byte[] bArr, int i, int i2) throws IOException {
        int fromInflater = readFromInflater(bArr, i, i2);
        if (fromInflater <= 0) {
            if (this.inf.finished()) {
                return -1;
            }
            if (this.inf.needsDictionary()) {
                throw new ZipException("This archive needs a preset dictionary which is not supported by Commons Compress.");
            }
            if (fromInflater == -1) {
                throw new IOException("Truncated ZIP file");
            }
        }
        return fromInflater;
    }

    private int readFromInflater(byte[] bArr, int i, int i2) throws IOException {
        int iInflate = 0;
        while (true) {
            if (this.inf.needsInput()) {
                int iFill = fill();
                if (iFill > 0) {
                    this.current.bytesReadFromStream += (long) this.buf.limit();
                    iInflate = this.inf.inflate(bArr, i, i2);
                    if (iInflate != 0) {
                        break;
                    }
                    break;
                    break;
                }
                if (iFill == -1) {
                    return -1;
                }
            } else {
                try {
                    iInflate = this.inf.inflate(bArr, i, i2);
                    if (iInflate != 0 || !this.inf.needsInput()) {
                        break;
                    }
                } catch (DataFormatException e) {
                    throw ((IOException) new ZipException(e.getMessage()).initCause(e));
                }
            }
        }
        return iInflate;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        try {
            this.in.close();
        } finally {
            this.inf.end();
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException();
        }
        while (j2 < j) {
            long length = j - j2;
            byte[] bArr = this.skipBuf;
            if (bArr.length <= length) {
                length = bArr.length;
            }
            int i = read(bArr, 0, (int) length);
            if (i == -1) {
                return j2;
            }
            j2 += (long) i;
        }
        return j2;
    }

    public static boolean matches(byte[] bArr, int i) {
        if (i < ZipArchiveOutputStream.LFH_SIG.length) {
            return false;
        }
        return checksig(bArr, ZipArchiveOutputStream.LFH_SIG) || checksig(bArr, ZipArchiveOutputStream.EOCD_SIG) || checksig(bArr, ZipArchiveOutputStream.DD_SIG) || checksig(bArr, ZipLong.SINGLE_SEGMENT_SPLIT_MARKER.getBytes());
    }

    private static boolean checksig(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr2.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    private void closeEntry() throws IOException {
        long bytesInflated;
        if (this.closed) {
            throw new IOException("The stream is closed");
        }
        if (this.current == null) {
            return;
        }
        if (currentEntryHasOutstandingBytes()) {
            drainCurrentEntryData();
        } else {
            skip(Long.MAX_VALUE);
            if (this.current.entry.getMethod() == 8) {
                bytesInflated = getBytesInflated();
            } else {
                bytesInflated = this.current.bytesRead;
            }
            int i = (int) (this.current.bytesReadFromStream - bytesInflated);
            if (i > 0) {
                pushback(this.buf.array(), this.buf.limit() - i, i);
                this.current.bytesReadFromStream -= (long) i;
            }
            if (currentEntryHasOutstandingBytes()) {
                drainCurrentEntryData();
            }
        }
        if (this.lastStoredEntry == null && this.current.hasDataDescriptor) {
            readDataDescriptor();
        }
        this.inf.reset();
        this.buf.clear().flip();
        this.current = null;
        this.lastStoredEntry = null;
    }

    private boolean currentEntryHasOutstandingBytes() {
        return this.current.bytesReadFromStream <= this.current.entry.getCompressedSize() && !this.current.hasDataDescriptor;
    }

    private void drainCurrentEntryData() throws IOException {
        long compressedSize = this.current.entry.getCompressedSize() - this.current.bytesReadFromStream;
        while (compressedSize > 0) {
            long j = this.in.read(this.buf.array(), 0, (int) Math.min(this.buf.capacity(), compressedSize));
            if (j < 0) {
                throw new EOFException("Truncated ZIP entry: " + ArchiveUtils.sanitize(this.current.entry.getName()));
            }
            count(j);
            compressedSize -= j;
        }
    }

    private long getBytesInflated() {
        long bytesRead = this.inf.getBytesRead();
        if (this.current.bytesReadFromStream >= TWO_EXP_32) {
            while (true) {
                long j = bytesRead + TWO_EXP_32;
                if (j > this.current.bytesReadFromStream) {
                    break;
                }
                bytesRead = j;
            }
        }
        return bytesRead;
    }

    private int fill() throws IOException {
        if (this.closed) {
            throw new IOException("The stream is closed");
        }
        int i = this.in.read(this.buf.array());
        if (i > 0) {
            this.buf.limit(i);
            count(this.buf.limit());
            this.inf.setInput(this.buf.array(), 0, this.buf.limit());
        }
        return i;
    }

    private void readFully(byte[] bArr) throws IOException {
        readFully(bArr, 0);
    }

    private void readFully(byte[] bArr, int i) throws IOException {
        int length = bArr.length - i;
        int fully = IOUtils.readFully(this.in, bArr, i, length);
        count(fully);
        if (fully < length) {
            throw new EOFException();
        }
    }

    private byte[] readRange(int i) throws IOException {
        byte[] range = IOUtils.readRange(this.in, i);
        count(range.length);
        if (range.length >= i) {
            return range;
        }
        throw new EOFException();
    }

    private void readDataDescriptor() throws IOException {
        readFully(this.wordBuf);
        ZipLong zipLong = new ZipLong(this.wordBuf);
        if (ZipLong.DD_SIG.equals(zipLong)) {
            readFully(this.wordBuf);
            zipLong = new ZipLong(this.wordBuf);
        }
        this.current.entry.setCrc(zipLong.getValue());
        readFully(this.twoDwordBuf);
        ZipLong zipLong2 = new ZipLong(this.twoDwordBuf, 8);
        if (zipLong2.equals(ZipLong.CFH_SIG) || zipLong2.equals(ZipLong.LFH_SIG)) {
            pushback(this.twoDwordBuf, 8, 8);
            long value = ZipLong.getValue(this.twoDwordBuf);
            if (value < 0) {
                throw new ZipException("broken archive, entry with negative compressed size");
            }
            this.current.entry.setCompressedSize(value);
            long value2 = ZipLong.getValue(this.twoDwordBuf, 4);
            if (value2 < 0) {
                throw new ZipException("broken archive, entry with negative size");
            }
            this.current.entry.setSize(value2);
            return;
        }
        long longValue = ZipEightByteInteger.getLongValue(this.twoDwordBuf);
        if (longValue < 0) {
            throw new ZipException("broken archive, entry with negative compressed size");
        }
        this.current.entry.setCompressedSize(longValue);
        long longValue2 = ZipEightByteInteger.getLongValue(this.twoDwordBuf, 8);
        if (longValue2 < 0) {
            throw new ZipException("broken archive, entry with negative size");
        }
        this.current.entry.setSize(longValue2);
    }

    private boolean supportsDataDescriptorFor(ZipArchiveEntry zipArchiveEntry) {
        return !zipArchiveEntry.getGeneralPurposeBit().usesDataDescriptor() || (this.allowStoredEntriesWithDataDescriptor && zipArchiveEntry.getMethod() == 0) || zipArchiveEntry.getMethod() == 8 || zipArchiveEntry.getMethod() == ZipMethod.ENHANCED_DEFLATED.getCode();
    }

    private boolean supportsCompressedSizeFor(ZipArchiveEntry zipArchiveEntry) {
        return zipArchiveEntry.getCompressedSize() != -1 || zipArchiveEntry.getMethod() == 8 || zipArchiveEntry.getMethod() == ZipMethod.ENHANCED_DEFLATED.getCode() || (zipArchiveEntry.getGeneralPurposeBit().usesDataDescriptor() && this.allowStoredEntriesWithDataDescriptor && zipArchiveEntry.getMethod() == 0);
    }

    private void readStoredEntry() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = this.current.usesZip64 ? 20 : 12;
        boolean zBufferContainsSignature = false;
        int iCacheBytesRead = 0;
        while (!zBufferContainsSignature) {
            int i2 = this.in.read(this.buf.array(), iCacheBytesRead, 512 - iCacheBytesRead);
            if (i2 <= 0) {
                throw new IOException("Truncated ZIP file");
            }
            int i3 = i2 + iCacheBytesRead;
            if (i3 < 4) {
                iCacheBytesRead = i3;
            } else {
                zBufferContainsSignature = bufferContainsSignature(byteArrayOutputStream, iCacheBytesRead, i2, i);
                if (!zBufferContainsSignature) {
                    iCacheBytesRead = cacheBytesRead(byteArrayOutputStream, iCacheBytesRead, i2, i);
                }
            }
        }
        if (this.current.entry.getCompressedSize() != this.current.entry.getSize()) {
            throw new ZipException("compressed and uncompressed size don't match while reading a stored entry using data descriptor. Either the archive is broken or it can not be read using ZipArchiveInputStream and you must use ZipFile. A common cause for this is a ZIP archive containing a ZIP archive. See http://commons.apache.org/proper/commons-compress/zip.html#ZipArchiveInputStream_vs_ZipFile");
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (byteArray.length != this.current.entry.getSize()) {
            throw new ZipException("actual and claimed size don't match while reading a stored entry using data descriptor. Either the archive is broken or it can not be read using ZipArchiveInputStream and you must use ZipFile. A common cause for this is a ZIP archive containing a ZIP archive. See http://commons.apache.org/proper/commons-compress/zip.html#ZipArchiveInputStream_vs_ZipFile");
        }
        this.lastStoredEntry = new ByteArrayInputStream(byteArray);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a2 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean bufferContainsSignature(java.io.ByteArrayOutputStream r12, int r13, int r14, int r15) throws java.io.IOException {
        /*
            r11 = this;
            r0 = 0
            r1 = r0
            r2 = r1
        L3:
            if (r1 != 0) goto La6
            int r3 = r13 + r14
            int r4 = r3 + (-4)
            if (r2 >= r4) goto La6
            java.nio.ByteBuffer r4 = r11.buf
            byte[] r4 = r4.array()
            r4 = r4[r2]
            byte[] r5 = org.apache.commons.compress.archivers.zip.ZipArchiveInputStream.LFH
            r6 = r5[r0]
            if (r4 != r6) goto La2
            java.nio.ByteBuffer r4 = r11.buf
            byte[] r4 = r4.array()
            int r6 = r2 + 1
            r4 = r4[r6]
            r6 = 1
            r7 = r5[r6]
            if (r4 != r7) goto La2
            r4 = 2
            r7 = 3
            if (r2 < r15) goto L48
            java.nio.ByteBuffer r8 = r11.buf
            byte[] r8 = r8.array()
            int r9 = r2 + 2
            r8 = r8[r9]
            r9 = r5[r4]
            if (r8 != r9) goto L48
            java.nio.ByteBuffer r8 = r11.buf
            byte[] r8 = r8.array()
            int r9 = r2 + 3
            r8 = r8[r9]
            r5 = r5[r7]
            if (r8 == r5) goto L66
        L48:
            java.nio.ByteBuffer r5 = r11.buf
            byte[] r5 = r5.array()
            int r8 = r2 + 2
            r5 = r5[r8]
            byte[] r9 = org.apache.commons.compress.archivers.zip.ZipArchiveInputStream.CFH
            r10 = r9[r4]
            if (r5 != r10) goto L6b
            java.nio.ByteBuffer r5 = r11.buf
            byte[] r5 = r5.array()
            int r10 = r2 + 3
            r5 = r5[r10]
            r9 = r9[r7]
            if (r5 != r9) goto L6b
        L66:
            int r1 = r2 - r15
            r4 = r1
        L69:
            r1 = r6
            goto L8a
        L6b:
            java.nio.ByteBuffer r5 = r11.buf
            byte[] r5 = r5.array()
            r5 = r5[r8]
            byte[] r8 = org.apache.commons.compress.archivers.zip.ZipArchiveInputStream.DD
            r4 = r8[r4]
            if (r5 != r4) goto L89
            java.nio.ByteBuffer r4 = r11.buf
            byte[] r4 = r4.array()
            int r5 = r2 + 3
            r4 = r4[r5]
            r5 = r8[r7]
            if (r4 != r5) goto L89
            r4 = r2
            goto L69
        L89:
            r4 = r2
        L8a:
            if (r1 == 0) goto La2
            java.nio.ByteBuffer r5 = r11.buf
            byte[] r5 = r5.array()
            int r3 = r3 - r4
            r11.pushback(r5, r4, r3)
            java.nio.ByteBuffer r3 = r11.buf
            byte[] r3 = r3.array()
            r12.write(r3, r0, r4)
            r11.readDataDescriptor()
        La2:
            int r2 = r2 + 1
            goto L3
        La6:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.zip.ZipArchiveInputStream.bufferContainsSignature(java.io.ByteArrayOutputStream, int, int, int):boolean");
    }

    private int cacheBytesRead(ByteArrayOutputStream byteArrayOutputStream, int i, int i2, int i3) {
        int i4 = i + i2;
        int i5 = (i4 - i3) - 3;
        if (i5 <= 0) {
            return i4;
        }
        byteArrayOutputStream.write(this.buf.array(), 0, i5);
        int i6 = i3 + 3;
        System.arraycopy(this.buf.array(), i5, this.buf.array(), 0, i6);
        return i6;
    }

    private void pushback(byte[] bArr, int i, int i2) throws IOException {
        ((PushbackInputStream) this.in).unread(bArr, i, i2);
        pushedBackBytes(i2);
    }

    private void skipRemainderOfArchive() throws IOException {
        int i = this.entriesRead;
        if (i > 0) {
            realSkip((((long) i) * 46) - 30);
            if (findEocdRecord()) {
                realSkip(16L);
                readFully(this.shortBuf);
                int value = ZipShort.getValue(this.shortBuf);
                if (value >= 0) {
                    realSkip(value);
                    return;
                }
            }
        }
        throw new IOException("Truncated ZIP file");
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x003f, code lost:
    
        return false;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x000c A[PHI: r3
  0x000c: PHI (r3v3 int) = (r3v2 int), (r3v8 int) binds: [B:4:0x0004, B:6:0x000a] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean findEocdRecord() throws java.io.IOException {
        /*
            r6 = this;
            r0 = -1
            r1 = 0
            r3 = r0
        L3:
            r2 = r1
        L4:
            if (r2 != 0) goto Lc
            int r3 = r6.readOneByte()
            if (r3 <= r0) goto L3f
        Lc:
            boolean r2 = r6.isFirstByteOfEocdSig(r3)
            if (r2 != 0) goto L13
            goto L3
        L13:
            int r3 = r6.readOneByte()
            byte[] r2 = org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream.EOCD_SIG
            r4 = 1
            r2 = r2[r4]
            if (r3 == r2) goto L26
            if (r3 != r0) goto L21
            goto L3f
        L21:
            boolean r2 = r6.isFirstByteOfEocdSig(r3)
            goto L4
        L26:
            int r3 = r6.readOneByte()
            byte[] r2 = org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream.EOCD_SIG
            r5 = 2
            r2 = r2[r5]
            if (r3 == r2) goto L39
            if (r3 != r0) goto L34
            goto L3f
        L34:
            boolean r2 = r6.isFirstByteOfEocdSig(r3)
            goto L4
        L39:
            int r3 = r6.readOneByte()
            if (r3 != r0) goto L40
        L3f:
            return r1
        L40:
            byte[] r2 = org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream.EOCD_SIG
            r5 = 3
            r2 = r2[r5]
            if (r3 != r2) goto L48
            return r4
        L48:
            boolean r2 = r6.isFirstByteOfEocdSig(r3)
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.zip.ZipArchiveInputStream.findEocdRecord():boolean");
    }

    private void realSkip(long j) throws IOException {
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException();
        }
        while (j2 < j) {
            long length = j - j2;
            InputStream inputStream = this.in;
            byte[] bArr = this.skipBuf;
            if (bArr.length <= length) {
                length = bArr.length;
            }
            int i = inputStream.read(bArr, 0, (int) length);
            if (i == -1) {
                return;
            }
            count(i);
            j2 += (long) i;
        }
    }

    private int readOneByte() throws IOException {
        int i = this.in.read();
        if (i != -1) {
            count(1);
        }
        return i;
    }

    private boolean isFirstByteOfEocdSig(int i) {
        return i == ZipArchiveOutputStream.EOCD_SIG[0];
    }

    private boolean isApkSigningBlock(byte[] bArr) throws IOException {
        BigInteger value = ZipEightByteInteger.getValue(bArr);
        long length = 8 - bArr.length;
        byte[] bArr2 = APK_SIGNING_BLOCK_MAGIC;
        BigInteger bigIntegerAdd = value.add(BigInteger.valueOf(length - ((long) bArr2.length)));
        int length2 = bArr2.length;
        byte[] bArr3 = new byte[length2];
        try {
            if (bigIntegerAdd.signum() < 0) {
                int length3 = bArr.length + bigIntegerAdd.intValue();
                if (length3 < 8) {
                    return false;
                }
                int iAbs = Math.abs(bigIntegerAdd.intValue());
                System.arraycopy(bArr, length3, bArr3, 0, Math.min(iAbs, length2));
                if (iAbs < length2) {
                    readFully(bArr3, iAbs);
                }
            } else {
                while (true) {
                    BigInteger bigInteger = LONG_MAX;
                    if (bigIntegerAdd.compareTo(bigInteger) <= 0) {
                        break;
                    }
                    realSkip(Long.MAX_VALUE);
                    bigIntegerAdd = bigIntegerAdd.add(bigInteger.negate());
                }
                realSkip(bigIntegerAdd.longValue());
                readFully(bArr3);
            }
            return Arrays.equals(bArr3, APK_SIGNING_BLOCK_MAGIC);
        } catch (EOFException unused) {
            return false;
        }
    }

    private static final class CurrentEntry {
        private long bytesRead;
        private long bytesReadFromStream;
        private final CRC32 crc;
        private final ZipArchiveEntry entry;
        private boolean hasDataDescriptor;
        private InputStream in;
        private boolean usesZip64;

        private CurrentEntry() {
            this.entry = new ZipArchiveEntry();
            this.crc = new CRC32();
        }

        /* synthetic */ CurrentEntry(AnonymousClass1 anonymousClass1) {
            this();
        }

        static /* synthetic */ long access$708(CurrentEntry currentEntry) {
            long j = currentEntry.bytesReadFromStream;
            currentEntry.bytesReadFromStream = 1 + j;
            return j;
        }
    }

    private class BoundedInputStream extends InputStream {
        private final InputStream in;
        private final long max;
        private long pos;

        public BoundedInputStream(InputStream inputStream, long j) {
            this.max = j;
            this.in = inputStream;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            long j = this.max;
            if (j >= 0 && this.pos >= j) {
                return -1;
            }
            int i = this.in.read();
            this.pos++;
            ZipArchiveInputStream.this.count(1);
            CurrentEntry.access$708(ZipArchiveInputStream.this.current);
            return i;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            return read(bArr, 0, bArr.length);
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (i2 == 0) {
                return 0;
            }
            long j = this.max;
            if (j >= 0 && this.pos >= j) {
                return -1;
            }
            int i3 = this.in.read(bArr, i, (int) (j >= 0 ? Math.min(i2, j - this.pos) : i2));
            if (i3 == -1) {
                return -1;
            }
            long j2 = i3;
            this.pos += j2;
            ZipArchiveInputStream.this.count(i3);
            ZipArchiveInputStream.this.current.bytesReadFromStream += j2;
            return i3;
        }

        @Override // java.io.InputStream
        public long skip(long j) throws IOException {
            long j2 = this.max;
            if (j2 >= 0) {
                j = Math.min(j, j2 - this.pos);
            }
            long jSkip = IOUtils.skip(this.in, j);
            this.pos += jSkip;
            return jSkip;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            long j = this.max;
            if (j < 0 || this.pos < j) {
                return this.in.available();
            }
            return 0;
        }
    }
}
