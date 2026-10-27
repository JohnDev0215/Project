package org.apache.commons.compress.harmony.unpack200;

import cerence.ark.assistant.controller.enumeration.TemperatureUnitEnum;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.ConstantValueAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.DeprecatedAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.EnclosingMethodAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.ExceptionsAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LineNumberTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LocalVariableTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LocalVariableTypeTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.SignatureAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.SourceFileAttribute;

/* JADX INFO: loaded from: classes4.dex */
public class ClassBands extends BandSet {
    private final AttributeLayoutMap attrMap;
    private long[] classAccessFlags;
    private ArrayList[] classAttributes;
    private final int classCount;
    private int[] classFieldCount;
    private long[] classFlags;
    private int[][] classInterfacesInts;
    private int[] classMethodCount;
    private int[] classSuperInts;
    private String[] classThis;
    private int[] classThisInts;
    private int[] classVersionMajor;
    private int[] classVersionMinor;
    private List[] codeAttributes;
    private int[][] codeHandlerCatchPO;
    private int[][] codeHandlerClassRCN;
    private int[] codeHandlerCount;
    private int[][] codeHandlerEndPO;
    private int[][] codeHandlerStartP;
    private boolean[] codeHasAttributes;
    private int[] codeMaxNALocals;
    private int[] codeMaxStack;
    private final CpBands cpBands;
    private long[][] fieldAccessFlags;
    private ArrayList[][] fieldAttributes;
    private String[][] fieldDescr;
    private int[][] fieldDescrInts;
    private long[][] fieldFlags;
    private IcTuple[][] icLocal;
    private long[][] methodAccessFlags;
    private int[] methodAttrCalls;
    private ArrayList[][] methodAttributes;
    private String[][] methodDescr;
    private int[][] methodDescrInts;
    private long[][] methodFlags;
    private final SegmentOptions options;

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void unpack() {
    }

    public ClassBands(Segment segment) {
        super(segment);
        this.attrMap = segment.getAttrDefinitionBands().getAttributeDefinitionMap();
        this.cpBands = segment.getCpBands();
        this.classCount = this.header.getClassCount();
        this.options = this.header.getOptions();
    }

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void read(InputStream inputStream) throws Pack200Exception, IOException {
        int classCount = this.header.getClassCount();
        int[] iArrDecodeBandInt = decodeBandInt("class_this", inputStream, Codec.DELTA5, classCount);
        this.classThisInts = iArrDecodeBandInt;
        this.classThis = getReferences(iArrDecodeBandInt, this.cpBands.getCpClass());
        this.classSuperInts = decodeBandInt("class_super", inputStream, Codec.DELTA5, classCount);
        this.classInterfacesInts = decodeBandInt("class_interface", inputStream, Codec.DELTA5, decodeBandInt("class_interface_count", inputStream, Codec.DELTA5, classCount));
        this.classFieldCount = decodeBandInt("class_field_count", inputStream, Codec.DELTA5, classCount);
        this.classMethodCount = decodeBandInt("class_method_count", inputStream, Codec.DELTA5, classCount);
        parseFieldBands(inputStream);
        parseMethodBands(inputStream);
        parseClassAttrBands(inputStream);
        parseCodeBands(inputStream);
    }

    private void parseFieldBands(InputStream inputStream) throws Pack200Exception, IOException {
        int[][] iArrDecodeBandInt = decodeBandInt("field_descr", inputStream, Codec.DELTA5, this.classFieldCount);
        this.fieldDescrInts = iArrDecodeBandInt;
        this.fieldDescr = getReferences(iArrDecodeBandInt, this.cpBands.getCpDescriptor());
        parseFieldAttrBands(inputStream);
    }

    private void parseFieldAttrBands(InputStream inputStream) throws Pack200Exception, IOException {
        int i;
        int[] iArr;
        AttributeLayout attributeLayout;
        AttributeLayout attributeLayout2;
        long[][] flags = parseFlags("field_flags", inputStream, this.classFieldCount, Codec.UNSIGNED5, this.options.hasFieldFlagsHi());
        this.fieldFlags = flags;
        int[] iArrDecodeBandInt = decodeBandInt("field_attr_calls", inputStream, Codec.UNSIGNED5, getCallCount(decodeBandInt("field_attr_indexes", inputStream, Codec.UNSIGNED5, decodeBandInt("field_attr_count", inputStream, Codec.UNSIGNED5, SegmentUtils.countBit16(flags))), this.fieldFlags, 1));
        this.fieldAttributes = new ArrayList[this.classCount][];
        for (int i2 = 0; i2 < this.classCount; i2++) {
            this.fieldAttributes[i2] = new ArrayList[this.fieldFlags[i2].length];
            for (int i3 = 0; i3 < this.fieldFlags[i2].length; i3++) {
                this.fieldAttributes[i2][i3] = new ArrayList();
            }
        }
        AttributeLayout attributeLayout3 = this.attrMap.getAttributeLayout("ConstantValue", 1);
        int[] iArrDecodeBandInt2 = decodeBandInt("field_ConstantValue_KQ", inputStream, Codec.UNSIGNED5, SegmentUtils.countMatches(this.fieldFlags, attributeLayout3));
        AttributeLayout attributeLayout4 = this.attrMap.getAttributeLayout("Signature", 1);
        int[] iArrDecodeBandInt3 = decodeBandInt("field_Signature_RS", inputStream, Codec.UNSIGNED5, SegmentUtils.countMatches(this.fieldFlags, attributeLayout4));
        AttributeLayout attributeLayout5 = this.attrMap.getAttributeLayout("Deprecated", 1);
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < this.classCount) {
            int i7 = 0;
            while (true) {
                long[] jArr = this.fieldFlags[i4];
                if (i7 < jArr.length) {
                    long j = jArr[i7];
                    if (attributeLayout5.matches(j)) {
                        this.fieldAttributes[i4][i7].add(new DeprecatedAttribute());
                    }
                    if (attributeLayout3.matches(j)) {
                        AttributeLayout attributeLayout6 = attributeLayout3;
                        long j2 = iArrDecodeBandInt2[i5];
                        String str = this.fieldDescr[i4][i7];
                        iArr = iArrDecodeBandInt2;
                        attributeLayout = attributeLayout5;
                        String strSubstring = str.substring(str.indexOf(58) + 1);
                        if (strSubstring.equals("B") || strSubstring.equals("S") || strSubstring.equals(TemperatureUnitEnum.C) || strSubstring.equals("Z")) {
                            strSubstring = "I";
                        }
                        attributeLayout2 = attributeLayout6;
                        this.fieldAttributes[i4][i7].add(new ConstantValueAttribute(attributeLayout2.getValue(j2, strSubstring, this.cpBands.getConstantPool())));
                        i5++;
                    } else {
                        iArr = iArrDecodeBandInt2;
                        attributeLayout = attributeLayout5;
                        attributeLayout2 = attributeLayout3;
                    }
                    if (attributeLayout4.matches(j)) {
                        long j3 = iArrDecodeBandInt3[i6];
                        String str2 = this.fieldDescr[i4][i7];
                        this.fieldAttributes[i4][i7].add(new SignatureAttribute((CPUTF8) attributeLayout4.getValue(j3, str2.substring(str2.indexOf(58) + 1), this.cpBands.getConstantPool())));
                        i6++;
                    }
                    i7++;
                    attributeLayout3 = attributeLayout2;
                    iArrDecodeBandInt2 = iArr;
                    attributeLayout5 = attributeLayout;
                }
            }
            i4++;
            attributeLayout5 = attributeLayout5;
        }
        int fieldMetadataBands = parseFieldMetadataBands(inputStream, iArrDecodeBandInt);
        int i8 = this.options.hasFieldFlagsHi() ? 62 : 31;
        int i9 = i8 + 1;
        AttributeLayout[] attributeLayoutArr = new AttributeLayout[i9];
        int[] iArr2 = new int[i9];
        List[] listArr = new List[i9];
        for (int i10 = 0; i10 < i8; i10++) {
            AttributeLayout attributeLayout7 = this.attrMap.getAttributeLayout(i10, 1);
            if (attributeLayout7 != null && !attributeLayout7.isDefaultLayout()) {
                attributeLayoutArr[i10] = attributeLayout7;
                iArr2[i10] = SegmentUtils.countMatches(this.fieldFlags, attributeLayout7);
            }
        }
        for (int i11 = 0; i11 < i9; i11++) {
            if (iArr2[i11] > 0) {
                NewAttributeBands attributeBands = this.attrMap.getAttributeBands(attributeLayoutArr[i11]);
                listArr[i11] = attributeBands.parseAttributes(inputStream, iArr2[i11]);
                int iNumBackwardsCallables = attributeLayoutArr[i11].numBackwardsCallables();
                if (iNumBackwardsCallables > 0) {
                    int[] iArr3 = new int[iNumBackwardsCallables];
                    System.arraycopy(iArrDecodeBandInt, fieldMetadataBands, iArr3, 0, iNumBackwardsCallables);
                    attributeBands.setBackwardsCalls(iArr3);
                    fieldMetadataBands += iNumBackwardsCallables;
                }
            }
        }
        for (int i12 = 0; i12 < this.classCount; i12++) {
            int i13 = 0;
            while (true) {
                long[] jArr2 = this.fieldFlags[i12];
                if (i13 < jArr2.length) {
                    long j4 = jArr2[i13];
                    int i14 = 0;
                    for (int i15 = 0; i15 < i9; i15++) {
                        AttributeLayout attributeLayout8 = attributeLayoutArr[i15];
                        if (attributeLayout8 != null && attributeLayout8.matches(j4)) {
                            if (attributeLayoutArr[i15].getIndex() < 15) {
                                i = 0;
                                this.fieldAttributes[i12][i13].add(i14, listArr[i15].get(0));
                                i14++;
                            } else {
                                i = 0;
                                this.fieldAttributes[i12][i13].add(listArr[i15].get(0));
                            }
                            listArr[i15].remove(i);
                        }
                    }
                    i13++;
                }
            }
        }
    }

    private void parseMethodBands(InputStream inputStream) throws Pack200Exception, IOException {
        int[][] iArrDecodeBandInt = decodeBandInt("method_descr", inputStream, Codec.MDELTA5, this.classMethodCount);
        this.methodDescrInts = iArrDecodeBandInt;
        this.methodDescr = getReferences(iArrDecodeBandInt, this.cpBands.getCpDescriptor());
        parseMethodAttrBands(inputStream);
    }

    private void parseMethodAttrBands(InputStream inputStream) throws Pack200Exception, IOException {
        int i;
        AttributeLayout attributeLayout;
        int[] iArr;
        long[][] flags = parseFlags("method_flags", inputStream, this.classMethodCount, Codec.UNSIGNED5, this.options.hasMethodFlagsHi());
        this.methodFlags = flags;
        this.methodAttrCalls = decodeBandInt("method_attr_calls", inputStream, Codec.UNSIGNED5, getCallCount(decodeBandInt("method_attr_indexes", inputStream, Codec.UNSIGNED5, decodeBandInt("method_attr_count", inputStream, Codec.UNSIGNED5, SegmentUtils.countBit16(flags))), this.methodFlags, 2));
        this.methodAttributes = new ArrayList[this.classCount][];
        for (int i2 = 0; i2 < this.classCount; i2++) {
            this.methodAttributes[i2] = new ArrayList[this.methodFlags[i2].length];
            for (int i3 = 0; i3 < this.methodFlags[i2].length; i3++) {
                this.methodAttributes[i2][i3] = new ArrayList();
            }
        }
        AttributeLayout attributeLayout2 = this.attrMap.getAttributeLayout("Exceptions", 2);
        int[] iArrDecodeBandInt = decodeBandInt("method_Exceptions_n", inputStream, Codec.UNSIGNED5, SegmentUtils.countMatches(this.methodFlags, attributeLayout2));
        int[][] iArrDecodeBandInt2 = decodeBandInt("method_Exceptions_RC", inputStream, Codec.UNSIGNED5, iArrDecodeBandInt);
        AttributeLayout attributeLayout3 = this.attrMap.getAttributeLayout("Signature", 2);
        int[] iArrDecodeBandInt3 = decodeBandInt("method_signature_RS", inputStream, Codec.UNSIGNED5, SegmentUtils.countMatches(this.methodFlags, attributeLayout3));
        AttributeLayout attributeLayout4 = this.attrMap.getAttributeLayout("Deprecated", 2);
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < this.methodAttributes.length; i6++) {
            int i7 = 0;
            while (i7 < this.methodAttributes[i6].length) {
                long j = this.methodFlags[i6][i7];
                if (attributeLayout2.matches(j)) {
                    int i8 = iArrDecodeBandInt[i4];
                    int[] iArr2 = iArrDecodeBandInt2[i4];
                    CPClass[] cPClassArr = new CPClass[i8];
                    attributeLayout = attributeLayout2;
                    int i9 = 0;
                    while (i9 < i8) {
                        cPClassArr[i9] = this.cpBands.cpClassValue(iArr2[i9]);
                        i9++;
                        i8 = i8;
                        iArrDecodeBandInt = iArrDecodeBandInt;
                    }
                    iArr = iArrDecodeBandInt;
                    this.methodAttributes[i6][i7].add(new ExceptionsAttribute(cPClassArr));
                    i4++;
                } else {
                    attributeLayout = attributeLayout2;
                    iArr = iArrDecodeBandInt;
                }
                if (attributeLayout3.matches(j)) {
                    long j2 = iArrDecodeBandInt3[i5];
                    String str = this.methodDescr[i6][i7];
                    String strSubstring = str.substring(str.indexOf(58) + 1);
                    if (strSubstring.equals("B") || strSubstring.equals("H")) {
                        strSubstring = "I";
                    }
                    this.methodAttributes[i6][i7].add(new SignatureAttribute((CPUTF8) attributeLayout3.getValue(j2, strSubstring, this.cpBands.getConstantPool())));
                    i5++;
                }
                if (attributeLayout4.matches(j)) {
                    this.methodAttributes[i6][i7].add(new DeprecatedAttribute());
                }
                i7++;
                attributeLayout2 = attributeLayout;
                iArrDecodeBandInt = iArr;
            }
        }
        int methodMetadataBands = parseMethodMetadataBands(inputStream, this.methodAttrCalls);
        int i10 = this.options.hasMethodFlagsHi() ? 62 : 31;
        int i11 = i10 + 1;
        AttributeLayout[] attributeLayoutArr = new AttributeLayout[i11];
        int[] iArr3 = new int[i11];
        List[] listArr = new List[i11];
        for (int i12 = 0; i12 < i10; i12++) {
            AttributeLayout attributeLayout5 = this.attrMap.getAttributeLayout(i12, 2);
            if (attributeLayout5 != null && !attributeLayout5.isDefaultLayout()) {
                attributeLayoutArr[i12] = attributeLayout5;
                iArr3[i12] = SegmentUtils.countMatches(this.methodFlags, attributeLayout5);
            }
        }
        for (int i13 = 0; i13 < i11; i13++) {
            if (iArr3[i13] > 0) {
                NewAttributeBands attributeBands = this.attrMap.getAttributeBands(attributeLayoutArr[i13]);
                listArr[i13] = attributeBands.parseAttributes(inputStream, iArr3[i13]);
                int iNumBackwardsCallables = attributeLayoutArr[i13].numBackwardsCallables();
                if (iNumBackwardsCallables > 0) {
                    int[] iArr4 = new int[iNumBackwardsCallables];
                    System.arraycopy(this.methodAttrCalls, methodMetadataBands, iArr4, 0, iNumBackwardsCallables);
                    attributeBands.setBackwardsCalls(iArr4);
                    methodMetadataBands += iNumBackwardsCallables;
                }
            }
        }
        for (int i14 = 0; i14 < this.methodAttributes.length; i14++) {
            for (int i15 = 0; i15 < this.methodAttributes[i14].length; i15++) {
                long j3 = this.methodFlags[i14][i15];
                int i16 = 0;
                for (int i17 = 0; i17 < i11; i17++) {
                    AttributeLayout attributeLayout6 = attributeLayoutArr[i17];
                    if (attributeLayout6 != null && attributeLayout6.matches(j3)) {
                        if (attributeLayoutArr[i17].getIndex() < 15) {
                            i = 0;
                            this.methodAttributes[i14][i15].add(i16, listArr[i17].get(0));
                            i16++;
                        } else {
                            i = 0;
                            this.methodAttributes[i14][i15].add(listArr[i17].get(0));
                        }
                        listArr[i17].remove(i);
                    }
                }
            }
        }
    }

    private int getCallCount(int[][] iArr, long[][] jArr, int i) throws Pack200Exception {
        int iNumBackwardsCallables = 0;
        for (int[] iArr2 : iArr) {
            int i2 = 0;
            while (true) {
                if (i2 < iArr2.length) {
                    iNumBackwardsCallables += this.attrMap.getAttributeLayout(iArr2[i2], i).numBackwardsCallables();
                    i2++;
                }
            }
        }
        int i3 = 0;
        for (long[] jArr2 : jArr) {
            int i4 = 0;
            while (true) {
                if (i4 < jArr2.length) {
                    i3 = (int) (((long) i3) | jArr2[i4]);
                    i4++;
                }
            }
        }
        for (int i5 = 0; i5 < 26; i5++) {
            if (((1 << i5) & i3) != 0) {
                iNumBackwardsCallables += this.attrMap.getAttributeLayout(i5, i).numBackwardsCallables();
            }
        }
        return iNumBackwardsCallables;
    }

    private void parseClassAttrBands(InputStream inputStream) throws Pack200Exception, IOException {
        int i;
        AttributeLayout attributeLayout;
        List[] listArr;
        AttributeLayout attributeLayout2;
        int[] iArr;
        int i2;
        int i3;
        int i4;
        AttributeLayout attributeLayout3;
        int i5;
        AttributeLayout attributeLayout4;
        AttributeLayout attributeLayout5;
        int i6;
        int i7;
        int i8;
        AttributeLayout attributeLayout6;
        int i9;
        String str;
        String n;
        int i10;
        int i11;
        int i12;
        int[] iArr2;
        int i13;
        int[][] iArr3;
        InputStream inputStream2 = inputStream;
        String[] cpUTF8 = this.cpBands.getCpUTF8();
        String[] cpClass = this.cpBands.getCpClass();
        this.classAttributes = new ArrayList[this.classCount];
        int i14 = 0;
        while (true) {
            i = this.classCount;
            if (i14 >= i) {
                break;
            }
            this.classAttributes[i14] = new ArrayList();
            i14++;
        }
        long[] flags = parseFlags("class_flags", inputStream, i, Codec.UNSIGNED5, this.options.hasClassFlagsHi());
        this.classFlags = flags;
        int[] iArrDecodeBandInt = decodeBandInt("class_attr_calls", inputStream2, Codec.UNSIGNED5, getCallCount(decodeBandInt("class_attr_indexes", inputStream2, Codec.UNSIGNED5, decodeBandInt("class_attr_count", inputStream2, Codec.UNSIGNED5, SegmentUtils.countBit16(flags))), new long[][]{this.classFlags}, 0));
        AttributeLayout attributeLayout7 = this.attrMap.getAttributeLayout("Deprecated", 0);
        AttributeLayout attributeLayout8 = this.attrMap.getAttributeLayout("SourceFile", 0);
        int[] iArrDecodeBandInt2 = decodeBandInt("class_SourceFile_RUN", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.classFlags, attributeLayout8));
        AttributeLayout attributeLayout9 = this.attrMap.getAttributeLayout("EnclosingMethod", 0);
        int iCountMatches = SegmentUtils.countMatches(this.classFlags, attributeLayout9);
        int[] iArrDecodeBandInt3 = decodeBandInt("class_EnclosingMethod_RC", inputStream2, Codec.UNSIGNED5, iCountMatches);
        int[] iArrDecodeBandInt4 = decodeBandInt("class_EnclosingMethod_RDN", inputStream2, Codec.UNSIGNED5, iCountMatches);
        AttributeLayout attributeLayout10 = this.attrMap.getAttributeLayout("Signature", 0);
        int[] iArrDecodeBandInt5 = decodeBandInt("class_Signature_RS", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.classFlags, attributeLayout10));
        int classMetadataBands = parseClassMetadataBands(inputStream2, iArrDecodeBandInt);
        AttributeLayout attributeLayout11 = this.attrMap.getAttributeLayout("InnerClasses", 0);
        int[] iArrDecodeBandInt6 = decodeBandInt("class_InnerClasses_N", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.classFlags, attributeLayout11));
        int[][] iArrDecodeBandInt7 = decodeBandInt("class_InnerClasses_RC", inputStream2, Codec.UNSIGNED5, iArrDecodeBandInt6);
        int[][] iArrDecodeBandInt8 = decodeBandInt("class_InnerClasses_F", inputStream2, Codec.UNSIGNED5, iArrDecodeBandInt6);
        int i15 = 0;
        int i16 = 0;
        while (i16 < iArrDecodeBandInt8.length) {
            AttributeLayout attributeLayout12 = attributeLayout11;
            int i17 = 0;
            while (true) {
                int[] iArr4 = iArrDecodeBandInt8[i16];
                iArr3 = iArrDecodeBandInt8;
                if (i17 < iArr4.length) {
                    if (iArr4[i17] != 0) {
                        i15++;
                    }
                    i17++;
                    iArrDecodeBandInt8 = iArr3;
                }
            }
            i16++;
            attributeLayout11 = attributeLayout12;
            iArrDecodeBandInt8 = iArr3;
        }
        int[][] iArr5 = iArrDecodeBandInt8;
        AttributeLayout attributeLayout13 = attributeLayout11;
        int[] iArrDecodeBandInt9 = decodeBandInt("class_InnerClasses_outer_RCN", inputStream2, Codec.UNSIGNED5, i15);
        int[] iArrDecodeBandInt10 = decodeBandInt("class_InnerClasses_name_RUN", inputStream2, Codec.UNSIGNED5, i15);
        AttributeLayout attributeLayout14 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_CLASS_FILE_VERSION, 0);
        int iCountMatches2 = SegmentUtils.countMatches(this.classFlags, attributeLayout14);
        AttributeLayout attributeLayout15 = attributeLayout14;
        int[] iArrDecodeBandInt11 = decodeBandInt("class_file_version_minor_H", inputStream2, Codec.UNSIGNED5, iCountMatches2);
        int[] iArrDecodeBandInt12 = decodeBandInt("class_file_version_major_H", inputStream2, Codec.UNSIGNED5, iCountMatches2);
        if (iCountMatches2 > 0) {
            int i18 = this.classCount;
            this.classVersionMajor = new int[i18];
            this.classVersionMinor = new int[i18];
        }
        int defaultClassMajorVersion = this.header.getDefaultClassMajorVersion();
        int defaultClassMinorVersion = this.header.getDefaultClassMinorVersion();
        int i19 = this.options.hasClassFlagsHi() ? 62 : 31;
        int i20 = i19 + 1;
        AttributeLayout[] attributeLayoutArr = new AttributeLayout[i20];
        int[] iArr6 = new int[i20];
        List[] listArr2 = new List[i20];
        int i21 = 0;
        while (i21 < i19) {
            int i22 = i19;
            AttributeLayout attributeLayout16 = attributeLayout10;
            AttributeLayout attributeLayout17 = this.attrMap.getAttributeLayout(i21, 0);
            if (attributeLayout17 != null && !attributeLayout17.isDefaultLayout()) {
                attributeLayoutArr[i21] = attributeLayout17;
                iArr6[i21] = SegmentUtils.countMatches(this.classFlags, attributeLayout17);
            }
            i21++;
            i19 = i22;
            attributeLayout10 = attributeLayout16;
        }
        AttributeLayout attributeLayout18 = attributeLayout10;
        int i23 = classMetadataBands;
        int i24 = 0;
        while (i24 < i20) {
            if (iArr6[i24] > 0) {
                i13 = i20;
                NewAttributeBands attributeBands = this.attrMap.getAttributeBands(attributeLayoutArr[i24]);
                listArr2[i24] = attributeBands.parseAttributes(inputStream2, iArr6[i24]);
                int iNumBackwardsCallables = attributeLayoutArr[i24].numBackwardsCallables();
                if (iNumBackwardsCallables > 0) {
                    int[] iArr7 = new int[iNumBackwardsCallables];
                    iArr2 = iArr6;
                    System.arraycopy(iArrDecodeBandInt, i23, iArr7, 0, iNumBackwardsCallables);
                    attributeBands.setBackwardsCalls(iArr7);
                    i23 += iNumBackwardsCallables;
                } else {
                    iArr2 = iArr6;
                }
            } else {
                iArr2 = iArr6;
                i13 = i20;
            }
            i24++;
            inputStream2 = inputStream;
            i20 = i13;
            iArr6 = iArr2;
        }
        int i25 = i20;
        this.icLocal = new IcTuple[this.classCount][];
        int i26 = 0;
        int i27 = 0;
        int i28 = 0;
        int i29 = 0;
        int i30 = 0;
        int i31 = 0;
        int i32 = 0;
        while (i32 < this.classCount) {
            int i33 = i31;
            long j = this.classFlags[i32];
            if (attributeLayout7.matches(j)) {
                attributeLayout = attributeLayout7;
                listArr = listArr2;
                this.classAttributes[i32].add(new DeprecatedAttribute());
            } else {
                attributeLayout = attributeLayout7;
                listArr = listArr2;
            }
            if (attributeLayout8.matches(j)) {
                i2 = i30;
                ClassFileEntry value = attributeLayout8.getValue(iArrDecodeBandInt2[i27], this.cpBands.getConstantPool());
                if (value == null) {
                    String str2 = this.classThis[i32];
                    String strSubstring = str2.substring(str2.lastIndexOf(47) + 1);
                    String strSubstring2 = strSubstring.substring(strSubstring.lastIndexOf(46) + 1);
                    char[] charArray = strSubstring2.toCharArray();
                    attributeLayout2 = attributeLayout8;
                    int i34 = 0;
                    while (true) {
                        if (i34 >= charArray.length) {
                            iArr = iArrDecodeBandInt2;
                            i12 = -1;
                            i34 = -1;
                            break;
                        } else {
                            iArr = iArrDecodeBandInt2;
                            if (charArray[i34] <= '-') {
                                i12 = -1;
                                break;
                            } else {
                                i34++;
                                iArrDecodeBandInt2 = iArr;
                            }
                        }
                    }
                    if (i34 > i12) {
                        strSubstring2 = strSubstring2.substring(0, i34);
                    }
                    value = this.cpBands.cpUTF8Value(strSubstring2 + ".java", true);
                } else {
                    attributeLayout2 = attributeLayout8;
                    iArr = iArrDecodeBandInt2;
                }
                this.classAttributes[i32].add(new SourceFileAttribute((CPUTF8) value));
                i27++;
            } else {
                attributeLayout2 = attributeLayout8;
                iArr = iArrDecodeBandInt2;
                i2 = i30;
            }
            if (attributeLayout9.matches(j)) {
                CPClass cPClassCpClassValue = this.cpBands.cpClassValue(iArrDecodeBandInt3[i28]);
                int i35 = iArrDecodeBandInt4[i28];
                this.classAttributes[i32].add(new EnclosingMethodAttribute(cPClassCpClassValue, i35 != 0 ? this.cpBands.cpNameAndTypeValue(i35 - 1) : null));
                i28++;
            }
            AttributeLayout attributeLayout19 = attributeLayout18;
            if (attributeLayout19.matches(j)) {
                this.classAttributes[i32].add(new SignatureAttribute((CPUTF8) attributeLayout19.getValue(iArrDecodeBandInt5[i29], this.cpBands.getConstantPool())));
                i29++;
            }
            AttributeLayout attributeLayout20 = attributeLayout13;
            if (attributeLayout20.matches(j)) {
                this.icLocal[i32] = new IcTuple[iArrDecodeBandInt6[i2]];
                i5 = i26;
                int i36 = 0;
                while (i36 < this.icLocal[i32].length) {
                    int i37 = iArrDecodeBandInt7[i2][i36];
                    String str3 = cpClass[i37];
                    int i38 = iArr5[i2][i36];
                    if (i38 != 0) {
                        int i39 = iArrDecodeBandInt9[i5];
                        int i40 = iArrDecodeBandInt10[i5];
                        String str4 = cpClass[i39];
                        i5++;
                        i7 = i27;
                        i8 = i28;
                        attributeLayout6 = attributeLayout19;
                        i10 = i39;
                        i11 = i40;
                        n = cpUTF8[i40];
                        str = str4;
                        i9 = i38;
                    } else {
                        i7 = i27;
                        IcTuple[] icTuples = this.segment.getIcBands().getIcTuples();
                        i8 = i28;
                        attributeLayout6 = attributeLayout19;
                        int i41 = 0;
                        while (true) {
                            if (i41 >= icTuples.length) {
                                i9 = i38;
                                str = null;
                                n = null;
                                break;
                            } else {
                                if (icTuples[i41].getC().equals(str3)) {
                                    int f = icTuples[i41].getF();
                                    String c2 = icTuples[i41].getC2();
                                    n = icTuples[i41].getN();
                                    i9 = f;
                                    str = c2;
                                    break;
                                }
                                i41++;
                            }
                        }
                        i10 = -1;
                        i11 = -1;
                    }
                    this.icLocal[i32][i36] = new IcTuple(str3, i9, str, n, i37, i10, i11, i36);
                    i36++;
                    i27 = i7;
                    i28 = i8;
                    attributeLayout19 = attributeLayout6;
                }
                i3 = i27;
                i4 = i28;
                attributeLayout3 = attributeLayout19;
                i30 = i2 + 1;
                attributeLayout4 = attributeLayout15;
            } else {
                i3 = i27;
                i4 = i28;
                attributeLayout3 = attributeLayout19;
                i5 = i26;
                attributeLayout4 = attributeLayout15;
                i30 = i2;
            }
            if (attributeLayout4.matches(j)) {
                this.classVersionMajor[i32] = iArrDecodeBandInt12[i33];
                this.classVersionMinor[i32] = iArrDecodeBandInt11[i33];
                i33++;
            } else {
                int[] iArr8 = this.classVersionMajor;
                if (iArr8 != null) {
                    iArr8[i32] = defaultClassMajorVersion;
                    this.classVersionMinor[i32] = defaultClassMinorVersion;
                }
            }
            int i42 = i25;
            int i43 = 0;
            while (i43 < i42) {
                AttributeLayout attributeLayout21 = attributeLayoutArr[i43];
                if (attributeLayout21 == null || !attributeLayout21.matches(j)) {
                    attributeLayout5 = attributeLayout4;
                    i6 = i42;
                } else {
                    attributeLayout5 = attributeLayout4;
                    i6 = i42;
                    this.classAttributes[i32].add(listArr[i43].get(0));
                    listArr[i43].remove(0);
                }
                i43++;
                i42 = i6;
                attributeLayout4 = attributeLayout5;
            }
            attributeLayout15 = attributeLayout4;
            i25 = i42;
            i32++;
            attributeLayout13 = attributeLayout20;
            i26 = i5;
            i31 = i33;
            attributeLayout7 = attributeLayout;
            listArr2 = listArr;
            attributeLayout8 = attributeLayout2;
            iArrDecodeBandInt2 = iArr;
            i27 = i3;
            i28 = i4;
            attributeLayout18 = attributeLayout3;
        }
    }

    private void parseCodeBands(InputStream inputStream) throws Pack200Exception, IOException {
        char c;
        char c2 = 2;
        int iCountMatches = SegmentUtils.countMatches(this.methodFlags, this.attrMap.getAttributeLayout("Code", 2));
        int[] iArrDecodeBandInt = decodeBandInt("code_headers", inputStream, Codec.BYTE1, iCountMatches);
        boolean zHasAllCodeFlags = this.segment.getSegmentHeader().getOptions().hasAllCodeFlags();
        if (!zHasAllCodeFlags) {
            this.codeHasAttributes = new boolean[iCountMatches];
        }
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < iCountMatches; i3++) {
            if (iArrDecodeBandInt[i3] == 0) {
                i2++;
                if (!zHasAllCodeFlags) {
                    this.codeHasAttributes[i3] = true;
                }
            }
        }
        int[] iArrDecodeBandInt2 = decodeBandInt("code_max_stack", inputStream, Codec.UNSIGNED5, i2);
        int[] iArrDecodeBandInt3 = decodeBandInt("code_max_na_locals", inputStream, Codec.UNSIGNED5, i2);
        int[] iArrDecodeBandInt4 = decodeBandInt("code_handler_count", inputStream, Codec.UNSIGNED5, i2);
        this.codeMaxStack = new int[iCountMatches];
        this.codeMaxNALocals = new int[iCountMatches];
        this.codeHandlerCount = new int[iCountMatches];
        int i4 = 0;
        int i5 = 0;
        while (i4 < iCountMatches) {
            int i6 = iArrDecodeBandInt[i4] & 255;
            if (i6 < 0) {
                throw new IllegalStateException("Shouldn't get here");
            }
            if (i6 == 0) {
                this.codeMaxStack[i4] = iArrDecodeBandInt2[i5];
                this.codeMaxNALocals[i4] = iArrDecodeBandInt3[i5];
                this.codeHandlerCount[i4] = iArrDecodeBandInt4[i5];
                i5++;
                c = c2;
            } else {
                if (i6 <= 144) {
                    int i7 = i6 - 1;
                    this.codeMaxStack[i4] = i7 % 12;
                    this.codeMaxNALocals[i4] = i7 / 12;
                    this.codeHandlerCount[i4] = 0;
                } else if (i6 <= 208) {
                    int i8 = i6 - 145;
                    this.codeMaxStack[i4] = i8 % 8;
                    this.codeMaxNALocals[i4] = i8 / 8;
                    this.codeHandlerCount[i4] = 1;
                } else if (i6 <= 255) {
                    int i9 = i6 - 209;
                    this.codeMaxStack[i4] = i9 % 7;
                    this.codeMaxNALocals[i4] = i9 / 7;
                    c = 2;
                    this.codeHandlerCount[i4] = 2;
                } else {
                    throw new IllegalStateException("Shouldn't get here either");
                }
                c = 2;
            }
            i4++;
            c2 = c;
        }
        this.codeHandlerStartP = decodeBandInt("code_handler_start_P", inputStream, Codec.BCI5, this.codeHandlerCount);
        this.codeHandlerEndPO = decodeBandInt("code_handler_end_PO", inputStream, Codec.BRANCH5, this.codeHandlerCount);
        this.codeHandlerCatchPO = decodeBandInt("code_handler_catch_PO", inputStream, Codec.BRANCH5, this.codeHandlerCount);
        this.codeHandlerClassRCN = decodeBandInt("code_handler_class_RCN", inputStream, Codec.UNSIGNED5, this.codeHandlerCount);
        if (!zHasAllCodeFlags) {
            iCountMatches = i2;
        }
        this.codeAttributes = new List[iCountMatches];
        while (true) {
            List[] listArr = this.codeAttributes;
            if (i < listArr.length) {
                listArr[i] = new ArrayList();
                i++;
            } else {
                parseCodeAttrBands(inputStream, iCountMatches);
                return;
            }
        }
    }

    private void parseCodeAttrBands(InputStream inputStream, int i) throws Pack200Exception, IOException {
        AttributeLayout attributeLayout;
        int[] iArr;
        int i2;
        InputStream inputStream2 = inputStream;
        long[] flags = parseFlags("code_flags", inputStream, i, Codec.UNSIGNED5, this.segment.getSegmentHeader().getOptions().hasCodeFlagsHi());
        int iNumBackwardsCallables = 0;
        for (int[] iArr2 : decodeBandInt("code_attr_indexes", inputStream2, Codec.UNSIGNED5, decodeBandInt("code_attr_count", inputStream2, Codec.UNSIGNED5, SegmentUtils.countBit16(flags)))) {
            int i3 = 0;
            while (true) {
                if (i3 < iArr2.length) {
                    iNumBackwardsCallables += this.attrMap.getAttributeLayout(iArr2[i3], 3).numBackwardsCallables();
                    i3++;
                }
            }
        }
        int[] iArrDecodeBandInt = decodeBandInt("code_attr_calls", inputStream2, Codec.UNSIGNED5, iNumBackwardsCallables);
        AttributeLayout attributeLayout2 = this.attrMap.getAttributeLayout("LineNumberTable", 3);
        int[] iArrDecodeBandInt2 = decodeBandInt("code_LineNumberTable_N", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(flags, attributeLayout2));
        int[][] iArrDecodeBandInt3 = decodeBandInt("code_LineNumberTable_bci_P", inputStream2, Codec.BCI5, iArrDecodeBandInt2);
        int[][] iArrDecodeBandInt4 = decodeBandInt("code_LineNumberTable_line", inputStream2, Codec.UNSIGNED5, iArrDecodeBandInt2);
        AttributeLayout attributeLayout3 = this.attrMap.getAttributeLayout("LocalVariableTable", 3);
        AttributeLayout attributeLayout4 = this.attrMap.getAttributeLayout("LocalVariableTypeTable", 3);
        int[] iArrDecodeBandInt5 = decodeBandInt("code_LocalVariableTable_N", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(flags, attributeLayout3));
        int[][] iArrDecodeBandInt6 = decodeBandInt("code_LocalVariableTable_bci_P", inputStream2, Codec.BCI5, iArrDecodeBandInt5);
        int[][] iArrDecodeBandInt7 = decodeBandInt("code_LocalVariableTable_span_O", inputStream2, Codec.BRANCH5, iArrDecodeBandInt5);
        CPUTF8[][] cPUTF8References = parseCPUTF8References("code_LocalVariableTable_name_RU", inputStream2, Codec.UNSIGNED5, iArrDecodeBandInt5);
        CPUTF8[][] cPSignatureReferences = parseCPSignatureReferences("code_LocalVariableTable_type_RS", inputStream2, Codec.UNSIGNED5, iArrDecodeBandInt5);
        int[][] iArrDecodeBandInt8 = decodeBandInt("code_LocalVariableTable_slot", inputStream2, Codec.UNSIGNED5, iArrDecodeBandInt5);
        AttributeLayout attributeLayout5 = attributeLayout4;
        int[] iArrDecodeBandInt9 = decodeBandInt("code_LocalVariableTypeTable_N", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(flags, attributeLayout4));
        int[][] iArrDecodeBandInt10 = decodeBandInt("code_LocalVariableTypeTable_bci_P", inputStream2, Codec.BCI5, iArrDecodeBandInt9);
        int[][] iArrDecodeBandInt11 = decodeBandInt("code_LocalVariableTypeTable_span_O", inputStream2, Codec.BRANCH5, iArrDecodeBandInt9);
        CPUTF8[][] cPUTF8References2 = parseCPUTF8References("code_LocalVariableTypeTable_name_RU", inputStream2, Codec.UNSIGNED5, iArrDecodeBandInt9);
        CPUTF8[][] cPSignatureReferences2 = parseCPSignatureReferences("code_LocalVariableTypeTable_type_RS", inputStream2, Codec.UNSIGNED5, iArrDecodeBandInt9);
        int[][] iArrDecodeBandInt12 = decodeBandInt("code_LocalVariableTypeTable_slot", inputStream2, Codec.UNSIGNED5, iArrDecodeBandInt9);
        int i4 = this.options.hasCodeFlagsHi() ? 62 : 31;
        int i5 = i4 + 1;
        AttributeLayout[] attributeLayoutArr = new AttributeLayout[i5];
        int[] iArr3 = new int[i5];
        List[] listArr = new List[i5];
        int i6 = 0;
        while (i6 < i4) {
            int i7 = i4;
            int[] iArr4 = iArrDecodeBandInt5;
            AttributeLayout attributeLayout6 = this.attrMap.getAttributeLayout(i6, 3);
            if (attributeLayout6 != null && !attributeLayout6.isDefaultLayout()) {
                attributeLayoutArr[i6] = attributeLayout6;
                iArr3[i6] = SegmentUtils.countMatches(flags, attributeLayout6);
            }
            i6++;
            i4 = i7;
            iArrDecodeBandInt5 = iArr4;
        }
        int[] iArr5 = iArrDecodeBandInt5;
        int i8 = 0;
        int i9 = 0;
        while (i8 < i5) {
            if (iArr3[i8] > 0) {
                i2 = i5;
                NewAttributeBands attributeBands = this.attrMap.getAttributeBands(attributeLayoutArr[i8]);
                listArr[i8] = attributeBands.parseAttributes(inputStream2, iArr3[i8]);
                int iNumBackwardsCallables2 = attributeLayoutArr[i8].numBackwardsCallables();
                iArr = iArr3;
                if (iNumBackwardsCallables2 > 0) {
                    int[] iArr6 = new int[iNumBackwardsCallables2];
                    System.arraycopy(iArrDecodeBandInt, i9, iArr6, 0, iNumBackwardsCallables2);
                    attributeBands.setBackwardsCalls(iArr6);
                    i9 += iNumBackwardsCallables2;
                }
            } else {
                iArr = iArr3;
                i2 = i5;
            }
            i8++;
            inputStream2 = inputStream;
            i5 = i2;
            iArr3 = iArr;
        }
        int i10 = i5;
        int i11 = i;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        while (i14 < i11) {
            if (attributeLayout2.matches(flags[i14])) {
                attributeLayout = attributeLayout2;
                LineNumberTableAttribute lineNumberTableAttribute = new LineNumberTableAttribute(iArrDecodeBandInt2[i12], iArrDecodeBandInt3[i12], iArrDecodeBandInt4[i12]);
                i12++;
                this.codeAttributes[i14].add(lineNumberTableAttribute);
            } else {
                attributeLayout = attributeLayout2;
            }
            if (attributeLayout3.matches(flags[i14])) {
                LocalVariableTableAttribute localVariableTableAttribute = new LocalVariableTableAttribute(iArr5[i13], iArrDecodeBandInt6[i13], iArrDecodeBandInt7[i13], cPUTF8References[i13], cPSignatureReferences[i13], iArrDecodeBandInt8[i13]);
                i13++;
                this.codeAttributes[i14].add(localVariableTableAttribute);
            }
            AttributeLayout attributeLayout7 = attributeLayout5;
            if (attributeLayout7.matches(flags[i14])) {
                LocalVariableTypeTableAttribute localVariableTypeTableAttribute = new LocalVariableTypeTableAttribute(iArrDecodeBandInt9[i15], iArrDecodeBandInt10[i15], iArrDecodeBandInt11[i15], cPUTF8References2[i15], cPSignatureReferences2[i15], iArrDecodeBandInt12[i15]);
                i15++;
                this.codeAttributes[i14].add(localVariableTypeTableAttribute);
            }
            int i16 = i10;
            int i17 = 0;
            while (i17 < i16) {
                AttributeLayout attributeLayout8 = attributeLayoutArr[i17];
                int i18 = i12;
                int i19 = i13;
                if (attributeLayout8 != null && attributeLayout8.matches(flags[i14])) {
                    this.codeAttributes[i14].add(listArr[i17].get(0));
                    listArr[i17].remove(0);
                }
                i17++;
                i12 = i18;
                i13 = i19;
            }
            i14++;
            i11 = i;
            attributeLayout5 = attributeLayout7;
            attributeLayout2 = attributeLayout;
            i10 = i16;
        }
    }

    private int parseFieldMetadataBands(InputStream inputStream, int[] iArr) throws Pack200Exception, IOException {
        int i;
        String[] strArr = {"RVA", "RIA"};
        AttributeLayout attributeLayout = this.attrMap.getAttributeLayout("RuntimeVisibleAnnotations", 1);
        AttributeLayout attributeLayout2 = this.attrMap.getAttributeLayout("RuntimeInvisibleAnnotations", 1);
        int iCountMatches = SegmentUtils.countMatches(this.fieldFlags, attributeLayout);
        int iCountMatches2 = SegmentUtils.countMatches(this.fieldFlags, attributeLayout2);
        int[] iArr2 = {iCountMatches, iCountMatches2};
        int[] iArr3 = {0, 0};
        if (iCountMatches > 0) {
            iArr3[0] = iArr[0];
            if (iCountMatches2 > 0) {
                iArr3[1] = iArr[1];
                i = 2;
            } else {
                i = 1;
            }
        } else if (iCountMatches2 > 0) {
            iArr3[1] = iArr[0];
            i = 1;
        } else {
            i = 0;
        }
        MetadataBandGroup[] metadata = parseMetadata(inputStream, strArr, iArr2, iArr3, "field");
        List attributes = metadata[0].getAttributes();
        List attributes2 = metadata[1].getAttributes();
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < this.fieldFlags.length; i4++) {
            int i5 = 0;
            while (true) {
                long[] jArr = this.fieldFlags[i4];
                if (i5 < jArr.length) {
                    if (attributeLayout.matches(jArr[i5])) {
                        this.fieldAttributes[i4][i5].add(attributes.get(i2));
                        i2++;
                    }
                    if (attributeLayout2.matches(this.fieldFlags[i4][i5])) {
                        this.fieldAttributes[i4][i5].add(attributes2.get(i3));
                        i3++;
                    }
                    i5++;
                }
            }
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0198  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private org.apache.commons.compress.harmony.unpack200.MetadataBandGroup[] parseMetadata(java.io.InputStream r20, java.lang.String[] r21, int[] r22, int[] r23, java.lang.String r24) throws org.apache.commons.compress.harmony.pack200.Pack200Exception, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 944
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.unpack200.ClassBands.parseMetadata(java.io.InputStream, java.lang.String[], int[], int[], java.lang.String):org.apache.commons.compress.harmony.unpack200.MetadataBandGroup[]");
    }

    private int parseMethodMetadataBands(InputStream inputStream, int[] iArr) throws Pack200Exception, IOException {
        String[] strArr = {"RVA", "RIA", "RVPA", "RIPA", "AD"};
        int[] iArr2 = {0, 0, 0, 0, 0};
        AttributeLayout[] attributeLayoutArr = {this.attrMap.getAttributeLayout("RuntimeVisibleAnnotations", 2), this.attrMap.getAttributeLayout("RuntimeInvisibleAnnotations", 2), this.attrMap.getAttributeLayout("RuntimeVisibleParameterAnnotations", 2), this.attrMap.getAttributeLayout("RuntimeInvisibleParameterAnnotations", 2), this.attrMap.getAttributeLayout("AnnotationDefault", 2)};
        for (int i = 0; i < 5; i++) {
            iArr2[i] = SegmentUtils.countMatches(this.methodFlags, attributeLayoutArr[i]);
        }
        int[] iArr3 = new int[5];
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < 5; i4++) {
            if (iArr2[i4] > 0) {
                i2++;
                iArr3[i4] = iArr[i3];
                i3++;
            } else {
                iArr3[i4] = 0;
            }
        }
        MetadataBandGroup[] metadata = parseMetadata(inputStream, strArr, iArr2, iArr3, "method");
        List[] listArr = new List[5];
        int[] iArr4 = new int[5];
        for (int i5 = 0; i5 < metadata.length; i5++) {
            listArr[i5] = metadata[i5].getAttributes();
            iArr4[i5] = 0;
        }
        for (int i6 = 0; i6 < this.methodFlags.length; i6++) {
            for (int i7 = 0; i7 < this.methodFlags[i6].length; i7++) {
                for (int i8 = 0; i8 < 5; i8++) {
                    if (attributeLayoutArr[i8].matches(this.methodFlags[i6][i7])) {
                        ArrayList arrayList = this.methodAttributes[i6][i7];
                        List list = listArr[i8];
                        int i9 = iArr4[i8];
                        iArr4[i8] = i9 + 1;
                        arrayList.add(list.get(i9));
                    }
                }
            }
        }
        return i2;
    }

    private int parseClassMetadataBands(InputStream inputStream, int[] iArr) throws Pack200Exception, IOException {
        int i;
        String[] strArr = {"RVA", "RIA"};
        int i2 = 0;
        AttributeLayout attributeLayout = this.attrMap.getAttributeLayout("RuntimeVisibleAnnotations", 0);
        AttributeLayout attributeLayout2 = this.attrMap.getAttributeLayout("RuntimeInvisibleAnnotations", 0);
        int iCountMatches = SegmentUtils.countMatches(this.classFlags, attributeLayout);
        int iCountMatches2 = SegmentUtils.countMatches(this.classFlags, attributeLayout2);
        int[] iArr2 = {iCountMatches, iCountMatches2};
        int[] iArr3 = {0, 0};
        if (iCountMatches > 0) {
            iArr3[0] = iArr[0];
            if (iCountMatches2 > 0) {
                iArr3[1] = iArr[1];
                i = 2;
            } else {
                i = 1;
            }
        } else if (iCountMatches2 > 0) {
            iArr3[1] = iArr[0];
            i = 1;
        } else {
            i = 0;
        }
        MetadataBandGroup[] metadata = parseMetadata(inputStream, strArr, iArr2, iArr3, "class");
        List attributes = metadata[0].getAttributes();
        List attributes2 = metadata[1].getAttributes();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            long[] jArr = this.classFlags;
            if (i2 >= jArr.length) {
                return i;
            }
            if (attributeLayout.matches(jArr[i2])) {
                this.classAttributes[i2].add(attributes.get(i3));
                i3++;
            }
            if (attributeLayout2.matches(this.classFlags[i2])) {
                this.classAttributes[i2].add(attributes2.get(i4));
                i4++;
            }
            i2++;
        }
    }

    public ArrayList[] getClassAttributes() {
        return this.classAttributes;
    }

    public int[] getClassFieldCount() {
        return this.classFieldCount;
    }

    public long[] getRawClassFlags() {
        return this.classFlags;
    }

    public long[] getClassFlags() throws Pack200Exception {
        if (this.classAccessFlags == null) {
            int i = 0;
            long j = 32767;
            for (int i2 = 0; i2 < 16; i2++) {
                AttributeLayout attributeLayout = this.attrMap.getAttributeLayout(i2, 0);
                if (attributeLayout != null && !attributeLayout.isDefaultLayout()) {
                    j &= (long) (~(1 << i2));
                }
            }
            this.classAccessFlags = new long[this.classFlags.length];
            while (true) {
                long[] jArr = this.classFlags;
                if (i >= jArr.length) {
                    break;
                }
                this.classAccessFlags[i] = jArr[i] & j;
                i++;
            }
        }
        return this.classAccessFlags;
    }

    public int[][] getClassInterfacesInts() {
        return this.classInterfacesInts;
    }

    public int[] getClassMethodCount() {
        return this.classMethodCount;
    }

    public int[] getClassSuperInts() {
        return this.classSuperInts;
    }

    public int[] getClassThisInts() {
        return this.classThisInts;
    }

    public int[] getCodeMaxNALocals() {
        return this.codeMaxNALocals;
    }

    public int[] getCodeMaxStack() {
        return this.codeMaxStack;
    }

    public ArrayList[][] getFieldAttributes() {
        return this.fieldAttributes;
    }

    public int[][] getFieldDescrInts() {
        return this.fieldDescrInts;
    }

    public int[][] getMethodDescrInts() {
        return this.methodDescrInts;
    }

    public long[][] getFieldFlags() throws Pack200Exception {
        if (this.fieldAccessFlags == null) {
            long j = 32767;
            for (int i = 0; i < 16; i++) {
                AttributeLayout attributeLayout = this.attrMap.getAttributeLayout(i, 1);
                if (attributeLayout != null && !attributeLayout.isDefaultLayout()) {
                    j &= (long) (~(1 << i));
                }
            }
            this.fieldAccessFlags = new long[this.fieldFlags.length][];
            int i2 = 0;
            while (true) {
                long[][] jArr = this.fieldFlags;
                if (i2 >= jArr.length) {
                    break;
                }
                this.fieldAccessFlags[i2] = new long[jArr[i2].length];
                int i3 = 0;
                while (true) {
                    long[] jArr2 = this.fieldFlags[i2];
                    if (i3 < jArr2.length) {
                        this.fieldAccessFlags[i2][i3] = jArr2[i3] & j;
                        i3++;
                    }
                }
                i2++;
            }
        }
        return this.fieldAccessFlags;
    }

    public ArrayList getOrderedCodeAttributes() {
        ArrayList arrayList = new ArrayList(this.codeAttributes.length);
        for (int i = 0; i < this.codeAttributes.length; i++) {
            ArrayList arrayList2 = new ArrayList(this.codeAttributes[i].size());
            for (int i2 = 0; i2 < this.codeAttributes[i].size(); i2++) {
                arrayList2.add((Attribute) this.codeAttributes[i].get(i2));
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    public ArrayList[][] getMethodAttributes() {
        return this.methodAttributes;
    }

    public String[][] getMethodDescr() {
        return this.methodDescr;
    }

    public long[][] getMethodFlags() throws Pack200Exception {
        if (this.methodAccessFlags == null) {
            long j = 32767;
            for (int i = 0; i < 16; i++) {
                AttributeLayout attributeLayout = this.attrMap.getAttributeLayout(i, 2);
                if (attributeLayout != null && !attributeLayout.isDefaultLayout()) {
                    j &= (long) (~(1 << i));
                }
            }
            this.methodAccessFlags = new long[this.methodFlags.length][];
            int i2 = 0;
            while (true) {
                long[][] jArr = this.methodFlags;
                if (i2 >= jArr.length) {
                    break;
                }
                this.methodAccessFlags[i2] = new long[jArr[i2].length];
                int i3 = 0;
                while (true) {
                    long[] jArr2 = this.methodFlags[i2];
                    if (i3 < jArr2.length) {
                        this.methodAccessFlags[i2][i3] = jArr2[i3] & j;
                        i3++;
                    }
                }
                i2++;
            }
        }
        return this.methodAccessFlags;
    }

    public int[] getClassVersionMajor() {
        return this.classVersionMajor;
    }

    public int[] getClassVersionMinor() {
        return this.classVersionMinor;
    }

    public int[] getCodeHandlerCount() {
        return this.codeHandlerCount;
    }

    public int[][] getCodeHandlerCatchPO() {
        return this.codeHandlerCatchPO;
    }

    public int[][] getCodeHandlerClassRCN() {
        return this.codeHandlerClassRCN;
    }

    public int[][] getCodeHandlerEndPO() {
        return this.codeHandlerEndPO;
    }

    public int[][] getCodeHandlerStartP() {
        return this.codeHandlerStartP;
    }

    public IcTuple[][] getIcLocal() {
        return this.icLocal;
    }

    public boolean[] getCodeHasAttributes() {
        return this.codeHasAttributes;
    }
}
