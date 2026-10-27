package com.amazon.mp3.reactnative.modules;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SingleModuleReactPackage.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016\u0082\u0001\u000e\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/reactnative/modules/SingleModuleReactPackage;", "Lcom/facebook/react/ReactPackage;", "()V", "createViewManagers", "", "Lcom/facebook/react/uimanager/ViewManager;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "Lcom/amazon/mp3/reactnative/modules/IdentityPackage;", "Lcom/amazon/mp3/reactnative/modules/DeviceLoggerPackage;", "Lcom/amazon/mp3/reactnative/modules/DeeplinkPackage;", "Lcom/amazon/mp3/reactnative/modules/DeviceCachePackage;", "Lcom/amazon/mp3/reactnative/modules/NotificationPackage;", "Lcom/amazon/mp3/reactnative/modules/ToastPackage;", "Lcom/amazon/mp3/reactnative/modules/MetricsLoggerPackage;", "Lcom/amazon/mp3/reactnative/modules/DeviceInfoPackage;", "Lcom/amazon/mp3/reactnative/modules/NetworkInfoPackage;", "Lcom/amazon/mp3/reactnative/modules/PlaybackPackage;", "Lcom/amazon/mp3/reactnative/modules/PlayQueuePackage;", "Lcom/amazon/mp3/reactnative/modules/FeatureGatingPackage;", "Lcom/amazon/mp3/reactnative/modules/PlaybackControlsPackage;", "Lcom/amazon/mp3/reactnative/modules/SettingPackage;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class SingleModuleReactPackage implements ReactPackage {
    public /* synthetic */ SingleModuleReactPackage(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private SingleModuleReactPackage() {
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager<?, ?>> createViewManagers(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return new ArrayList();
    }
}
