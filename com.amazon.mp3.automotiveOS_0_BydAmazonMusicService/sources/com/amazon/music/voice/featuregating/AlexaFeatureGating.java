package com.amazon.music.voice.featuregating;

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

/* JADX INFO: compiled from: AlexaFeatureGating.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/amazon/music/voice/featuregating/AlexaFeatureGating;", "", "Lcom/amazon/music/platform/featuregate/Feature;", "(Ljava/lang/String;I)V", Constants.ENABLE_DISABLE, "", "ALEXA", "WAKEWORD", "ALEXA_EMP_PLAYBACK", "AOM_TEXT_CAPABILITY", "HYPERLOOP_CAPABILITY", "AOM_HINDI_SUPPORT", "DM_AOM_ANDROID_CQE_LAUNCH", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum AlexaFeatureGating implements Feature {
    ALEXA { // from class: com.amazon.music.voice.featuregating.AlexaFeatureGating.ALEXA
        @Override // com.amazon.music.voice.featuregating.AlexaFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(FeatureFlag.ALEXA.getId());
        }
    },
    WAKEWORD { // from class: com.amazon.music.voice.featuregating.AlexaFeatureGating.WAKEWORD
        @Override // com.amazon.music.voice.featuregating.AlexaFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(FeatureFlag.WAKEWORD.getId());
        }
    },
    ALEXA_EMP_PLAYBACK { // from class: com.amazon.music.voice.featuregating.AlexaFeatureGating.ALEXA_EMP_PLAYBACK
        @Override // com.amazon.music.voice.featuregating.AlexaFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(FeatureFlag.ALEXA_EMP_PLAYBACK.getId());
        }
    },
    AOM_TEXT_CAPABILITY { // from class: com.amazon.music.voice.featuregating.AlexaFeatureGating.AOM_TEXT_CAPABILITY
        @Override // com.amazon.music.voice.featuregating.AlexaFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(FeatureFlag.AOM_TEXT_CAPABILITY.getId());
        }
    },
    HYPERLOOP_CAPABILITY { // from class: com.amazon.music.voice.featuregating.AlexaFeatureGating.HYPERLOOP_CAPABILITY
        @Override // com.amazon.music.voice.featuregating.AlexaFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(FeatureFlag.HYPERLOOP_CAPABILITY.getId());
        }
    },
    AOM_HINDI_SUPPORT { // from class: com.amazon.music.voice.featuregating.AlexaFeatureGating.AOM_HINDI_SUPPORT
        @Override // com.amazon.music.voice.featuregating.AlexaFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(FeatureFlag.AOM_HINDI_SUPPORT.getId());
        }
    },
    DM_AOM_ANDROID_CQE_LAUNCH { // from class: com.amazon.music.voice.featuregating.AlexaFeatureGating.DM_AOM_ANDROID_CQE_LAUNCH
        @Override // com.amazon.music.voice.featuregating.AlexaFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule(Weblab.DM_AOM_ANDROID_CQE_LAUNCH.getId(), WeblabTreatment.T1);
        }
    };

    /* synthetic */ AlexaFeatureGating(DefaultConstructorMarker defaultConstructorMarker) {
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
            return false;
        }
        return featureGateProvider.isFeatureAvailable(this);
    }
}
