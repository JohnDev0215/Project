package rx.internal.operators;

import androidx.core.location.LocationRequestCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.internal.producers.ProducerArbiter;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.UtilityFunctions;
import rx.observables.GroupedObservable;
import rx.observers.Subscribers;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes.dex */
public final class OperatorGroupByEvicting<T, K, V> implements Observable.Operator<GroupedObservable<K, V>, T> {
    final int bufferSize;
    final boolean delayError;
    final Func1<? super T, ? extends K> keySelector;
    final Func1<Action1<Object>, Map<K, Object>> mapFactory;
    final Func1<? super T, ? extends V> valueSelector;

    public OperatorGroupByEvicting(Func1<? super T, ? extends K> func1) {
        this(func1, UtilityFunctions.identity(), RxRingBuffer.SIZE, false, null);
    }

    public OperatorGroupByEvicting(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12) {
        this(func1, func12, RxRingBuffer.SIZE, false, null);
    }

    public OperatorGroupByEvicting(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, int i, boolean z, Func1<Action1<Object>, Map<K, Object>> func13) {
        this.keySelector = func1;
        this.valueSelector = func12;
        this.bufferSize = i;
        this.delayError = z;
        this.mapFactory = func13;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super GroupedObservable<K, V>> subscriber) {
        ConcurrentLinkedQueue concurrentLinkedQueue;
        Map<K, Object> mapCall;
        if (this.mapFactory == null) {
            concurrentLinkedQueue = null;
            mapCall = new ConcurrentHashMap<>();
        } else {
            concurrentLinkedQueue = new ConcurrentLinkedQueue();
            try {
                mapCall = this.mapFactory.call(new EvictionAction(concurrentLinkedQueue));
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, subscriber);
                Subscriber<? super T> subscriberEmpty = Subscribers.empty();
                subscriberEmpty.unsubscribe();
                return subscriberEmpty;
            }
        }
        final GroupBySubscriber groupBySubscriber = new GroupBySubscriber(subscriber, this.keySelector, this.valueSelector, this.bufferSize, this.delayError, mapCall, concurrentLinkedQueue);
        subscriber.add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorGroupByEvicting.1
            @Override // rx.functions.Action0
            public void call() {
                groupBySubscriber.cancel();
            }
        }));
        subscriber.setProducer(groupBySubscriber.producer);
        return groupBySubscriber;
    }

    public static final class GroupByProducer implements Producer {
        final GroupBySubscriber<?, ?, ?> parent;

        public GroupByProducer(GroupBySubscriber<?, ?, ?> groupBySubscriber) {
            this.parent = groupBySubscriber;
        }

        @Override // rx.Producer
        public void request(long j) {
            this.parent.requestMore(j);
        }
    }

    public static final class GroupBySubscriber<T, K, V> extends Subscriber<T> {
        static final Object NULL_KEY = new Object();
        final Subscriber<? super GroupedObservable<K, V>> actual;
        final int bufferSize;
        final AtomicBoolean cancelled;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final Queue<GroupedUnicast<K, V>> evictedGroups;
        final AtomicInteger groupCount;
        final Map<K, GroupedUnicast<K, V>> groups;
        final Func1<? super T, ? extends K> keySelector;
        final GroupByProducer producer;
        final Queue<GroupedUnicast<K, V>> queue = new ConcurrentLinkedQueue();
        final AtomicLong requested;
        final ProducerArbiter s;
        final Func1<? super T, ? extends V> valueSelector;
        final AtomicInteger wip;

        public GroupBySubscriber(Subscriber<? super GroupedObservable<K, V>> subscriber, Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, int i, boolean z, Map<K, GroupedUnicast<K, V>> map, Queue<GroupedUnicast<K, V>> queue) {
            this.actual = subscriber;
            this.keySelector = func1;
            this.valueSelector = func12;
            this.bufferSize = i;
            this.delayError = z;
            ProducerArbiter producerArbiter = new ProducerArbiter();
            this.s = producerArbiter;
            producerArbiter.request(i);
            this.producer = new GroupByProducer(this);
            this.cancelled = new AtomicBoolean();
            this.requested = new AtomicLong();
            this.groupCount = new AtomicInteger(1);
            this.wip = new AtomicInteger();
            this.groups = map;
            this.evictedGroups = queue;
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.s.setProducer(producer);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            Queue<?> queue = this.queue;
            Subscriber<? super GroupedObservable<K, V>> subscriber = this.actual;
            try {
                K kCall = this.keySelector.call(t);
                Object obj = kCall != null ? kCall : NULL_KEY;
                GroupedUnicast<K, V> groupedUnicastCreateWith = this.groups.get(obj);
                if (groupedUnicastCreateWith == null) {
                    if (this.cancelled.get()) {
                        return;
                    }
                    groupedUnicastCreateWith = GroupedUnicast.createWith(kCall, this.bufferSize, this, this.delayError);
                    this.groups.put((K) obj, groupedUnicastCreateWith);
                    this.groupCount.getAndIncrement();
                    queue.offer(groupedUnicastCreateWith);
                    drain();
                }
                try {
                    groupedUnicastCreateWith.onNext(this.valueSelector.call(t));
                    if (this.evictedGroups == null) {
                        return;
                    }
                    while (true) {
                        GroupedUnicast<K, V> groupedUnicastPoll = this.evictedGroups.poll();
                        if (groupedUnicastPoll == null) {
                            return;
                        } else {
                            groupedUnicastPoll.onComplete();
                        }
                    }
                } catch (Throwable th) {
                    unsubscribe();
                    errorAll(subscriber, queue, th);
                }
            } catch (Throwable th2) {
                unsubscribe();
                errorAll(subscriber, queue, th2);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaHooks.onError(th);
                return;
            }
            this.error = th;
            this.done = true;
            this.groupCount.decrementAndGet();
            drain();
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            Iterator<GroupedUnicast<K, V>> it = this.groups.values().iterator();
            while (it.hasNext()) {
                it.next().onComplete();
            }
            this.groups.clear();
            Queue<GroupedUnicast<K, V>> queue = this.evictedGroups;
            if (queue != null) {
                queue.clear();
            }
            this.done = true;
            this.groupCount.decrementAndGet();
            drain();
        }

        public void requestMore(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            }
            BackpressureUtils.getAndAddRequest(this.requested, j);
            drain();
        }

        public void cancel() {
            if (this.cancelled.compareAndSet(false, true) && this.groupCount.decrementAndGet() == 0) {
                unsubscribe();
            }
        }

        public void cancel(K k) {
            if (k == null) {
                k = (K) NULL_KEY;
            }
            if (this.groups.remove(k) == null || this.groupCount.decrementAndGet() != 0) {
                return;
            }
            unsubscribe();
        }

        void drain() {
            if (this.wip.getAndIncrement() != 0) {
                return;
            }
            Queue<GroupedUnicast<K, V>> queue = this.queue;
            Subscriber<? super GroupedObservable<K, V>> subscriber = this.actual;
            int iAddAndGet = 1;
            while (!checkTerminated(this.done, queue.isEmpty(), subscriber, queue)) {
                long j = this.requested.get();
                boolean z = j == LocationRequestCompat.PASSIVE_INTERVAL;
                long j2 = 0;
                while (j != 0) {
                    boolean z2 = this.done;
                    GroupedUnicast<K, V> groupedUnicastPoll = queue.poll();
                    boolean z3 = groupedUnicastPoll == null;
                    if (checkTerminated(z2, z3, subscriber, queue)) {
                        return;
                    }
                    if (z3) {
                        break;
                    }
                    subscriber.onNext(groupedUnicastPoll);
                    j--;
                    j2--;
                }
                if (j2 != 0) {
                    if (!z) {
                        this.requested.addAndGet(j2);
                    }
                    this.s.request(-j2);
                }
                iAddAndGet = this.wip.addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
        }

        void errorAll(Subscriber<? super GroupedObservable<K, V>> subscriber, Queue<?> queue, Throwable th) {
            queue.clear();
            ArrayList arrayList = new ArrayList(this.groups.values());
            this.groups.clear();
            Queue<GroupedUnicast<K, V>> queue2 = this.evictedGroups;
            if (queue2 != null) {
                queue2.clear();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((GroupedUnicast) it.next()).onError(th);
            }
            subscriber.onError(th);
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super GroupedObservable<K, V>> subscriber, Queue<?> queue) {
            if (!z) {
                return false;
            }
            Throwable th = this.error;
            if (th != null) {
                errorAll(subscriber, queue, th);
                return true;
            }
            if (!z2) {
                return false;
            }
            this.actual.onCompleted();
            return true;
        }
    }

    static class EvictionAction<K, V> implements Action1<GroupedUnicast<K, V>> {
        final Queue<GroupedUnicast<K, V>> evictedGroups;

        EvictionAction(Queue<GroupedUnicast<K, V>> queue) {
            this.evictedGroups = queue;
        }

        @Override // rx.functions.Action1
        public void call(GroupedUnicast<K, V> groupedUnicast) {
            this.evictedGroups.offer(groupedUnicast);
        }
    }

    static final class GroupedUnicast<K, T> extends GroupedObservable<K, T> {
        final State<T, K> state;

        public static <T, K> GroupedUnicast<K, T> createWith(K k, int i, GroupBySubscriber<?, K, T> groupBySubscriber, boolean z) {
            return new GroupedUnicast<>(k, new State(i, groupBySubscriber, k, z));
        }

        protected GroupedUnicast(K k, State<T, K> state) {
            super(k, state);
            this.state = state;
        }

        public void onNext(T t) {
            this.state.onNext(t);
        }

        public void onError(Throwable th) {
            this.state.onError(th);
        }

        public void onComplete() {
            this.state.onComplete();
        }
    }

    static final class State<T, K> extends AtomicInteger implements Producer, Subscription, Observable.OnSubscribe<T> {
        private static final long serialVersionUID = -3852313036005250360L;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final K key;
        final GroupBySubscriber<?, K, T> parent;
        final Queue<Object> queue = new ConcurrentLinkedQueue();
        final AtomicBoolean cancelled = new AtomicBoolean();
        final AtomicReference<Subscriber<? super T>> actual = new AtomicReference<>();
        final AtomicBoolean once = new AtomicBoolean();
        final AtomicLong requested = new AtomicLong();

        public State(int i, GroupBySubscriber<?, K, T> groupBySubscriber, K k, boolean z) {
            this.parent = groupBySubscriber;
            this.key = k;
            this.delayError = z;
        }

        @Override // rx.Producer
        public void request(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= required but it was " + j);
            }
            if (j != 0) {
                BackpressureUtils.getAndAddRequest(this.requested, j);
                drain();
            }
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.cancelled.get();
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (this.cancelled.compareAndSet(false, true) && getAndIncrement() == 0) {
                this.parent.cancel(this.key);
            }
        }

        @Override // rx.functions.Action1
        public void call(Subscriber<? super T> subscriber) {
            if (this.once.compareAndSet(false, true)) {
                subscriber.add(this);
                subscriber.setProducer(this);
                this.actual.lazySet(subscriber);
                drain();
                return;
            }
            subscriber.onError(new IllegalStateException("Only one Subscriber allowed!"));
        }

        public void onNext(T t) {
            if (t == null) {
                this.error = new NullPointerException();
                this.done = true;
            } else {
                this.queue.offer(NotificationLite.next(t));
            }
            drain();
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            Queue<Object> queue = this.queue;
            boolean z = this.delayError;
            Subscriber<? super T> subscriber = this.actual.get();
            int iAddAndGet = 1;
            while (true) {
                if (subscriber != null) {
                    if (checkTerminated(this.done, queue.isEmpty(), subscriber, z)) {
                        return;
                    }
                    long j = this.requested.get();
                    boolean z2 = j == LocationRequestCompat.PASSIVE_INTERVAL;
                    long j2 = 0;
                    while (j != 0) {
                        boolean z3 = this.done;
                        Object objPoll = queue.poll();
                        boolean z4 = objPoll == null;
                        if (checkTerminated(z3, z4, subscriber, z)) {
                            return;
                        }
                        if (z4) {
                            break;
                        }
                        subscriber.onNext((Object) NotificationLite.getValue(objPoll));
                        j--;
                        j2--;
                    }
                    if (j2 != 0) {
                        if (!z2) {
                            this.requested.addAndGet(j2);
                        }
                        this.parent.s.request(-j2);
                    }
                }
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
                if (subscriber == null) {
                    subscriber = this.actual.get();
                }
            }
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super T> subscriber, boolean z3) {
            if (this.cancelled.get()) {
                this.queue.clear();
                this.parent.cancel(this.key);
                return true;
            }
            if (!z) {
                return false;
            }
            if (z3) {
                if (!z2) {
                    return false;
                }
                Throwable th = this.error;
                if (th != null) {
                    subscriber.onError(th);
                } else {
                    subscriber.onCompleted();
                }
                return true;
            }
            Throwable th2 = this.error;
            if (th2 != null) {
                this.queue.clear();
                subscriber.onError(th2);
                return true;
            }
            if (!z2) {
                return false;
            }
            subscriber.onCompleted();
            return true;
        }
    }
}
