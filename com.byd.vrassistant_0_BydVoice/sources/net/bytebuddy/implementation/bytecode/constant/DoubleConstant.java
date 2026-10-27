package net.bytebuddy.implementation.bytecode.constant;

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public enum DoubleConstant implements StackManipulation {
    ZERO(14),
    ONE(15);

    private static final StackManipulation.Size SIZE = StackSize.DOUBLE.toIncreasingSize();
    private final int opcode;

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public boolean isValid() {
        return true;
    }

    DoubleConstant(int i) {
        this.opcode = i;
    }

    public static StackManipulation forValue(double d) {
        if (d == 0.0d) {
            return ZERO;
        }
        if (d == 1.0d) {
            return ONE;
        }
        return new ConstantPool(d);
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitInsn(this.opcode);
        return SIZE;
    }

    protected static class ConstantPool implements StackManipulation {
        private final double value;

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
            return constantPool.canEqual(this) && Double.compare(this.value, constantPool.value) == 0;
        }

        public int hashCode() {
            long jDoubleToLongBits = Double.doubleToLongBits(this.value);
            return 59 + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)));
        }

        protected ConstantPool(double d) {
            this.value = d;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitLdcInsn(Double.valueOf(this.value));
            return DoubleConstant.SIZE;
        }
    }
}
