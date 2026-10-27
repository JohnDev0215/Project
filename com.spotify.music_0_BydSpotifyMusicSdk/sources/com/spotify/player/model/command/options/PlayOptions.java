package com.spotify.player.model.command.options;

import com.google.common.base.Optional;
import com.spotify.player.model.command.options.AutoValue_PlayOptions;

/* JADX INFO: loaded from: classes.dex */
public abstract class PlayOptions {

    public static abstract class Builder {
        public abstract PlayOptions a();
    }

    static {
        a().a();
    }

    public static Builder a() {
        AutoValue_PlayOptions.Builder builder = new AutoValue_PlayOptions.Builder();
        CommandOptions commandOptions = CommandOptions.a;
        if (commandOptions == null) {
            throw new NullPointerException("Null commandOptions");
        }
        builder.c = commandOptions;
        return builder;
    }

    public abstract CommandOptions b();

    public abstract Optional c();

    public abstract Optional d();
}
