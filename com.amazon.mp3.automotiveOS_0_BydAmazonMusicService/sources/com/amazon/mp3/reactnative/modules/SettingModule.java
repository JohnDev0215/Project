package com.amazon.mp3.reactnative.modules;

import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.data.eligibility.AppStateEligibility;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.CapabilityActionProvider;
import com.amazon.music.platform.providers.CustomerPreferencesProvider;
import com.amazon.music.platform.providers.EligibilityProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.impl.CapabilityActionProviderImpl;
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

/* JADX INFO: compiled from: SettingModule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0007R#\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001d\u0010\f\u001a\u0004\u0018\u00010\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/reactnative/modules/SettingModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "context", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "appStateEligibilityProvider", "Lcom/amazon/music/platform/providers/EligibilityProvider;", "Lcom/amazon/music/platform/data/eligibility/AppStateEligibility;", "getAppStateEligibilityProvider", "()Lcom/amazon/music/platform/providers/EligibilityProvider;", "appStateEligibilityProvider$delegate", "Lkotlin/Lazy;", "customerPreferencesProvider", "Lcom/amazon/music/platform/providers/CustomerPreferencesProvider;", "getCustomerPreferencesProvider", "()Lcom/amazon/music/platform/providers/CustomerPreferencesProvider;", "customerPreferencesProvider$delegate", "getName", "", "isExplicitLanguageAllowed", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "isWakeWordEnabled", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SettingModule extends ReactContextBaseJavaModule {
    private static final String TAG = SettingModule.class.getSimpleName();

    /* JADX INFO: renamed from: appStateEligibilityProvider$delegate, reason: from kotlin metadata */
    private final Lazy appStateEligibilityProvider;

    /* JADX INFO: renamed from: customerPreferencesProvider$delegate, reason: from kotlin metadata */
    private final Lazy customerPreferencesProvider;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingModule(ReactApplicationContext context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.customerPreferencesProvider = LazyKt__LazyJVMKt.lazy(new Function0<CustomerPreferencesProvider>() { // from class: com.amazon.mp3.reactnative.modules.SettingModule$customerPreferencesProvider$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CustomerPreferencesProvider invoke() {
                AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
                String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(CustomerPreferencesProvider.class));
                Intrinsics.checkNotNull(originalClassName);
                InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
                if (!(interfaceProvider instanceof CustomerPreferencesProvider)) {
                    interfaceProvider = null;
                }
                return (CustomerPreferencesProvider) interfaceProvider;
            }
        });
        this.appStateEligibilityProvider = LazyKt__LazyJVMKt.lazy(new Function0<EligibilityProvider<? extends AppStateEligibility>>() { // from class: com.amazon.mp3.reactnative.modules.SettingModule$appStateEligibilityProvider$2
            @Override // kotlin.jvm.functions.Function0
            public final EligibilityProvider<? extends AppStateEligibility> invoke() {
                AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
                String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(CapabilityActionProvider.class));
                Intrinsics.checkNotNull(originalClassName);
                InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
                if (!(interfaceProvider instanceof CapabilityActionProvider)) {
                    interfaceProvider = null;
                }
                CapabilityActionProvider capabilityActionProvider = (CapabilityActionProvider) interfaceProvider;
                CapabilityActionProviderImpl capabilityActionProviderImpl = capabilityActionProvider instanceof CapabilityActionProviderImpl ? (CapabilityActionProviderImpl) capabilityActionProvider : null;
                if (capabilityActionProviderImpl == null) {
                    return null;
                }
                return capabilityActionProviderImpl.getAppStateEligibilityProvider();
            }
        });
    }

    private final CustomerPreferencesProvider getCustomerPreferencesProvider() {
        return (CustomerPreferencesProvider) this.customerPreferencesProvider.getValue();
    }

    private final EligibilityProvider<AppStateEligibility> getAppStateEligibilityProvider() {
        return (EligibilityProvider) this.appStateEligibilityProvider.getValue();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        return TAG2;
    }

    @ReactMethod
    public final void isWakeWordEnabled(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        EligibilityProvider<AppStateEligibility> appStateEligibilityProvider = getAppStateEligibilityProvider();
        if (appStateEligibilityProvider != null) {
            AppStateEligibility eligibility = appStateEligibilityProvider.getEligibility();
            PromiseExtensionKt.resolveOrRejectWhenNull(promise, eligibility != null ? Boolean.valueOf(eligibility.getIsWakeWordEnabled()) : null, "isWakeWordEnabled");
            obj = Unit.INSTANCE;
        }
        if (obj == null) {
            PromiseExtensionKt.rejectWithProviderNotFound(promise, "AppStateEligibility");
        }
    }

    @ReactMethod
    public final void isExplicitLanguageAllowed(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        CustomerPreferencesProvider customerPreferencesProvider = getCustomerPreferencesProvider();
        if (customerPreferencesProvider == null) {
            unit = null;
        } else {
            promise.resolve(Boolean.valueOf(customerPreferencesProvider.isExplicitLanguageAllowed()));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            PromiseExtensionKt.rejectWithProviderNotFound(promise, "CustomerPreferencesProvider");
        }
    }
}
