package com.waze.sdk;

import android.content.Context;
import android.util.Log;
import com.waze.sdk.WazeSdk;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes7.dex */
public class WazeAudioSdk extends WazeSdk {
    private static WeakReference<WazeAudioSdk> sInstance;

    public interface NavigationListener extends WazeSdk.NavigationListener {
    }

    @Override // com.waze.sdk.WazeSdk
    public /* bridge */ /* synthetic */ void disconnect() {
        super.disconnect();
    }

    @Override // com.waze.sdk.WazeSdk
    public /* bridge */ /* synthetic */ boolean isConnected() {
        return super.isConnected();
    }

    @Override // com.waze.sdk.WazeSdk
    public /* bridge */ /* synthetic */ boolean openWaze() {
        return super.openWaze();
    }

    public static WazeAudioSdk init(Context context, WazeAudioSdkSettings wazeAudioSdkSettings, WazeSdkCallback wazeSdkCallback) throws IllegalStateException {
        int wazeVersionCode = WazeSdkUtils.getWazeVersionCode(context);
        if (wazeVersionCode == 0) {
            throw new IllegalStateException("Waze not installed.");
        }
        if (wazeVersionCode < 1021549) {
            throw new IllegalStateException(String.format("Waze version %s does not support Audio SDK version %s.", WazeSdkUtils.getWazeBuildnumber(context), "1.0.0.8"));
        }
        WeakReference<WazeAudioSdk> weakReference = sInstance;
        if (weakReference != null && weakReference.get() != null && sInstance.get().isConnected()) {
            sInstance.get().disconnect();
        }
        Log.d("WazeSdk", "Waze Audio SDK 1.0.0.8 started...");
        WeakReference<WazeAudioSdk> weakReference2 = new WeakReference<>(new WazeAudioSdk(context, wazeAudioSdkSettings, wazeSdkCallback));
        sInstance = weakReference2;
        return weakReference2.get();
    }

    public static boolean isWazeVersionSupported(Context context) {
        return WazeSdkUtils.getWazeVersionCode(context) >= 1021549;
    }

    public void setNavigationListener(NavigationListener navigationListener) {
        super.setNavigationListenerInternal(navigationListener);
    }

    private WazeAudioSdk(Context context, WazeAudioSdkSettings wazeAudioSdkSettings, WazeSdkCallback wazeSdkCallback) {
        super(context, wazeAudioSdkSettings, wazeSdkCallback);
    }
}
