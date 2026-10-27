package com.amazon.music.alps.gating;

import android.util.Log;
import com.amazon.music.featuregating.rules.BooleanConfigurationRule;
import com.amazon.music.featuregating.rules.WeblabRule;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.featuregate.Feature;
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

/* JADX INFO: compiled from: AlpsFeatureGating.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u000eB\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/alps/gating/AlpsFeatureGating;", "", "Lcom/amazon/music/platform/featuregate/Feature;", "(Ljava/lang/String;I)V", Constants.ENABLE_DISABLE, "", "provider", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "LIVE_EVENT_SEARCH_TOP_RESULTS", "LIVE_EVENT_TOP_ONE_RECOMMENDER", "LIVE_EVENT_DETAIL", "LIVE_REFRESH", "MERCH_ON_BROWSE_HOME", "IMPROVED_EDP", "Companion", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum AlpsFeatureGating implements Feature {
    LIVE_EVENT_SEARCH_TOP_RESULTS { // from class: com.amazon.music.alps.gating.AlpsFeatureGating.LIVE_EVENT_SEARCH_TOP_RESULTS
        @Override // com.amazon.music.alps.gating.AlpsFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_ALPS_SEARCH_TOP_RESULTS_LIVEEVENT_ANDROID_569064", WeblabTreatment.T1);
        }
    },
    LIVE_EVENT_TOP_ONE_RECOMMENDER { // from class: com.amazon.music.alps.gating.AlpsFeatureGating.LIVE_EVENT_TOP_ONE_RECOMMENDER
        @Override // com.amazon.music.alps.gating.AlpsFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_ALPS_LIVE_BH_MOBILE_534879", WeblabTreatment.T1);
        }
    },
    LIVE_EVENT_DETAIL { // from class: com.amazon.music.alps.gating.AlpsFeatureGating.LIVE_EVENT_DETAIL
        @Override // com.amazon.music.alps.gating.AlpsFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_ALPS_FESTIVAL_MOBILE_476376", WeblabTreatment.T1);
        }
    },
    LIVE_REFRESH { // from class: com.amazon.music.alps.gating.AlpsFeatureGating.LIVE_REFRESH
        @Override // com.amazon.music.alps.gating.AlpsFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_ALPS_LIVE_UPCOMING_723023", WeblabTreatment.T1);
        }
    },
    MERCH_ON_BROWSE_HOME { // from class: com.amazon.music.alps.gating.AlpsFeatureGating.MERCH_ON_BROWSE_HOME
        @Override // com.amazon.music.alps.gating.AlpsFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("is_merch_on_browse_home_enabled");
        }
    },
    IMPROVED_EDP { // from class: com.amazon.music.alps.gating.AlpsFeatureGating.IMPROVED_EDP
        @Override // com.amazon.music.alps.gating.AlpsFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_ALPS_IMPROVE_EDP_ANDROID_611641", WeblabTreatment.T1);
        }
    };

    private static final String TAG = Feature.class.getSimpleName();

    /* synthetic */ AlpsFeatureGating(DefaultConstructorMarker defaultConstructorMarker) {
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
            Log.e(TAG, Intrinsics.stringPlus("Error fetching feature gate provider got null: ", name()));
            return false;
        }
        return isEnabled(featureGateProvider);
    }

    public final boolean isEnabled(FeatureGateProvider provider) {
        if (provider == null) {
            return false;
        }
        return provider.isFeatureAvailable(this);
    }
}
