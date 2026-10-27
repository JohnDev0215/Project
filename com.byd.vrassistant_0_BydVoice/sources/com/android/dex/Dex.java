package com.android.dex;

import com.android.dex.ClassData;
import com.android.dex.Code;
import com.android.dex.util.ByteInput;
import com.android.dex.util.ByteOutput;
import com.android.dex.util.FileUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UTFDataFormatException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.zip.Adler32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.UShort;

/* JADX INFO: loaded from: classes.dex */
public final class Dex {
    private static final int CHECKSUM_OFFSET = 8;
    private static final int CHECKSUM_SIZE = 4;
    static final short[] EMPTY_SHORT_ARRAY = new short[0];
    private static final int SIGNATURE_OFFSET = 12;
    private static final int SIGNATURE_SIZE = 20;
    private ByteBuffer data;
    private final FieldIdTable fieldIds;
    private final MethodIdTable methodIds;
    private int nextSectionStart;
    private final ProtoIdTable protoIds;
    private final StringTable strings;
    private final TableOfContents tableOfContents;
    private final TypeIndexToDescriptorIndexTable typeIds;
    private final TypeIndexToDescriptorTable typeNames;

    public Dex(byte[] bArr) throws IOException {
        this(ByteBuffer.wrap(bArr));
    }

    private Dex(ByteBuffer byteBuffer) throws IOException {
        TableOfContents tableOfContents = new TableOfContents();
        this.tableOfContents = tableOfContents;
        this.nextSectionStart = 0;
        this.strings = new StringTable();
        this.typeIds = new TypeIndexToDescriptorIndexTable();
        this.typeNames = new TypeIndexToDescriptorTable();
        this.protoIds = new ProtoIdTable();
        this.fieldIds = new FieldIdTable();
        this.methodIds = new MethodIdTable();
        this.data = byteBuffer;
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        tableOfContents.readFrom(this);
    }

    public Dex(int i) throws IOException {
        this.tableOfContents = new TableOfContents();
        this.nextSectionStart = 0;
        this.strings = new StringTable();
        this.typeIds = new TypeIndexToDescriptorIndexTable();
        this.typeNames = new TypeIndexToDescriptorTable();
        this.protoIds = new ProtoIdTable();
        this.fieldIds = new FieldIdTable();
        this.methodIds = new MethodIdTable();
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[i]);
        this.data = byteBufferWrap;
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
    }

    public Dex(InputStream inputStream) throws IOException {
        this.tableOfContents = new TableOfContents();
        this.nextSectionStart = 0;
        this.strings = new StringTable();
        this.typeIds = new TypeIndexToDescriptorIndexTable();
        this.typeNames = new TypeIndexToDescriptorTable();
        this.protoIds = new ProtoIdTable();
        this.fieldIds = new FieldIdTable();
        this.methodIds = new MethodIdTable();
        loadFrom(inputStream);
    }

    public Dex(File file) throws IOException {
        this.tableOfContents = new TableOfContents();
        this.nextSectionStart = 0;
        this.strings = new StringTable();
        this.typeIds = new TypeIndexToDescriptorIndexTable();
        this.typeNames = new TypeIndexToDescriptorTable();
        this.protoIds = new ProtoIdTable();
        this.fieldIds = new FieldIdTable();
        this.methodIds = new MethodIdTable();
        if (FileUtils.hasArchiveSuffix(file.getName())) {
            ZipFile zipFile = new ZipFile(file);
            ZipEntry entry = zipFile.getEntry(DexFormat.DEX_IN_JAR_NAME);
            if (entry != null) {
                loadFrom(zipFile.getInputStream(entry));
                zipFile.close();
                return;
            }
            throw new DexException("Expected classes.dex in " + file);
        }
        if (file.getName().endsWith(".dex")) {
            loadFrom(new FileInputStream(file));
            return;
        }
        throw new DexException("unknown output extension: " + file);
    }

    public static Dex create(ByteBuffer byteBuffer) throws IOException {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        if (byteBuffer.get(0) == 100 && byteBuffer.get(1) == 101 && byteBuffer.get(2) == 121 && byteBuffer.get(3) == 10) {
            byteBuffer.position(8);
            int i = byteBuffer.getInt();
            int i2 = byteBuffer.getInt();
            byteBuffer.position(i);
            byteBuffer.limit(i + i2);
            byteBuffer = byteBuffer.slice();
        }
        return new Dex(byteBuffer);
    }

    private void loadFrom(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                inputStream.close();
                ByteBuffer byteBufferWrap = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
                this.data = byteBufferWrap;
                byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
                this.tableOfContents.readFrom(this);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkBounds(int i, int i2) {
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException("index:" + i + ", length=" + i2);
        }
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        ByteBuffer byteBufferDuplicate = this.data.duplicate();
        byteBufferDuplicate.clear();
        while (byteBufferDuplicate.hasRemaining()) {
            int iMin = Math.min(8192, byteBufferDuplicate.remaining());
            byteBufferDuplicate.get(bArr, 0, iMin);
            outputStream.write(bArr, 0, iMin);
        }
    }

    public void writeTo(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        writeTo(fileOutputStream);
        fileOutputStream.close();
    }

    public TableOfContents getTableOfContents() {
        return this.tableOfContents;
    }

    public Section open(int i) {
        if (i < 0 || i >= this.data.capacity()) {
            throw new IllegalArgumentException("position=" + i + " length=" + this.data.capacity());
        }
        ByteBuffer byteBufferDuplicate = this.data.duplicate();
        byteBufferDuplicate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferDuplicate.position(i);
        byteBufferDuplicate.limit(this.data.capacity());
        return new Section("section", byteBufferDuplicate);
    }

    public Section appendSection(int i, String str) {
        if ((i & 3) != 0) {
            throw new IllegalStateException("Not four byte aligned!");
        }
        int i2 = this.nextSectionStart + i;
        ByteBuffer byteBufferDuplicate = this.data.duplicate();
        byteBufferDuplicate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferDuplicate.position(this.nextSectionStart);
        byteBufferDuplicate.limit(i2);
        Section section = new Section(str, byteBufferDuplicate);
        this.nextSectionStart = i2;
        return section;
    }

    public int getLength() {
        return this.data.capacity();
    }

    public int getNextSectionStart() {
        return this.nextSectionStart;
    }

    public byte[] getBytes() {
        ByteBuffer byteBufferDuplicate = this.data.duplicate();
        byte[] bArr = new byte[byteBufferDuplicate.capacity()];
        byteBufferDuplicate.position(0);
        byteBufferDuplicate.get(bArr);
        return bArr;
    }

    public List<String> strings() {
        return this.strings;
    }

    public List<Integer> typeIds() {
        return this.typeIds;
    }

    public List<String> typeNames() {
        return this.typeNames;
    }

    public List<ProtoId> protoIds() {
        return this.protoIds;
    }

    public List<FieldId> fieldIds() {
        return this.fieldIds;
    }

    public List<MethodId> methodIds() {
        return this.methodIds;
    }

    public Iterable<ClassDef> classDefs() {
        return new ClassDefIterable();
    }

    public TypeList readTypeList(int i) {
        if (i == 0) {
            return TypeList.EMPTY;
        }
        return open(i).readTypeList();
    }

    public ClassData readClassData(ClassDef classDef) {
        int classDataOffset = classDef.getClassDataOffset();
        if (classDataOffset == 0) {
            throw new IllegalArgumentException("offset == 0");
        }
        return open(classDataOffset).readClassData();
    }

    public Code readCode(ClassData.Method method) {
        int codeOffset = method.getCodeOffset();
        if (codeOffset == 0) {
            throw new IllegalArgumentException("offset == 0");
        }
        return open(codeOffset).readCode();
    }

    public byte[] computeSignature() throws IOException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] bArr = new byte[8192];
            ByteBuffer byteBufferDuplicate = this.data.duplicate();
            byteBufferDuplicate.limit(byteBufferDuplicate.capacity());
            byteBufferDuplicate.position(32);
            while (byteBufferDuplicate.hasRemaining()) {
                int iMin = Math.min(8192, byteBufferDuplicate.remaining());
                byteBufferDuplicate.get(bArr, 0, iMin);
                messageDigest.update(bArr, 0, iMin);
            }
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    public int computeChecksum() throws IOException {
        Adler32 adler32 = new Adler32();
        byte[] bArr = new byte[8192];
        ByteBuffer byteBufferDuplicate = this.data.duplicate();
        byteBufferDuplicate.limit(byteBufferDuplicate.capacity());
        byteBufferDuplicate.position(12);
        while (byteBufferDuplicate.hasRemaining()) {
            int iMin = Math.min(8192, byteBufferDuplicate.remaining());
            byteBufferDuplicate.get(bArr, 0, iMin);
            adler32.update(bArr, 0, iMin);
        }
        return (int) adler32.getValue();
    }

    public void writeHashes() throws IOException {
        open(12).write(computeSignature());
        open(8).writeInt(computeChecksum());
    }

    public int nameIndexFromFieldIndex(int i) {
        checkBounds(i, this.tableOfContents.fieldIds.size);
        return this.data.getInt(this.tableOfContents.fieldIds.off + (i * 8) + 2 + 2);
    }

    public int findStringIndex(String str) {
        return Collections.binarySearch(this.strings, str);
    }

    public int findTypeIndex(String str) {
        return Collections.binarySearch(this.typeNames, str);
    }

    public int findFieldIndex(FieldId fieldId) {
        return Collections.binarySearch(this.fieldIds, fieldId);
    }

    public int findMethodIndex(MethodId methodId) {
        return Collections.binarySearch(this.methodIds, methodId);
    }

    public int findClassDefIndexFromTypeIndex(int i) {
        checkBounds(i, this.tableOfContents.typeIds.size);
        if (!this.tableOfContents.classDefs.exists()) {
            return -1;
        }
        for (int i2 = 0; i2 < this.tableOfContents.classDefs.size; i2++) {
            if (typeIndexFromClassDefIndex(i2) == i) {
                return i2;
            }
        }
        return -1;
    }

    public int typeIndexFromFieldIndex(int i) {
        checkBounds(i, this.tableOfContents.fieldIds.size);
        return this.data.getShort(this.tableOfContents.fieldIds.off + (i * 8) + 2) & UShort.MAX_VALUE;
    }

    public int declaringClassIndexFromMethodIndex(int i) {
        checkBounds(i, this.tableOfContents.methodIds.size);
        return this.data.getShort(this.tableOfContents.methodIds.off + (i * 8)) & UShort.MAX_VALUE;
    }

    public int nameIndexFromMethodIndex(int i) {
        checkBounds(i, this.tableOfContents.methodIds.size);
        return this.data.getInt(this.tableOfContents.methodIds.off + (i * 8) + 2 + 2);
    }

    public short[] parameterTypeIndicesFromMethodIndex(int i) {
        checkBounds(i, this.tableOfContents.methodIds.size);
        int i2 = this.data.getShort(this.tableOfContents.methodIds.off + (i * 8) + 2) & UShort.MAX_VALUE;
        checkBounds(i2, this.tableOfContents.protoIds.size);
        int i3 = this.data.getInt(this.tableOfContents.protoIds.off + (i2 * 12) + 4 + 4);
        if (i3 == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        int i4 = this.data.getInt(i3);
        if (i4 <= 0) {
            throw new AssertionError("Unexpected parameter type list size: " + i4);
        }
        int i5 = i3 + 4;
        short[] sArr = new short[i4];
        for (int i6 = 0; i6 < i4; i6++) {
            sArr[i6] = this.data.getShort(i5);
            i5 += 2;
        }
        return sArr;
    }

    public int returnTypeIndexFromMethodIndex(int i) {
        checkBounds(i, this.tableOfContents.methodIds.size);
        int i2 = this.data.getShort(this.tableOfContents.methodIds.off + (i * 8) + 2) & UShort.MAX_VALUE;
        checkBounds(i2, this.tableOfContents.protoIds.size);
        return this.data.getInt(this.tableOfContents.protoIds.off + (i2 * 12) + 4);
    }

    public int descriptorIndexFromTypeIndex(int i) {
        checkBounds(i, this.tableOfContents.typeIds.size);
        return this.data.getInt(this.tableOfContents.typeIds.off + (i * 4));
    }

    public int typeIndexFromClassDefIndex(int i) {
        checkBounds(i, this.tableOfContents.classDefs.size);
        return this.data.getInt(this.tableOfContents.classDefs.off + (i * 32));
    }

    public int annotationDirectoryOffsetFromClassDefIndex(int i) {
        checkBounds(i, this.tableOfContents.classDefs.size);
        return this.data.getInt(this.tableOfContents.classDefs.off + (i * 32) + 4 + 4 + 4 + 4 + 4);
    }

    public short[] interfaceTypeIndicesFromClassDefIndex(int i) {
        checkBounds(i, this.tableOfContents.classDefs.size);
        int i2 = this.data.getInt(this.tableOfContents.classDefs.off + (i * 32) + 4 + 4 + 4);
        if (i2 == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        int i3 = this.data.getInt(i2);
        if (i3 <= 0) {
            throw new AssertionError("Unexpected interfaces list size: " + i3);
        }
        int i4 = i2 + 4;
        short[] sArr = new short[i3];
        for (int i5 = 0; i5 < i3; i5++) {
            sArr[i5] = this.data.getShort(i4);
            i4 += 2;
        }
        return sArr;
    }

    public final class Section implements ByteInput, ByteOutput {
        private final ByteBuffer data;
        private final int initialPosition;
        private final String name;

        private Section(String str, ByteBuffer byteBuffer) {
            this.name = str;
            this.data = byteBuffer;
            this.initialPosition = byteBuffer.position();
        }

        public int getPosition() {
            return this.data.position();
        }

        public int readInt() {
            return this.data.getInt();
        }

        public short readShort() {
            return this.data.getShort();
        }

        public int readUnsignedShort() {
            return readShort() & UShort.MAX_VALUE;
        }

        @Override // com.android.dex.util.ByteInput
        public byte readByte() {
            return this.data.get();
        }

        public byte[] readByteArray(int i) {
            byte[] bArr = new byte[i];
            this.data.get(bArr);
            return bArr;
        }

        public short[] readShortArray(int i) {
            if (i == 0) {
                return Dex.EMPTY_SHORT_ARRAY;
            }
            short[] sArr = new short[i];
            for (int i2 = 0; i2 < i; i2++) {
                sArr[i2] = readShort();
            }
            return sArr;
        }

        public int readUleb128() {
            return Leb128.readUnsignedLeb128(this);
        }

        public int readUleb128p1() {
            return Leb128.readUnsignedLeb128(this) - 1;
        }

        public int readSleb128() {
            return Leb128.readSignedLeb128(this);
        }

        public void writeUleb128p1(int i) {
            writeUleb128(i + 1);
        }

        public TypeList readTypeList() {
            short[] shortArray = readShortArray(readInt());
            alignToFourBytes();
            return new TypeList(Dex.this, shortArray);
        }

        public String readString() {
            int i = readInt();
            int iPosition = this.data.position();
            int iLimit = this.data.limit();
            this.data.position(i);
            ByteBuffer byteBuffer = this.data;
            byteBuffer.limit(byteBuffer.capacity());
            try {
                try {
                    int uleb128 = readUleb128();
                    String strDecode = Mutf8.decode(this, new char[uleb128]);
                    if (strDecode.length() == uleb128) {
                        return strDecode;
                    }
                    throw new DexException("Declared length " + uleb128 + " doesn't match decoded length of " + strDecode.length());
                } catch (UTFDataFormatException e) {
                    throw new DexException(e);
                }
            } finally {
                this.data.position(iPosition);
                this.data.limit(iLimit);
            }
            this.data.position(iPosition);
            this.data.limit(iLimit);
        }

        public FieldId readFieldId() {
            return new FieldId(Dex.this, readUnsignedShort(), readUnsignedShort(), readInt());
        }

        public MethodId readMethodId() {
            return new MethodId(Dex.this, readUnsignedShort(), readUnsignedShort(), readInt());
        }

        public ProtoId readProtoId() {
            return new ProtoId(Dex.this, readInt(), readInt(), readInt());
        }

        public ClassDef readClassDef() {
            return new ClassDef(Dex.this, getPosition(), readInt(), readInt(), readInt(), readInt(), readInt(), readInt(), readInt(), readInt());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Code readCode() {
            Code.Try[] tries;
            Code.CatchHandler[] catchHandlers;
            int unsignedShort = readUnsignedShort();
            int unsignedShort2 = readUnsignedShort();
            int unsignedShort3 = readUnsignedShort();
            int unsignedShort4 = readUnsignedShort();
            int i = readInt();
            short[] shortArray = readShortArray(readInt());
            if (unsignedShort4 > 0) {
                if (shortArray.length % 2 == 1) {
                    readShort();
                }
                Section sectionOpen = Dex.this.open(this.data.position());
                skip(unsignedShort4 * 8);
                catchHandlers = readCatchHandlers();
                tries = sectionOpen.readTries(unsignedShort4, catchHandlers);
            } else {
                tries = new Code.Try[0];
                catchHandlers = new Code.CatchHandler[0];
            }
            return new Code(unsignedShort, unsignedShort2, unsignedShort3, i, shortArray, tries, catchHandlers);
        }

        private Code.CatchHandler[] readCatchHandlers() {
            int iPosition = this.data.position();
            int uleb128 = readUleb128();
            Code.CatchHandler[] catchHandlerArr = new Code.CatchHandler[uleb128];
            for (int i = 0; i < uleb128; i++) {
                catchHandlerArr[i] = readCatchHandler(this.data.position() - iPosition);
            }
            return catchHandlerArr;
        }

        private Code.Try[] readTries(int i, Code.CatchHandler[] catchHandlerArr) {
            Code.Try[] tryArr = new Code.Try[i];
            for (int i2 = 0; i2 < i; i2++) {
                tryArr[i2] = new Code.Try(readInt(), readUnsignedShort(), findCatchHandlerIndex(catchHandlerArr, readUnsignedShort()));
            }
            return tryArr;
        }

        private int findCatchHandlerIndex(Code.CatchHandler[] catchHandlerArr, int i) {
            for (int i2 = 0; i2 < catchHandlerArr.length; i2++) {
                if (catchHandlerArr[i2].getOffset() == i) {
                    return i2;
                }
            }
            throw new IllegalArgumentException();
        }

        private Code.CatchHandler readCatchHandler(int i) {
            int sleb128 = readSleb128();
            int iAbs = Math.abs(sleb128);
            int[] iArr = new int[iAbs];
            int[] iArr2 = new int[iAbs];
            for (int i2 = 0; i2 < iAbs; i2++) {
                iArr[i2] = readUleb128();
                iArr2[i2] = readUleb128();
            }
            return new Code.CatchHandler(iArr, iArr2, sleb128 <= 0 ? readUleb128() : -1, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ClassData readClassData() {
            return new ClassData(readFields(readUleb128()), readFields(readUleb128()), readMethods(readUleb128()), readMethods(readUleb128()));
        }

        private ClassData.Field[] readFields(int i) {
            ClassData.Field[] fieldArr = new ClassData.Field[i];
            int uleb128 = 0;
            for (int i2 = 0; i2 < i; i2++) {
                uleb128 += readUleb128();
                fieldArr[i2] = new ClassData.Field(uleb128, readUleb128());
            }
            return fieldArr;
        }

        private ClassData.Method[] readMethods(int i) {
            ClassData.Method[] methodArr = new ClassData.Method[i];
            int uleb128 = 0;
            for (int i2 = 0; i2 < i; i2++) {
                uleb128 += readUleb128();
                methodArr[i2] = new ClassData.Method(uleb128, readUleb128(), readUleb128());
            }
            return methodArr;
        }

        private byte[] getBytesFrom(int i) {
            byte[] bArr = new byte[this.data.position() - i];
            this.data.position(i);
            this.data.get(bArr);
            return bArr;
        }

        public Annotation readAnnotation() {
            byte b = readByte();
            int iPosition = this.data.position();
            new EncodedValueReader(this, 29).skipValue();
            return new Annotation(Dex.this, b, new EncodedValue(getBytesFrom(iPosition)));
        }

        public EncodedValue readEncodedArray() {
            int iPosition = this.data.position();
            new EncodedValueReader(this, 28).skipValue();
            return new EncodedValue(getBytesFrom(iPosition));
        }

        public void skip(int i) {
            if (i < 0) {
                throw new IllegalArgumentException();
            }
            ByteBuffer byteBuffer = this.data;
            byteBuffer.position(byteBuffer.position() + i);
        }

        public void alignToFourBytes() {
            ByteBuffer byteBuffer = this.data;
            byteBuffer.position((byteBuffer.position() + 3) & (-4));
        }

        public void alignToFourBytesWithZeroFill() {
            while ((this.data.position() & 3) != 0) {
                this.data.put((byte) 0);
            }
        }

        public void assertFourByteAligned() {
            if ((this.data.position() & 3) != 0) {
                throw new IllegalStateException("Not four byte aligned!");
            }
        }

        public void write(byte[] bArr) {
            this.data.put(bArr);
        }

        @Override // com.android.dex.util.ByteOutput
        public void writeByte(int i) {
            this.data.put((byte) i);
        }

        public void writeShort(short s) {
            this.data.putShort(s);
        }

        public void writeUnsignedShort(int i) {
            short s = (short) i;
            if (i != (65535 & s)) {
                throw new IllegalArgumentException("Expected an unsigned short: " + i);
            }
            writeShort(s);
        }

        public void write(short[] sArr) {
            for (short s : sArr) {
                writeShort(s);
            }
        }

        public void writeInt(int i) {
            this.data.putInt(i);
        }

        public void writeUleb128(int i) {
            try {
                Leb128.writeUnsignedLeb128(this, i);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new DexException("Section limit " + this.data.limit() + " exceeded by " + this.name);
            }
        }

        public void writeSleb128(int i) {
            try {
                Leb128.writeSignedLeb128(this, i);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new DexException("Section limit " + this.data.limit() + " exceeded by " + this.name);
            }
        }

        public void writeStringData(String str) {
            try {
                writeUleb128(str.length());
                write(Mutf8.encode(str));
                writeByte(0);
            } catch (UTFDataFormatException unused) {
                throw new AssertionError();
            }
        }

        public void writeTypeList(TypeList typeList) {
            short[] types = typeList.getTypes();
            writeInt(types.length);
            for (short s : types) {
                writeShort(s);
            }
            alignToFourBytesWithZeroFill();
        }

        public int remaining() {
            return this.data.remaining();
        }

        public int used() {
            return this.data.position() - this.initialPosition;
        }
    }

    private final class StringTable extends AbstractList<String> implements RandomAccess {
        private StringTable() {
        }

        @Override // java.util.AbstractList, java.util.List
        public String get(int i) {
            Dex.checkBounds(i, Dex.this.tableOfContents.stringIds.size);
            Dex dex = Dex.this;
            return dex.open(dex.tableOfContents.stringIds.off + (i * 4)).readString();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return Dex.this.tableOfContents.stringIds.size;
        }
    }

    private final class TypeIndexToDescriptorIndexTable extends AbstractList<Integer> implements RandomAccess {
        private TypeIndexToDescriptorIndexTable() {
        }

        @Override // java.util.AbstractList, java.util.List
        public Integer get(int i) {
            return Integer.valueOf(Dex.this.descriptorIndexFromTypeIndex(i));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return Dex.this.tableOfContents.typeIds.size;
        }
    }

    private final class TypeIndexToDescriptorTable extends AbstractList<String> implements RandomAccess {
        private TypeIndexToDescriptorTable() {
        }

        @Override // java.util.AbstractList, java.util.List
        public String get(int i) {
            return Dex.this.strings.get(Dex.this.descriptorIndexFromTypeIndex(i));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return Dex.this.tableOfContents.typeIds.size;
        }
    }

    private final class ProtoIdTable extends AbstractList<ProtoId> implements RandomAccess {
        private ProtoIdTable() {
        }

        @Override // java.util.AbstractList, java.util.List
        public ProtoId get(int i) {
            Dex.checkBounds(i, Dex.this.tableOfContents.protoIds.size);
            Dex dex = Dex.this;
            return dex.open(dex.tableOfContents.protoIds.off + (i * 12)).readProtoId();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return Dex.this.tableOfContents.protoIds.size;
        }
    }

    private final class FieldIdTable extends AbstractList<FieldId> implements RandomAccess {
        private FieldIdTable() {
        }

        @Override // java.util.AbstractList, java.util.List
        public FieldId get(int i) {
            Dex.checkBounds(i, Dex.this.tableOfContents.fieldIds.size);
            Dex dex = Dex.this;
            return dex.open(dex.tableOfContents.fieldIds.off + (i * 8)).readFieldId();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return Dex.this.tableOfContents.fieldIds.size;
        }
    }

    private final class MethodIdTable extends AbstractList<MethodId> implements RandomAccess {
        private MethodIdTable() {
        }

        @Override // java.util.AbstractList, java.util.List
        public MethodId get(int i) {
            Dex.checkBounds(i, Dex.this.tableOfContents.methodIds.size);
            Dex dex = Dex.this;
            return dex.open(dex.tableOfContents.methodIds.off + (i * 8)).readMethodId();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return Dex.this.tableOfContents.methodIds.size;
        }
    }

    private final class ClassDefIterator implements Iterator<ClassDef> {
        private int count;
        private final Section in;

        private ClassDefIterator() {
            this.in = Dex.this.open(Dex.this.tableOfContents.classDefs.off);
            this.count = 0;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.count < Dex.this.tableOfContents.classDefs.size;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public ClassDef next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.count++;
            return this.in.readClassDef();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private final class ClassDefIterable implements Iterable<ClassDef> {
        private ClassDefIterable() {
        }

        @Override // java.lang.Iterable
        public Iterator<ClassDef> iterator() {
            return !Dex.this.tableOfContents.classDefs.exists() ? Collections.emptySet().iterator() : new ClassDefIterator();
        }
    }
}
