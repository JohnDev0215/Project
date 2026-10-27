package net.bytebuddy.jar.asm.commons;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

/* JADX INFO: loaded from: classes3.dex */
public class StaticInitMerger extends ClassVisitor {
    private MethodVisitor clinit;
    private int counter;
    private String name;
    private final String prefix;

    public StaticInitMerger(String str, ClassVisitor classVisitor) {
        this(Opcodes.ASM6, str, classVisitor);
    }

    protected StaticInitMerger(int i, String str, ClassVisitor classVisitor) {
        super(i, classVisitor);
        this.prefix = str;
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
        this.cv.visit(i, i2, str, str2, str3, strArr);
        this.name = str;
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
        if (MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME.equals(str)) {
            StringBuilder sbAppend = new StringBuilder().append(this.prefix);
            int i2 = this.counter;
            this.counter = i2 + 1;
            String string = sbAppend.append(i2).toString();
            MethodVisitor methodVisitorVisitMethod = this.cv.visitMethod(10, string, str2, str3, strArr);
            if (this.clinit == null) {
                this.clinit = this.cv.visitMethod(10, str, str2, null, null);
            }
            this.clinit.visitMethodInsn(184, this.name, string, str2, false);
            return methodVisitorVisitMethod;
        }
        return this.cv.visitMethod(i, str, str2, str3, strArr);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public void visitEnd() {
        MethodVisitor methodVisitor = this.clinit;
        if (methodVisitor != null) {
            methodVisitor.visitInsn(177);
            this.clinit.visitMaxs(0, 0);
        }
        this.cv.visitEnd();
    }
}
