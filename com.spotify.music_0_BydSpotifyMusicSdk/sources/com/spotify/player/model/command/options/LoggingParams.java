package com.spotify.player.model.command.options;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.spotify.player.model.command.options.AutoValue_LoggingParams;

/* JADX INFO: loaded from: classes.dex */
public abstract class LoggingParams {
    public static final LoggingParams a = new AutoValue_LoggingParams.Builder().a();

    public static abstract class Builder {
        public Builder() {
            d(ImmutableSet.q());
            c(ImmutableSet.q());
        }

        public abstract LoggingParams a();

        public abstract Builder b(Long l);

        public abstract Builder c(ImmutableSet immutableSet);

        public abstract Builder d(ImmutableSet immutableSet);
    }

    public static Builder a() {
        return new AutoValue_LoggingParams.Builder();
    }

    public abstract Optional b();

    public abstract Optional c();

    public abstract ImmutableSet d();

    public abstract ImmutableSet e();

    public abstract Builder f();
}
