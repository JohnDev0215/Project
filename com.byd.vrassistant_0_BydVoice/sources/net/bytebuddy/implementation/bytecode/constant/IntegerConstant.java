package net.bytebuddy.implementation.bytecode.constant;

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public enum IntegerConstant implements StackManipulation {
    MINUS_ONE(2),
    ZERO(3),
    ONE(4),
    TWO(5),
    THREE(6),
    FOUR(7),
    FIVE(8);

    private static final StackManipulation.Size SIZE = StackSize.SINGLE.toIncreasingSize();
    private final int opcode;

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public boolean isValid() {
        return true;
    }

    IntegerConstant(int i) {
        this.opcode = i;
    }

    public static StackManipulation forValue(boolean z) {
        return z ? ONE : ZERO;
    }

    public static StackManipulation forValue(int i) {
        switch (i) {
            case -1:
                return MINUS_ONE;
            case 0:
                return ZERO;
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return THREE;
            case 4:
                return FOUR;
            case 5:
                return FIVE;
            default:
                if (i >= -128 && i <= 127) {
                    return new SingleBytePush((byte) i);
                }
                if (i >= -32768 && i <= 32767) {
                    return new TwoBytePush((short) i);
                }
                return new ConstantPool(i);
        }
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitInsn(this.opcode);
        return SIZE;
    }

    protected static class SingleBytePush implements StackManipulation {
        private final byte value;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return true;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof SingleBytePush;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof SingleBytePush)) {
                return false;
            }
            SingleBytePush singleBytePush = (SingleBytePush) obj;
            return singleBytePush.canEqual(this) && this.value == singleBytePush.value;
        }

        public int hashCode() {
            return 59 + this.value;
        }

        protected SingleBytePush(byte b) {
            this.value = b;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitIntInsn(16, this.value);
            return IntegerConstant.SIZE;
        }
    }

    protected static class TwoBytePush implements StackManipulation {
        private final short value;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return true;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof TwoBytePush;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof TwoBytePush)) {
                return false;
            }
            TwoBytePush twoBytePush = (TwoBytePush) obj;
            return twoBytePush.canEqual(this) && this.value == twoBytePush.value;
        }

        public int hashCode() {
            return 59 + this.value;
        }

        protected TwoBytePush(short s) {
            this.value = s;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitIntInsn(17, this.value);
            return IntegerConstant.SIZE;
        }
    }

    protected static class ConstantPool implements StackManipulation {
        private final int value;

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
            return 59 + this.value;
        }

        protected ConstantPool(int i) {
            this.value = i;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitLdcInsn(Integer.valueOf(this.value));
            return IntegerConstant.SIZE;
        }
    }
}
