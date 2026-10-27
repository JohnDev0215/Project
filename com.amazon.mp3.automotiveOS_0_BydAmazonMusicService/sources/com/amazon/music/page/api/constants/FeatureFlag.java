package com.amazon.music.page.api.constants;

/* JADX INFO: loaded from: classes4.dex */
public enum FeatureFlag {
    INCLUDE_VIDEO("includeVideo"),
    INCLUDE_LIVE_STREAM("includeLiveStream"),
    INCLUDE_PODCAST("includePodcast"),
    INCLUDE_SPATIAL_AUDIO("includeSpatialAudio"),
    INCLUDE_PODCAST_CONTENT("includePodcastContent"),
    USE_BRUSH_V3_CATEGORY_PAGES("useBrushV3CategoryPages"),
    USE_CAR_MODE_VISUAL_SHOVELER("useCarModeVisualShoveler"),
    INCLUDE_VIDEO_STORY("includeVideoStory");

    String feature;

    FeatureFlag(String str) {
        this.feature = str;
    }

    public String getFeature() {
        return this.feature;
    }
}
