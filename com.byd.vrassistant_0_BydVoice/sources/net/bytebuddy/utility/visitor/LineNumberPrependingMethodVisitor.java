package net.bytebuddy.utility.visitor;

import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

/* JADX INFO: loaded from: classes3.dex */
public class LineNumberPrependingMethodVisitor extends ExceptionTableSensitiveMethodVisitor {
    private boolean prependLineNumber;
    private final Label startOfMethod;

    public LineNumberPrependingMethodVisitor(MethodVisitor methodVisitor) {
        super(Opcodes.ASM6, methodVisitor);
        this.startOfMethod = new Label();
        this.prependLineNumber = true;
    }

    @Override // net.bytebuddy.utility.visitor.ExceptionTableSensitiveMethodVisitor
    protected void onAfterExceptionTable() {
        super.visitLabel(this.startOfMethod);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLineNumber(int i, Label label) {
        if (this.prependLineNumber) {
            label = this.startOfMethod;
            this.prependLineNumber = false;
        }
        super.visitLineNumber(i, label);
    }
}
