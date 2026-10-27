package java9.util;

import java9.util.concurrent.CountedCompleter;
import java9.util.concurrent.ForkJoinPool;
import java9.util.function.BinaryOperator;
import java9.util.function.DoubleBinaryOperator;
import java9.util.function.IntBinaryOperator;
import java9.util.function.LongBinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
class ArrayPrefixHelpers {
    static final int CUMULATE = 1;
    static final int FINISHED = 4;
    static final int MIN_PARTITION = 16;
    static final int SUMMED = 2;

    private ArrayPrefixHelpers() {
    }

    static final class CumulateTask<T> extends CountedCompleter<Void> {
        final T[] array;
        final int fence;
        final BinaryOperator<T> function;
        final int hi;
        T in;
        CumulateTask<T> left;
        final int lo;
        final int origin;
        T out;
        CumulateTask<T> right;
        final int threshold;

        public CumulateTask(CumulateTask<T> cumulateTask, BinaryOperator<T> binaryOperator, T[] tArr, int i, int i2) {
            super(cumulateTask);
            this.function = binaryOperator;
            this.array = tArr;
            this.origin = i;
            this.lo = i;
            this.fence = i2;
            this.hi = i2;
            int commonPoolParallelism = (i2 - i) / (ForkJoinPool.getCommonPoolParallelism() << 3);
            this.threshold = commonPoolParallelism <= 16 ? 16 : commonPoolParallelism;
        }

        CumulateTask(CumulateTask<T> cumulateTask, BinaryOperator<T> binaryOperator, T[] tArr, int i, int i2, int i3, int i4, int i5) {
            super(cumulateTask);
            this.function = binaryOperator;
            this.array = tArr;
            this.origin = i;
            this.fence = i2;
            this.threshold = i3;
            this.lo = i4;
            this.hi = i5;
        }

        /* JADX WARN: Removed duplicated region for block: B:112:0x0156 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:119:0x0086 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x009a  */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // java9.util.concurrent.CountedCompleter
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void compute() {
            /*
                Method dump skipped, instruction units count: 345
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: java9.util.ArrayPrefixHelpers.CumulateTask.compute():void");
        }
    }

    static final class LongCumulateTask extends CountedCompleter<Void> {
        final long[] array;
        final int fence;
        final LongBinaryOperator function;
        final int hi;
        long in;
        LongCumulateTask left;
        final int lo;
        final int origin;
        long out;
        LongCumulateTask right;
        final int threshold;

        public LongCumulateTask(LongCumulateTask longCumulateTask, LongBinaryOperator longBinaryOperator, long[] jArr, int i, int i2) {
            super(longCumulateTask);
            this.function = longBinaryOperator;
            this.array = jArr;
            this.origin = i;
            this.lo = i;
            this.fence = i2;
            this.hi = i2;
            int commonPoolParallelism = (i2 - i) / (ForkJoinPool.getCommonPoolParallelism() << 3);
            this.threshold = commonPoolParallelism <= 16 ? 16 : commonPoolParallelism;
        }

        LongCumulateTask(LongCumulateTask longCumulateTask, LongBinaryOperator longBinaryOperator, long[] jArr, int i, int i2, int i3, int i4, int i5) {
            super(longCumulateTask);
            this.function = longBinaryOperator;
            this.array = jArr;
            this.origin = i;
            this.fence = i2;
            this.threshold = i3;
            this.lo = i4;
            this.hi = i5;
        }

        /* JADX WARN: Removed duplicated region for block: B:111:0x0156 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:118:0x0086 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x009a  */
        @Override // java9.util.concurrent.CountedCompleter
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void compute() {
            /*
                Method dump skipped, instruction units count: 345
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: java9.util.ArrayPrefixHelpers.LongCumulateTask.compute():void");
        }
    }

    static final class DoubleCumulateTask extends CountedCompleter<Void> {
        final double[] array;
        final int fence;
        final DoubleBinaryOperator function;
        final int hi;
        double in;
        DoubleCumulateTask left;
        final int lo;
        final int origin;
        double out;
        DoubleCumulateTask right;
        final int threshold;

        public DoubleCumulateTask(DoubleCumulateTask doubleCumulateTask, DoubleBinaryOperator doubleBinaryOperator, double[] dArr, int i, int i2) {
            super(doubleCumulateTask);
            this.function = doubleBinaryOperator;
            this.array = dArr;
            this.origin = i;
            this.lo = i;
            this.fence = i2;
            this.hi = i2;
            int commonPoolParallelism = (i2 - i) / (ForkJoinPool.getCommonPoolParallelism() << 3);
            this.threshold = commonPoolParallelism <= 16 ? 16 : commonPoolParallelism;
        }

        DoubleCumulateTask(DoubleCumulateTask doubleCumulateTask, DoubleBinaryOperator doubleBinaryOperator, double[] dArr, int i, int i2, int i3, int i4, int i5) {
            super(doubleCumulateTask);
            this.function = doubleBinaryOperator;
            this.array = dArr;
            this.origin = i;
            this.fence = i2;
            this.threshold = i3;
            this.lo = i4;
            this.hi = i5;
        }

        /* JADX WARN: Removed duplicated region for block: B:111:0x0156 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:118:0x0086 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x009a  */
        @Override // java9.util.concurrent.CountedCompleter
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void compute() {
            /*
                Method dump skipped, instruction units count: 345
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: java9.util.ArrayPrefixHelpers.DoubleCumulateTask.compute():void");
        }
    }

    static final class IntCumulateTask extends CountedCompleter<Void> {
        final int[] array;
        final int fence;
        final IntBinaryOperator function;
        final int hi;
        int in;
        IntCumulateTask left;
        final int lo;
        final int origin;
        int out;
        IntCumulateTask right;
        final int threshold;

        public IntCumulateTask(IntCumulateTask intCumulateTask, IntBinaryOperator intBinaryOperator, int[] iArr, int i, int i2) {
            super(intCumulateTask);
            this.function = intBinaryOperator;
            this.array = iArr;
            this.origin = i;
            this.lo = i;
            this.fence = i2;
            this.hi = i2;
            int commonPoolParallelism = (i2 - i) / (ForkJoinPool.getCommonPoolParallelism() << 3);
            this.threshold = commonPoolParallelism <= 16 ? 16 : commonPoolParallelism;
        }

        IntCumulateTask(IntCumulateTask intCumulateTask, IntBinaryOperator intBinaryOperator, int[] iArr, int i, int i2, int i3, int i4, int i5) {
            super(intCumulateTask);
            this.function = intBinaryOperator;
            this.array = iArr;
            this.origin = i;
            this.fence = i2;
            this.threshold = i3;
            this.lo = i4;
            this.hi = i5;
        }

        /* JADX WARN: Removed duplicated region for block: B:112:0x0156 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:119:0x0086 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x009a  */
        @Override // java9.util.concurrent.CountedCompleter
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void compute() {
            /*
                Method dump skipped, instruction units count: 345
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: java9.util.ArrayPrefixHelpers.IntCumulateTask.compute():void");
        }
    }
}
