package com.facebook.react.fabric;

import androidx.annotation.NonNull;
import com.facebook.react.bridge.JSExceptionHandler;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.ChoreographerCompat;

/* JADX INFO: loaded from: classes6.dex */
public abstract class GuardedFrameCallback extends ChoreographerCompat.FrameCallback {

    @NonNull
    private final JSExceptionHandler mExceptionHandler;

    protected abstract void doFrameGuarded(long j);

    @Deprecated
    protected GuardedFrameCallback(@NonNull ReactContext reactContext) {
        this(reactContext.getExceptionHandler());
    }

    protected GuardedFrameCallback(@NonNull JSExceptionHandler jSExceptionHandler) {
        this.mExceptionHandler = jSExceptionHandler;
    }

    @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
    public final void doFrame(long j) {
        try {
            doFrameGuarded(j);
        } catch (RuntimeException e) {
            this.mExceptionHandler.handleException(e);
        }
    }
}
