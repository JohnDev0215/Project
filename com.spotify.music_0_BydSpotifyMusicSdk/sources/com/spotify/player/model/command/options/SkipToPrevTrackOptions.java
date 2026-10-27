package com.spotify.player.model.command.options;

import com.google.common.base.Optional;
import com.spotify.player.model.command.options.AutoValue_SkipToPrevTrackOptions;

/* JADX INFO: loaded from: classes.dex */
public abstract class SkipToPrevTrackOptions {

    public static abstract class Builder {
        public abstract Builder a(Boolean bool);

        public abstract SkipToPrevTrackOptions b();
    }

    static {
        b().b();
    }

    public static Builder b() {
        AutoValue_SkipToPrevTrackOptions.Builder builder = new AutoValue_SkipToPrevTrackOptions.Builder();
        CommandOptions commandOptions = CommandOptions.a;
        if (commandOptions == null) {
            throw new NullPointerException("Null commandOptions");
        }
        builder.b = commandOptions;
        return builder;
    }

    public abstract Optional a();

    public abstract CommandOptions c();
}
