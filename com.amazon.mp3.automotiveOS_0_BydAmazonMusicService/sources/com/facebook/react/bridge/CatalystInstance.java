package com.facebook.react.bridge;

import androidx.annotation.Nullable;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
public interface CatalystInstance extends MemoryPressureListener, JSInstance, JSBundleLoaderDelegate {
    void addBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener);

    void addJSIModules(List<JSIModuleSpec> list);

    @DoNotStrip
    void callFunction(String str, String str2, NativeArray nativeArray);

    void destroy();

    void extendNativeModules(NativeModuleRegistry nativeModuleRegistry);

    CallInvokerHolder getJSCallInvokerHolder();

    JSIModule getJSIModule(JSIModuleType jSIModuleType);

    <T extends JavaScriptModule> T getJSModule(Class<T> cls);

    @Deprecated
    JavaScriptContextHolder getJavaScriptContextHolder();

    CallInvokerHolder getNativeCallInvokerHolder();

    @Nullable
    <T extends NativeModule> T getNativeModule(Class<T> cls);

    @Nullable
    NativeModule getNativeModule(String str);

    Collection<NativeModule> getNativeModules();

    ReactQueueConfiguration getReactQueueConfiguration();

    RuntimeExecutor getRuntimeExecutor();

    RuntimeScheduler getRuntimeScheduler();

    @Nullable
    String getSourceURL();

    <T extends NativeModule> boolean hasNativeModule(Class<T> cls);

    boolean hasRunJSBundle();

    void initialize();

    @Override // com.facebook.react.bridge.JSInstance
    @DoNotStrip
    void invokeCallback(int i, NativeArrayInterface nativeArrayInterface);

    boolean isDestroyed();

    void registerSegment(int i, String str);

    void removeBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener);

    void runJSBundle();

    void setGlobalVariable(String str, String str2);

    void setTurboModuleManager(JSIModule jSIModule);
}
