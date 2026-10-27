package com.spotify.player.model;

import com.spotify.player.model.AutoValue_PlaybackQuality;

/* JADX INFO: loaded from: classes.dex */
public abstract class PlaybackQuality {

    public static abstract class Builder {
        public abstract Builder a(BitrateLevel bitrateLevel);

        public abstract PlaybackQuality b();

        public abstract Builder c(HiFiStatus hiFiStatus);

        public abstract Builder d(BitrateStrategy bitrateStrategy);

        public abstract Builder e(boolean z);

        public abstract Builder f(BitrateLevel bitrateLevel);
    }

    static {
        b().b();
    }

    public static Builder b() {
        AutoValue_PlaybackQuality.Builder builder = new AutoValue_PlaybackQuality.Builder();
        BitrateLevel bitrateLevel = BitrateLevel.c;
        builder.a(bitrateLevel);
        builder.d(BitrateStrategy.c);
        builder.f(bitrateLevel);
        builder.e(false);
        builder.c(HiFiStatus.c);
        return builder;
    }

    public abstract BitrateLevel a();

    public abstract HiFiStatus c();

    public abstract BitrateStrategy d();

    public abstract boolean e();

    public abstract BitrateLevel f();
}
