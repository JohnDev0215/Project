package com.amazon.music.inappmessaging.internal.util;

import com.amazon.music.featuregating.rules.BooleanConfigurationRule;
import com.amazon.music.featuregating.rules.WeblabRule;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.featuregate.rules.AndRule;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SplashUpsellV2SupportedFeature.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/inappmessaging/internal/util/SplashUpsellV2SupportedFeature;", "", "Lcom/amazon/music/platform/featuregate/Feature;", "(Ljava/lang/String;I)V", "splash_support_on_dynamic_messaging_v2", "DMMNotificationUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum SplashUpsellV2SupportedFeature implements Feature {
    splash_support_on_dynamic_messaging_v2 { // from class: com.amazon.music.inappmessaging.internal.util.SplashUpsellV2SupportedFeature.splash_support_on_dynamic_messaging_v2
        @Override // com.amazon.music.inappmessaging.internal.util.SplashUpsellV2SupportedFeature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new WeblabRule("DM_CCMP_ANDROID_DYNAMIC_MESSAGE_SPLASH_V2_745433", WeblabTreatment.T1), new BooleanConfigurationRule("is_dynamic_messaging_splash_v2_enabled")}), false, 2, null);
        }
    };

    /* synthetic */ SplashUpsellV2SupportedFeature(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // com.amazon.music.platform.featuregate.Feature
    public abstract /* synthetic */ Rule getRule();
}
