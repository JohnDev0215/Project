package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.objectweb.asm.Label;

/* JADX INFO: loaded from: classes4.dex */
public class BcBands extends BandSet {
    private static final int ALOAD_0 = 42;
    private static final int IINC = 132;
    private static final int INVOKEINTERFACE = 185;
    private static final int LOOKUPSWITCH = 171;
    private static final int MULTIANEWARRAY = 197;
    private static final int TABLESWITCH = 170;
    private static final int WIDE = 196;
    private static final int endMarker = 255;
    private final IntList bcByte;
    private final IntList bcCaseCount;
    private final IntList bcCaseValue;
    private final List bcClassRef;
    private final IntList bcCodes;
    private final List bcDoubleRef;
    private final List bcFieldRef;
    private final List bcFloatRef;
    private final List bcIMethodRef;
    private List bcInitRef;
    private final List bcIntref;
    private final List bcLabel;
    private final IntList bcLabelRelativeOffsets;
    private final IntList bcLocal;
    private final List bcLongRef;
    private final List bcMethodRef;
    private final IntList bcShort;
    private final List bcStringRef;
    private final List bcSuperField;
    private List bcSuperMethod;
    private List bcThisField;
    private List bcThisMethod;
    private final IntList bciRenumbering;
    private int byteCodeOffset;
    private final CpBands cpBands;
    private String currentClass;
    private String currentNewClass;
    private final Map labelsToOffsets;
    private int renumberedOffset;
    private final Segment segment;
    private String superClass;

    public BcBands(CpBands cpBands, Segment segment, int i) {
        super(i, segment.getSegmentHeader());
        this.bcCodes = new IntList();
        this.bcCaseCount = new IntList();
        this.bcCaseValue = new IntList();
        this.bcByte = new IntList();
        this.bcShort = new IntList();
        this.bcLocal = new IntList();
        this.bcLabel = new ArrayList();
        this.bcIntref = new ArrayList();
        this.bcFloatRef = new ArrayList();
        this.bcLongRef = new ArrayList();
        this.bcDoubleRef = new ArrayList();
        this.bcStringRef = new ArrayList();
        this.bcClassRef = new ArrayList();
        this.bcFieldRef = new ArrayList();
        this.bcMethodRef = new ArrayList();
        this.bcIMethodRef = new ArrayList();
        this.bcThisField = new ArrayList();
        this.bcSuperField = new ArrayList();
        this.bcThisMethod = new ArrayList();
        this.bcSuperMethod = new ArrayList();
        this.bcInitRef = new ArrayList();
        this.bciRenumbering = new IntList();
        this.labelsToOffsets = new HashMap();
        this.bcLabelRelativeOffsets = new IntList();
        this.cpBands = cpBands;
        this.segment = segment;
    }

    public void setCurrentClass(String str, String str2) {
        this.currentClass = str;
        this.superClass = str2;
    }

    public void finaliseBands() {
        this.bcThisField = getIndexInClass(this.bcThisField);
        this.bcThisMethod = getIndexInClass(this.bcThisMethod);
        this.bcSuperMethod = getIndexInClass(this.bcSuperMethod);
        this.bcInitRef = getIndexInClassForConstructor(this.bcInitRef);
    }

    @Override // org.apache.commons.compress.harmony.pack200.BandSet
    public void pack(OutputStream outputStream) throws Pack200Exception, IOException {
        PackingUtils.log("Writing byte code bands...");
        byte[] bArrEncodeBandInt = encodeBandInt("bcCodes", this.bcCodes.toArray(), Codec.BYTE1);
        outputStream.write(bArrEncodeBandInt);
        PackingUtils.log("Wrote " + bArrEncodeBandInt.length + " bytes from bcCodes[" + this.bcCodes.size() + "]");
        byte[] bArrEncodeBandInt2 = encodeBandInt("bcCaseCount", this.bcCaseCount.toArray(), Codec.UNSIGNED5);
        outputStream.write(bArrEncodeBandInt2);
        PackingUtils.log("Wrote " + bArrEncodeBandInt2.length + " bytes from bcCaseCount[" + this.bcCaseCount.size() + "]");
        byte[] bArrEncodeBandInt3 = encodeBandInt("bcCaseValue", this.bcCaseValue.toArray(), Codec.DELTA5);
        outputStream.write(bArrEncodeBandInt3);
        PackingUtils.log("Wrote " + bArrEncodeBandInt3.length + " bytes from bcCaseValue[" + this.bcCaseValue.size() + "]");
        byte[] bArrEncodeBandInt4 = encodeBandInt("bcByte", this.bcByte.toArray(), Codec.BYTE1);
        outputStream.write(bArrEncodeBandInt4);
        PackingUtils.log("Wrote " + bArrEncodeBandInt4.length + " bytes from bcByte[" + this.bcByte.size() + "]");
        byte[] bArrEncodeBandInt5 = encodeBandInt("bcShort", this.bcShort.toArray(), Codec.DELTA5);
        outputStream.write(bArrEncodeBandInt5);
        PackingUtils.log("Wrote " + bArrEncodeBandInt5.length + " bytes from bcShort[" + this.bcShort.size() + "]");
        byte[] bArrEncodeBandInt6 = encodeBandInt("bcLocal", this.bcLocal.toArray(), Codec.UNSIGNED5);
        outputStream.write(bArrEncodeBandInt6);
        PackingUtils.log("Wrote " + bArrEncodeBandInt6.length + " bytes from bcLocal[" + this.bcLocal.size() + "]");
        byte[] bArrEncodeBandInt7 = encodeBandInt("bcLabel", integerListToArray(this.bcLabel), Codec.BRANCH5);
        outputStream.write(bArrEncodeBandInt7);
        PackingUtils.log("Wrote " + bArrEncodeBandInt7.length + " bytes from bcLabel[" + this.bcLabel.size() + "]");
        byte[] bArrEncodeBandInt8 = encodeBandInt("bcIntref", cpEntryListToArray(this.bcIntref), Codec.DELTA5);
        outputStream.write(bArrEncodeBandInt8);
        PackingUtils.log("Wrote " + bArrEncodeBandInt8.length + " bytes from bcIntref[" + this.bcIntref.size() + "]");
        byte[] bArrEncodeBandInt9 = encodeBandInt("bcFloatRef", cpEntryListToArray(this.bcFloatRef), Codec.DELTA5);
        outputStream.write(bArrEncodeBandInt9);
        PackingUtils.log("Wrote " + bArrEncodeBandInt9.length + " bytes from bcFloatRef[" + this.bcFloatRef.size() + "]");
        byte[] bArrEncodeBandInt10 = encodeBandInt("bcLongRef", cpEntryListToArray(this.bcLongRef), Codec.DELTA5);
        outputStream.write(bArrEncodeBandInt10);
        PackingUtils.log("Wrote " + bArrEncodeBandInt10.length + " bytes from bcLongRef[" + this.bcLongRef.size() + "]");
        byte[] bArrEncodeBandInt11 = encodeBandInt("bcDoubleRef", cpEntryListToArray(this.bcDoubleRef), Codec.DELTA5);
        outputStream.write(bArrEncodeBandInt11);
        PackingUtils.log("Wrote " + bArrEncodeBandInt11.length + " bytes from bcDoubleRef[" + this.bcDoubleRef.size() + "]");
        byte[] bArrEncodeBandInt12 = encodeBandInt("bcStringRef", cpEntryListToArray(this.bcStringRef), Codec.DELTA5);
        outputStream.write(bArrEncodeBandInt12);
        PackingUtils.log("Wrote " + bArrEncodeBandInt12.length + " bytes from bcStringRef[" + this.bcStringRef.size() + "]");
        byte[] bArrEncodeBandInt13 = encodeBandInt("bcClassRef", cpEntryOrNullListToArray(this.bcClassRef), Codec.UNSIGNED5);
        outputStream.write(bArrEncodeBandInt13);
        PackingUtils.log("Wrote " + bArrEncodeBandInt13.length + " bytes from bcClassRef[" + this.bcClassRef.size() + "]");
        byte[] bArrEncodeBandInt14 = encodeBandInt("bcFieldRef", cpEntryListToArray(this.bcFieldRef), Codec.DELTA5);
        outputStream.write(bArrEncodeBandInt14);
        PackingUtils.log("Wrote " + bArrEncodeBandInt14.length + " bytes from bcFieldRef[" + this.bcFieldRef.size() + "]");
        byte[] bArrEncodeBandInt15 = encodeBandInt("bcMethodRef", cpEntryListToArray(this.bcMethodRef), Codec.UNSIGNED5);
        outputStream.write(bArrEncodeBandInt15);
        PackingUtils.log("Wrote " + bArrEncodeBandInt15.length + " bytes from bcMethodRef[" + this.bcMethodRef.size() + "]");
        byte[] bArrEncodeBandInt16 = encodeBandInt("bcIMethodRef", cpEntryListToArray(this.bcIMethodRef), Codec.DELTA5);
        outputStream.write(bArrEncodeBandInt16);
        PackingUtils.log("Wrote " + bArrEncodeBandInt16.length + " bytes from bcIMethodRef[" + this.bcIMethodRef.size() + "]");
        byte[] bArrEncodeBandInt17 = encodeBandInt("bcThisField", integerListToArray(this.bcThisField), Codec.UNSIGNED5);
        outputStream.write(bArrEncodeBandInt17);
        PackingUtils.log("Wrote " + bArrEncodeBandInt17.length + " bytes from bcThisField[" + this.bcThisField.size() + "]");
        byte[] bArrEncodeBandInt18 = encodeBandInt("bcSuperField", integerListToArray(this.bcSuperField), Codec.UNSIGNED5);
        outputStream.write(bArrEncodeBandInt18);
        PackingUtils.log("Wrote " + bArrEncodeBandInt18.length + " bytes from bcSuperField[" + this.bcSuperField.size() + "]");
        byte[] bArrEncodeBandInt19 = encodeBandInt("bcThisMethod", integerListToArray(this.bcThisMethod), Codec.UNSIGNED5);
        outputStream.write(bArrEncodeBandInt19);
        PackingUtils.log("Wrote " + bArrEncodeBandInt19.length + " bytes from bcThisMethod[" + this.bcThisMethod.size() + "]");
        byte[] bArrEncodeBandInt20 = encodeBandInt("bcSuperMethod", integerListToArray(this.bcSuperMethod), Codec.UNSIGNED5);
        outputStream.write(bArrEncodeBandInt20);
        PackingUtils.log("Wrote " + bArrEncodeBandInt20.length + " bytes from bcSuperMethod[" + this.bcSuperMethod.size() + "]");
        byte[] bArrEncodeBandInt21 = encodeBandInt("bcInitRef", integerListToArray(this.bcInitRef), Codec.UNSIGNED5);
        outputStream.write(bArrEncodeBandInt21);
        PackingUtils.log("Wrote " + bArrEncodeBandInt21.length + " bytes from bcInitRef[" + this.bcInitRef.size() + "]");
    }

    private List getIndexInClass(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(Integer.valueOf(((CPMethodOrField) list.get(i)).getIndexInClass()));
        }
        return arrayList;
    }

    private List getIndexInClassForConstructor(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(Integer.valueOf(((CPMethodOrField) list.get(i)).getIndexInClassForConstructor()));
        }
        return arrayList;
    }

    public void visitEnd() {
        for (int i = 0; i < this.bciRenumbering.size(); i++) {
            if (this.bciRenumbering.get(i) == -1) {
                this.bciRenumbering.remove(i);
                IntList intList = this.bciRenumbering;
                int i2 = this.renumberedOffset + 1;
                this.renumberedOffset = i2;
                intList.add(i, i2);
            }
        }
        int i3 = this.renumberedOffset;
        if (i3 != 0) {
            if (i3 + 1 != this.bciRenumbering.size()) {
                throw new RuntimeException("Mistake made with renumbering");
            }
            for (int size = this.bcLabel.size() - 1; size >= 0; size--) {
                Object obj = this.bcLabel.get(size);
                if (obj instanceof Integer) {
                    break;
                }
                if (obj instanceof Label) {
                    this.bcLabel.remove(size);
                    this.bcLabel.add(size, Integer.valueOf(this.bciRenumbering.get(((Integer) this.labelsToOffsets.get(obj)).intValue()) - this.bciRenumbering.get(this.bcLabelRelativeOffsets.get(size))));
                }
            }
            this.bcCodes.add(255);
            this.segment.getClassBands().doBciRenumbering(this.bciRenumbering, this.labelsToOffsets);
            this.bciRenumbering.clear();
            this.labelsToOffsets.clear();
            this.byteCodeOffset = 0;
            this.renumberedOffset = 0;
        }
    }

    public void visitLabel(Label label) {
        this.labelsToOffsets.put(label, Integer.valueOf(this.byteCodeOffset));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void visitFieldInsn(int r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            r4 = this;
            int r0 = r4.byteCodeOffset
            int r0 = r0 + 3
            r4.byteCodeOffset = r0
            r4.updateRenumbering()
            org.apache.commons.compress.harmony.pack200.IntList r0 = r4.bcCodes
            int r0 = r0.size()
            r1 = 42
            if (r0 <= 0) goto L2c
            org.apache.commons.compress.harmony.pack200.IntList r0 = r4.bcCodes
            int r2 = r0.size()
            r3 = 1
            int r2 = r2 - r3
            int r0 = r0.get(r2)
            if (r0 != r1) goto L2c
            org.apache.commons.compress.harmony.pack200.IntList r0 = r4.bcCodes
            int r2 = r0.size()
            int r2 = r2 - r3
            r0.remove(r2)
            goto L2d
        L2c:
            r3 = 0
        L2d:
            org.apache.commons.compress.harmony.pack200.CpBands r0 = r4.cpBands
            org.apache.commons.compress.harmony.pack200.CPMethodOrField r7 = r0.getCPField(r6, r7, r8)
            if (r3 == 0) goto L37
            int r5 = r5 + 7
        L37:
            java.lang.String r8 = r4.currentClass
            boolean r6 = r6.equals(r8)
            if (r6 == 0) goto L47
            int r5 = r5 + 24
            java.util.List r6 = r4.bcThisField
            r6.add(r7)
            goto L55
        L47:
            if (r3 == 0) goto L50
            int r5 = r5 + (-7)
            org.apache.commons.compress.harmony.pack200.IntList r6 = r4.bcCodes
            r6.add(r1)
        L50:
            java.util.List r6 = r4.bcFieldRef
            r6.add(r7)
        L55:
            org.apache.commons.compress.harmony.pack200.IntList r6 = r4.bcCodes
            r6.add(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.pack200.BcBands.visitFieldInsn(int, java.lang.String, java.lang.String, java.lang.String):void");
    }

    private void updateRenumbering() {
        if (this.bciRenumbering.isEmpty()) {
            this.bciRenumbering.add(0);
        }
        this.renumberedOffset++;
        for (int size = this.bciRenumbering.size(); size < this.byteCodeOffset; size++) {
            this.bciRenumbering.add(-1);
        }
        this.bciRenumbering.add(this.renumberedOffset);
    }

    public void visitIincInsn(int i, int i2) {
        if (i > 255 || i2 > 255) {
            this.byteCodeOffset += 6;
            this.bcCodes.add(196);
            this.bcCodes.add(132);
            this.bcLocal.add(i);
            this.bcShort.add(i2);
        } else {
            this.byteCodeOffset += 3;
            this.bcCodes.add(132);
            this.bcLocal.add(i);
            this.bcByte.add(i2 & 255);
        }
        updateRenumbering();
    }

    public void visitInsn(int i) {
        if (i >= 202) {
            throw new RuntimeException("Non-standard bytecode instructions not supported");
        }
        this.bcCodes.add(i);
        this.byteCodeOffset++;
        updateRenumbering();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void visitIntInsn(int r2, int r3) {
        /*
            r1 = this;
            r0 = 16
            if (r2 == r0) goto L1e
            r0 = 17
            if (r2 == r0) goto Ld
            r0 = 188(0xbc, float:2.63E-43)
            if (r2 == r0) goto L1e
            goto L30
        Ld:
            org.apache.commons.compress.harmony.pack200.IntList r0 = r1.bcCodes
            r0.add(r2)
            org.apache.commons.compress.harmony.pack200.IntList r2 = r1.bcShort
            r2.add(r3)
            int r2 = r1.byteCodeOffset
            int r2 = r2 + 3
            r1.byteCodeOffset = r2
            goto L30
        L1e:
            org.apache.commons.compress.harmony.pack200.IntList r0 = r1.bcCodes
            r0.add(r2)
            org.apache.commons.compress.harmony.pack200.IntList r2 = r1.bcByte
            r3 = r3 & 255(0xff, float:3.57E-43)
            r2.add(r3)
            int r2 = r1.byteCodeOffset
            int r2 = r2 + 2
            r1.byteCodeOffset = r2
        L30:
            r1.updateRenumbering()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.pack200.BcBands.visitIntInsn(int, int):void");
    }

    public void visitJumpInsn(int i, Label label) {
        this.bcCodes.add(i);
        this.bcLabel.add(label);
        this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        this.byteCodeOffset += 3;
        updateRenumbering();
    }

    public void visitLdcInsn(Object obj) {
        CPConstant constant = this.cpBands.getConstant(obj);
        if (this.segment.lastConstantHadWideIndex() || (constant instanceof CPLong) || (constant instanceof CPDouble)) {
            this.byteCodeOffset += 3;
            if (constant instanceof CPInt) {
                this.bcCodes.add(237);
                this.bcIntref.add(constant);
            } else if (constant instanceof CPFloat) {
                this.bcCodes.add(238);
                this.bcFloatRef.add(constant);
            } else if (constant instanceof CPLong) {
                this.bcCodes.add(20);
                this.bcLongRef.add(constant);
            } else if (constant instanceof CPDouble) {
                this.bcCodes.add(239);
                this.bcDoubleRef.add(constant);
            } else if (constant instanceof CPString) {
                this.bcCodes.add(19);
                this.bcStringRef.add(constant);
            } else if (constant instanceof CPClass) {
                this.bcCodes.add(236);
                this.bcClassRef.add(constant);
            } else {
                throw new RuntimeException("Constant should not be null");
            }
        } else {
            this.byteCodeOffset += 2;
            if (constant instanceof CPInt) {
                this.bcCodes.add(234);
                this.bcIntref.add(constant);
            } else if (constant instanceof CPFloat) {
                this.bcCodes.add(235);
                this.bcFloatRef.add(constant);
            } else if (constant instanceof CPString) {
                this.bcCodes.add(18);
                this.bcStringRef.add(constant);
            } else if (constant instanceof CPClass) {
                this.bcCodes.add(233);
                this.bcClassRef.add(constant);
            }
        }
        updateRenumbering();
    }

    public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        this.bcCodes.add(171);
        this.bcLabel.add(label);
        this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        this.bcCaseCount.add(iArr.length);
        for (int i = 0; i < labelArr.length; i++) {
            this.bcCaseValue.add(iArr[i]);
            this.bcLabel.add(labelArr[i]);
            this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        }
        int i2 = this.byteCodeOffset;
        this.byteCodeOffset = i2 + ((i2 + 1) % 4 != 0 ? 4 - ((i2 + 1) % 4) : 0) + 1 + 8 + (iArr.length * 8);
        updateRenumbering();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void visitMethodInsn(int r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            Method dump skipped, instruction units count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.pack200.BcBands.visitMethodInsn(int, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public void visitMultiANewArrayInsn(String str, int i) {
        this.byteCodeOffset += 4;
        updateRenumbering();
        this.bcCodes.add(197);
        this.bcClassRef.add(this.cpBands.getCPClass(str));
        this.bcByte.add(i & 255);
    }

    public void visitTableSwitchInsn(int i, int i2, Label label, Label[] labelArr) {
        this.bcCodes.add(170);
        this.bcLabel.add(label);
        this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        this.bcCaseValue.add(i);
        this.bcCaseCount.add(labelArr.length);
        for (Label label2 : labelArr) {
            this.bcLabel.add(label2);
            this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        }
        int i3 = this.byteCodeOffset;
        this.byteCodeOffset = i3 + (i3 % 4 != 0 ? 4 - (i3 % 4) : 0) + 12 + (labelArr.length * 4);
        updateRenumbering();
    }

    public void visitTypeInsn(int i, String str) {
        this.byteCodeOffset += 3;
        updateRenumbering();
        this.bcCodes.add(i);
        this.bcClassRef.add(this.cpBands.getCPClass(str));
        if (i == 187) {
            this.currentNewClass = str;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0055  */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1091)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void visitVarInsn(int r3, int r4) {
        /*
            r2 = this;
            r0 = 255(0xff, float:3.57E-43)
            if (r4 <= r0) goto L1c
            int r0 = r2.byteCodeOffset
            int r0 = r0 + 4
            r2.byteCodeOffset = r0
            org.apache.commons.compress.harmony.pack200.IntList r0 = r2.bcCodes
            r1 = 196(0xc4, float:2.75E-43)
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.IntList r0 = r2.bcCodes
            r0.add(r3)
            org.apache.commons.compress.harmony.pack200.IntList r3 = r2.bcLocal
            r3.add(r4)
            goto L6e
        L1c:
            r0 = 3
            if (r4 > r0) goto L5e
            r0 = 169(0xa9, float:2.37E-43)
            if (r3 != r0) goto L24
            goto L5e
        L24:
            int r0 = r2.byteCodeOffset
            int r0 = r0 + 1
            r2.byteCodeOffset = r0
            switch(r3) {
                case 21: goto L55;
                case 22: goto L4c;
                case 23: goto L43;
                case 24: goto L3a;
                case 25: goto L31;
                default: goto L2d;
            }
        L2d:
            switch(r3) {
                case 54: goto L55;
                case 55: goto L4c;
                case 56: goto L43;
                case 57: goto L3a;
                case 58: goto L31;
                default: goto L30;
            }
        L30:
            goto L6e
        L31:
            org.apache.commons.compress.harmony.pack200.IntList r0 = r2.bcCodes
            int r3 = r3 + 17
            int r3 = r3 + r4
            r0.add(r3)
            goto L6e
        L3a:
            org.apache.commons.compress.harmony.pack200.IntList r0 = r2.bcCodes
            int r3 = r3 + 14
            int r3 = r3 + r4
            r0.add(r3)
            goto L6e
        L43:
            org.apache.commons.compress.harmony.pack200.IntList r0 = r2.bcCodes
            int r3 = r3 + 11
            int r3 = r3 + r4
            r0.add(r3)
            goto L6e
        L4c:
            org.apache.commons.compress.harmony.pack200.IntList r0 = r2.bcCodes
            int r3 = r3 + 8
            int r3 = r3 + r4
            r0.add(r3)
            goto L6e
        L55:
            org.apache.commons.compress.harmony.pack200.IntList r0 = r2.bcCodes
            int r3 = r3 + 5
            int r3 = r3 + r4
            r0.add(r3)
            goto L6e
        L5e:
            int r0 = r2.byteCodeOffset
            int r0 = r0 + 2
            r2.byteCodeOffset = r0
            org.apache.commons.compress.harmony.pack200.IntList r0 = r2.bcCodes
            r0.add(r3)
            org.apache.commons.compress.harmony.pack200.IntList r3 = r2.bcLocal
            r3.add(r4)
        L6e:
            r2.updateRenumbering()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.pack200.BcBands.visitVarInsn(int, int):void");
    }
}
