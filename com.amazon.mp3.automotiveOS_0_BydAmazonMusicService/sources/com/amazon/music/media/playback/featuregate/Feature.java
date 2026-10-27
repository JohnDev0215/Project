package com.amazon.music.media.playback.featuregate;

import android.util.Log;
import com.amazon.music.featuregating.rules.BooleanConfigurationRule;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: Feature.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lcom/amazon/music/media/playback/featuregate/Feature;", "", "Lcom/amazon/music/platform/featuregate/Feature;", "(Ljava/lang/String;I)V", Constants.ENABLE_DISABLE, "", "featureGateProvider", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "sonic_rush", "entity_skippable_check", "start_foreground_on_update_notification", "Companion", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum Feature implements com.amazon.music.platform.featuregate.Feature {
    sonic_rush { // from class: com.amazon.music.media.playback.featuregate.Feature.sonic_rush
        @Override // com.amazon.music.media.playback.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("sonic_rush");
        }
    },
    entity_skippable_check { // from class: com.amazon.music.media.playback.featuregate.Feature.entity_skippable_check
        @Override // com.amazon.music.media.playback.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("entity_skippable_check");
        }
    },
    start_foreground_on_update_notification { // from class: com.amazon.music.media.playback.featuregate.Feature.start_foreground_on_update_notification
        @Override // com.amazon.music.media.playback.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("start_foreground_on_update_notification");
        }
    };

    private static final String TAG = Feature.class.getSimpleName();

    /* synthetic */ Feature(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // com.amazon.music.platform.featuregate.Feature
    public abstract /* synthetic */ Rule getRule();

    public final boolean isEnabled() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(FeatureGateProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof FeatureGateProvider)) {
            interfaceProvider = null;
        }
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) interfaceProvider;
        if (featureGateProvider == null) {
            Log.e(TAG, Intrinsics.stringPlus("Error fetching feature gate provider got null ", name()));
            return false;
        }
        return isEnabled(featureGateProvider);
    }

    public final boolean isEnabled(FeatureGateProvider featureGateProvider) {
        Intrinsics.checkNotNullParameter(featureGateProvider, "featureGateProvider");
        return featureGateProvider.isFeatureAvailable(this);
    }
}
