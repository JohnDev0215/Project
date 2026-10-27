package io.reactivex.rxjava3.subjects;

import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes7.dex */
public final class ReplaySubject<T> extends Subject<T> {
    final ReplayBuffer<T> buffer;
    boolean done;
    final AtomicReference<ReplayDisposable<T>[]> observers = new AtomicReference<>(EMPTY);
    static final ReplayDisposable[] EMPTY = new ReplayDisposable[0];
    static final ReplayDisposable[] TERMINATED = new ReplayDisposable[0];
    private static final Object[] EMPTY_ARRAY = new Object[0];

    interface ReplayBuffer<T> {
        void add(T value);

        void addFinal(Object notificationLite);

        boolean compareAndSet(Object expected, Object next);

        void replay(ReplayDisposable<T> rs);
    }

    public static <T> ReplaySubject<T> create() {
        return new ReplaySubject<>(new UnboundedReplayBuffer(16));
    }

    ReplaySubject(ReplayBuffer<T> buffer) {
        this.buffer = buffer;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    protected void subscribeActual(Observer<? super T> observer) {
        ReplayDisposable<T> replayDisposable = new ReplayDisposable<>(observer, this);
        observer.onSubscribe(replayDisposable);
        if (add(replayDisposable) && replayDisposable.cancelled) {
            remove(replayDisposable);
        } else {
            this.buffer.replay(replayDisposable);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onSubscribe(Disposable d) {
        if (this.done) {
            d.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onNext(T t) {
        ExceptionHelper.nullCheck(t, "onNext called with a null value.");
        if (this.done) {
            return;
        }
        ReplayBuffer<T> replayBuffer = this.buffer;
        replayBuffer.add(t);
        for (ReplayDisposable<T> replayDisposable : this.observers.get()) {
            replayBuffer.replay(replayDisposable);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onError(Throwable t) {
        ExceptionHelper.nullCheck(t, "onError called with a null Throwable.");
        if (this.done) {
            RxJavaPlugins.onError(t);
            return;
        }
        this.done = true;
        Object objError = NotificationLite.error(t);
        ReplayBuffer<T> replayBuffer = this.buffer;
        replayBuffer.addFinal(objError);
        for (ReplayDisposable<T> replayDisposable : terminate(objError)) {
            replayBuffer.replay(replayDisposable);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        Object objComplete = NotificationLite.complete();
        ReplayBuffer<T> replayBuffer = this.buffer;
        replayBuffer.addFinal(objComplete);
        for (ReplayDisposable<T> replayDisposable : terminate(objComplete)) {
            replayBuffer.replay(replayDisposable);
        }
    }

    boolean add(ReplayDisposable<T> rs) {
        ReplayDisposable<T>[] replayDisposableArr;
        ReplayDisposable[] replayDisposableArr2;
        do {
            replayDisposableArr = this.observers.get();
            if (replayDisposableArr == TERMINATED) {
                return false;
            }
            int length = replayDisposableArr.length;
            replayDisposableArr2 = new ReplayDisposable[length + 1];
            System.arraycopy(replayDisposableArr, 0, replayDisposableArr2, 0, length);
            replayDisposableArr2[length] = rs;
        } while (!CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.observers, replayDisposableArr, replayDisposableArr2));
        return true;
    }

    void remove(ReplayDisposable<T> rs) {
        ReplayDisposable<T>[] replayDisposableArr;
        ReplayDisposable[] replayDisposableArr2;
        do {
            replayDisposableArr = this.observers.get();
            if (replayDisposableArr == TERMINATED || replayDisposableArr == EMPTY) {
                return;
            }
            int length = replayDisposableArr.length;
            int i = -1;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                if (replayDisposableArr[i2] == rs) {
                    i = i2;
                    break;
                }
                i2++;
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                replayDisposableArr2 = EMPTY;
            } else {
                ReplayDisposable[] replayDisposableArr3 = new ReplayDisposable[length - 1];
                System.arraycopy(replayDisposableArr, 0, replayDisposableArr3, 0, i);
                System.arraycopy(replayDisposableArr, i + 1, replayDisposableArr3, i, (length - i) - 1);
                replayDisposableArr2 = replayDisposableArr3;
            }
        } while (!CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.observers, replayDisposableArr, replayDisposableArr2));
    }

    ReplayDisposable<T>[] terminate(Object terminalValue) {
        this.buffer.compareAndSet(null, terminalValue);
        return this.observers.getAndSet(TERMINATED);
    }

    static final class ReplayDisposable<T> extends AtomicInteger implements Disposable {
        volatile boolean cancelled;
        final Observer<? super T> downstream;
        Object index;
        final ReplaySubject<T> state;

        ReplayDisposable(Observer<? super T> actual, ReplaySubject<T> state) {
            this.downstream = actual;
            this.state = state;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            this.state.remove(this);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }
    }

    static final class UnboundedReplayBuffer<T> extends AtomicReference<Object> implements ReplayBuffer<T> {
        final List<Object> buffer;
        volatile boolean done;
        volatile int size;

        public void trimHead() {
        }

        UnboundedReplayBuffer(int capacityHint) {
            this.buffer = new ArrayList(capacityHint);
        }

        @Override // io.reactivex.rxjava3.subjects.ReplaySubject.ReplayBuffer
        public void add(T value) {
            this.buffer.add(value);
            this.size++;
        }

        @Override // io.reactivex.rxjava3.subjects.ReplaySubject.ReplayBuffer
        public void addFinal(Object notificationLite) {
            this.buffer.add(notificationLite);
            trimHead();
            this.size++;
            this.done = true;
        }

        @Override // io.reactivex.rxjava3.subjects.ReplaySubject.ReplayBuffer
        public void replay(ReplayDisposable<T> rs) {
            int i;
            if (rs.getAndIncrement() != 0) {
                return;
            }
            List<Object> list = this.buffer;
            Observer<? super T> observer = rs.downstream;
            Integer num = (Integer) rs.index;
            int iIntValue = 0;
            if (num != null) {
                iIntValue = num.intValue();
            } else {
                rs.index = 0;
            }
            int iAddAndGet = 1;
            while (!rs.cancelled) {
                int i2 = this.size;
                while (i2 != iIntValue) {
                    if (rs.cancelled) {
                        rs.index = null;
                        return;
                    }
                    Object obj = list.get(iIntValue);
                    if (this.done && (i = iIntValue + 1) == i2 && i == (i2 = this.size)) {
                        if (NotificationLite.isComplete(obj)) {
                            observer.onComplete();
                        } else {
                            observer.onError(NotificationLite.getError(obj));
                        }
                        rs.index = null;
                        rs.cancelled = true;
                        return;
                    }
                    observer.onNext(obj);
                    iIntValue++;
                }
                if (iIntValue == this.size) {
                    rs.index = Integer.valueOf(iIntValue);
                    iAddAndGet = rs.addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                }
            }
            rs.index = null;
        }
    }
}
