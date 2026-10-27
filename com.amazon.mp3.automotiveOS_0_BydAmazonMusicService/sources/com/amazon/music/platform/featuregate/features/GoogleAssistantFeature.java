package com.amazon.music.platform.featuregate.features;

import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.featuregate.rules.ConfigurationRule;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: GoogleAssistantFeature.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/platform/featuregate/features/GoogleAssistantFeature;", "", "Lcom/amazon/music/platform/featuregate/Feature;", "(Ljava/lang/String;I)V", "google_assistant_queue_based_playback", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum GoogleAssistantFeature implements Feature {
    google_assistant_queue_based_playback { // from class: com.amazon.music.platform.featuregate.features.GoogleAssistantFeature.google_assistant_queue_based_playback
        @Override // com.amazon.music.platform.featuregate.features.GoogleAssistantFeature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new ConfigurationRule("google_assistant_queue_based_playback");
        }
    };

    /* synthetic */ GoogleAssistantFeature(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // com.amazon.music.platform.featuregate.Feature
    public abstract /* synthetic */ Rule getRule();
}
