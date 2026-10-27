package net.bytebuddy.implementation.bytecode.constant;

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public class TextConstant implements StackManipulation {
    private final String text;

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public boolean isValid() {
        return true;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof TextConstant;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TextConstant)) {
            return false;
        }
        TextConstant textConstant = (TextConstant) obj;
        if (!textConstant.canEqual(this)) {
            return false;
        }
        String str = this.text;
        String str2 = textConstant.text;
        return str != null ? str.equals(str2) : str2 == null;
    }

    public int hashCode() {
        String str = this.text;
        return 59 + (str == null ? 43 : str.hashCode());
    }

    public TextConstant(String str) {
        this.text = str;
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitLdcInsn(this.text);
        return StackSize.SINGLE.toIncreasingSize();
    }
}
