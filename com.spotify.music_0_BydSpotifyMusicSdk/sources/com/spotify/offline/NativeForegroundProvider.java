package com.spotify.offline;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import p.dm1;
import p.du;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class NativeForegroundProvider {
    public static final dm1 Companion = new dm1();
    private final Observable<Boolean> foreground;
    private Disposable subscription;

    public NativeForegroundProvider(Observable<Boolean> observable) {
        v03.j(observable, "foreground");
        this.foreground = observable;
        this.subscription = EmptyDisposable.c;
    }

    public static final NativeForegroundProvider noop() {
        Companion.getClass();
        Observable observableEmpty = Observable.empty();
        v03.i(observableEmpty, "empty<Boolean>()");
        return new NativeForegroundProvider(observableEmpty);
    }

    public final Observable<Boolean> getForeground() {
        return this.foreground;
    }

    public final void observe(NativeForegroundObserver nativeForegroundObserver) {
        v03.j(nativeForegroundObserver, "observer");
        Disposable disposableSubscribe = this.foreground.subscribe(new du(6, nativeForegroundObserver));
        v03.i(disposableSubscribe, "observer: NativeForegrou…server.onForeground(it) }");
        this.subscription = disposableSubscribe;
    }

    public final void stop() {
        this.subscription.dispose();
    }
}
