package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.PauseResumeOrigin;
import com.spotify.player.model.command.AutoValue_PauseCommand;
import com.spotify.player.model.command.options.CommandOptions;

/* JADX INFO: loaded from: classes.dex */
public abstract class PauseCommand implements Command {

    public static abstract class Builder {
        public abstract PauseCommand a();

        public abstract Builder b(CommandOptions commandOptions);

        public abstract Builder c(PauseResumeOrigin pauseResumeOrigin);
    }

    public static Builder a() {
        return new AutoValue_PauseCommand.Builder();
    }

    public abstract Optional b();

    public abstract Optional c();

    public abstract Optional d();
}
