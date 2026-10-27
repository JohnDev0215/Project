package com.amazon.music.share.featuregaing;

import android.util.Log;
import com.amazon.music.featuregating.rules.BooleanConfigurationRule;
import com.amazon.music.featuregating.rules.WeblabRule;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.featuregate.rules.AndRule;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: ShareFeatureGating.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\fB\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, d2 = {"Lcom/amazon/music/share/featuregaing/ShareFeatureGating;", "", "Lcom/amazon/music/platform/featuregate/Feature;", "(Ljava/lang/String;I)V", Constants.ENABLE_DISABLE, "", "provider", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "IS_CUSTOM_SHARE_MENU_ENABLED", "IS_CUSTOM_SHARE_SHEET_V2_ENABLED", "SHARING_TO_SNAPCHAT", "PROFILE_SHARING", "Companion", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum ShareFeatureGating implements Feature {
    IS_CUSTOM_SHARE_MENU_ENABLED { // from class: com.amazon.music.share.featuregaing.ShareFeatureGating.IS_CUSTOM_SHARE_MENU_ENABLED
        @Override // com.amazon.music.share.featuregaing.ShareFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanConfigurationRule("is_custom_share_menu_enabled"), new WeblabRule("DM_CUSTOM_ANDROID_SHARE_MENU_671833", WeblabTreatment.T1)), false, 2, null);
        }
    },
    IS_CUSTOM_SHARE_SHEET_V2_ENABLED { // from class: com.amazon.music.share.featuregaing.ShareFeatureGating.IS_CUSTOM_SHARE_SHEET_V2_ENABLED
        @Override // com.amazon.music.share.featuregaing.ShareFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanConfigurationRule("shareSheetV2"), new WeblabRule("DM_SHARE_SHEET_V2_696249", WeblabTreatment.T1)), false, 2, null);
        }
    },
    SHARING_TO_SNAPCHAT { // from class: com.amazon.music.share.featuregaing.ShareFeatureGating.SHARING_TO_SNAPCHAT
        @Override // com.amazon.music.share.featuregaing.ShareFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanConfigurationRule("share_content_to_snapchat"), new WeblabRule("DM_SHARE_TO_SNAPCHAT_STORIES_707984", WeblabTreatment.T1)), false, 2, null);
        }
    },
    PROFILE_SHARING { // from class: com.amazon.music.share.featuregaing.ShareFeatureGating.PROFILE_SHARING
        @Override // com.amazon.music.share.featuregaing.ShareFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_PROFILE_SHARING_ANDROID_718807", WeblabTreatment.T1);
        }
    };

    private static final String TAG = Feature.class.getSimpleName();

    /* synthetic */ ShareFeatureGating(DefaultConstructorMarker defaultConstructorMarker) {
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
