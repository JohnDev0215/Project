package net.bytebuddy.implementation.bytecode.member;

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.utility.JavaConstant;

/* JADX INFO: loaded from: classes3.dex */
public class HandleInvocation implements StackManipulation {
    private static final String INVOKE_EXACT = "invokeExact";
    private static final String METHOD_HANDLE_NAME = "java/lang/invoke/MethodHandle";
    private final JavaConstant.MethodType methodType;

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public boolean isValid() {
        return true;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof HandleInvocation;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HandleInvocation)) {
            return false;
        }
        HandleInvocation handleInvocation = (HandleInvocation) obj;
        if (!handleInvocation.canEqual(this)) {
            return false;
        }
        JavaConstant.MethodType methodType = this.methodType;
        JavaConstant.MethodType methodType2 = handleInvocation.methodType;
        return methodType != null ? methodType.equals(methodType2) : methodType2 == null;
    }

    public int hashCode() {
        JavaConstant.MethodType methodType = this.methodType;
        return 59 + (methodType == null ? 43 : methodType.hashCode());
    }

    public HandleInvocation(JavaConstant.MethodType methodType) {
        this.methodType = methodType;
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitMethodInsn(182, METHOD_HANDLE_NAME, INVOKE_EXACT, this.methodType.getDescriptor(), false);
        int size = this.methodType.getReturnType().getStackSize().getSize() - this.methodType.getParameterTypes().getStackSize();
        return new StackManipulation.Size(size, Math.max(size, 0));
    }
}
