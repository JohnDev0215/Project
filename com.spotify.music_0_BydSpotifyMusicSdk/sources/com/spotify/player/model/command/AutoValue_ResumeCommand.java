package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.PauseResumeOrigin;
import com.spotify.player.model.command.ResumeCommand;
import com.spotify.player.model.command.options.CommandOptions;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_ResumeCommand extends ResumeCommand {
    public final Optional a;
    public final Optional b;
    public final Optional c;

    public static final class Builder extends ResumeCommand.Builder {
        public Optional a = Optional.a();
        public final Optional b = Optional.a();
        public Optional c = Optional.a();

        @Override // com.spotify.player.model.command.ResumeCommand.Builder
        public final ResumeCommand a() {
            return new AutoValue_ResumeCommand(this.a, this.b, this.c);
        }

        @Override // com.spotify.player.model.command.ResumeCommand.Builder
        public final ResumeCommand.Builder b(CommandOptions commandOptions) {
            this.a = Optional.e(commandOptions);
            return this;
        }

        @Override // com.spotify.player.model.command.ResumeCommand.Builder
        public final ResumeCommand.Builder c(PauseResumeOrigin pauseResumeOrigin) {
            this.c = Optional.e(pauseResumeOrigin);
            return this;
        }
    }

    public AutoValue_ResumeCommand(Optional optional, Optional optional2, Optional optional3) {
        this.a = optional;
        this.b = optional2;
        this.c = optional3;
    }

    @Override // com.spotify.player.model.command.ResumeCommand
    public final Optional b() {
        return this.b;
    }

    @Override // com.spotify.player.model.command.ResumeCommand
    public final Optional c() {
        return this.a;
    }

    @Override // com.spotify.player.model.command.ResumeCommand
    public final Optional d() {
        return this.c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResumeCommand)) {
            return false;
        }
        ResumeCommand resumeCommand = (ResumeCommand) obj;
        return this.a.equals(resumeCommand.c()) && this.b.equals(resumeCommand.b()) && this.c.equals(resumeCommand.d());
    }

    public final int hashCode() {
        return ((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    public final String toString() {
        return "ResumeCommand{options=" + this.a + ", loggingParams=" + this.b + ", resumeOrigin=" + this.c + "}";
    }
}
