package com.spotify.player.model.command.options;

import com.google.common.base.Optional;
import com.spotify.player.model.command.options.CommandOptions;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_CommandOptions extends CommandOptions {
    public final Optional b;
    public final Optional c;
    public final Optional d;
    public final Optional e;

    public static final class Builder extends CommandOptions.Builder {
        public final Optional a = Optional.a();
        public final Optional b = Optional.a();
        public Optional c = Optional.a();
        public final Optional d = Optional.a();

        @Override // com.spotify.player.model.command.options.CommandOptions.Builder
        public final CommandOptions a() {
            return new AutoValue_CommandOptions(this.a, this.b, this.c, this.d);
        }

        @Override // com.spotify.player.model.command.options.CommandOptions.Builder
        public final CommandOptions.Builder b(boolean z) {
            this.c = Optional.e(Boolean.valueOf(z));
            return this;
        }
    }

    public AutoValue_CommandOptions(Optional optional, Optional optional2, Optional optional3, Optional optional4) {
        this.b = optional;
        this.c = optional2;
        this.d = optional3;
        this.e = optional4;
    }

    @Override // com.spotify.player.model.command.options.CommandOptions
    public final Optional b() {
        return this.c;
    }

    @Override // com.spotify.player.model.command.options.CommandOptions
    public final Optional c() {
        return this.e;
    }

    @Override // com.spotify.player.model.command.options.CommandOptions
    public final Optional d() {
        return this.b;
    }

    @Override // com.spotify.player.model.command.options.CommandOptions
    public final Optional e() {
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CommandOptions)) {
            return false;
        }
        CommandOptions commandOptions = (CommandOptions) obj;
        return this.b.equals(commandOptions.d()) && this.c.equals(commandOptions.b()) && this.d.equals(commandOptions.e()) && this.e.equals(commandOptions.c());
    }

    public final int hashCode() {
        return ((((((this.b.hashCode() ^ 1000003) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode();
    }

    public final String toString() {
        return "CommandOptions{overrideRestrictions=" + this.b + ", onlyForLocalDevice=" + this.c + ", systemInitiated=" + this.d + ", onlyForPlaybackId=" + this.e + "}";
    }
}
