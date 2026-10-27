package com.amazon.mp3.reactnative;

import android.app.Application;
import android.os.Trace;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.reactnative.modules.AmazonNativeModules;
import com.amazon.music.skyfire.platform.Log;
import com.facebook.hermes.reactexecutor.HermesExecutorFactory;
import com.facebook.react.PackageList;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.defaults.DefaultReactNativeHost;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PrimaryReactInitializer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u0006H\u0014J\b\u0010\u000b\u001a\u00020\u0006H\u0014J\n\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\b\u001a\u00020\tR\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/reactnative/PrimaryReactInitializer;", "Lcom/facebook/react/defaults/DefaultReactNativeHost;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "TAG", "", "kotlin.jvm.PlatformType", "hostActivity", "Landroidx/fragment/app/FragmentActivity;", "getBundleAssetName", "getJSMainModuleName", "getJavaScriptExecutorFactory", "Lcom/facebook/react/bridge/JavaScriptExecutorFactory;", "getPackages", "", "Lcom/facebook/react/ReactPackage;", "getUseDeveloperSupport", "", "initializeBridge", "", "setHostActivity", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PrimaryReactInitializer extends DefaultReactNativeHost {
    private final String TAG;
    private FragmentActivity hostActivity;

    @Override // com.facebook.react.ReactNativeHost
    protected String getBundleAssetName() {
        return "index.android.bundle";
    }

    @Override // com.facebook.react.ReactNativeHost
    protected String getJSMainModuleName() {
        return "index";
    }

    @Override // com.facebook.react.ReactNativeHost
    public boolean getUseDeveloperSupport() {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PrimaryReactInitializer(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.TAG = PrimaryReactInitializer.class.getSimpleName();
    }

    @Override // com.facebook.react.ReactNativeHost
    protected List<ReactPackage> getPackages() {
        ArrayList<ReactPackage> packages = new PackageList(this).getPackages();
        Intrinsics.checkNotNullExpressionValue(packages, "PackageList(this).packages");
        packages.addAll(AmazonNativeModules.INSTANCE.getNativeModulePackages(this.hostActivity));
        return packages;
    }

    @Override // com.facebook.react.ReactNativeHost
    protected JavaScriptExecutorFactory getJavaScriptExecutorFactory() {
        return new HermesExecutorFactory();
    }

    public final void initializeBridge() {
        Trace.beginSection("AMA.PrimaryReactInitializer.initializeBridge");
        getReactInstanceManager().createReactContextInBackground();
        Log log = Log.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkNotNullExpressionValue(TAG, "TAG");
        log.debug(TAG, "React Native bridge initialization via createReactContextInBackground started");
        Trace.endSection();
    }

    public final void setHostActivity(FragmentActivity hostActivity) {
        Intrinsics.checkNotNullParameter(hostActivity, "hostActivity");
        this.hostActivity = hostActivity;
    }
}
