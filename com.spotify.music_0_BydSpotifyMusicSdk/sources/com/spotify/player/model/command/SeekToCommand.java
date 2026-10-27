package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.command.AutoValue_SeekToCommand;

/* JADX INFO: loaded from: classes.dex */
public abstract class SeekToCommand implements Command {

    public static abstract class Builder {
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class Relative {
        public static final /* synthetic */ Relative[] c = {new Relative("BEGINNING", 0), new Relative("END", 1), new Relative("CURRENT", 2)};

        /* JADX INFO: Fake field, exist only in values array */
        Relative EF5;

        public static Relative valueOf(String str) {
            return (Relative) Enum.valueOf(Relative.class, str);
        }

        public static Relative[] values() {
            return (Relative[]) c.clone();
        }
    }

    public static SeekToCommand a(long j) {
        AutoValue_SeekToCommand.Builder builder = new AutoValue_SeekToCommand.Builder();
        Long lValueOf = Long.valueOf(j);
        builder.a = lValueOf;
        String str = lValueOf == null ? " value" : "";
        if (str.isEmpty()) {
            return new AutoValue_SeekToCommand(builder.a.longValue(), builder.b, builder.c, builder.d);
        }
        throw new IllegalStateException("Missing required properties:".concat(str));
    }

    public abstract Optional b();

    public abstract Optional c();

    public abstract Optional d();

    public abstract long e();
}
