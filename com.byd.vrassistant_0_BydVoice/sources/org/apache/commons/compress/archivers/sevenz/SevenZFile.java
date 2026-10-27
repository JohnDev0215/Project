package org.apache.commons.compress.archivers.sevenz;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.common.base.Ascii;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.zip.CRC32;
import org.apache.commons.compress.MemoryLimitException;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.commons.compress.utils.BoundedInputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.CRC32VerifyingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.bouncycastle.crypto.signers.PSSSigner;

/* JADX INFO: loaded from: classes4.dex */
public class SevenZFile implements Closeable {
    private static final String DEFAULT_FILE_NAME = "unknown archive";
    static final int SIGNATURE_HEADER_SIZE = 32;
    private final Archive archive;
    private SeekableByteChannel channel;
    private long compressedBytesReadFromCurrentEntry;
    private int currentEntryIndex;
    private int currentFolderIndex;
    private InputStream currentFolderInputStream;
    private final ArrayList<InputStream> deferredBlockStreams;
    private final String fileName;
    private final SevenZFileOptions options;
    private byte[] password;
    private long uncompressedBytesReadFromCurrentEntry;
    static final byte[] sevenZSignature = {TarConstants.LF_CONTIG, 122, PSSSigner.TRAILER_IMPLICIT, -81, 39, Ascii.FS};
    private static final CharsetEncoder PASSWORD_ENCODER = StandardCharsets.UTF_16LE.newEncoder();

    public SevenZFile(File file, char[] cArr) throws IOException {
        this(file, cArr, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(File file, char[] cArr, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(Files.newByteChannel(file.toPath(), EnumSet.of(StandardOpenOption.READ), new FileAttribute[0]), file.getAbsolutePath(), utf16Decode(cArr), true, sevenZFileOptions);
    }

    @Deprecated
    public SevenZFile(File file, byte[] bArr) throws IOException {
        this(Files.newByteChannel(file.toPath(), EnumSet.of(StandardOpenOption.READ), new FileAttribute[0]), file.getAbsolutePath(), bArr, true, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel) throws IOException {
        this(seekableByteChannel, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(seekableByteChannel, DEFAULT_FILE_NAME, null, sevenZFileOptions);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, char[] cArr) throws IOException {
        this(seekableByteChannel, cArr, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, char[] cArr, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(seekableByteChannel, DEFAULT_FILE_NAME, cArr, sevenZFileOptions);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, String str, char[] cArr) throws IOException {
        this(seekableByteChannel, str, cArr, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, String str, char[] cArr, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(seekableByteChannel, str, utf16Decode(cArr), false, sevenZFileOptions);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, String str) throws IOException {
        this(seekableByteChannel, str, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, String str, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(seekableByteChannel, str, null, false, sevenZFileOptions);
    }

    @Deprecated
    public SevenZFile(SeekableByteChannel seekableByteChannel, byte[] bArr) throws IOException {
        this(seekableByteChannel, DEFAULT_FILE_NAME, bArr);
    }

    @Deprecated
    public SevenZFile(SeekableByteChannel seekableByteChannel, String str, byte[] bArr) throws IOException {
        this(seekableByteChannel, str, bArr, false, SevenZFileOptions.DEFAULT);
    }

    private SevenZFile(SeekableByteChannel seekableByteChannel, String str, byte[] bArr, boolean z, SevenZFileOptions sevenZFileOptions) throws IOException {
        this.currentEntryIndex = -1;
        this.currentFolderIndex = -1;
        this.deferredBlockStreams = new ArrayList<>();
        this.channel = seekableByteChannel;
        this.fileName = str;
        this.options = sevenZFileOptions;
        try {
            this.archive = readHeaders(bArr);
            if (bArr != null) {
                this.password = Arrays.copyOf(bArr, bArr.length);
            } else {
                this.password = null;
            }
        } catch (Throwable th) {
            if (z) {
                this.channel.close();
            }
            throw th;
        }
    }

    public SevenZFile(File file) throws IOException {
        this(file, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(File file, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(file, (char[]) null, sevenZFileOptions);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        SeekableByteChannel seekableByteChannel = this.channel;
        if (seekableByteChannel != null) {
            try {
                seekableByteChannel.close();
            } finally {
                this.channel = null;
                byte[] bArr = this.password;
                if (bArr != null) {
                    Arrays.fill(bArr, (byte) 0);
                }
                this.password = null;
            }
        }
    }

    public SevenZArchiveEntry getNextEntry() throws IOException {
        if (this.currentEntryIndex >= this.archive.files.length - 1) {
            return null;
        }
        this.currentEntryIndex++;
        SevenZArchiveEntry sevenZArchiveEntry = this.archive.files[this.currentEntryIndex];
        if (sevenZArchiveEntry.getName() == null && this.options.getUseDefaultNameForUnnamedEntries()) {
            sevenZArchiveEntry.setName(getDefaultName());
        }
        buildDecodingStream(this.currentEntryIndex, false);
        this.compressedBytesReadFromCurrentEntry = 0L;
        this.uncompressedBytesReadFromCurrentEntry = 0L;
        return sevenZArchiveEntry;
    }

    public Iterable<SevenZArchiveEntry> getEntries() {
        return new ArrayList(Arrays.asList(this.archive.files));
    }

    private Archive readHeaders(byte[] bArr) throws IOException {
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN);
        readFully(byteBufferOrder);
        byte[] bArr2 = new byte[6];
        byteBufferOrder.get(bArr2);
        if (!Arrays.equals(bArr2, sevenZSignature)) {
            throw new IOException("Bad 7z signature");
        }
        byte b = byteBufferOrder.get();
        byte b2 = byteBufferOrder.get();
        boolean z = false;
        if (b != 0) {
            throw new IOException(String.format("Unsupported 7z version (%d,%d)", Byte.valueOf(b), Byte.valueOf(b2)));
        }
        long j = ((long) byteBufferOrder.getInt()) & 4294967295L;
        if (j != 0) {
            z = true;
            break;
        }
        long jPosition = this.channel.position();
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(20);
        readFully(byteBufferAllocate);
        this.channel.position(jPosition);
        while (byteBufferAllocate.hasRemaining()) {
            if (byteBufferAllocate.get() != 0) {
                z = true;
                break;
            }
        }
        if (z) {
            return initializeArchive(readStartHeader(j), bArr, true);
        }
        if (this.options.getTryToRecoverBrokenArchives()) {
            return tryToLocateEndHeader(bArr);
        }
        throw new IOException("archive seems to be invalid.\nYou may want to retry and enable the tryToRecoverBrokenArchives if the archive could be a multi volume archive that has been closed prematurely.");
    }

    private Archive tryToLocateEndHeader(byte[] bArr) throws IOException {
        long size;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(1);
        long jPosition = this.channel.position() + 20;
        if (this.channel.position() + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED > this.channel.size()) {
            size = this.channel.position();
        } else {
            size = this.channel.size() - PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        }
        long size2 = this.channel.size() - 1;
        while (size2 > size) {
            size2--;
            this.channel.position(size2);
            byteBufferAllocate.rewind();
            if (this.channel.read(byteBufferAllocate) < 1) {
                throw new EOFException();
            }
            byte b = byteBufferAllocate.array()[0];
            if (b == 23 || b == 1) {
                try {
                    StartHeader startHeader = new StartHeader();
                    startHeader.nextHeaderOffset = size2 - jPosition;
                    startHeader.nextHeaderSize = this.channel.size() - size2;
                    Archive archiveInitializeArchive = initializeArchive(startHeader, bArr, false);
                    if (archiveInitializeArchive.packSizes.length > 0 && archiveInitializeArchive.files.length > 0) {
                        return archiveInitializeArchive;
                    }
                } catch (Exception unused) {
                    continue;
                }
            }
        }
        throw new IOException("Start header corrupt and unable to guess end header");
    }

    private Archive initializeArchive(StartHeader startHeader, byte[] bArr, boolean z) throws IOException {
        assertFitsIntoNonNegativeInt("nextHeaderSize", startHeader.nextHeaderSize);
        int i = (int) startHeader.nextHeaderSize;
        this.channel.position(startHeader.nextHeaderOffset + 32);
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(i).order(ByteOrder.LITTLE_ENDIAN);
        readFully(byteBufferOrder);
        if (z) {
            CRC32 crc32 = new CRC32();
            crc32.update(byteBufferOrder.array());
            if (startHeader.nextHeaderCrc != crc32.getValue()) {
                throw new IOException("NextHeader CRC mismatch");
            }
        }
        Archive archive = new Archive();
        int unsignedByte = getUnsignedByte(byteBufferOrder);
        if (unsignedByte == 23) {
            byteBufferOrder = readEncodedHeader(byteBufferOrder, archive, bArr);
            archive = new Archive();
            unsignedByte = getUnsignedByte(byteBufferOrder);
        }
        if (unsignedByte != 1) {
            throw new IOException("Broken or unsupported archive: no Header");
        }
        readHeader(byteBufferOrder, archive);
        archive.subStreamsInfo = null;
        return archive;
    }

    private StartHeader readStartHeader(long j) throws IOException {
        StartHeader startHeader = new StartHeader();
        DataInputStream dataInputStream = new DataInputStream(new CRC32VerifyingInputStream(new BoundedSeekableByteChannelInputStream(this.channel, 20L), 20L, j));
        try {
            startHeader.nextHeaderOffset = Long.reverseBytes(dataInputStream.readLong());
            if (startHeader.nextHeaderOffset < 0 || startHeader.nextHeaderOffset + 32 > this.channel.size()) {
                throw new IOException("nextHeaderOffset is out of bounds");
            }
            startHeader.nextHeaderSize = Long.reverseBytes(dataInputStream.readLong());
            long j2 = startHeader.nextHeaderOffset + startHeader.nextHeaderSize;
            if (j2 < startHeader.nextHeaderOffset || j2 + 32 > this.channel.size()) {
                throw new IOException("nextHeaderSize is out of bounds");
            }
            startHeader.nextHeaderCrc = ((long) Integer.reverseBytes(dataInputStream.readInt())) & 4294967295L;
            dataInputStream.close();
            return startHeader;
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

    private void readHeader(ByteBuffer byteBuffer, Archive archive) throws IOException {
        int iPosition = byteBuffer.position();
        sanityCheckAndCollectStatistics(byteBuffer).assertValidity(this.options.getMaxMemoryLimitInKb());
        byteBuffer.position(iPosition);
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 2) {
            readArchiveProperties(byteBuffer);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 3) {
            throw new IOException("Additional streams unsupported");
        }
        if (unsignedByte == 4) {
            readStreamsInfo(byteBuffer, archive);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 5) {
            readFilesInfo(byteBuffer, archive);
            getUnsignedByte(byteBuffer);
        }
    }

    private ArchiveStatistics sanityCheckAndCollectStatistics(ByteBuffer byteBuffer) throws IOException {
        ArchiveStatistics archiveStatistics = new ArchiveStatistics();
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 2) {
            sanityCheckArchiveProperties(byteBuffer);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 3) {
            throw new IOException("Additional streams unsupported");
        }
        if (unsignedByte == 4) {
            sanityCheckStreamsInfo(byteBuffer, archiveStatistics);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 5) {
            sanityCheckFilesInfo(byteBuffer, archiveStatistics);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 0) {
            return archiveStatistics;
        }
        throw new IOException("Badly terminated header, found " + unsignedByte);
    }

    private void readArchiveProperties(ByteBuffer byteBuffer) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        while (unsignedByte != 0) {
            get(byteBuffer, new byte[(int) readUint64(byteBuffer)]);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
    }

    private void sanityCheckArchiveProperties(ByteBuffer byteBuffer) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        while (unsignedByte != 0) {
            long jAssertFitsIntoNonNegativeInt = assertFitsIntoNonNegativeInt("propertySize", readUint64(byteBuffer));
            if (skipBytesFully(byteBuffer, jAssertFitsIntoNonNegativeInt) < jAssertFitsIntoNonNegativeInt) {
                throw new IOException("invalid property size");
            }
            unsignedByte = getUnsignedByte(byteBuffer);
        }
    }

    private ByteBuffer readEncodedHeader(ByteBuffer byteBuffer, Archive archive, byte[] bArr) throws IOException {
        int iPosition = byteBuffer.position();
        ArchiveStatistics archiveStatistics = new ArchiveStatistics();
        sanityCheckStreamsInfo(byteBuffer, archiveStatistics);
        archiveStatistics.assertValidity(this.options.getMaxMemoryLimitInKb());
        byteBuffer.position(iPosition);
        readStreamsInfo(byteBuffer, archive);
        if (archive.folders == null || archive.folders.length == 0) {
            throw new IOException("no folders, can't read encoded header");
        }
        if (archive.packSizes == null || archive.packSizes.length == 0) {
            throw new IOException("no packed streams, can't read encoded header");
        }
        Folder folder = archive.folders[0];
        this.channel.position(archive.packPos + 32 + 0);
        BoundedSeekableByteChannelInputStream boundedSeekableByteChannelInputStream = new BoundedSeekableByteChannelInputStream(this.channel, archive.packSizes[0]);
        InputStream cRC32VerifyingInputStream = boundedSeekableByteChannelInputStream;
        for (Coder coder : folder.getOrderedCoders()) {
            if (coder.numInStreams != 1 || coder.numOutStreams != 1) {
                throw new IOException("Multi input/output stream coders are not yet supported");
            }
            cRC32VerifyingInputStream = Coders.addDecoder(this.fileName, cRC32VerifyingInputStream, folder.getUnpackSizeForCoder(coder), coder, bArr, this.options.getMaxMemoryLimitInKb());
        }
        if (folder.hasCrc) {
            cRC32VerifyingInputStream = new CRC32VerifyingInputStream(cRC32VerifyingInputStream, folder.getUnpackSize(), folder.crc);
        }
        int iAssertFitsIntoNonNegativeInt = assertFitsIntoNonNegativeInt("unpackSize", folder.getUnpackSize());
        byte[] range = IOUtils.readRange(cRC32VerifyingInputStream, iAssertFitsIntoNonNegativeInt);
        if (range.length < iAssertFitsIntoNonNegativeInt) {
            throw new IOException("premature end of stream");
        }
        cRC32VerifyingInputStream.close();
        return ByteBuffer.wrap(range).order(ByteOrder.LITTLE_ENDIAN);
    }

    private void sanityCheckStreamsInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 6) {
            sanityCheckPackInfo(byteBuffer, archiveStatistics);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 7) {
            sanityCheckUnpackInfo(byteBuffer, archiveStatistics);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 8) {
            sanityCheckSubStreamsInfo(byteBuffer, archiveStatistics);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte != 0) {
            throw new IOException("Badly terminated StreamsInfo");
        }
    }

    private void readStreamsInfo(ByteBuffer byteBuffer, Archive archive) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 6) {
            readPackInfo(byteBuffer, archive);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 7) {
            readUnpackInfo(byteBuffer, archive);
            unsignedByte = getUnsignedByte(byteBuffer);
        } else {
            archive.folders = Folder.EMPTY_FOLDER_ARRAY;
        }
        if (unsignedByte == 8) {
            readSubStreamsInfo(byteBuffer, archive);
            getUnsignedByte(byteBuffer);
        }
    }

    private void sanityCheckPackInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        long uint64 = readUint64(byteBuffer);
        long j = 0;
        if (uint64 >= 0) {
            long j2 = 32 + uint64;
            if (j2 <= this.channel.size() && j2 >= 0) {
                archiveStatistics.numberOfPackedStreams = assertFitsIntoNonNegativeInt("numPackStreams", readUint64(byteBuffer));
                int unsignedByte = getUnsignedByte(byteBuffer);
                if (unsignedByte == 9) {
                    int i = 0;
                    long j3 = 0;
                    while (i < archiveStatistics.numberOfPackedStreams) {
                        long uint642 = readUint64(byteBuffer);
                        j3 += uint642;
                        long j4 = j2 + j3;
                        if (uint642 < j || j4 > this.channel.size() || j4 < uint64) {
                            throw new IOException("packSize (" + uint642 + ") is out of range");
                        }
                        i++;
                        j = 0;
                    }
                    unsignedByte = getUnsignedByte(byteBuffer);
                }
                if (unsignedByte == 10) {
                    long jCardinality = readAllOrBits(byteBuffer, archiveStatistics.numberOfPackedStreams).cardinality() * 4;
                    if (skipBytesFully(byteBuffer, jCardinality) < jCardinality) {
                        throw new IOException("invalid number of CRCs in PackInfo");
                    }
                    unsignedByte = getUnsignedByte(byteBuffer);
                }
                if (unsignedByte != 0) {
                    throw new IOException("Badly terminated PackInfo (" + unsignedByte + ")");
                }
                return;
            }
        }
        throw new IOException("packPos (" + uint64 + ") is out of range");
    }

    private void readPackInfo(ByteBuffer byteBuffer, Archive archive) throws IOException {
        archive.packPos = readUint64(byteBuffer);
        int uint64 = (int) readUint64(byteBuffer);
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 9) {
            archive.packSizes = new long[uint64];
            for (int i = 0; i < archive.packSizes.length; i++) {
                archive.packSizes[i] = readUint64(byteBuffer);
            }
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 10) {
            archive.packCrcsDefined = readAllOrBits(byteBuffer, uint64);
            archive.packCrcs = new long[uint64];
            for (int i2 = 0; i2 < uint64; i2++) {
                if (archive.packCrcsDefined.get(i2)) {
                    archive.packCrcs[i2] = ((long) getInt(byteBuffer)) & 4294967295L;
                }
            }
            getUnsignedByte(byteBuffer);
        }
    }

    private void sanityCheckUnpackInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte != 11) {
            throw new IOException("Expected kFolder, got " + unsignedByte);
        }
        archiveStatistics.numberOfFolders = assertFitsIntoNonNegativeInt("numFolders", readUint64(byteBuffer));
        if (getUnsignedByte(byteBuffer) != 0) {
            throw new IOException("External unsupported");
        }
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < archiveStatistics.numberOfFolders; i++) {
            linkedList.add(Integer.valueOf(sanityCheckFolder(byteBuffer, archiveStatistics)));
        }
        if (archiveStatistics.numberOfInStreams - (archiveStatistics.numberOfOutStreams - ((long) archiveStatistics.numberOfFolders)) < archiveStatistics.numberOfPackedStreams) {
            throw new IOException("archive doesn't contain enough packed streams");
        }
        int unsignedByte2 = getUnsignedByte(byteBuffer);
        if (unsignedByte2 != 12) {
            throw new IOException("Expected kCodersUnpackSize, got " + unsignedByte2);
        }
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Integer) it.next()).intValue();
            for (int i2 = 0; i2 < iIntValue; i2++) {
                if (readUint64(byteBuffer) < 0) {
                    throw new IllegalArgumentException("negative unpackSize");
                }
            }
        }
        int unsignedByte3 = getUnsignedByte(byteBuffer);
        if (unsignedByte3 == 10) {
            archiveStatistics.folderHasCrc = readAllOrBits(byteBuffer, archiveStatistics.numberOfFolders);
            long jCardinality = archiveStatistics.folderHasCrc.cardinality() * 4;
            if (skipBytesFully(byteBuffer, jCardinality) < jCardinality) {
                throw new IOException("invalid number of CRCs in UnpackInfo");
            }
            unsignedByte3 = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte3 != 0) {
            throw new IOException("Badly terminated UnpackInfo");
        }
    }

    private void readUnpackInfo(ByteBuffer byteBuffer, Archive archive) throws IOException {
        getUnsignedByte(byteBuffer);
        int uint64 = (int) readUint64(byteBuffer);
        Folder[] folderArr = new Folder[uint64];
        archive.folders = folderArr;
        getUnsignedByte(byteBuffer);
        for (int i = 0; i < uint64; i++) {
            folderArr[i] = readFolder(byteBuffer);
        }
        getUnsignedByte(byteBuffer);
        for (int i2 = 0; i2 < uint64; i2++) {
            Folder folder = folderArr[i2];
            assertFitsIntoNonNegativeInt("totalOutputStreams", folder.totalOutputStreams);
            folder.unpackSizes = new long[(int) folder.totalOutputStreams];
            for (int i3 = 0; i3 < folder.totalOutputStreams; i3++) {
                folder.unpackSizes[i3] = readUint64(byteBuffer);
            }
        }
        if (getUnsignedByte(byteBuffer) == 10) {
            BitSet allOrBits = readAllOrBits(byteBuffer, uint64);
            for (int i4 = 0; i4 < uint64; i4++) {
                if (allOrBits.get(i4)) {
                    folderArr[i4].hasCrc = true;
                    folderArr[i4].crc = ((long) getInt(byteBuffer)) & 4294967295L;
                } else {
                    folderArr[i4].hasCrc = false;
                }
            }
            getUnsignedByte(byteBuffer);
        }
    }

    private void sanityCheckSubStreamsInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        int iCardinality;
        int unsignedByte = getUnsignedByte(byteBuffer);
        LinkedList linkedList = new LinkedList();
        int i = 0;
        if (unsignedByte == 13) {
            for (int i2 = 0; i2 < archiveStatistics.numberOfFolders; i2++) {
                linkedList.add(Integer.valueOf(assertFitsIntoNonNegativeInt("numStreams", readUint64(byteBuffer))));
            }
            archiveStatistics.numberOfUnpackSubStreams = ((Long) linkedList.stream().collect(Collectors.summingLong(new ToLongFunction() { // from class: org.apache.commons.compress.archivers.sevenz.SevenZFile$$ExternalSyntheticLambda0
                @Override // java.util.function.ToLongFunction
                public final long applyAsLong(Object obj) {
                    return ((Integer) obj).longValue();
                }
            }))).longValue();
            unsignedByte = getUnsignedByte(byteBuffer);
        } else {
            archiveStatistics.numberOfUnpackSubStreams = archiveStatistics.numberOfFolders;
        }
        assertFitsIntoNonNegativeInt("totalUnpackStreams", archiveStatistics.numberOfUnpackSubStreams);
        if (unsignedByte == 9) {
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                int iIntValue = ((Integer) it.next()).intValue();
                if (iIntValue != 0) {
                    for (int i3 = 0; i3 < iIntValue - 1; i3++) {
                        if (readUint64(byteBuffer) < 0) {
                            throw new IOException("negative unpackSize");
                        }
                    }
                }
            }
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (!linkedList.isEmpty()) {
            Iterator it2 = linkedList.iterator();
            int i4 = 0;
            while (it2.hasNext()) {
                int iIntValue2 = ((Integer) it2.next()).intValue();
                if (iIntValue2 == 1 && archiveStatistics.folderHasCrc != null) {
                    int i5 = i4 + 1;
                    if (archiveStatistics.folderHasCrc.get(i4)) {
                        i4 = i5;
                    } else {
                        i4 = i5;
                    }
                }
                i += iIntValue2;
            }
            iCardinality = i;
        } else {
            iCardinality = archiveStatistics.folderHasCrc == null ? archiveStatistics.numberOfFolders : archiveStatistics.numberOfFolders - archiveStatistics.folderHasCrc.cardinality();
        }
        if (unsignedByte == 10) {
            assertFitsIntoNonNegativeInt("numDigests", iCardinality);
            long jCardinality = readAllOrBits(byteBuffer, iCardinality).cardinality() * 4;
            if (skipBytesFully(byteBuffer, jCardinality) < jCardinality) {
                throw new IOException("invalid number of missing CRCs in SubStreamInfo");
            }
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte != 0) {
            throw new IOException("Badly terminated SubStreamsInfo");
        }
    }

    private void readSubStreamsInfo(ByteBuffer byteBuffer, Archive archive) throws IOException {
        for (Folder folder : archive.folders) {
            folder.numUnpackSubStreams = 1;
        }
        long length = archive.folders.length;
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 13) {
            long j = 0;
            for (Folder folder2 : archive.folders) {
                long uint64 = readUint64(byteBuffer);
                folder2.numUnpackSubStreams = (int) uint64;
                j += uint64;
            }
            unsignedByte = getUnsignedByte(byteBuffer);
            length = j;
        }
        int i = (int) length;
        SubStreamsInfo subStreamsInfo = new SubStreamsInfo();
        subStreamsInfo.unpackSizes = new long[i];
        subStreamsInfo.hasCrc = new BitSet(i);
        subStreamsInfo.crcs = new long[i];
        int i2 = 0;
        for (Folder folder3 : archive.folders) {
            if (folder3.numUnpackSubStreams != 0) {
                long j2 = 0;
                if (unsignedByte == 9) {
                    int i3 = 0;
                    while (i3 < folder3.numUnpackSubStreams - 1) {
                        long uint642 = readUint64(byteBuffer);
                        subStreamsInfo.unpackSizes[i2] = uint642;
                        j2 += uint642;
                        i3++;
                        i2++;
                    }
                }
                if (j2 > folder3.getUnpackSize()) {
                    throw new IOException("sum of unpack sizes of folder exceeds total unpack size");
                }
                subStreamsInfo.unpackSizes[i2] = folder3.getUnpackSize() - j2;
                i2++;
            }
        }
        if (unsignedByte == 9) {
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        int i4 = 0;
        for (Folder folder4 : archive.folders) {
            if (folder4.numUnpackSubStreams != 1 || !folder4.hasCrc) {
                i4 += folder4.numUnpackSubStreams;
            }
        }
        if (unsignedByte == 10) {
            BitSet allOrBits = readAllOrBits(byteBuffer, i4);
            long[] jArr = new long[i4];
            for (int i5 = 0; i5 < i4; i5++) {
                if (allOrBits.get(i5)) {
                    jArr[i5] = ((long) getInt(byteBuffer)) & 4294967295L;
                }
            }
            int i6 = 0;
            int i7 = 0;
            for (Folder folder5 : archive.folders) {
                if (folder5.numUnpackSubStreams == 1 && folder5.hasCrc) {
                    subStreamsInfo.hasCrc.set(i6, true);
                    subStreamsInfo.crcs[i6] = folder5.crc;
                    i6++;
                } else {
                    for (int i8 = 0; i8 < folder5.numUnpackSubStreams; i8++) {
                        subStreamsInfo.hasCrc.set(i6, allOrBits.get(i7));
                        subStreamsInfo.crcs[i6] = jArr[i7];
                        i6++;
                        i7++;
                    }
                }
            }
            getUnsignedByte(byteBuffer);
        }
        archive.subStreamsInfo = subStreamsInfo;
    }

    private int sanityCheckFolder(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        int iAssertFitsIntoNonNegativeInt = assertFitsIntoNonNegativeInt("numCoders", readUint64(byteBuffer));
        if (iAssertFitsIntoNonNegativeInt == 0) {
            throw new IOException("Folder without coders");
        }
        archiveStatistics.numberOfCoders += (long) iAssertFitsIntoNonNegativeInt;
        long jAssertFitsIntoNonNegativeInt = 0;
        long j = 0;
        int i = 0;
        while (true) {
            long jAssertFitsIntoNonNegativeInt2 = 1;
            if (i < iAssertFitsIntoNonNegativeInt) {
                int unsignedByte = getUnsignedByte(byteBuffer);
                get(byteBuffer, new byte[unsignedByte & 15]);
                boolean z = (unsignedByte & 16) == 0;
                boolean z2 = (unsignedByte & 32) != 0;
                if ((unsignedByte & 128) != 0) {
                    throw new IOException("Alternative methods are unsupported, please report. The reference implementation doesn't support them either.");
                }
                if (z) {
                    jAssertFitsIntoNonNegativeInt++;
                } else {
                    jAssertFitsIntoNonNegativeInt += (long) assertFitsIntoNonNegativeInt("numInStreams", readUint64(byteBuffer));
                    jAssertFitsIntoNonNegativeInt2 = assertFitsIntoNonNegativeInt("numOutStreams", readUint64(byteBuffer));
                }
                j += jAssertFitsIntoNonNegativeInt2;
                if (z2) {
                    long jAssertFitsIntoNonNegativeInt3 = assertFitsIntoNonNegativeInt("propertiesSize", readUint64(byteBuffer));
                    if (skipBytesFully(byteBuffer, jAssertFitsIntoNonNegativeInt3) < jAssertFitsIntoNonNegativeInt3) {
                        throw new IOException("invalid propertiesSize in folder");
                    }
                }
                i++;
            } else {
                assertFitsIntoNonNegativeInt("totalInStreams", jAssertFitsIntoNonNegativeInt);
                assertFitsIntoNonNegativeInt("totalOutStreams", j);
                archiveStatistics.numberOfOutStreams += j;
                archiveStatistics.numberOfInStreams += jAssertFitsIntoNonNegativeInt;
                if (j == 0) {
                    throw new IOException("Total output streams can't be 0");
                }
                int iAssertFitsIntoNonNegativeInt2 = assertFitsIntoNonNegativeInt("numBindPairs", j - 1);
                long j2 = iAssertFitsIntoNonNegativeInt2;
                if (jAssertFitsIntoNonNegativeInt < j2) {
                    throw new IOException("Total input streams can't be less than the number of bind pairs");
                }
                BitSet bitSet = new BitSet((int) jAssertFitsIntoNonNegativeInt);
                for (int i2 = 0; i2 < iAssertFitsIntoNonNegativeInt2; i2++) {
                    int iAssertFitsIntoNonNegativeInt3 = assertFitsIntoNonNegativeInt("inIndex", readUint64(byteBuffer));
                    if (jAssertFitsIntoNonNegativeInt <= iAssertFitsIntoNonNegativeInt3) {
                        throw new IOException("inIndex is bigger than number of inStreams");
                    }
                    bitSet.set(iAssertFitsIntoNonNegativeInt3);
                    if (j <= assertFitsIntoNonNegativeInt("outIndex", readUint64(byteBuffer))) {
                        throw new IOException("outIndex is bigger than number of outStreams");
                    }
                }
                int iAssertFitsIntoNonNegativeInt4 = assertFitsIntoNonNegativeInt("numPackedStreams", jAssertFitsIntoNonNegativeInt - j2);
                if (iAssertFitsIntoNonNegativeInt4 != 1) {
                    for (int i3 = 0; i3 < iAssertFitsIntoNonNegativeInt4; i3++) {
                        if (assertFitsIntoNonNegativeInt("packedStreamIndex", readUint64(byteBuffer)) >= jAssertFitsIntoNonNegativeInt) {
                            throw new IOException("packedStreamIndex is bigger than number of totalInStreams");
                        }
                    }
                } else if (bitSet.nextClearBit(0) == -1) {
                    throw new IOException("Couldn't find stream's bind pair index");
                }
                return (int) j;
            }
        }
    }

    private Folder readFolder(ByteBuffer byteBuffer) throws IOException {
        Folder folder = new Folder();
        int uint64 = (int) readUint64(byteBuffer);
        Coder[] coderArr = new Coder[uint64];
        long j = 0;
        long j2 = 0;
        for (int i = 0; i < uint64; i++) {
            coderArr[i] = new Coder();
            int unsignedByte = getUnsignedByte(byteBuffer);
            int i2 = unsignedByte & 15;
            boolean z = (unsignedByte & 16) == 0;
            boolean z2 = (unsignedByte & 32) != 0;
            boolean z3 = (unsignedByte & 128) != 0;
            coderArr[i].decompressionMethodId = new byte[i2];
            get(byteBuffer, coderArr[i].decompressionMethodId);
            if (z) {
                coderArr[i].numInStreams = 1L;
                coderArr[i].numOutStreams = 1L;
            } else {
                coderArr[i].numInStreams = readUint64(byteBuffer);
                coderArr[i].numOutStreams = readUint64(byteBuffer);
            }
            j += coderArr[i].numInStreams;
            j2 += coderArr[i].numOutStreams;
            if (z2) {
                coderArr[i].properties = new byte[(int) readUint64(byteBuffer)];
                get(byteBuffer, coderArr[i].properties);
            }
            if (z3) {
                throw new IOException("Alternative methods are unsupported, please report. The reference implementation doesn't support them either.");
            }
        }
        folder.coders = coderArr;
        folder.totalInputStreams = j;
        folder.totalOutputStreams = j2;
        long j3 = j2 - 1;
        int i3 = (int) j3;
        BindPair[] bindPairArr = new BindPair[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            BindPair bindPair = new BindPair();
            bindPairArr[i4] = bindPair;
            bindPair.inIndex = readUint64(byteBuffer);
            bindPairArr[i4].outIndex = readUint64(byteBuffer);
        }
        folder.bindPairs = bindPairArr;
        long j4 = j - j3;
        int i5 = (int) j4;
        long[] jArr = new long[i5];
        if (j4 == 1) {
            int i6 = 0;
            while (i6 < ((int) j) && folder.findBindPairForInStream(i6) >= 0) {
                i6++;
            }
            jArr[0] = i6;
        } else {
            for (int i7 = 0; i7 < i5; i7++) {
                jArr[i7] = readUint64(byteBuffer);
            }
        }
        folder.packedStreams = jArr;
        return folder;
    }

    private BitSet readAllOrBits(ByteBuffer byteBuffer, int i) throws IOException {
        if (getUnsignedByte(byteBuffer) != 0) {
            BitSet bitSet = new BitSet(i);
            for (int i2 = 0; i2 < i; i2++) {
                bitSet.set(i2, true);
            }
            return bitSet;
        }
        return readBits(byteBuffer, i);
    }

    private BitSet readBits(ByteBuffer byteBuffer, int i) throws IOException {
        BitSet bitSet = new BitSet(i);
        int i2 = 0;
        int unsignedByte = 0;
        for (int i3 = 0; i3 < i; i3++) {
            if (i2 == 0) {
                unsignedByte = getUnsignedByte(byteBuffer);
                i2 = 128;
            }
            bitSet.set(i3, (unsignedByte & i2) != 0);
            i2 >>>= 1;
        }
        return bitSet;
    }

    private void sanityCheckFilesInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        archiveStatistics.numberOfEntries = assertFitsIntoNonNegativeInt("numFiles", readUint64(byteBuffer));
        int iCardinality = -1;
        while (true) {
            int unsignedByte = getUnsignedByte(byteBuffer);
            if (unsignedByte != 0) {
                long uint64 = readUint64(byteBuffer);
                switch (unsignedByte) {
                    case 14:
                        iCardinality = readBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                        break;
                    case 15:
                        if (iCardinality == -1) {
                            throw new IOException("Header format error: kEmptyStream must appear before kEmptyFile");
                        }
                        readBits(byteBuffer, iCardinality);
                        break;
                        break;
                    case 16:
                        if (iCardinality == -1) {
                            throw new IOException("Header format error: kEmptyStream must appear before kAnti");
                        }
                        readBits(byteBuffer, iCardinality);
                        break;
                        break;
                    case 17:
                        if (getUnsignedByte(byteBuffer) != 0) {
                            throw new IOException("Not implemented");
                        }
                        int iAssertFitsIntoNonNegativeInt = assertFitsIntoNonNegativeInt("file names length", uint64 - 1);
                        if ((iAssertFitsIntoNonNegativeInt & 1) != 0) {
                            throw new IOException("File names length invalid");
                        }
                        int i = 0;
                        for (int i2 = 0; i2 < iAssertFitsIntoNonNegativeInt; i2 += 2) {
                            if (getChar(byteBuffer) == 0) {
                                i++;
                            }
                        }
                        if (i != archiveStatistics.numberOfEntries) {
                            throw new IOException("Invalid number of file names (" + i + " instead of " + archiveStatistics.numberOfEntries + ")");
                        }
                        break;
                        break;
                    case 18:
                        int iCardinality2 = readAllOrBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                        if (getUnsignedByte(byteBuffer) != 0) {
                            throw new IOException("Not implemented");
                        }
                        long j = iCardinality2 * 8;
                        if (skipBytesFully(byteBuffer, j) < j) {
                            throw new IOException("invalid creation dates size");
                        }
                        break;
                        break;
                    case 19:
                        int iCardinality3 = readAllOrBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                        if (getUnsignedByte(byteBuffer) != 0) {
                            throw new IOException("Not implemented");
                        }
                        long j2 = iCardinality3 * 8;
                        if (skipBytesFully(byteBuffer, j2) < j2) {
                            throw new IOException("invalid access dates size");
                        }
                        break;
                        break;
                    case 20:
                        int iCardinality4 = readAllOrBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                        if (getUnsignedByte(byteBuffer) != 0) {
                            throw new IOException("Not implemented");
                        }
                        long j3 = iCardinality4 * 8;
                        if (skipBytesFully(byteBuffer, j3) < j3) {
                            throw new IOException("invalid modification dates size");
                        }
                        break;
                        break;
                    case 21:
                        int iCardinality5 = readAllOrBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                        if (getUnsignedByte(byteBuffer) != 0) {
                            throw new IOException("Not implemented");
                        }
                        long j4 = iCardinality5 * 4;
                        if (skipBytesFully(byteBuffer, j4) < j4) {
                            throw new IOException("invalid windows attributes size");
                        }
                        break;
                        break;
                    case 22:
                    case 23:
                    default:
                        if (skipBytesFully(byteBuffer, uint64) < uint64) {
                            throw new IOException("Incomplete property of type " + unsignedByte);
                        }
                        break;
                        break;
                    case 24:
                        throw new IOException("kStartPos is unsupported, please report");
                    case 25:
                        if (skipBytesFully(byteBuffer, uint64) < uint64) {
                            throw new IOException("Incomplete kDummy property");
                        }
                        break;
                        break;
                }
            } else {
                int i3 = archiveStatistics.numberOfEntries;
                if (iCardinality <= 0) {
                    iCardinality = 0;
                }
                archiveStatistics.numberOfEntriesWithStream = i3 - iCardinality;
                return;
            }
        }
    }

    private void readFilesInfo(ByteBuffer byteBuffer, Archive archive) throws IOException {
        Archive archive2 = archive;
        int uint64 = (int) readUint64(byteBuffer);
        HashMap map = new HashMap();
        BitSet bits = null;
        BitSet bits2 = null;
        BitSet bits3 = null;
        while (true) {
            int unsignedByte = getUnsignedByte(byteBuffer);
            int i = 0;
            if (unsignedByte != 0) {
                long uint642 = readUint64(byteBuffer);
                if (unsignedByte != 25) {
                    switch (unsignedByte) {
                        case 14:
                            bits = readBits(byteBuffer, uint64);
                            break;
                        case 15:
                            bits2 = readBits(byteBuffer, bits.cardinality());
                            break;
                        case 16:
                            bits3 = readBits(byteBuffer, bits.cardinality());
                            break;
                        case 17:
                            getUnsignedByte(byteBuffer);
                            int i2 = (int) (uint642 - 1);
                            byte[] bArr = new byte[i2];
                            get(byteBuffer, bArr);
                            int i3 = 0;
                            int i4 = 0;
                            while (i < i2) {
                                if (bArr[i] == 0 && bArr[i + 1] == 0) {
                                    checkEntryIsInitialized(map, i4);
                                    map.get(Integer.valueOf(i4)).setName(new String(bArr, i3, i - i3, StandardCharsets.UTF_16LE));
                                    i4++;
                                    i3 = i + 2;
                                }
                                i += 2;
                            }
                            if (i3 != i2 || i4 != uint64) {
                            }
                            break;
                        case 18:
                            BitSet allOrBits = readAllOrBits(byteBuffer, uint64);
                            getUnsignedByte(byteBuffer);
                            while (i < uint64) {
                                checkEntryIsInitialized(map, i);
                                SevenZArchiveEntry sevenZArchiveEntry = map.get(Integer.valueOf(i));
                                sevenZArchiveEntry.setHasCreationDate(allOrBits.get(i));
                                if (sevenZArchiveEntry.getHasCreationDate()) {
                                    sevenZArchiveEntry.setCreationDate(getLong(byteBuffer));
                                }
                                i++;
                            }
                            break;
                        case 19:
                            BitSet allOrBits2 = readAllOrBits(byteBuffer, uint64);
                            getUnsignedByte(byteBuffer);
                            while (i < uint64) {
                                checkEntryIsInitialized(map, i);
                                SevenZArchiveEntry sevenZArchiveEntry2 = map.get(Integer.valueOf(i));
                                sevenZArchiveEntry2.setHasAccessDate(allOrBits2.get(i));
                                if (sevenZArchiveEntry2.getHasAccessDate()) {
                                    sevenZArchiveEntry2.setAccessDate(getLong(byteBuffer));
                                }
                                i++;
                            }
                            break;
                        case 20:
                            BitSet allOrBits3 = readAllOrBits(byteBuffer, uint64);
                            getUnsignedByte(byteBuffer);
                            while (i < uint64) {
                                checkEntryIsInitialized(map, i);
                                SevenZArchiveEntry sevenZArchiveEntry3 = map.get(Integer.valueOf(i));
                                sevenZArchiveEntry3.setHasLastModifiedDate(allOrBits3.get(i));
                                if (sevenZArchiveEntry3.getHasLastModifiedDate()) {
                                    sevenZArchiveEntry3.setLastModifiedDate(getLong(byteBuffer));
                                }
                                i++;
                            }
                            break;
                        case 21:
                            BitSet allOrBits4 = readAllOrBits(byteBuffer, uint64);
                            getUnsignedByte(byteBuffer);
                            while (i < uint64) {
                                checkEntryIsInitialized(map, i);
                                SevenZArchiveEntry sevenZArchiveEntry4 = map.get(Integer.valueOf(i));
                                sevenZArchiveEntry4.setHasWindowsAttributes(allOrBits4.get(i));
                                if (sevenZArchiveEntry4.getHasWindowsAttributes()) {
                                    sevenZArchiveEntry4.setWindowsAttributes(getInt(byteBuffer));
                                }
                                i++;
                            }
                            break;
                        default:
                            skipBytesFully(byteBuffer, uint642);
                            break;
                    }
                } else {
                    skipBytesFully(byteBuffer, uint642);
                }
                archive2 = archive;
            } else {
                int i5 = 0;
                int i6 = 0;
                for (int i7 = 0; i7 < uint64; i7++) {
                    SevenZArchiveEntry sevenZArchiveEntry5 = map.get(Integer.valueOf(i7));
                    if (sevenZArchiveEntry5 != null) {
                        sevenZArchiveEntry5.setHasStream(bits == null || !bits.get(i7));
                        if (sevenZArchiveEntry5.hasStream()) {
                            if (archive2.subStreamsInfo == null) {
                                throw new IOException("Archive contains file with streams but no subStreamsInfo");
                            }
                            sevenZArchiveEntry5.setDirectory(false);
                            sevenZArchiveEntry5.setAntiItem(false);
                            sevenZArchiveEntry5.setHasCrc(archive2.subStreamsInfo.hasCrc.get(i6));
                            sevenZArchiveEntry5.setCrcValue(archive2.subStreamsInfo.crcs[i6]);
                            sevenZArchiveEntry5.setSize(archive2.subStreamsInfo.unpackSizes[i6]);
                            if (sevenZArchiveEntry5.getSize() < 0) {
                                throw new IOException("broken archive, entry with negative size");
                            }
                            i6++;
                        } else {
                            sevenZArchiveEntry5.setDirectory(bits2 == null || !bits2.get(i5));
                            sevenZArchiveEntry5.setAntiItem(bits3 != null && bits3.get(i5));
                            sevenZArchiveEntry5.setHasCrc(false);
                            sevenZArchiveEntry5.setSize(0L);
                            i5++;
                        }
                    }
                }
                ArrayList arrayList = new ArrayList();
                for (SevenZArchiveEntry sevenZArchiveEntry6 : map.values()) {
                    if (sevenZArchiveEntry6 != null) {
                        arrayList.add(sevenZArchiveEntry6);
                    }
                }
                archive2.files = (SevenZArchiveEntry[]) arrayList.toArray(SevenZArchiveEntry.EMPTY_SEVEN_Z_ARCHIVE_ENTRY_ARRAY);
                calculateStreamMap(archive2);
                return;
            }
        }
        throw new IOException("Error parsing file names");
    }

    private void checkEntryIsInitialized(Map<Integer, SevenZArchiveEntry> map, int i) {
        if (map.get(Integer.valueOf(i)) == null) {
            map.put(Integer.valueOf(i), new SevenZArchiveEntry());
        }
    }

    private void calculateStreamMap(Archive archive) throws IOException {
        StreamMap streamMap = new StreamMap();
        int length = archive.folders != null ? archive.folders.length : 0;
        streamMap.folderFirstPackStreamIndex = new int[length];
        int length2 = 0;
        for (int i = 0; i < length; i++) {
            streamMap.folderFirstPackStreamIndex[i] = length2;
            length2 += archive.folders[i].packedStreams.length;
        }
        int length3 = archive.packSizes.length;
        streamMap.packStreamOffsets = new long[length3];
        long j = 0;
        for (int i2 = 0; i2 < length3; i2++) {
            streamMap.packStreamOffsets[i2] = j;
            j += archive.packSizes[i2];
        }
        streamMap.folderFirstFileIndex = new int[length];
        streamMap.fileFolderIndex = new int[archive.files.length];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < archive.files.length; i5++) {
            if (!archive.files[i5].hasStream() && i3 == 0) {
                streamMap.fileFolderIndex[i5] = -1;
            } else {
                if (i3 == 0) {
                    while (i4 < archive.folders.length) {
                        streamMap.folderFirstFileIndex[i4] = i5;
                        if (archive.folders[i4].numUnpackSubStreams > 0) {
                            break;
                        } else {
                            i4++;
                        }
                    }
                    if (i4 >= archive.folders.length) {
                        throw new IOException("Too few folders in archive");
                    }
                }
                streamMap.fileFolderIndex[i5] = i4;
                if (archive.files[i5].hasStream() && (i3 = i3 + 1) >= archive.folders[i4].numUnpackSubStreams) {
                    i4++;
                    i3 = 0;
                }
            }
        }
        archive.streamMap = streamMap;
    }

    private void buildDecodingStream(int i, boolean z) throws IOException {
        boolean z2;
        if (this.archive.streamMap == null) {
            throw new IOException("Archive doesn't contain stream information to read entries");
        }
        int i2 = this.archive.streamMap.fileFolderIndex[i];
        if (i2 < 0) {
            this.deferredBlockStreams.clear();
            return;
        }
        SevenZArchiveEntry sevenZArchiveEntry = this.archive.files[i];
        if (this.currentFolderIndex == i2) {
            if (i > 0) {
                sevenZArchiveEntry.setContentMethods(this.archive.files[i - 1].getContentMethods());
            }
            if (z && sevenZArchiveEntry.getContentMethods() == null) {
                sevenZArchiveEntry.setContentMethods(this.archive.files[this.archive.streamMap.folderFirstFileIndex[i2]].getContentMethods());
            }
            z2 = true;
        } else {
            this.currentFolderIndex = i2;
            reopenFolderInputStream(i2, sevenZArchiveEntry);
            z2 = false;
        }
        boolean zSkipEntriesWhenNeeded = z ? skipEntriesWhenNeeded(i, z2, i2) : false;
        if (z && this.currentEntryIndex == i && !zSkipEntriesWhenNeeded) {
            return;
        }
        InputStream boundedInputStream = new BoundedInputStream(this.currentFolderInputStream, sevenZArchiveEntry.getSize());
        if (sevenZArchiveEntry.getHasCrc()) {
            boundedInputStream = new CRC32VerifyingInputStream(boundedInputStream, sevenZArchiveEntry.getSize(), sevenZArchiveEntry.getCrcValue());
        }
        this.deferredBlockStreams.add(boundedInputStream);
    }

    private void reopenFolderInputStream(int i, SevenZArchiveEntry sevenZArchiveEntry) throws IOException {
        this.deferredBlockStreams.clear();
        InputStream inputStream = this.currentFolderInputStream;
        if (inputStream != null) {
            inputStream.close();
            this.currentFolderInputStream = null;
        }
        Folder folder = this.archive.folders[i];
        int i2 = this.archive.streamMap.folderFirstPackStreamIndex[i];
        this.currentFolderInputStream = buildDecoderStack(folder, this.archive.streamMap.packStreamOffsets[i2] + this.archive.packPos + 32, i2, sevenZArchiveEntry);
    }

    private boolean skipEntriesWhenNeeded(int i, boolean z, int i2) throws IOException {
        SevenZArchiveEntry sevenZArchiveEntry = this.archive.files[i];
        if (this.currentEntryIndex == i && !hasCurrentEntryBeenRead()) {
            return false;
        }
        int i3 = this.archive.streamMap.folderFirstFileIndex[this.currentFolderIndex];
        if (z) {
            int i4 = this.currentEntryIndex;
            if (i4 < i) {
                i3 = i4 + 1;
            } else {
                reopenFolderInputStream(i2, sevenZArchiveEntry);
            }
        }
        while (i3 < i) {
            SevenZArchiveEntry sevenZArchiveEntry2 = this.archive.files[i3];
            InputStream boundedInputStream = new BoundedInputStream(this.currentFolderInputStream, sevenZArchiveEntry2.getSize());
            if (sevenZArchiveEntry2.getHasCrc()) {
                boundedInputStream = new CRC32VerifyingInputStream(boundedInputStream, sevenZArchiveEntry2.getSize(), sevenZArchiveEntry2.getCrcValue());
            }
            this.deferredBlockStreams.add(boundedInputStream);
            sevenZArchiveEntry2.setContentMethods(sevenZArchiveEntry.getContentMethods());
            i3++;
        }
        return true;
    }

    private boolean hasCurrentEntryBeenRead() {
        if (this.deferredBlockStreams.isEmpty()) {
            return false;
        }
        ArrayList<InputStream> arrayList = this.deferredBlockStreams;
        InputStream inputStream = arrayList.get(arrayList.size() - 1);
        return inputStream instanceof BoundedInputStream ? ((BoundedInputStream) inputStream).getBytesRemaining() != this.archive.files[this.currentEntryIndex].getSize() : (inputStream instanceof CRC32VerifyingInputStream) && ((CRC32VerifyingInputStream) inputStream).getBytesRemaining() != this.archive.files[this.currentEntryIndex].getSize();
    }

    private InputStream buildDecoderStack(Folder folder, long j, int i, SevenZArchiveEntry sevenZArchiveEntry) throws IOException {
        this.channel.position(j);
        FilterInputStream filterInputStream = new FilterInputStream(new BufferedInputStream(new BoundedSeekableByteChannelInputStream(this.channel, this.archive.packSizes[i]))) { // from class: org.apache.commons.compress.archivers.sevenz.SevenZFile.1
            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read() throws IOException {
                int i2 = this.in.read();
                if (i2 >= 0) {
                    count(1);
                }
                return i2;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read(byte[] bArr) throws IOException {
                return read(bArr, 0, bArr.length);
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read(byte[] bArr, int i2, int i3) throws IOException {
                if (i3 == 0) {
                    return 0;
                }
                int i4 = this.in.read(bArr, i2, i3);
                if (i4 >= 0) {
                    count(i4);
                }
                return i4;
            }

            private void count(int i2) {
                SevenZFile.this.compressedBytesReadFromCurrentEntry += (long) i2;
            }
        };
        LinkedList linkedList = new LinkedList();
        InputStream inputStreamAddDecoder = filterInputStream;
        for (Coder coder : folder.getOrderedCoders()) {
            if (coder.numInStreams != 1 || coder.numOutStreams != 1) {
                throw new IOException("Multi input/output stream coders are not yet supported");
            }
            SevenZMethod sevenZMethodById = SevenZMethod.byId(coder.decompressionMethodId);
            inputStreamAddDecoder = Coders.addDecoder(this.fileName, inputStreamAddDecoder, folder.getUnpackSizeForCoder(coder), coder, this.password, this.options.getMaxMemoryLimitInKb());
            linkedList.addFirst(new SevenZMethodConfiguration(sevenZMethodById, Coders.findByMethod(sevenZMethodById).getOptionsFromCoder(coder, inputStreamAddDecoder)));
        }
        sevenZArchiveEntry.setContentMethods(linkedList);
        return folder.hasCrc ? new CRC32VerifyingInputStream(inputStreamAddDecoder, folder.getUnpackSize(), folder.crc) : inputStreamAddDecoder;
    }

    public int read() throws IOException {
        int i = getCurrentStream().read();
        if (i >= 0) {
            this.uncompressedBytesReadFromCurrentEntry++;
        }
        return i;
    }

    private InputStream getCurrentStream() throws IOException {
        if (this.archive.files[this.currentEntryIndex].getSize() == 0) {
            return new ByteArrayInputStream(ByteUtils.EMPTY_BYTE_ARRAY);
        }
        if (this.deferredBlockStreams.isEmpty()) {
            throw new IllegalStateException("No current 7z entry (call getNextEntry() first).");
        }
        while (this.deferredBlockStreams.size() > 1) {
            InputStream inputStreamRemove = this.deferredBlockStreams.remove(0);
            try {
                IOUtils.skip(inputStreamRemove, Long.MAX_VALUE);
                if (inputStreamRemove != null) {
                    inputStreamRemove.close();
                }
                this.compressedBytesReadFromCurrentEntry = 0L;
            } finally {
            }
        }
        return this.deferredBlockStreams.get(0);
    }

    public InputStream getInputStream(SevenZArchiveEntry sevenZArchiveEntry) throws IOException {
        int i = 0;
        while (true) {
            if (i >= this.archive.files.length) {
                i = -1;
                break;
            }
            if (sevenZArchiveEntry == this.archive.files[i]) {
                break;
            }
            i++;
        }
        if (i < 0) {
            throw new IllegalArgumentException("Can not find " + sevenZArchiveEntry.getName() + " in " + this.fileName);
        }
        buildDecodingStream(i, true);
        this.currentEntryIndex = i;
        this.currentFolderIndex = this.archive.streamMap.fileFolderIndex[i];
        return getCurrentStream();
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int i3 = getCurrentStream().read(bArr, i, i2);
        if (i3 > 0) {
            this.uncompressedBytesReadFromCurrentEntry += (long) i3;
        }
        return i3;
    }

    public InputStreamStatistics getStatisticsForCurrentEntry() {
        return new InputStreamStatistics() { // from class: org.apache.commons.compress.archivers.sevenz.SevenZFile.2
            @Override // org.apache.commons.compress.utils.InputStreamStatistics
            public long getCompressedCount() {
                return SevenZFile.this.compressedBytesReadFromCurrentEntry;
            }

            @Override // org.apache.commons.compress.utils.InputStreamStatistics
            public long getUncompressedCount() {
                return SevenZFile.this.uncompressedBytesReadFromCurrentEntry;
            }
        };
    }

    private static long readUint64(ByteBuffer byteBuffer) throws IOException {
        long unsignedByte = getUnsignedByte(byteBuffer);
        int i = 128;
        long unsignedByte2 = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            if ((((long) i) & unsignedByte) == 0) {
                return ((unsignedByte & ((long) (i - 1))) << (i2 * 8)) | unsignedByte2;
            }
            unsignedByte2 |= ((long) getUnsignedByte(byteBuffer)) << (i2 * 8);
            i >>>= 1;
        }
        return unsignedByte2;
    }

    private static char getChar(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() < 2) {
            throw new EOFException();
        }
        return byteBuffer.getChar();
    }

    private static int getInt(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() < 4) {
            throw new EOFException();
        }
        return byteBuffer.getInt();
    }

    private static long getLong(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() < 8) {
            throw new EOFException();
        }
        return byteBuffer.getLong();
    }

    private static void get(ByteBuffer byteBuffer, byte[] bArr) throws IOException {
        if (byteBuffer.remaining() < bArr.length) {
            throw new EOFException();
        }
        byteBuffer.get(bArr);
    }

    private static int getUnsignedByte(ByteBuffer byteBuffer) throws IOException {
        if (!byteBuffer.hasRemaining()) {
            throw new EOFException();
        }
        return byteBuffer.get() & 255;
    }

    public static boolean matches(byte[] bArr, int i) {
        if (i < sevenZSignature.length) {
            return false;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr2 = sevenZSignature;
            if (i2 >= bArr2.length) {
                return true;
            }
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
            i2++;
        }
    }

    private static long skipBytesFully(ByteBuffer byteBuffer, long j) throws IOException {
        if (j < 1) {
            return 0L;
        }
        int iPosition = byteBuffer.position();
        long jRemaining = byteBuffer.remaining();
        if (jRemaining < j) {
            j = jRemaining;
        }
        byteBuffer.position(iPosition + ((int) j));
        return j;
    }

    private void readFully(ByteBuffer byteBuffer) throws IOException {
        byteBuffer.rewind();
        IOUtils.readFully(this.channel, byteBuffer);
        byteBuffer.flip();
    }

    public String toString() {
        return this.archive.toString();
    }

    public String getDefaultName() {
        if (DEFAULT_FILE_NAME.equals(this.fileName) || this.fileName == null) {
            return null;
        }
        String name = new File(this.fileName).getName();
        int iLastIndexOf = name.lastIndexOf(".");
        if (iLastIndexOf > 0) {
            return name.substring(0, iLastIndexOf);
        }
        return name + "~";
    }

    private static byte[] utf16Decode(char[] cArr) throws IOException {
        if (cArr == null) {
            return null;
        }
        ByteBuffer byteBufferEncode = PASSWORD_ENCODER.encode(CharBuffer.wrap(cArr));
        if (byteBufferEncode.hasArray()) {
            return byteBufferEncode.array();
        }
        byte[] bArr = new byte[byteBufferEncode.remaining()];
        byteBufferEncode.get(bArr);
        return bArr;
    }

    private static int assertFitsIntoNonNegativeInt(String str, long j) throws IOException {
        if (j > 2147483647L || j < 0) {
            throw new IOException("Cannot handle " + str + " " + j);
        }
        return (int) j;
    }

    private static class ArchiveStatistics {
        private BitSet folderHasCrc;
        private long numberOfCoders;
        private int numberOfEntries;
        private int numberOfEntriesWithStream;
        private int numberOfFolders;
        private long numberOfInStreams;
        private long numberOfOutStreams;
        private int numberOfPackedStreams;
        private long numberOfUnpackSubStreams;

        private long bindPairSize() {
            return 16L;
        }

        private long coderSize() {
            return 22L;
        }

        private long entrySize() {
            return 100L;
        }

        private long folderSize() {
            return 30L;
        }

        private ArchiveStatistics() {
        }

        public String toString() {
            return "Archive with " + this.numberOfEntries + " entries in " + this.numberOfFolders + " folders. Estimated size " + (estimateSize() / 1024) + " kB.";
        }

        long estimateSize() {
            int i = this.numberOfPackedStreams;
            long jFolderSize = (((long) i) * 16) + ((long) (i / 8)) + (((long) this.numberOfFolders) * folderSize()) + (this.numberOfCoders * coderSize()) + ((this.numberOfOutStreams - ((long) this.numberOfFolders)) * bindPairSize());
            long j = this.numberOfInStreams;
            long j2 = this.numberOfOutStreams;
            return (jFolderSize + (((j - j2) + ((long) this.numberOfFolders)) * 8) + (j2 * 8) + (((long) this.numberOfEntries) * entrySize()) + streamMapSize()) * 2;
        }

        void assertValidity(int i) throws IOException {
            int i2 = this.numberOfEntriesWithStream;
            if (i2 > 0 && this.numberOfFolders == 0) {
                throw new IOException("archive with entries but no folders");
            }
            if (i2 > this.numberOfUnpackSubStreams) {
                throw new IOException("archive doesn't contain enough substreams for entries");
            }
            long jEstimateSize = estimateSize() / 1024;
            if (i < jEstimateSize) {
                throw new MemoryLimitException(jEstimateSize, i);
            }
        }

        private long streamMapSize() {
            return (this.numberOfFolders * 8) + (this.numberOfPackedStreams * 8) + (this.numberOfEntries * 4);
        }
    }
}
