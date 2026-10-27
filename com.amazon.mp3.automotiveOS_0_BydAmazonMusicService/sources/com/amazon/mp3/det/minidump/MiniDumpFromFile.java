package com.amazon.mp3.det.minidump;

import androidx.core.internal.view.SupportMenu;
import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class MiniDumpFromFile {
    private FileChannel channel;
    private RandomAccessFile connection;
    private DataInput dataInput;
    private final File file;
    private final List<ModuleStream> moduleStreams;
    private final Collection<ModuleStream> roModuleStreams;
    private final Header header = new Header();
    private final Map<StreamType, DirectoryEntry> deMap = new LinkedHashMap();

    class Header {
        private int checksum;
        private long flags;
        private int msTimestamp;
        private int streamCount;
        private int version;
        private final byte[] signature = new byte[4];
        private final RVA streamDirectoryRva = new RVA();

        Header() {
        }

        public void readExternal(DataInput dataInput, FileChannel fileChannel) throws IOException {
            dataInput.readFully(this.signature);
            this.version = dataInput.readInt();
            this.streamCount = dataInput.readInt();
            this.streamDirectoryRva.readExternal(dataInput, fileChannel);
            this.checksum = dataInput.readInt();
            this.msTimestamp = dataInput.readInt();
            this.flags = dataInput.readLong();
        }

        public int getStreamCount() {
            return this.streamCount;
        }
    }

    static class StreamTypes {
        static final Map<Short, StreamType[]> exhaustiveMapping = new HashMap<Short, StreamType[]>() { // from class: com.amazon.mp3.det.minidump.MiniDumpFromFile.StreamTypes.1
            {
                register(Microsoft.values());
                register(Google.values());
            }

            private void register(StreamType[] values) {
                put(Short.valueOf((short) values[0].type()), values);
            }
        };

        public enum Google implements StreamType {
            BpBegin,
            BpInfoStream,
            BpAssertionInfo,
            ProcCpuInfo,
            ProcStatus,
            EtcLsbRelease,
            ProcCmdLine,
            ProcEnviron,
            ProcAuxV,
            ProcMaps,
            LinuxDSODebug;

            @Override // com.amazon.mp3.det.minidump.StreamType
            public int type() {
                return 18279;
            }
        }

        public enum Microsoft implements StreamType {
            UnusedStream,
            ReservedStream0,
            ReservedStream1,
            ThreadListStream,
            ModuleListStream,
            MemoryListStream,
            ExceptionStream,
            SystemInfoStream,
            ThreadExListStream,
            Memory64ListStream,
            CommentStreamA,
            CommentStreamW,
            HandleDataStream,
            FunctionTableStream,
            UnloadedModuleListStream,
            MiscInfoStream,
            MemoryInfoListStream,
            ThreadInfoListStream,
            HandleOperationListStream;

            @Override // com.amazon.mp3.det.minidump.StreamType
            public int type() {
                return 0;
            }
        }

        StreamTypes() {
        }

        public static StreamType fromInt(int id) {
            return exhaustiveMapping.get(Short.valueOf((short) (id >> 16)))[id & SupportMenu.USER_MASK];
        }
    }

    class DirectoryEntry {
        private final LocationDescription ld = new LocationDescription();
        private StreamType streamType;

        DirectoryEntry() {
        }

        public void readExternal(DataInput dataInput, FileChannel fileChannel) throws IOException {
            this.streamType = StreamTypes.fromInt(dataInput.readInt());
            this.ld.readExternal(dataInput, fileChannel);
        }

        public StreamType getStreamType() {
            return this.streamType;
        }

        public LocationDescription getLocationDescription() {
            return this.ld;
        }
    }

    class ReverseEndianDataInput implements DataInput {
        private final DataInput delegate;

        public ReverseEndianDataInput(DataInput dataInput) {
            this.delegate = dataInput;
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bytes) throws IOException {
            this.delegate.readFully(bytes);
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bytes, int offset, int length) throws IOException {
            this.delegate.readFully(bytes, offset, length);
        }

        @Override // java.io.DataInput
        public int skipBytes(int i) throws IOException {
            return this.delegate.skipBytes(i);
        }

        @Override // java.io.DataInput
        public boolean readBoolean() throws IOException {
            return this.delegate.readBoolean();
        }

        @Override // java.io.DataInput
        public byte readByte() throws IOException {
            return this.delegate.readByte();
        }

        @Override // java.io.DataInput
        public int readUnsignedByte() throws IOException {
            return this.delegate.readUnsignedByte();
        }

        @Override // java.io.DataInput
        public short readShort() throws IOException {
            return Short.reverseBytes(this.delegate.readShort());
        }

        @Override // java.io.DataInput
        public int readUnsignedShort() throws IOException {
            return readChar();
        }

        @Override // java.io.DataInput
        public char readChar() throws IOException {
            return Character.reverseBytes(this.delegate.readChar());
        }

        @Override // java.io.DataInput
        public int readInt() throws IOException {
            return Integer.reverseBytes(this.delegate.readInt());
        }

        @Override // java.io.DataInput
        public long readLong() throws IOException {
            return Long.reverseBytes(this.delegate.readLong());
        }

        @Override // java.io.DataInput
        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readInt());
        }

        @Override // java.io.DataInput
        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readLong());
        }

        @Override // java.io.DataInput
        public String readLine() throws IOException {
            return this.delegate.readLine();
        }

        @Override // java.io.DataInput
        public String readUTF() throws IOException {
            return this.delegate.readUTF();
        }
    }

    public MiniDumpFromFile(File file) {
        ArrayList arrayList = new ArrayList();
        this.moduleStreams = arrayList;
        this.roModuleStreams = Collections.unmodifiableList(arrayList);
        this.file = file;
    }

    public void open() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(this.file, "r");
        this.connection = randomAccessFile;
        this.dataInput = new ReverseEndianDataInput(randomAccessFile);
        FileChannel channel = this.connection.getChannel();
        this.channel = channel;
        this.header.readExternal(this.dataInput, channel);
        for (int i = 0; i < this.header.getStreamCount(); i++) {
            DirectoryEntry directoryEntry = new DirectoryEntry();
            directoryEntry.readExternal(this.dataInput, this.channel);
            this.deMap.put(directoryEntry.getStreamType(), directoryEntry);
        }
        readModuleStreams();
    }

    public void close() {
        try {
            this.connection.close();
        } catch (IOException unused) {
        }
    }

    private void navigateTo(DirectoryEntry entry) throws IOException {
        navigateTo(entry.getLocationDescription());
    }

    private void navigateTo(LocationDescription ld) throws IOException {
        ld.getRVA().navigate(this.channel);
    }

    private void readModuleStreams() throws IOException {
        navigateTo(this.deMap.get(StreamTypes.Microsoft.ModuleListStream));
        int i = this.dataInput.readInt();
        for (int i2 = 0; i2 < i; i2++) {
            ModuleStream moduleStream = new ModuleStream();
            moduleStream.readExternal(this.dataInput, this.channel);
            this.moduleStreams.add(moduleStream);
        }
        Iterator<ModuleStream> it = this.moduleStreams.iterator();
        while (it.hasNext()) {
            it.next().readName(this.dataInput, this.channel);
        }
    }

    public Collection<ModuleStream> getModuleStreams() {
        return this.roModuleStreams;
    }
}
