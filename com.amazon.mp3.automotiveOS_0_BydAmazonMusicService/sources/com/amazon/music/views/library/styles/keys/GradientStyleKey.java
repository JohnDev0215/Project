package com.amazon.music.views.library.styles.keys;

import kotlin.Metadata;

/* JADX INFO: compiled from: GradientStyleKey.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/views/library/styles/keys/GradientStyleKey;", "", "key", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "GRADIENT_1", "GRADIENT_2", "GRADIENT_3", "TEXT_TRUNCATION", "FEATURE_BARKER", "FEATURED_PLAY_BACKGROUND", "FEATURED_PLAY_BACKGROUND_TOP", "FEATURED_PLAY_BACKGROUND_BOTTOM", "FEATURED_PLAY_HERO", "FEATURED_PLAY_VIDEO_VERTICAL", "FEATURED_PLAY_HERO_TOP", "FEATURED_PLAY_HERO_BOTTOM", "FEATURED_PLAY_HERO_BACKGROUND", "FEATURED_PLAY_HERO_BACKGROUND_TOP", "FEATURED_PLAY_HERO_BACKGROUND_BOTTOM", "TRANSPARENT_GRADIENT", "PLAYBACK_CONTAINER_GRADIENT", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum GradientStyleKey {
    GRADIENT_1("Gradient_1"),
    GRADIENT_2("Gradient_2"),
    GRADIENT_3("Gradient_3"),
    TEXT_TRUNCATION("Text_Truncation"),
    FEATURE_BARKER("Featured_Barker"),
    FEATURED_PLAY_BACKGROUND("Featured_Play_Background"),
    FEATURED_PLAY_BACKGROUND_TOP("Featured_Play_Background_Top"),
    FEATURED_PLAY_BACKGROUND_BOTTOM("Featured_Play_Background_Bottom"),
    FEATURED_PLAY_HERO("Featured_Play_Hero"),
    FEATURED_PLAY_VIDEO_VERTICAL("Feature_Play_Video_Vertical_Gradient"),
    FEATURED_PLAY_HERO_TOP("Featured_Play_Hero_Top"),
    FEATURED_PLAY_HERO_BOTTOM("Featured_Play_Hero_Bottom"),
    FEATURED_PLAY_HERO_BACKGROUND("Featured_Play_Hero_Background"),
    FEATURED_PLAY_HERO_BACKGROUND_TOP("Featured_Play_Hero_Background_Top"),
    FEATURED_PLAY_HERO_BACKGROUND_BOTTOM("Featured_Play_Hero_Background_Bottom"),
    TRANSPARENT_GRADIENT("Transparent_Gradient"),
    PLAYBACK_CONTAINER_GRADIENT("Playback_Container_Gradient");

    private final String key;

    GradientStyleKey(String str) {
        this.key = str;
    }

    public final String getKey() {
        return this.key;
    }
}
