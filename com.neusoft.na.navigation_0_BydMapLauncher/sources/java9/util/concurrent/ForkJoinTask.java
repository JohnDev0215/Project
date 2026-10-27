package java9.util.concurrent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.locks.ReentrantLock;
import java9.util.Objects;
import java9.util.concurrent.ForkJoinPool;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ForkJoinTask<V> implements Future<V>, Serializable {
    static final int CANCELLED = -1073741824;
    static final int DONE_MASK = -268435456;
    static final int EXCEPTIONAL = Integer.MIN_VALUE;
    static final int NORMAL = -268435456;
    static final int SIGNAL = 65536;
    static final int SMASK = 65535;
    private static final long STATUS;
    private static final Unsafe U;
    private static final ExceptionNode[] exceptionTable = new ExceptionNode[32];
    private static final ReentrantLock exceptionTableLock = new ReentrantLock();
    private static final ReferenceQueue<ForkJoinTask<?>> exceptionTableRefQueue = new ReferenceQueue<>();
    private static final long serialVersionUID = -7721805057305804111L;
    volatile int status;

    protected abstract boolean exec();

    public abstract V getRawResult();

    void internalPropagateException(Throwable th) {
    }

    protected abstract void setRawResult(V v);

    private int setCompletion(int i) {
        int i2;
        do {
            i2 = this.status;
            if (i2 < 0) {
                return i2;
            }
        } while (!U.compareAndSwapInt(this, STATUS, i2, i2 | i));
        if ((i2 >>> 16) != 0) {
            synchronized (this) {
                notifyAll();
            }
        }
        return i;
    }

    final int doExec() {
        int i = this.status;
        if (i < 0) {
            return i;
        }
        try {
            return exec() ? setCompletion(-268435456) : i;
        } catch (Throwable th) {
            return setExceptionalCompletion(th);
        }
    }

    final void internalWait(long j) {
        int i = this.status;
        if (i < 0 || !U.compareAndSwapInt(this, STATUS, i, i | 65536)) {
            return;
        }
        synchronized (this) {
            if (this.status >= 0) {
                try {
                    wait(j);
                } catch (InterruptedException unused) {
                }
            } else {
                notifyAll();
            }
        }
    }

    private int externalAwaitDone() {
        int iDoExec;
        boolean z = false;
        if (this instanceof CountedCompleter) {
            iDoExec = ForkJoinPool.common.externalHelpComplete((CountedCompleter) this, 0);
        } else {
            iDoExec = ForkJoinPool.common.tryExternalUnpush(this) ? doExec() : 0;
        }
        if (iDoExec < 0) {
            return iDoExec;
        }
        int i = this.status;
        if (i < 0) {
            return i;
        }
        int i2 = i;
        do {
            if (U.compareAndSwapInt(this, STATUS, i2, i2 | 65536)) {
                synchronized (this) {
                    if (this.status >= 0) {
                        try {
                            wait(0L);
                        } catch (InterruptedException unused) {
                            z = true;
                        }
                    } else {
                        notifyAll();
                    }
                }
            }
            i2 = this.status;
        } while (i2 >= 0);
        if (z) {
            Thread.currentThread().interrupt();
        }
        return i2;
    }

    private int externalInterruptibleAwaitDone() throws InterruptedException {
        int iDoExec;
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        int i = this.status;
        if (i < 0) {
            return i;
        }
        if (this instanceof CountedCompleter) {
            iDoExec = ForkJoinPool.common.externalHelpComplete((CountedCompleter) this, 0);
        } else {
            iDoExec = ForkJoinPool.common.tryExternalUnpush(this) ? doExec() : 0;
        }
        if (iDoExec < 0) {
            return iDoExec;
        }
        while (true) {
            int i2 = this.status;
            if (i2 < 0) {
                return i2;
            }
            if (U.compareAndSwapInt(this, STATUS, i2, i2 | 65536)) {
                synchronized (this) {
                    if (this.status >= 0) {
                        wait(0L);
                    } else {
                        notifyAll();
                    }
                }
            }
        }
    }

    private int doJoin() {
        int iDoExec;
        int i = this.status;
        if (i < 0) {
            return i;
        }
        Thread threadCurrentThread = Thread.currentThread();
        if (!(threadCurrentThread instanceof ForkJoinWorkerThread)) {
            return externalAwaitDone();
        }
        ForkJoinWorkerThread forkJoinWorkerThread = (ForkJoinWorkerThread) threadCurrentThread;
        ForkJoinPool.WorkQueue workQueue = forkJoinWorkerThread.workQueue;
        return (!workQueue.tryUnpush(this) || (iDoExec = doExec()) >= 0) ? forkJoinWorkerThread.pool.awaitJoin(workQueue, this, 0L) : iDoExec;
    }

    private int doInvoke() {
        int iDoExec = doExec();
        if (iDoExec < 0) {
            return iDoExec;
        }
        Thread threadCurrentThread = Thread.currentThread();
        if (threadCurrentThread instanceof ForkJoinWorkerThread) {
            ForkJoinWorkerThread forkJoinWorkerThread = (ForkJoinWorkerThread) threadCurrentThread;
            return forkJoinWorkerThread.pool.awaitJoin(forkJoinWorkerThread.workQueue, this, 0L);
        }
        return externalAwaitDone();
    }

    static {
        Unsafe unsafe = UnsafeAccess.unsafe;
        U = unsafe;
        try {
            STATUS = unsafe.objectFieldOffset(ForkJoinTask.class.getDeclaredField("status"));
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    static final class ExceptionNode extends WeakReference<ForkJoinTask<?>> {
        final Throwable ex;
        final int hashCode;
        ExceptionNode next;
        final long thrower;

        ExceptionNode(ForkJoinTask<?> forkJoinTask, Throwable th, ExceptionNode exceptionNode, ReferenceQueue<ForkJoinTask<?>> referenceQueue) {
            super(forkJoinTask, referenceQueue);
            this.ex = th;
            this.next = exceptionNode;
            this.thrower = Thread.currentThread().getId();
            this.hashCode = System.identityHashCode(forkJoinTask);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x001a, code lost:
    
        r2[r0] = new java9.util.concurrent.ForkJoinTask.ExceptionNode(r6, r7, r2[r0], java9.util.concurrent.ForkJoinTask.exceptionTableRefQueue);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final int recordExceptionalCompletion(java.lang.Throwable r7) {
        /*
            r6 = this;
            int r0 = r6.status
            if (r0 < 0) goto L3e
            int r0 = java.lang.System.identityHashCode(r6)
            java.util.concurrent.locks.ReentrantLock r1 = java9.util.concurrent.ForkJoinTask.exceptionTableLock
            r1.lock()
            expungeStaleExceptions()     // Catch: java.lang.Throwable -> L39
            java9.util.concurrent.ForkJoinTask$ExceptionNode[] r2 = java9.util.concurrent.ForkJoinTask.exceptionTable     // Catch: java.lang.Throwable -> L39
            int r3 = r2.length     // Catch: java.lang.Throwable -> L39
            int r3 = r3 + (-1)
            r0 = r0 & r3
            r3 = r2[r0]     // Catch: java.lang.Throwable -> L39
        L18:
            if (r3 != 0) goto L26
            java9.util.concurrent.ForkJoinTask$ExceptionNode r3 = new java9.util.concurrent.ForkJoinTask$ExceptionNode     // Catch: java.lang.Throwable -> L39
            r4 = r2[r0]     // Catch: java.lang.Throwable -> L39
            java.lang.ref.ReferenceQueue<java9.util.concurrent.ForkJoinTask<?>> r5 = java9.util.concurrent.ForkJoinTask.exceptionTableRefQueue     // Catch: java.lang.Throwable -> L39
            r3.<init>(r6, r7, r4, r5)     // Catch: java.lang.Throwable -> L39
            r2[r0] = r3     // Catch: java.lang.Throwable -> L39
            goto L2c
        L26:
            java.lang.Object r4 = r3.get()     // Catch: java.lang.Throwable -> L39
            if (r4 != r6) goto L36
        L2c:
            r1.unlock()
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            int r0 = r6.setCompletion(r7)
            goto L3e
        L36:
            java9.util.concurrent.ForkJoinTask$ExceptionNode r3 = r3.next     // Catch: java.lang.Throwable -> L39
            goto L18
        L39:
            r7 = move-exception
            r1.unlock()
            throw r7
        L3e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java9.util.concurrent.ForkJoinTask.recordExceptionalCompletion(java.lang.Throwable):int");
    }

    private int setExceptionalCompletion(Throwable th) {
        int iRecordExceptionalCompletion = recordExceptionalCompletion(th);
        if (((-268435456) & iRecordExceptionalCompletion) == Integer.MIN_VALUE) {
            internalPropagateException(th);
        }
        return iRecordExceptionalCompletion;
    }

    static final void cancelIgnoringExceptions(ForkJoinTask<?> forkJoinTask) {
        if (forkJoinTask == null || forkJoinTask.status < 0) {
            return;
        }
        try {
            forkJoinTask.cancel(false);
        } catch (Throwable unused) {
        }
    }

    private void clearExceptionalCompletion() {
        int iIdentityHashCode = System.identityHashCode(this);
        ReentrantLock reentrantLock = exceptionTableLock;
        reentrantLock.lock();
        try {
            ExceptionNode[] exceptionNodeArr = exceptionTable;
            int length = iIdentityHashCode & (exceptionNodeArr.length - 1);
            ExceptionNode exceptionNode = exceptionNodeArr[length];
            ExceptionNode exceptionNode2 = null;
            while (true) {
                if (exceptionNode == null) {
                    break;
                }
                ExceptionNode exceptionNode3 = exceptionNode.next;
                if (exceptionNode.get() != this) {
                    exceptionNode2 = exceptionNode;
                    exceptionNode = exceptionNode3;
                } else if (exceptionNode2 == null) {
                    exceptionNodeArr[length] = exceptionNode3;
                } else {
                    exceptionNode2.next = exceptionNode3;
                }
            }
            expungeStaleExceptions();
            this.status = 0;
        } finally {
            reentrantLock.unlock();
        }
    }

    private Throwable getThrowableException() {
        Throwable th;
        int iIdentityHashCode = System.identityHashCode(this);
        ReentrantLock reentrantLock = exceptionTableLock;
        reentrantLock.lock();
        try {
            expungeStaleExceptions();
            ExceptionNode[] exceptionNodeArr = exceptionTable;
            ExceptionNode exceptionNode = exceptionNodeArr[iIdentityHashCode & (exceptionNodeArr.length - 1)];
            while (exceptionNode != null) {
                if (exceptionNode.get() == this) {
                    break;
                }
                exceptionNode = exceptionNode.next;
            }
            reentrantLock.unlock();
            Constructor<?> constructor = null;
            if (exceptionNode == null || (th = exceptionNode.ex) == null) {
                return null;
            }
            if (exceptionNode.thrower != Thread.currentThread().getId()) {
                try {
                    for (Constructor<?> constructor2 : th.getClass().getConstructors()) {
                        Class<?>[] parameterTypes = constructor2.getParameterTypes();
                        if (parameterTypes.length == 0) {
                            constructor = constructor2;
                        } else if (parameterTypes.length == 1 && parameterTypes[0] == Throwable.class) {
                            return (Throwable) constructor2.newInstance(th);
                        }
                    }
                    if (constructor != null) {
                        Throwable th2 = (Throwable) constructor.newInstance(new Object[0]);
                        th2.initCause(th);
                        return th2;
                    }
                } catch (Exception unused) {
                }
            }
            return th;
        } catch (Throwable th3) {
            reentrantLock.unlock();
            throw th3;
        }
    }

    private static void expungeStaleExceptions() {
        while (true) {
            Reference<? extends ForkJoinTask<?>> referencePoll = exceptionTableRefQueue.poll();
            if (referencePoll == null) {
                return;
            }
            if (referencePoll instanceof ExceptionNode) {
                ExceptionNode[] exceptionNodeArr = exceptionTable;
                int length = ((ExceptionNode) referencePoll).hashCode & (exceptionNodeArr.length - 1);
                ExceptionNode exceptionNode = exceptionNodeArr[length];
                ExceptionNode exceptionNode2 = null;
                while (true) {
                    if (exceptionNode != null) {
                        ExceptionNode exceptionNode3 = exceptionNode.next;
                        if (exceptionNode != referencePoll) {
                            exceptionNode2 = exceptionNode;
                            exceptionNode = exceptionNode3;
                        } else if (exceptionNode2 == null) {
                            exceptionNodeArr[length] = exceptionNode3;
                        } else {
                            exceptionNode2.next = exceptionNode3;
                        }
                    }
                }
            }
        }
    }

    static final void helpExpungeStaleExceptions() {
        ReentrantLock reentrantLock = exceptionTableLock;
        if (reentrantLock.tryLock()) {
            try {
                expungeStaleExceptions();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    static void rethrow(Throwable th) throws Throwable {
        uncheckedThrow(th);
    }

    static <T extends Throwable> void uncheckedThrow(Throwable th) throws Throwable {
        if (th != null) {
            throw th;
        }
        throw new Error("Unknown Exception");
    }

    private void reportException(int i) throws Throwable {
        if (i == -1073741824) {
            throw new CancellationException();
        }
        if (i == Integer.MIN_VALUE) {
            rethrow(getThrowableException());
        }
    }

    public final ForkJoinTask<V> fork() {
        Thread threadCurrentThread = Thread.currentThread();
        if (threadCurrentThread instanceof ForkJoinWorkerThread) {
            ((ForkJoinWorkerThread) threadCurrentThread).workQueue.push(this);
        } else {
            ForkJoinPool.common.externalPush(this);
        }
        return this;
    }

    public final V join() throws Throwable {
        int iDoJoin = doJoin() & (-268435456);
        if (iDoJoin != -268435456) {
            reportException(iDoJoin);
        }
        return getRawResult();
    }

    public final V invoke() throws Throwable {
        int iDoInvoke = doInvoke() & (-268435456);
        if (iDoInvoke != -268435456) {
            reportException(iDoInvoke);
        }
        return getRawResult();
    }

    public static void invokeAll(ForkJoinTask<?> forkJoinTask, ForkJoinTask<?> forkJoinTask2) throws Throwable {
        forkJoinTask2.fork();
        int iDoInvoke = forkJoinTask.doInvoke() & (-268435456);
        if (iDoInvoke != -268435456) {
            forkJoinTask.reportException(iDoInvoke);
        }
        int iDoJoin = forkJoinTask2.doJoin() & (-268435456);
        if (iDoJoin != -268435456) {
            forkJoinTask2.reportException(iDoJoin);
        }
    }

    public static void invokeAll(ForkJoinTask<?>... forkJoinTaskArr) throws Throwable {
        int length = forkJoinTaskArr.length - 1;
        Throwable exception = null;
        for (int i = length; i >= 0; i--) {
            ForkJoinTask<?> forkJoinTask = forkJoinTaskArr[i];
            if (forkJoinTask == null) {
                if (exception == null) {
                    exception = new NullPointerException();
                }
            } else if (i != 0) {
                forkJoinTask.fork();
            } else if (forkJoinTask.doInvoke() < -268435456 && exception == null) {
                exception = forkJoinTask.getException();
            }
        }
        for (int i2 = 1; i2 <= length; i2++) {
            ForkJoinTask<?> forkJoinTask2 = forkJoinTaskArr[i2];
            if (forkJoinTask2 != null) {
                if (exception != null) {
                    forkJoinTask2.cancel(false);
                } else if (forkJoinTask2.doJoin() < -268435456) {
                    exception = forkJoinTask2.getException();
                }
            }
        }
        if (exception != null) {
            rethrow(exception);
        }
    }

    public static <T extends ForkJoinTask<?>> Collection<T> invokeAll(Collection<T> collection) throws Throwable {
        if (!(collection instanceof RandomAccess) || !(collection instanceof List)) {
            invokeAll((ForkJoinTask<?>[]) collection.toArray(new ForkJoinTask[0]));
            return collection;
        }
        List list = (List) collection;
        int size = list.size() - 1;
        Throwable exception = null;
        for (int i = size; i >= 0; i--) {
            ForkJoinTask forkJoinTask = (ForkJoinTask) list.get(i);
            if (forkJoinTask == null) {
                if (exception == null) {
                    exception = new NullPointerException();
                }
            } else if (i != 0) {
                forkJoinTask.fork();
            } else if (forkJoinTask.doInvoke() < -268435456 && exception == null) {
                exception = forkJoinTask.getException();
            }
        }
        for (int i2 = 1; i2 <= size; i2++) {
            ForkJoinTask forkJoinTask2 = (ForkJoinTask) list.get(i2);
            if (forkJoinTask2 != null) {
                if (exception != null) {
                    forkJoinTask2.cancel(false);
                } else if (forkJoinTask2.doJoin() < -268435456) {
                    exception = forkJoinTask2.getException();
                }
            }
        }
        if (exception != null) {
            rethrow(exception);
        }
        return collection;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return (setCompletion(-1073741824) & (-268435456)) == -1073741824;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.status < 0;
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return (this.status & (-268435456)) == -1073741824;
    }

    public final boolean isCompletedAbnormally() {
        return this.status < -268435456;
    }

    public final boolean isCompletedNormally() {
        return (this.status & (-268435456)) == -268435456;
    }

    public final Throwable getException() {
        int i = this.status & (-268435456);
        if (i >= -268435456) {
            return null;
        }
        if (i == -1073741824) {
            return new CancellationException();
        }
        return getThrowableException();
    }

    public void completeExceptionally(Throwable th) {
        if (!(th instanceof RuntimeException) && !(th instanceof Error)) {
            th = new RuntimeException(th);
        }
        setExceptionalCompletion(th);
    }

    public void complete(V v) {
        try {
            setRawResult(v);
            setCompletion(-268435456);
        } catch (Throwable th) {
            setExceptionalCompletion(th);
        }
    }

    public final void quietlyComplete() {
        setCompletion(-268435456);
    }

    @Override // java.util.concurrent.Future
    public final V get() throws ExecutionException, InterruptedException {
        int iDoJoin = (Thread.currentThread() instanceof ForkJoinWorkerThread ? doJoin() : externalInterruptibleAwaitDone()) & (-268435456);
        if (iDoJoin == -1073741824) {
            throw new CancellationException();
        }
        if (iDoJoin == Integer.MIN_VALUE) {
            throw new ExecutionException(getThrowableException());
        }
        return getRawResult();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0052 A[LOOP:0: B:23:0x0052->B:66:0x0052, LOOP_START] */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final V get(long r11, java.util.concurrent.TimeUnit r13) throws java.util.concurrent.ExecutionException, java.lang.InterruptedException, java.util.concurrent.TimeoutException {
        /*
            r10 = this;
            long r11 = r13.toNanos(r11)
            boolean r13 = java.lang.Thread.interrupted()
            if (r13 != 0) goto Lb8
            int r13 = r10.status
            if (r13 < 0) goto L8b
            r0 = 0
            int r2 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r2 <= 0) goto L8b
            long r2 = java.lang.System.nanoTime()
            long r2 = r2 + r11
            int r11 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r11 != 0) goto L1f
            r2 = 1
        L1f:
            java.lang.Thread r11 = java.lang.Thread.currentThread()
            boolean r12 = r11 instanceof java9.util.concurrent.ForkJoinWorkerThread
            if (r12 == 0) goto L32
            java9.util.concurrent.ForkJoinWorkerThread r11 = (java9.util.concurrent.ForkJoinWorkerThread) r11
            java9.util.concurrent.ForkJoinPool r12 = r11.pool
            java9.util.concurrent.ForkJoinPool$WorkQueue r11 = r11.workQueue
            int r13 = r12.awaitJoin(r11, r10, r2)
            goto L8b
        L32:
            boolean r11 = r10 instanceof java9.util.concurrent.CountedCompleter
            r12 = 0
            if (r11 == 0) goto L42
            java9.util.concurrent.ForkJoinPool r11 = java9.util.concurrent.ForkJoinPool.common
            r13 = r10
            java9.util.concurrent.CountedCompleter r13 = (java9.util.concurrent.CountedCompleter) r13
            int r11 = r11.externalHelpComplete(r13, r12)
        L40:
            r13 = r11
            goto L50
        L42:
            java9.util.concurrent.ForkJoinPool r11 = java9.util.concurrent.ForkJoinPool.common
            boolean r11 = r11.tryExternalUnpush(r10)
            if (r11 == 0) goto L4f
            int r11 = r10.doExec()
            goto L40
        L4f:
            r13 = r12
        L50:
            if (r13 < 0) goto L8b
        L52:
            int r8 = r10.status
            if (r8 < 0) goto L8a
            long r11 = java.lang.System.nanoTime()
            long r11 = r2 - r11
            int r13 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r13 <= 0) goto L8a
            java.util.concurrent.TimeUnit r13 = java.util.concurrent.TimeUnit.NANOSECONDS
            long r11 = r13.toMillis(r11)
            int r13 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r13 <= 0) goto L52
            sun.misc.Unsafe r4 = java9.util.concurrent.ForkJoinTask.U
            long r6 = java9.util.concurrent.ForkJoinTask.STATUS
            r13 = 65536(0x10000, float:9.1835E-41)
            r9 = r8 | r13
            r5 = r10
            boolean r13 = r4.compareAndSwapInt(r5, r6, r8, r9)
            if (r13 == 0) goto L52
            monitor-enter(r10)
            int r13 = r10.status     // Catch: java.lang.Throwable -> L87
            if (r13 < 0) goto L82
            r10.wait(r11)     // Catch: java.lang.Throwable -> L87
            goto L85
        L82:
            r10.notifyAll()     // Catch: java.lang.Throwable -> L87
        L85:
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L87
            goto L52
        L87:
            r11 = move-exception
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L87
            throw r11
        L8a:
            r13 = r8
        L8b:
            if (r13 < 0) goto L8f
            int r13 = r10.status
        L8f:
            r11 = -268435456(0xfffffffff0000000, float:-1.5845633E29)
            r12 = r13 & r11
            if (r12 == r11) goto Lb3
            r11 = -1073741824(0xffffffffc0000000, float:-2.0)
            if (r12 == r11) goto Lad
            r11 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r12 == r11) goto La3
            java.util.concurrent.TimeoutException r11 = new java.util.concurrent.TimeoutException
            r11.<init>()
            throw r11
        La3:
            java.util.concurrent.ExecutionException r11 = new java.util.concurrent.ExecutionException
            java.lang.Throwable r12 = r10.getThrowableException()
            r11.<init>(r12)
            throw r11
        Lad:
            java.util.concurrent.CancellationException r11 = new java.util.concurrent.CancellationException
            r11.<init>()
            throw r11
        Lb3:
            java.lang.Object r11 = r10.getRawResult()
            return r11
        Lb8:
            java.lang.InterruptedException r11 = new java.lang.InterruptedException
            r11.<init>()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: java9.util.concurrent.ForkJoinTask.get(long, java.util.concurrent.TimeUnit):java.lang.Object");
    }

    public final void quietlyJoin() {
        doJoin();
    }

    public final void quietlyInvoke() {
        doInvoke();
    }

    public static void helpQuiesce() {
        Thread threadCurrentThread = Thread.currentThread();
        if (threadCurrentThread instanceof ForkJoinWorkerThread) {
            ForkJoinWorkerThread forkJoinWorkerThread = (ForkJoinWorkerThread) threadCurrentThread;
            forkJoinWorkerThread.pool.helpQuiescePool(forkJoinWorkerThread.workQueue);
        } else {
            ForkJoinPool.quiesceCommonPool();
        }
    }

    public void reinitialize() {
        if ((this.status & (-268435456)) == Integer.MIN_VALUE) {
            clearExceptionalCompletion();
        } else {
            this.status = 0;
        }
    }

    public static ForkJoinPool getPool() {
        Thread threadCurrentThread = Thread.currentThread();
        if (threadCurrentThread instanceof ForkJoinWorkerThread) {
            return ((ForkJoinWorkerThread) threadCurrentThread).pool;
        }
        return null;
    }

    public static boolean inForkJoinPool() {
        return Thread.currentThread() instanceof ForkJoinWorkerThread;
    }

    public boolean tryUnfork() {
        Thread threadCurrentThread = Thread.currentThread();
        if (threadCurrentThread instanceof ForkJoinWorkerThread) {
            return ((ForkJoinWorkerThread) threadCurrentThread).workQueue.tryUnpush(this);
        }
        return ForkJoinPool.common.tryExternalUnpush(this);
    }

    public static int getQueuedTaskCount() {
        ForkJoinPool.WorkQueue workQueueCommonSubmitterQueue;
        Thread threadCurrentThread = Thread.currentThread();
        if (threadCurrentThread instanceof ForkJoinWorkerThread) {
            workQueueCommonSubmitterQueue = ((ForkJoinWorkerThread) threadCurrentThread).workQueue;
        } else {
            workQueueCommonSubmitterQueue = ForkJoinPool.commonSubmitterQueue();
        }
        if (workQueueCommonSubmitterQueue == null) {
            return 0;
        }
        return workQueueCommonSubmitterQueue.queueSize();
    }

    public static int getSurplusQueuedTaskCount() {
        return ForkJoinPool.getSurplusQueuedTaskCount();
    }

    protected static ForkJoinTask<?> peekNextLocalTask() {
        ForkJoinPool.WorkQueue workQueueCommonSubmitterQueue;
        Thread threadCurrentThread = Thread.currentThread();
        if (threadCurrentThread instanceof ForkJoinWorkerThread) {
            workQueueCommonSubmitterQueue = ((ForkJoinWorkerThread) threadCurrentThread).workQueue;
        } else {
            workQueueCommonSubmitterQueue = ForkJoinPool.commonSubmitterQueue();
        }
        if (workQueueCommonSubmitterQueue == null) {
            return null;
        }
        return workQueueCommonSubmitterQueue.peek();
    }

    protected static ForkJoinTask<?> pollNextLocalTask() {
        Thread threadCurrentThread = Thread.currentThread();
        if (threadCurrentThread instanceof ForkJoinWorkerThread) {
            return ((ForkJoinWorkerThread) threadCurrentThread).workQueue.nextLocalTask();
        }
        return null;
    }

    protected static ForkJoinTask<?> pollTask() {
        Thread threadCurrentThread = Thread.currentThread();
        if (!(threadCurrentThread instanceof ForkJoinWorkerThread)) {
            return null;
        }
        ForkJoinWorkerThread forkJoinWorkerThread = (ForkJoinWorkerThread) threadCurrentThread;
        return forkJoinWorkerThread.pool.nextTaskFor(forkJoinWorkerThread.workQueue);
    }

    protected static ForkJoinTask<?> pollSubmission() {
        Thread threadCurrentThread = Thread.currentThread();
        if (threadCurrentThread instanceof ForkJoinWorkerThread) {
            return ((ForkJoinWorkerThread) threadCurrentThread).pool.pollSubmission();
        }
        return null;
    }

    public final short getForkJoinTaskTag() {
        return (short) this.status;
    }

    public final short setForkJoinTaskTag(short s) {
        Unsafe unsafe;
        long j;
        int i;
        do {
            unsafe = U;
            j = STATUS;
            i = this.status;
        } while (!unsafe.compareAndSwapInt(this, j, i, ((-65536) & i) | (65535 & s)));
        return (short) i;
    }

    public final boolean compareAndSetForkJoinTaskTag(short s, short s2) {
        int i;
        do {
            i = this.status;
            if (((short) i) != s) {
                return false;
            }
        } while (!U.compareAndSwapInt(this, STATUS, i, (65535 & s2) | ((-65536) & i)));
        return true;
    }

    static final class AdaptedRunnable<T> extends ForkJoinTask<T> implements RunnableFuture<T> {
        private static final long serialVersionUID = 5232453952276885070L;
        T result;
        final Runnable runnable;

        AdaptedRunnable(Runnable runnable, T t) {
            this.runnable = (Runnable) Objects.requireNonNull(runnable);
            this.result = t;
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final T getRawResult() {
            return this.result;
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final void setRawResult(T t) {
            this.result = t;
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final boolean exec() {
            this.runnable.run();
            return true;
        }

        @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
        public final void run() {
            invoke();
        }

        public String toString() {
            return super.toString() + "[Wrapped task = " + this.runnable + "]";
        }
    }

    static final class AdaptedRunnableAction extends ForkJoinTask<Void> implements RunnableFuture<Void> {
        private static final long serialVersionUID = 5232453952276885070L;
        final Runnable runnable;

        @Override // java9.util.concurrent.ForkJoinTask
        public final Void getRawResult() {
            return null;
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final void setRawResult(Void r1) {
        }

        AdaptedRunnableAction(Runnable runnable) {
            this.runnable = (Runnable) Objects.requireNonNull(runnable);
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final boolean exec() {
            this.runnable.run();
            return true;
        }

        @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
        public final void run() {
            invoke();
        }

        public String toString() {
            return super.toString() + "[Wrapped task = " + this.runnable + "]";
        }
    }

    static final class RunnableExecuteAction extends ForkJoinTask<Void> {
        private static final long serialVersionUID = 5232453952276885070L;
        final Runnable runnable;

        @Override // java9.util.concurrent.ForkJoinTask
        public final Void getRawResult() {
            return null;
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final void setRawResult(Void r1) {
        }

        RunnableExecuteAction(Runnable runnable) {
            this.runnable = (Runnable) Objects.requireNonNull(runnable);
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final boolean exec() {
            this.runnable.run();
            return true;
        }

        @Override // java9.util.concurrent.ForkJoinTask
        void internalPropagateException(Throwable th) {
            rethrow(th);
        }
    }

    static final class AdaptedCallable<T> extends ForkJoinTask<T> implements RunnableFuture<T> {
        private static final long serialVersionUID = 2838392045355241008L;
        final Callable<? extends T> callable;
        T result;

        AdaptedCallable(Callable<? extends T> callable) {
            this.callable = (Callable) Objects.requireNonNull(callable);
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final T getRawResult() {
            return this.result;
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final void setRawResult(T t) {
            this.result = t;
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final boolean exec() {
            try {
                this.result = this.callable.call();
                return true;
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
        public final void run() {
            invoke();
        }

        public String toString() {
            return super.toString() + "[Wrapped task = " + this.callable + "]";
        }
    }

    public static ForkJoinTask<?> adapt(Runnable runnable) {
        return new AdaptedRunnableAction(runnable);
    }

    public static <T> ForkJoinTask<T> adapt(Runnable runnable, T t) {
        return new AdaptedRunnable(runnable, t);
    }

    public static <T> ForkJoinTask<T> adapt(Callable<? extends T> callable) {
        return new AdaptedCallable(callable);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getException());
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        Object object = objectInputStream.readObject();
        if (object != null) {
            setExceptionalCompletion((Throwable) object);
        }
    }
}
