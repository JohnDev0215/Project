package com.spotify.player.model;

import com.google.common.base.Optional;
import com.spotify.player.model.PlayerOptions;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_PlayerOptions extends PlayerOptions {
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final Optional e;

    public static final class Builder extends PlayerOptions.Builder {
        public Boolean a;
        public Boolean b;
        public Boolean c;
        public Optional d = Optional.a();

        @Override // com.spotify.player.model.PlayerOptions.Builder
        public final PlayerOptions a() {
            String strH = this.a == null ? " shufflingContext" : "";
            if (this.b == null) {
                strH = strH.concat(" repeatingContext");
            }
            if (this.c == null) {
                strH = u4.h(strH, " repeatingTrack");
            }
            if (strH.isEmpty()) {
                return new AutoValue_PlayerOptions(this.a.booleanValue(), this.b.booleanValue(), this.c.booleanValue(), this.d);
            }
            throw new IllegalStateException("Missing required properties:".concat(strH));
        }

        @Override // com.spotify.player.model.PlayerOptions.Builder
        public final PlayerOptions.Builder b(float f) {
            this.d = Optional.e(Float.valueOf(f));
            return this;
        }

        @Override // com.spotify.player.model.PlayerOptions.Builder
        public final PlayerOptions.Builder c(boolean z) {
            this.b = Boolean.valueOf(z);
            return this;
        }

        @Override // com.spotify.player.model.PlayerOptions.Builder
        public final PlayerOptions.Builder d(boolean z) {
            this.c = Boolean.valueOf(z);
            return this;
        }

        @Override // com.spotify.player.model.PlayerOptions.Builder
        public final PlayerOptions.Builder e(boolean z) {
            this.a = Boolean.valueOf(z);
            return this;
        }
    }

    public AutoValue_PlayerOptions(boolean z, boolean z2, boolean z3, Optional optional) {
        this.b = z;
        this.c = z2;
        this.d = z3;
        this.e = optional;
    }

    @Override // com.spotify.player.model.PlayerOptions
    public final Optional b() {
        return this.e;
    }

    @Override // com.spotify.player.model.PlayerOptions
    public final boolean c() {
        return this.c;
    }

    @Override // com.spotify.player.model.PlayerOptions
    public final boolean d() {
        return this.d;
    }

    @Override // com.spotify.player.model.PlayerOptions
    public final boolean e() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlayerOptions)) {
            return false;
        }
        PlayerOptions playerOptions = (PlayerOptions) obj;
        return this.b == playerOptions.e() && this.c == playerOptions.c() && this.d == playerOptions.d() && this.e.equals(playerOptions.b());
    }

    public final int hashCode() {
        return (((((((this.b ? 1231 : 1237) ^ 1000003) * 1000003) ^ (this.c ? 1231 : 1237)) * 1000003) ^ (this.d ? 1231 : 1237)) * 1000003) ^ this.e.hashCode();
    }

    public final String toString() {
        return "PlayerOptions{shufflingContext=" + this.b + ", repeatingContext=" + this.c + ", repeatingTrack=" + this.d + ", playbackSpeed=" + this.e + "}";
    }
}
