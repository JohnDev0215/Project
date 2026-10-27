package com.amazon.mp3.reactnative.modules;

import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.NetworkInfoProvider;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: NetworkInfoModule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007R\u001d\u0010\u0005\u001a\u0004\u0018\u00010\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/reactnative/modules/NetworkInfoModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "context", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "networkInfoProvider", "Lcom/amazon/music/platform/providers/NetworkInfoProvider;", "getNetworkInfoProvider", "()Lcom/amazon/music/platform/providers/NetworkInfoProvider;", "networkInfoProvider$delegate", "Lkotlin/Lazy;", "getName", "", "isConnected", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NetworkInfoModule extends ReactContextBaseJavaModule {
    private static final String TAG = NetworkInfoModule.class.getSimpleName();

    /* JADX INFO: renamed from: networkInfoProvider$delegate, reason: from kotlin metadata */
    private final Lazy networkInfoProvider;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "NetInfoModule";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkInfoModule(ReactApplicationContext context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.networkInfoProvider = LazyKt__LazyJVMKt.lazy(new Function0<NetworkInfoProvider>() { // from class: com.amazon.mp3.reactnative.modules.NetworkInfoModule$networkInfoProvider$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final NetworkInfoProvider invoke() {
                AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
                String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(NetworkInfoProvider.class));
                Intrinsics.checkNotNull(originalClassName);
                InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
                if (!(interfaceProvider instanceof NetworkInfoProvider)) {
                    interfaceProvider = null;
                }
                return (NetworkInfoProvider) interfaceProvider;
            }
        });
    }

    private final NetworkInfoProvider getNetworkInfoProvider() {
        return (NetworkInfoProvider) this.networkInfoProvider.getValue();
    }

    @ReactMethod
    public final void isConnected(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        NetworkInfoProvider networkInfoProvider = getNetworkInfoProvider();
        if (networkInfoProvider == null) {
            unit = null;
        } else {
            promise.resolve(Boolean.valueOf(networkInfoProvider.getNetworkInfo().getIsConnected()));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            PromiseExtensionKt.rejectWithProviderNotFound(promise, "NetworkInfoProvider");
        }
    }
}
