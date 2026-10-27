package com.amazon.mp3.reactnative.modules;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SingleModuleReactPackage.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/reactnative/modules/DeviceLoggerPackage;", "Lcom/amazon/mp3/reactnative/modules/SingleModuleReactPackage;", "()V", "createNativeModules", "", "Lcom/facebook/react/bridge/NativeModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeviceLoggerPackage extends SingleModuleReactPackage {
    public static final DeviceLoggerPackage INSTANCE = new DeviceLoggerPackage();

    private DeviceLoggerPackage() {
        super(null);
    }

    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return CollectionsKt__CollectionsKt.mutableListOf(new DeviceLoggerModule(reactContext));
    }
}
