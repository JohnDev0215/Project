package com.spotify.player.model.command.options;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.spotify.player.model.AudioStream;
import com.spotify.player.model.Suppressions;
import com.spotify.player.model.command.options.PreparePlayOptions;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_PreparePlayOptions extends PreparePlayOptions {
    public final Optional b;
    public final Optional c;
    public final Optional d;
    public final Optional e;
    public final Optional f;
    public final Optional g;
    public final Optional h;
    public final Optional i;
    public final Optional j;
    public final Optional k;
    public final Optional l;
    public final Optional m;
    public final ImmutableMap n;

    public static final class Builder extends PreparePlayOptions.Builder {
        public Optional a = Optional.a();
        public Optional b = Optional.a();
        public Optional c = Optional.a();
        public Optional d = Optional.a();
        public Optional e = Optional.a();
        public Optional f = Optional.a();
        public Optional g = Optional.a();
        public Optional h = Optional.a();
        public Optional i = Optional.a();
        public Optional j = Optional.a();
        public Optional k = Optional.a();
        public Optional l = Optional.a();
        public ImmutableMap m;

        @Override // com.spotify.player.model.command.options.PreparePlayOptions.Builder
        public final PreparePlayOptions.Builder a(boolean z) {
            this.b = Optional.e(Boolean.valueOf(z));
            return this;
        }

        @Override // com.spotify.player.model.command.options.PreparePlayOptions.Builder
        public final PreparePlayOptions.Builder b(AudioStream audioStream) {
            this.j = Optional.e(audioStream);
            return this;
        }

        @Override // com.spotify.player.model.command.options.PreparePlayOptions.Builder
        public final PreparePlayOptions c() {
            String str = this.m == null ? " configurationOverride" : "";
            if (str.isEmpty()) {
                return new AutoValue_PreparePlayOptions(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m);
            }
            throw new IllegalStateException("Missing required properties:".concat(str));
        }

        @Override // com.spotify.player.model.command.options.PreparePlayOptions.Builder
        public final PreparePlayOptions.Builder d(ImmutableMap immutableMap) {
            if (immutableMap == null) {
                throw new NullPointerException("Null configurationOverride");
            }
            this.m = immutableMap;
            return this;
        }

        @Override // com.spotify.player.model.command.options.PreparePlayOptions.Builder
        public final PreparePlayOptions.Builder e(boolean z) {
            this.e = Optional.e(Boolean.valueOf(z));
            return this;
        }

        @Override // com.spotify.player.model.command.options.PreparePlayOptions.Builder
        public final PreparePlayOptions.Builder f(String str) {
            this.l = Optional.e(str);
            return this;
        }

        @Override // com.spotify.player.model.command.options.PreparePlayOptions.Builder
        public final PreparePlayOptions.Builder g(String str) {
            this.a = Optional.e(str);
            return this;
        }

        @Override // com.spotify.player.model.command.options.PreparePlayOptions.Builder
        public final PreparePlayOptions.Builder h(PlayerOptionOverrides playerOptionOverrides) {
            this.g = Optional.e(playerOptionOverrides);
            return this;
        }

        @Override // com.spotify.player.model.command.options.PreparePlayOptions.Builder
        public final PreparePlayOptions.Builder i(PrefetchLevel prefetchLevel) {
            this.i = Optional.e(prefetchLevel);
            return this;
        }

        @Override // com.spotify.player.model.command.options.PreparePlayOptions.Builder
        public final PreparePlayOptions.Builder j(Long l) {
            this.d = Optional.e(l);
            return this;
        }

        @Override // com.spotify.player.model.command.options.PreparePlayOptions.Builder
        public final PreparePlayOptions.Builder k(String str) {
            this.k = Optional.e(str);
            return this;
        }

        @Override // com.spotify.player.model.command.options.PreparePlayOptions.Builder
        public final PreparePlayOptions.Builder l(SkipToTrack skipToTrack) {
            this.c = Optional.e(skipToTrack);
            return this;
        }

        @Override // com.spotify.player.model.command.options.PreparePlayOptions.Builder
        public final PreparePlayOptions.Builder m(Suppressions suppressions) {
            this.h = Optional.e(suppressions);
            return this;
        }

        @Override // com.spotify.player.model.command.options.PreparePlayOptions.Builder
        public final PreparePlayOptions.Builder n(boolean z) {
            this.f = Optional.e(Boolean.valueOf(z));
            return this;
        }
    }

    public AutoValue_PreparePlayOptions(Optional optional, Optional optional2, Optional optional3, Optional optional4, Optional optional5, Optional optional6, Optional optional7, Optional optional8, Optional optional9, Optional optional10, Optional optional11, Optional optional12, ImmutableMap immutableMap) {
        this.b = optional;
        this.c = optional2;
        this.d = optional3;
        this.e = optional4;
        this.f = optional5;
        this.g = optional6;
        this.h = optional7;
        this.i = optional8;
        this.j = optional9;
        this.k = optional10;
        this.l = optional11;
        this.m = optional12;
        this.n = immutableMap;
    }

    @Override // com.spotify.player.model.command.options.PreparePlayOptions
    public final Optional a() {
        return this.c;
    }

    @Override // com.spotify.player.model.command.options.PreparePlayOptions
    public final Optional b() {
        return this.k;
    }

    @Override // com.spotify.player.model.command.options.PreparePlayOptions
    public final ImmutableMap d() {
        return this.n;
    }

    @Override // com.spotify.player.model.command.options.PreparePlayOptions
    public final Optional e() {
        return this.f;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PreparePlayOptions)) {
            return false;
        }
        PreparePlayOptions preparePlayOptions = (PreparePlayOptions) obj;
        return this.b.equals(preparePlayOptions.g()) && this.c.equals(preparePlayOptions.a()) && this.d.equals(preparePlayOptions.l()) && this.e.equals(preparePlayOptions.j()) && this.f.equals(preparePlayOptions.e()) && this.g.equals(preparePlayOptions.n()) && this.h.equals(preparePlayOptions.h()) && this.i.equals(preparePlayOptions.m()) && this.j.equals(preparePlayOptions.i()) && this.k.equals(preparePlayOptions.b()) && this.l.equals(preparePlayOptions.k()) && this.m.equals(preparePlayOptions.f()) && this.n.equals(preparePlayOptions.d());
    }

    @Override // com.spotify.player.model.command.options.PreparePlayOptions
    public final Optional f() {
        return this.m;
    }

    @Override // com.spotify.player.model.command.options.PreparePlayOptions
    public final Optional g() {
        return this.b;
    }

    @Override // com.spotify.player.model.command.options.PreparePlayOptions
    public final Optional h() {
        return this.h;
    }

    public final int hashCode() {
        return ((((((((((((((((((((((((this.b.hashCode() ^ 1000003) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode()) * 1000003) ^ this.f.hashCode()) * 1000003) ^ this.g.hashCode()) * 1000003) ^ this.h.hashCode()) * 1000003) ^ this.i.hashCode()) * 1000003) ^ this.j.hashCode()) * 1000003) ^ this.k.hashCode()) * 1000003) ^ this.l.hashCode()) * 1000003) ^ this.m.hashCode()) * 1000003) ^ this.n.hashCode();
    }

    @Override // com.spotify.player.model.command.options.PreparePlayOptions
    public final Optional i() {
        return this.j;
    }

    @Override // com.spotify.player.model.command.options.PreparePlayOptions
    public final Optional j() {
        return this.e;
    }

    @Override // com.spotify.player.model.command.options.PreparePlayOptions
    public final Optional k() {
        return this.l;
    }

    @Override // com.spotify.player.model.command.options.PreparePlayOptions
    public final Optional l() {
        return this.d;
    }

    @Override // com.spotify.player.model.command.options.PreparePlayOptions
    public final Optional m() {
        return this.i;
    }

    @Override // com.spotify.player.model.command.options.PreparePlayOptions
    public final Optional n() {
        return this.g;
    }

    @Override // com.spotify.player.model.command.options.PreparePlayOptions
    public final PreparePlayOptions.Builder o() {
        Builder builder = new Builder();
        builder.a = Optional.a();
        builder.b = Optional.a();
        builder.c = Optional.a();
        builder.d = Optional.a();
        builder.e = Optional.a();
        builder.f = Optional.a();
        builder.g = Optional.a();
        builder.h = Optional.a();
        builder.i = Optional.a();
        builder.j = Optional.a();
        builder.k = Optional.a();
        builder.l = Optional.a();
        builder.a = g();
        builder.b = a();
        builder.c = l();
        builder.d = j();
        builder.e = e();
        builder.f = n();
        builder.g = h();
        builder.h = m();
        builder.i = i();
        builder.j = b();
        builder.k = k();
        builder.l = f();
        builder.m = d();
        return builder;
    }

    public final String toString() {
        return "PreparePlayOptions{playbackId=" + this.b + ", alwaysPlaySomething=" + this.c + ", skipTo=" + this.d + ", seekTo=" + this.e + ", initiallyPaused=" + this.f + ", systemInitiated=" + this.g + ", playerOptionsOverride=" + this.h + ", suppressions=" + this.i + ", prefetchLevel=" + this.j + ", audioStream=" + this.k + ", sessionId=" + this.l + ", license=" + this.m + ", configurationOverride=" + this.n + "}";
    }
}
