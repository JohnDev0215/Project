package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.Context;
import com.spotify.player.model.PlayOrigin;
import com.spotify.player.model.command.PlayCommand;
import com.spotify.player.model.command.options.LoggingParams;
import com.spotify.player.model.command.options.PlayOptions;
import com.spotify.player.model.command.options.PreparePlayOptions;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_PlayCommand extends PlayCommand {
    public final Context a;
    public final PlayOrigin b;
    public final Optional c;
    public final Optional d;
    public final Optional e;

    public static final class Builder extends PlayCommand.Builder {
        public Context a;
        public PlayOrigin b;
        public Optional c = Optional.a();
        public Optional d = Optional.a();
        public Optional e = Optional.a();

        @Override // com.spotify.player.model.command.PlayCommand.Builder
        public final PlayCommand a() {
            String strConcat = this.a == null ? " context" : "";
            if (this.b == null) {
                strConcat = strConcat.concat(" playOrigin");
            }
            if (strConcat.isEmpty()) {
                return new AutoValue_PlayCommand(this.a, this.b, this.c, this.d, this.e);
            }
            throw new IllegalStateException("Missing required properties:".concat(strConcat));
        }

        @Override // com.spotify.player.model.command.PlayCommand.Builder
        public final PlayCommand.Builder b(LoggingParams loggingParams) {
            this.e = Optional.e(loggingParams);
            return this;
        }

        @Override // com.spotify.player.model.command.PlayCommand.Builder
        public final PlayCommand.Builder c(PreparePlayOptions preparePlayOptions) {
            this.c = Optional.e(preparePlayOptions);
            return this;
        }

        @Override // com.spotify.player.model.command.PlayCommand.Builder
        public final PlayCommand.Builder d(PlayOptions playOptions) {
            this.d = Optional.e(playOptions);
            return this;
        }
    }

    public AutoValue_PlayCommand(Context context, PlayOrigin playOrigin, Optional optional, Optional optional2, Optional optional3) {
        this.a = context;
        this.b = playOrigin;
        this.c = optional;
        this.d = optional2;
        this.e = optional3;
    }

    @Override // com.spotify.player.model.command.PlayCommand
    public final Context b() {
        return this.a;
    }

    @Override // com.spotify.player.model.command.PlayCommand
    public final Optional c() {
        return this.e;
    }

    @Override // com.spotify.player.model.command.PlayCommand
    public final Optional d() {
        return this.c;
    }

    @Override // com.spotify.player.model.command.PlayCommand
    public final Optional e() {
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlayCommand)) {
            return false;
        }
        PlayCommand playCommand = (PlayCommand) obj;
        return this.a.equals(playCommand.b()) && this.b.equals(playCommand.f()) && this.c.equals(playCommand.d()) && this.d.equals(playCommand.e()) && this.e.equals(playCommand.c());
    }

    @Override // com.spotify.player.model.command.PlayCommand
    public final PlayOrigin f() {
        return this.b;
    }

    public final int hashCode() {
        return ((((((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode();
    }

    public final String toString() {
        return "PlayCommand{context=" + this.a + ", playOrigin=" + this.b + ", options=" + this.c + ", playOptions=" + this.d + ", loggingParams=" + this.e + "}";
    }
}
