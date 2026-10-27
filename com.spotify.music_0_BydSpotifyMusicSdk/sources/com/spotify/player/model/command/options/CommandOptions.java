package com.spotify.player.model.command.options;

import com.google.common.base.Optional;
import com.spotify.player.model.command.options.AutoValue_CommandOptions;

/* JADX INFO: loaded from: classes.dex */
public abstract class CommandOptions {
    public static final CommandOptions a = new AutoValue_CommandOptions.Builder().a();

    public static abstract class Builder {
        public abstract CommandOptions a();

        public abstract Builder b(boolean z);
    }

    public static Builder a() {
        return new AutoValue_CommandOptions.Builder();
    }

    public abstract Optional b();

    public abstract Optional c();

    public abstract Optional d();

    public abstract Optional e();
}
