package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.ContextTrack;
import com.spotify.player.model.command.SkipToNextTrackCommand;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_SkipToNextTrackCommand extends SkipToNextTrackCommand {
    public final Optional a;
    public final Optional b;
    public final Optional c;

    public static final class Builder extends SkipToNextTrackCommand.Builder {
        public Optional a = Optional.a();
        public final Optional b = Optional.a();
        public final Optional c = Optional.a();

        @Override // com.spotify.player.model.command.SkipToNextTrackCommand.Builder
        public final SkipToNextTrackCommand a() {
            return new AutoValue_SkipToNextTrackCommand(this.a, this.b, this.c);
        }

        @Override // com.spotify.player.model.command.SkipToNextTrackCommand.Builder
        public final SkipToNextTrackCommand.Builder b(ContextTrack contextTrack) {
            this.a = Optional.e(contextTrack);
            return this;
        }
    }

    public AutoValue_SkipToNextTrackCommand(Optional optional, Optional optional2, Optional optional3) {
        this.a = optional;
        this.b = optional2;
        this.c = optional3;
    }

    @Override // com.spotify.player.model.command.SkipToNextTrackCommand
    public final Optional c() {
        return this.c;
    }

    @Override // com.spotify.player.model.command.SkipToNextTrackCommand
    public final Optional d() {
        return this.b;
    }

    @Override // com.spotify.player.model.command.SkipToNextTrackCommand
    public final Optional e() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SkipToNextTrackCommand)) {
            return false;
        }
        SkipToNextTrackCommand skipToNextTrackCommand = (SkipToNextTrackCommand) obj;
        return this.a.equals(skipToNextTrackCommand.e()) && this.b.equals(skipToNextTrackCommand.d()) && this.c.equals(skipToNextTrackCommand.c());
    }

    public final int hashCode() {
        return ((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    public final String toString() {
        return "SkipToNextTrackCommand{track=" + this.a + ", options=" + this.b + ", loggingParams=" + this.c + "}";
    }
}
