package com.amazon.music.media.auto.feature.weblab;

import kotlin.Metadata;

/* JADX INFO: compiled from: AutomotiveWeblab.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/media/auto/feature/weblab/AutomotiveWeblab;", "", "id", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getId", "()Ljava/lang/String;", "AMAZON_MUSIC_ANDROID_AUTOMOTIVE_SONIC_RUSH_502000", "AMAZON_MUSIC_ANDROID_AUTOMOTIVE_MESK_HOME_TAB_627180", "AMAZON_MUSIC_ANDROID_AUTOMOTIVE_MY_PRESETS_676664", "AMAZON_MUSIC_ANDROID_AUTOMOTIVE_MY_PRESETS_WITH_PODCAST_688976", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum AutomotiveWeblab {
    AMAZON_MUSIC_ANDROID_AUTOMOTIVE_SONIC_RUSH_502000("AMAZON_MUSIC_ANDROID_AUTOMOTIVE_SONIC_RUSH_502000"),
    AMAZON_MUSIC_ANDROID_AUTOMOTIVE_MESK_HOME_TAB_627180("AMAZON_MUSIC_ANDROID_AUTOMOTIVE_MESK_HOME_TAB_627180"),
    AMAZON_MUSIC_ANDROID_AUTOMOTIVE_MY_PRESETS_676664("AMAZON_MUSIC_ANDROID_AUTOMOTIVE_MY_PRESETS_676664"),
    AMAZON_MUSIC_ANDROID_AUTOMOTIVE_MY_PRESETS_WITH_PODCAST_688976("AMAZON_MUSIC_ANDROID_AUTOMOTIVE_MY_PRESETS_WITH_PODCAST_688976");

    private final String id;

    AutomotiveWeblab(String str) {
        this.id = str;
    }

    public final String getId() {
        return this.id;
    }
}
