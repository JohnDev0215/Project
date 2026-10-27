package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.PauseResumeOrigin;
import com.spotify.player.model.command.PauseCommand;
import com.spotify.player.model.command.options.CommandOptions;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_PauseCommand extends PauseCommand {
    public final Optional a;
    public final Optional b;
    public final Optional c;

    public static final class Builder extends PauseCommand.Builder {
        public Optional a = Optional.a();
        public final Optional b = Optional.a();
        public Optional c = Optional.a();

        @Override // com.spotify.player.model.command.PauseCommand.Builder
        public final PauseCommand a() {
            return new AutoValue_PauseCommand(this.a, this.b, this.c);
        }

        @Override // com.spotify.player.model.command.PauseCommand.Builder
        public final PauseCommand.Builder b(CommandOptions commandOptions) {
            this.a = Optional.e(commandOptions);
            return this;
        }

        @Override // com.spotify.player.model.command.PauseCommand.Builder
        public final PauseCommand.Builder c(PauseResumeOrigin pauseResumeOrigin) {
            this.c = Optional.e(pauseResumeOrigin);
            return this;
        }
    }

    public AutoValue_PauseCommand(Optional optional, Optional optional2, Optional optional3) {
        this.a = optional;
        this.b = optional2;
        this.c = optional3;
    }

    @Override // com.spotify.player.model.command.PauseCommand
    public final Optional b() {
        return this.b;
    }

    @Override // com.spotify.player.model.command.PauseCommand
    public final Optional c() {
        return this.a;
    }

    @Override // com.spotify.player.model.command.PauseCommand
    public final Optional d() {
        return this.c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PauseCommand)) {
            return false;
        }
        PauseCommand pauseCommand = (PauseCommand) obj;
        return this.a.equals(pauseCommand.c()) && this.b.equals(pauseCommand.b()) && this.c.equals(pauseCommand.d());
    }

    public final int hashCode() {
        return ((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    public final String toString() {
        return "PauseCommand{options=" + this.a + ", loggingParams=" + this.b + ", pauseOrigin=" + this.c + "}";
    }
}
