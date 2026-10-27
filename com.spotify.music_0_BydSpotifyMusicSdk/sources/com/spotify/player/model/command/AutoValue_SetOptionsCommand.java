package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.command.SetOptionsCommand;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_SetOptionsCommand extends SetOptionsCommand {
    public final Optional a;
    public final Optional b;
    public final Optional c;
    public final Optional d;
    public final Optional e;
    public final Optional f;

    public static final class Builder extends SetOptionsCommand.Builder {
        public Optional a = Optional.a();
        public Optional b = Optional.a();
        public final Optional c = Optional.a();
        public final Optional d = Optional.a();
        public final Optional e = Optional.a();
        public final Optional f = Optional.a();

        @Override // com.spotify.player.model.command.SetOptionsCommand.Builder
        public final SetOptionsCommand a() {
            return new AutoValue_SetOptionsCommand(this.a, this.b, this.c, this.d, this.e, this.f);
        }

        @Override // com.spotify.player.model.command.SetOptionsCommand.Builder
        public final SetOptionsCommand.Builder b(boolean z) {
            this.b = Optional.e(Boolean.valueOf(z));
            return this;
        }

        @Override // com.spotify.player.model.command.SetOptionsCommand.Builder
        public final SetOptionsCommand.Builder c(boolean z) {
            this.a = Optional.e(Boolean.valueOf(z));
            return this;
        }
    }

    public AutoValue_SetOptionsCommand(Optional optional, Optional optional2, Optional optional3, Optional optional4, Optional optional5, Optional optional6) {
        this.a = optional;
        this.b = optional2;
        this.c = optional3;
        this.d = optional4;
        this.e = optional5;
        this.f = optional6;
    }

    @Override // com.spotify.player.model.command.SetOptionsCommand
    public final Optional b() {
        return this.f;
    }

    @Override // com.spotify.player.model.command.SetOptionsCommand
    public final Optional c() {
        return this.e;
    }

    @Override // com.spotify.player.model.command.SetOptionsCommand
    public final Optional d() {
        return this.d;
    }

    @Override // com.spotify.player.model.command.SetOptionsCommand
    public final Optional e() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SetOptionsCommand)) {
            return false;
        }
        SetOptionsCommand setOptionsCommand = (SetOptionsCommand) obj;
        return this.a.equals(setOptionsCommand.f()) && this.b.equals(setOptionsCommand.e()) && this.c.equals(setOptionsCommand.g()) && this.d.equals(setOptionsCommand.d()) && this.e.equals(setOptionsCommand.c()) && this.f.equals(setOptionsCommand.b());
    }

    @Override // com.spotify.player.model.command.SetOptionsCommand
    public final Optional f() {
        return this.a;
    }

    @Override // com.spotify.player.model.command.SetOptionsCommand
    public final Optional g() {
        return this.c;
    }

    public final int hashCode() {
        return ((((((((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode()) * 1000003) ^ this.f.hashCode();
    }

    public final String toString() {
        return "SetOptionsCommand{repeatingTrack=" + this.a + ", repeatingContext=" + this.b + ", shufflingContext=" + this.c + ", playbackSpeed=" + this.d + ", options=" + this.e + ", loggingParams=" + this.f + "}";
    }
}
