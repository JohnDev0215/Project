package rx.internal.operators;

import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action0;
import rx.functions.Func0;
import rx.internal.util.OpenHashSet;
import rx.observables.ConnectableObservable;
import rx.schedulers.Timestamped;
import rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes8.dex */
public final class OperatorReplay<T> extends ConnectableObservable<T> implements Subscription {
    static final Func0 DEFAULT_UNBOUNDED_FACTORY = new Func0() { // from class: rx.internal.operators.OperatorReplay.1
        @Override // rx.functions.Func0, java.util.concurrent.Callable
        public Object call() {
            return new UnboundedReplayBuffer(16);
        }
    };
    final Func0<? extends ReplayBuffer<T>> bufferFactory;
    final AtomicReference<ReplaySubscriber<T>> current;
    final Observable<? extends T> source;

    interface ReplayBuffer<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(InnerProducer<T> innerProducer);
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> observable) {
        return create(observable, DEFAULT_UNBOUNDED_FACTORY);
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> observable, final int i) {
        if (i == Integer.MAX_VALUE) {
            return create(observable);
        }
        return create(observable, new Func0<ReplayBuffer<T>>() { // from class: rx.internal.operators.OperatorReplay.5
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            public ReplayBuffer<T> call() {
                return new SizeBoundReplayBuffer(i);
            }
        });
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> observable, long j, TimeUnit timeUnit, Scheduler scheduler) {
        return create(observable, j, timeUnit, scheduler, Integer.MAX_VALUE);
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> observable, long j, TimeUnit timeUnit, final Scheduler scheduler, final int i) {
        final long millis = timeUnit.toMillis(j);
        return create(observable, new Func0<ReplayBuffer<T>>() { // from class: rx.internal.operators.OperatorReplay.6
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            public ReplayBuffer<T> call() {
                return new SizeAndTimeBoundReplayBuffer(i, millis, scheduler);
            }
        });
    }

    static <T> ConnectableObservable<T> create(Observable<? extends T> observable, final Func0<? extends ReplayBuffer<T>> func0) {
        final AtomicReference atomicReference = new AtomicReference();
        return new OperatorReplay(new Observable.OnSubscribe<T>() { // from class: rx.internal.operators.OperatorReplay.7
            @Override // rx.functions.Action1
            public void call(Subscriber<? super T> subscriber) {
                ReplaySubscriber replaySubscriber;
                while (true) {
                    replaySubscriber = (ReplaySubscriber) atomicReference.get();
                    if (replaySubscriber != null) {
                        break;
                    }
                    ReplaySubscriber replaySubscriber2 = new ReplaySubscriber((ReplayBuffer) func0.call());
                    replaySubscriber2.init();
                    if (CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, replaySubscriber, replaySubscriber2)) {
                        replaySubscriber = replaySubscriber2;
                        break;
                    }
                }
                InnerProducer<T> innerProducer = new InnerProducer<>(replaySubscriber, subscriber);
                replaySubscriber.add((InnerProducer) innerProducer);
                subscriber.add(innerProducer);
                replaySubscriber.buffer.replay(innerProducer);
                subscriber.setProducer(innerProducer);
            }
        }, observable, atomicReference, func0);
    }

    private OperatorReplay(Observable.OnSubscribe<T> onSubscribe, Observable<? extends T> observable, AtomicReference<ReplaySubscriber<T>> atomicReference, Func0<? extends ReplayBuffer<T>> func0) {
        super(onSubscribe);
        this.source = observable;
        this.current = atomicReference;
        this.bufferFactory = func0;
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        this.current.lazySet(null);
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        ReplaySubscriber<T> replaySubscriber = this.current.get();
        return replaySubscriber == null || replaySubscriber.isUnsubscribed();
    }

    static final class ReplaySubscriber<T> extends Subscriber<T> {
        static final InnerProducer[] EMPTY = new InnerProducer[0];
        static final InnerProducer[] TERMINATED = new InnerProducer[0];
        final ReplayBuffer<T> buffer;
        boolean coordinateAll;
        List<InnerProducer<T>> coordinationQueue;
        boolean done;
        boolean emitting;
        long maxChildRequested;
        long maxUpstreamRequested;
        boolean missed;
        volatile Producer producer;
        long producersCacheVersion;
        volatile long producersVersion;
        volatile boolean terminated;
        final OpenHashSet<InnerProducer<T>> producers = new OpenHashSet<>();
        InnerProducer<T>[] producersCache = EMPTY;
        final AtomicBoolean shouldConnect = new AtomicBoolean();

        public ReplaySubscriber(ReplayBuffer<T> replayBuffer) {
            this.buffer = replayBuffer;
            request(0L);
        }

        void init() {
            add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorReplay.ReplaySubscriber.1
                @Override // rx.functions.Action0
                public void call() {
                    if (ReplaySubscriber.this.terminated) {
                        return;
                    }
                    synchronized (ReplaySubscriber.this.producers) {
                        if (!ReplaySubscriber.this.terminated) {
                            ReplaySubscriber.this.producers.terminate();
                            ReplaySubscriber.this.producersVersion++;
                            ReplaySubscriber.this.terminated = true;
                        }
                    }
                }
            }));
        }

        boolean add(InnerProducer<T> innerProducer) {
            innerProducer.getClass();
            if (this.terminated) {
                return false;
            }
            synchronized (this.producers) {
                if (this.terminated) {
                    return false;
                }
                this.producers.add(innerProducer);
                this.producersVersion++;
                return true;
            }
        }

        void remove(InnerProducer<T> innerProducer) {
            if (this.terminated) {
                return;
            }
            synchronized (this.producers) {
                if (this.terminated) {
                    return;
                }
                this.producers.remove(innerProducer);
                if (this.producers.isEmpty()) {
                    this.producersCache = EMPTY;
                }
                this.producersVersion++;
            }
        }

        @Override // rx.Subscriber
        public void setProducer(Producer producer) {
            if (this.producer != null) {
                throw new IllegalStateException("Only a single producer can be set on a Subscriber.");
            }
            this.producer = producer;
            manageRequests(null);
            replay();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            this.buffer.next(t);
            replay();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                return;
            }
            this.done = true;
            try {
                this.buffer.error(th);
                replay();
            } finally {
                unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            try {
                this.buffer.complete();
                replay();
            } finally {
                unsubscribe();
            }
        }

        void manageRequests(InnerProducer<T> innerProducer) {
            long jMax;
            List<InnerProducer<T>> list;
            boolean z;
            long jMax2;
            if (isUnsubscribed()) {
                return;
            }
            synchronized (this) {
                if (this.emitting) {
                    if (innerProducer != null) {
                        List arrayList = this.coordinationQueue;
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                            this.coordinationQueue = arrayList;
                        }
                        arrayList.add(innerProducer);
                    } else {
                        this.coordinateAll = true;
                    }
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                long j = this.maxChildRequested;
                if (innerProducer != null) {
                    jMax = Math.max(j, innerProducer.totalRequested.get());
                } else {
                    long jMax3 = j;
                    for (InnerProducer<T> innerProducer2 : copyProducers()) {
                        if (innerProducer2 != null) {
                            jMax3 = Math.max(jMax3, innerProducer2.totalRequested.get());
                        }
                    }
                    jMax = jMax3;
                }
                makeRequest(jMax, j);
                while (!isUnsubscribed()) {
                    synchronized (this) {
                        if (!this.missed) {
                            this.emitting = false;
                            return;
                        }
                        this.missed = false;
                        list = this.coordinationQueue;
                        this.coordinationQueue = null;
                        z = this.coordinateAll;
                        this.coordinateAll = false;
                    }
                    long j2 = this.maxChildRequested;
                    if (list != null) {
                        Iterator<InnerProducer<T>> it = list.iterator();
                        jMax2 = j2;
                        while (it.hasNext()) {
                            jMax2 = Math.max(jMax2, it.next().totalRequested.get());
                        }
                    } else {
                        jMax2 = j2;
                    }
                    if (z) {
                        for (InnerProducer<T> innerProducer3 : copyProducers()) {
                            if (innerProducer3 != null) {
                                jMax2 = Math.max(jMax2, innerProducer3.totalRequested.get());
                            }
                        }
                    }
                    makeRequest(jMax2, j2);
                }
            }
        }

        InnerProducer<T>[] copyProducers() {
            InnerProducer<T>[] innerProducerArr;
            synchronized (this.producers) {
                InnerProducer<T>[] innerProducerArrValues = this.producers.values();
                int length = innerProducerArrValues.length;
                innerProducerArr = new InnerProducer[length];
                System.arraycopy(innerProducerArrValues, 0, innerProducerArr, 0, length);
            }
            return innerProducerArr;
        }

        void makeRequest(long j, long j2) {
            long j3 = this.maxUpstreamRequested;
            Producer producer = this.producer;
            long j4 = j - j2;
            if (j4 == 0) {
                if (j3 == 0 || producer == null) {
                    return;
                }
                this.maxUpstreamRequested = 0L;
                producer.request(j3);
                return;
            }
            this.maxChildRequested = j;
            if (producer == null) {
                long j5 = j3 + j4;
                if (j5 < 0) {
                    j5 = Long.MAX_VALUE;
                }
                this.maxUpstreamRequested = j5;
                return;
            }
            if (j3 != 0) {
                this.maxUpstreamRequested = 0L;
                producer.request(j3 + j4);
            } else {
                producer.request(j4);
            }
        }

        void replay() {
            InnerProducer<T>[] innerProducerArr = this.producersCache;
            if (this.producersCacheVersion != this.producersVersion) {
                synchronized (this.producers) {
                    innerProducerArr = this.producersCache;
                    InnerProducer<T>[] innerProducerArrValues = this.producers.values();
                    int length = innerProducerArrValues.length;
                    if (innerProducerArr.length != length) {
                        innerProducerArr = new InnerProducer[length];
                        this.producersCache = innerProducerArr;
                    }
                    System.arraycopy(innerProducerArrValues, 0, innerProducerArr, 0, length);
                    this.producersCacheVersion = this.producersVersion;
                }
            }
            ReplayBuffer<T> replayBuffer = this.buffer;
            for (InnerProducer<T> innerProducer : innerProducerArr) {
                if (innerProducer != null) {
                    replayBuffer.replay(innerProducer);
                }
            }
        }
    }

    static final class InnerProducer<T> extends AtomicLong implements Producer, Subscription {
        Subscriber<? super T> child;
        boolean emitting;
        Object index;
        boolean missed;
        final ReplaySubscriber<T> parent;
        final AtomicLong totalRequested = new AtomicLong();

        public InnerProducer(ReplaySubscriber<T> replaySubscriber, Subscriber<? super T> subscriber) {
            this.parent = replaySubscriber;
            this.child = subscriber;
        }

        @Override // rx.Producer
        public void request(long j) {
            long j2;
            long j3;
            if (j < 0) {
                return;
            }
            do {
                j2 = get();
                if (j2 == Long.MIN_VALUE) {
                    return;
                }
                if (j2 >= 0 && j == 0) {
                    return;
                }
                j3 = j2 + j;
                if (j3 < 0) {
                    j3 = Long.MAX_VALUE;
                }
            } while (!compareAndSet(j2, j3));
            addTotalRequested(j);
            this.parent.manageRequests(this);
            this.parent.buffer.replay(this);
        }

        void addTotalRequested(long j) {
            long j2;
            long j3;
            do {
                j2 = this.totalRequested.get();
                j3 = j2 + j;
                if (j3 < 0) {
                    j3 = Long.MAX_VALUE;
                }
            } while (!this.totalRequested.compareAndSet(j2, j3));
        }

        public long produced(long j) {
            long j2;
            long j3;
            if (j <= 0) {
                throw new IllegalArgumentException("Cant produce zero or less");
            }
            do {
                j2 = get();
                if (j2 == Long.MIN_VALUE) {
                    return Long.MIN_VALUE;
                }
                j3 = j2 - j;
                if (j3 < 0) {
                    throw new IllegalStateException("More produced (" + j + ") than requested (" + j2 + ")");
                }
            } while (!compareAndSet(j2, j3));
            return j3;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (get() == Long.MIN_VALUE || getAndSet(Long.MIN_VALUE) == Long.MIN_VALUE) {
                return;
            }
            this.parent.remove(this);
            this.parent.manageRequests(this);
            this.child = null;
        }

        <U> U index() {
            return (U) this.index;
        }
    }

    static final class UnboundedReplayBuffer<T> extends ArrayList<Object> implements ReplayBuffer<T> {
        volatile int size;

        public UnboundedReplayBuffer(int i) {
            super(i);
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public void next(T t) {
            add(NotificationLite.next(t));
            this.size++;
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public void error(Throwable th) {
            add(NotificationLite.error(th));
            this.size++;
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public void complete() {
            add(NotificationLite.completed());
            this.size++;
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public void replay(InnerProducer<T> innerProducer) {
            synchronized (innerProducer) {
                if (innerProducer.emitting) {
                    innerProducer.missed = true;
                    return;
                }
                innerProducer.emitting = true;
                while (!innerProducer.isUnsubscribed()) {
                    int i = this.size;
                    Integer num = (Integer) innerProducer.index();
                    int iIntValue = num != null ? num.intValue() : 0;
                    Subscriber<? super T> subscriber = innerProducer.child;
                    if (subscriber == null) {
                        return;
                    }
                    long j = innerProducer.get();
                    long j2 = 0;
                    while (j2 != j && iIntValue < i) {
                        Object obj = get(iIntValue);
                        try {
                            if (NotificationLite.accept(subscriber, obj) || innerProducer.isUnsubscribed()) {
                                return;
                            }
                            iIntValue++;
                            j2++;
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            innerProducer.unsubscribe();
                            if (NotificationLite.isError(obj) || NotificationLite.isCompleted(obj)) {
                                return;
                            }
                            subscriber.onError(OnErrorThrowable.addValueAsLastCause(th, NotificationLite.getValue(obj)));
                            return;
                        }
                    }
                    if (j2 != 0) {
                        innerProducer.index = Integer.valueOf(iIntValue);
                        if (j != Long.MAX_VALUE) {
                            innerProducer.produced(j2);
                        }
                    }
                    synchronized (innerProducer) {
                        if (!innerProducer.missed) {
                            innerProducer.emitting = false;
                            return;
                        }
                        innerProducer.missed = false;
                    }
                }
            }
        }
    }

    static final class Node extends AtomicReference<Node> {
        final long index;
        final Object value;

        public Node(Object obj, long j) {
            this.value = obj;
            this.index = j;
        }
    }

    static class BoundedReplayBuffer<T> extends AtomicReference<Node> implements ReplayBuffer<T> {
        long index;
        int size;
        Node tail;

        Object enterTransform(Object obj) {
            return obj;
        }

        Object leaveTransform(Object obj) {
            return obj;
        }

        void truncate() {
        }

        void truncateFinal() {
        }

        public BoundedReplayBuffer() {
            Node node = new Node(null, 0L);
            this.tail = node;
            set(node);
        }

        final void addLast(Node node) {
            this.tail.set(node);
            this.tail = node;
            this.size++;
        }

        final void removeFirst() {
            Node node = get().get();
            if (node == null) {
                throw new IllegalStateException("Empty list!");
            }
            this.size--;
            setFirst(node);
        }

        final void setFirst(Node node) {
            set(node);
        }

        Node getInitialHead() {
            return get();
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public final void next(T t) {
            Object objEnterTransform = enterTransform(NotificationLite.next(t));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(objEnterTransform, j));
            truncate();
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public final void error(Throwable th) {
            Object objEnterTransform = enterTransform(NotificationLite.error(th));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(objEnterTransform, j));
            truncateFinal();
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public final void complete() {
            Object objEnterTransform = enterTransform(NotificationLite.completed());
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(objEnterTransform, j));
            truncateFinal();
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public final void replay(InnerProducer<T> innerProducer) {
            Subscriber<? super T> subscriber;
            Node node;
            synchronized (innerProducer) {
                if (innerProducer.emitting) {
                    innerProducer.missed = true;
                    return;
                }
                innerProducer.emitting = true;
                while (!innerProducer.isUnsubscribed()) {
                    Node initialHead = (Node) innerProducer.index();
                    if (initialHead == null) {
                        initialHead = getInitialHead();
                        innerProducer.index = initialHead;
                        innerProducer.addTotalRequested(initialHead.index);
                    }
                    if (innerProducer.isUnsubscribed() || (subscriber = innerProducer.child) == null) {
                        return;
                    }
                    long j = innerProducer.get();
                    long j2 = 0;
                    while (j2 != j && (node = initialHead.get()) != null) {
                        Object objLeaveTransform = leaveTransform(node.value);
                        try {
                            if (NotificationLite.accept(subscriber, objLeaveTransform)) {
                                innerProducer.index = null;
                                return;
                            }
                            j2++;
                            if (innerProducer.isUnsubscribed()) {
                                return;
                            } else {
                                initialHead = node;
                            }
                        } catch (Throwable th) {
                            innerProducer.index = null;
                            Exceptions.throwIfFatal(th);
                            innerProducer.unsubscribe();
                            if (NotificationLite.isError(objLeaveTransform) || NotificationLite.isCompleted(objLeaveTransform)) {
                                return;
                            }
                            subscriber.onError(OnErrorThrowable.addValueAsLastCause(th, NotificationLite.getValue(objLeaveTransform)));
                            return;
                        }
                    }
                    if (j2 != 0) {
                        innerProducer.index = initialHead;
                        if (j != Long.MAX_VALUE) {
                            innerProducer.produced(j2);
                        }
                    }
                    synchronized (innerProducer) {
                        if (!innerProducer.missed) {
                            innerProducer.emitting = false;
                            return;
                        }
                        innerProducer.missed = false;
                    }
                }
            }
        }
    }

    static final class SizeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        final int limit;

        public SizeBoundReplayBuffer(int i) {
            this.limit = i;
        }

        @Override // rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    static final class SizeAndTimeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        final int limit;
        final long maxAgeInMillis;
        final Scheduler scheduler;

        public SizeAndTimeBoundReplayBuffer(int i, long j, Scheduler scheduler) {
            this.scheduler = scheduler;
            this.limit = i;
            this.maxAgeInMillis = j;
        }

        @Override // rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        Object enterTransform(Object obj) {
            return new Timestamped(this.scheduler.now(), obj);
        }

        @Override // rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        Object leaveTransform(Object obj) {
            return ((Timestamped) obj).getValue();
        }

        @Override // rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        Node getInitialHead() {
            Node node;
            long jNow = this.scheduler.now() - this.maxAgeInMillis;
            Node node2 = get();
            Node node3 = node2.get();
            while (true) {
                Node node4 = node3;
                node = node2;
                node2 = node4;
                if (node2 == null) {
                    break;
                }
                Object obj = node2.value;
                Object objLeaveTransform = leaveTransform(obj);
                if (NotificationLite.isCompleted(objLeaveTransform) || NotificationLite.isError(objLeaveTransform) || ((Timestamped) obj).getTimestampMillis() > jNow) {
                    break;
                }
                node3 = node2.get();
            }
            return node;
        }

        @Override // rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        void truncate() {
            Node node;
            long jNow = this.scheduler.now() - this.maxAgeInMillis;
            Node node2 = get();
            Node node3 = node2.get();
            int i = 0;
            while (true) {
                Node node4 = node3;
                node = node2;
                node2 = node4;
                if (node2 != null) {
                    int i2 = this.size;
                    if (i2 <= this.limit) {
                        if (((Timestamped) node2.value).getTimestampMillis() > jNow) {
                            break;
                        }
                        i++;
                        this.size--;
                        node3 = node2.get();
                    } else {
                        i++;
                        this.size = i2 - 1;
                        node3 = node2.get();
                    }
                } else {
                    break;
                }
            }
            if (i != 0) {
                setFirst(node);
            }
        }

        @Override // rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        void truncateFinal() {
            Node node;
            long jNow = this.scheduler.now() - this.maxAgeInMillis;
            Node node2 = get();
            Node node3 = node2.get();
            int i = 0;
            while (true) {
                Node node4 = node3;
                node = node2;
                node2 = node4;
                if (node2 == null || this.size <= 1 || ((Timestamped) node2.value).getTimestampMillis() > jNow) {
                    break;
                }
                i++;
                this.size--;
                node3 = node2.get();
            }
            if (i != 0) {
                setFirst(node);
            }
        }
    }
}
