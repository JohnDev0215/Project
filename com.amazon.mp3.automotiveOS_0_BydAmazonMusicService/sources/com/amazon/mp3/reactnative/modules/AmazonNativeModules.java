package com.amazon.mp3.reactnative.modules;

import androidx.fragment.app.FragmentActivity;
import com.facebook.react.ReactPackage;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: SingleModuleReactPackage.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/reactnative/modules/AmazonNativeModules;", "", "()V", "getNativeModulePackages", "", "Lcom/facebook/react/ReactPackage;", "hostActivity", "Landroidx/fragment/app/FragmentActivity;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AmazonNativeModules {
    public static final AmazonNativeModules INSTANCE = new AmazonNativeModules();

    private AmazonNativeModules() {
    }

    public final List<ReactPackage> getNativeModulePackages(FragmentActivity hostActivity) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(IdentityPackage.INSTANCE);
        arrayList.add(DeviceLoggerPackage.INSTANCE);
        arrayList.add(DeviceInfoPackage.INSTANCE);
        arrayList.add(DeeplinkPackage.INSTANCE);
        arrayList.add(DeviceCachePackage.INSTANCE);
        arrayList.add(NotificationPackage.INSTANCE);
        arrayList.add(MetricsLoggerPackage.INSTANCE);
        arrayList.add(new ToastPackage(hostActivity));
        arrayList.add(NetworkInfoPackage.INSTANCE);
        arrayList.add(PlaybackPackage.INSTANCE);
        arrayList.add(PlayQueuePackage.INSTANCE);
        arrayList.add(FeatureGatingPackage.INSTANCE);
        arrayList.add(PlaybackControlsPackage.INSTANCE);
        arrayList.add(SettingPackage.INSTANCE);
        return arrayList;
    }
}
