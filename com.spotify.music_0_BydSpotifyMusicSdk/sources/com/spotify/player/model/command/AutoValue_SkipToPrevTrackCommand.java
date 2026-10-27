package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.command.SkipToPrevTrackCommand;
import com.spotify.player.model.command.options.SkipToPrevTrackOptions;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_SkipToPrevTrackCommand extends SkipToPrevTrackCommand {
    public final Optional a;
    public final Optional b;
    public final Optional c;

    public static final class Builder extends SkipToPrevTrackCommand.Builder {
        public final Optional a = Optional.a();
        public Optional b = Optional.a();
        public final Optional c = Optional.a();

        @Override // com.spotify.player.model.command.SkipToPrevTrackCommand.Builder
        public final SkipToPrevTrackCommand a() {
            return new AutoValue_SkipToPrevTrackCommand(this.a, this.b, this.c);
        }

        @Override // com.spotify.player.model.command.SkipToPrevTrackCommand.Builder
        public final SkipToPrevTrackCommand.Builder b(SkipToPrevTrackOptions skipToPrevTrackOptions) {
            this.b = Optional.e(skipToPrevTrackOptions);
            return this;
        }
    }

    public AutoValue_SkipToPrevTrackCommand(Optional optional, Optional optional2, Optional optional3) {
        this.a = optional;
        this.b = optional2;
        this.c = optional3;
    }

    @Override // com.spotify.player.model.command.SkipToPrevTrackCommand
    public final Optional c() {
        return this.c;
    }

    @Override // com.spotify.player.model.command.SkipToPrevTrackCommand
    public final Optional d() {
        return this.b;
    }

    @Override // com.spotify.player.model.command.SkipToPrevTrackCommand
    public final Optional e() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SkipToPrevTrackCommand)) {
            return false;
        }
        SkipToPrevTrackCommand skipToPrevTrackCommand = (SkipToPrevTrackCommand) obj;
        return this.a.equals(skipToPrevTrackCommand.e()) && this.b.equals(skipToPrevTrackCommand.d()) && this.c.equals(skipToPrevTrackCommand.c());
    }

    public final int hashCode() {
        return ((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    public final String toString() {
        return "SkipToPrevTrackCommand{track=" + this.a + ", options=" + this.b + ", loggingParams=" + this.c + "}";
    }
}
