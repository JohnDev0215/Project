package java9.util.concurrent;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java9.util.Objects;
import java9.util.concurrent.CompletableFuture;
import java9.util.concurrent.Flow;
import java9.util.concurrent.ForkJoinPool;
import java9.util.function.BiConsumer;
import java9.util.function.BiPredicate;
import java9.util.function.Consumer;
import kotlin.jvm.internal.LongCompanionObject;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
public class SubmissionPublisher<T> implements Flow.Publisher<T> {
    private static final Executor ASYNC_POOL;
    static final int BUFFER_CAPACITY_LIMIT = 1073741824;
    static final int INITIAL_CAPACITY = 32;
    BufferedSubscription<T> clients;
    volatile boolean closed;
    volatile Throwable closedException;
    final Executor executor;
    final int maxBufferCapacity;
    final BiConsumer<? super Flow.Subscriber<? super T>, ? super Throwable> onNextHandler;
    Thread owner;
    boolean subscribed;

    static final int roundCapacity(int i) {
        int i2 = i - 1;
        int i3 = i2 | (i2 >>> 1);
        int i4 = i3 | (i3 >>> 2);
        int i5 = i4 | (i4 >>> 4);
        int i6 = i5 | (i5 >>> 8);
        int i7 = i6 | (i6 >>> 16);
        if (i7 <= 0) {
            return 1;
        }
        if (i7 >= 1073741824) {
            return 1073741824;
        }
        return 1 + i7;
    }

    static {
        ASYNC_POOL = ForkJoinPool.getCommonPoolParallelism() > 1 ? ForkJoinPool.commonPool() : new ThreadPerTaskExecutor();
    }

    private static final class ThreadPerTaskExecutor implements Executor {
        ThreadPerTaskExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            new Thread(runnable).start();
        }
    }

    public SubmissionPublisher(Executor executor, int i, BiConsumer<? super Flow.Subscriber<? super T>, ? super Throwable> biConsumer) {
        Objects.requireNonNull(executor);
        if (i <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.executor = executor;
        this.onNextHandler = biConsumer;
        this.maxBufferCapacity = roundCapacity(i);
    }

    public SubmissionPublisher(Executor executor, int i) {
        this(executor, i, null);
    }

    public SubmissionPublisher() {
        this(ASYNC_POOL, Flow.defaultBufferSize(), null);
    }

    @Override // java9.util.concurrent.Flow.Publisher
    public void subscribe(Flow.Subscriber<? super T> subscriber) {
        Objects.requireNonNull(subscriber);
        int i = this.maxBufferCapacity;
        BufferedSubscription<T> bufferedSubscription = new BufferedSubscription<>(subscriber, this.executor, this.onNextHandler, new Object[i < 32 ? i : 32], i);
        synchronized (this) {
            if (!this.subscribed) {
                this.subscribed = true;
                this.owner = Thread.currentThread();
            }
            BufferedSubscription<T> bufferedSubscription2 = this.clients;
            BufferedSubscription<T> bufferedSubscription3 = null;
            while (true) {
                if (bufferedSubscription2 == null) {
                    bufferedSubscription.onSubscribe();
                    Throwable th = this.closedException;
                    if (th != null) {
                        bufferedSubscription.onError(th);
                    } else if (this.closed) {
                        bufferedSubscription.onComplete();
                    } else if (bufferedSubscription3 == null) {
                        this.clients = bufferedSubscription;
                    } else {
                        bufferedSubscription3.next = bufferedSubscription;
                    }
                } else {
                    BufferedSubscription<T> bufferedSubscription4 = bufferedSubscription2.next;
                    if (bufferedSubscription2.isClosed()) {
                        bufferedSubscription2.next = null;
                        if (bufferedSubscription3 == null) {
                            this.clients = bufferedSubscription4;
                        } else {
                            bufferedSubscription3.next = bufferedSubscription4;
                        }
                    } else {
                        if (subscriber.equals(bufferedSubscription2.subscriber)) {
                            bufferedSubscription2.onError(new IllegalStateException("Duplicate subscribe"));
                            break;
                        }
                        bufferedSubscription3 = bufferedSubscription2;
                    }
                    bufferedSubscription2 = bufferedSubscription4;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0062 A[LOOP:0: B:15:0x0026->B:37:0x0062, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0045 A[EDGE_INSN: B:43:0x0045->B:29:0x0045 BREAK  A[LOOP:0: B:15:0x0026->B:37:0x0062], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int doOffer(T r15, long r16, java9.util.function.BiPredicate<java9.util.concurrent.Flow.Subscriber<? super T>, ? super T> r18) {
        /*
            r14 = this;
            r9 = r14
            java9.util.Objects.requireNonNull(r15)
            monitor-enter(r14)
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L66
            java9.util.concurrent.SubmissionPublisher$BufferedSubscription<T> r1 = r9.clients     // Catch: java.lang.Throwable -> L66
            java.lang.Thread r2 = r9.owner     // Catch: java.lang.Throwable -> L66
            r3 = 1
            r10 = 0
            if (r2 == r0) goto L13
            r0 = r3
            goto L14
        L13:
            r0 = r10
        L14:
            r4 = 0
            if (r0 == 0) goto L1b
            if (r2 == 0) goto L1b
            r9.owner = r4     // Catch: java.lang.Throwable -> L66
        L1b:
            if (r1 != 0) goto L22
            boolean r0 = r9.closed     // Catch: java.lang.Throwable -> L66
            r12 = r10
            r10 = r0
            goto L56
        L22:
            r5 = r4
            r6 = r5
            r2 = r10
            r7 = r2
        L26:
            java9.util.concurrent.SubmissionPublisher$BufferedSubscription<T> r8 = r1.next     // Catch: java.lang.Throwable -> L66
            r11 = r15
            int r12 = r1.offer(r15, r0)     // Catch: java.lang.Throwable -> L66
            if (r12 != 0) goto L39
            r1.nextRetry = r4     // Catch: java.lang.Throwable -> L66
            if (r5 != 0) goto L35
            r6 = r1
            goto L37
        L35:
            r5.nextRetry = r1     // Catch: java.lang.Throwable -> L66
        L37:
            r5 = r1
            goto L41
        L39:
            if (r12 >= 0) goto L3e
            r12 = r2
            r13 = r3
            goto L43
        L3e:
            if (r12 <= r2) goto L41
            goto L42
        L41:
            r12 = r2
        L42:
            r13 = r7
        L43:
            if (r8 != 0) goto L62
            if (r6 != 0) goto L49
            if (r13 == 0) goto L56
        L49:
            r1 = r14
            r2 = r15
            r3 = r16
            r5 = r18
            r7 = r12
            r8 = r13
            int r0 = r1.retryOffer(r2, r3, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L66
            r12 = r0
        L56:
            monitor-exit(r14)     // Catch: java.lang.Throwable -> L66
            if (r10 != 0) goto L5a
            return r12
        L5a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Closed"
            r0.<init>(r1)
            throw r0
        L62:
            r1 = r8
            r2 = r12
            r7 = r13
            goto L26
        L66:
            r0 = move-exception
            monitor-exit(r14)     // Catch: java.lang.Throwable -> L66
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java9.util.concurrent.SubmissionPublisher.doOffer(java.lang.Object, long, java9.util.function.BiPredicate):int");
    }

    private int retryOffer(T t, long j, BiPredicate<Flow.Subscriber<? super T>, ? super T> biPredicate, BufferedSubscription<T> bufferedSubscription, int i, boolean z) {
        while (bufferedSubscription != null) {
            BufferedSubscription<T> bufferedSubscription2 = bufferedSubscription.nextRetry;
            bufferedSubscription.nextRetry = null;
            if (j > 0) {
                bufferedSubscription.awaitSpace(j);
            }
            int iRetryOffer = bufferedSubscription.retryOffer(t);
            if (iRetryOffer == 0 && biPredicate != null && biPredicate.test(bufferedSubscription.subscriber, t)) {
                iRetryOffer = bufferedSubscription.retryOffer(t);
            }
            if (iRetryOffer == 0) {
                i = i >= 0 ? -1 : i - 1;
            } else if (iRetryOffer < 0) {
                z = true;
            } else if (i >= 0 && iRetryOffer > i) {
                i = iRetryOffer;
            }
            bufferedSubscription = bufferedSubscription2;
        }
        if (z) {
            cleanAndCount();
        }
        return i;
    }

    private int cleanAndCount() {
        BufferedSubscription<T> bufferedSubscription = this.clients;
        int i = 0;
        BufferedSubscription<T> bufferedSubscription2 = null;
        while (bufferedSubscription != null) {
            BufferedSubscription<T> bufferedSubscription3 = bufferedSubscription.next;
            if (bufferedSubscription.isClosed()) {
                bufferedSubscription.next = null;
                if (bufferedSubscription2 == null) {
                    this.clients = bufferedSubscription3;
                } else {
                    bufferedSubscription2.next = bufferedSubscription3;
                }
            } else {
                i++;
                bufferedSubscription2 = bufferedSubscription;
            }
            bufferedSubscription = bufferedSubscription3;
        }
        return i;
    }

    public int submit(T t) {
        return doOffer(t, LongCompanionObject.MAX_VALUE, null);
    }

    public int offer(T t, BiPredicate<Flow.Subscriber<? super T>, ? super T> biPredicate) {
        return doOffer(t, 0L, biPredicate);
    }

    public int offer(T t, long j, TimeUnit timeUnit, BiPredicate<Flow.Subscriber<? super T>, ? super T> biPredicate) {
        long nanos = timeUnit.toNanos(j);
        if (nanos == LongCompanionObject.MAX_VALUE) {
            nanos--;
        }
        return doOffer(t, nanos, biPredicate);
    }

    public void close() {
        BufferedSubscription<T> bufferedSubscription;
        if (this.closed) {
            return;
        }
        synchronized (this) {
            bufferedSubscription = this.clients;
            this.clients = null;
            this.owner = null;
            this.closed = true;
        }
        while (bufferedSubscription != null) {
            BufferedSubscription<T> bufferedSubscription2 = bufferedSubscription.next;
            bufferedSubscription.next = null;
            bufferedSubscription.onComplete();
            bufferedSubscription = bufferedSubscription2;
        }
    }

    public void closeExceptionally(Throwable th) {
        BufferedSubscription<T> bufferedSubscription;
        Objects.requireNonNull(th);
        if (this.closed) {
            return;
        }
        synchronized (this) {
            bufferedSubscription = this.clients;
            if (!this.closed) {
                this.closedException = th;
                this.clients = null;
                this.owner = null;
                this.closed = true;
            }
        }
        while (bufferedSubscription != null) {
            BufferedSubscription<T> bufferedSubscription2 = bufferedSubscription.next;
            bufferedSubscription.next = null;
            bufferedSubscription.onError(th);
            bufferedSubscription = bufferedSubscription2;
        }
    }

    public boolean isClosed() {
        return this.closed;
    }

    public Throwable getClosedException() {
        return this.closedException;
    }

    public boolean hasSubscribers() {
        boolean z;
        synchronized (this) {
            BufferedSubscription<T> bufferedSubscription = this.clients;
            while (true) {
                if (bufferedSubscription == null) {
                    z = false;
                    break;
                }
                BufferedSubscription<T> bufferedSubscription2 = bufferedSubscription.next;
                if (!bufferedSubscription.isClosed()) {
                    z = true;
                    break;
                }
                bufferedSubscription.next = null;
                this.clients = bufferedSubscription2;
                bufferedSubscription = bufferedSubscription2;
            }
        }
        return z;
    }

    public int getNumberOfSubscribers() {
        int iCleanAndCount;
        synchronized (this) {
            iCleanAndCount = cleanAndCount();
        }
        return iCleanAndCount;
    }

    public Executor getExecutor() {
        return this.executor;
    }

    public int getMaxBufferCapacity() {
        return this.maxBufferCapacity;
    }

    public List<Flow.Subscriber<? super T>> getSubscribers() {
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            BufferedSubscription<T> bufferedSubscription = this.clients;
            BufferedSubscription<T> bufferedSubscription2 = null;
            while (bufferedSubscription != null) {
                BufferedSubscription<T> bufferedSubscription3 = bufferedSubscription.next;
                if (bufferedSubscription.isClosed()) {
                    bufferedSubscription.next = null;
                    if (bufferedSubscription2 == null) {
                        this.clients = bufferedSubscription3;
                    } else {
                        bufferedSubscription2.next = bufferedSubscription3;
                    }
                } else {
                    arrayList.add(bufferedSubscription.subscriber);
                    bufferedSubscription2 = bufferedSubscription;
                }
                bufferedSubscription = bufferedSubscription3;
            }
        }
        return arrayList;
    }

    public boolean isSubscribed(Flow.Subscriber<? super T> subscriber) {
        Objects.requireNonNull(subscriber);
        if (this.closed) {
            return false;
        }
        synchronized (this) {
            BufferedSubscription<T> bufferedSubscription = this.clients;
            BufferedSubscription<T> bufferedSubscription2 = null;
            while (bufferedSubscription != null) {
                BufferedSubscription<T> bufferedSubscription3 = bufferedSubscription.next;
                if (bufferedSubscription.isClosed()) {
                    bufferedSubscription.next = null;
                    if (bufferedSubscription2 == null) {
                        this.clients = bufferedSubscription3;
                    } else {
                        bufferedSubscription2.next = bufferedSubscription3;
                    }
                } else {
                    if (subscriber.equals(bufferedSubscription.subscriber)) {
                        return true;
                    }
                    bufferedSubscription2 = bufferedSubscription;
                }
                bufferedSubscription = bufferedSubscription3;
            }
            return false;
        }
    }

    public long estimateMinimumDemand() {
        long j;
        boolean z;
        synchronized (this) {
            BufferedSubscription<T> bufferedSubscription = this.clients;
            j = LongCompanionObject.MAX_VALUE;
            z = false;
            BufferedSubscription<T> bufferedSubscription2 = null;
            while (bufferedSubscription != null) {
                BufferedSubscription<T> bufferedSubscription3 = bufferedSubscription.next;
                int iEstimateLag = bufferedSubscription.estimateLag();
                if (iEstimateLag < 0) {
                    bufferedSubscription.next = null;
                    if (bufferedSubscription2 == null) {
                        this.clients = bufferedSubscription3;
                    } else {
                        bufferedSubscription2.next = bufferedSubscription3;
                    }
                } else {
                    long j2 = bufferedSubscription.demand - ((long) iEstimateLag);
                    if (j2 < j) {
                        j = j2;
                    }
                    z = true;
                    bufferedSubscription2 = bufferedSubscription;
                }
                bufferedSubscription = bufferedSubscription3;
            }
        }
        if (z) {
            return j;
        }
        return 0L;
    }

    public int estimateMaximumLag() {
        int i;
        synchronized (this) {
            BufferedSubscription<T> bufferedSubscription = this.clients;
            i = 0;
            BufferedSubscription<T> bufferedSubscription2 = null;
            while (bufferedSubscription != null) {
                BufferedSubscription<T> bufferedSubscription3 = bufferedSubscription.next;
                int iEstimateLag = bufferedSubscription.estimateLag();
                if (iEstimateLag < 0) {
                    bufferedSubscription.next = null;
                    if (bufferedSubscription2 == null) {
                        this.clients = bufferedSubscription3;
                    } else {
                        bufferedSubscription2.next = bufferedSubscription3;
                    }
                } else {
                    if (iEstimateLag > i) {
                        i = iEstimateLag;
                    }
                    bufferedSubscription2 = bufferedSubscription;
                }
                bufferedSubscription = bufferedSubscription3;
            }
        }
        return i;
    }

    public CompletableFuture<Void> consume(Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        subscribe(new ConsumerSubscriber(completableFuture, consumer));
        return completableFuture;
    }

    static final class ConsumerSubscriber<T> implements Flow.Subscriber<T> {
        final Consumer<? super T> consumer;
        final CompletableFuture<Void> status;
        Flow.Subscription subscription;

        ConsumerSubscriber(CompletableFuture<Void> completableFuture, Consumer<? super T> consumer) {
            this.status = completableFuture;
            this.consumer = consumer;
        }

        @Override // java9.util.concurrent.Flow.Subscriber
        public final void onSubscribe(final Flow.Subscription subscription) {
            this.subscription = subscription;
            this.status.whenComplete((BiConsumer<? super Void, ? super Throwable>) new BiConsumer<Void, Throwable>() { // from class: java9.util.concurrent.SubmissionPublisher.ConsumerSubscriber.1
                @Override // java9.util.function.BiConsumer
                public void accept(Void r1, Throwable th) {
                    subscription.cancel();
                }
            });
            if (this.status.isDone()) {
                return;
            }
            subscription.request(LongCompanionObject.MAX_VALUE);
        }

        @Override // java9.util.concurrent.Flow.Subscriber
        public final void onError(Throwable th) {
            this.status.completeExceptionally(th);
        }

        @Override // java9.util.concurrent.Flow.Subscriber
        public final void onComplete() {
            this.status.complete(null);
        }

        @Override // java9.util.concurrent.Flow.Subscriber
        public final void onNext(T t) {
            try {
                this.consumer.accept(t);
            } catch (Throwable th) {
                this.subscription.cancel();
                this.status.completeExceptionally(th);
            }
        }
    }

    static final class ConsumerTask<T> extends ForkJoinTask<Void> implements Runnable, CompletableFuture.AsynchronousCompletionTask {
        final BufferedSubscription<T> consumer;

        @Override // java9.util.concurrent.ForkJoinTask
        public final Void getRawResult() {
            return null;
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final void setRawResult(Void r1) {
        }

        ConsumerTask(BufferedSubscription<T> bufferedSubscription) {
            this.consumer = bufferedSubscription;
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final boolean exec() {
            this.consumer.consume();
            return false;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.consumer.consume();
        }
    }

    static final class BufferedSubscription<T> implements Flow.Subscription, ForkJoinPool.ManagedBlocker {
        private static final int ABASE;
        static final int ACTIVE = 2;
        private static final int ASHIFT;
        static final int CLOSED = 1;
        static final int COMPLETE = 16;
        private static final long CTL;
        private static final long DEMAND;
        static final int ERROR = 8;
        static final long INTERRUPTED = -1;
        static final int OPEN = 64;
        static final int REQS = 4;
        static final int RUN = 32;
        private static final Unsafe U;
        Object[] array;
        volatile int ctl;
        volatile long demand;
        Executor executor;
        int head;
        final int maxCapacity;
        BufferedSubscription<T> next;
        BufferedSubscription<T> nextRetry;
        final BiConsumer<? super Flow.Subscriber<? super T>, ? super Throwable> onNextHandler;
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
        volatile long pad1f;
        volatile long pad20;
        volatile long pad21;
        volatile long pad22;
        volatile long pad23;
        volatile long pad24;
        volatile long pad25;
        volatile long pad26;
        volatile long pad27;
        volatile long pad28;
        volatile long pad29;
        volatile long pad2a;
        volatile long pad2b;
        volatile long pad2c;
        volatile long pad2d;
        volatile long pad2e;
        volatile Object pad2f;
        volatile Object pad30;
        volatile Object pad31;
        volatile Object pad32;
        volatile Object pad33;
        volatile Object pad34;
        volatile Object pad35;
        volatile Object pad36;
        volatile Object pad37;
        volatile Object pad38;
        volatile Object pad39;
        volatile Object pad3a;
        volatile Object pad3b;
        volatile Object pad3c;
        volatile Object pad3d;
        volatile Object pad3e;
        Throwable pendingError;
        final Flow.Subscriber<? super T> subscriber;
        int tail;
        long timeout;
        Thread waiter;
        volatile int waiting;

        BufferedSubscription(Flow.Subscriber<? super T> subscriber, Executor executor, BiConsumer<? super Flow.Subscriber<? super T>, ? super Throwable> biConsumer, Object[] objArr, int i) {
            this.subscriber = subscriber;
            this.executor = executor;
            this.onNextHandler = biConsumer;
            this.array = objArr;
            this.maxCapacity = i;
        }

        static int getAndBitwiseOr(Object obj, long j, int i) {
            Unsafe unsafe;
            int intVolatile;
            do {
                unsafe = U;
                intVolatile = unsafe.getIntVolatile(obj, j);
            } while (!unsafe.compareAndSwapInt(obj, j, intVolatile, intVolatile | i));
            return intVolatile;
        }

        final boolean weakCasCtl(int i, int i2) {
            return U.compareAndSwapInt(this, CTL, i, i2);
        }

        final int getAndBitwiseOrCtl(int i) {
            return getAndBitwiseOr(this, CTL, i);
        }

        final long subtractDemand(int i) {
            long j = -i;
            return j + ForkJoinPool.getAndAddLong(this, DEMAND, j);
        }

        final boolean casDemand(long j, long j2) {
            return U.compareAndSwapLong(this, DEMAND, j, j2);
        }

        static Object getAndSetArrayElement(Object[] objArr, int i, Object obj) {
            Unsafe unsafe;
            Object objectVolatile;
            long offsetFromArrayIndex = getOffsetFromArrayIndex(i);
            do {
                unsafe = U;
                objectVolatile = unsafe.getObjectVolatile(objArr, offsetFromArrayIndex);
            } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(unsafe, objArr, offsetFromArrayIndex, objectVolatile, obj));
            return objectVolatile;
        }

        static Object getArrayElementVolatile(Object[] objArr, int i) {
            return U.getObjectVolatile(objArr, getOffsetFromArrayIndex(i));
        }

        static void setArrayElementVolatile(Object[] objArr, int i, Object obj) {
            U.putObjectVolatile(objArr, getOffsetFromArrayIndex(i), obj);
        }

        static boolean compareAndSetArrayElement(Object[] objArr, int i, Object obj, Object obj2) {
            return LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(U, objArr, getOffsetFromArrayIndex(i), obj, obj2);
        }

        static long getOffsetFromArrayIndex(int i) {
            return (((long) i) << ASHIFT) + ((long) ABASE);
        }

        final boolean isClosed() {
            return (this.ctl & 1) != 0;
        }

        final int estimateLag() {
            int i = this.ctl;
            int i2 = this.tail - this.head;
            if ((i & 1) != 0) {
                return -1;
            }
            if (i2 < 0) {
                return 0;
            }
            return i2;
        }

        final int offer(T t, boolean z) {
            boolean zCompareAndSetArrayElement;
            Object[] objArr = this.array;
            int i = 0;
            int length = objArr == null ? 0 : objArr.length;
            int i2 = this.tail;
            int i3 = (length - 1) & i2;
            int i4 = i2 + 1;
            int i5 = i4 - this.head;
            if (length > 0) {
                if (i5 >= length && length < this.maxCapacity) {
                    zCompareAndSetArrayElement = growAndOffer(t, objArr, i2);
                } else if (i5 >= length || z) {
                    zCompareAndSetArrayElement = compareAndSetArrayElement(objArr, i3, null, t);
                } else {
                    setArrayElementVolatile(objArr, i3, t);
                    zCompareAndSetArrayElement = true;
                }
                if (zCompareAndSetArrayElement) {
                    this.tail = i4;
                    i = i5;
                }
            }
            return startOnOffer(i);
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0015 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0016  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        final boolean growAndOffer(T r8, java.lang.Object[] r9, int r10) {
            /*
                r7 = this;
                r0 = 0
                r1 = 0
                if (r9 == 0) goto L10
                int r2 = r9.length
                if (r2 <= 0) goto Le
                int r3 = r2 << 1
                if (r3 <= 0) goto L12
                java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch: java.lang.OutOfMemoryError -> L12
                goto L13
            Le:
                r3 = r0
                goto L12
            L10:
                r2 = r0
                r3 = r2
            L12:
                r4 = r1
            L13:
                if (r4 != 0) goto L16
                return r0
            L16:
                r0 = 1
                int r3 = r3 - r0
                int r5 = r10 + (-1)
                r10 = r10 & r3
                r4[r10] = r8
                int r2 = r2 - r0
                r8 = r2
            L1f:
                if (r8 < 0) goto L33
                r10 = r5 & r2
                java.lang.Object r10 = getAndSetArrayElement(r9, r10, r1)
                if (r10 != 0) goto L2a
                goto L33
            L2a:
                int r6 = r5 + (-1)
                r5 = r5 & r3
                r4[r5] = r10
                int r8 = r8 + (-1)
                r5 = r6
                goto L1f
            L33:
                r7.array = r4
                java9.util.concurrent.ForkJoinPool.MemBar.fullFence()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: java9.util.concurrent.SubmissionPublisher.BufferedSubscription.growAndOffer(java.lang.Object, java.lang.Object[], int):boolean");
        }

        final int retryOffer(T t) {
            int i;
            int length;
            int i2 = this.tail;
            int i3 = this.head;
            Object[] objArr = this.array;
            if (objArr == null || (length = objArr.length) <= 0 || !compareAndSetArrayElement(objArr, (length - 1) & i2, null, t)) {
                i = 0;
            } else {
                int i4 = i2 + 1;
                this.tail = i4;
                i = i4 - i3;
            }
            return startOnOffer(i);
        }

        final int startOnOffer(int i) {
            int andBitwiseOrCtl = this.ctl;
            if ((andBitwiseOrCtl & 6) == 4) {
                andBitwiseOrCtl = getAndBitwiseOrCtl(34);
                if ((andBitwiseOrCtl & 33) == 0) {
                    tryStart();
                    return i;
                }
            }
            if ((andBitwiseOrCtl & 1) != 0) {
                return -1;
            }
            return i;
        }

        final void tryStart() {
            try {
                ConsumerTask consumerTask = new ConsumerTask(this);
                Executor executor = this.executor;
                if (executor != null) {
                    executor.execute(consumerTask);
                }
            } catch (Error e) {
                getAndBitwiseOrCtl(9);
                throw e;
            } catch (RuntimeException e2) {
                getAndBitwiseOrCtl(9);
                throw e2;
            }
        }

        final void startOnSignal(int i) {
            if ((this.ctl & i) == i || (getAndBitwiseOrCtl(i) & 33) != 0) {
                return;
            }
            tryStart();
        }

        final void onSubscribe() {
            startOnSignal(34);
        }

        final void onComplete() {
            startOnSignal(50);
        }

        final void onError(Throwable th) {
            if (th != null) {
                this.pendingError = th;
            }
            int andBitwiseOrCtl = getAndBitwiseOrCtl(42);
            if ((andBitwiseOrCtl & 1) == 0) {
                if ((andBitwiseOrCtl & 32) == 0) {
                    tryStart();
                    return;
                }
                Object[] objArr = this.array;
                if (objArr != null) {
                    Arrays.fill(objArr, (Object) null);
                }
            }
        }

        @Override // java9.util.concurrent.Flow.Subscription
        public final void cancel() {
            onError(null);
        }

        @Override // java9.util.concurrent.Flow.Subscription
        public final void request(long j) {
            long j2;
            long j3;
            if (j > 0) {
                do {
                    j2 = this.demand;
                    j3 = j2 + j;
                    if (j3 < j2) {
                        j3 = LongCompanionObject.MAX_VALUE;
                    }
                } while (!casDemand(j2, j3));
                startOnSignal(38);
                return;
            }
            onError(new IllegalArgumentException("non-positive subscription request"));
        }

        final void consume() {
            Flow.Subscriber<? super T> subscriber = this.subscriber;
            if (subscriber == null) {
                return;
            }
            subscribeOnOpen(subscriber);
            long jSubtractDemand = this.demand;
            int i = this.head;
            int i2 = this.tail;
            while (true) {
                int i3 = this.ctl;
                if ((i3 & 8) != 0) {
                    closeOnError(subscriber, null);
                    return;
                }
                int iTakeItems = takeItems(subscriber, jSubtractDemand, i);
                if (iTakeItems > 0) {
                    i += iTakeItems;
                    this.head = i;
                    jSubtractDemand = subtractDemand(iTakeItems);
                } else {
                    jSubtractDemand = this.demand;
                    if (jSubtractDemand == 0 && (i3 & 4) != 0) {
                        weakCasCtl(i3, i3 & (-5));
                    } else if (jSubtractDemand != 0 && (i3 & 4) == 0) {
                        weakCasCtl(i3, i3 | 4);
                    } else {
                        int i4 = this.tail;
                        if (i2 == i4) {
                            boolean z = i4 == i;
                            if (z && (i3 & 16) != 0) {
                                closeOnComplete(subscriber);
                                return;
                            } else if (z || jSubtractDemand == 0) {
                                int i5 = (i3 & 2) != 0 ? 2 : 32;
                                if (weakCasCtl(i3, (~i5) & i3) && i5 == 32) {
                                    return;
                                }
                            }
                        }
                        i2 = i4;
                    }
                }
            }
        }

        final int takeItems(Flow.Subscriber<? super T> subscriber, long j, int i) {
            int length;
            Object[] objArr = this.array;
            int i2 = 0;
            if (objArr != null && (length = objArr.length) > 0) {
                int i3 = length - 1;
                int i4 = (i3 >>> 3) + 1;
                if (j < i4) {
                    i4 = (int) j;
                }
                while (i2 < i4) {
                    Object andSetArrayElement = getAndSetArrayElement(objArr, i & i3, null);
                    if (this.waiting != 0) {
                        signalWaiter();
                    }
                    if (andSetArrayElement == null || !consumeNext(subscriber, andSetArrayElement)) {
                        break;
                    }
                    i++;
                    i2++;
                }
            }
            return i2;
        }

        final boolean consumeNext(Flow.Subscriber<? super T> subscriber, Object obj) {
            if (subscriber == null) {
                return true;
            }
            try {
                subscriber.onNext(obj);
                return true;
            } catch (Throwable th) {
                handleOnNext(subscriber, th);
                return false;
            }
        }

        final void handleOnNext(Flow.Subscriber<? super T> subscriber, Throwable th) {
            try {
                BiConsumer<? super Flow.Subscriber<? super T>, ? super Throwable> biConsumer = this.onNextHandler;
                if (biConsumer != null) {
                    biConsumer.accept(subscriber, th);
                }
            } catch (Throwable unused) {
            }
            closeOnError(subscriber, th);
        }

        final void subscribeOnOpen(Flow.Subscriber<? super T> subscriber) {
            if ((this.ctl & 64) == 0 && (getAndBitwiseOrCtl(64) & 64) == 0) {
                consumeSubscribe(subscriber);
            }
        }

        final void consumeSubscribe(Flow.Subscriber<? super T> subscriber) {
            if (subscriber != null) {
                try {
                    subscriber.onSubscribe(this);
                } catch (Throwable th) {
                    closeOnError(subscriber, th);
                }
            }
        }

        final void closeOnComplete(Flow.Subscriber<? super T> subscriber) {
            if ((1 & getAndBitwiseOrCtl(1)) == 0) {
                consumeComplete(subscriber);
            }
        }

        final void consumeComplete(Flow.Subscriber<? super T> subscriber) {
            if (subscriber != null) {
                try {
                    subscriber.onComplete();
                } catch (Throwable unused) {
                }
            }
        }

        final void closeOnError(Flow.Subscriber<? super T> subscriber, Throwable th) {
            if ((getAndBitwiseOrCtl(9) & 1) == 0) {
                if (th == null) {
                    th = this.pendingError;
                }
                this.pendingError = null;
                this.executor = null;
                signalWaiter();
                consumeError(subscriber, th);
            }
        }

        final void consumeError(Flow.Subscriber<? super T> subscriber, Throwable th) {
            if (th == null || subscriber == null) {
                return;
            }
            try {
                subscriber.onError(th);
            } catch (Throwable unused) {
            }
        }

        final void signalWaiter() {
            this.waiting = 0;
            Thread thread = this.waiter;
            if (thread != null) {
                LockSupport.unpark(thread);
            }
        }

        @Override // java9.util.concurrent.ForkJoinPool.ManagedBlocker
        public final boolean isReleasable() {
            int length;
            if ((this.ctl & 1) != 0) {
                return true;
            }
            Object[] objArr = this.array;
            return objArr != null && (length = objArr.length) > 0 && getArrayElementVolatile(objArr, (length - 1) & this.tail) == null;
        }

        final void awaitSpace(long j) {
            if (isReleasable()) {
                return;
            }
            ForkJoinPool.helpAsyncBlocker(this.executor, this);
            if (isReleasable()) {
                return;
            }
            this.timeout = j;
            try {
                ForkJoinPool.managedBlock(this);
            } catch (InterruptedException unused) {
                this.timeout = -1L;
            }
            if (this.timeout == -1) {
                Thread.currentThread().interrupt();
            }
        }

        @Override // java9.util.concurrent.ForkJoinPool.ManagedBlocker
        public final boolean block() {
            long jNanoTime = this.timeout;
            boolean z = jNanoTime < LongCompanionObject.MAX_VALUE;
            long jNanoTime2 = z ? System.nanoTime() + jNanoTime : 0L;
            while (!isReleasable()) {
                if (Thread.interrupted()) {
                    this.timeout = -1L;
                    if (z) {
                        break;
                    }
                } else {
                    if (z) {
                        jNanoTime = jNanoTime2 - System.nanoTime();
                        if (jNanoTime <= 0) {
                            break;
                        }
                    }
                    if (this.waiter == null) {
                        this.waiter = Thread.currentThread();
                    } else if (this.waiting == 0) {
                        this.waiting = 1;
                    } else if (z) {
                        LockSupport.parkNanos(this, jNanoTime);
                    } else {
                        LockSupport.park(this);
                    }
                }
            }
            this.waiter = null;
            this.waiting = 0;
            return true;
        }

        static {
            Unsafe unsafe = UnsafeAccess.unsafe;
            U = unsafe;
            try {
                CTL = unsafe.objectFieldOffset(BufferedSubscription.class.getDeclaredField("ctl"));
                DEMAND = unsafe.objectFieldOffset(BufferedSubscription.class.getDeclaredField("demand"));
                ABASE = unsafe.arrayBaseOffset(Object[].class);
                int iArrayIndexScale = unsafe.arrayIndexScale(Object[].class);
                if (((iArrayIndexScale - 1) & iArrayIndexScale) != 0) {
                    throw new ExceptionInInitializerError("data type scale not a power of two");
                }
                ASHIFT = 31 - Integer.numberOfLeadingZeros(iArrayIndexScale);
            } catch (Exception e) {
                throw new ExceptionInInitializerError(e);
            }
        }
    }
}
