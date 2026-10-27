package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.command.AutoValue_SkipToPrevTrackCommand;
import com.spotify.player.model.command.options.SkipToPrevTrackOptions;

/* JADX INFO: loaded from: classes.dex */
public abstract class SkipToPrevTrackCommand implements Command {

    public static abstract class Builder {
        public abstract SkipToPrevTrackCommand a();

        public abstract Builder b(SkipToPrevTrackOptions skipToPrevTrackOptions);
    }

    public static Builder a() {
        return new AutoValue_SkipToPrevTrackCommand.Builder();
    }

    public static SkipToPrevTrackCommand b() {
        return new AutoValue_SkipToPrevTrackCommand.Builder().a();
    }

    public abstract Optional c();

    public abstract Optional d();

    public abstract Optional e();
}
