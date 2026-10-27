package io.reactivex.internal.disposables;

import io.reactivex.disposables.Disposable;

/* JADX INFO: loaded from: classes5.dex */
public interface ResettableConnectable {
    void resetIf(Disposable disposable);
}
