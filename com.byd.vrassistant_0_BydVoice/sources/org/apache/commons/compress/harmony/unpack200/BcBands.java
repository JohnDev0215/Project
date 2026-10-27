package org.apache.commons.compress.harmony.unpack200;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CodeAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.ExceptionTableEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.NewAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

/* JADX INFO: loaded from: classes4.dex */
public class BcBands extends BandSet {
    private int[] bcByte;
    private int[] bcCaseCount;
    private int[] bcCaseValue;
    private int[] bcClassRef;
    private int[] bcDoubleRef;
    private int[][] bcEscByte;
    private int[] bcEscRef;
    private int[] bcEscRefSize;
    private int[] bcEscSize;
    private int[] bcFieldRef;
    private int[] bcFloatRef;
    private int[] bcIMethodRef;
    private int[] bcInitRef;
    private int[] bcIntRef;
    private int[] bcLabel;
    private int[] bcLocal;
    private int[] bcLongRef;
    private int[] bcMethodRef;
    private int[] bcShort;
    private int[] bcStringRef;
    private int[] bcSuperField;
    private int[] bcSuperMethod;
    private int[] bcThisField;
    private int[] bcThisMethod;
    private byte[][][] methodByteCodePacked;
    private List wideByteCodes;

    private boolean endsWithLoad(int i) {
        return i >= 21 && i <= 25;
    }

    private boolean endsWithStore(int i) {
        return i >= 54 && i <= 58;
    }

    private boolean startsWithIf(int i) {
        return (i >= 153 && i <= 166) || i == 198 || i == 199;
    }

    public BcBands(Segment segment) {
        super(segment);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:34:0x00f7. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:35:0x00fa. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:36:0x00fd. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0134  */
    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void read(java.io.InputStream r38) throws org.apache.commons.compress.harmony.pack200.Pack200Exception, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 974
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.unpack200.BcBands.read(java.io.InputStream):void");
    }

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void unpack() throws Pack200Exception {
        int[] iArr;
        int[] iArr2;
        String[][] strArr;
        AttributeLayout attributeLayout;
        AttributeLayout attributeLayout2;
        ArrayList arrayList;
        List list;
        List list2;
        int classCount = this.header.getClassCount();
        long[][] methodFlags = this.segment.getClassBands().getMethodFlags();
        int[] codeMaxNALocals = this.segment.getClassBands().getCodeMaxNALocals();
        int[] codeMaxStack = this.segment.getClassBands().getCodeMaxStack();
        ArrayList[][] methodAttributes = this.segment.getClassBands().getMethodAttributes();
        String[][] methodDescr = this.segment.getClassBands().getMethodDescr();
        AttributeLayoutMap attributeDefinitionMap = this.segment.getAttrDefinitionBands().getAttributeDefinitionMap();
        AttributeLayout attributeLayout3 = attributeDefinitionMap.getAttributeLayout(AttributeLayout.ACC_ABSTRACT, 2);
        AttributeLayout attributeLayout4 = attributeDefinitionMap.getAttributeLayout(AttributeLayout.ACC_NATIVE, 2);
        AttributeLayout attributeLayout5 = attributeDefinitionMap.getAttributeLayout(AttributeLayout.ACC_STATIC, 2);
        int size = this.wideByteCodes.size();
        int[] iArr3 = new int[size];
        for (int i = 0; i < size; i++) {
            iArr3[i] = ((Integer) this.wideByteCodes.get(i)).intValue();
        }
        OperandManager operandManager = new OperandManager(this.bcCaseCount, this.bcCaseValue, this.bcByte, this.bcShort, this.bcLocal, this.bcLabel, this.bcIntRef, this.bcFloatRef, this.bcLongRef, this.bcDoubleRef, this.bcStringRef, this.bcClassRef, this.bcFieldRef, this.bcMethodRef, this.bcIMethodRef, this.bcThisField, this.bcSuperField, this.bcThisMethod, this.bcSuperMethod, this.bcInitRef, iArr3);
        operandManager.setSegment(this.segment);
        ArrayList orderedCodeAttributes = this.segment.getClassBands().getOrderedCodeAttributes();
        int[] codeHandlerCount = this.segment.getClassBands().getCodeHandlerCount();
        int[][] codeHandlerStartP = this.segment.getClassBands().getCodeHandlerStartP();
        int[][] codeHandlerEndPO = this.segment.getClassBands().getCodeHandlerEndPO();
        int[][] codeHandlerCatchPO = this.segment.getClassBands().getCodeHandlerCatchPO();
        int[][] codeHandlerClassRCN = this.segment.getClassBands().getCodeHandlerClassRCN();
        ArrayList arrayList2 = orderedCodeAttributes;
        boolean zHasAllCodeFlags = this.segment.getSegmentHeader().getOptions().hasAllCodeFlags();
        boolean[] codeHasAttributes = this.segment.getClassBands().getCodeHasAttributes();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < classCount) {
            int i5 = classCount;
            int length = methodFlags[i2].length;
            int[][] iArr4 = codeHandlerEndPO;
            int[][] iArr5 = codeHandlerCatchPO;
            int i6 = i3;
            int i7 = i4;
            int i8 = 0;
            while (i8 < length) {
                int i9 = length;
                long[][] jArr = methodFlags;
                long j = methodFlags[i2][i8];
                if (attributeLayout3.matches(j) || attributeLayout4.matches(j)) {
                    iArr = codeMaxNALocals;
                    iArr2 = codeMaxStack;
                    strArr = methodDescr;
                    attributeLayout = attributeLayout5;
                    attributeLayout2 = attributeLayout3;
                    arrayList = arrayList2;
                    i7 = i7;
                } else {
                    int i10 = codeMaxStack[i6];
                    int i11 = codeMaxNALocals[i6];
                    if (!attributeLayout5.matches(j)) {
                        i11++;
                    }
                    int iCountInvokeInterfaceArgs = i11 + SegmentUtils.countInvokeInterfaceArgs(methodDescr[i2][i8]);
                    String[] cpClass = this.segment.getCpBands().getCpClass();
                    operandManager.setCurrentClass(cpClass[this.segment.getClassBands().getClassThisInts()[i2]]);
                    operandManager.setSuperClass(cpClass[this.segment.getClassBands().getClassSuperInts()[i2]]);
                    ArrayList arrayList3 = new ArrayList();
                    iArr = codeMaxNALocals;
                    if (codeHandlerCount != null) {
                        int i12 = 0;
                        while (i12 < codeHandlerCount[i6]) {
                            int i13 = codeHandlerClassRCN[i6][i12] - 1;
                            int[] iArr6 = codeMaxStack;
                            arrayList3.add(new ExceptionTableEntry(codeHandlerStartP[i6][i12], iArr4[i6][i12], iArr5[i6][i12], i13 != -1 ? this.segment.getCpBands().cpClassValue(i13) : null));
                            i12++;
                            codeMaxStack = iArr6;
                            methodDescr = methodDescr;
                            attributeLayout5 = attributeLayout5;
                            attributeLayout3 = attributeLayout3;
                        }
                    }
                    iArr2 = codeMaxStack;
                    strArr = methodDescr;
                    attributeLayout = attributeLayout5;
                    attributeLayout2 = attributeLayout3;
                    CodeAttribute codeAttribute = new CodeAttribute(i10, iCountInvokeInterfaceArgs, this.methodByteCodePacked[i2][i8], this.segment, operandManager, arrayList3);
                    ArrayList arrayList4 = methodAttributes[i2][i8];
                    int i14 = 0;
                    for (int i15 = 0; i15 < arrayList4.size(); i15++) {
                        Attribute attribute = (Attribute) arrayList4.get(i15);
                        if (!(attribute instanceof NewAttribute) || ((NewAttribute) attribute).getLayoutIndex() >= 15) {
                            break;
                        }
                        i14++;
                    }
                    arrayList4.add(i14, codeAttribute);
                    codeAttribute.renumber(codeAttribute.byteCodeOffsets);
                    if (zHasAllCodeFlags) {
                        arrayList = arrayList2;
                        list2 = (List) arrayList.get(i6);
                    } else {
                        arrayList = arrayList2;
                        if (codeHasAttributes[i6]) {
                            int i16 = i7;
                            list = (List) arrayList.get(i16);
                            i7 = i16 + 1;
                        } else {
                            list = Collections.EMPTY_LIST;
                        }
                        list2 = list;
                    }
                    for (int i17 = 0; i17 < list2.size(); i17++) {
                        Attribute attribute2 = (Attribute) list2.get(i17);
                        codeAttribute.addAttribute(attribute2);
                        if (attribute2.hasBCIRenumbering()) {
                            ((BCIRenumberedAttribute) attribute2).renumber(codeAttribute.byteCodeOffsets);
                        }
                    }
                    i6++;
                }
                i8++;
                arrayList2 = arrayList;
                methodFlags = jArr;
                length = i9;
                codeMaxNALocals = iArr;
                codeMaxStack = iArr2;
                methodDescr = strArr;
                attributeLayout5 = attributeLayout;
                attributeLayout3 = attributeLayout2;
            }
            i2++;
            i4 = i7;
            i3 = i6;
            classCount = i5;
            codeHandlerCatchPO = iArr5;
            codeHandlerEndPO = iArr4;
            codeMaxNALocals = codeMaxNALocals;
        }
    }

    public byte[][][] getMethodByteCodePacked() {
        return this.methodByteCodePacked;
    }

    public int[] getBcCaseCount() {
        return this.bcCaseCount;
    }

    public int[] getBcCaseValue() {
        return this.bcCaseValue;
    }

    public int[] getBcByte() {
        return this.bcByte;
    }

    public int[] getBcClassRef() {
        return this.bcClassRef;
    }

    public int[] getBcDoubleRef() {
        return this.bcDoubleRef;
    }

    public int[] getBcFieldRef() {
        return this.bcFieldRef;
    }

    public int[] getBcFloatRef() {
        return this.bcFloatRef;
    }

    public int[] getBcIMethodRef() {
        return this.bcIMethodRef;
    }

    public int[] getBcInitRef() {
        return this.bcInitRef;
    }

    public int[] getBcIntRef() {
        return this.bcIntRef;
    }

    public int[] getBcLabel() {
        return this.bcLabel;
    }

    public int[] getBcLocal() {
        return this.bcLocal;
    }

    public int[] getBcLongRef() {
        return this.bcLongRef;
    }

    public int[] getBcMethodRef() {
        return this.bcMethodRef;
    }

    public int[] getBcShort() {
        return this.bcShort;
    }

    public int[] getBcStringRef() {
        return this.bcStringRef;
    }

    public int[] getBcSuperField() {
        return this.bcSuperField;
    }

    public int[] getBcSuperMethod() {
        return this.bcSuperMethod;
    }

    public int[] getBcThisField() {
        return this.bcThisField;
    }

    public int[] getBcThisMethod() {
        return this.bcThisMethod;
    }
}
