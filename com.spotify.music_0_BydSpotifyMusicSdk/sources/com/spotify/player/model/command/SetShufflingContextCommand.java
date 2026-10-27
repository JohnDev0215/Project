package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.command.AutoValue_SetShufflingContextCommand;

/* JADX INFO: loaded from: classes.dex */
public abstract class SetShufflingContextCommand implements Command {

    public static abstract class Builder {
    }

    public static SetShufflingContextCommand a(boolean z) {
        AutoValue_SetShufflingContextCommand.Builder builder = new AutoValue_SetShufflingContextCommand.Builder();
        Boolean boolValueOf = Boolean.valueOf(z);
        builder.a = boolValueOf;
        String str = boolValueOf == null ? " value" : "";
        if (str.isEmpty()) {
            return new AutoValue_SetShufflingContextCommand(builder.a.booleanValue(), builder.b, builder.c);
        }
        throw new IllegalStateException("Missing required properties:".concat(str));
    }

    public abstract Optional b();

    public abstract Optional c();

    public abstract boolean d();
}
