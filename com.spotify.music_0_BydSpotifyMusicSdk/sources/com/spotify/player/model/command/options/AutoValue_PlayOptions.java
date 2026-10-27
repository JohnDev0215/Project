package com.spotify.player.model.command.options;

import com.google.common.base.Optional;
import com.spotify.player.model.command.options.PlayOptions;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_PlayOptions extends PlayOptions {
    public final Optional a;
    public final Optional b;
    public final CommandOptions c;

    public static final class Builder extends PlayOptions.Builder {
        public final Optional a = Optional.a();
        public final Optional b = Optional.a();
        public CommandOptions c;

        @Override // com.spotify.player.model.command.options.PlayOptions.Builder
        public final PlayOptions a() {
            String str = this.c == null ? " commandOptions" : "";
            if (str.isEmpty()) {
                return new AutoValue_PlayOptions(this.a, this.b, this.c);
            }
            throw new IllegalStateException("Missing required properties:".concat(str));
        }
    }

    public AutoValue_PlayOptions(Optional optional, Optional optional2, CommandOptions commandOptions) {
        this.a = optional;
        this.b = optional2;
        this.c = commandOptions;
    }

    @Override // com.spotify.player.model.command.options.PlayOptions
    public final CommandOptions b() {
        return this.c;
    }

    @Override // com.spotify.player.model.command.options.PlayOptions
    public final Optional c() {
        return this.a;
    }

    @Override // com.spotify.player.model.command.options.PlayOptions
    public final Optional d() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlayOptions)) {
            return false;
        }
        PlayOptions playOptions = (PlayOptions) obj;
        return this.a.equals(playOptions.c()) && this.b.equals(playOptions.d()) && this.c.equals(playOptions.b());
    }

    public final int hashCode() {
        return ((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    public final String toString() {
        return "PlayOptions{operation=" + this.a + ", trigger=" + this.b + ", commandOptions=" + this.c + "}";
    }
}
