package net.bytebuddy.implementation.bytecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public interface ByteCodeAppender {
    Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription);

    public static class Size {
        private final int localVariableSize;
        private final int operandStackSize;

        protected boolean canEqual(Object obj) {
            return obj instanceof Size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Size)) {
                return false;
            }
            Size size = (Size) obj;
            return size.canEqual(this) && getOperandStackSize() == size.getOperandStackSize() && getLocalVariableSize() == size.getLocalVariableSize();
        }

        public int hashCode() {
            return ((getOperandStackSize() + 59) * 59) + getLocalVariableSize();
        }

        public Size(int i, int i2) {
            this.operandStackSize = i;
            this.localVariableSize = i2;
        }

        public int getOperandStackSize() {
            return this.operandStackSize;
        }

        public int getLocalVariableSize() {
            return this.localVariableSize;
        }

        public Size merge(Size size) {
            return new Size(Math.max(this.operandStackSize, size.operandStackSize), Math.max(this.localVariableSize, size.localVariableSize));
        }
    }

    public static class Compound implements ByteCodeAppender {
        private final List<ByteCodeAppender> byteCodeAppenders;

        protected boolean canEqual(Object obj) {
            return obj instanceof Compound;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Compound)) {
                return false;
            }
            Compound compound = (Compound) obj;
            if (!compound.canEqual(this)) {
                return false;
            }
            List<ByteCodeAppender> list = this.byteCodeAppenders;
            List<ByteCodeAppender> list2 = compound.byteCodeAppenders;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            List<ByteCodeAppender> list = this.byteCodeAppenders;
            return 59 + (list == null ? 43 : list.hashCode());
        }

        public Compound(ByteCodeAppender... byteCodeAppenderArr) {
            this((List<? extends ByteCodeAppender>) Arrays.asList(byteCodeAppenderArr));
        }

        public Compound(List<? extends ByteCodeAppender> list) {
            this.byteCodeAppenders = new ArrayList();
            for (ByteCodeAppender byteCodeAppender : list) {
                if (byteCodeAppender instanceof Compound) {
                    this.byteCodeAppenders.addAll(((Compound) byteCodeAppender).byteCodeAppenders);
                } else {
                    this.byteCodeAppenders.add(byteCodeAppender);
                }
            }
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            Size size = new Size(0, methodDescription.getStackSize());
            Iterator<ByteCodeAppender> it = this.byteCodeAppenders.iterator();
            while (it.hasNext()) {
                size = size.merge(it.next().apply(methodVisitor, context, methodDescription));
            }
            return size;
        }
    }

    public static class Simple implements ByteCodeAppender {
        private final StackManipulation stackManipulation;

        protected boolean canEqual(Object obj) {
            return obj instanceof Simple;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Simple)) {
                return false;
            }
            Simple simple = (Simple) obj;
            if (!simple.canEqual(this)) {
                return false;
            }
            StackManipulation stackManipulation = this.stackManipulation;
            StackManipulation stackManipulation2 = simple.stackManipulation;
            return stackManipulation != null ? stackManipulation.equals(stackManipulation2) : stackManipulation2 == null;
        }

        public int hashCode() {
            StackManipulation stackManipulation = this.stackManipulation;
            return 59 + (stackManipulation == null ? 43 : stackManipulation.hashCode());
        }

        public Simple(StackManipulation... stackManipulationArr) {
            this((List<? extends StackManipulation>) Arrays.asList(stackManipulationArr));
        }

        public Simple(List<? extends StackManipulation> list) {
            this.stackManipulation = new StackManipulation.Compound(list);
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            return new Size(this.stackManipulation.apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
        }
    }
}
