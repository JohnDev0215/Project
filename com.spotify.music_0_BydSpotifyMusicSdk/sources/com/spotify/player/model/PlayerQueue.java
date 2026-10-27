package com.spotify.player.model;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.spotify.player.model.AutoValue_PlayerQueue;

/* JADX INFO: loaded from: classes.dex */
public abstract class PlayerQueue {

    public static abstract class Builder {
        public abstract PlayerQueue a();

        public abstract Builder b(ImmutableList immutableList);

        public abstract Builder c(ImmutableList immutableList);

        public abstract Builder d(String str);

        public abstract Builder e(ContextTrack contextTrack);
    }

    static {
        a().a();
    }

    public static Builder a() {
        AutoValue_PlayerQueue.Builder builder = new AutoValue_PlayerQueue.Builder();
        builder.d("");
        builder.b(ImmutableList.q());
        builder.c(ImmutableList.q());
        return builder;
    }

    public abstract ImmutableList b();

    public abstract ImmutableList c();

    public abstract String d();

    public abstract Builder e();

    public abstract Optional f();
}
