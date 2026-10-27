package com.facebook.react.uimanager;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.JSIModule;
import com.facebook.react.bridge.JSIModuleType;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;

/* JADX INFO: loaded from: classes6.dex */
public class ThemedReactContext extends ReactContext {

    @Nullable
    private final String mModuleName;
    private final ReactApplicationContext mReactApplicationContext;
    private final int mSurfaceId;

    public ThemedReactContext(ReactApplicationContext reactApplicationContext, Context context, @Nullable String str, int i) {
        super(context);
        if (reactApplicationContext.hasCatalystInstance()) {
            initializeWithInstance(reactApplicationContext.getCatalystInstance());
        }
        this.mReactApplicationContext = reactApplicationContext;
        this.mModuleName = str;
        this.mSurfaceId = i;
    }

    @Override // com.facebook.react.bridge.ReactContext
    public void addLifecycleEventListener(LifecycleEventListener lifecycleEventListener) {
        this.mReactApplicationContext.addLifecycleEventListener(lifecycleEventListener);
    }

    @Override // com.facebook.react.bridge.ReactContext
    public void removeLifecycleEventListener(LifecycleEventListener lifecycleEventListener) {
        this.mReactApplicationContext.removeLifecycleEventListener(lifecycleEventListener);
    }

    @Override // com.facebook.react.bridge.ReactContext
    public boolean hasCurrentActivity() {
        return this.mReactApplicationContext.hasCurrentActivity();
    }

    @Override // com.facebook.react.bridge.ReactContext
    @Nullable
    public Activity getCurrentActivity() {
        return this.mReactApplicationContext.getCurrentActivity();
    }

    @Nullable
    public String getModuleName() {
        return this.mModuleName;
    }

    public int getSurfaceId() {
        return this.mSurfaceId;
    }

    public ReactApplicationContext getReactApplicationContext() {
        return this.mReactApplicationContext;
    }

    @Override // com.facebook.react.bridge.ReactContext
    public boolean isBridgeless() {
        return this.mReactApplicationContext.isBridgeless();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public JSIModule getJSIModule(JSIModuleType jSIModuleType) {
        if (isBridgeless()) {
            return this.mReactApplicationContext.getJSIModule(jSIModuleType);
        }
        return super.getJSIModule(jSIModuleType);
    }
}
