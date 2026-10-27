package com.android.dx.dex.file;

import com.android.dex.util.ExceptionWithContext;
import com.android.dx.dex.DexOptions;
import com.android.dx.dex.file.MixedItemSection;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstBaseMethodRef;
import com.android.dx.rop.cst.CstEnumRef;
import com.android.dx.rop.cst.CstFieldRef;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Type;
import com.android.dx.util.ByteArrayAnnotatedOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Adler32;

/* JADX INFO: loaded from: classes.dex */
public final class DexFile {
    private final MixedItemSection byteData;
    private final MixedItemSection classData;
    private final ClassDefsSection classDefs;
    private DexOptions dexOptions;
    private int dumpWidth;
    private final FieldIdsSection fieldIds;
    private int fileSize;
    private final HeaderSection header;
    private final MixedItemSection map;
    private final MethodIdsSection methodIds;
    private final ProtoIdsSection protoIds;
    private final Section[] sections;
    private final MixedItemSection stringData;
    private final StringIdsSection stringIds;
    private final TypeIdsSection typeIds;
    private final MixedItemSection typeLists;
    private final MixedItemSection wordData;

    public DexFile(DexOptions dexOptions) {
        this.dexOptions = dexOptions;
        HeaderSection headerSection = new HeaderSection(this);
        this.header = headerSection;
        MixedItemSection mixedItemSection = new MixedItemSection(null, this, 4, MixedItemSection.SortType.NONE);
        this.typeLists = mixedItemSection;
        MixedItemSection mixedItemSection2 = new MixedItemSection("word_data", this, 4, MixedItemSection.SortType.TYPE);
        this.wordData = mixedItemSection2;
        MixedItemSection mixedItemSection3 = new MixedItemSection("string_data", this, 1, MixedItemSection.SortType.INSTANCE);
        this.stringData = mixedItemSection3;
        MixedItemSection mixedItemSection4 = new MixedItemSection(null, this, 1, MixedItemSection.SortType.NONE);
        this.classData = mixedItemSection4;
        MixedItemSection mixedItemSection5 = new MixedItemSection("byte_data", this, 1, MixedItemSection.SortType.TYPE);
        this.byteData = mixedItemSection5;
        StringIdsSection stringIdsSection = new StringIdsSection(this);
        this.stringIds = stringIdsSection;
        TypeIdsSection typeIdsSection = new TypeIdsSection(this);
        this.typeIds = typeIdsSection;
        ProtoIdsSection protoIdsSection = new ProtoIdsSection(this);
        this.protoIds = protoIdsSection;
        FieldIdsSection fieldIdsSection = new FieldIdsSection(this);
        this.fieldIds = fieldIdsSection;
        MethodIdsSection methodIdsSection = new MethodIdsSection(this);
        this.methodIds = methodIdsSection;
        ClassDefsSection classDefsSection = new ClassDefsSection(this);
        this.classDefs = classDefsSection;
        MixedItemSection mixedItemSection6 = new MixedItemSection("map", this, 4, MixedItemSection.SortType.NONE);
        this.map = mixedItemSection6;
        this.sections = new Section[]{headerSection, stringIdsSection, typeIdsSection, protoIdsSection, fieldIdsSection, methodIdsSection, classDefsSection, mixedItemSection2, mixedItemSection, mixedItemSection3, mixedItemSection5, mixedItemSection4, mixedItemSection6};
        this.fileSize = -1;
        this.dumpWidth = 79;
    }

    public boolean isEmpty() {
        return this.classDefs.items().isEmpty();
    }

    public DexOptions getDexOptions() {
        return this.dexOptions;
    }

    public void add(ClassDefItem classDefItem) {
        this.classDefs.add(classDefItem);
    }

    public ClassDefItem getClassOrNull(String str) {
        try {
            return (ClassDefItem) this.classDefs.get(new CstType(Type.internClassName(str)));
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public void writeTo(OutputStream outputStream, Writer writer, boolean z) throws IOException {
        boolean z2 = writer != null;
        ByteArrayAnnotatedOutput dex0 = toDex0(z2, z);
        if (outputStream != null) {
            outputStream.write(dex0.getArray());
        }
        if (z2) {
            dex0.writeAnnotationsTo(writer);
        }
    }

    public byte[] toDex(Writer writer, boolean z) throws IOException {
        boolean z2 = writer != null;
        ByteArrayAnnotatedOutput dex0 = toDex0(z2, z);
        if (z2) {
            dex0.writeAnnotationsTo(writer);
        }
        return dex0.getArray();
    }

    public void setDumpWidth(int i) {
        if (i < 40) {
            throw new IllegalArgumentException("dumpWidth < 40");
        }
        this.dumpWidth = i;
    }

    public int getFileSize() {
        int i = this.fileSize;
        if (i >= 0) {
            return i;
        }
        throw new RuntimeException("file size not yet known");
    }

    MixedItemSection getStringData() {
        return this.stringData;
    }

    MixedItemSection getWordData() {
        return this.wordData;
    }

    MixedItemSection getTypeLists() {
        return this.typeLists;
    }

    MixedItemSection getMap() {
        return this.map;
    }

    StringIdsSection getStringIds() {
        return this.stringIds;
    }

    public ClassDefsSection getClassDefs() {
        return this.classDefs;
    }

    MixedItemSection getClassData() {
        return this.classData;
    }

    public TypeIdsSection getTypeIds() {
        return this.typeIds;
    }

    ProtoIdsSection getProtoIds() {
        return this.protoIds;
    }

    public FieldIdsSection getFieldIds() {
        return this.fieldIds;
    }

    public MethodIdsSection getMethodIds() {
        return this.methodIds;
    }

    MixedItemSection getByteData() {
        return this.byteData;
    }

    Section getFirstDataSection() {
        return this.wordData;
    }

    Section getLastDataSection() {
        return this.map;
    }

    void internIfAppropriate(Constant constant) {
        if (constant instanceof CstString) {
            this.stringIds.intern((CstString) constant);
            return;
        }
        if (constant instanceof CstType) {
            this.typeIds.intern((CstType) constant);
            return;
        }
        if (constant instanceof CstBaseMethodRef) {
            this.methodIds.intern((CstBaseMethodRef) constant);
            return;
        }
        if (constant instanceof CstFieldRef) {
            this.fieldIds.intern((CstFieldRef) constant);
        } else if (constant instanceof CstEnumRef) {
            this.fieldIds.intern(((CstEnumRef) constant).getFieldRef());
        } else if (constant == null) {
            throw new NullPointerException("cst == null");
        }
    }

    IndexedItem findItemOrNull(Constant constant) {
        if (constant instanceof CstString) {
            return this.stringIds.get(constant);
        }
        if (constant instanceof CstType) {
            return this.typeIds.get(constant);
        }
        if (constant instanceof CstBaseMethodRef) {
            return this.methodIds.get(constant);
        }
        if (constant instanceof CstFieldRef) {
            return this.fieldIds.get(constant);
        }
        return null;
    }

    private ByteArrayAnnotatedOutput toDex0(boolean z, boolean z2) {
        ExceptionWithContext exceptionWithContext;
        this.classDefs.prepare();
        this.classData.prepare();
        this.wordData.prepare();
        this.byteData.prepare();
        this.methodIds.prepare();
        this.fieldIds.prepare();
        this.protoIds.prepare();
        this.typeLists.prepare();
        this.typeIds.prepare();
        this.stringIds.prepare();
        this.stringData.prepare();
        this.header.prepare();
        int length = this.sections.length;
        int iWriteSize = 0;
        for (int i = 0; i < length; i++) {
            Section section = this.sections[i];
            int fileOffset = section.setFileOffset(iWriteSize);
            if (fileOffset < iWriteSize) {
                throw new RuntimeException("bogus placement for section " + i);
            }
            try {
                MixedItemSection mixedItemSection = this.map;
                if (section == mixedItemSection) {
                    MapItem.addMap(this.sections, mixedItemSection);
                    this.map.prepare();
                }
                if (section instanceof MixedItemSection) {
                    ((MixedItemSection) section).placeItems();
                }
                iWriteSize = section.writeSize() + fileOffset;
            } catch (RuntimeException e) {
                throw ExceptionWithContext.withContext(e, "...while writing section " + i);
            }
        }
        this.fileSize = iWriteSize;
        byte[] bArr = new byte[iWriteSize];
        ByteArrayAnnotatedOutput byteArrayAnnotatedOutput = new ByteArrayAnnotatedOutput(bArr);
        if (z) {
            byteArrayAnnotatedOutput.enableAnnotations(this.dumpWidth, z2);
        }
        for (int i2 = 0; i2 < length; i2++) {
            try {
                Section section2 = this.sections[i2];
                int fileOffset2 = section2.getFileOffset() - byteArrayAnnotatedOutput.getCursor();
                if (fileOffset2 < 0) {
                    throw new ExceptionWithContext("excess write of " + (-fileOffset2));
                }
                byteArrayAnnotatedOutput.writeZeroes(section2.getFileOffset() - byteArrayAnnotatedOutput.getCursor());
                section2.writeTo(byteArrayAnnotatedOutput);
            } catch (RuntimeException e2) {
                if (e2 instanceof ExceptionWithContext) {
                    exceptionWithContext = (ExceptionWithContext) e2;
                } else {
                    exceptionWithContext = new ExceptionWithContext(e2);
                }
                exceptionWithContext.addContext("...while writing section " + i2);
                throw exceptionWithContext;
            }
        }
        if (byteArrayAnnotatedOutput.getCursor() != this.fileSize) {
            throw new RuntimeException("foreshortened write");
        }
        calcSignature(bArr);
        calcChecksum(bArr);
        if (z) {
            this.wordData.writeIndexAnnotation(byteArrayAnnotatedOutput, ItemType.TYPE_CODE_ITEM, "\nmethod code index:\n\n");
            getStatistics().writeAnnotation(byteArrayAnnotatedOutput);
            byteArrayAnnotatedOutput.finishAnnotating();
        }
        return byteArrayAnnotatedOutput;
    }

    public Statistics getStatistics() {
        Statistics statistics = new Statistics();
        for (Section section : this.sections) {
            statistics.addAll(section);
        }
        return statistics;
    }

    private static void calcSignature(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(bArr, 32, bArr.length - 32);
            try {
                int iDigest = messageDigest.digest(bArr, 12, 20);
                if (iDigest == 20) {
                } else {
                    throw new RuntimeException("unexpected digest write: " + iDigest + " bytes");
                }
            } catch (DigestException e) {
                throw new RuntimeException(e);
            }
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        }
    }

    private static void calcChecksum(byte[] bArr) {
        Adler32 adler32 = new Adler32();
        adler32.update(bArr, 12, bArr.length - 12);
        int value = (int) adler32.getValue();
        bArr[8] = (byte) value;
        bArr[9] = (byte) (value >> 8);
        bArr[10] = (byte) (value >> 16);
        bArr[11] = (byte) (value >> 24);
    }
}
