package com.amazon.mp3.auto.carmode;

import com.amazon.mp3.featuregate.Feature;
import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: CarModeConfigs.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0003\u001a\u00020\u0002H\u0007J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0007J\u0006\u0010\t\u001a\u00020\u0007J\u0006\u0010\n\u001a\u00020\u0002J\u0006\u0010\u000b\u001a\u00020\u0002¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/auto/carmode/CarModeConfigs;", "", "", "isCarModeAutoLaunchAllowed", "", "getPresetsLimit", "getCarModeAddToPresetsLimit", "", "getCarModePageTimeout", "getCarModeDetailPageTimeout", "alwaysShowDisclaimer", "showAdditionalDisclaimer", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CarModeConfigs {
    public static final CarModeConfigs INSTANCE = new CarModeConfigs();

    private CarModeConfigs() {
    }

    public final int getPresetsLimit() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ConfigurationProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof ConfigurationProvider)) {
            interfaceProvider = null;
        }
        ConfigurationProvider configurationProvider = (ConfigurationProvider) interfaceProvider;
        Configuration configuration = configurationProvider instanceof Configuration ? (Configuration) configurationProvider : null;
        Integer numMo2013int = configuration != null ? configuration.mo2013int("car_mode_v2_presets_limit") : null;
        if (numMo2013int == null || numMo2013int.intValue() <= 0) {
            return 15;
        }
        return numMo2013int.intValue();
    }

    public final int getCarModeAddToPresetsLimit() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ConfigurationProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof ConfigurationProvider)) {
            interfaceProvider = null;
        }
        ConfigurationProvider configurationProvider = (ConfigurationProvider) interfaceProvider;
        Configuration configuration = configurationProvider instanceof Configuration ? (Configuration) configurationProvider : null;
        Integer numMo2013int = configuration != null ? configuration.mo2013int("car_mode_v2_add_to_presets_limit") : null;
        if (numMo2013int == null || numMo2013int.intValue() <= 0) {
            return 12;
        }
        return numMo2013int.intValue();
    }

    public final long getCarModePageTimeout() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ConfigurationProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof ConfigurationProvider)) {
            interfaceProvider = null;
        }
        ConfigurationProvider configurationProvider = (ConfigurationProvider) interfaceProvider;
        Configuration configuration = configurationProvider instanceof Configuration ? (Configuration) configurationProvider : null;
        Integer numMo2013int = configuration != null ? configuration.mo2013int("car_mode_page_request_timeout_ms") : null;
        if (numMo2013int == null || numMo2013int.intValue() <= 0) {
            return 20000L;
        }
        return numMo2013int.intValue();
    }

    public final long getCarModeDetailPageTimeout() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ConfigurationProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof ConfigurationProvider)) {
            interfaceProvider = null;
        }
        ConfigurationProvider configurationProvider = (ConfigurationProvider) interfaceProvider;
        Configuration configuration = configurationProvider instanceof Configuration ? (Configuration) configurationProvider : null;
        Integer numMo2013int = configuration != null ? configuration.mo2013int("car_mode_detail_page_request_timeout_ms") : null;
        if (numMo2013int == null || numMo2013int.intValue() <= 0) {
            return 15000L;
        }
        return numMo2013int.intValue();
    }

    public final boolean alwaysShowDisclaimer() {
        return Feature.car_mode_always_show_disclaimer.isEnabled();
    }

    public final boolean showAdditionalDisclaimer() {
        return Feature.car_mode_additional_disclaimer.isEnabled();
    }

    @JvmStatic
    public static final boolean isCarModeAutoLaunchAllowed() {
        return Feature.car_mode_auto_launch.isEnabled();
    }
}
