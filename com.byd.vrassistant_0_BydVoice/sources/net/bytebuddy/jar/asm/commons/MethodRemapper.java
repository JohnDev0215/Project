package net.bytebuddy.jar.asm.commons;

import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.TypePath;

/* JADX INFO: loaded from: classes3.dex */
public class MethodRemapper extends MethodVisitor {
    protected final Remapper remapper;

    public MethodRemapper(MethodVisitor methodVisitor, Remapper remapper) {
        this(Opcodes.ASM6, methodVisitor, remapper);
    }

    protected MethodRemapper(int i, MethodVisitor methodVisitor, Remapper remapper) {
        super(i, methodVisitor);
        this.remapper = remapper;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public AnnotationVisitor visitAnnotationDefault() {
        AnnotationVisitor annotationVisitorVisitAnnotationDefault = super.visitAnnotationDefault();
        return annotationVisitorVisitAnnotationDefault == null ? annotationVisitorVisitAnnotationDefault : new AnnotationRemapper(annotationVisitorVisitAnnotationDefault, this.remapper);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        AnnotationVisitor annotationVisitorVisitAnnotation = super.visitAnnotation(this.remapper.mapDesc(str), z);
        return annotationVisitorVisitAnnotation == null ? annotationVisitorVisitAnnotation : new AnnotationRemapper(annotationVisitorVisitAnnotation, this.remapper);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        AnnotationVisitor annotationVisitorVisitTypeAnnotation = super.visitTypeAnnotation(i, typePath, this.remapper.mapDesc(str), z);
        return annotationVisitorVisitTypeAnnotation == null ? annotationVisitorVisitTypeAnnotation : new AnnotationRemapper(annotationVisitorVisitTypeAnnotation, this.remapper);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z) {
        AnnotationVisitor annotationVisitorVisitParameterAnnotation = super.visitParameterAnnotation(i, this.remapper.mapDesc(str), z);
        return annotationVisitorVisitParameterAnnotation == null ? annotationVisitorVisitParameterAnnotation : new AnnotationRemapper(annotationVisitorVisitParameterAnnotation, this.remapper);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        super.visitFrame(i, i2, remapEntries(i2, objArr), i3, remapEntries(i3, objArr2));
    }

    private Object[] remapEntries(int i, Object[] objArr) {
        if (objArr != null) {
            int i2 = 0;
            while (i2 < i) {
                if (objArr[i2] instanceof String) {
                    Object[] objArr2 = new Object[i];
                    if (i2 > 0) {
                        System.arraycopy(objArr, 0, objArr2, 0, i2);
                    }
                    while (true) {
                        Object objMapType = objArr[i2];
                        int i3 = i2 + 1;
                        if (objMapType instanceof String) {
                            objMapType = this.remapper.mapType((String) objMapType);
                        }
                        objArr2[i2] = objMapType;
                        if (i3 >= i) {
                            return objArr2;
                        }
                        i2 = i3;
                    }
                } else {
                    i2++;
                }
            }
        }
        return objArr;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitFieldInsn(int i, String str, String str2, String str3) {
        super.visitFieldInsn(i, this.remapper.mapType(str), this.remapper.mapFieldName(str, str2, str3), this.remapper.mapDesc(str3));
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    @Deprecated
    public void visitMethodInsn(int i, String str, String str2, String str3) {
        if (this.api >= 327680) {
            super.visitMethodInsn(i, str, str2, str3);
        } else {
            doVisitMethodInsn(i, str, str2, str3, i == 185);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
        if (this.api < 327680) {
            super.visitMethodInsn(i, str, str2, str3, z);
        } else {
            doVisitMethodInsn(i, str, str2, str3, z);
        }
    }

    private void doVisitMethodInsn(int i, String str, String str2, String str3, boolean z) {
        if (this.mv != null) {
            this.mv.visitMethodInsn(i, this.remapper.mapType(str), this.remapper.mapMethodName(str, str2, str3), this.remapper.mapMethodDesc(str3), z);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
        for (int i = 0; i < objArr.length; i++) {
            objArr[i] = this.remapper.mapValue(objArr[i]);
        }
        super.visitInvokeDynamicInsn(this.remapper.mapInvokeDynamicMethodName(str, str2), this.remapper.mapMethodDesc(str2), (Handle) this.remapper.mapValue(handle), objArr);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTypeInsn(int i, String str) {
        super.visitTypeInsn(i, this.remapper.mapType(str));
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLdcInsn(Object obj) {
        super.visitLdcInsn(this.remapper.mapValue(obj));
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitMultiANewArrayInsn(String str, int i) {
        super.visitMultiANewArrayInsn(this.remapper.mapDesc(str), i);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public AnnotationVisitor visitInsnAnnotation(int i, TypePath typePath, String str, boolean z) {
        AnnotationVisitor annotationVisitorVisitInsnAnnotation = super.visitInsnAnnotation(i, typePath, this.remapper.mapDesc(str), z);
        return annotationVisitorVisitInsnAnnotation == null ? annotationVisitorVisitInsnAnnotation : new AnnotationRemapper(annotationVisitorVisitInsnAnnotation, this.remapper);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTryCatchBlock(Label label, Label label2, Label label3, String str) {
        super.visitTryCatchBlock(label, label2, label3, str == null ? null : this.remapper.mapType(str));
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public AnnotationVisitor visitTryCatchAnnotation(int i, TypePath typePath, String str, boolean z) {
        AnnotationVisitor annotationVisitorVisitTryCatchAnnotation = super.visitTryCatchAnnotation(i, typePath, this.remapper.mapDesc(str), z);
        return annotationVisitorVisitTryCatchAnnotation == null ? annotationVisitorVisitTryCatchAnnotation : new AnnotationRemapper(annotationVisitorVisitTryCatchAnnotation, this.remapper);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLocalVariable(String str, String str2, String str3, Label label, Label label2, int i) {
        super.visitLocalVariable(str, this.remapper.mapDesc(str2), this.remapper.mapSignature(str3, true), label, label2, i);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public AnnotationVisitor visitLocalVariableAnnotation(int i, TypePath typePath, Label[] labelArr, Label[] labelArr2, int[] iArr, String str, boolean z) {
        AnnotationVisitor annotationVisitorVisitLocalVariableAnnotation = super.visitLocalVariableAnnotation(i, typePath, labelArr, labelArr2, iArr, this.remapper.mapDesc(str), z);
        return annotationVisitorVisitLocalVariableAnnotation == null ? annotationVisitorVisitLocalVariableAnnotation : new AnnotationRemapper(annotationVisitorVisitLocalVariableAnnotation, this.remapper);
    }
}
