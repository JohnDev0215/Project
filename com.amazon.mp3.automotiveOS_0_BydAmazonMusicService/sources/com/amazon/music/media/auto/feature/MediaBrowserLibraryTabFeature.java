package com.amazon.music.media.auto.feature;

import com.amazon.music.featuregating.rules.BooleanConfigurationRule;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.featuregate.Rule;
import kotlin.Metadata;

/* JADX INFO: compiled from: MediaBrowserLibraryTabFeature.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/media/auto/feature/MediaBrowserLibraryTabFeature;", "Lcom/amazon/music/platform/featuregate/Feature;", "()V", "getRule", "Lcom/amazon/music/platform/featuregate/Rule;", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MediaBrowserLibraryTabFeature implements Feature {
    @Override // com.amazon.music.platform.featuregate.Feature
    public Rule getRule() {
        return new BooleanConfigurationRule("media_browser_library_tab");
    }
}
