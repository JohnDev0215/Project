package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.ContextTrack;
import com.spotify.player.model.command.AutoValue_SkipToNextTrackCommand;

/* JADX INFO: loaded from: classes.dex */
public abstract class SkipToNextTrackCommand implements Command {

    public static abstract class Builder {
        public abstract SkipToNextTrackCommand a();

        public abstract Builder b(ContextTrack contextTrack);
    }

    public static Builder a() {
        return new AutoValue_SkipToNextTrackCommand.Builder();
    }

    public static SkipToNextTrackCommand b() {
        return new AutoValue_SkipToNextTrackCommand.Builder().a();
    }

    public abstract Optional c();

    public abstract Optional d();

    public abstract Optional e();
}
