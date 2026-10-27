package java9.util.concurrent;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import com.neusoft.na.common.ServiceBase;
import java.lang.Thread;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.Permission;
import java.security.Permissions;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java9.util.Objects;
import java9.util.concurrent.ForkJoinTask;
import java9.util.concurrent.ForkJoinWorkerThread;
import java9.util.function.Predicate;
import kotlin.jvm.internal.LongCompanionObject;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
public class ForkJoinPool extends AbstractExecutorService {
    private static final int ABASE;
    private static final Class<?> ACTCLASS;
    private static final long ADD_WORKER = 140737488355328L;
    private static final int ASHIFT;
    private static final int COMMON_MAX_SPARES;
    static final int COMMON_PARALLELISM;
    private static final long CTL;
    private static final int DEFAULT_COMMON_MAX_SPARES = 256;
    private static final long DEFAULT_KEEPALIVE = 60000;
    static final int DORMANT = -1073741824;
    static final int FIFO = 65536;
    static final int MAX_CAP = 32767;
    private static final long MODE;
    static final int OWNED = 1;
    static final int POLL_LIMIT = 1024;
    static final int QLOCK = 1;
    static final int QUIET = 1073741824;
    private static final long RC_MASK = -281474976710656L;
    private static final int RC_SHIFT = 48;
    private static final long RC_UNIT = 281474976710656L;
    private static final int SEED_INCREMENT = -1640531527;
    static final int SHUTDOWN = 262144;
    static final int SMASK = 65535;
    private static final long SP_MASK = 4294967295L;
    static final int SQMASK = 126;
    static final int SS_SEQ = 65536;
    static final int STOP = Integer.MIN_VALUE;
    static final int SWIDTH = 16;
    private static final long TC_MASK = 281470681743360L;
    private static final int TC_SHIFT = 32;
    private static final long TC_UNIT = 4294967296L;
    static final int TERMINATED = 524288;
    private static final long TIMEOUT_SLOP = 20;
    private static final Unsafe U;
    private static final long UC_MASK = -4294967296L;
    static final int UNSIGNALLED = Integer.MIN_VALUE;
    static final ForkJoinPool common;
    public static final ForkJoinWorkerThreadFactory defaultForkJoinWorkerThreadFactory;
    static final RuntimePermission modifyThreadPermission;
    private static int poolNumberSequence;
    final int bounds;
    volatile long ctl;
    final ForkJoinWorkerThreadFactory factory;
    int indexSeed;
    final long keepAlive;
    volatile int mode;
    volatile long pad00;
    volatile long pad01;
    volatile long pad02;
    volatile long pad03;
    volatile long pad04;
    volatile long pad05;
    volatile long pad06;
    volatile long pad07;
    volatile long pad08;
    volatile long pad09;
    volatile long pad0a;
    volatile long pad0b;
    volatile long pad0c;
    volatile long pad0d;
    volatile long pad0e;
    volatile long pad0f;
    volatile long pad10;
    volatile long pad11;
    volatile long pad12;
    volatile long pad13;
    volatile long pad14;
    volatile long pad15;
    volatile long pad16;
    volatile long pad17;
    volatile long pad18;
    volatile long pad19;
    volatile long pad1a;
    volatile long pad1b;
    volatile long pad1c;
    volatile long pad1d;
    volatile long pad1e;
    final Predicate<? super ForkJoinPool> saturate;
    volatile long stealCount;
    final Thread.UncaughtExceptionHandler ueh;
    WorkQueue[] workQueues;
    final String workerNamePrefix;

    public interface ForkJoinWorkerThreadFactory {
        ForkJoinWorkerThread newThread(ForkJoinPool forkJoinPool);
    }

    public interface ManagedBlocker {
        boolean block() throws InterruptedException;

        boolean isReleasable();
    }

    private static void checkPermission() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(modifyThreadPermission);
        }
    }

    static AccessControlContext contextWithPermissions(Permission... permissionArr) {
        Permissions permissions = new Permissions();
        for (Permission permission : permissionArr) {
            permissions.add(permission);
        }
        return new AccessControlContext(new ProtectionDomain[]{new ProtectionDomain(null, permissions)});
    }

    private static final class DefaultForkJoinWorkerThreadFactory implements ForkJoinWorkerThreadFactory {
        private static final AccessControlContext ACC = ForkJoinPool.contextWithPermissions(new RuntimePermission("getClassLoader"));

        private DefaultForkJoinWorkerThreadFactory() {
        }

        @Override // java9.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory
        public final ForkJoinWorkerThread newThread(final ForkJoinPool forkJoinPool) {
            return (ForkJoinWorkerThread) AccessController.doPrivileged(new PrivilegedAction<ForkJoinWorkerThread>() { // from class: java9.util.concurrent.ForkJoinPool.DefaultForkJoinWorkerThreadFactory.1
                @Override // java.security.PrivilegedAction
                public ForkJoinWorkerThread run() {
                    return new ForkJoinWorkerThread(forkJoinPool, ClassLoader.getSystemClassLoader());
                }
            }, ACC);
        }
    }

    static final class WorkQueue {
        private static final int ABASE;
        private static final int ASHIFT;
        static final int INITIAL_QUEUE_CAPACITY = 8192;
        static final int MAXIMUM_QUEUE_CAPACITY = 67108864;
        private static final long PHASE;
        private static final Unsafe U;
        ForkJoinTask<?>[] array;
        int id;
        int nsteals;
        final ForkJoinWorkerThread owner;
        volatile long pad00;
        volatile long pad01;
        volatile long pad02;
        volatile long pad03;
        volatile long pad04;
        volatile long pad05;
        volatile long pad06;
        volatile long pad07;
        volatile long pad08;
        volatile long pad09;
        volatile long pad0a;
        volatile long pad0b;
        volatile long pad0c;
        volatile long pad0d;
        volatile long pad0e;
        volatile long pad0f;
        volatile Object pad10;
        volatile Object pad11;
        volatile Object pad12;
        volatile Object pad13;
        volatile Object pad14;
        volatile Object pad15;
        volatile Object pad16;
        volatile Object pad17;
        volatile Object pad18;
        volatile Object pad19;
        volatile Object pad1a;
        volatile Object pad1b;
        volatile Object pad1c;
        volatile Object pad1d;
        volatile Object pad1e;
        volatile Object pad1f;
        volatile int phase;
        final ForkJoinPool pool;
        volatile int source;
        int stackPred;
        int top = 4096;
        volatile int base = 4096;

        WorkQueue(ForkJoinPool forkJoinPool, ForkJoinWorkerThread forkJoinWorkerThread) {
            this.pool = forkJoinPool;
            this.owner = forkJoinWorkerThread;
        }

        final int getPoolIndex() {
            return (this.id & 65535) >>> 1;
        }

        final int queueSize() {
            int i = this.base - this.top;
            if (i >= 0) {
                return 0;
            }
            return -i;
        }

        final boolean isEmpty() {
            ForkJoinTask<?>[] forkJoinTaskArr;
            int length;
            int i = this.base;
            int i2 = i - this.top;
            if (i2 < 0) {
                return i2 == -1 && ((forkJoinTaskArr = this.array) == null || (length = forkJoinTaskArr.length) == 0 || forkJoinTaskArr[i & (length - 1)] == null);
            }
            return true;
        }

        final void push(ForkJoinTask<?> forkJoinTask) {
            int length;
            int i = this.top;
            ForkJoinTask<?>[] forkJoinTaskArr = this.array;
            if (forkJoinTaskArr == null || (length = forkJoinTaskArr.length) <= 0) {
                return;
            }
            long j = (((long) ((length - 1) & i)) << ASHIFT) + ((long) ABASE);
            ForkJoinPool forkJoinPool = this.pool;
            this.top = i + 1;
            U.putOrderedObject(forkJoinTaskArr, j, forkJoinTask);
            int i2 = this.base - i;
            if (i2 == 0 && forkJoinPool != null) {
                MemBar.fullFence();
                forkJoinPool.signalWork();
            } else if (i2 + length == 1) {
                growArray();
            }
        }

        final ForkJoinTask<?>[] growArray() {
            int i;
            ForkJoinTask<?>[] forkJoinTaskArr = this.array;
            int length = forkJoinTaskArr != null ? forkJoinTaskArr.length : 0;
            int i2 = length > 0 ? length << 1 : 8192;
            if (i2 < 8192 || i2 > 67108864) {
                throw new RejectedExecutionException("Queue capacity exceeded");
            }
            ForkJoinTask<?>[] forkJoinTaskArr2 = new ForkJoinTask[i2];
            this.array = forkJoinTaskArr2;
            if (forkJoinTaskArr != null && length - 1 > 0) {
                int i3 = this.top;
                int i4 = this.base;
                if (i3 - i4 > 0) {
                    int i5 = i2 - 1;
                    int i6 = i4;
                    do {
                        long j = ((long) ABASE) + (((long) (i6 & i)) << ASHIFT);
                        Unsafe unsafe = U;
                        ForkJoinTask<?> forkJoinTask = (ForkJoinTask) unsafe.getObjectVolatile(forkJoinTaskArr, j);
                        if (forkJoinTask != null && LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(unsafe, forkJoinTaskArr, j, forkJoinTask, null)) {
                            forkJoinTaskArr2[i6 & i5] = forkJoinTask;
                        }
                        i6++;
                    } while (i6 != i3);
                    MemBar.storeFence();
                }
            }
            return forkJoinTaskArr2;
        }

        final ForkJoinTask<?> pop() {
            int length;
            int i = this.base;
            int i2 = this.top;
            ForkJoinTask<?>[] forkJoinTaskArr = this.array;
            if (forkJoinTaskArr == null || i == i2 || (length = forkJoinTaskArr.length) <= 0) {
                return null;
            }
            int i3 = i2 - 1;
            long j = (((long) ((length - 1) & i3)) << ASHIFT) + ((long) ABASE);
            Unsafe unsafe = U;
            ForkJoinTask<?> forkJoinTask = (ForkJoinTask) unsafe.getObject(forkJoinTaskArr, j);
            if (forkJoinTask == null || !LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(unsafe, forkJoinTaskArr, j, forkJoinTask, null)) {
                return null;
            }
            this.top = i3;
            MemBar.storeFence();
            return forkJoinTask;
        }

        final ForkJoinTask<?> poll() {
            int i;
            int length;
            while (true) {
                int i2 = this.base;
                int i3 = this.top;
                ForkJoinTask<?>[] forkJoinTaskArr = this.array;
                if (forkJoinTaskArr == null || (i = i2 - i3) >= 0 || (length = forkJoinTaskArr.length) <= 0) {
                    return null;
                }
                long j = (((long) ((length - 1) & i2)) << ASHIFT) + ((long) ABASE);
                Unsafe unsafe = U;
                ForkJoinTask<?> forkJoinTask = (ForkJoinTask) unsafe.getObjectVolatile(forkJoinTaskArr, j);
                int i4 = i2 + 1;
                if (i2 == this.base) {
                    if (forkJoinTask != null) {
                        if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(unsafe, forkJoinTaskArr, j, forkJoinTask, null)) {
                            this.base = i4;
                            return forkJoinTask;
                        }
                    } else if (i == -1) {
                        return null;
                    }
                }
            }
        }

        final ForkJoinTask<?> nextLocalTask() {
            return (this.id & 65536) != 0 ? poll() : pop();
        }

        final ForkJoinTask<?> peek() {
            int length;
            ForkJoinTask<?>[] forkJoinTaskArr = this.array;
            if (forkJoinTaskArr == null || (length = forkJoinTaskArr.length) <= 0) {
                return null;
            }
            return forkJoinTaskArr[(length - 1) & ((this.id & 65536) != 0 ? this.base : this.top - 1)];
        }

        final boolean tryUnpush(ForkJoinTask<?> forkJoinTask) {
            int length;
            int i = this.base;
            int i2 = this.top;
            ForkJoinTask<?>[] forkJoinTaskArr = this.array;
            if (forkJoinTaskArr == null || i == i2 || (length = forkJoinTaskArr.length) <= 0) {
                return false;
            }
            int i3 = i2 - 1;
            if (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(U, forkJoinTaskArr, (((long) ((length - 1) & i3)) << ASHIFT) + ((long) ABASE), forkJoinTask, null)) {
                return false;
            }
            this.top = i3;
            MemBar.storeFence();
            return true;
        }

        final void cancelAll() {
            while (true) {
                ForkJoinTask<?> forkJoinTaskPoll = poll();
                if (forkJoinTaskPoll == null) {
                    return;
                } else {
                    ForkJoinTask.cancelIgnoringExceptions(forkJoinTaskPoll);
                }
            }
        }

        final void localPopAndExec(int i) {
            int length;
            while (true) {
                int i2 = this.base;
                int i3 = this.top;
                ForkJoinTask<?>[] forkJoinTaskArr = this.array;
                if (forkJoinTaskArr == null || i2 == i3 || (length = forkJoinTaskArr.length) <= 0) {
                    return;
                }
                int i4 = i3 - 1;
                ForkJoinTask forkJoinTask = (ForkJoinTask) ForkJoinPool.getAndSetObject(forkJoinTaskArr, (((long) ((length - 1) & i4)) << ASHIFT) + ((long) ABASE), null);
                if (forkJoinTask == null) {
                    return;
                }
                this.top = i4;
                MemBar.storeFence();
                forkJoinTask.doExec();
                if (i != 0 && i - 1 == 0) {
                    return;
                }
            }
        }

        final void localPollAndExec(int i) {
            int i2;
            int length;
            do {
                int i3 = 0;
                while (true) {
                    int i4 = this.base;
                    int i5 = this.top;
                    ForkJoinTask<?>[] forkJoinTaskArr = this.array;
                    if (forkJoinTaskArr != null && (i2 = i4 - i5) < 0 && (length = forkJoinTaskArr.length) > 0) {
                        int i6 = i4 + 1;
                        ForkJoinTask forkJoinTask = (ForkJoinTask) ForkJoinPool.getAndSetObject(forkJoinTaskArr, (((long) (i4 & (length - 1))) << ASHIFT) + ((long) ABASE), null);
                        if (forkJoinTask == null) {
                            break;
                        }
                        this.base = i6;
                        forkJoinTask.doExec();
                        if (i != 0 && (i3 = i3 + 1) == i) {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            } while (i2 != -1);
        }

        final void tryRemoveAndExec(ForkJoinTask<?> forkJoinTask) {
            ForkJoinTask<?>[] forkJoinTaskArr;
            int length;
            int i = this.base;
            int i2 = this.top;
            if (i - i2 >= 0 || (forkJoinTaskArr = this.array) == null || (length = forkJoinTaskArr.length) <= 0) {
                return;
            }
            int i3 = length - 1;
            int i4 = i2 - 1;
            int i5 = i4;
            while (true) {
                long j = ((i5 & i3) << ASHIFT) + ABASE;
                Unsafe unsafe = U;
                ForkJoinTask<?> forkJoinTask2 = (ForkJoinTask) unsafe.getObject(forkJoinTaskArr, j);
                if (forkJoinTask2 == null) {
                    return;
                }
                if (forkJoinTask2 == forkJoinTask) {
                    if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(unsafe, forkJoinTaskArr, j, forkJoinTask2, null)) {
                        this.top = i4;
                        while (i5 != i4) {
                            int i6 = i5 + 1;
                            int i7 = ASHIFT;
                            long j2 = ((i6 & i3) << i7) + ABASE;
                            Unsafe unsafe2 = U;
                            ForkJoinTask forkJoinTask3 = (ForkJoinTask) unsafe2.getObject(forkJoinTaskArr, j2);
                            unsafe2.putObjectVolatile(forkJoinTaskArr, j2, (Object) null);
                            unsafe2.putOrderedObject(forkJoinTaskArr, ((i5 & i3) << i7) + r4, forkJoinTask3);
                            i5 = i6;
                        }
                        MemBar.storeFence();
                        forkJoinTask2.doExec();
                        return;
                    }
                    return;
                }
                i5--;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x004b A[EDGE_INSN: B:38:0x004b->B:21:0x004b BREAK  A[LOOP:1: B:14:0x0030->B:40:?]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        final int localHelpCC(java9.util.concurrent.CountedCompleter<?> r10, int r11) {
            /*
                r9 = this;
                r0 = 0
                if (r10 == 0) goto L5b
                int r1 = r10.status
                if (r1 < 0) goto L5a
            L7:
                int r1 = r9.base
                int r2 = r9.top
                java9.util.concurrent.ForkJoinTask<?>[] r4 = r9.array
                if (r4 == 0) goto L4b
                if (r1 == r2) goto L4b
                int r1 = r4.length
                if (r1 <= 0) goto L4b
                int r1 = r1 + (-1)
                int r2 = r2 + (-1)
                r1 = r1 & r2
                long r5 = (long) r1
                int r1 = java9.util.concurrent.ForkJoinPool.WorkQueue.ASHIFT
                long r5 = r5 << r1
                int r1 = java9.util.concurrent.ForkJoinPool.WorkQueue.ABASE
                long r7 = (long) r1
                long r5 = r5 + r7
                sun.misc.Unsafe r1 = java9.util.concurrent.ForkJoinPool.WorkQueue.U
                java.lang.Object r1 = r1.getObject(r4, r5)
                java9.util.concurrent.ForkJoinTask r1 = (java9.util.concurrent.ForkJoinTask) r1
                boolean r3 = r1 instanceof java9.util.concurrent.CountedCompleter
                if (r3 == 0) goto L4b
                java9.util.concurrent.CountedCompleter r1 = (java9.util.concurrent.CountedCompleter) r1
                r3 = r1
            L30:
                if (r3 == r10) goto L37
                java9.util.concurrent.CountedCompleter<?> r3 = r3.completer
                if (r3 != 0) goto L30
                goto L4b
            L37:
                sun.misc.Unsafe r3 = java9.util.concurrent.ForkJoinPool.WorkQueue.U
                r8 = 0
                r7 = r1
                boolean r3 = androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(r3, r4, r5, r7, r8)
                if (r3 == 0) goto L4b
                r9.top = r2
                java9.util.concurrent.ForkJoinPool.MemBar.storeFence()
                r1.doExec()
                r1 = 1
                goto L4c
            L4b:
                r1 = r0
            L4c:
                int r2 = r10.status
                if (r2 < 0) goto L58
                if (r1 == 0) goto L58
                if (r11 == 0) goto L7
                int r11 = r11 + (-1)
                if (r11 != 0) goto L7
            L58:
                r0 = r2
                goto L5b
            L5a:
                r0 = r1
            L5b:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: java9.util.concurrent.ForkJoinPool.WorkQueue.localHelpCC(java9.util.concurrent.CountedCompleter, int):int");
        }

        final boolean tryLockSharedQueue() {
            return U.compareAndSwapInt(this, PHASE, 0, 1);
        }

        final boolean trySharedUnpush(ForkJoinTask<?> forkJoinTask) {
            int length;
            boolean z = true;
            int i = this.top - 1;
            ForkJoinTask<?>[] forkJoinTaskArr = this.array;
            if (forkJoinTaskArr == null || (length = forkJoinTaskArr.length) <= 0) {
                return false;
            }
            long j = (((long) ((length - 1) & i)) << ASHIFT) + ((long) ABASE);
            Unsafe unsafe = U;
            if (((ForkJoinTask) unsafe.getObject(forkJoinTaskArr, j)) != forkJoinTask) {
                return false;
            }
            long j2 = PHASE;
            if (!unsafe.compareAndSwapInt(this, j2, 0, 1)) {
                return false;
            }
            if (this.top == i + 1 && this.array == forkJoinTaskArr && LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(unsafe, forkJoinTaskArr, j, forkJoinTask, null)) {
                this.top = i;
            } else {
                z = false;
            }
            unsafe.putOrderedInt(this, j2, 0);
            return z;
        }

        /* JADX WARN: Removed duplicated region for block: B:32:0x007a  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x007e A[EDGE_INSN: B:49:0x007e->B:33:0x007e BREAK  A[LOOP:1: B:15:0x003a->B:51:?]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        final int sharedHelpCC(java9.util.concurrent.CountedCompleter<?> r22, int r23) {
            /*
                r21 = this;
                r6 = r21
                r7 = r22
                r8 = 0
                if (r7 == 0) goto L8c
                int r0 = r7.status
                if (r0 < 0) goto L8b
                r9 = r23
            Ld:
                int r0 = r6.base
                int r10 = r6.top
                java9.util.concurrent.ForkJoinTask<?>[] r12 = r6.array
                if (r12 == 0) goto L7e
                if (r0 == r10) goto L7e
                int r0 = r12.length
                if (r0 <= 0) goto L7e
                int r0 = r0 + (-1)
                int r15 = r10 + (-1)
                r0 = r0 & r15
                long r0 = (long) r0
                int r2 = java9.util.concurrent.ForkJoinPool.WorkQueue.ASHIFT
                long r0 = r0 << r2
                int r2 = java9.util.concurrent.ForkJoinPool.WorkQueue.ABASE
                long r2 = (long) r2
                long r13 = r0 + r2
                sun.misc.Unsafe r0 = java9.util.concurrent.ForkJoinPool.WorkQueue.U
                java.lang.Object r0 = r0.getObject(r12, r13)
                java9.util.concurrent.ForkJoinTask r0 = (java9.util.concurrent.ForkJoinTask) r0
                boolean r1 = r0 instanceof java9.util.concurrent.CountedCompleter
                if (r1 == 0) goto L7e
                r17 = r0
                java9.util.concurrent.CountedCompleter r17 = (java9.util.concurrent.CountedCompleter) r17
                r0 = r17
            L3a:
                if (r0 == r7) goto L41
                java9.util.concurrent.CountedCompleter<?> r0 = r0.completer
                if (r0 != 0) goto L3a
                goto L7e
            L41:
                sun.misc.Unsafe r11 = java9.util.concurrent.ForkJoinPool.WorkQueue.U
                long r4 = java9.util.concurrent.ForkJoinPool.WorkQueue.PHASE
                r16 = 0
                r18 = 1
                r0 = r11
                r1 = r21
                r2 = r4
                r19 = r4
                r4 = r16
                r5 = r18
                boolean r0 = r0.compareAndSwapInt(r1, r2, r4, r5)
                if (r0 == 0) goto L7e
                int r0 = r6.top
                if (r0 != r10) goto L71
                java9.util.concurrent.ForkJoinTask<?>[] r0 = r6.array
                if (r0 != r12) goto L71
                r16 = 0
                r0 = r11
                r10 = r15
                r15 = r17
                boolean r1 = androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(r11, r12, r13, r15, r16)
                if (r1 == 0) goto L72
                r6.top = r10
                r1 = 1
                goto L73
            L71:
                r0 = r11
            L72:
                r1 = r8
            L73:
                r2 = r19
                r0.putOrderedInt(r6, r2, r8)
                if (r1 == 0) goto L7f
                r17.doExec()
                goto L7f
            L7e:
                r1 = r8
            L7f:
                int r0 = r7.status
                if (r0 < 0) goto L8b
                if (r1 == 0) goto L8b
                if (r9 == 0) goto Ld
                int r9 = r9 + (-1)
                if (r9 != 0) goto Ld
            L8b:
                r8 = r0
            L8c:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: java9.util.concurrent.ForkJoinPool.WorkQueue.sharedHelpCC(java9.util.concurrent.CountedCompleter, int):int");
        }

        final boolean isApparentlyUnblocked() {
            Thread.State state;
            ForkJoinWorkerThread forkJoinWorkerThread = this.owner;
            return (forkJoinWorkerThread == null || (state = forkJoinWorkerThread.getState()) == Thread.State.BLOCKED || state == Thread.State.WAITING || state == Thread.State.TIMED_WAITING) ? false : true;
        }

        static {
            Unsafe unsafe = UnsafeAccess.unsafe;
            U = unsafe;
            try {
                PHASE = unsafe.objectFieldOffset(WorkQueue.class.getDeclaredField("phase"));
                ABASE = unsafe.arrayBaseOffset(ForkJoinTask[].class);
                int iArrayIndexScale = unsafe.arrayIndexScale(ForkJoinTask[].class);
                if (((iArrayIndexScale - 1) & iArrayIndexScale) != 0) {
                    throw new ExceptionInInitializerError("array index scale not a power of two");
                }
                ASHIFT = 31 - Integer.numberOfLeadingZeros(iArrayIndexScale);
            } catch (Exception e) {
                throw new ExceptionInInitializerError(e);
            }
        }
    }

    private static final synchronized int nextPoolId() {
        int i;
        i = poolNumberSequence + 1;
        poolNumberSequence = i;
        return i;
    }

    static long getAndAddLong(Object obj, long j, long j2) {
        Unsafe unsafe;
        long longVolatile;
        do {
            unsafe = U;
            longVolatile = unsafe.getLongVolatile(obj, j);
        } while (!unsafe.compareAndSwapLong(obj, j, longVolatile, longVolatile + j2));
        return longVolatile;
    }

    static Object getAndSetObject(Object obj, long j, Object obj2) {
        Unsafe unsafe;
        Object objectVolatile;
        do {
            unsafe = U;
            objectVolatile = unsafe.getObjectVolatile(obj, j);
        } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(unsafe, obj, j, objectVolatile, obj2));
        return objectVolatile;
    }

    private boolean createWorker() throws Throwable {
        ForkJoinWorkerThread forkJoinWorkerThreadNewThread;
        ForkJoinWorkerThreadFactory forkJoinWorkerThreadFactory = this.factory;
        Throwable th = null;
        if (forkJoinWorkerThreadFactory != null) {
            try {
                forkJoinWorkerThreadNewThread = forkJoinWorkerThreadFactory.newThread(this);
                if (forkJoinWorkerThreadNewThread != null) {
                    try {
                        forkJoinWorkerThreadNewThread.start();
                        return true;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                forkJoinWorkerThreadNewThread = null;
            }
        } else {
            forkJoinWorkerThreadNewThread = null;
        }
        deregisterWorker(forkJoinWorkerThreadNewThread, th);
        return false;
    }

    private void tryAddWorker(long j) throws Throwable {
        long j2 = j;
        do {
            long j3 = ((RC_UNIT + j2) & RC_MASK) | ((TC_UNIT + j2) & TC_MASK);
            if (this.ctl == j2 && U.compareAndSwapLong(this, CTL, j2, j3)) {
                createWorker();
                return;
            } else {
                j2 = this.ctl;
                if ((ADD_WORKER & j2) == 0) {
                    return;
                }
            }
        } while (((int) j2) == 0);
    }

    final WorkQueue registerWorker(ForkJoinWorkerThread forkJoinWorkerThread) {
        int i;
        int length;
        forkJoinWorkerThread.setDaemon(true);
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.ueh;
        if (uncaughtExceptionHandler != null) {
            forkJoinWorkerThread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        }
        WorkQueue workQueue = new WorkQueue(this, forkJoinWorkerThread);
        int i2 = this.mode & 65536;
        String str = this.workerNamePrefix;
        if (str != null) {
            synchronized (str) {
                WorkQueue[] workQueueArr = this.workQueues;
                int i3 = this.indexSeed + SEED_INCREMENT;
                this.indexSeed = i3;
                i = 0;
                if (workQueueArr != null && (length = workQueueArr.length) > 1) {
                    int i4 = length - 1;
                    int i5 = i3 & i4;
                    int i6 = ((i3 << 1) | 1) & i4;
                    int i7 = length >>> 1;
                    while (true) {
                        WorkQueue workQueue2 = workQueueArr[i6];
                        if (workQueue2 == null || workQueue2.phase == 1073741824) {
                            break;
                        }
                        i7--;
                        if (i7 == 0) {
                            i6 = length | 1;
                            break;
                        }
                        i6 = (i6 + 2) & i4;
                    }
                    int i8 = i2 | i6 | (i3 & 1073610752);
                    workQueue.id = i8;
                    workQueue.phase = i8;
                    if (i6 < length) {
                        workQueueArr[i6] = workQueue;
                    } else {
                        int i9 = length << 1;
                        WorkQueue[] workQueueArr2 = new WorkQueue[i9];
                        workQueueArr2[i6] = workQueue;
                        int i10 = i9 - 1;
                        while (i < length) {
                            WorkQueue workQueue3 = workQueueArr[i];
                            if (workQueue3 != null) {
                                workQueueArr2[workQueue3.id & i10 & 126] = workQueue3;
                            }
                            int i11 = i + 1;
                            if (i11 >= length) {
                                break;
                            }
                            workQueueArr2[i11] = workQueueArr[i11];
                            i = i11 + 1;
                        }
                        this.workQueues = workQueueArr2;
                    }
                    i = i5;
                }
            }
            forkJoinWorkerThread.setName(str.concat(Integer.toString(i)));
        }
        return workQueue;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0040 A[LOOP:0: B:27:0x0040->B:44:?, LOOP_START] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final void deregisterWorker(java9.util.concurrent.ForkJoinWorkerThread r18, java.lang.Throwable r19) throws java.lang.Throwable {
        /*
            r17 = this;
            r9 = r17
            r0 = r18
            r10 = 4294967295(0xffffffff, double:2.1219957905E-314)
            r1 = 0
            r12 = 0
            if (r0 == 0) goto L3a
            java9.util.concurrent.ForkJoinPool$WorkQueue r0 = r0.workQueue
            if (r0 == 0) goto L3b
            java.lang.String r2 = r9.workerNamePrefix
            int r3 = r0.nsteals
            long r3 = (long) r3
            long r3 = r3 & r10
            int r5 = r0.id
            r6 = 65535(0xffff, float:9.1834E-41)
            r5 = r5 & r6
            if (r2 == 0) goto L37
            monitor-enter(r2)
            java9.util.concurrent.ForkJoinPool$WorkQueue[] r6 = r9.workQueues     // Catch: java.lang.Throwable -> L34
            if (r6 == 0) goto L2d
            int r7 = r6.length     // Catch: java.lang.Throwable -> L34
            if (r7 <= r5) goto L2d
            r7 = r6[r5]     // Catch: java.lang.Throwable -> L34
            if (r7 != r0) goto L2d
            r6[r5] = r1     // Catch: java.lang.Throwable -> L34
        L2d:
            long r5 = r9.stealCount     // Catch: java.lang.Throwable -> L34
            long r5 = r5 + r3
            r9.stealCount = r5     // Catch: java.lang.Throwable -> L34
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L34
            goto L37
        L34:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L34
            throw r0
        L37:
            int r1 = r0.phase
            goto L3c
        L3a:
            r0 = r1
        L3b:
            r1 = r12
        L3c:
            r2 = 1073741824(0x40000000, float:2.0)
            if (r1 == r2) goto L66
        L40:
            sun.misc.Unsafe r1 = java9.util.concurrent.ForkJoinPool.U
            long r3 = java9.util.concurrent.ForkJoinPool.CTL
            long r5 = r9.ctl
            r7 = 281474976710656(0x1000000000000, double:1.390671161567E-309)
            long r7 = r5 - r7
            r13 = -281474976710656(0xffff000000000000, double:NaN)
            long r7 = r7 & r13
            r13 = 4294967296(0x100000000, double:2.121995791E-314)
            long r13 = r5 - r13
            r15 = 281470681743360(0xffff00000000, double:1.39064994160909E-309)
            long r13 = r13 & r15
            long r7 = r7 | r13
            long r13 = r5 & r10
            long r7 = r7 | r13
            r2 = r17
            boolean r1 = r1.compareAndSwapLong(r2, r3, r5, r7)
            if (r1 == 0) goto L40
        L66:
            if (r0 == 0) goto L6b
            r0.cancelAll()
        L6b:
            boolean r1 = r9.tryTerminate(r12, r12)
            if (r1 != 0) goto L7a
            if (r0 == 0) goto L7a
            java9.util.concurrent.ForkJoinTask<?>[] r0 = r0.array
            if (r0 == 0) goto L7a
            r17.signalWork()
        L7a:
            if (r19 != 0) goto L80
            java9.util.concurrent.ForkJoinTask.helpExpungeStaleExceptions()
            goto L83
        L80:
            java9.util.concurrent.ForkJoinTask.rethrow(r19)
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: java9.util.concurrent.ForkJoinPool.deregisterWorker(java9.util.concurrent.ForkJoinWorkerThread, java.lang.Throwable):void");
    }

    final void signalWork() {
        int i;
        WorkQueue workQueue;
        while (true) {
            long j = this.ctl;
            if (j >= 0) {
                return;
            }
            int i2 = (int) j;
            if (i2 == 0) {
                if ((ADD_WORKER & j) != 0) {
                    tryAddWorker(j);
                    return;
                }
                return;
            }
            WorkQueue[] workQueueArr = this.workQueues;
            if (workQueueArr == null || workQueueArr.length <= (i = 65535 & i2) || (workQueue = workQueueArr[i]) == null) {
                return;
            }
            int i3 = i2 & Integer.MAX_VALUE;
            int i4 = workQueue.phase;
            long j2 = (((long) workQueue.stackPred) & SP_MASK) | ((RC_UNIT + j) & UC_MASK);
            ForkJoinWorkerThread forkJoinWorkerThread = workQueue.owner;
            if (i2 == i4 && U.compareAndSwapLong(this, CTL, j, j2)) {
                workQueue.phase = i3;
                if (workQueue.source < 0) {
                    LockSupport.unpark(forkJoinWorkerThread);
                    return;
                }
                return;
            }
        }
    }

    private int tryCompensate(WorkQueue workQueue) {
        int length;
        boolean z;
        Thread.State state;
        long j = this.ctl;
        WorkQueue[] workQueueArr = this.workQueues;
        short s = (short) (j >>> 32);
        if (s >= 0) {
            if (workQueueArr == null || (length = workQueueArr.length) <= 0 || workQueue == null) {
                return 0;
            }
            int i = (int) j;
            if (i != 0) {
                WorkQueue workQueue2 = workQueueArr[i & (length - 1)];
                int i2 = workQueue.phase;
                long j2 = UC_MASK & (i2 < 0 ? RC_UNIT + j : j);
                int i3 = i & Integer.MAX_VALUE;
                if (workQueue2 == null) {
                    return 0;
                }
                int i4 = workQueue2.phase;
                ForkJoinWorkerThread forkJoinWorkerThread = workQueue2.owner;
                long j3 = (((long) workQueue2.stackPred) & SP_MASK) | j2;
                if (i4 != i || !U.compareAndSwapLong(this, CTL, j, j3)) {
                    return 0;
                }
                workQueue2.phase = i3;
                if (workQueue2.source < 0) {
                    LockSupport.unpark(forkJoinWorkerThread);
                }
                return i2 < 0 ? -1 : 1;
            }
            if (((int) (j >> 48)) - ((short) (this.bounds & 65535)) > 0) {
                return U.compareAndSwapLong(this, CTL, j, (RC_MASK & (j - RC_UNIT)) | (281474976710655L & j)) ? 1 : 0;
            }
            int i5 = this.mode & 65535;
            int i6 = i5 + s;
            int i7 = i6;
            int i8 = 0;
            int i9 = 1;
            while (true) {
                if (i9 >= length) {
                    z = false;
                    break;
                }
                WorkQueue workQueue3 = workQueueArr[i9];
                if (workQueue3 != null) {
                    if (workQueue3.source == 0) {
                        z = true;
                        break;
                    }
                    i7--;
                    ForkJoinWorkerThread forkJoinWorkerThread2 = workQueue3.owner;
                    if (forkJoinWorkerThread2 != null && ((state = forkJoinWorkerThread2.getState()) == Thread.State.BLOCKED || state == Thread.State.WAITING)) {
                        i8++;
                    }
                }
                i9 += 2;
            }
            if (z || i7 != 0 || this.ctl != j) {
                return 0;
            }
            if (i6 >= MAX_CAP || s >= (this.bounds >>> 16)) {
                Predicate<? super ForkJoinPool> predicate = this.saturate;
                if (predicate != null && predicate.test(this)) {
                    return -1;
                }
                if (i8 < i5) {
                    Thread.yield();
                    return 0;
                }
                throw new RejectedExecutionException("Thread limit exceeded replacing blocked worker");
            }
        }
        return (U.compareAndSwapLong(this, CTL, j, ((TC_UNIT + j) & TC_MASK) | ((-281470681743361L) & j)) && createWorker()) ? 1 : 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x00c2, code lost:
    
        r10 = r24.phase;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ce, code lost:
    
        if (r10 < 0) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00d0, code lost:
    
        r15 = (r10 + 65536) | Integer.MIN_VALUE;
        r24.phase = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00d9, code lost:
    
        r4 = r23.ctl;
        r24.stackPred = (int) r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00f1, code lost:
    
        if (java9.util.concurrent.ForkJoinPool.U.compareAndSwapLong(r23, java9.util.concurrent.ForkJoinPool.CTL, r4, ((r4 - java9.util.concurrent.ForkJoinPool.RC_UNIT) & java9.util.concurrent.ForkJoinPool.UC_MASK) | (((long) r15) & java9.util.concurrent.ForkJoinPool.SP_MASK)) == false) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00f4, code lost:
    
        r15 = r24.stackPred;
        r24.source = -1073741824;
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00fd, code lost:
    
        if (r24.phase < 0) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00ff, code lost:
    
        r24.source = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0106, code lost:
    
        r0 = r23.mode;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0108, code lost:
    
        if (r0 >= 0) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x010a, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x010b, code lost:
    
        r4 = r23.ctl;
        r1 = (65535 & r0) + ((int) (r4 >> 48));
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0117, code lost:
    
        if (r1 > 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x011c, code lost:
    
        if ((r0 & 262144) == 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x011e, code lost:
    
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0123, code lost:
    
        if (tryTerminate(false, false) == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0125, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0126, code lost:
    
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0127, code lost:
    
        r17 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x012d, code lost:
    
        if ((r17 & 1) != 0) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x012f, code lost:
    
        java.lang.Thread.interrupted();
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0135, code lost:
    
        if (r1 > 0) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0137, code lost:
    
        if (r15 == 0) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x013a, code lost:
    
        if (r10 != ((int) r4)) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x013c, code lost:
    
        r0 = r23.keepAlive + java.lang.System.currentTimeMillis();
        java.util.concurrent.locks.LockSupport.parkUntil(r23, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x014a, code lost:
    
        if (r23.ctl != r4) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0155, code lost:
    
        if ((r0 - java.lang.System.currentTimeMillis()) > java9.util.concurrent.ForkJoinPool.TIMEOUT_SLOP) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0171, code lost:
    
        if (java9.util.concurrent.ForkJoinPool.U.compareAndSwapLong(r23, java9.util.concurrent.ForkJoinPool.CTL, r4, ((r4 - java9.util.concurrent.ForkJoinPool.TC_UNIT) & java9.util.concurrent.ForkJoinPool.UC_MASK) | (((long) r15) & java9.util.concurrent.ForkJoinPool.SP_MASK)) == false) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0173, code lost:
    
        r24.phase = 1073741824;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0177, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0178, code lost:
    
        java.util.concurrent.locks.LockSupport.park(r23);
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x017d, code lost:
    
        r2 = r17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final void runWorker(java9.util.concurrent.ForkJoinPool.WorkQueue r24) {
        /*
            Method dump skipped, instruction units count: 386
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: java9.util.concurrent.ForkJoinPool.runWorker(java9.util.concurrent.ForkJoinPool$WorkQueue):void");
    }

    final int awaitJoin(WorkQueue workQueue, ForkJoinTask<?> forkJoinTask, long j) {
        long millis;
        WorkQueue workQueue2;
        ForkJoinTask<?>[] forkJoinTaskArr;
        int length;
        int iLocalHelpCC;
        if (workQueue == null || forkJoinTask == null) {
            return 0;
        }
        if ((forkJoinTask instanceof CountedCompleter) && (iLocalHelpCC = workQueue.localHelpCC((CountedCompleter) forkJoinTask, 0)) < 0) {
            return iLocalHelpCC;
        }
        workQueue.tryRemoveAndExec(forkJoinTask);
        int i = workQueue.source;
        int i2 = workQueue.id;
        int i3 = forkJoinTask.status;
        while (i3 >= 0) {
            boolean z = true;
            int iNextSecondarySeed = TLRandom.nextSecondarySeed() | 1;
            WorkQueue[] workQueueArr = this.workQueues;
            if (workQueueArr != null) {
                int length2 = workQueueArr.length;
                int i4 = length2 - 1;
                for (int i5 = -length2; i5 < length2; i5 += 2) {
                    int i6 = (iNextSecondarySeed + i5) & i4;
                    if (i6 >= 0 && i6 < length2 && (workQueue2 = workQueueArr[i6]) != null && workQueue2.source == i2) {
                        int i7 = workQueue2.base;
                        if (i7 - workQueue2.top < 0 && (forkJoinTaskArr = workQueue2.array) != null && (length = forkJoinTaskArr.length) > 0) {
                            int i8 = workQueue2.id;
                            long j2 = (((long) ((length - 1) & i7)) << ASHIFT) + ((long) ABASE);
                            Unsafe unsafe = U;
                            ForkJoinTask forkJoinTask2 = (ForkJoinTask) unsafe.getObjectVolatile(forkJoinTaskArr, j2);
                            if (forkJoinTask2 != null) {
                                int i9 = i7 + 1;
                                if (i7 == workQueue2.base && i2 == workQueue2.source && LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(unsafe, forkJoinTaskArr, j2, forkJoinTask2, null)) {
                                    workQueue2.base = i9;
                                    workQueue.source = i8;
                                    forkJoinTask2.doExec();
                                    workQueue.source = i;
                                }
                            }
                        }
                    }
                }
                z = false;
            } else {
                z = false;
            }
            int i10 = forkJoinTask.status;
            if (i10 < 0) {
                return i10;
            }
            if (!z) {
                if (j == 0) {
                    millis = 0;
                } else {
                    long jNanoTime = j - System.nanoTime();
                    if (jNanoTime <= 0) {
                        return i10;
                    }
                    millis = TimeUnit.NANOSECONDS.toMillis(jNanoTime);
                    if (millis <= 0) {
                        millis = 1;
                    }
                }
                int iTryCompensate = tryCompensate(workQueue);
                if (iTryCompensate != 0) {
                    forkJoinTask.internalWait(millis);
                    getAndAddLong(this, CTL, iTryCompensate > 0 ? RC_UNIT : 0L);
                }
                i10 = forkJoinTask.status;
            }
            i3 = i10;
        }
        return i3;
    }

    final void helpQuiescePool(WorkQueue workQueue) {
        boolean z;
        boolean z2;
        int i;
        WorkQueue workQueue2;
        ForkJoinTask<?>[] forkJoinTaskArr;
        int length;
        int i2 = workQueue.source;
        int i3 = workQueue.id & 65536;
        byte b = -1;
        int i4 = i2;
        byte b2 = -1;
        while (true) {
            if (i3 != 0) {
                workQueue.localPollAndExec(0);
            } else {
                workQueue.localPopAndExec(0);
            }
            if (b2 == b && workQueue.phase >= 0) {
                b2 = 1;
            }
            int iNextSecondarySeed = TLRandom.nextSecondarySeed();
            WorkQueue[] workQueueArr = this.workQueues;
            long j = RC_UNIT;
            if (workQueueArr != null) {
                int length2 = workQueueArr.length;
                int i5 = length2 - 1;
                int i6 = length2;
                z2 = true;
                while (true) {
                    if (i6 <= 0) {
                        z = true;
                        break;
                    }
                    int i7 = (iNextSecondarySeed - i6) & i5;
                    if (i7 >= 0 && i7 < length2 && (workQueue2 = workQueueArr[i7]) != null) {
                        int i8 = workQueue2.base;
                        if (i8 - workQueue2.top < 0 && (forkJoinTaskArr = workQueue2.array) != null && (length = forkJoinTaskArr.length) > 0) {
                            int i9 = workQueue2.id;
                            if (b2 == 0) {
                                getAndAddLong(this, CTL, j);
                                b2 = 1;
                            }
                            long j2 = (((long) ((length - 1) & i8)) << ASHIFT) + ((long) ABASE);
                            Unsafe unsafe = U;
                            ForkJoinTask forkJoinTask = (ForkJoinTask) unsafe.getObjectVolatile(forkJoinTaskArr, j2);
                            if (forkJoinTask != null) {
                                int i10 = i8 + 1;
                                if (i8 == workQueue2.base && LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(unsafe, forkJoinTaskArr, j2, forkJoinTask, null)) {
                                    workQueue2.base = i10;
                                    workQueue.source = workQueue2.id;
                                    forkJoinTask.doExec();
                                    workQueue.source = i2;
                                    i4 = i2;
                                }
                            }
                            z = false;
                            z2 = false;
                        } else if ((workQueue2.source & 1073741824) == 0) {
                            z2 = false;
                        }
                    }
                    i6--;
                    j = RC_UNIT;
                }
            } else {
                z = true;
                z2 = true;
            }
            if (z2) {
                break;
            }
            if (z) {
                if (i4 != 1073741824) {
                    workQueue.source = 1073741824;
                    i = 1073741824;
                } else {
                    i = i4;
                }
                if (b2 == 1) {
                    getAndAddLong(this, CTL, RC_MASK);
                    i4 = i;
                    b2 = 0;
                } else {
                    i4 = i;
                }
            }
            b = -1;
        }
        if (b2 == 0) {
            getAndAddLong(this, CTL, RC_UNIT);
        }
        workQueue.source = i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x003d, code lost:
    
        r14 = (((long) ((r11 - 1) & r10)) << java9.util.concurrent.ForkJoinPool.ASHIFT) + ((long) java9.util.concurrent.ForkJoinPool.ABASE);
        r12 = java9.util.concurrent.ForkJoinPool.U;
        r1 = (java9.util.concurrent.ForkJoinTask) r12.getObjectVolatile(r13, r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0052, code lost:
    
        if (r1 == null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0054, code lost:
    
        r2 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0058, code lost:
    
        if (r10 != r9.base) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0062, code lost:
    
        if (androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(r12, r13, r14, r1, null) == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0064, code lost:
    
        r9.base = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0066, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0002, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java9.util.concurrent.ForkJoinTask<?> pollScan(boolean r19) {
        /*
            r18 = this;
            r0 = r18
        L2:
            int r1 = r0.mode
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 != 0) goto L71
            java9.util.concurrent.ForkJoinPool$WorkQueue[] r1 = r0.workQueues
            if (r1 == 0) goto L71
            int r2 = r1.length
            if (r2 <= 0) goto L71
            int r2 = r2 + (-1)
            int r3 = java9.util.concurrent.TLRandom.nextSecondarySeed()
            int r4 = r3 >>> 16
            if (r19 == 0) goto L22
            r3 = r3 & (-2)
            r3 = r3 & r2
            r4 = r4 & (-2)
            r4 = r4 | 2
            goto L25
        L22:
            r3 = r3 & r2
            r4 = r4 | 1
        L25:
            r5 = 0
            r6 = r3
            r7 = r5
            r8 = r7
        L29:
            r9 = r1[r6]
            if (r9 == 0) goto L67
            int r10 = r9.base
            int r7 = r7 + r10
            int r11 = r9.top
            int r11 = r10 - r11
            if (r11 >= 0) goto L67
            java9.util.concurrent.ForkJoinTask<?>[] r13 = r9.array
            if (r13 == 0) goto L67
            int r11 = r13.length
            if (r11 <= 0) goto L67
            int r11 = r11 + (-1)
            r1 = r11 & r10
            long r1 = (long) r1
            int r3 = java9.util.concurrent.ForkJoinPool.ASHIFT
            long r1 = r1 << r3
            int r3 = java9.util.concurrent.ForkJoinPool.ABASE
            long r3 = (long) r3
            long r14 = r1 + r3
            sun.misc.Unsafe r12 = java9.util.concurrent.ForkJoinPool.U
            java.lang.Object r1 = r12.getObjectVolatile(r13, r14)
            java9.util.concurrent.ForkJoinTask r1 = (java9.util.concurrent.ForkJoinTask) r1
            if (r1 == 0) goto L2
            int r2 = r10 + 1
            int r3 = r9.base
            if (r10 != r3) goto L2
            r17 = 0
            r16 = r1
            boolean r3 = androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(r12, r13, r14, r16, r17)
            if (r3 == 0) goto L2
            r9.base = r2
            return r1
        L67:
            int r6 = r6 + r4
            r6 = r6 & r2
            if (r6 != r3) goto L29
            if (r8 != r7) goto L6e
            goto L71
        L6e:
            r8 = r7
            r7 = r5
            goto L29
        L71:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: java9.util.concurrent.ForkJoinPool.pollScan(boolean):java9.util.concurrent.ForkJoinTask");
    }

    final ForkJoinTask<?> nextTaskFor(WorkQueue workQueue) {
        if (workQueue != null) {
            ForkJoinTask<?> forkJoinTaskPoll = (workQueue.id & 65536) != 0 ? workQueue.poll() : workQueue.pop();
            if (forkJoinTaskPoll != null) {
                return forkJoinTaskPoll;
            }
        }
        return pollScan(false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x00bc, code lost:
    
        throw new java.util.concurrent.RejectedExecutionException();
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final void externalPush(java9.util.concurrent.ForkJoinTask<?> r10) {
        /*
            r9 = this;
            int r0 = java9.util.concurrent.TLRandom.getProbe()
            if (r0 != 0) goto Ld
            java9.util.concurrent.TLRandom.localInit()
            int r0 = java9.util.concurrent.TLRandom.getProbe()
        Ld:
            int r1 = r9.mode
            java9.util.concurrent.ForkJoinPool$WorkQueue[] r2 = r9.workQueues
            r3 = 262144(0x40000, float:3.67342E-40)
            r1 = r1 & r3
            if (r1 != 0) goto Lb7
            if (r2 == 0) goto Lb7
            int r1 = r2.length
            if (r1 <= 0) goto Lb7
            int r1 = r1 + (-1)
            r1 = r1 & r0
            r1 = r1 & 126(0x7e, float:1.77E-43)
            r1 = r2[r1]
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L5e
            java.lang.String r1 = r9.workerNamePrefix
            r4 = 1073741824(0x40000000, float:2.0)
            r5 = r0 | r4
            r6 = -65538(0xfffffffffffefffe, float:NaN)
            r5 = r5 & r6
            java9.util.concurrent.ForkJoinPool$WorkQueue r6 = new java9.util.concurrent.ForkJoinPool$WorkQueue
            r7 = 0
            r6.<init>(r9, r7)
            r6.id = r5
            r6.source = r4
            r6.phase = r3
            if (r1 == 0) goto L5a
            monitor-enter(r1)
            java9.util.concurrent.ForkJoinPool$WorkQueue[] r4 = r9.workQueues     // Catch: java.lang.Throwable -> L57
            if (r4 == 0) goto L53
            int r7 = r4.length     // Catch: java.lang.Throwable -> L57
            if (r7 <= 0) goto L53
            int r7 = r7 + (-1)
            r5 = r5 & r7
            r5 = r5 & 126(0x7e, float:1.77E-43)
            r7 = r4[r5]     // Catch: java.lang.Throwable -> L57
            if (r7 != 0) goto L53
            r4[r5] = r6     // Catch: java.lang.Throwable -> L57
            r4 = r3
            goto L54
        L53:
            r4 = r2
        L54:
            r5 = r4
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L57
            goto L5c
        L57:
            r10 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L57
            throw r10
        L5a:
            r4 = r2
            r5 = r4
        L5c:
            r1 = r6
            goto L8f
        L5e:
            boolean r4 = r1.tryLockSharedQueue()
            if (r4 == 0) goto L8d
            int r4 = r1.base
            int r5 = r1.top
            java9.util.concurrent.ForkJoinTask<?>[] r6 = r1.array
            if (r6 == 0) goto L8a
            int r7 = r6.length
            if (r7 <= 0) goto L8a
            int r7 = r7 + (-1)
            int r4 = r4 - r5
            int r8 = r7 + r4
            if (r8 <= 0) goto L8a
            r7 = r7 & r5
            r6[r7] = r10
            int r6 = r5 + 1
            r1.top = r6
            r1.phase = r2
            if (r4 >= 0) goto L88
            int r4 = r1.base
            int r4 = r4 - r5
            r5 = -1
            if (r4 >= r5) goto L88
            goto Lb0
        L88:
            r5 = r2
            goto L8b
        L8a:
            r5 = r3
        L8b:
            r4 = r3
            goto L8f
        L8d:
            r4 = r2
            r5 = r4
        L8f:
            if (r4 == 0) goto Lb1
            if (r5 == 0) goto Lad
            r1.growArray()     // Catch: java.lang.Throwable -> La9
            int r0 = r1.top     // Catch: java.lang.Throwable -> La9
            java9.util.concurrent.ForkJoinTask<?>[] r4 = r1.array     // Catch: java.lang.Throwable -> La9
            if (r4 == 0) goto La6
            int r5 = r4.length     // Catch: java.lang.Throwable -> La9
            if (r5 <= 0) goto La6
            int r5 = r5 - r3
            r5 = r5 & r0
            r4[r5] = r10     // Catch: java.lang.Throwable -> La9
            int r0 = r0 + r3
            r1.top = r0     // Catch: java.lang.Throwable -> La9
        La6:
            r1.phase = r2
            goto Lad
        La9:
            r10 = move-exception
            r1.phase = r2
            throw r10
        Lad:
            r9.signalWork()
        Lb0:
            return
        Lb1:
            int r0 = java9.util.concurrent.TLRandom.advanceProbe(r0)
            goto Ld
        Lb7:
            java.util.concurrent.RejectedExecutionException r10 = new java.util.concurrent.RejectedExecutionException
            r10.<init>()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: java9.util.concurrent.ForkJoinPool.externalPush(java9.util.concurrent.ForkJoinTask):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private <T> java9.util.concurrent.ForkJoinTask<T> externalSubmit(java9.util.concurrent.ForkJoinTask<T> r3) {
        /*
            r2 = this;
            java9.util.Objects.requireNonNull(r3)
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            boolean r1 = r0 instanceof java9.util.concurrent.ForkJoinWorkerThread
            if (r1 == 0) goto L19
            java9.util.concurrent.ForkJoinWorkerThread r0 = (java9.util.concurrent.ForkJoinWorkerThread) r0
            java9.util.concurrent.ForkJoinPool r1 = r0.pool
            if (r1 != r2) goto L19
            java9.util.concurrent.ForkJoinPool$WorkQueue r0 = r0.workQueue
            if (r0 == 0) goto L19
            r0.push(r3)
            goto L1c
        L19:
            r2.externalPush(r3)
        L1c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: java9.util.concurrent.ForkJoinPool.externalSubmit(java9.util.concurrent.ForkJoinTask):java9.util.concurrent.ForkJoinTask");
    }

    static WorkQueue commonSubmitterQueue() {
        WorkQueue[] workQueueArr;
        int length;
        ForkJoinPool forkJoinPool = common;
        int probe = TLRandom.getProbe();
        if (forkJoinPool == null || (workQueueArr = forkJoinPool.workQueues) == null || (length = workQueueArr.length) <= 0) {
            return null;
        }
        return workQueueArr[probe & (length - 1) & 126];
    }

    final boolean tryExternalUnpush(ForkJoinTask<?> forkJoinTask) {
        int length;
        WorkQueue workQueue;
        int probe = TLRandom.getProbe();
        WorkQueue[] workQueueArr = this.workQueues;
        return workQueueArr != null && (length = workQueueArr.length) > 0 && (workQueue = workQueueArr[(probe & (length - 1)) & 126]) != null && workQueue.trySharedUnpush(forkJoinTask);
    }

    final int externalHelpComplete(CountedCompleter<?> countedCompleter, int i) {
        int length;
        WorkQueue workQueue;
        int probe = TLRandom.getProbe();
        WorkQueue[] workQueueArr = this.workQueues;
        if (workQueueArr == null || (length = workQueueArr.length) <= 0 || (workQueue = workQueueArr[probe & (length - 1) & 126]) == null) {
            return 0;
        }
        return workQueue.sharedHelpCC(countedCompleter, i);
    }

    final int helpComplete(WorkQueue workQueue, CountedCompleter<?> countedCompleter, int i) {
        if (workQueue == null) {
            return 0;
        }
        return workQueue.localHelpCC(countedCompleter, i);
    }

    static int getSurplusQueuedTaskCount() {
        ForkJoinWorkerThread forkJoinWorkerThread;
        ForkJoinPool forkJoinPool;
        WorkQueue workQueue;
        Thread threadCurrentThread = Thread.currentThread();
        int i = 0;
        if (!(threadCurrentThread instanceof ForkJoinWorkerThread) || (forkJoinPool = (forkJoinWorkerThread = (ForkJoinWorkerThread) threadCurrentThread).pool) == null || (workQueue = forkJoinWorkerThread.workQueue) == null) {
            return 0;
        }
        int i2 = forkJoinPool.mode & 65535;
        int i3 = ((int) (forkJoinPool.ctl >> 48)) + i2;
        int i4 = workQueue.top - workQueue.base;
        int i5 = i2 >>> 1;
        if (i3 <= i5) {
            int i6 = i5 >>> 1;
            if (i3 > i6) {
                i = 1;
            } else {
                int i7 = i6 >>> 1;
                i = i3 > i7 ? 2 : i3 > (i7 >>> 1) ? 4 : 8;
            }
        }
        return i4 - i;
    }

    private boolean tryTerminate(boolean z, boolean z2) {
        int i;
        int i2;
        while (true) {
            int i3 = this.mode;
            int i4 = 0;
            if ((i3 & 262144) != 0) {
                while (true) {
                    int i5 = this.mode;
                    int i6 = 65535;
                    long j = 0;
                    int i7 = 1;
                    if ((i5 & Integer.MIN_VALUE) != 0) {
                        while ((this.mode & 524288) == 0) {
                            long j2 = 0;
                            while (true) {
                                long j3 = this.ctl;
                                WorkQueue[] workQueueArr = this.workQueues;
                                if (workQueueArr != null) {
                                    for (int i8 = i4; i8 < workQueueArr.length; i8++) {
                                        WorkQueue workQueue = workQueueArr[i8];
                                        if (workQueue != null) {
                                            ForkJoinWorkerThread forkJoinWorkerThread = workQueue.owner;
                                            workQueue.cancelAll();
                                            if (forkJoinWorkerThread != null) {
                                                try {
                                                    forkJoinWorkerThread.interrupt();
                                                } catch (Throwable unused) {
                                                }
                                            }
                                            j3 += (((long) workQueue.phase) << 32) + ((long) workQueue.base);
                                        }
                                    }
                                }
                                i = this.mode;
                                i2 = i & 524288;
                                if (i2 != 0) {
                                    break;
                                }
                                if (this.workQueues == workQueueArr) {
                                    if (j2 == j3) {
                                        break;
                                    }
                                    j2 = j3;
                                }
                                i4 = 0;
                            }
                            if (i2 != 0 || (i & 65535) + ((short) (this.ctl >>> 32)) > 0) {
                                return true;
                            }
                            if (U.compareAndSwapInt(this, MODE, i, i | 524288)) {
                                synchronized (this) {
                                    notifyAll();
                                }
                                return true;
                            }
                            i4 = 0;
                        }
                        return true;
                    }
                    if (!z) {
                        while (true) {
                            long j4 = this.ctl;
                            WorkQueue[] workQueueArr2 = this.workQueues;
                            char c = '0';
                            if ((i5 & i6) + ((int) (j4 >> 48)) <= 0) {
                                if (workQueueArr2 != null) {
                                    int i9 = i4;
                                    while (i9 < workQueueArr2.length) {
                                        WorkQueue workQueue2 = workQueueArr2[i9];
                                        if (workQueue2 != null) {
                                            int i10 = workQueue2.source;
                                            int i11 = workQueue2.phase;
                                            int i12 = workQueue2.id;
                                            int i13 = workQueue2.base;
                                            if (i13 != workQueue2.top || ((i12 & 1) == i7 && (i10 >= 0 || i11 >= 0))) {
                                                i7 = 1;
                                                break;
                                            }
                                            j4 += (((long) i10) << c) + (((long) i11) << 32) + (((long) i13) << 16) + ((long) i12);
                                        }
                                        i9++;
                                        c = '0';
                                        i7 = 1;
                                    }
                                    i7 = 0;
                                } else {
                                    i7 = 0;
                                }
                            }
                            i5 = this.mode;
                            if ((i5 & Integer.MIN_VALUE) != 0) {
                                i4 = 0;
                                break;
                            }
                            if (i7 != 0) {
                                return false;
                            }
                            i4 = 0;
                            if (this.workQueues == workQueueArr2) {
                                if (j == j4) {
                                    break;
                                }
                                j = j4;
                            }
                            i6 = 65535;
                            i7 = 1;
                        }
                    }
                    int i14 = i5;
                    if ((i14 & Integer.MIN_VALUE) == 0) {
                        U.compareAndSwapInt(this, MODE, i14, i14 | Integer.MIN_VALUE);
                    }
                }
            } else {
                if (!z2 || this == common) {
                    break;
                }
                U.compareAndSwapInt(this, MODE, i3, i3 | 262144);
            }
        }
        return false;
    }

    public ForkJoinPool() {
        this(Math.min(MAX_CAP, Runtime.getRuntime().availableProcessors()), defaultForkJoinWorkerThreadFactory, null, false, 0, MAX_CAP, 1, null, 60000L, TimeUnit.MILLISECONDS);
    }

    public ForkJoinPool(int i) {
        this(i, defaultForkJoinWorkerThreadFactory, null, false, 0, MAX_CAP, 1, null, 60000L, TimeUnit.MILLISECONDS);
    }

    public ForkJoinPool(int i, ForkJoinWorkerThreadFactory forkJoinWorkerThreadFactory, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, boolean z) {
        this(i, forkJoinWorkerThreadFactory, uncaughtExceptionHandler, z, 0, MAX_CAP, 1, null, 60000L, TimeUnit.MILLISECONDS);
    }

    public ForkJoinPool(int i, ForkJoinWorkerThreadFactory forkJoinWorkerThreadFactory, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, boolean z, int i2, int i3, int i4, Predicate<? super ForkJoinPool> predicate, long j, TimeUnit timeUnit) {
        if (i <= 0 || i > MAX_CAP || i3 < i || j <= 0) {
            throw new IllegalArgumentException();
        }
        Objects.requireNonNull(forkJoinWorkerThreadFactory);
        long jMax = Math.max(timeUnit.toMillis(j), TIMEOUT_SLOP);
        long j2 = ((((long) (-Math.min(Math.max(i2, i), MAX_CAP))) << 32) & TC_MASK) | ((((long) (-i)) << 48) & RC_MASK);
        int i5 = (z ? 65536 : 0) | i;
        int iMin = ((Math.min(i3, MAX_CAP) - i) << 16) | ((Math.min(Math.max(i4, 0), MAX_CAP) - i) & 65535);
        int i6 = i > 1 ? i - 1 : 1;
        int i7 = i6 | (i6 >>> 1);
        int i8 = i7 | (i7 >>> 2);
        int i9 = i8 | (i8 >>> 4);
        int i10 = i9 | (i9 >>> 8);
        this.workerNamePrefix = "ForkJoinPool-" + nextPoolId() + "-worker-";
        this.workQueues = new WorkQueue[((i10 | (i10 >>> 16)) + 1) << 1];
        this.factory = forkJoinWorkerThreadFactory;
        this.ueh = uncaughtExceptionHandler;
        this.saturate = predicate;
        this.keepAlive = jMax;
        this.bounds = iMin;
        this.mode = i5;
        this.ctl = j2;
        checkPermission();
    }

    private static Object newInstanceFromSystemProperty(String str) throws Exception {
        String property = System.getProperty(str);
        if (property == null) {
            return null;
        }
        return ClassLoader.getSystemClassLoader().loadClass(property).getConstructor(new Class[0]).newInstance(new Object[0]);
    }

    private ForkJoinPool(byte b) {
        ForkJoinWorkerThreadFactory innocuousForkJoinWorkerThreadFactory;
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        try {
            String property = System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism");
            iAvailableProcessors = property != null ? Integer.parseInt(property) : -1;
            innocuousForkJoinWorkerThreadFactory = (ForkJoinWorkerThreadFactory) newInstanceFromSystemProperty("java.util.concurrent.ForkJoinPool.common.threadFactory");
        } catch (Exception unused) {
            innocuousForkJoinWorkerThreadFactory = null;
        }
        try {
            uncaughtExceptionHandler = (Thread.UncaughtExceptionHandler) newInstanceFromSystemProperty("java.util.concurrent.ForkJoinPool.common.exceptionHandler");
        } catch (Exception unused2) {
            uncaughtExceptionHandler = null;
        }
        if (innocuousForkJoinWorkerThreadFactory == null) {
            if (System.getSecurityManager() == null) {
                innocuousForkJoinWorkerThreadFactory = defaultForkJoinWorkerThreadFactory;
            } else {
                innocuousForkJoinWorkerThreadFactory = new InnocuousForkJoinWorkerThreadFactory();
            }
        }
        if (iAvailableProcessors < 0 && (iAvailableProcessors = Runtime.getRuntime().availableProcessors() - 1) <= 0) {
            iAvailableProcessors = 1;
        }
        iAvailableProcessors = iAvailableProcessors > MAX_CAP ? MAX_CAP : iAvailableProcessors;
        long j = -iAvailableProcessors;
        long j2 = ((j << 48) & RC_MASK) | ((j << 32) & TC_MASK);
        int i = ((1 - iAvailableProcessors) & 65535) | (COMMON_MAX_SPARES << 16);
        int i2 = iAvailableProcessors > 1 ? iAvailableProcessors - 1 : 1;
        int i3 = i2 | (i2 >>> 1);
        int i4 = i3 | (i3 >>> 2);
        int i5 = i4 | (i4 >>> 4);
        int i6 = i5 | (i5 >>> 8);
        this.workerNamePrefix = "ForkJoinPool.commonPool-worker-";
        this.workQueues = new WorkQueue[((i6 | (i6 >>> 16)) + 1) << 1];
        this.factory = innocuousForkJoinWorkerThreadFactory;
        this.ueh = uncaughtExceptionHandler;
        this.saturate = null;
        this.keepAlive = 60000L;
        this.bounds = i;
        this.mode = iAvailableProcessors;
        this.ctl = j2;
    }

    public static ForkJoinPool commonPool() {
        return common;
    }

    public <T> T invoke(ForkJoinTask<T> forkJoinTask) {
        externalSubmit((ForkJoinTask) Objects.requireNonNull(forkJoinTask));
        return forkJoinTask.join();
    }

    public void execute(ForkJoinTask<?> forkJoinTask) {
        externalSubmit(forkJoinTask);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        ForkJoinTask runnableExecuteAction;
        Objects.requireNonNull(runnable);
        if (runnable instanceof ForkJoinTask) {
            runnableExecuteAction = (ForkJoinTask) runnable;
        } else {
            runnableExecuteAction = new ForkJoinTask.RunnableExecuteAction(runnable);
        }
        externalSubmit(runnableExecuteAction);
    }

    public <T> ForkJoinTask<T> submit(ForkJoinTask<T> forkJoinTask) {
        return externalSubmit(forkJoinTask);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> ForkJoinTask<T> submit(Callable<T> callable) {
        return externalSubmit(new ForkJoinTask.AdaptedCallable(callable));
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> ForkJoinTask<T> submit(Runnable runnable, T t) {
        return externalSubmit(new ForkJoinTask.AdaptedRunnable(runnable, t));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public ForkJoinTask<?> submit(Runnable runnable) {
        Objects.requireNonNull(runnable);
        return externalSubmit(runnable instanceof ForkJoinTask ? (ForkJoinTask) runnable : new ForkJoinTask.AdaptedRunnableAction(runnable));
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        try {
            Iterator<? extends Callable<T>> it = collection.iterator();
            while (it.hasNext()) {
                ForkJoinTask.AdaptedCallable adaptedCallable = new ForkJoinTask.AdaptedCallable(it.next());
                arrayList.add(adaptedCallable);
                externalSubmit(adaptedCallable);
            }
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((ForkJoinTask) arrayList.get(i)).quietlyJoin();
            }
            return arrayList;
        } catch (Throwable th) {
            int size2 = arrayList.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ((Future) arrayList.get(i2)).cancel(false);
            }
            throw th;
        }
    }

    public ForkJoinWorkerThreadFactory getFactory() {
        return this.factory;
    }

    public Thread.UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return this.ueh;
    }

    public int getParallelism() {
        int i = this.mode & 65535;
        if (i > 0) {
            return i;
        }
        return 1;
    }

    public static int getCommonPoolParallelism() {
        return COMMON_PARALLELISM;
    }

    public int getPoolSize() {
        return (this.mode & 65535) + ((short) (this.ctl >>> 32));
    }

    public boolean getAsyncMode() {
        return (this.mode & 65536) != 0;
    }

    public int getRunningThreadCount() {
        WorkQueue[] workQueueArr = this.workQueues;
        int i = 0;
        if (workQueueArr != null) {
            for (int i2 = 1; i2 < workQueueArr.length; i2 += 2) {
                WorkQueue workQueue = workQueueArr[i2];
                if (workQueue != null && workQueue.isApparentlyUnblocked()) {
                    i++;
                }
            }
        }
        return i;
    }

    public int getActiveThreadCount() {
        int i = (this.mode & 65535) + ((int) (this.ctl >> 48));
        if (i <= 0) {
            return 0;
        }
        return i;
    }

    public boolean isQuiescent() {
        while (true) {
            long j = this.ctl;
            int i = this.mode;
            int i2 = 65535 & i;
            int i3 = ((short) (j >>> 32)) + i2;
            int i4 = i2 + ((int) (j >> 48));
            if ((i & (-2146959360)) != 0) {
                return true;
            }
            if (i4 > 0) {
                return false;
            }
            WorkQueue[] workQueueArr = this.workQueues;
            if (workQueueArr != null) {
                for (int i5 = 1; i5 < workQueueArr.length; i5 += 2) {
                    WorkQueue workQueue = workQueueArr[i5];
                    if (workQueue != null) {
                        if ((workQueue.source & 1073741824) == 0) {
                            return false;
                        }
                        i3--;
                    }
                }
            }
            if (i3 == 0 && this.ctl == j) {
                return true;
            }
        }
    }

    public long getStealCount() {
        long j = this.stealCount;
        WorkQueue[] workQueueArr = this.workQueues;
        if (workQueueArr != null) {
            for (int i = 1; i < workQueueArr.length; i += 2) {
                WorkQueue workQueue = workQueueArr[i];
                if (workQueue != null) {
                    j += ((long) workQueue.nsteals) & SP_MASK;
                }
            }
        }
        return j;
    }

    public long getQueuedTaskCount() {
        WorkQueue[] workQueueArr = this.workQueues;
        long jQueueSize = 0;
        if (workQueueArr != null) {
            for (int i = 1; i < workQueueArr.length; i += 2) {
                WorkQueue workQueue = workQueueArr[i];
                if (workQueue != null) {
                    jQueueSize += (long) workQueue.queueSize();
                }
            }
        }
        return jQueueSize;
    }

    public int getQueuedSubmissionCount() {
        WorkQueue[] workQueueArr = this.workQueues;
        if (workQueueArr == null) {
            return 0;
        }
        int iQueueSize = 0;
        for (int i = 0; i < workQueueArr.length; i += 2) {
            WorkQueue workQueue = workQueueArr[i];
            if (workQueue != null) {
                iQueueSize += workQueue.queueSize();
            }
        }
        return iQueueSize;
    }

    public boolean hasQueuedSubmissions() {
        WorkQueue[] workQueueArr = this.workQueues;
        if (workQueueArr != null) {
            for (int i = 0; i < workQueueArr.length; i += 2) {
                WorkQueue workQueue = workQueueArr[i];
                if (workQueue != null && !workQueue.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    protected ForkJoinTask<?> pollSubmission() {
        return pollScan(true);
    }

    protected int drainTasksTo(Collection<? super ForkJoinTask<?>> collection) {
        WorkQueue[] workQueueArr = this.workQueues;
        if (workQueueArr == null) {
            return 0;
        }
        int i = 0;
        for (WorkQueue workQueue : workQueueArr) {
            if (workQueue != null) {
                while (true) {
                    ForkJoinTask<?> forkJoinTaskPoll = workQueue.poll();
                    if (forkJoinTaskPoll != null) {
                        collection.add(forkJoinTaskPoll);
                        i++;
                    }
                }
            }
        }
        return i;
    }

    public String toString() {
        int i;
        long j = this.stealCount;
        WorkQueue[] workQueueArr = this.workQueues;
        long j2 = 0;
        long j3 = 0;
        if (workQueueArr != null) {
            i = 0;
            for (int i2 = 0; i2 < workQueueArr.length; i2++) {
                WorkQueue workQueue = workQueueArr[i2];
                if (workQueue != null) {
                    int iQueueSize = workQueue.queueSize();
                    if ((i2 & 1) == 0) {
                        j3 += (long) iQueueSize;
                    } else {
                        j2 += (long) iQueueSize;
                        j += ((long) workQueue.nsteals) & SP_MASK;
                        if (workQueue.isApparentlyUnblocked()) {
                            i++;
                        }
                    }
                }
            }
        } else {
            i = 0;
        }
        int i3 = this.mode;
        int i4 = 65535 & i3;
        int i5 = ((short) (r10 >>> 32)) + i4;
        int i6 = ((int) (this.ctl >> 48)) + i4;
        return super.toString() + "[" + ((524288 & i3) != 0 ? "Terminated" : (Integer.MIN_VALUE & i3) != 0 ? "Terminating" : (i3 & 262144) != 0 ? "Shutting down" : "Running") + ", parallelism = " + i4 + ", size = " + i5 + ", active = " + (i6 >= 0 ? i6 : 0) + ", running = " + i + ", steals = " + j + ", tasks = " + j2 + ", submissions = " + j3 + "]";
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        checkPermission();
        tryTerminate(false, true);
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        checkPermission();
        tryTerminate(true, true);
        return Collections.emptyList();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return (this.mode & 524288) != 0;
    }

    public boolean isTerminating() {
        int i = this.mode;
        return (Integer.MIN_VALUE & i) != 0 && (i & 524288) == 0;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return (this.mode & 262144) != 0;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        if (this == common) {
            awaitQuiescence(j, timeUnit);
            return false;
        }
        long nanos = timeUnit.toNanos(j);
        if (isTerminated()) {
            return true;
        }
        if (nanos <= 0) {
            return false;
        }
        long jNanoTime = System.nanoTime() + nanos;
        synchronized (this) {
            while (!isTerminated()) {
                if (nanos <= 0) {
                    return false;
                }
                long millis = TimeUnit.NANOSECONDS.toMillis(nanos);
                if (millis <= 0) {
                    millis = 1;
                }
                wait(millis);
                nanos = jNanoTime - System.nanoTime();
            }
            return true;
        }
    }

    public boolean awaitQuiescence(long j, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j);
        Thread threadCurrentThread = Thread.currentThread();
        if (threadCurrentThread instanceof ForkJoinWorkerThread) {
            ForkJoinWorkerThread forkJoinWorkerThread = (ForkJoinWorkerThread) threadCurrentThread;
            if (forkJoinWorkerThread.pool == this) {
                helpQuiescePool(forkJoinWorkerThread.workQueue);
                return true;
            }
        }
        long jNanoTime = System.nanoTime();
        while (true) {
            ForkJoinTask<?> forkJoinTaskPollScan = pollScan(false);
            if (forkJoinTaskPollScan != null) {
                forkJoinTaskPollScan.doExec();
            } else {
                if (isQuiescent()) {
                    return true;
                }
                if (System.nanoTime() - jNanoTime > nanos) {
                    return false;
                }
                Thread.yield();
            }
        }
    }

    static void quiesceCommonPool() {
        common.awaitQuiescence(LongCompanionObject.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

    public static void managedBlock(ManagedBlocker managedBlocker) throws InterruptedException {
        ForkJoinWorkerThread forkJoinWorkerThread;
        ForkJoinPool forkJoinPool;
        WorkQueue workQueue;
        Thread threadCurrentThread = Thread.currentThread();
        if ((threadCurrentThread instanceof ForkJoinWorkerThread) && (forkJoinPool = (forkJoinWorkerThread = (ForkJoinWorkerThread) threadCurrentThread).pool) != null && (workQueue = forkJoinWorkerThread.workQueue) != null) {
            while (!managedBlocker.isReleasable()) {
                int iTryCompensate = forkJoinPool.tryCompensate(workQueue);
                if (iTryCompensate != 0) {
                    do {
                        long j = RC_UNIT;
                        try {
                            if (managedBlocker.isReleasable()) {
                                break;
                            }
                        } finally {
                            long j2 = CTL;
                            if (iTryCompensate <= 0) {
                                j = 0;
                            }
                            getAndAddLong(forkJoinPool, j2, j);
                        }
                    } while (!managedBlocker.block());
                    return;
                }
            }
            return;
        }
        while (!managedBlocker.isReleasable() && !managedBlocker.block()) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static void helpAsyncBlocker(java.util.concurrent.Executor r10, java9.util.concurrent.ForkJoinPool.ManagedBlocker r11) {
        /*
            if (r11 == 0) goto L7c
            boolean r0 = r10 instanceof java9.util.concurrent.ForkJoinPool
            if (r0 == 0) goto L7c
            java9.util.concurrent.ForkJoinPool r10 = (java9.util.concurrent.ForkJoinPool) r10
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            boolean r1 = r0 instanceof java9.util.concurrent.ForkJoinWorkerThread
            if (r1 == 0) goto L19
            java9.util.concurrent.ForkJoinWorkerThread r0 = (java9.util.concurrent.ForkJoinWorkerThread) r0
            java9.util.concurrent.ForkJoinPool r1 = r0.pool
            if (r1 != r10) goto L19
            java9.util.concurrent.ForkJoinPool$WorkQueue r10 = r0.workQueue
            goto L2f
        L19:
            int r0 = java9.util.concurrent.TLRandom.getProbe()
            if (r0 == 0) goto L2e
            java9.util.concurrent.ForkJoinPool$WorkQueue[] r10 = r10.workQueues
            if (r10 == 0) goto L2e
            int r1 = r10.length
            if (r1 <= 0) goto L2e
            int r1 = r1 + (-1)
            r0 = r0 & r1
            r0 = r0 & 126(0x7e, float:1.77E-43)
            r10 = r10[r0]
            goto L2f
        L2e:
            r10 = 0
        L2f:
            if (r10 == 0) goto L7c
        L31:
            int r0 = r10.base
            int r1 = r10.top
            java9.util.concurrent.ForkJoinTask<?>[] r3 = r10.array
            if (r3 == 0) goto L7c
            int r1 = r0 - r1
            if (r1 >= 0) goto L7c
            int r2 = r3.length
            if (r2 <= 0) goto L7c
            int r2 = r2 + (-1)
            r2 = r2 & r0
            long r4 = (long) r2
            int r2 = java9.util.concurrent.ForkJoinPool.ASHIFT
            long r4 = r4 << r2
            int r2 = java9.util.concurrent.ForkJoinPool.ABASE
            long r6 = (long) r2
            long r4 = r4 + r6
            sun.misc.Unsafe r2 = java9.util.concurrent.ForkJoinPool.U
            java.lang.Object r6 = r2.getObjectVolatile(r3, r4)
            r8 = r6
            java9.util.concurrent.ForkJoinTask r8 = (java9.util.concurrent.ForkJoinTask) r8
            boolean r6 = r11.isReleasable()
            if (r6 == 0) goto L5b
            goto L7c
        L5b:
            int r9 = r0 + 1
            int r6 = r10.base
            if (r0 != r6) goto L31
            if (r8 != 0) goto L67
            r0 = -1
            if (r1 != r0) goto L31
            goto L7c
        L67:
            boolean r0 = isInstanceOfAsynCompTask(r8)
            if (r0 != 0) goto L6e
            goto L7c
        L6e:
            r7 = 0
            r6 = r8
            boolean r0 = androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(r2, r3, r4, r6, r7)
            if (r0 == 0) goto L31
            r10.base = r9
            r8.doExec()
            goto L31
        L7c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: java9.util.concurrent.ForkJoinPool.helpAsyncBlocker(java.util.concurrent.Executor, java9.util.concurrent.ForkJoinPool$ManagedBlocker):void");
    }

    static boolean isInstanceOfAsynCompTask(ForkJoinTask<?> forkJoinTask) {
        Class<?> cls;
        if (forkJoinTask == null || (cls = ACTCLASS) == null) {
            return false;
        }
        return cls.isAssignableFrom(forkJoinTask.getClass());
    }

    @Override // java.util.concurrent.AbstractExecutorService
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new ForkJoinTask.AdaptedRunnable(runnable, t);
    }

    @Override // java.util.concurrent.AbstractExecutorService
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new ForkJoinTask.AdaptedCallable(callable);
    }

    static {
        Unsafe unsafe = UnsafeAccess.unsafe;
        U = unsafe;
        try {
            CTL = unsafe.objectFieldOffset(ForkJoinPool.class.getDeclaredField("ctl"));
            MODE = unsafe.objectFieldOffset(ForkJoinPool.class.getDeclaredField(ServiceBase.PAYLOAD_MODE));
            ABASE = unsafe.arrayBaseOffset(ForkJoinTask[].class);
            int iArrayIndexScale = unsafe.arrayIndexScale(ForkJoinTask[].class);
            if (((iArrayIndexScale - 1) & iArrayIndexScale) != 0) {
                throw new ExceptionInInitializerError("array index scale not a power of two");
            }
            ASHIFT = 31 - Integer.numberOfLeadingZeros(iArrayIndexScale);
            int i = 256;
            try {
                String property = System.getProperty("java.util.concurrent.ForkJoinPool.common.maximumSpares");
                if (property != null) {
                    i = Integer.parseInt(property);
                }
            } catch (Exception unused) {
            }
            COMMON_MAX_SPARES = i;
            defaultForkJoinWorkerThreadFactory = new DefaultForkJoinWorkerThreadFactory();
            modifyThreadPermission = new RuntimePermission("modifyThread");
            ForkJoinPool forkJoinPool = (ForkJoinPool) AccessController.doPrivileged(new PrivilegedAction<ForkJoinPool>() { // from class: java9.util.concurrent.ForkJoinPool.1
                @Override // java.security.PrivilegedAction
                public ForkJoinPool run() {
                    return new ForkJoinPool((byte) 0);
                }
            });
            common = forkJoinPool;
            COMMON_PARALLELISM = Math.max(forkJoinPool.mode & 65535, 1);
            try {
                ACTCLASS = Class.forName("java9.util.concurrent.CompletableFuture$AsynchronousCompletionTask");
            } catch (Exception unused2) {
                ACTCLASS = null;
            } catch (Throwable th) {
                ACTCLASS = null;
                throw th;
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private static final class InnocuousForkJoinWorkerThreadFactory implements ForkJoinWorkerThreadFactory {
        private static final AccessControlContext ACC = ForkJoinPool.contextWithPermissions(ForkJoinPool.modifyThreadPermission, new RuntimePermission("enableContextClassLoaderOverride"), new RuntimePermission("modifyThreadGroup"), new RuntimePermission("getClassLoader"), new RuntimePermission("setContextClassLoader"));

        private InnocuousForkJoinWorkerThreadFactory() {
        }

        @Override // java9.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory
        public final ForkJoinWorkerThread newThread(final ForkJoinPool forkJoinPool) {
            return (ForkJoinWorkerThread) AccessController.doPrivileged(new PrivilegedAction<ForkJoinWorkerThread>() { // from class: java9.util.concurrent.ForkJoinPool.InnocuousForkJoinWorkerThreadFactory.1
                @Override // java.security.PrivilegedAction
                public ForkJoinWorkerThread run() {
                    return new ForkJoinWorkerThread.InnocuousForkJoinWorkerThread(forkJoinPool);
                }
            }, ACC);
        }
    }

    static final class MemBar {
        private static final long OFF;
        private static final Unsafe U;
        private static final Mock x = new Mock();

        static {
            Unsafe unsafe = UnsafeAccess.unsafe;
            U = unsafe;
            try {
                OFF = unsafe.objectFieldOffset(Mock.class.getDeclaredField("v"));
            } catch (Exception e) {
                throw new ExceptionInInitializerError(e);
            }
        }

        static void loadFence() {
            U.getIntVolatile(x, OFF);
        }

        static void storeFence() {
            U.putOrderedInt(x, OFF, 0);
        }

        static void fullFence() {
            U.putIntVolatile(x, OFF, 0);
        }

        private MemBar() {
        }
    }
}
