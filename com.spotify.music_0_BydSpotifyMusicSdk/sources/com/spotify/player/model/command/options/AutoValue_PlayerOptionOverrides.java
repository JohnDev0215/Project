package com.spotify.player.model.command.options;

import com.google.common.base.Optional;
import com.spotify.player.model.command.options.PlayerOptionOverrides;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_PlayerOptionOverrides extends PlayerOptionOverrides {
    public final Optional b;
    public final Optional c;
    public final Optional d;

    public static final class Builder extends PlayerOptionOverrides.Builder {
        public Optional a = Optional.a();
        public Optional b = Optional.a();
        public Optional c = Optional.a();

        @Override // com.spotify.player.model.command.options.PlayerOptionOverrides.Builder
        public final PlayerOptionOverrides a() {
            return new AutoValue_PlayerOptionOverrides(this.a, this.b, this.c);
        }

        @Override // com.spotify.player.model.command.options.PlayerOptionOverrides.Builder
        public final PlayerOptionOverrides.Builder b(Boolean bool) {
            this.b = Optional.e(bool);
            return this;
        }

        @Override // com.spotify.player.model.command.options.PlayerOptionOverrides.Builder
        public final PlayerOptionOverrides.Builder c(Boolean bool) {
            this.c = Optional.e(bool);
            return this;
        }

        @Override // com.spotify.player.model.command.options.PlayerOptionOverrides.Builder
        public final PlayerOptionOverrides.Builder d(Boolean bool) {
            this.a = Optional.e(bool);
            return this;
        }
    }

    public AutoValue_PlayerOptionOverrides(Optional optional, Optional optional2, Optional optional3) {
        this.b = optional;
        this.c = optional2;
        this.d = optional3;
    }

    @Override // com.spotify.player.model.command.options.PlayerOptionOverrides
    public final Optional b() {
        return this.c;
    }

    @Override // com.spotify.player.model.command.options.PlayerOptionOverrides
    public final Optional c() {
        return this.d;
    }

    @Override // com.spotify.player.model.command.options.PlayerOptionOverrides
    public final Optional d() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlayerOptionOverrides)) {
            return false;
        }
        PlayerOptionOverrides playerOptionOverrides = (PlayerOptionOverrides) obj;
        return this.b.equals(playerOptionOverrides.d()) && this.c.equals(playerOptionOverrides.b()) && this.d.equals(playerOptionOverrides.c());
    }

    public final int hashCode() {
        return ((((this.b.hashCode() ^ 1000003) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode();
    }

    public final String toString() {
        return "PlayerOptionOverrides{shufflingContext=" + this.b + ", repeatingContext=" + this.c + ", repeatingTrack=" + this.d + "}";
    }
}
