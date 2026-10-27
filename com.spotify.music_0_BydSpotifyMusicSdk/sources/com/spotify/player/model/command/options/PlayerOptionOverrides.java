package com.spotify.player.model.command.options;

import com.google.common.base.Optional;
import com.spotify.player.model.command.options.AutoValue_PlayerOptionOverrides;

/* JADX INFO: loaded from: classes.dex */
public abstract class PlayerOptionOverrides {
    public static final PlayerOptionOverrides a = new AutoValue_PlayerOptionOverrides.Builder().a();

    public static abstract class Builder {
        public abstract PlayerOptionOverrides a();

        public abstract Builder b(Boolean bool);

        public abstract Builder c(Boolean bool);

        public abstract Builder d(Boolean bool);
    }

    public static Builder a() {
        return new AutoValue_PlayerOptionOverrides.Builder();
    }

    public abstract Optional b();

    public abstract Optional c();

    public abstract Optional d();
}
