package com.spotify.player.model.command.options;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.spotify.player.model.AudioStream;
import com.spotify.player.model.Suppressions;
import com.spotify.player.model.command.options.AutoValue_PreparePlayOptions;

/* JADX INFO: loaded from: classes.dex */
public abstract class PreparePlayOptions {
    public static final PreparePlayOptions a = c().c();

    public static abstract class Builder {
        public abstract Builder a(boolean z);

        public abstract Builder b(AudioStream audioStream);

        public abstract PreparePlayOptions c();

        public abstract Builder d(ImmutableMap immutableMap);

        public abstract Builder e(boolean z);

        public abstract Builder f(String str);

        public abstract Builder g(String str);

        public abstract Builder h(PlayerOptionOverrides playerOptionOverrides);

        public abstract Builder i(PrefetchLevel prefetchLevel);

        public abstract Builder j(Long l);

        public abstract Builder k(String str);

        public abstract Builder l(SkipToTrack skipToTrack);

        public abstract Builder m(Suppressions suppressions);

        public abstract Builder n(boolean z);
    }

    public static Builder c() {
        AutoValue_PreparePlayOptions.Builder builder = new AutoValue_PreparePlayOptions.Builder();
        builder.d(ImmutableMap.d());
        return builder;
    }

    public abstract Optional<Boolean> a();

    public abstract Optional<AudioStream> b();

    public abstract ImmutableMap<String, String> d();

    public abstract Optional<Boolean> e();

    public abstract Optional<String> f();

    public abstract Optional<String> g();

    public abstract Optional<PlayerOptionOverrides> h();

    public abstract Optional<PrefetchLevel> i();

    public abstract Optional<Long> j();

    public abstract Optional<String> k();

    public abstract Optional<SkipToTrack> l();

    public abstract Optional<Suppressions> m();

    public abstract Optional<Boolean> n();

    public abstract Builder o();
}
