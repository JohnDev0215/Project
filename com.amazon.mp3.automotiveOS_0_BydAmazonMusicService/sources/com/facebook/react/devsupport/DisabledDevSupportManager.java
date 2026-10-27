package com.facebook.react.devsupport;

import android.app.Activity;
import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.DefaultJSExceptionHandler;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.SurfaceDelegate;
import com.facebook.react.devsupport.interfaces.DevOptionHandler;
import com.facebook.react.devsupport.interfaces.DevSplitBundleCallback;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;

/* JADX INFO: loaded from: classes6.dex */
public class DisabledDevSupportManager implements DevSupportManager {
    private final DefaultJSExceptionHandler mDefaultJSExceptionHandler = new DefaultJSExceptionHandler();

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void addCustomDevOption(String str, DevOptionHandler devOptionHandler) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    @Nullable
    public View createRootView(String str) {
        return null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    @Nullable
    public SurfaceDelegate createSurfaceDelegate(String str) {
        return null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void destroyRootView(View view) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    @Nullable
    public Activity getCurrentActivity() {
        return null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public DeveloperSettings getDevSettings() {
        return null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public boolean getDevSupportEnabled() {
        return false;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public String getDownloadedJSBundleFile() {
        return null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public int getLastErrorCookie() {
        return 0;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public String getSourceUrl() {
        return null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void handleReloadJS() {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public boolean hasUpToDateJSBundleInCache() {
        return false;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void hideRedboxDialog() {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void loadSplitBundleFromServer(String str, DevSplitBundleCallback devSplitBundleCallback) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void onNewReactContextCreated(ReactContext reactContext) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void onReactInstanceDestroyed(ReactContext reactContext) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setDevSupportEnabled(boolean z) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setFpsDebugEnabled(boolean z) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setHotModuleReplacementEnabled(boolean z) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setRemoteJSDebugEnabled(boolean z) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void showDevOptionsDialog() {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void startInspector() {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void toggleElementInspector() {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void updateJSError(String str, ReadableArray readableArray, int i) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void isPackagerRunning(PackagerStatusCallback packagerStatusCallback) {
        packagerStatusCallback.onPackagerStatusFetched(false);
    }

    @Override // com.facebook.react.bridge.JSExceptionHandler
    public void handleException(Exception exc) {
        this.mDefaultJSExceptionHandler.handleException(exc);
    }
}
