package net.bytebuddy.jar.asm;

import com.android.dx.cf.attrib.AttSynthetic;

/* JADX INFO: loaded from: classes3.dex */
final class FieldWriter extends FieldVisitor {
    private final int access;
    private AnnotationWriter anns;
    private Attribute attrs;
    private final ClassWriter cw;
    private final int desc;
    private AnnotationWriter ianns;
    private AnnotationWriter itanns;
    private final int name;
    private int signature;
    private AnnotationWriter tanns;
    private int value;

    @Override // net.bytebuddy.jar.asm.FieldVisitor
    public void visitEnd() {
    }

    FieldWriter(ClassWriter classWriter, int i, String str, String str2, String str3, Object obj) {
        super(Opcodes.ASM6);
        if (classWriter.firstField == null) {
            classWriter.firstField = this;
        } else {
            classWriter.lastField.fv = this;
        }
        classWriter.lastField = this;
        this.cw = classWriter;
        this.access = i;
        this.name = classWriter.newUTF8(str);
        this.desc = classWriter.newUTF8(str2);
        if (str3 != null) {
            this.signature = classWriter.newUTF8(str3);
        }
        if (obj != null) {
            this.value = classWriter.newConstItem(obj).index;
        }
    }

    @Override // net.bytebuddy.jar.asm.FieldVisitor
    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(this.cw.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, 2);
        if (z) {
            annotationWriter.next = this.anns;
            this.anns = annotationWriter;
        } else {
            annotationWriter.next = this.ianns;
            this.ianns = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // net.bytebuddy.jar.asm.FieldVisitor
    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.putTarget(i, typePath, byteVector);
        byteVector.putShort(this.cw.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, byteVector.length - 2);
        if (z) {
            annotationWriter.next = this.tanns;
            this.tanns = annotationWriter;
        } else {
            annotationWriter.next = this.itanns;
            this.itanns = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // net.bytebuddy.jar.asm.FieldVisitor
    public void visitAttribute(Attribute attribute) {
        attribute.next = this.attrs;
        this.attrs = attribute;
    }

    int getSize() {
        int size;
        if (this.value != 0) {
            this.cw.newUTF8("ConstantValue");
            size = 16;
        } else {
            size = 8;
        }
        if ((this.access & 4096) != 0 && ((this.cw.version & 65535) < 49 || (this.access & 262144) != 0)) {
            this.cw.newUTF8(AttSynthetic.ATTRIBUTE_NAME);
            size += 6;
        }
        if ((this.access & 131072) != 0) {
            this.cw.newUTF8("Deprecated");
            size += 6;
        }
        if (this.signature != 0) {
            this.cw.newUTF8("Signature");
            size += 8;
        }
        if (this.anns != null) {
            this.cw.newUTF8("RuntimeVisibleAnnotations");
            size += this.anns.getSize() + 8;
        }
        if (this.ianns != null) {
            this.cw.newUTF8("RuntimeInvisibleAnnotations");
            size += this.ianns.getSize() + 8;
        }
        if (this.tanns != null) {
            this.cw.newUTF8("RuntimeVisibleTypeAnnotations");
            size += this.tanns.getSize() + 8;
        }
        if (this.itanns != null) {
            this.cw.newUTF8("RuntimeInvisibleTypeAnnotations");
            size += this.itanns.getSize() + 8;
        }
        Attribute attribute = this.attrs;
        return attribute != null ? size + attribute.getSize(this.cw, null, 0, -1, -1) : size;
    }

    void put(ByteVector byteVector) {
        int i = this.access;
        byteVector.putShort(i & (~(((i & 262144) / 64) | Opcodes.ASM6))).putShort(this.name).putShort(this.desc);
        int count = this.value != 0 ? 1 : 0;
        if ((this.access & 4096) != 0 && ((this.cw.version & 65535) < 49 || (this.access & 262144) != 0)) {
            count++;
        }
        if ((this.access & 131072) != 0) {
            count++;
        }
        if (this.signature != 0) {
            count++;
        }
        if (this.anns != null) {
            count++;
        }
        if (this.ianns != null) {
            count++;
        }
        if (this.tanns != null) {
            count++;
        }
        if (this.itanns != null) {
            count++;
        }
        Attribute attribute = this.attrs;
        if (attribute != null) {
            count += attribute.getCount();
        }
        byteVector.putShort(count);
        if (this.value != 0) {
            byteVector.putShort(this.cw.newUTF8("ConstantValue"));
            byteVector.putInt(2).putShort(this.value);
        }
        if ((this.access & 4096) != 0 && ((this.cw.version & 65535) < 49 || (this.access & 262144) != 0)) {
            byteVector.putShort(this.cw.newUTF8(AttSynthetic.ATTRIBUTE_NAME)).putInt(0);
        }
        if ((this.access & 131072) != 0) {
            byteVector.putShort(this.cw.newUTF8("Deprecated")).putInt(0);
        }
        if (this.signature != 0) {
            byteVector.putShort(this.cw.newUTF8("Signature"));
            byteVector.putInt(2).putShort(this.signature);
        }
        if (this.anns != null) {
            byteVector.putShort(this.cw.newUTF8("RuntimeVisibleAnnotations"));
            this.anns.put(byteVector);
        }
        if (this.ianns != null) {
            byteVector.putShort(this.cw.newUTF8("RuntimeInvisibleAnnotations"));
            this.ianns.put(byteVector);
        }
        if (this.tanns != null) {
            byteVector.putShort(this.cw.newUTF8("RuntimeVisibleTypeAnnotations"));
            this.tanns.put(byteVector);
        }
        if (this.itanns != null) {
            byteVector.putShort(this.cw.newUTF8("RuntimeInvisibleTypeAnnotations"));
            this.itanns.put(byteVector);
        }
        Attribute attribute2 = this.attrs;
        if (attribute2 != null) {
            attribute2.put(this.cw, null, 0, -1, -1, byteVector);
        }
    }
}
