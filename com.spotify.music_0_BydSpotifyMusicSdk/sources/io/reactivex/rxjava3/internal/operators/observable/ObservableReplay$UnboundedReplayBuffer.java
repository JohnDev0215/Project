package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
final class ObservableReplay$UnboundedReplayBuffer<T> extends ArrayList<Object> implements q3 {
    private static final long serialVersionUID = 7063189396499112664L;
    volatile int size;

    @Override // io.reactivex.rxjava3.internal.operators.observable.q3
    public final void a() {
        add(NotificationLite.c);
        this.size++;
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.q3
    public final void d(Object obj) {
        add(obj);
        this.size++;
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.q3
    public final void e(Throwable th) {
        add(NotificationLite.f(th));
        this.size++;
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.q3
    public final void h(ObservableReplay$InnerDisposable observableReplay$InnerDisposable) {
        if (observableReplay$InnerDisposable.getAndIncrement() != 0) {
            return;
        }
        Observer<? super T> observer = observableReplay$InnerDisposable.child;
        int iAddAndGet = 1;
        while (!observableReplay$InnerDisposable.isDisposed()) {
            int i = this.size;
            Integer num = (Integer) observableReplay$InnerDisposable.index;
            int iIntValue = num != null ? num.intValue() : 0;
            while (iIntValue < i) {
                if (NotificationLite.a(observer, get(iIntValue)) || observableReplay$InnerDisposable.isDisposed()) {
                    return;
                } else {
                    iIntValue++;
                }
            }
            observableReplay$InnerDisposable.index = Integer.valueOf(iIntValue);
            iAddAndGet = observableReplay$InnerDisposable.addAndGet(-iAddAndGet);
            if (iAddAndGet == 0) {
                return;
            }
        }
    }
}
