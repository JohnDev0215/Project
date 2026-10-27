package io.reactivex.rxjava3.internal.util;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.tg2;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class EmptyComponent implements FlowableSubscriber<Object>, Observer<Object>, MaybeObserver<Object>, SingleObserver<Object>, CompletableObserver, tg2, Disposable {
    public static final EmptyComponent c;
    public static final /* synthetic */ EmptyComponent[] e;

    static {
        EmptyComponent emptyComponent = new EmptyComponent("INSTANCE", 0);
        c = emptyComponent;
        e = new EmptyComponent[]{emptyComponent};
    }

    public static EmptyComponent valueOf(String str) {
        return (EmptyComponent) Enum.valueOf(EmptyComponent.class, str);
    }

    public static EmptyComponent[] values() {
        return (EmptyComponent[]) e.clone();
    }

    @Override // p.tg2
    public final void a(long j) {
    }

    @Override // p.tg2
    public final void cancel() {
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return true;
    }

    @Override // p.rg2
    public final void onComplete() {
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        RxJavaPlugins.b(th);
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        disposable.dispose();
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        tg2Var.cancel();
    }
}
