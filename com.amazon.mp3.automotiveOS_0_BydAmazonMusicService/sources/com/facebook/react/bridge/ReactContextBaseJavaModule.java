package com.facebook.react.bridge;

import android.app.Activity;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Assertions;

/* JADX INFO: loaded from: classes6.dex */
public abstract class ReactContextBaseJavaModule extends BaseJavaModule {

    @Nullable
    private final ReactApplicationContext mReactApplicationContext;

    public ReactContextBaseJavaModule() {
        this.mReactApplicationContext = null;
    }

    public ReactContextBaseJavaModule(@Nullable ReactApplicationContext reactApplicationContext) {
        this.mReactApplicationContext = reactApplicationContext;
    }

    protected final ReactApplicationContext getReactApplicationContext() {
        return (ReactApplicationContext) Assertions.assertNotNull(this.mReactApplicationContext, "Tried to get ReactApplicationContext even though NativeModule wasn't instantiated with one");
    }

    @Nullable
    protected final ReactApplicationContext getReactApplicationContextIfActiveOrWarn() {
        if (this.mReactApplicationContext.hasActiveReactInstance()) {
            return this.mReactApplicationContext;
        }
        ReactSoftExceptionLogger.logSoftException("ReactContextBaseJavaModule", new RuntimeException("Catalyst Instance has already disappeared: requested by " + getName()));
        return null;
    }

    @Nullable
    protected final Activity getCurrentActivity() {
        return this.mReactApplicationContext.getCurrentActivity();
    }
}
