package com.amazon.mp3.reactnative;

import android.app.Application;
import androidx.fragment.app.FragmentActivity;
import androidx.tracing.Trace;
import com.amazon.mp3.reactnative.modules.DeeplinkPackage;
import com.amazon.mp3.reactnative.modules.DeviceCachePackage;
import com.amazon.mp3.reactnative.modules.DeviceInfoPackage;
import com.amazon.mp3.reactnative.modules.DeviceLoggerPackage;
import com.amazon.mp3.reactnative.modules.FeatureGatingPackage;
import com.amazon.mp3.reactnative.modules.IdentityPackage;
import com.amazon.mp3.reactnative.modules.MetricsLoggerPackage;
import com.amazon.mp3.reactnative.modules.NetworkInfoPackage;
import com.amazon.mp3.reactnative.modules.NotificationPackage;
import com.amazon.mp3.reactnative.modules.PlaybackControlsPackage;
import com.amazon.mp3.reactnative.modules.PlaybackPackage;
import com.amazon.mp3.reactnative.modules.ToastPackage;
import com.amazon.mp3.reactnativemodule.provider.IdentityProvider;
import com.amazon.mp3.reactnativemodule.provider.ToastProvider;
import com.amazon.mp3.reactnativemodule.provider.ToolbarProvider;
import com.facebook.hermes.reactexecutor.HermesExecutorFactory;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.shell.MainReactPackage;
import com.horcrux.svg.SvgPackage;
import com.reactnativecommunity.asyncstorage.AsyncStoragePackage;
import com.shopify.reactnative.flash_list.ReactNativeFlashListPackage;
import com.swmansion.gesturehandler.RNGestureHandlerPackage;
import com.swmansion.rnscreens.RNScreensPackage;
import com.th3rdwave.safeareacontext.SafeAreaContextPackage;
import java.util.ArrayList;
import java.util.List;
import org.reactnative.maskedview.RNCMaskedViewPackage;

/* JADX INFO: loaded from: classes4.dex */
public class ReactInitializer extends ReactNativeHost {
    private final String TAG;
    private FragmentActivity hostActivity;
    private IdentityProvider identityProvider;
    private ToolbarProvider toolbarProvider;

    @Override // com.facebook.react.ReactNativeHost
    public String getBundleAssetName() {
        return "legacy.android.bundle";
    }

    @Override // com.facebook.react.ReactNativeHost
    public String getJSMainModuleName() {
        return "index";
    }

    @Override // com.facebook.react.ReactNativeHost
    public boolean getUseDeveloperSupport() {
        return false;
    }

    public void setToastProvider(ToastProvider toastProvider) {
    }

    public ReactInitializer(Application application) {
        super(application);
        this.TAG = ReactInitializer.class.getSimpleName();
    }

    @Override // com.facebook.react.ReactNativeHost
    public List<ReactPackage> getPackages() {
        Trace.beginSection("AMA.ReactInitializer.getPackages");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MainReactPackage());
        arrayList.add(new SvgPackage());
        arrayList.add(new RNScreensPackage());
        arrayList.add(new RNGestureHandlerPackage());
        arrayList.add(new SafeAreaContextPackage());
        arrayList.add(new AsyncStoragePackage());
        arrayList.add(new ReactNativeFlashListPackage());
        arrayList.add(new RNCMaskedViewPackage());
        arrayList.add(IdentityPackage.INSTANCE);
        arrayList.add(DeviceLoggerPackage.INSTANCE);
        arrayList.add(DeeplinkPackage.INSTANCE);
        arrayList.add(DeviceCachePackage.INSTANCE);
        arrayList.add(NotificationPackage.INSTANCE);
        arrayList.add(MetricsLoggerPackage.INSTANCE);
        arrayList.add(new ToastPackage(this.hostActivity));
        arrayList.add(DeviceInfoPackage.INSTANCE);
        arrayList.add(NetworkInfoPackage.INSTANCE);
        arrayList.add(PlaybackPackage.INSTANCE);
        arrayList.add(FeatureGatingPackage.INSTANCE);
        arrayList.add(PlaybackControlsPackage.INSTANCE);
        Trace.endSection();
        return arrayList;
    }

    public void setIdentityProvider(IdentityProvider identityProvider) {
        this.identityProvider = identityProvider;
    }

    public void setToolbarProvider(ToolbarProvider toolbarProvider) {
        this.toolbarProvider = toolbarProvider;
    }

    public ToolbarProvider getToolbarProvider() {
        return this.toolbarProvider;
    }

    public void setHostActivity(FragmentActivity hostActivity) {
        this.hostActivity = hostActivity;
    }

    @Override // com.facebook.react.ReactNativeHost
    protected JavaScriptExecutorFactory getJavaScriptExecutorFactory() {
        return new HermesExecutorFactory();
    }
}
