package com.amazon.mp3.reactnative.modules;

import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: DeviceInfoModule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u0017\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007R\u001d\u0010\u0005\u001a\u0004\u0018\u00010\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/reactnative/modules/DeviceInfoModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "context", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "deviceInfoProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "getDeviceInfoProvider", "()Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "deviceInfoProvider$delegate", "Lkotlin/Lazy;", "getDeviceId", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "getDeviceType", "getLocale", "getManufacturer", "getModel", "getName", "", "getOsVersion", "getTimeZone", "getUserAgent", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeviceInfoModule extends ReactContextBaseJavaModule {
    private static final String PROVIDER_NAME = "DeviceInfoProvider";

    /* JADX INFO: renamed from: deviceInfoProvider$delegate, reason: from kotlin metadata */
    private final Lazy deviceInfoProvider;
    private static final String TAG = DeviceInfoModule.class.getSimpleName();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceInfoModule(ReactApplicationContext context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.deviceInfoProvider = LazyKt__LazyJVMKt.lazy(new Function0<DeviceInfoProvider>() { // from class: com.amazon.mp3.reactnative.modules.DeviceInfoModule$deviceInfoProvider$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DeviceInfoProvider invoke() {
                AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
                String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(DeviceInfoProvider.class));
                Intrinsics.checkNotNull(originalClassName);
                InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
                if (!(interfaceProvider instanceof DeviceInfoProvider)) {
                    interfaceProvider = null;
                }
                return (DeviceInfoProvider) interfaceProvider;
            }
        });
    }

    private final DeviceInfoProvider getDeviceInfoProvider() {
        return (DeviceInfoProvider) this.deviceInfoProvider.getValue();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        return TAG2;
    }

    @ReactMethod
    public final void getDeviceId(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        DeviceInfoProvider deviceInfoProvider = getDeviceInfoProvider();
        if (deviceInfoProvider == null) {
            unit = null;
        } else {
            promise.resolve(deviceInfoProvider.getDeviceIdentity().getDeviceId());
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            PromiseExtensionKt.rejectWithProviderNotFound(promise, PROVIDER_NAME);
        }
    }

    @ReactMethod
    public final void getDeviceType(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        DeviceInfoProvider deviceInfoProvider = getDeviceInfoProvider();
        if (deviceInfoProvider == null) {
            unit = null;
        } else {
            promise.resolve(deviceInfoProvider.getDeviceIdentity().getDeviceTypeId());
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            PromiseExtensionKt.rejectWithProviderNotFound(promise, PROVIDER_NAME);
        }
    }

    @ReactMethod
    public final void getLocale(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        DeviceInfoProvider deviceInfoProvider = getDeviceInfoProvider();
        if (deviceInfoProvider == null) {
            unit = null;
        } else {
            String string = (String) CollectionsKt___CollectionsKt.firstOrNull((List) deviceInfoProvider.getLocales());
            if (string == null) {
                string = Locale.getDefault().toString();
                Intrinsics.checkNotNullExpressionValue(string, "getDefault().toString()");
            }
            promise.resolve(string);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            PromiseExtensionKt.rejectWithProviderNotFound(promise, PROVIDER_NAME);
        }
    }

    @ReactMethod
    public final void getManufacturer(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        DeviceInfoProvider deviceInfoProvider = getDeviceInfoProvider();
        if (deviceInfoProvider == null) {
            unit = null;
        } else {
            promise.resolve(deviceInfoProvider.getManufacturer());
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            PromiseExtensionKt.rejectWithProviderNotFound(promise, PROVIDER_NAME);
        }
    }

    @ReactMethod
    public final void getModel(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        DeviceInfoProvider deviceInfoProvider = getDeviceInfoProvider();
        if (deviceInfoProvider == null) {
            unit = null;
        } else {
            promise.resolve(deviceInfoProvider.getModel());
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            PromiseExtensionKt.rejectWithProviderNotFound(promise, PROVIDER_NAME);
        }
    }

    @ReactMethod
    public final void getOsVersion(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        DeviceInfoProvider deviceInfoProvider = getDeviceInfoProvider();
        if (deviceInfoProvider == null) {
            unit = null;
        } else {
            promise.resolve(deviceInfoProvider.getOsVersion());
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            PromiseExtensionKt.rejectWithProviderNotFound(promise, PROVIDER_NAME);
        }
    }

    @ReactMethod
    public final void getTimeZone(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        DeviceInfoProvider deviceInfoProvider = getDeviceInfoProvider();
        if (deviceInfoProvider == null) {
            unit = null;
        } else {
            promise.resolve(deviceInfoProvider.getTimeZone());
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            PromiseExtensionKt.rejectWithProviderNotFound(promise, PROVIDER_NAME);
        }
    }

    @ReactMethod
    public final void getUserAgent(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        DeviceInfoProvider deviceInfoProvider = getDeviceInfoProvider();
        if (deviceInfoProvider == null) {
            unit = null;
        } else {
            promise.resolve(deviceInfoProvider.getUserAgent());
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            PromiseExtensionKt.rejectWithProviderNotFound(promise, PROVIDER_NAME);
        }
    }
}
