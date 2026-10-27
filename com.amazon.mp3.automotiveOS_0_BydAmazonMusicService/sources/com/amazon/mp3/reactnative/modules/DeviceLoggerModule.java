package com.amazon.mp3.reactnative.modules;

import com.amazon.mp3.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DeviceLoggerModule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\n\u001a\u00020\bH\u0016J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/reactnative/modules/DeviceLoggerModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "context", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "action", "", "log", "", "critical", "getName", "normal", "verbose", "warn", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeviceLoggerModule extends ReactContextBaseJavaModule {
    private static final String TAG = DeviceLoggerModule.class.getSimpleName();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceLoggerModule(ReactApplicationContext context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        return TAG2;
    }

    @ReactMethod
    public final void critical(String log) {
        Intrinsics.checkNotNullParameter(log, "log");
        Log.error(TAG, log);
    }

    @ReactMethod
    public final void action(String log) {
        Intrinsics.checkNotNullParameter(log, "log");
        Log.info(TAG, log);
    }

    @ReactMethod
    public final void warn(String log) {
        Intrinsics.checkNotNullParameter(log, "log");
        Log.warning(TAG, log);
    }

    @ReactMethod
    public final void verbose(String log) {
        Intrinsics.checkNotNullParameter(log, "log");
        Log.verbose(TAG, log);
    }

    @ReactMethod
    public final void normal(String log) {
        Intrinsics.checkNotNullParameter(log, "log");
        Log.info(TAG, log);
    }
}
