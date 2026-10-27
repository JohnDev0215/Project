package com.shopify.reactnativeperformance;

import android.os.SystemClock;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactNativePerformanceModule.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\r"}, d2 = {"Lcom/shopify/reactnativeperformance/ReactNativePerformanceModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "(Lcom/facebook/react/bridge/ReactContext;)V", "getName", "", "getNativeStartupTimestamp", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "getNativeTime", "getNativeUuid", "shopify_react-native-performance_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ReactNativePerformanceModule extends ReactContextBaseJavaModule {
    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "Performance";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactNativePerformanceModule(ReactContext reactContext) {
        super((ReactApplicationContext) reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
    }

    @ReactMethod
    public final void getNativeStartupTimestamp(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        Long nativeStartupTimestamp$shopify_react_native_performance_release = ReactNativePerformance.INSTANCE.getNativeStartupTimestamp$shopify_react_native_performance_release();
        if (nativeStartupTimestamp$shopify_react_native_performance_release == null) {
            unit = null;
        } else {
            promise.resolve(String.valueOf(nativeStartupTimestamp$shopify_react_native_performance_release.longValue()));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            promise.reject(new IllegalStateException("ReactNativePerformance.onAppStarted was never called by the native portion of the app."));
        }
    }

    @ReactMethod
    public final void getNativeTime(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jUptimeMillis = SystemClock.uptimeMillis();
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("timeSinceEpochMillis", String.valueOf(jCurrentTimeMillis));
        writableNativeMap.putString("uptimeMillis", String.valueOf(jUptimeMillis));
        promise.resolve(writableNativeMap);
    }

    @ReactMethod
    public final void getNativeUuid(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        promise.resolve(UUID.randomUUID().toString());
    }
}
