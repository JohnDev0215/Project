package com.facebook.react.defaults;

import android.app.Application;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.JSIModulePackage;
import com.facebook.react.defaults.DefaultTurboModuleManagerDelegate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultReactNativeHost.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0011\b\u0004\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0014J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0014R\u0014\u0010\u0007\u001a\u00020\u00068TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/facebook/react/defaults/DefaultReactNativeHost;", "Lcom/facebook/react/ReactNativeHost;", "Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$Builder;", "getReactPackageTurboModuleManagerDelegateBuilder", "Lcom/facebook/react/bridge/JSIModulePackage;", "getJSIModulePackage", "", "isNewArchEnabled", "()Z", "Landroid/app/Application;", "application", "<init>", "(Landroid/app/Application;)V", "ReactAndroid_release"}, k = 1, mv = {1, 6, 0})
public abstract class DefaultReactNativeHost extends ReactNativeHost {
    protected boolean isNewArchEnabled() {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    protected DefaultReactNativeHost(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
    }

    @Override // com.facebook.react.ReactNativeHost
    protected ReactPackageTurboModuleManagerDelegate.Builder getReactPackageTurboModuleManagerDelegateBuilder() {
        if (isNewArchEnabled()) {
            return new DefaultTurboModuleManagerDelegate.Builder();
        }
        return null;
    }

    @Override // com.facebook.react.ReactNativeHost
    protected JSIModulePackage getJSIModulePackage() {
        if (isNewArchEnabled()) {
            return new DefaultJSIModulePackage(this);
        }
        return null;
    }
}
