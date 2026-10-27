package com.spotify.audiomanager.audiomanagerproxy;

/* JADX INFO: loaded from: classes.dex */
public enum AudioManagerProxyUser {
    /* JADX INFO: Fake field, exist only in values array */
    EF0("android-system-audio", "android-audio-focus"),
    /* JADX INFO: Fake field, exist only in values array */
    EF1("android-system-audio-focus", "android-audio-focus-audiofocus"),
    c("android-system-audio-focus", "android-audio-focus-coreinternal"),
    /* JADX INFO: Fake field, exist only in values array */
    EF45("android-system-campaigns", "android-campaigns-audioplayerimpl"),
    /* JADX INFO: Fake field, exist only in values array */
    EF57("android-system-fullscreenstory", "android-fullscreenstory-fullscreenstoryimpl"),
    /* JADX INFO: Fake field, exist only in values array */
    EF69("android-system-music", "android-media-browser-service-mediabrowserservice"),
    /* JADX INFO: Fake field, exist only in values array */
    EF81("android-system-previewplayer", "android-previewplayer-api"),
    /* JADX INFO: Fake field, exist only in values array */
    EF93("android-system-preview", "android-preview-previewapi"),
    /* JADX INFO: Fake field, exist only in values array */
    EF106("android-system-share", "android-share-templates-entity"),
    /* JADX INFO: Fake field, exist only in values array */
    EF119("android-system-kids", "android-kids-playback-notification"),
    /* JADX INFO: Fake field, exist only in values array */
    EF130("test", "test");

    private final String identifier;
    private final String system;

    AudioManagerProxyUser(String str, String str2) {
        this.system = str;
        this.identifier = str2;
    }

    public final String a() {
        return this.identifier;
    }
}
