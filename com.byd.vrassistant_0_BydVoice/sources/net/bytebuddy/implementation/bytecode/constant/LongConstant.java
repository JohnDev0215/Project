package net.bytebuddy.implementation.bytecode.constant;

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public enum LongConstant implements StackManipulation {
    ZERO(9),
    ONE(10);

    private static final StackManipulation.Size SIZE = StackSize.DOUBLE.toIncreasingSize();
    private final int opcode;

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public boolean isValid() {
        return true;
    }

    LongConstant(int i) {
        this.opcode = i;
    }

    public static StackManipulation forValue(long j) {
        if (j == 0) {
            return ZERO;
        }
        if (j == 1) {
            return ONE;
        }
        return new ConstantPool(j);
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitInsn(this.opcode);
        return SIZE;
    }

    protected static class ConstantPool implements StackManipulation {
        private final long value;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return true;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof ConstantPool;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ConstantPool)) {
                return false;
            }
            ConstantPool constantPool = (ConstantPool) obj;
            return constantPool.canEqual(this) && this.value == constantPool.value;
        }

        public int hashCode() {
            long j = this.value;
            return 59 + ((int) (j ^ (j >>> 32)));
        }

        protected ConstantPool(long j) {
            this.value = j;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitLdcInsn(Long.valueOf(this.value));
            return LongConstant.SIZE;
        }
    }
}
