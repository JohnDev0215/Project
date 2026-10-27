package net.bytebuddy.jar.asm;

/* JADX INFO: loaded from: classes3.dex */
final class AnnotationWriter extends AnnotationVisitor {
    private final ByteVector bv;
    private final ClassWriter cw;
    private final boolean named;
    AnnotationWriter next;
    private final int offset;
    private final ByteVector parent;
    AnnotationWriter prev;
    private int size;

    AnnotationWriter(ClassWriter classWriter, boolean z, ByteVector byteVector, ByteVector byteVector2, int i) {
        super(Opcodes.ASM6);
        this.cw = classWriter;
        this.named = z;
        this.bv = byteVector;
        this.parent = byteVector2;
        this.offset = i;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // net.bytebuddy.jar.asm.AnnotationVisitor
    public void visit(String str, Object obj) {
        this.size++;
        if (this.named) {
            this.bv.putShort(this.cw.newUTF8(str));
        }
        if (obj instanceof String) {
            this.bv.put12(115, this.cw.newUTF8((String) obj));
            return;
        }
        if (obj instanceof Byte) {
            this.bv.put12(66, this.cw.newInteger(((Byte) obj).byteValue()).index);
            return;
        }
        if (obj instanceof Boolean) {
            this.bv.put12(90, this.cw.newInteger(((Boolean) obj).booleanValue() ? 1 : 0).index);
            return;
        }
        if (obj instanceof Character) {
            this.bv.put12(67, this.cw.newInteger(((Character) obj).charValue()).index);
            return;
        }
        if (obj instanceof Short) {
            this.bv.put12(83, this.cw.newInteger(((Short) obj).shortValue()).index);
            return;
        }
        if (obj instanceof Type) {
            this.bv.put12(99, this.cw.newUTF8(((Type) obj).getDescriptor()));
            return;
        }
        int i = 0;
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            this.bv.put12(91, bArr.length);
            while (i < bArr.length) {
                this.bv.put12(66, this.cw.newInteger(bArr[i]).index);
                i++;
            }
            return;
        }
        if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            this.bv.put12(91, zArr.length);
            while (i < zArr.length) {
                this.bv.put12(90, this.cw.newInteger(zArr[i] ? 1 : 0).index);
                i++;
            }
            return;
        }
        if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            this.bv.put12(91, sArr.length);
            while (i < sArr.length) {
                this.bv.put12(83, this.cw.newInteger(sArr[i]).index);
                i++;
            }
            return;
        }
        if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            this.bv.put12(91, cArr.length);
            while (i < cArr.length) {
                this.bv.put12(67, this.cw.newInteger(cArr[i]).index);
                i++;
            }
            return;
        }
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            this.bv.put12(91, iArr.length);
            while (i < iArr.length) {
                this.bv.put12(73, this.cw.newInteger(iArr[i]).index);
                i++;
            }
            return;
        }
        if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            this.bv.put12(91, jArr.length);
            while (i < jArr.length) {
                this.bv.put12(74, this.cw.newLong(jArr[i]).index);
                i++;
            }
            return;
        }
        if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            this.bv.put12(91, fArr.length);
            while (i < fArr.length) {
                this.bv.put12(70, this.cw.newFloat(fArr[i]).index);
                i++;
            }
            return;
        }
        if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            this.bv.put12(91, dArr.length);
            while (i < dArr.length) {
                this.bv.put12(68, this.cw.newDouble(dArr[i]).index);
                i++;
            }
            return;
        }
        Item itemNewConstItem = this.cw.newConstItem(obj);
        this.bv.put12(".s.IFJDCS".charAt(itemNewConstItem.type), itemNewConstItem.index);
    }

    @Override // net.bytebuddy.jar.asm.AnnotationVisitor
    public void visitEnum(String str, String str2, String str3) {
        this.size++;
        if (this.named) {
            this.bv.putShort(this.cw.newUTF8(str));
        }
        this.bv.put12(101, this.cw.newUTF8(str2)).putShort(this.cw.newUTF8(str3));
    }

    @Override // net.bytebuddy.jar.asm.AnnotationVisitor
    public AnnotationVisitor visitAnnotation(String str, String str2) {
        this.size++;
        if (this.named) {
            this.bv.putShort(this.cw.newUTF8(str));
        }
        this.bv.put12(64, this.cw.newUTF8(str2)).putShort(0);
        ClassWriter classWriter = this.cw;
        ByteVector byteVector = this.bv;
        return new AnnotationWriter(classWriter, true, byteVector, byteVector, byteVector.length - 2);
    }

    @Override // net.bytebuddy.jar.asm.AnnotationVisitor
    public AnnotationVisitor visitArray(String str) {
        this.size++;
        if (this.named) {
            this.bv.putShort(this.cw.newUTF8(str));
        }
        this.bv.put12(91, 0);
        ClassWriter classWriter = this.cw;
        ByteVector byteVector = this.bv;
        return new AnnotationWriter(classWriter, false, byteVector, byteVector, byteVector.length - 2);
    }

    @Override // net.bytebuddy.jar.asm.AnnotationVisitor
    public void visitEnd() {
        ByteVector byteVector = this.parent;
        if (byteVector != null) {
            byte[] bArr = byteVector.data;
            int i = this.offset;
            int i2 = this.size;
            bArr[i] = (byte) (i2 >>> 8);
            bArr[i + 1] = (byte) i2;
        }
    }

    int getSize() {
        int i = 0;
        for (AnnotationWriter annotationWriter = this; annotationWriter != null; annotationWriter = annotationWriter.next) {
            i += annotationWriter.bv.length;
        }
        return i;
    }

    void put(ByteVector byteVector) {
        int i = 2;
        int i2 = 0;
        AnnotationWriter annotationWriter = null;
        for (AnnotationWriter annotationWriter2 = this; annotationWriter2 != null; annotationWriter2 = annotationWriter2.next) {
            i2++;
            i += annotationWriter2.bv.length;
            annotationWriter2.visitEnd();
            annotationWriter2.prev = annotationWriter;
            annotationWriter = annotationWriter2;
        }
        byteVector.putInt(i);
        byteVector.putShort(i2);
        while (annotationWriter != null) {
            byteVector.putByteArray(annotationWriter.bv.data, 0, annotationWriter.bv.length);
            annotationWriter = annotationWriter.prev;
        }
    }

    static void put(AnnotationWriter[] annotationWriterArr, int i, ByteVector byteVector) {
        int length = ((annotationWriterArr.length - i) * 2) + 1;
        int i2 = i;
        while (true) {
            int size = 0;
            if (i2 >= annotationWriterArr.length) {
                break;
            }
            AnnotationWriter annotationWriter = annotationWriterArr[i2];
            if (annotationWriter != null) {
                size = annotationWriter.getSize();
            }
            length += size;
            i2++;
        }
        byteVector.putInt(length).putByte(annotationWriterArr.length - i);
        while (i < annotationWriterArr.length) {
            AnnotationWriter annotationWriter2 = null;
            int i3 = 0;
            for (AnnotationWriter annotationWriter3 = annotationWriterArr[i]; annotationWriter3 != null; annotationWriter3 = annotationWriter3.next) {
                i3++;
                annotationWriter3.visitEnd();
                annotationWriter3.prev = annotationWriter2;
                annotationWriter2 = annotationWriter3;
            }
            byteVector.putShort(i3);
            while (annotationWriter2 != null) {
                byteVector.putByteArray(annotationWriter2.bv.data, 0, annotationWriter2.bv.length);
                annotationWriter2 = annotationWriter2.prev;
            }
            i++;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static void putTarget(int r3, net.bytebuddy.jar.asm.TypePath r4, net.bytebuddy.jar.asm.ByteVector r5) {
        /*
            int r0 = r3 >>> 24
            r1 = 1
            if (r0 == 0) goto L1f
            if (r0 == r1) goto L1f
            switch(r0) {
                case 19: goto L1b;
                case 20: goto L1b;
                case 21: goto L1b;
                case 22: goto L1f;
                default: goto La;
            }
        La:
            switch(r0) {
                case 71: goto L17;
                case 72: goto L17;
                case 73: goto L17;
                case 74: goto L17;
                case 75: goto L17;
                default: goto Ld;
            }
        Ld:
            r2 = 16776960(0xffff00, float:2.3509528E-38)
            r3 = r3 & r2
            int r3 = r3 >> 8
            r5.put12(r0, r3)
            goto L24
        L17:
            r5.putInt(r3)
            goto L24
        L1b:
            r5.putByte(r0)
            goto L24
        L1f:
            int r3 = r3 >>> 16
            r5.putShort(r3)
        L24:
            if (r4 != 0) goto L2b
            r3 = 0
            r5.putByte(r3)
            goto L3b
        L2b:
            byte[] r3 = r4.b
            int r0 = r4.offset
            r3 = r3[r0]
            int r3 = r3 * 2
            int r3 = r3 + r1
            byte[] r0 = r4.b
            int r4 = r4.offset
            r5.putByteArray(r0, r4, r3)
        L3b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.AnnotationWriter.putTarget(int, net.bytebuddy.jar.asm.TypePath, net.bytebuddy.jar.asm.ByteVector):void");
    }
}
