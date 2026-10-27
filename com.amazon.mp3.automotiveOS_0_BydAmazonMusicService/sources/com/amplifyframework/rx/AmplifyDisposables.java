package com.amplifyframework.rx;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.core.async.Cancelable;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes5.dex */
final class AmplifyDisposables {
    private AmplifyDisposables() {
    }

    @NonNull
    static Disposable fromCancelable(@Nullable final Cancelable cancelable) {
        if (cancelable == null) {
            return Disposable.empty();
        }
        return new Disposable() { // from class: com.amplifyframework.rx.AmplifyDisposables.1
            private final AtomicReference<Boolean> isCanceled = new AtomicReference<>(Boolean.FALSE);

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public void dispose() {
                synchronized (this.isCanceled) {
                    if (!this.isCanceled.get().booleanValue()) {
                        cancelable.cancel();
                        this.isCanceled.set(Boolean.TRUE);
                    }
                }
            }

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public boolean isDisposed() {
                boolean zBooleanValue;
                synchronized (this.isCanceled) {
                    zBooleanValue = this.isCanceled.get().booleanValue();
                }
                return zBooleanValue;
            }
        };
    }
}
