package com.android.inputmethod.latin.utils;

import android.os.Handler;
import android.os.Looper;
import java.lang.ref.WeakReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class LeakGuardHandlerWrapper<T> extends Handler {
    private final WeakReference<T> mOwnerInstanceRef;

    public LeakGuardHandlerWrapper(@Nonnull final T ownerInstance) {
        this(ownerInstance, Looper.myLooper());
    }

    public LeakGuardHandlerWrapper(@Nonnull final T ownerInstance, final Looper looper) {
        super(looper);
        this.mOwnerInstanceRef = new WeakReference<>(ownerInstance);
    }

    @Nullable
    public T getOwnerInstance() {
        return this.mOwnerInstanceRef.get();
    }
}
