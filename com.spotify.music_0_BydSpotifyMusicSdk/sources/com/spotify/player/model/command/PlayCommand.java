package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.Context;
import com.spotify.player.model.PlayOrigin;
import com.spotify.player.model.command.AutoValue_PlayCommand;
import com.spotify.player.model.command.options.LoggingParams;
import com.spotify.player.model.command.options.PlayOptions;
import com.spotify.player.model.command.options.PreparePlayOptions;

/* JADX INFO: loaded from: classes.dex */
public abstract class PlayCommand implements Command {

    public static abstract class Builder {
        public abstract PlayCommand a();

        public abstract Builder b(LoggingParams loggingParams);

        public abstract Builder c(PreparePlayOptions preparePlayOptions);

        public abstract Builder d(PlayOptions playOptions);
    }

    public static Builder a(Context context, PlayOrigin playOrigin) {
        AutoValue_PlayCommand.Builder builder = new AutoValue_PlayCommand.Builder();
        builder.a = context;
        if (playOrigin == null) {
            throw new NullPointerException("Null playOrigin");
        }
        builder.b = playOrigin;
        return builder;
    }

    public abstract Context b();

    public abstract Optional c();

    public abstract Optional d();

    public abstract Optional e();

    public abstract PlayOrigin f();
}
