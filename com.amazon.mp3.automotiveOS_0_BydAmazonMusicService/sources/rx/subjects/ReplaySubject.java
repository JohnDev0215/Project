package rx.subjects;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.internal.operators.BackpressureUtils;
import rx.plugins.RxJavaHooks;

/* JADX INFO: loaded from: classes8.dex */
public final class ReplaySubject<T> extends Subject<T, T> {
    private static final Object[] EMPTY_ARRAY = new Object[0];
    final ReplayState<T> state;

    interface ReplayBuffer<T> {
        void complete();

        void drain(ReplayProducer<T> replayProducer);

        void error(Throwable th);

        void next(T t);
    }

    public static <T> ReplaySubject<T> create() {
        return create(16);
    }

    public static <T> ReplaySubject<T> create(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("capacity > 0 required but it was " + i);
        }
        return new ReplaySubject<>(new ReplayState(new ReplayUnboundedBuffer(i)));
    }

    ReplaySubject(ReplayState<T> replayState) {
        super(replayState);
        this.state = replayState;
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.state.onNext(t);
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.state.onError(th);
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.state.onCompleted();
    }

    static final class ReplayState<T> extends AtomicReference<ReplayProducer<T>[]> implements Observable.OnSubscribe<T>, Observer<T> {
        static final ReplayProducer[] EMPTY = new ReplayProducer[0];
        static final ReplayProducer[] TERMINATED = new ReplayProducer[0];
        final ReplayBuffer<T> buffer;

        public ReplayState(ReplayBuffer<T> replayBuffer) {
            this.buffer = replayBuffer;
            lazySet(EMPTY);
        }

        @Override // rx.functions.Action1
        public void call(Subscriber<? super T> subscriber) {
            ReplayProducer<T> replayProducer = new ReplayProducer<>(subscriber, this);
            subscriber.add(replayProducer);
            subscriber.setProducer(replayProducer);
            if (add(replayProducer) && replayProducer.isUnsubscribed()) {
                remove(replayProducer);
            } else {
                this.buffer.drain(replayProducer);
            }
        }

        boolean add(ReplayProducer<T> replayProducer) {
            ReplayProducer<T>[] replayProducerArr;
            ReplayProducer[] replayProducerArr2;
            do {
                replayProducerArr = get();
                if (replayProducerArr == TERMINATED) {
                    return false;
                }
                int length = replayProducerArr.length;
                replayProducerArr2 = new ReplayProducer[length + 1];
                System.arraycopy(replayProducerArr, 0, replayProducerArr2, 0, length);
                replayProducerArr2[length] = replayProducer;
            } while (!compareAndSet(replayProducerArr, replayProducerArr2));
            return true;
        }

        void remove(ReplayProducer<T> replayProducer) {
            ReplayProducer<T>[] replayProducerArr;
            ReplayProducer[] replayProducerArr2;
            do {
                replayProducerArr = get();
                if (replayProducerArr == TERMINATED || replayProducerArr == EMPTY) {
                    return;
                }
                int length = replayProducerArr.length;
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    if (replayProducerArr[i2] == replayProducer) {
                        i = i2;
                        break;
                    }
                    i2++;
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    replayProducerArr2 = EMPTY;
                } else {
                    ReplayProducer[] replayProducerArr3 = new ReplayProducer[length - 1];
                    System.arraycopy(replayProducerArr, 0, replayProducerArr3, 0, i);
                    System.arraycopy(replayProducerArr, i + 1, replayProducerArr3, i, (length - i) - 1);
                    replayProducerArr2 = replayProducerArr3;
                }
            } while (!compareAndSet(replayProducerArr, replayProducerArr2));
        }

        @Override // rx.Observer
        public void onNext(T t) {
            ReplayBuffer<T> replayBuffer = this.buffer;
            replayBuffer.next(t);
            for (ReplayProducer<T> replayProducer : get()) {
                replayBuffer.drain(replayProducer);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            ReplayBuffer<T> replayBuffer = this.buffer;
            replayBuffer.error(th);
            ArrayList arrayList = null;
            for (ReplayProducer<T> replayProducer : getAndSet(TERMINATED)) {
                try {
                    replayBuffer.drain(replayProducer);
                } catch (Throwable th2) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th2);
                }
            }
            Exceptions.throwIfAny(arrayList);
        }

        @Override // rx.Observer
        public void onCompleted() {
            ReplayBuffer<T> replayBuffer = this.buffer;
            replayBuffer.complete();
            for (ReplayProducer<T> replayProducer : getAndSet(TERMINATED)) {
                replayBuffer.drain(replayProducer);
            }
        }
    }

    static final class ReplayUnboundedBuffer<T> implements ReplayBuffer<T> {
        final int capacity;
        volatile boolean done;
        Throwable error;
        final Object[] head;
        volatile int size;
        Object[] tail;
        int tailIndex;

        public ReplayUnboundedBuffer(int i) {
            this.capacity = i;
            Object[] objArr = new Object[i + 1];
            this.head = objArr;
            this.tail = objArr;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void next(T t) {
            if (this.done) {
                return;
            }
            int i = this.tailIndex;
            Object[] objArr = this.tail;
            if (i == objArr.length - 1) {
                Object[] objArr2 = new Object[objArr.length];
                objArr2[0] = t;
                this.tailIndex = 1;
                objArr[i] = objArr2;
                this.tail = objArr2;
            } else {
                objArr[i] = t;
                this.tailIndex = i + 1;
            }
            this.size++;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void error(Throwable th) {
            if (this.done) {
                RxJavaHooks.onError(th);
            } else {
                this.error = th;
                this.done = true;
            }
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void complete() {
            this.done = true;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void drain(ReplayProducer<T> replayProducer) {
            if (replayProducer.getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super T> subscriber = replayProducer.actual;
            int i = this.capacity;
            int iAddAndGet = 1;
            do {
                long j = replayProducer.requested.get();
                Object[] objArr = (Object[]) replayProducer.node;
                if (objArr == null) {
                    objArr = this.head;
                }
                int i2 = replayProducer.tailIndex;
                int i3 = replayProducer.index;
                long j2 = 0;
                while (true) {
                    if (j2 == j) {
                        break;
                    }
                    if (subscriber.isUnsubscribed()) {
                        replayProducer.node = null;
                        return;
                    }
                    boolean z = this.done;
                    boolean z2 = i3 == this.size;
                    if (z && z2) {
                        replayProducer.node = null;
                        Throwable th = this.error;
                        if (th != null) {
                            subscriber.onError(th);
                            return;
                        } else {
                            subscriber.onCompleted();
                            return;
                        }
                    }
                    if (z2) {
                        break;
                    }
                    if (i2 == i) {
                        objArr = (Object[]) objArr[i2];
                        i2 = 0;
                    }
                    subscriber.onNext(objArr[i2]);
                    j2++;
                    i2++;
                    i3++;
                }
                if (j2 == j) {
                    if (subscriber.isUnsubscribed()) {
                        replayProducer.node = null;
                        return;
                    }
                    boolean z3 = this.done;
                    boolean z4 = i3 == this.size;
                    if (z3 && z4) {
                        replayProducer.node = null;
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            subscriber.onError(th2);
                            return;
                        } else {
                            subscriber.onCompleted();
                            return;
                        }
                    }
                }
                if (j2 != 0 && j != Long.MAX_VALUE) {
                    BackpressureUtils.produced(replayProducer.requested, j2);
                }
                replayProducer.index = i3;
                replayProducer.tailIndex = i2;
                replayProducer.node = objArr;
                iAddAndGet = replayProducer.addAndGet(-iAddAndGet);
            } while (iAddAndGet != 0);
        }
    }

    static final class ReplayProducer<T> extends AtomicInteger implements Producer, Subscription {
        final Subscriber<? super T> actual;
        int index;
        Object node;
        final AtomicLong requested = new AtomicLong();
        final ReplayState<T> state;
        int tailIndex;

        public ReplayProducer(Subscriber<? super T> subscriber, ReplayState<T> replayState) {
            this.actual = subscriber;
            this.state = replayState;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.state.remove(this);
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.actual.isUnsubscribed();
        }

        @Override // rx.Producer
        public void request(long j) {
            if (j > 0) {
                BackpressureUtils.getAndAddRequest(this.requested, j);
                this.state.buffer.drain(this);
            } else {
                if (j >= 0) {
                    return;
                }
                throw new IllegalArgumentException("n >= required but it was " + j);
            }
        }
    }
}
