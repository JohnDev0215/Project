package net.bytebuddy.jar.asm.commons;

import java.util.List;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.Attribute;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.ModuleVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.TypePath;

/* JADX INFO: loaded from: classes3.dex */
public class ClassRemapper extends ClassVisitor {
    protected String className;
    protected final Remapper remapper;

    public ClassRemapper(ClassVisitor classVisitor, Remapper remapper) {
        this(Opcodes.ASM6, classVisitor, remapper);
    }

    protected ClassRemapper(int i, ClassVisitor classVisitor, Remapper remapper) {
        super(i, classVisitor);
        this.remapper = remapper;
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
        this.className = str;
        super.visit(i, i2, this.remapper.mapType(str), this.remapper.mapSignature(str2, false), this.remapper.mapType(str3), strArr == null ? null : this.remapper.mapTypes(strArr));
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public ModuleVisitor visitModule(String str, int i, String str2) {
        ModuleVisitor moduleVisitorVisitModule = super.visitModule(this.remapper.mapModuleName(str), i, str2);
        if (moduleVisitorVisitModule == null) {
            return null;
        }
        return createModuleRemapper(moduleVisitorVisitModule);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        AnnotationVisitor annotationVisitorVisitAnnotation = super.visitAnnotation(this.remapper.mapDesc(str), z);
        if (annotationVisitorVisitAnnotation == null) {
            return null;
        }
        return createAnnotationRemapper(annotationVisitorVisitAnnotation);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        AnnotationVisitor annotationVisitorVisitTypeAnnotation = super.visitTypeAnnotation(i, typePath, this.remapper.mapDesc(str), z);
        if (annotationVisitorVisitTypeAnnotation == null) {
            return null;
        }
        return createAnnotationRemapper(annotationVisitorVisitTypeAnnotation);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public void visitAttribute(Attribute attribute) {
        if (attribute instanceof ModuleHashesAttribute) {
            List<String> list = new ModuleHashesAttribute().modules;
            for (int i = 0; i < list.size(); i++) {
                list.set(i, this.remapper.mapModuleName(list.get(i)));
            }
        }
        super.visitAttribute(attribute);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
        FieldVisitor fieldVisitorVisitField = super.visitField(i, this.remapper.mapFieldName(this.className, str, str2), this.remapper.mapDesc(str2), this.remapper.mapSignature(str3, true), this.remapper.mapValue(obj));
        if (fieldVisitorVisitField == null) {
            return null;
        }
        return createFieldRemapper(fieldVisitorVisitField);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
        MethodVisitor methodVisitorVisitMethod = super.visitMethod(i, this.remapper.mapMethodName(this.className, str, str2), this.remapper.mapMethodDesc(str2), this.remapper.mapSignature(str3, false), strArr == null ? null : this.remapper.mapTypes(strArr));
        if (methodVisitorVisitMethod == null) {
            return null;
        }
        return createMethodRemapper(methodVisitorVisitMethod);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public void visitInnerClass(String str, String str2, String str3, int i) {
        super.visitInnerClass(this.remapper.mapType(str), str2 == null ? null : this.remapper.mapType(str2), str3, i);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public void visitOuterClass(String str, String str2, String str3) {
        super.visitOuterClass(this.remapper.mapType(str), str2 == null ? null : this.remapper.mapMethodName(str, str2, str3), str3 != null ? this.remapper.mapMethodDesc(str3) : null);
    }

    protected FieldVisitor createFieldRemapper(FieldVisitor fieldVisitor) {
        return new FieldRemapper(fieldVisitor, this.remapper);
    }

    protected MethodVisitor createMethodRemapper(MethodVisitor methodVisitor) {
        return new MethodRemapper(methodVisitor, this.remapper);
    }

    protected AnnotationVisitor createAnnotationRemapper(AnnotationVisitor annotationVisitor) {
        return new AnnotationRemapper(annotationVisitor, this.remapper);
    }

    protected ModuleVisitor createModuleRemapper(ModuleVisitor moduleVisitor) {
        return new ModuleRemapper(moduleVisitor, this.remapper);
    }
}
