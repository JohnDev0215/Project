package io.objectbox;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public interface TxCallback<T> {
    void txFinished(@Nullable T t, @Nullable Throwable th);
}
