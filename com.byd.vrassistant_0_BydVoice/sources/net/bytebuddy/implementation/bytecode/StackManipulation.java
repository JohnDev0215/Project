package net.bytebuddy.implementation.bytecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public interface StackManipulation {
    Size apply(MethodVisitor methodVisitor, Implementation.Context context);

    boolean isValid();

    public enum Illegal implements StackManipulation {
        INSTANCE;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return false;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            throw new IllegalStateException("An illegal stack manipulation must not be applied");
        }
    }

    public enum Trivial implements StackManipulation {
        INSTANCE;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return true;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return StackSize.ZERO.toIncreasingSize();
        }
    }

    public static class Size {
        private final int maximalSize;
        private final int sizeImpact;

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
            return size.canEqual(this) && getSizeImpact() == size.getSizeImpact() && getMaximalSize() == size.getMaximalSize();
        }

        public int hashCode() {
            return ((getSizeImpact() + 59) * 59) + getMaximalSize();
        }

        public Size(int i, int i2) {
            this.sizeImpact = i;
            this.maximalSize = i2;
        }

        public int getSizeImpact() {
            return this.sizeImpact;
        }

        public int getMaximalSize() {
            return this.maximalSize;
        }

        public Size aggregate(Size size) {
            return aggregate(size.sizeImpact, size.maximalSize);
        }

        private Size aggregate(int i, int i2) {
            int i3 = this.sizeImpact;
            return new Size(i + i3, Math.max(this.maximalSize, i3 + i2));
        }
    }

    public static class Compound implements StackManipulation {
        private final List<StackManipulation> stackManipulations;

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
            List<StackManipulation> list = this.stackManipulations;
            List<StackManipulation> list2 = compound.stackManipulations;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            List<StackManipulation> list = this.stackManipulations;
            return 59 + (list == null ? 43 : list.hashCode());
        }

        public Compound(StackManipulation... stackManipulationArr) {
            this((List<? extends StackManipulation>) Arrays.asList(stackManipulationArr));
        }

        public Compound(List<? extends StackManipulation> list) {
            this.stackManipulations = new ArrayList();
            for (StackManipulation stackManipulation : list) {
                if (stackManipulation instanceof Compound) {
                    this.stackManipulations.addAll(((Compound) stackManipulation).stackManipulations);
                } else if (!(stackManipulation instanceof Trivial)) {
                    this.stackManipulations.add(stackManipulation);
                }
            }
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            Iterator<StackManipulation> it = this.stackManipulations.iterator();
            while (it.hasNext()) {
                if (!it.next().isValid()) {
                    return false;
                }
            }
            return true;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            Size size = new Size(0, 0);
            Iterator<StackManipulation> it = this.stackManipulations.iterator();
            while (it.hasNext()) {
                size = size.aggregate(it.next().apply(methodVisitor, context));
            }
            return size;
        }
    }
}
