package com.spotify.player.model.command.options;

import com.google.common.base.Optional;
import com.spotify.player.model.command.options.AutoValue_SkipToTrack;

/* JADX INFO: loaded from: classes.dex */
public abstract class SkipToTrack {
    public static final SkipToTrack a = new AutoValue_SkipToTrack.Builder().a();

    public static abstract class Builder {
        public abstract SkipToTrack a();

        public abstract Builder b(Long l);

        public abstract Builder c(String str);

        public abstract Builder d(Long l);

        public abstract Builder e(String str);

        public abstract Builder f(String str);
    }

    public static Builder a() {
        return new AutoValue_SkipToTrack.Builder();
    }

    public static SkipToTrack b(String str) {
        AutoValue_SkipToTrack.Builder builder = new AutoValue_SkipToTrack.Builder();
        builder.d = Optional.e(str);
        return builder.a();
    }

    public abstract Optional c();

    public abstract Optional d();

    public abstract Optional e();

    public abstract Optional f();

    public abstract Optional g();
}
