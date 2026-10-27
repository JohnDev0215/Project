package org.apache.commons.compress.archivers.zip;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ToLongFunction;
import java.util.zip.Inflater;
import java.util.zip.ZipException;
import okhttp3.internal.ws.WebSocketProtocol;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;
import org.apache.commons.compress.utils.BoundedArchiveInputStream;
import org.apache.commons.compress.utils.BoundedSeekableByteChannelInputStream;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;

/* JADX INFO: loaded from: classes4.dex */
public class ZipFile implements Closeable {
    static final int BYTE_SHIFT = 8;
    private static final int CFD_DISK_OFFSET = 6;
    private static final int CFD_LOCATOR_OFFSET = 16;
    private static final int CFD_LOCATOR_RELATIVE_OFFSET = 8;
    private static final int CFH_LEN = 42;
    private static final int HASH_SIZE = 509;
    private static final long LFH_OFFSET_FOR_FILENAME_LENGTH = 26;
    private static final int MAX_EOCD_SIZE = 65557;
    static final int MIN_EOCD_SIZE = 22;
    static final int NIBLET_MASK = 15;
    private static final int POS_0 = 0;
    private static final int POS_1 = 1;
    private static final int POS_2 = 2;
    private static final int POS_3 = 3;
    private static final int ZIP64_EOCDL_LENGTH = 20;
    private static final int ZIP64_EOCDL_LOCATOR_OFFSET = 8;
    private static final int ZIP64_EOCD_CFD_DISK_OFFSET = 20;
    private static final int ZIP64_EOCD_CFD_LOCATOR_OFFSET = 48;
    private static final int ZIP64_EOCD_CFD_LOCATOR_RELATIVE_OFFSET = 24;
    private final SeekableByteChannel archive;
    private final String archiveName;
    private long centralDirectoryStartDiskNumber;
    private long centralDirectoryStartOffset;
    private long centralDirectoryStartRelativeOffset;
    private final ByteBuffer cfhBbuf;
    private final byte[] cfhBuf;
    private volatile boolean closed;
    private final ByteBuffer dwordBbuf;
    private final byte[] dwordBuf;
    private final String encoding;
    private final List<ZipArchiveEntry> entries;
    private final boolean isSplitZipArchive;
    private final Map<String, LinkedList<ZipArchiveEntry>> nameMap;
    private final Comparator<ZipArchiveEntry> offsetComparator;
    private final ByteBuffer shortBbuf;
    private final byte[] shortBuf;
    private final boolean useUnicodeExtraFields;
    private final ByteBuffer wordBbuf;
    private final byte[] wordBuf;
    private final ZipEncoding zipEncoding;
    private static final byte[] ONE_ZERO_BYTE = new byte[1];
    private static final long CFH_SIG = ZipLong.getValue(ZipArchiveOutputStream.CFH_SIG);

    public ZipFile(File file) throws IOException {
        this(file, "UTF8");
    }

    public ZipFile(String str) throws IOException {
        this(new File(str), "UTF8");
    }

    public ZipFile(String str, String str2) throws IOException {
        this(new File(str), str2, true);
    }

    public ZipFile(File file, String str) throws IOException {
        this(file, str, true);
    }

    public ZipFile(File file, String str, boolean z) throws IOException {
        this(file, str, z, false);
    }

    public ZipFile(File file, String str, boolean z, boolean z2) throws IOException {
        this(Files.newByteChannel(file.toPath(), EnumSet.of(StandardOpenOption.READ), new FileAttribute[0]), file.getAbsolutePath(), str, z, true, z2);
    }

    public ZipFile(SeekableByteChannel seekableByteChannel) throws IOException {
        this(seekableByteChannel, "unknown archive", "UTF8", true);
    }

    public ZipFile(SeekableByteChannel seekableByteChannel, String str) throws IOException {
        this(seekableByteChannel, "unknown archive", str, true);
    }

    public ZipFile(SeekableByteChannel seekableByteChannel, String str, String str2, boolean z) throws IOException {
        this(seekableByteChannel, str, str2, z, false, false);
    }

    public ZipFile(SeekableByteChannel seekableByteChannel, String str, String str2, boolean z, boolean z2) throws IOException {
        this(seekableByteChannel, str, str2, z, false, z2);
    }

    /* JADX WARN: Finally extract failed */
    private ZipFile(SeekableByteChannel seekableByteChannel, String str, String str2, boolean z, boolean z2, boolean z3) throws IOException {
        this.entries = new LinkedList();
        this.nameMap = new HashMap(509);
        this.closed = true;
        byte[] bArr = new byte[8];
        this.dwordBuf = bArr;
        byte[] bArr2 = new byte[4];
        this.wordBuf = bArr2;
        byte[] bArr3 = new byte[42];
        this.cfhBuf = bArr3;
        byte[] bArr4 = new byte[2];
        this.shortBuf = bArr4;
        this.dwordBbuf = ByteBuffer.wrap(bArr);
        this.wordBbuf = ByteBuffer.wrap(bArr2);
        this.cfhBbuf = ByteBuffer.wrap(bArr3);
        this.shortBbuf = ByteBuffer.wrap(bArr4);
        this.offsetComparator = Comparator.comparingLong(new ToLongFunction() { // from class: org.apache.commons.compress.archivers.zip.ZipFile$$ExternalSyntheticLambda0
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                return ((ZipArchiveEntry) obj).getDiskNumberStart();
            }
        }).thenComparingLong(new ToLongFunction() { // from class: org.apache.commons.compress.archivers.zip.ZipFile$$ExternalSyntheticLambda1
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                return ((ZipArchiveEntry) obj).getLocalHeaderOffset();
            }
        });
        this.isSplitZipArchive = seekableByteChannel instanceof ZipSplitReadOnlySeekableByteChannel;
        this.archiveName = str;
        this.encoding = str2;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(str2);
        this.useUnicodeExtraFields = z;
        this.archive = seekableByteChannel;
        try {
            try {
                Map<ZipArchiveEntry, NameAndComment> mapPopulateFromCentralDirectory = populateFromCentralDirectory();
                if (!z3) {
                    resolveLocalFileHeaderData(mapPopulateFromCentralDirectory);
                }
                fillNameMap();
                this.closed = false;
            } catch (IOException e) {
                throw new IOException("Error on ZipFile " + str, e);
            }
        } catch (Throwable th) {
            this.closed = true;
            if (z2) {
                IOUtils.closeQuietly(this.archive);
            }
            throw th;
        }
    }

    public String getEncoding() {
        return this.encoding;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.closed = true;
        this.archive.close();
    }

    public static void closeQuietly(ZipFile zipFile) {
        IOUtils.closeQuietly(zipFile);
    }

    public Enumeration<ZipArchiveEntry> getEntries() {
        return Collections.enumeration(this.entries);
    }

    public Enumeration<ZipArchiveEntry> getEntriesInPhysicalOrder() {
        ZipArchiveEntry[] zipArchiveEntryArr = (ZipArchiveEntry[]) this.entries.toArray(ZipArchiveEntry.EMPTY_ZIP_ARCHIVE_ENTRY_ARRAY);
        Arrays.sort(zipArchiveEntryArr, this.offsetComparator);
        return Collections.enumeration(Arrays.asList(zipArchiveEntryArr));
    }

    public ZipArchiveEntry getEntry(String str) {
        LinkedList<ZipArchiveEntry> linkedList = this.nameMap.get(str);
        if (linkedList != null) {
            return linkedList.getFirst();
        }
        return null;
    }

    public Iterable<ZipArchiveEntry> getEntries(String str) {
        LinkedList<ZipArchiveEntry> linkedList = this.nameMap.get(str);
        return linkedList != null ? linkedList : Collections.emptyList();
    }

    public Iterable<ZipArchiveEntry> getEntriesInPhysicalOrder(String str) {
        ZipArchiveEntry[] zipArchiveEntryArr = ZipArchiveEntry.EMPTY_ZIP_ARCHIVE_ENTRY_ARRAY;
        if (this.nameMap.containsKey(str)) {
            zipArchiveEntryArr = (ZipArchiveEntry[]) this.nameMap.get(str).toArray(zipArchiveEntryArr);
            Arrays.sort(zipArchiveEntryArr, this.offsetComparator);
        }
        return Arrays.asList(zipArchiveEntryArr);
    }

    public boolean canReadEntryData(ZipArchiveEntry zipArchiveEntry) {
        return ZipUtil.canHandleEntryData(zipArchiveEntry);
    }

    public InputStream getRawInputStream(ZipArchiveEntry zipArchiveEntry) {
        if (!(zipArchiveEntry instanceof Entry)) {
            return null;
        }
        long dataOffset = zipArchiveEntry.getDataOffset();
        if (dataOffset == -1) {
            return null;
        }
        return createBoundedInputStream(dataOffset, zipArchiveEntry.getCompressedSize());
    }

    public void copyRawEntries(ZipArchiveOutputStream zipArchiveOutputStream, ZipArchiveEntryPredicate zipArchiveEntryPredicate) throws IOException {
        Enumeration<ZipArchiveEntry> entriesInPhysicalOrder = getEntriesInPhysicalOrder();
        while (entriesInPhysicalOrder.hasMoreElements()) {
            ZipArchiveEntry zipArchiveEntryNextElement = entriesInPhysicalOrder.nextElement();
            if (zipArchiveEntryPredicate.test(zipArchiveEntryNextElement)) {
                zipArchiveOutputStream.addRawArchiveEntry(zipArchiveEntryNextElement, getRawInputStream(zipArchiveEntryNextElement));
            }
        }
    }

    public InputStream getInputStream(ZipArchiveEntry zipArchiveEntry) throws IOException {
        if (!(zipArchiveEntry instanceof Entry)) {
            return null;
        }
        ZipUtil.checkRequestedFeatures(zipArchiveEntry);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(createBoundedInputStream(getDataOffset(zipArchiveEntry), zipArchiveEntry.getCompressedSize()));
        switch (AnonymousClass2.$SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.getMethodByCode(zipArchiveEntry.getMethod()).ordinal()]) {
            case 1:
                return new StoredStatisticsStream(bufferedInputStream);
            case 2:
                return new UnshrinkingInputStream(bufferedInputStream);
            case 3:
                try {
                    return new ExplodingInputStream(zipArchiveEntry.getGeneralPurposeBit().getSlidingDictionarySize(), zipArchiveEntry.getGeneralPurposeBit().getNumberOfShannonFanoTrees(), bufferedInputStream);
                } catch (IllegalArgumentException e) {
                    throw new IOException("bad IMPLODE data", e);
                }
            case 4:
                final Inflater inflater = new Inflater(true);
                return new InflaterInputStreamWithStatistics(new SequenceInputStream(bufferedInputStream, new ByteArrayInputStream(ONE_ZERO_BYTE)), inflater) { // from class: org.apache.commons.compress.archivers.zip.ZipFile.1
                    @Override // java.util.zip.InflaterInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                    public void close() throws IOException {
                        try {
                            super.close();
                        } finally {
                            inflater.end();
                        }
                    }
                };
            case 5:
                return new BZip2CompressorInputStream(bufferedInputStream);
            case 6:
                return new Deflate64CompressorInputStream(bufferedInputStream);
            default:
                throw new UnsupportedZipFeatureException(ZipMethod.getMethodByCode(zipArchiveEntry.getMethod()), zipArchiveEntry);
        }
    }

    /* JADX INFO: renamed from: org.apache.commons.compress.archivers.zip.ZipFile$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod;

        static {
            int[] iArr = new int[ZipMethod.values().length];
            $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod = iArr;
            try {
                iArr[ZipMethod.STORED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.UNSHRINKING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.IMPLODING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.DEFLATED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.BZIP2.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.ENHANCED_DEFLATED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.AES_ENCRYPTED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.EXPANDING_LEVEL_1.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.EXPANDING_LEVEL_2.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.EXPANDING_LEVEL_3.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.EXPANDING_LEVEL_4.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.JPEG.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.LZMA.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.PKWARE_IMPLODING.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.PPMD.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.TOKENIZATION.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.UNKNOWN.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.WAVPACK.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.XZ.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
        }
    }

    public String getUnixSymlink(ZipArchiveEntry zipArchiveEntry) throws IOException {
        if (zipArchiveEntry == null || !zipArchiveEntry.isUnixSymlink()) {
            return null;
        }
        InputStream inputStream = getInputStream(zipArchiveEntry);
        try {
            String strDecode = this.zipEncoding.decode(IOUtils.toByteArray(inputStream));
            if (inputStream != null) {
                inputStream.close();
            }
            return strDecode;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    protected void finalize() throws Throwable {
        try {
            if (!this.closed) {
                System.err.println("Cleaning up unclosed ZipFile for archive " + this.archiveName);
                close();
            }
        } finally {
            super.finalize();
        }
    }

    private Map<ZipArchiveEntry, NameAndComment> populateFromCentralDirectory() throws IOException {
        HashMap map = new HashMap();
        positionAtCentralDirectory();
        this.centralDirectoryStartOffset = this.archive.position();
        this.wordBbuf.rewind();
        IOUtils.readFully(this.archive, this.wordBbuf);
        long value = ZipLong.getValue(this.wordBuf);
        if (value != CFH_SIG && startsWithLocalFileHeader()) {
            throw new IOException("Central directory is empty, can't expand corrupt archive.");
        }
        while (value == CFH_SIG) {
            readCentralDirectoryEntry(map);
            this.wordBbuf.rewind();
            IOUtils.readFully(this.archive, this.wordBbuf);
            value = ZipLong.getValue(this.wordBuf);
        }
        return map;
    }

    private void readCentralDirectoryEntry(Map<ZipArchiveEntry, NameAndComment> map) throws IOException {
        this.cfhBbuf.rewind();
        IOUtils.readFully(this.archive, this.cfhBbuf);
        Entry entry = new Entry();
        int value = ZipShort.getValue(this.cfhBuf, 0);
        entry.setVersionMadeBy(value);
        entry.setPlatform((value >> 8) & 15);
        entry.setVersionRequired(ZipShort.getValue(this.cfhBuf, 2));
        GeneralPurposeBit generalPurposeBit = GeneralPurposeBit.parse(this.cfhBuf, 4);
        boolean zUsesUTF8ForNames = generalPurposeBit.usesUTF8ForNames();
        ZipEncoding zipEncoding = zUsesUTF8ForNames ? ZipEncodingHelper.UTF8_ZIP_ENCODING : this.zipEncoding;
        if (zUsesUTF8ForNames) {
            entry.setNameSource(ZipArchiveEntry.NameSource.NAME_WITH_EFS_FLAG);
        }
        entry.setGeneralPurposeBit(generalPurposeBit);
        entry.setRawFlag(ZipShort.getValue(this.cfhBuf, 4));
        entry.setMethod(ZipShort.getValue(this.cfhBuf, 6));
        entry.setTime(ZipUtil.dosToJavaTime(ZipLong.getValue(this.cfhBuf, 8)));
        entry.setCrc(ZipLong.getValue(this.cfhBuf, 12));
        long value2 = ZipLong.getValue(this.cfhBuf, 16);
        if (value2 < 0) {
            throw new IOException("broken archive, entry with negative compressed size");
        }
        entry.setCompressedSize(value2);
        long value3 = ZipLong.getValue(this.cfhBuf, 20);
        if (value3 < 0) {
            throw new IOException("broken archive, entry with negative size");
        }
        entry.setSize(value3);
        int value4 = ZipShort.getValue(this.cfhBuf, 24);
        if (value4 < 0) {
            throw new IOException("broken archive, entry with negative fileNameLen");
        }
        int value5 = ZipShort.getValue(this.cfhBuf, 26);
        if (value5 < 0) {
            throw new IOException("broken archive, entry with negative extraLen");
        }
        int value6 = ZipShort.getValue(this.cfhBuf, 28);
        if (value6 < 0) {
            throw new IOException("broken archive, entry with negative commentLen");
        }
        entry.setDiskNumberStart(ZipShort.getValue(this.cfhBuf, 30));
        entry.setInternalAttributes(ZipShort.getValue(this.cfhBuf, 32));
        entry.setExternalAttributes(ZipLong.getValue(this.cfhBuf, 34));
        byte[] range = IOUtils.readRange(this.archive, value4);
        if (range.length < value4) {
            throw new EOFException();
        }
        entry.setName(zipEncoding.decode(range), range);
        entry.setLocalHeaderOffset(ZipLong.getValue(this.cfhBuf, 38));
        this.entries.add(entry);
        byte[] range2 = IOUtils.readRange(this.archive, value5);
        if (range2.length < value5) {
            throw new EOFException();
        }
        try {
            entry.setCentralDirectoryExtra(range2);
            setSizesAndOffsetFromZip64Extra(entry);
            sanityCheckLFHOffset(entry);
            byte[] range3 = IOUtils.readRange(this.archive, value6);
            if (range3.length < value6) {
                throw new EOFException();
            }
            entry.setComment(zipEncoding.decode(range3));
            if (!zUsesUTF8ForNames && this.useUnicodeExtraFields) {
                map.put(entry, new NameAndComment(range, range3));
            }
            entry.setStreamContiguous(true);
        } catch (RuntimeException e) {
            ZipException zipException = new ZipException("Invalid extra data in entry " + entry.getName());
            zipException.initCause(e);
            throw zipException;
        }
    }

    private void sanityCheckLFHOffset(ZipArchiveEntry zipArchiveEntry) throws IOException {
        if (zipArchiveEntry.getDiskNumberStart() < 0) {
            throw new IOException("broken archive, entry with negative disk number");
        }
        if (zipArchiveEntry.getLocalHeaderOffset() < 0) {
            throw new IOException("broken archive, entry with negative local file header offset");
        }
        if (!this.isSplitZipArchive) {
            if (zipArchiveEntry.getLocalHeaderOffset() > this.centralDirectoryStartOffset) {
                throw new IOException("local file header for " + zipArchiveEntry.getName() + " starts after central directory");
            }
        } else {
            if (zipArchiveEntry.getDiskNumberStart() > this.centralDirectoryStartDiskNumber) {
                throw new IOException("local file header for " + zipArchiveEntry.getName() + " starts on a later disk than central directory");
            }
            if (zipArchiveEntry.getDiskNumberStart() == this.centralDirectoryStartDiskNumber && zipArchiveEntry.getLocalHeaderOffset() > this.centralDirectoryStartRelativeOffset) {
                throw new IOException("local file header for " + zipArchiveEntry.getName() + " starts after central directory");
            }
        }
    }

    private void setSizesAndOffsetFromZip64Extra(ZipArchiveEntry zipArchiveEntry) throws IOException {
        ZipExtraField extraField = zipArchiveEntry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
        if (extraField != null && !(extraField instanceof Zip64ExtendedInformationExtraField)) {
            throw new ZipException("archive contains unparseable zip64 extra field");
        }
        Zip64ExtendedInformationExtraField zip64ExtendedInformationExtraField = (Zip64ExtendedInformationExtraField) extraField;
        if (zip64ExtendedInformationExtraField != null) {
            boolean z = zipArchiveEntry.getSize() == 4294967295L;
            boolean z2 = zipArchiveEntry.getCompressedSize() == 4294967295L;
            boolean z3 = zipArchiveEntry.getLocalHeaderOffset() == 4294967295L;
            boolean z4 = zipArchiveEntry.getDiskNumberStart() == WebSocketProtocol.PAYLOAD_SHORT_MAX;
            zip64ExtendedInformationExtraField.reparseCentralDirectoryData(z, z2, z3, z4);
            if (z) {
                long longValue = zip64ExtendedInformationExtraField.getSize().getLongValue();
                if (longValue < 0) {
                    throw new IOException("broken archive, entry with negative size");
                }
                zipArchiveEntry.setSize(longValue);
            } else if (z2) {
                zip64ExtendedInformationExtraField.setSize(new ZipEightByteInteger(zipArchiveEntry.getSize()));
            }
            if (z2) {
                long longValue2 = zip64ExtendedInformationExtraField.getCompressedSize().getLongValue();
                if (longValue2 < 0) {
                    throw new IOException("broken archive, entry with negative compressed size");
                }
                zipArchiveEntry.setCompressedSize(longValue2);
            } else if (z) {
                zip64ExtendedInformationExtraField.setCompressedSize(new ZipEightByteInteger(zipArchiveEntry.getCompressedSize()));
            }
            if (z3) {
                zipArchiveEntry.setLocalHeaderOffset(zip64ExtendedInformationExtraField.getRelativeHeaderOffset().getLongValue());
            }
            if (z4) {
                zipArchiveEntry.setDiskNumberStart(zip64ExtendedInformationExtraField.getDiskStartNumber().getValue());
            }
        }
    }

    private void positionAtCentralDirectory() throws IOException {
        positionAtEndOfCentralDirectoryRecord();
        boolean zEquals = false;
        boolean z = this.archive.position() > 20;
        if (z) {
            SeekableByteChannel seekableByteChannel = this.archive;
            seekableByteChannel.position(seekableByteChannel.position() - 20);
            this.wordBbuf.rewind();
            IOUtils.readFully(this.archive, this.wordBbuf);
            zEquals = Arrays.equals(ZipArchiveOutputStream.ZIP64_EOCD_LOC_SIG, this.wordBuf);
        }
        if (!zEquals) {
            if (z) {
                skipBytes(16);
            }
            positionAtCentralDirectory32();
            return;
        }
        positionAtCentralDirectory64();
    }

    private void positionAtCentralDirectory64() throws IOException {
        if (this.isSplitZipArchive) {
            this.wordBbuf.rewind();
            IOUtils.readFully(this.archive, this.wordBbuf);
            long value = ZipLong.getValue(this.wordBuf);
            this.dwordBbuf.rewind();
            IOUtils.readFully(this.archive, this.dwordBbuf);
            ((ZipSplitReadOnlySeekableByteChannel) this.archive).position(value, ZipEightByteInteger.getLongValue(this.dwordBuf));
        } else {
            skipBytes(4);
            this.dwordBbuf.rewind();
            IOUtils.readFully(this.archive, this.dwordBbuf);
            this.archive.position(ZipEightByteInteger.getLongValue(this.dwordBuf));
        }
        this.wordBbuf.rewind();
        IOUtils.readFully(this.archive, this.wordBbuf);
        if (!Arrays.equals(this.wordBuf, ZipArchiveOutputStream.ZIP64_EOCD_SIG)) {
            throw new ZipException("Archive's ZIP64 end of central directory locator is corrupt.");
        }
        if (this.isSplitZipArchive) {
            skipBytes(16);
            this.wordBbuf.rewind();
            IOUtils.readFully(this.archive, this.wordBbuf);
            this.centralDirectoryStartDiskNumber = ZipLong.getValue(this.wordBuf);
            skipBytes(24);
            this.dwordBbuf.rewind();
            IOUtils.readFully(this.archive, this.dwordBbuf);
            long longValue = ZipEightByteInteger.getLongValue(this.dwordBuf);
            this.centralDirectoryStartRelativeOffset = longValue;
            ((ZipSplitReadOnlySeekableByteChannel) this.archive).position(this.centralDirectoryStartDiskNumber, longValue);
            return;
        }
        skipBytes(44);
        this.dwordBbuf.rewind();
        IOUtils.readFully(this.archive, this.dwordBbuf);
        this.centralDirectoryStartDiskNumber = 0L;
        long longValue2 = ZipEightByteInteger.getLongValue(this.dwordBuf);
        this.centralDirectoryStartRelativeOffset = longValue2;
        this.archive.position(longValue2);
    }

    private void positionAtCentralDirectory32() throws IOException {
        if (this.isSplitZipArchive) {
            skipBytes(6);
            this.shortBbuf.rewind();
            IOUtils.readFully(this.archive, this.shortBbuf);
            this.centralDirectoryStartDiskNumber = ZipShort.getValue(this.shortBuf);
            skipBytes(8);
            this.wordBbuf.rewind();
            IOUtils.readFully(this.archive, this.wordBbuf);
            long value = ZipLong.getValue(this.wordBuf);
            this.centralDirectoryStartRelativeOffset = value;
            ((ZipSplitReadOnlySeekableByteChannel) this.archive).position(this.centralDirectoryStartDiskNumber, value);
            return;
        }
        skipBytes(16);
        this.wordBbuf.rewind();
        IOUtils.readFully(this.archive, this.wordBbuf);
        this.centralDirectoryStartDiskNumber = 0L;
        long value2 = ZipLong.getValue(this.wordBuf);
        this.centralDirectoryStartRelativeOffset = value2;
        this.archive.position(value2);
    }

    private void positionAtEndOfCentralDirectoryRecord() throws IOException {
        if (!tryToLocateSignature(22L, 65557L, ZipArchiveOutputStream.EOCD_SIG)) {
            throw new ZipException("Archive is not a ZIP archive");
        }
    }

    private boolean tryToLocateSignature(long j, long j2, byte[] bArr) throws IOException {
        long size = this.archive.size() - j;
        long jMax = Math.max(0L, this.archive.size() - j2);
        boolean z = false;
        if (size >= 0) {
            while (true) {
                if (size < jMax) {
                    break;
                }
                this.archive.position(size);
                try {
                    this.wordBbuf.rewind();
                    IOUtils.readFully(this.archive, this.wordBbuf);
                    this.wordBbuf.flip();
                    if (this.wordBbuf.get() == bArr[0] && this.wordBbuf.get() == bArr[1] && this.wordBbuf.get() == bArr[2] && this.wordBbuf.get() == bArr[3]) {
                        z = true;
                        break;
                    }
                    size--;
                } catch (EOFException unused) {
                }
            }
        }
        if (z) {
            this.archive.position(size);
        }
        return z;
    }

    private void skipBytes(int i) throws IOException {
        long jPosition = this.archive.position() + ((long) i);
        if (jPosition > this.archive.size()) {
            throw new EOFException();
        }
        this.archive.position(jPosition);
    }

    private void resolveLocalFileHeaderData(Map<ZipArchiveEntry, NameAndComment> map) throws IOException {
        Iterator<ZipArchiveEntry> it = this.entries.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            int[] dataOffset = setDataOffset(entry);
            int i = dataOffset[0];
            int i2 = dataOffset[1];
            skipBytes(i);
            byte[] range = IOUtils.readRange(this.archive, i2);
            if (range.length < i2) {
                throw new EOFException();
            }
            try {
                entry.setExtra(range);
                if (map.containsKey(entry)) {
                    NameAndComment nameAndComment = map.get(entry);
                    ZipUtil.setNameAndCommentFromExtraFields(entry, nameAndComment.name, nameAndComment.comment);
                }
            } catch (RuntimeException e) {
                ZipException zipException = new ZipException("Invalid extra data in entry " + entry.getName());
                zipException.initCause(e);
                throw zipException;
            }
        }
    }

    private void fillNameMap() {
        for (ZipArchiveEntry zipArchiveEntry : this.entries) {
            this.nameMap.computeIfAbsent(zipArchiveEntry.getName(), new Function() { // from class: org.apache.commons.compress.archivers.zip.ZipFile$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ZipFile.lambda$fillNameMap$0((String) obj);
                }
            }).addLast(zipArchiveEntry);
        }
    }

    static /* synthetic */ LinkedList lambda$fillNameMap$0(String str) {
        return new LinkedList();
    }

    private int[] setDataOffset(ZipArchiveEntry zipArchiveEntry) throws IOException {
        long localHeaderOffset = zipArchiveEntry.getLocalHeaderOffset();
        if (this.isSplitZipArchive) {
            ((ZipSplitReadOnlySeekableByteChannel) this.archive).position(zipArchiveEntry.getDiskNumberStart(), localHeaderOffset + LFH_OFFSET_FOR_FILENAME_LENGTH);
            localHeaderOffset = this.archive.position() - LFH_OFFSET_FOR_FILENAME_LENGTH;
        } else {
            this.archive.position(localHeaderOffset + LFH_OFFSET_FOR_FILENAME_LENGTH);
        }
        this.wordBbuf.rewind();
        IOUtils.readFully(this.archive, this.wordBbuf);
        this.wordBbuf.flip();
        this.wordBbuf.get(this.shortBuf);
        int value = ZipShort.getValue(this.shortBuf);
        this.wordBbuf.get(this.shortBuf);
        int value2 = ZipShort.getValue(this.shortBuf);
        zipArchiveEntry.setDataOffset(localHeaderOffset + LFH_OFFSET_FOR_FILENAME_LENGTH + 2 + 2 + ((long) value) + ((long) value2));
        if (zipArchiveEntry.getDataOffset() + zipArchiveEntry.getCompressedSize() <= this.centralDirectoryStartOffset) {
            return new int[]{value, value2};
        }
        throw new IOException("data for " + zipArchiveEntry.getName() + " overlaps with central directory.");
    }

    private long getDataOffset(ZipArchiveEntry zipArchiveEntry) throws IOException {
        long dataOffset = zipArchiveEntry.getDataOffset();
        if (dataOffset != -1) {
            return dataOffset;
        }
        setDataOffset(zipArchiveEntry);
        return zipArchiveEntry.getDataOffset();
    }

    private boolean startsWithLocalFileHeader() throws IOException {
        this.archive.position(0L);
        this.wordBbuf.rewind();
        IOUtils.readFully(this.archive, this.wordBbuf);
        return Arrays.equals(this.wordBuf, ZipArchiveOutputStream.LFH_SIG);
    }

    private BoundedArchiveInputStream createBoundedInputStream(long j, long j2) {
        if (j < 0 || j2 < 0 || j + j2 < j) {
            throw new IllegalArgumentException("Corrupted archive, stream boundaries are out of range");
        }
        return this.archive instanceof FileChannel ? new BoundedFileChannelInputStream(j, j2) : new BoundedSeekableByteChannelInputStream(j, j2, this.archive);
    }

    private class BoundedFileChannelInputStream extends BoundedArchiveInputStream {
        private final FileChannel archive;

        BoundedFileChannelInputStream(long j, long j2) {
            super(j, j2);
            this.archive = (FileChannel) ZipFile.this.archive;
        }

        @Override // org.apache.commons.compress.utils.BoundedArchiveInputStream
        protected int read(long j, ByteBuffer byteBuffer) throws IOException {
            int i = this.archive.read(byteBuffer, j);
            byteBuffer.flip();
            return i;
        }
    }

    private static final class NameAndComment {
        private final byte[] comment;
        private final byte[] name;

        private NameAndComment(byte[] bArr, byte[] bArr2) {
            this.name = bArr;
            this.comment = bArr2;
        }
    }

    private static class Entry extends ZipArchiveEntry {
        Entry() {
        }

        @Override // org.apache.commons.compress.archivers.zip.ZipArchiveEntry, java.util.zip.ZipEntry
        public int hashCode() {
            return (super.hashCode() * 3) + ((int) getLocalHeaderOffset()) + ((int) (getLocalHeaderOffset() >> 32));
        }

        @Override // org.apache.commons.compress.archivers.zip.ZipArchiveEntry
        public boolean equals(Object obj) {
            if (!super.equals(obj)) {
                return false;
            }
            Entry entry = (Entry) obj;
            return getLocalHeaderOffset() == entry.getLocalHeaderOffset() && super.getDataOffset() == entry.getDataOffset() && super.getDiskNumberStart() == entry.getDiskNumberStart();
        }
    }

    private static class StoredStatisticsStream extends CountingInputStream implements InputStreamStatistics {
        StoredStatisticsStream(InputStream inputStream) {
            super(inputStream);
        }

        @Override // org.apache.commons.compress.utils.InputStreamStatistics
        public long getCompressedCount() {
            return super.getBytesRead();
        }

        @Override // org.apache.commons.compress.utils.InputStreamStatistics
        public long getUncompressedCount() {
            return getCompressedCount();
        }
    }
}
