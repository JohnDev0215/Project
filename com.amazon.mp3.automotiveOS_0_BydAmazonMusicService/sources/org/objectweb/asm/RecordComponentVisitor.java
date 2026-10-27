package org.objectweb.asm;

/* JADX INFO: loaded from: classes6.dex */
public abstract class RecordComponentVisitor {
    protected final int api;
    RecordComponentVisitor delegate;

    public abstract AnnotationVisitor visitAnnotation(String str, boolean z);

    public abstract void visitAttribute(Attribute attribute);

    public abstract void visitEnd();

    public abstract AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z);

    public RecordComponentVisitor(int i) {
        this(i, null);
    }

    public RecordComponentVisitor(int i, RecordComponentVisitor recordComponentVisitor) {
        if (i != 589824 && i != 524288 && i != 458752 && i != 393216 && i != 327680 && i != 262144 && i != 17432576) {
            throw new IllegalArgumentException("Unsupported api " + i);
        }
        if (i == 17432576) {
            Constants.checkAsmExperimental(this);
        }
        this.api = i;
        this.delegate = recordComponentVisitor;
    }
}
