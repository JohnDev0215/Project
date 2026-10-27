package com.spotify.connectivity.productstateesperanto;

import com.spotify.connectivity.productstate.RxProductStateUpdater;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;

/* JADX INFO: loaded from: classes.dex */
public class RxProductStateUpdaterImpl implements RxProductStateUpdater {
    private final io.reactivex.rxjava3.disposables.a disposables = new io.reactivex.rxjava3.disposables.a();
    private final ProductStateMethods mproductStateMethods;

    public RxProductStateUpdaterImpl(ProductStateMethods productStateMethods) {
        this.mproductStateMethods = productStateMethods;
    }

    @Override // com.spotify.connectivity.productstate.RxProductStateUpdater
    public void dispose() {
        this.disposables.d();
    }

    @Override // com.spotify.connectivity.productstate.RxProductStateUpdater
    public void update(String str, String str2) {
        this.mproductStateMethods.updateState(str, str2).subscribe(new CompletableObserver() { // from class: com.spotify.connectivity.productstateesperanto.RxProductStateUpdaterImpl.1
            private Disposable disposable = null;

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onComplete() {
                this.disposable.dispose();
                RxProductStateUpdaterImpl.this.disposables.a(this.disposable);
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable th) {
                onComplete();
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onSubscribe(Disposable disposable) {
                this.disposable = disposable;
                RxProductStateUpdaterImpl.this.disposables.c(this.disposable);
            }
        });
    }
}
