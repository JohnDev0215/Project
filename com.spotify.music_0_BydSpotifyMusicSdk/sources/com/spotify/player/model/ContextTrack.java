package com.spotify.player.model;

import com.google.common.collect.ImmutableMap;
import com.spotify.player.model.AutoValue_ContextTrack;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class ContextTrack {
    public static final List a = Arrays.asList("spotify:meta:delimiter", "spotify:delimiter");

    public static abstract class Builder {
        public abstract ContextTrack a();

        public abstract Builder b(Map map);

        public abstract Builder c(String str);

        public abstract Builder d(String str);

        public abstract Builder e(String str);
    }

    public static final class MediaType {
    }

    public static final class Metadata {
    }

    public static final class TrackAction {
    }

    public static final class TrackPlayer {
    }

    public static Builder a(String str) {
        AutoValue_ContextTrack.Builder builder = new AutoValue_ContextTrack.Builder();
        builder.e(str);
        builder.d("");
        builder.b(Collections.emptyMap());
        builder.c("");
        return builder;
    }

    public abstract ImmutableMap b();

    public abstract String c();

    public abstract Builder d();

    public abstract String e();

    public abstract String f();
}
