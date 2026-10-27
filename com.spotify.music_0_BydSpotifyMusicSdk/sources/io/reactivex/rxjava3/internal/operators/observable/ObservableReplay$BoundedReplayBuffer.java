package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.internal.util.NotificationLite;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
abstract class ObservableReplay$BoundedReplayBuffer<T> extends AtomicReference<ObservableReplay$Node> implements q3 {
    private static final long serialVersionUID = 2346567790059478686L;
    final boolean eagerTruncate;
    int size;
    ObservableReplay$Node tail;

    public ObservableReplay$BoundedReplayBuffer(boolean z) {
        this.eagerTruncate = z;
        ObservableReplay$Node observableReplay$Node = new ObservableReplay$Node(null);
        this.tail = observableReplay$Node;
        set(observableReplay$Node);
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.q3
    public final void a() {
        ObservableReplay$Node observableReplay$Node = new ObservableReplay$Node(b(NotificationLite.c));
        this.tail.set(observableReplay$Node);
        this.tail = observableReplay$Node;
        this.size++;
        j();
    }

    public Object b(Object obj) {
        return obj;
    }

    public ObservableReplay$Node c() {
        return get();
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.q3
    public final void d(Object obj) {
        ObservableReplay$Node observableReplay$Node = new ObservableReplay$Node(b(obj));
        this.tail.set(observableReplay$Node);
        this.tail = observableReplay$Node;
        this.size++;
        i();
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.q3
    public final void e(Throwable th) {
        ObservableReplay$Node observableReplay$Node = new ObservableReplay$Node(b(NotificationLite.f(th)));
        this.tail.set(observableReplay$Node);
        this.tail = observableReplay$Node;
        this.size++;
        j();
    }

    public Object f(Object obj) {
        return obj;
    }

    public final void g(ObservableReplay$Node observableReplay$Node) {
        if (this.eagerTruncate) {
            ObservableReplay$Node observableReplay$Node2 = new ObservableReplay$Node(null);
            observableReplay$Node2.lazySet(observableReplay$Node.get());
            observableReplay$Node = observableReplay$Node2;
        }
        set(observableReplay$Node);
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.q3
    public final void h(ObservableReplay$InnerDisposable observableReplay$InnerDisposable) {
        if (observableReplay$InnerDisposable.getAndIncrement() != 0) {
            return;
        }
        int iAddAndGet = 1;
        do {
            ObservableReplay$Node observableReplay$NodeC = (ObservableReplay$Node) observableReplay$InnerDisposable.index;
            if (observableReplay$NodeC == null) {
                observableReplay$NodeC = c();
                observableReplay$InnerDisposable.index = observableReplay$NodeC;
            }
            while (!observableReplay$InnerDisposable.isDisposed()) {
                ObservableReplay$Node observableReplay$Node = observableReplay$NodeC.get();
                if (observableReplay$Node != null) {
                    if (NotificationLite.a(observableReplay$InnerDisposable.child, f(observableReplay$Node.value))) {
                        observableReplay$InnerDisposable.index = null;
                        return;
                    }
                    observableReplay$NodeC = observableReplay$Node;
                } else {
                    observableReplay$InnerDisposable.index = observableReplay$NodeC;
                    iAddAndGet = observableReplay$InnerDisposable.addAndGet(-iAddAndGet);
                }
            }
            observableReplay$InnerDisposable.index = null;
            return;
        } while (iAddAndGet != 0);
    }

    public abstract void i();

    public void j() {
        ObservableReplay$Node observableReplay$Node = get();
        if (observableReplay$Node.value != null) {
            ObservableReplay$Node observableReplay$Node2 = new ObservableReplay$Node(null);
            observableReplay$Node2.lazySet(observableReplay$Node.get());
            set(observableReplay$Node2);
        }
    }
}
