package io.reactivex.rxjava3.observers;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
final class TestObserver$EmptyObserver implements Observer<Object> {
    public static final TestObserver$EmptyObserver c;
    public static final /* synthetic */ TestObserver$EmptyObserver[] e;

    static {
        TestObserver$EmptyObserver testObserver$EmptyObserver = new TestObserver$EmptyObserver("INSTANCE", 0);
        c = testObserver$EmptyObserver;
        e = new TestObserver$EmptyObserver[]{testObserver$EmptyObserver};
    }

    public static TestObserver$EmptyObserver valueOf(String str) {
        return (TestObserver$EmptyObserver) Enum.valueOf(TestObserver$EmptyObserver.class, str);
    }

    public static TestObserver$EmptyObserver[] values() {
        return (TestObserver$EmptyObserver[]) e.clone();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
    }
}
