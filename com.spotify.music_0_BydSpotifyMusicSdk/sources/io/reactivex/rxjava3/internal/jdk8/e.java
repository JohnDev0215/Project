package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class e extends j {
    public final /* synthetic */ int k;
    public final boolean l;
    public final Object m;

    public /* synthetic */ e(boolean z, Object obj, int i) {
        this.k = i;
        this.l = z;
        this.m = obj;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        DisposableHelper disposableHelper = DisposableHelper.c;
        AtomicReference atomicReference = this.c;
        int i = this.k;
        Object obj = this.m;
        boolean z = this.l;
        switch (i) {
            case 0:
                if (!isDone()) {
                    this.e = null;
                    atomicReference.lazySet(disposableHelper);
                    if (!z) {
                        completeExceptionally(new NoSuchElementException());
                    } else {
                        complete(obj);
                    }
                }
                break;
            case 1:
                if (!isDone()) {
                    Object obj2 = this.e;
                    this.e = null;
                    atomicReference.lazySet(disposableHelper);
                    if (obj2 != null) {
                        complete(obj2);
                    } else if (!z) {
                        completeExceptionally(new NoSuchElementException());
                    } else {
                        complete(obj);
                    }
                }
                break;
            default:
                if (!isDone()) {
                    Object obj3 = this.e;
                    this.e = null;
                    atomicReference.lazySet(disposableHelper);
                    if (obj3 != null) {
                        complete(obj3);
                    } else if (!z) {
                        completeExceptionally(new NoSuchElementException());
                    } else {
                        complete(obj);
                    }
                }
                break;
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        switch (this.k) {
            case 0:
                complete(obj);
                break;
            case 1:
                this.e = obj;
                break;
            default:
                if (this.e == null) {
                    this.e = obj;
                } else {
                    this.e = null;
                    completeExceptionally(new IllegalArgumentException("Sequence contains more than one element!"));
                }
                break;
        }
    }
}
