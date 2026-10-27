package java9.util.concurrent.atomic;

import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import java.lang.reflect.Method;
import java9.util.function.DoubleBinaryOperator;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
abstract class Striped64 extends Number {
    private static final long BASE;
    private static final long CELLSBUSY;
    private static final Method GET_PROBE_METHOD;
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    private static final Method SET_PROBE_METHOD;
    private static final Unsafe U;
    volatile transient long base;
    volatile transient Cell[] cells;
    volatile transient int cellsBusy;

    static final class Cell {
        private static final Unsafe U;
        private static final long VALUE;
        volatile long p0;
        volatile long p1;
        volatile long p2;
        volatile long p3;
        volatile long p4;
        volatile long p5;
        volatile long p6;
        volatile long q0;
        volatile long q1;
        volatile long q2;
        volatile long q3;
        volatile long q4;
        volatile long q5;
        volatile long q6;
        volatile long value;

        Cell(long j) {
            this.value = j;
        }

        final boolean cas(long j, long j2) {
            return U.compareAndSwapLong(this, VALUE, j, j2);
        }

        final void reset() {
            U.putLongVolatile(this, VALUE, 0L);
        }

        final void reset(long j) {
            U.putLongVolatile(this, VALUE, j);
        }

        final long getAndSet(long j) {
            return Striped64.getAndSetLong(this, VALUE, j);
        }

        static {
            Unsafe unsafe = UnsafeAccess.unsafe;
            U = unsafe;
            try {
                VALUE = unsafe.objectFieldOffset(Cell.class.getDeclaredField(MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE));
            } catch (Exception e) {
                throw new ExceptionInInitializerError(e);
            }
        }
    }

    static {
        Unsafe unsafe = UnsafeAccess.unsafe;
        U = unsafe;
        try {
            BASE = unsafe.objectFieldOffset(Striped64.class.getDeclaredField("base"));
            CELLSBUSY = unsafe.objectFieldOffset(Striped64.class.getDeclaredField("cellsBusy"));
            Class<?> cls = Class.forName("java9.util.concurrent.TLRandom");
            Method declaredMethod = cls.getDeclaredMethod("getThreadLocalRandomProbe", new Class[0]);
            declaredMethod.setAccessible(true);
            Method declaredMethod2 = cls.getDeclaredMethod("setThreadLocalRandomProbe", Integer.TYPE);
            declaredMethod2.setAccessible(true);
            GET_PROBE_METHOD = declaredMethod;
            SET_PROBE_METHOD = declaredMethod2;
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    Striped64() {
    }

    final boolean casBase(long j, long j2) {
        return U.compareAndSwapLong(this, BASE, j, j2);
    }

    final long getAndSetBase(long j) {
        return getAndSetLong(this, BASE, j);
    }

    final boolean casCellsBusy() {
        return U.compareAndSwapInt(this, CELLSBUSY, 0, 1);
    }

    static int getProbe() {
        try {
            return ((Integer) GET_PROBE_METHOD.invoke(null, new Object[0])).intValue();
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    private static void setProbe(int i) {
        try {
            SET_PROBE_METHOD.invoke(null, Integer.valueOf(i));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    static int advanceProbe(int i) {
        int i2 = i ^ (i << 13);
        int i3 = i2 ^ (i2 >>> 17);
        int i4 = i3 ^ (i3 << 5);
        setProbe(i4);
        return i4;
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x007f, code lost:
    
        if (r11.cells != r4) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0081, code lost:
    
        r11.cells = (java9.util.concurrent.atomic.Striped64.Cell[]) java.util.Arrays.copyOf(r4, r5 << 1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final void longAccumulate(long r12, java9.util.function.LongBinaryOperator r14, boolean r15) {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: java9.util.concurrent.atomic.Striped64.longAccumulate(long, java9.util.function.LongBinaryOperator, boolean):void");
    }

    private static long apply(DoubleBinaryOperator doubleBinaryOperator, long j, double d) {
        double dLongBitsToDouble = Double.longBitsToDouble(j);
        return Double.doubleToRawLongBits(doubleBinaryOperator == null ? dLongBitsToDouble + d : doubleBinaryOperator.applyAsDouble(dLongBitsToDouble, d));
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x007e, code lost:
    
        if (r11.cells != r4) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0080, code lost:
    
        r11.cells = (java9.util.concurrent.atomic.Striped64.Cell[]) java.util.Arrays.copyOf(r4, r5 << 1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final void doubleAccumulate(double r12, java9.util.function.DoubleBinaryOperator r14, boolean r15) {
        /*
            Method dump skipped, instruction units count: 211
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: java9.util.concurrent.atomic.Striped64.doubleAccumulate(double, java9.util.function.DoubleBinaryOperator, boolean):void");
    }

    static long getAndSetLong(Object obj, long j, long j2) {
        Unsafe unsafe;
        long longVolatile;
        do {
            unsafe = U;
            longVolatile = unsafe.getLongVolatile(obj, j);
        } while (!unsafe.compareAndSwapLong(obj, j, longVolatile, j2));
        return longVolatile;
    }
}
