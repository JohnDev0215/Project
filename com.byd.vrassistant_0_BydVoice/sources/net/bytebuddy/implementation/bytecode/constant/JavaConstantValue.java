package net.bytebuddy.implementation.bytecode.constant;

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.utility.JavaConstant;

/* JADX INFO: loaded from: classes3.dex */
public class JavaConstantValue implements StackManipulation {
    private final JavaConstant javaConstant;

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public boolean isValid() {
        return true;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof JavaConstantValue;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof JavaConstantValue)) {
            return false;
        }
        JavaConstantValue javaConstantValue = (JavaConstantValue) obj;
        if (!javaConstantValue.canEqual(this)) {
            return false;
        }
        JavaConstant javaConstant = this.javaConstant;
        JavaConstant javaConstant2 = javaConstantValue.javaConstant;
        return javaConstant != null ? javaConstant.equals(javaConstant2) : javaConstant2 == null;
    }

    public int hashCode() {
        JavaConstant javaConstant = this.javaConstant;
        return 59 + (javaConstant == null ? 43 : javaConstant.hashCode());
    }

    public JavaConstantValue(JavaConstant javaConstant) {
        this.javaConstant = javaConstant;
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitLdcInsn(this.javaConstant.asConstantPoolValue());
        return StackSize.SINGLE.toIncreasingSize();
    }
}
