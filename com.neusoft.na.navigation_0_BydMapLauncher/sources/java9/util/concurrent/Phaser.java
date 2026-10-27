package java9.util.concurrent;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import java9.util.concurrent.ForkJoinPool;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
public class Phaser {
    private static final long COUNTS_MASK = 4294967295L;
    private static final int EMPTY = 1;
    private static final int MAX_PARTIES = 65535;
    private static final int MAX_PHASE = Integer.MAX_VALUE;
    private static final int NCPU;
    private static final int ONE_ARRIVAL = 1;
    private static final int ONE_DEREGISTER = 65537;
    private static final int ONE_PARTY = 65536;
    private static final long PARTIES_MASK = 4294901760L;
    private static final int PARTIES_SHIFT = 16;
    private static final int PHASE_SHIFT = 32;
    static final int SPINS_PER_ARRIVAL;
    private static final long STATE;
    private static final long TERMINATION_BIT = Long.MIN_VALUE;
    private static final Unsafe U;
    private static final int UNARRIVED_MASK = 65535;
    private final AtomicReference<QNode> evenQ;
    private final AtomicReference<QNode> oddQ;
    private final Phaser parent;
    private final Phaser root;
    private volatile long state;

    private static int arrivedOf(long j) {
        int i = (int) j;
        if (i == 1) {
            return 0;
        }
        return (i >>> 16) - (i & 65535);
    }

    private static int partiesOf(long j) {
        return ((int) j) >>> 16;
    }

    private static int phaseOf(long j) {
        return (int) (j >>> 32);
    }

    private static int unarrivedOf(long j) {
        int i = (int) j;
        if (i == 1) {
            return 0;
        }
        return i & 65535;
    }

    protected boolean onAdvance(int i, int i2) {
        return i2 == 0;
    }

    private String badArrive(long j) {
        return "Attempted arrival of unregistered party for " + stateToString(j);
    }

    private String badRegister(long j) {
        return "Attempt to register more than 65535 parties for " + stateToString(j);
    }

    private int doArrive(int i) {
        long jReconcileState;
        int i2;
        int i3;
        Unsafe unsafe;
        long j;
        long j2;
        long j3;
        long j4;
        Phaser phaser = this.root;
        do {
            jReconcileState = phaser == this ? this.state : reconcileState();
            i2 = (int) (jReconcileState >>> 32);
            if (i2 < 0) {
                return i2;
            }
            int i4 = (int) jReconcileState;
            i3 = i4 == 1 ? 0 : i4 & 65535;
            if (i3 <= 0) {
                throw new IllegalStateException(badArrive(jReconcileState));
            }
            unsafe = U;
            j = STATE;
            j2 = jReconcileState - ((long) i);
        } while (!unsafe.compareAndSwapLong(this, j, jReconcileState, j2));
        if (i3 != 1) {
            return i2;
        }
        long j5 = j2 & PARTIES_MASK;
        int i5 = ((int) j5) >>> 16;
        if (phaser != this) {
            if (i5 == 0) {
                int iDoArrive = this.parent.doArrive(65537);
                unsafe.compareAndSwapLong(this, j, j2, j2 | 1);
                return iDoArrive;
            }
            return this.parent.doArrive(1);
        }
        if (onAdvance(i2, i5)) {
            j3 = Long.MIN_VALUE;
        } else {
            if (i5 == 0) {
                j4 = j5 | 1;
                unsafe.compareAndSwapLong(this, j, j2, j4 | (((long) ((i2 + 1) & Integer.MAX_VALUE)) << 32));
                releaseWaiters(i2);
                return i2;
            }
            j3 = i5;
        }
        j4 = j5 | j3;
        unsafe.compareAndSwapLong(this, j, j2, j4 | (((long) ((i2 + 1) & Integer.MAX_VALUE)) << 32));
        releaseWaiters(i2);
        return i2;
    }

    private int doRegister(int i) {
        int iDoRegister;
        long j = i;
        long j2 = j | (j << 16);
        Phaser phaser = this.parent;
        while (true) {
            long jReconcileState = phaser == null ? this.state : reconcileState();
            int i2 = (int) jReconcileState;
            int i3 = i2 & 65535;
            if (i > 65535 - (i2 >>> 16)) {
                throw new IllegalStateException(badRegister(jReconcileState));
            }
            iDoRegister = (int) (jReconcileState >>> 32);
            if (iDoRegister < 0) {
                break;
            }
            if (i2 != 1) {
                if (phaser == null || reconcileState() == jReconcileState) {
                    if (i3 == 0) {
                        this.root.internalAwaitAdvance(iDoRegister, null);
                    } else if (U.compareAndSwapLong(this, STATE, jReconcileState, jReconcileState + j2)) {
                        break;
                    }
                }
            } else if (phaser == null) {
                if (U.compareAndSwapLong(this, STATE, jReconcileState, (((long) iDoRegister) << 32) | j2)) {
                    break;
                }
            } else {
                synchronized (this) {
                    if (this.state == jReconcileState) {
                        iDoRegister = phaser.doRegister(1);
                        if (iDoRegister >= 0) {
                            while (!U.compareAndSwapLong(this, STATE, jReconcileState, (((long) iDoRegister) << 32) | j2)) {
                                jReconcileState = this.state;
                                iDoRegister = (int) (this.root.state >>> 32);
                            }
                        }
                    }
                }
            }
        }
        return iDoRegister;
    }

    private long reconcileState() {
        long j;
        Phaser phaser = this.root;
        long j2 = this.state;
        if (phaser == this) {
            return j2;
        }
        long j3 = j2;
        while (true) {
            int i = (int) (phaser.state >>> 32);
            if (i == ((int) (j3 >>> 32))) {
                return j3;
            }
            Unsafe unsafe = U;
            long j4 = STATE;
            long j5 = ((long) i) << 32;
            if (i < 0) {
                j = COUNTS_MASK & j3;
            } else {
                int i2 = ((int) j3) >>> 16;
                j = i2 == 0 ? 1L : (PARTIES_MASK & j3) | ((long) i2);
            }
            long j6 = j5 | j;
            if (unsafe.compareAndSwapLong(this, j4, j3, j6)) {
                return j6;
            }
            j3 = this.state;
        }
    }

    public Phaser() {
        this(null, 0);
    }

    public Phaser(int i) {
        this(null, i);
    }

    public Phaser(Phaser phaser) {
        this(phaser, 0);
    }

    public Phaser(Phaser phaser, int i) {
        long j;
        if ((i >>> 16) != 0) {
            throw new IllegalArgumentException("Illegal number of parties");
        }
        this.parent = phaser;
        int iDoRegister = 0;
        if (phaser != null) {
            Phaser phaser2 = phaser.root;
            this.root = phaser2;
            this.evenQ = phaser2.evenQ;
            this.oddQ = phaser2.oddQ;
            if (i != 0) {
                iDoRegister = phaser.doRegister(1);
            }
        } else {
            this.root = this;
            this.evenQ = new AtomicReference<>();
            this.oddQ = new AtomicReference<>();
        }
        if (i == 0) {
            j = 1;
        } else {
            long j2 = i;
            j = j2 | (((long) iDoRegister) << 32) | (j2 << 16);
        }
        this.state = j;
    }

    public int register() {
        return doRegister(1);
    }

    public int bulkRegister(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i == 0) {
            return getPhase();
        }
        return doRegister(i);
    }

    public int arrive() {
        return doArrive(1);
    }

    public int arriveAndDeregister() {
        return doArrive(65537);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int arriveAndAwaitAdvance() {
        /*
            r21 = this;
            r8 = r21
            java9.util.concurrent.Phaser r9 = r8.root
        L4:
            if (r9 != r8) goto L9
            long r0 = r8.state
            goto Ld
        L9:
            long r0 = r21.reconcileState()
        Ld:
            r4 = r0
            r10 = 32
            long r0 = r4 >>> r10
            int r11 = (int) r0
            if (r11 >= 0) goto L16
            return r11
        L16:
            int r0 = (int) r4
            r12 = 1
            if (r0 != r12) goto L1c
            r0 = 0
            goto L20
        L1c:
            r1 = 65535(0xffff, float:9.1834E-41)
            r0 = r0 & r1
        L20:
            r13 = r0
            if (r13 <= 0) goto L83
            sun.misc.Unsafe r14 = java9.util.concurrent.Phaser.U
            long r15 = java9.util.concurrent.Phaser.STATE
            r17 = 1
            long r19 = r4 - r17
            r0 = r14
            r1 = r21
            r2 = r15
            r6 = r19
            boolean r0 = r0.compareAndSwapLong(r1, r2, r4, r6)
            if (r0 == 0) goto L4
            if (r13 <= r12) goto L3f
            r0 = 0
            int r0 = r9.internalAwaitAdvance(r11, r0)
            return r0
        L3f:
            if (r9 == r8) goto L48
            java9.util.concurrent.Phaser r0 = r8.parent
            int r0 = r0.arriveAndAwaitAdvance()
            return r0
        L48:
            r0 = 4294901760(0xffff0000, double:2.121963412E-314)
            long r0 = r19 & r0
            int r2 = (int) r0
            int r2 = r2 >>> 16
            boolean r3 = r8.onAdvance(r11, r2)
            if (r3 == 0) goto L5c
            r2 = -9223372036854775808
        L5a:
            long r0 = r0 | r2
            goto L63
        L5c:
            if (r2 != 0) goto L61
            long r0 = r0 | r17
            goto L63
        L61:
            long r2 = (long) r2
            goto L5a
        L63:
            int r2 = r11 + 1
            r3 = 2147483647(0x7fffffff, float:NaN)
            r9 = r2 & r3
            long r2 = (long) r9
            long r2 = r2 << r10
            long r6 = r0 | r2
            r0 = r14
            r1 = r21
            r2 = r15
            r4 = r19
            boolean r0 = r0.compareAndSwapLong(r1, r2, r4, r6)
            if (r0 != 0) goto L7f
            long r0 = r8.state
            long r0 = r0 >>> r10
            int r0 = (int) r0
            return r0
        L7f:
            r8.releaseWaiters(r11)
            return r9
        L83:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = r8.badArrive(r4)
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java9.util.concurrent.Phaser.arriveAndAwaitAdvance():int");
    }

    public int awaitAdvance(int i) {
        Phaser phaser = this.root;
        int iReconcileState = (int) ((phaser == this ? this.state : reconcileState()) >>> 32);
        return i < 0 ? i : iReconcileState == i ? phaser.internalAwaitAdvance(i, null) : iReconcileState;
    }

    public int awaitAdvanceInterruptibly(int i) throws InterruptedException {
        Phaser phaser = this.root;
        int iReconcileState = (int) ((phaser == this ? this.state : reconcileState()) >>> 32);
        if (i < 0) {
            return i;
        }
        if (iReconcileState != i) {
            return iReconcileState;
        }
        QNode qNode = new QNode(this, i, true, false, 0L);
        int iInternalAwaitAdvance = phaser.internalAwaitAdvance(i, qNode);
        if (qNode.wasInterrupted) {
            throw new InterruptedException();
        }
        return iInternalAwaitAdvance;
    }

    public int awaitAdvanceInterruptibly(int i, long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        long nanos = timeUnit.toNanos(j);
        Phaser phaser = this.root;
        int iReconcileState = (int) ((phaser == this ? this.state : reconcileState()) >>> 32);
        if (i < 0) {
            return i;
        }
        if (iReconcileState != i) {
            return iReconcileState;
        }
        QNode qNode = new QNode(this, i, true, true, nanos);
        int iInternalAwaitAdvance = phaser.internalAwaitAdvance(i, qNode);
        if (qNode.wasInterrupted) {
            throw new InterruptedException();
        }
        if (iInternalAwaitAdvance != i) {
            return iInternalAwaitAdvance;
        }
        throw new TimeoutException();
    }

    public void forceTermination() {
        long j;
        Phaser phaser = this.root;
        do {
            j = phaser.state;
            if (j < 0) {
                return;
            }
        } while (!U.compareAndSwapLong(phaser, STATE, j, Long.MIN_VALUE | j));
        releaseWaiters(0);
        releaseWaiters(1);
    }

    public final int getPhase() {
        return (int) (this.root.state >>> 32);
    }

    public int getRegisteredParties() {
        return partiesOf(this.state);
    }

    public int getArrivedParties() {
        return arrivedOf(reconcileState());
    }

    public int getUnarrivedParties() {
        return unarrivedOf(reconcileState());
    }

    public Phaser getParent() {
        return this.parent;
    }

    public Phaser getRoot() {
        return this.root;
    }

    public boolean isTerminated() {
        return this.root.state < 0;
    }

    public String toString() {
        return stateToString(reconcileState());
    }

    private String stateToString(long j) {
        return super.toString() + "[phase = " + phaseOf(j) + " parties = " + partiesOf(j) + " arrived = " + arrivedOf(j) + "]";
    }

    private void releaseWaiters(int i) {
        Thread thread;
        AtomicReference<QNode> atomicReference = (i & 1) == 0 ? this.evenQ : this.oddQ;
        while (true) {
            QNode qNode = atomicReference.get();
            if (qNode == null || qNode.phase == ((int) (this.root.state >>> 32))) {
                return;
            }
            if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, qNode, qNode.next) && (thread = qNode.thread) != null) {
                qNode.thread = null;
                LockSupport.unpark(thread);
            }
        }
    }

    private int abortWait(int i) {
        int i2;
        Thread thread;
        AtomicReference<QNode> atomicReference = (i & 1) == 0 ? this.evenQ : this.oddQ;
        while (true) {
            QNode qNode = atomicReference.get();
            i2 = (int) (this.root.state >>> 32);
            if (qNode == null || ((thread = qNode.thread) != null && qNode.phase == i2)) {
                break;
            }
            if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, qNode, qNode.next) && thread != null) {
                qNode.thread = null;
                LockSupport.unpark(thread);
            }
        }
        return i2;
    }

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        NCPU = iAvailableProcessors;
        SPINS_PER_ARRIVAL = iAvailableProcessors < 2 ? 1 : 256;
        Unsafe unsafe = UnsafeAccess.unsafe;
        U = unsafe;
        try {
            STATE = unsafe.objectFieldOffset(Phaser.class.getDeclaredField("state"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    private int internalAwaitAdvance(int i, QNode qNode) {
        int i2;
        releaseWaiters(i - 1);
        int i3 = SPINS_PER_ARRIVAL;
        int i4 = 0;
        boolean zM = false;
        while (true) {
            long j = this.state;
            i2 = (int) (j >>> 32);
            if (i2 != i) {
                break;
            }
            if (qNode == null) {
                int i5 = ((int) j) & 65535;
                if (i5 != i4) {
                    if (i5 < NCPU) {
                        i3 += SPINS_PER_ARRIVAL;
                    }
                    i4 = i5;
                }
                boolean zInterrupted = Thread.interrupted();
                if (zInterrupted || i3 - 1 < 0) {
                    qNode = new QNode(this, i, false, false, 0L);
                    qNode.wasInterrupted = zInterrupted;
                }
            } else {
                if (qNode.isReleasable()) {
                    break;
                }
                if (!zM) {
                    AtomicReference<QNode> atomicReference = (i & 1) == 0 ? this.evenQ : this.oddQ;
                    QNode qNode2 = atomicReference.get();
                    qNode.next = qNode2;
                    if (qNode2 == null || qNode2.phase == i) {
                        if (((int) (this.state >>> 32)) == i) {
                            zM = LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, qNode2, qNode);
                        }
                    }
                } else {
                    try {
                        ForkJoinPool.managedBlock(qNode);
                    } catch (InterruptedException unused) {
                        qNode.wasInterrupted = true;
                    }
                }
            }
        }
        if (qNode != null) {
            if (qNode.thread != null) {
                qNode.thread = null;
            }
            if (qNode.wasInterrupted && !qNode.interruptible) {
                Thread.currentThread().interrupt();
            }
            if (i2 == i && (i2 = (int) (this.state >>> 32)) == i) {
                return abortWait(i);
            }
        }
        releaseWaiters(i);
        return i2;
    }

    static final class QNode implements ForkJoinPool.ManagedBlocker {
        final long deadline;
        final boolean interruptible;
        long nanos;
        QNode next;
        final int phase;
        final Phaser phaser;
        volatile Thread thread;
        final boolean timed;
        boolean wasInterrupted;

        QNode(Phaser phaser, int i, boolean z, boolean z2, long j) {
            this.phaser = phaser;
            this.phase = i;
            this.interruptible = z;
            this.nanos = j;
            this.timed = z2;
            this.deadline = z2 ? System.nanoTime() + j : 0L;
            this.thread = Thread.currentThread();
        }

        @Override // java9.util.concurrent.ForkJoinPool.ManagedBlocker
        public boolean isReleasable() {
            if (this.thread == null) {
                return true;
            }
            if (this.phaser.getPhase() != this.phase) {
                this.thread = null;
                return true;
            }
            if (Thread.interrupted()) {
                this.wasInterrupted = true;
            }
            if (this.wasInterrupted && this.interruptible) {
                this.thread = null;
                return true;
            }
            if (!this.timed) {
                return false;
            }
            if (this.nanos > 0) {
                long jNanoTime = this.deadline - System.nanoTime();
                this.nanos = jNanoTime;
                if (jNanoTime > 0) {
                    return false;
                }
            }
            this.thread = null;
            return true;
        }

        @Override // java9.util.concurrent.ForkJoinPool.ManagedBlocker
        public boolean block() {
            while (!isReleasable()) {
                if (this.timed) {
                    LockSupport.parkNanos(this, this.nanos);
                } else {
                    LockSupport.park(this);
                }
            }
            return true;
        }
    }
}
