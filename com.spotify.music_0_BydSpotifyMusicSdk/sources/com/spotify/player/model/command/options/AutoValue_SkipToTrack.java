package com.spotify.player.model.command.options;

import com.google.common.base.Optional;
import com.spotify.player.model.command.options.SkipToTrack;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_SkipToTrack extends SkipToTrack {
    public final Optional b;
    public final Optional c;
    public final Optional d;
    public final Optional e;
    public final Optional f;

    public static final class Builder extends SkipToTrack.Builder {
        public Optional a = Optional.a();
        public Optional b = Optional.a();
        public Optional c = Optional.a();
        public Optional d = Optional.a();
        public Optional e = Optional.a();

        @Override // com.spotify.player.model.command.options.SkipToTrack.Builder
        public final SkipToTrack a() {
            return new AutoValue_SkipToTrack(this.a, this.b, this.c, this.d, this.e);
        }

        @Override // com.spotify.player.model.command.options.SkipToTrack.Builder
        public final SkipToTrack.Builder b(Long l) {
            this.b = Optional.e(l);
            return this;
        }

        @Override // com.spotify.player.model.command.options.SkipToTrack.Builder
        public final SkipToTrack.Builder c(String str) {
            this.a = Optional.e(str);
            return this;
        }

        @Override // com.spotify.player.model.command.options.SkipToTrack.Builder
        public final SkipToTrack.Builder d(Long l) {
            this.e = Optional.e(l);
            return this;
        }

        @Override // com.spotify.player.model.command.options.SkipToTrack.Builder
        public final SkipToTrack.Builder e(String str) {
            this.c = Optional.e(str);
            return this;
        }

        @Override // com.spotify.player.model.command.options.SkipToTrack.Builder
        public final SkipToTrack.Builder f(String str) {
            this.d = Optional.e(str);
            return this;
        }
    }

    public AutoValue_SkipToTrack(Optional optional, Optional optional2, Optional optional3, Optional optional4, Optional optional5) {
        this.b = optional;
        this.c = optional2;
        this.d = optional3;
        this.e = optional4;
        this.f = optional5;
    }

    @Override // com.spotify.player.model.command.options.SkipToTrack
    public final Optional c() {
        return this.c;
    }

    @Override // com.spotify.player.model.command.options.SkipToTrack
    public final Optional d() {
        return this.b;
    }

    @Override // com.spotify.player.model.command.options.SkipToTrack
    public final Optional e() {
        return this.f;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SkipToTrack)) {
            return false;
        }
        SkipToTrack skipToTrack = (SkipToTrack) obj;
        return this.b.equals(skipToTrack.d()) && this.c.equals(skipToTrack.c()) && this.d.equals(skipToTrack.f()) && this.e.equals(skipToTrack.g()) && this.f.equals(skipToTrack.e());
    }

    @Override // com.spotify.player.model.command.options.SkipToTrack
    public final Optional f() {
        return this.d;
    }

    @Override // com.spotify.player.model.command.options.SkipToTrack
    public final Optional g() {
        return this.e;
    }

    public final int hashCode() {
        return ((((((((this.b.hashCode() ^ 1000003) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode()) * 1000003) ^ this.f.hashCode();
    }

    public final String toString() {
        return "SkipToTrack{pageUrl=" + this.b + ", pageIndex=" + this.c + ", trackUid=" + this.d + ", trackUri=" + this.e + ", trackIndex=" + this.f + "}";
    }
}
