package com.amazon.mp3.featuregate.rules.config;

import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.featuregate.rules.specific.SubscriptionRule;
import com.amazon.mp3.featuregate.rules.specific.SubscriptionType;
import com.amazon.music.featureflag.FeatureFlagProvider;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.featuregate.rules.AndRule;
import com.amazon.music.platform.featuregate.rules.BooleanMethodRule;
import com.amazon.music.platform.featuregate.rules.FailableRule;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArtistTasteCollectionRuleConfiguration.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/featuregate/rules/config/ArtistTasteCollectionRuleConfiguration;", "", "featureFlagProvider", "Lcom/amazon/music/featureflag/FeatureFlagProvider;", "(Lcom/amazon/music/featureflag/FeatureFlagProvider;)V", "getRules", "Lcom/amazon/music/platform/featuregate/Rule;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ArtistTasteCollectionRuleConfiguration {
    private final FeatureFlagProvider featureFlagProvider;

    public ArtistTasteCollectionRuleConfiguration() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public ArtistTasteCollectionRuleConfiguration(FeatureFlagProvider featureFlagProvider) {
        Intrinsics.checkNotNullParameter(featureFlagProvider, "featureFlagProvider");
        this.featureFlagProvider = featureFlagProvider;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ArtistTasteCollectionRuleConfiguration(FeatureFlagProvider featureFlagProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            featureFlagProvider = FeatureFlagsProvider.getFeatureFlagProvider();
            Intrinsics.checkNotNullExpressionValue(featureFlagProvider, "getFeatureFlagProvider()");
        }
        this(featureFlagProvider);
    }

    public final Rule getRules() {
        return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new SubscriptionRule(CollectionsKt__CollectionsKt.listOf((Object[]) new SubscriptionType[]{SubscriptionType.AMU, SubscriptionType.PRIME, SubscriptionType.NIGHTWING_ONLY}), false, null, 6, null), new FailableRule(new BooleanMethodRule(new AnonymousClass1(this.featureFlagProvider), false, 2, null), false, false, 6, null)}), false, 2, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.featuregate.rules.config.ArtistTasteCollectionRuleConfiguration$getRules$1, reason: invalid class name */
    /* JADX INFO: compiled from: ArtistTasteCollectionRuleConfiguration.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0<Boolean> {
        AnonymousClass1(Object obj) {
            super(0, obj, FeatureFlagProvider.class, "isArtistTasteCollectionEnabled", "isArtistTasteCollectionEnabled()Z", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(((FeatureFlagProvider) this.receiver).isArtistTasteCollectionEnabled());
        }
    }
}
