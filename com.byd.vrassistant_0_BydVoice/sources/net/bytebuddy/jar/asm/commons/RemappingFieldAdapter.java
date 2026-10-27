package net.bytebuddy.jar.asm.commons;

import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.TypePath;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class RemappingFieldAdapter extends FieldVisitor {
    private final Remapper remapper;

    public RemappingFieldAdapter(FieldVisitor fieldVisitor, Remapper remapper) {
        this(Opcodes.ASM6, fieldVisitor, remapper);
    }

    protected RemappingFieldAdapter(int i, FieldVisitor fieldVisitor, Remapper remapper) {
        super(i, fieldVisitor);
        this.remapper = remapper;
    }

    @Override // net.bytebuddy.jar.asm.FieldVisitor
    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        AnnotationVisitor annotationVisitorVisitAnnotation = this.fv.visitAnnotation(this.remapper.mapDesc(str), z);
        if (annotationVisitorVisitAnnotation == null) {
            return null;
        }
        return new RemappingAnnotationAdapter(annotationVisitorVisitAnnotation, this.remapper);
    }

    @Override // net.bytebuddy.jar.asm.FieldVisitor
    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        AnnotationVisitor annotationVisitorVisitTypeAnnotation = super.visitTypeAnnotation(i, typePath, this.remapper.mapDesc(str), z);
        if (annotationVisitorVisitTypeAnnotation == null) {
            return null;
        }
        return new RemappingAnnotationAdapter(annotationVisitorVisitTypeAnnotation, this.remapper);
    }
}
