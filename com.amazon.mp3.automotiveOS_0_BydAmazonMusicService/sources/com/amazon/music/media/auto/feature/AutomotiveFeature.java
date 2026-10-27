package com.amazon.music.media.auto.feature;

import com.amazon.music.featuregating.rules.WeblabRule;
import com.amazon.music.media.auto.feature.weblab.AutomotiveWeblab;
import com.amazon.music.media.auto.util.MediaBrowserMetricsUtil;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.featuregate.rules.AndRule;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AutomotiveFeature.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/media/auto/feature/AutomotiveFeature;", "", "Lcom/amazon/music/platform/featuregate/Feature;", "(Ljava/lang/String;I)V", Constants.ENABLE_DISABLE, "", "provider", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "GOOGLE_ASSISTANT_VOICE", "SONIC_RUSH", "MESK_BASED_HOME", "MEDIA_BROWSER_LIBRARY_TAB", "MY_PRESETS", "MEDIA_BROWSER_CONNECTIVITY_RELOAD", "MY_PRESETS_WITH_PODCAST", "MEDIA_BROWSER_TEXT_SEARCH", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum AutomotiveFeature implements Feature {
    GOOGLE_ASSISTANT_VOICE { // from class: com.amazon.music.media.auto.feature.AutomotiveFeature.GOOGLE_ASSISTANT_VOICE
        @Override // com.amazon.music.media.auto.feature.AutomotiveFeature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new GoogleAssistantVoiceIntegrationFeature().getRule();
        }
    },
    SONIC_RUSH { // from class: com.amazon.music.media.auto.feature.AutomotiveFeature.SONIC_RUSH
        @Override // com.amazon.music.media.auto.feature.AutomotiveFeature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule(AutomotiveWeblab.AMAZON_MUSIC_ANDROID_AUTOMOTIVE_SONIC_RUSH_502000.getId(), WeblabTreatment.T1);
        }
    },
    MESK_BASED_HOME { // from class: com.amazon.music.media.auto.feature.AutomotiveFeature.MESK_BASED_HOME
        @Override // com.amazon.music.media.auto.feature.AutomotiveFeature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule(AutomotiveWeblab.AMAZON_MUSIC_ANDROID_AUTOMOTIVE_MESK_HOME_TAB_627180.getId(), WeblabTreatment.T1);
        }
    },
    MEDIA_BROWSER_LIBRARY_TAB { // from class: com.amazon.music.media.auto.feature.AutomotiveFeature.MEDIA_BROWSER_LIBRARY_TAB
        @Override // com.amazon.music.media.auto.feature.AutomotiveFeature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new MediaBrowserLibraryTabFeature().getRule();
        }
    },
    MY_PRESETS { // from class: com.amazon.music.media.auto.feature.AutomotiveFeature.MY_PRESETS
        @Override // com.amazon.music.media.auto.feature.AutomotiveFeature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new WeblabRule(AutomotiveWeblab.AMAZON_MUSIC_ANDROID_AUTOMOTIVE_MY_PRESETS_676664.getId(), WeblabTreatment.T1), new MyPresetsFeature().getRule()}), false, 2, null);
        }
    },
    MEDIA_BROWSER_CONNECTIVITY_RELOAD { // from class: com.amazon.music.media.auto.feature.AutomotiveFeature.MEDIA_BROWSER_CONNECTIVITY_RELOAD
        @Override // com.amazon.music.media.auto.feature.AutomotiveFeature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new ConnectivityReloadFeature().getRule();
        }
    },
    MY_PRESETS_WITH_PODCAST { // from class: com.amazon.music.media.auto.feature.AutomotiveFeature.MY_PRESETS_WITH_PODCAST
        @Override // com.amazon.music.media.auto.feature.AutomotiveFeature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule(AutomotiveWeblab.AMAZON_MUSIC_ANDROID_AUTOMOTIVE_MY_PRESETS_WITH_PODCAST_688976.getId(), WeblabTreatment.T1);
        }
    },
    MEDIA_BROWSER_TEXT_SEARCH { // from class: com.amazon.music.media.auto.feature.AutomotiveFeature.MEDIA_BROWSER_TEXT_SEARCH
        @Override // com.amazon.music.media.auto.feature.AutomotiveFeature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new MediaBrowserTextSearchFeature().getRule();
        }
    };

    /* synthetic */ AutomotiveFeature(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // com.amazon.music.platform.featuregate.Feature
    public abstract /* synthetic */ Rule getRule();

    public final boolean isEnabled(FeatureGateProvider provider) {
        if (provider == null) {
            return false;
        }
        try {
            return provider.isFeatureAvailable(this);
        } catch (Exception e) {
            MediaBrowserMetricsUtil.INSTANCE.sendEvent("autoMediaBrowserWeblabInitFailure", name(), e.getMessage());
            return false;
        }
    }
}
