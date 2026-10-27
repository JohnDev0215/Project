package com.spotify.player.model;

import com.google.common.base.Optional;
import com.spotify.player.model.AutoValue_PlayerOptions;

/* JADX INFO: loaded from: classes.dex */
public abstract class PlayerOptions {
    public static final PlayerOptions a = a().a();

    public static abstract class Builder {
        public abstract PlayerOptions a();

        public abstract Builder b(float f);

        public abstract Builder c(boolean z);

        public abstract Builder d(boolean z);

        public abstract Builder e(boolean z);
    }

    public static Builder a() {
        AutoValue_PlayerOptions.Builder builder = new AutoValue_PlayerOptions.Builder();
        Boolean bool = Boolean.FALSE;
        builder.a = bool;
        builder.b = bool;
        builder.c = bool;
        return builder;
    }

    public abstract Optional b();

    public abstract boolean c();

    public abstract boolean d();

    public abstract boolean e();
}
