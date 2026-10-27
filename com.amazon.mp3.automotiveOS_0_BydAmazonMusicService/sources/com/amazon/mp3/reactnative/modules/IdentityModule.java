package com.amazon.mp3.reactnative.modules;

import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.ProfileProvider;
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

/* JADX INFO: compiled from: IdentityModule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 '2\u00020\u0001:\u0001'B\u0011\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0007J\u0010\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0007J\u0010\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0007J\u0010\u0010 \u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0007J\u0010\u0010!\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0007J\b\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0007J\u0010\u0010%\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0007J\u0010\u0010&\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0007R\u001d\u0010\u0005\u001a\u0004\u0018\u00010\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\u000b\u001a\u0004\u0018\u00010\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u0010\u001a\u0004\u0018\u00010\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0015\u001a\u0004\u0018\u00010\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u0017\u0010\u0018¨\u0006("}, d2 = {"Lcom/amazon/mp3/reactnative/modules/IdentityModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "context", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "authenticationProvider", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "getAuthenticationProvider", "()Lcom/amazon/music/platform/providers/AuthenticationProvider;", "authenticationProvider$delegate", "Lkotlin/Lazy;", "customerMetadataProvider", "Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "getCustomerMetadataProvider", "()Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "customerMetadataProvider$delegate", "deviceInfoProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "getDeviceInfoProvider", "()Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "deviceInfoProvider$delegate", "profileProvider", "Lcom/amazon/music/platform/providers/ProfileProvider;", "getProfileProvider", "()Lcom/amazon/music/platform/providers/ProfileProvider;", "profileProvider$delegate", "getCustomerId", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "getDeviceId", "getDeviceType", "getLocale", "getMusicTerritory", "getName", "", "getProfileId", "getSessionId", "getToken", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class IdentityModule extends ReactContextBaseJavaModule {
    private static final String TAG = IdentityModule.class.getSimpleName();

    /* JADX INFO: renamed from: authenticationProvider$delegate, reason: from kotlin metadata */
    private final Lazy authenticationProvider;

    /* JADX INFO: renamed from: customerMetadataProvider$delegate, reason: from kotlin metadata */
    private final Lazy customerMetadataProvider;

    /* JADX INFO: renamed from: deviceInfoProvider$delegate, reason: from kotlin metadata */
    private final Lazy deviceInfoProvider;

    /* JADX INFO: renamed from: profileProvider$delegate, reason: from kotlin metadata */
    private final Lazy profileProvider;

    public IdentityModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.authenticationProvider = LazyKt__LazyJVMKt.lazy(new Function0<AuthenticationProvider>() { // from class: com.amazon.mp3.reactnative.modules.IdentityModule$authenticationProvider$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AuthenticationProvider invoke() {
                AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
                String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(AuthenticationProvider.class));
                Intrinsics.checkNotNull(originalClassName);
                InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
                if (!(interfaceProvider instanceof AuthenticationProvider)) {
                    interfaceProvider = null;
                }
                return (AuthenticationProvider) interfaceProvider;
            }
        });
        this.deviceInfoProvider = LazyKt__LazyJVMKt.lazy(new Function0<DeviceInfoProvider>() { // from class: com.amazon.mp3.reactnative.modules.IdentityModule$deviceInfoProvider$2
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
        this.profileProvider = LazyKt__LazyJVMKt.lazy(new Function0<ProfileProvider>() { // from class: com.amazon.mp3.reactnative.modules.IdentityModule$profileProvider$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ProfileProvider invoke() {
                AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
                String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ProfileProvider.class));
                Intrinsics.checkNotNull(originalClassName);
                InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
                if (!(interfaceProvider instanceof ProfileProvider)) {
                    interfaceProvider = null;
                }
                return (ProfileProvider) interfaceProvider;
            }
        });
        this.customerMetadataProvider = LazyKt__LazyJVMKt.lazy(new Function0<CustomerMetadataProvider>() { // from class: com.amazon.mp3.reactnative.modules.IdentityModule$customerMetadataProvider$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CustomerMetadataProvider invoke() {
                AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
                String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(CustomerMetadataProvider.class));
                Intrinsics.checkNotNull(originalClassName);
                InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
                if (!(interfaceProvider instanceof CustomerMetadataProvider)) {
                    interfaceProvider = null;
                }
                return (CustomerMetadataProvider) interfaceProvider;
            }
        });
    }

    private final AuthenticationProvider getAuthenticationProvider() {
        return (AuthenticationProvider) this.authenticationProvider.getValue();
    }

    private final DeviceInfoProvider getDeviceInfoProvider() {
        return (DeviceInfoProvider) this.deviceInfoProvider.getValue();
    }

    private final ProfileProvider getProfileProvider() {
        return (ProfileProvider) this.profileProvider.getValue();
    }

    private final CustomerMetadataProvider getCustomerMetadataProvider() {
        return (CustomerMetadataProvider) this.customerMetadataProvider.getValue();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        return TAG2;
    }

    @ReactMethod
    public final void getToken(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        AuthenticationProvider authenticationProvider = getAuthenticationProvider();
        if (authenticationProvider == null) {
            unit = null;
        } else {
            PromiseExtensionKt.resolveOrRejectWhenNull(promise, authenticationProvider.getAccessTokenBlocking(), "getToken");
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            PromiseExtensionKt.rejectWithProviderNotFound(promise, "AuthenticationProvider");
        }
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
            PromiseExtensionKt.rejectWithProviderNotFound(promise, "DeviceInfoProvider");
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
            PromiseExtensionKt.rejectWithProviderNotFound(promise, "DeviceInfoProvider");
        }
    }

    @ReactMethod
    public final void getMusicTerritory(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        CustomerMetadataProvider customerMetadataProvider = getCustomerMetadataProvider();
        if (customerMetadataProvider == null) {
            unit = null;
        } else {
            PromiseExtensionKt.resolveOrRejectWhenNull(promise, customerMetadataProvider.getCustomerMetadata().getMusicTerritory(), "getMusicTerritory");
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            PromiseExtensionKt.rejectWithProviderNotFound(promise, "CustomerMetadataProvider");
        }
    }

    @ReactMethod
    public final void getCustomerId(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        PromiseExtensionKt.resolveOrRejectWhenNull(promise, AccountDetailUtil.get(getReactApplicationContext()).getCustomerId(), "getCustomerId");
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
            PromiseExtensionKt.rejectWithProviderNotFound(promise, "DeviceInfoProvider");
        }
    }

    @ReactMethod
    public final void getProfileId(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        ProfileProvider profileProvider = getProfileProvider();
        Unit unit = null;
        if (profileProvider != null) {
            profileProvider.currentProfile();
            PromiseExtensionKt.resolveOrRejectWhenNull(promise, null, "getProfileId");
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            PromiseExtensionKt.rejectWithProviderNotFound(promise, "ProfileProvider");
        }
    }

    @ReactMethod
    public final void getSessionId(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        CustomerMetadataProvider customerMetadataProvider = getCustomerMetadataProvider();
        if (customerMetadataProvider == null) {
            unit = null;
        } else {
            PromiseExtensionKt.resolveOrRejectWhenNull(promise, customerMetadataProvider.getCustomerMetadata().getSessionId(), "getSessionId");
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            PromiseExtensionKt.rejectWithProviderNotFound(promise, "CustomerMetadataProvider");
        }
    }
}
