package com.spotify.localfiles.mediastore;

/* JADX INFO: loaded from: classes.dex */
public final class MediaStoreReaderOptions {
    private final int durationMin;
    private final boolean includeAlarms;
    private final boolean includeAudiobooks;
    private final boolean includeMusic;
    private final boolean includeNotifications;
    private final boolean includePodcasts;
    private final boolean includeRingtones;

    public MediaStoreReaderOptions(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, int i) {
        this.includeAlarms = z;
        this.includeAudiobooks = z2;
        this.includeMusic = z3;
        this.includeNotifications = z4;
        this.includePodcasts = z5;
        this.includeRingtones = z6;
        this.durationMin = i;
    }

    public final int getDurationMin() {
        return this.durationMin;
    }

    public final boolean getIncludeAlarms() {
        return this.includeAlarms;
    }

    public final boolean getIncludeAudiobooks() {
        return this.includeAudiobooks;
    }

    public final boolean getIncludeMusic() {
        return this.includeMusic;
    }

    public final boolean getIncludeNotifications() {
        return this.includeNotifications;
    }

    public final boolean getIncludePodcasts() {
        return this.includePodcasts;
    }

    public final boolean getIncludeRingtones() {
        return this.includeRingtones;
    }
}
