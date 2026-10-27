package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.command.AutoValue_SetRepeatingContextCommand;

/* JADX INFO: loaded from: classes.dex */
public abstract class SetRepeatingContextCommand implements Command {

    public static abstract class Builder {
    }

    public static SetRepeatingContextCommand a(boolean z) {
        AutoValue_SetRepeatingContextCommand.Builder builder = new AutoValue_SetRepeatingContextCommand.Builder();
        Boolean boolValueOf = Boolean.valueOf(z);
        builder.a = boolValueOf;
        String str = boolValueOf == null ? " value" : "";
        if (str.isEmpty()) {
            return new AutoValue_SetRepeatingContextCommand(builder.a.booleanValue(), builder.b, builder.c);
        }
        throw new IllegalStateException("Missing required properties:".concat(str));
    }

    public abstract Optional b();

    public abstract Optional c();

    public abstract boolean d();
}
