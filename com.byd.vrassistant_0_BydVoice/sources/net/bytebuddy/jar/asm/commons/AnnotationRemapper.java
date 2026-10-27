package net.bytebuddy.jar.asm.commons;

import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.Opcodes;

/* JADX INFO: loaded from: classes3.dex */
public class AnnotationRemapper extends AnnotationVisitor {
    protected final Remapper remapper;

    public AnnotationRemapper(AnnotationVisitor annotationVisitor, Remapper remapper) {
        this(Opcodes.ASM6, annotationVisitor, remapper);
    }

    protected AnnotationRemapper(int i, AnnotationVisitor annotationVisitor, Remapper remapper) {
        super(i, annotationVisitor);
        this.remapper = remapper;
    }

    @Override // net.bytebuddy.jar.asm.AnnotationVisitor
    public void visit(String str, Object obj) {
        this.av.visit(str, this.remapper.mapValue(obj));
    }

    @Override // net.bytebuddy.jar.asm.AnnotationVisitor
    public void visitEnum(String str, String str2, String str3) {
        this.av.visitEnum(str, this.remapper.mapDesc(str2), str3);
    }

    @Override // net.bytebuddy.jar.asm.AnnotationVisitor
    public AnnotationVisitor visitAnnotation(String str, String str2) {
        AnnotationVisitor annotationVisitorVisitAnnotation = this.av.visitAnnotation(str, this.remapper.mapDesc(str2));
        if (annotationVisitorVisitAnnotation == null) {
            return null;
        }
        return annotationVisitorVisitAnnotation == this.av ? this : new AnnotationRemapper(annotationVisitorVisitAnnotation, this.remapper);
    }

    @Override // net.bytebuddy.jar.asm.AnnotationVisitor
    public AnnotationVisitor visitArray(String str) {
        AnnotationVisitor annotationVisitorVisitArray = this.av.visitArray(str);
        if (annotationVisitorVisitArray == null) {
            return null;
        }
        return annotationVisitorVisitArray == this.av ? this : new AnnotationRemapper(annotationVisitorVisitArray, this.remapper);
    }
}
