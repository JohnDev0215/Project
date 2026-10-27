package net.bytebuddy.implementation.bytecode.constant;

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public enum FloatConstant implements StackManipulation {
    ZERO(11),
    ONE(12),
    TWO(13);

    private static final StackManipulation.Size SIZE = StackSize.SINGLE.toIncreasingSize();
    private final int opcode;

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public boolean isValid() {
        return true;
    }

    FloatConstant(int i) {
        this.opcode = i;
    }

    public static StackManipulation forValue(float f) {
        if (f == 0.0f) {
            return ZERO;
        }
        if (f == 1.0f) {
            return ONE;
        }
        if (f == 2.0f) {
            return TWO;
        }
        return new ConstantPool(f);
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitInsn(this.opcode);
        return SIZE;
    }

    protected static class ConstantPool implements StackManipulation {
        private final float value;

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
            return constantPool.canEqual(this) && Float.compare(this.value, constantPool.value) == 0;
        }

        public int hashCode() {
            return 59 + Float.floatToIntBits(this.value);
        }

        protected ConstantPool(float f) {
            this.value = f;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitLdcInsn(Float.valueOf(this.value));
            return FloatConstant.SIZE;
        }
    }
}
