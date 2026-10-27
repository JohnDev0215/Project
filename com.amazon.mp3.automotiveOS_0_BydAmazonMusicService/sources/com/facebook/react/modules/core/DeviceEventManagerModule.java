package com.facebook.react.modules.core;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.fbreact.specs.NativeDeviceEventManagerSpec;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.google.android.gms.common.internal.ImagesContract;

/* JADX INFO: loaded from: classes6.dex */
@ReactModule(name = DeviceEventManagerModule.NAME)
public class DeviceEventManagerModule extends NativeDeviceEventManagerSpec {
    public static final String NAME = "DeviceEventManager";
    private final Runnable mInvokeDefaultBackPressRunnable;

    @DoNotStrip
    public interface RCTDeviceEventEmitter extends JavaScriptModule {
        void emit(@NonNull String str, @Nullable Object obj);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public DeviceEventManagerModule(ReactApplicationContext reactApplicationContext, final DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler) {
        super(reactApplicationContext);
        this.mInvokeDefaultBackPressRunnable = new Runnable() { // from class: com.facebook.react.modules.core.DeviceEventManagerModule.1
            @Override // java.lang.Runnable
            public void run() {
                UiThreadUtil.assertOnUiThread();
                defaultHardwareBackBtnHandler.invokeDefaultOnBackPressed();
            }
        };
    }

    public void emitHardwareBackPressed() {
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            ((RCTDeviceEventEmitter) reactApplicationContextIfActiveOrWarn.getJSModule(RCTDeviceEventEmitter.class)).emit("hardwareBackPress", null);
        }
    }

    public void emitNewIntentReceived(Uri uri) {
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putString(ImagesContract.URL, uri.toString());
            ((RCTDeviceEventEmitter) reactApplicationContextIfActiveOrWarn.getJSModule(RCTDeviceEventEmitter.class)).emit(ImagesContract.URL, writableMapCreateMap);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeDeviceEventManagerSpec
    public void invokeDefaultBackPressHandler() {
        getReactApplicationContext().runOnUiQueueThread(this.mInvokeDefaultBackPressRunnable);
    }
}
