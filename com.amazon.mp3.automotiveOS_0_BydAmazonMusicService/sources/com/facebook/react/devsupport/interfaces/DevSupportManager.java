package com.facebook.react.devsupport.interfaces;

import android.app.Activity;
import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.JSExceptionHandler;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.SurfaceDelegate;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;

/* JADX INFO: loaded from: classes6.dex */
public interface DevSupportManager extends JSExceptionHandler {
    void addCustomDevOption(String str, DevOptionHandler devOptionHandler);

    @Nullable
    View createRootView(String str);

    @Nullable
    SurfaceDelegate createSurfaceDelegate(String str);

    void destroyRootView(View view);

    @Nullable
    Activity getCurrentActivity();

    DeveloperSettings getDevSettings();

    boolean getDevSupportEnabled();

    String getDownloadedJSBundleFile();

    int getLastErrorCookie();

    String getSourceUrl();

    void handleReloadJS();

    boolean hasUpToDateJSBundleInCache();

    void hideRedboxDialog();

    void isPackagerRunning(PackagerStatusCallback packagerStatusCallback);

    void loadSplitBundleFromServer(String str, DevSplitBundleCallback devSplitBundleCallback);

    void onNewReactContextCreated(ReactContext reactContext);

    void onReactInstanceDestroyed(ReactContext reactContext);

    void setDevSupportEnabled(boolean z);

    void setFpsDebugEnabled(boolean z);

    void setHotModuleReplacementEnabled(boolean z);

    void setRemoteJSDebugEnabled(boolean z);

    void showDevOptionsDialog();

    void startInspector();

    void toggleElementInspector();

    void updateJSError(String str, ReadableArray readableArray, int i);
}
