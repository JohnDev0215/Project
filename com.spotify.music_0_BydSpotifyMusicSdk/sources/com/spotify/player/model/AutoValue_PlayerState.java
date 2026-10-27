package com.spotify.player.model;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.spotify.player.model.PlayerState;
import java.util.List;
import java.util.Map;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_PlayerState extends PlayerState {
    public final ImmutableList A;
    public final long a;
    public final String b;
    public final String c;
    public final Restrictions d;
    public final PlayOrigin e;
    public final Optional f;
    public final Optional g;
    public final Optional h;
    public final Optional i;
    public final Optional j;
    public final Optional k;
    public final Optional l;
    public final boolean m;
    public final boolean n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f56p;
    public final PlayerOptions q;
    public final Restrictions r;
    public final Suppressions s;
    public final ImmutableList t;
    public final ImmutableList u;
    public final ImmutableMap v;
    public final ImmutableMap w;
    public final String x;
    public final String y;
    public final AudioStream z;

    public static final class Builder extends PlayerState.Builder {
        public ImmutableList A;
        public Long a;
        public String b;
        public String c;
        public Restrictions d;
        public PlayOrigin e;
        public Optional f = Optional.a();
        public Optional g = Optional.a();
        public Optional h = Optional.a();
        public Optional i = Optional.a();
        public Optional j = Optional.a();
        public Optional k = Optional.a();
        public Optional l = Optional.a();
        public Boolean m;
        public Boolean n;
        public Boolean o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public Boolean f57p;
        public PlayerOptions q;
        public Restrictions r;
        public Suppressions s;
        public ImmutableList t;
        public ImmutableList u;
        public ImmutableMap v;
        public ImmutableMap w;
        public String x;
        public String y;
        public AudioStream z;

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder A(long j) {
            this.a = Long.valueOf(j);
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder B(ContextTrack contextTrack) {
            this.g = Optional.e(contextTrack);
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder a(AudioStream audioStream) {
            if (audioStream == null) {
                throw new NullPointerException("Null audioStream");
            }
            this.z = audioStream;
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState b() {
            String strH = this.a == null ? " timestamp" : "";
            if (this.b == null) {
                strH = strH.concat(" contextUri");
            }
            if (this.c == null) {
                strH = u4.h(strH, " contextUrl");
            }
            if (this.d == null) {
                strH = u4.h(strH, " contextRestrictions");
            }
            if (this.e == null) {
                strH = u4.h(strH, " playOrigin");
            }
            if (this.m == null) {
                strH = u4.h(strH, " isPlaying");
            }
            if (this.n == null) {
                strH = u4.h(strH, " isPaused");
            }
            if (this.o == null) {
                strH = u4.h(strH, " isBuffering");
            }
            if (this.f57p == null) {
                strH = u4.h(strH, " isSystemInitiated");
            }
            if (this.q == null) {
                strH = u4.h(strH, " options");
            }
            if (this.r == null) {
                strH = u4.h(strH, " restrictions");
            }
            if (this.s == null) {
                strH = u4.h(strH, " suppressions");
            }
            if (this.t == null) {
                strH = u4.h(strH, " prevTracks");
            }
            if (this.u == null) {
                strH = u4.h(strH, " nextTracks");
            }
            if (this.v == null) {
                strH = u4.h(strH, " contextMetadata");
            }
            if (this.w == null) {
                strH = u4.h(strH, " pageMetadata");
            }
            if (this.x == null) {
                strH = u4.h(strH, " sessionId");
            }
            if (this.y == null) {
                strH = u4.h(strH, " queueRevision");
            }
            if (this.z == null) {
                strH = u4.h(strH, " audioStream");
            }
            if (this.A == null) {
                strH = u4.h(strH, " signals");
            }
            if (strH.isEmpty()) {
                return new AutoValue_PlayerState(this.a.longValue(), this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m.booleanValue(), this.n.booleanValue(), this.o.booleanValue(), this.f57p.booleanValue(), this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A);
            }
            throw new IllegalStateException("Missing required properties:".concat(strH));
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder c(Map map) {
            this.v = ImmutableMap.b(map);
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder d(Restrictions restrictions) {
            if (restrictions == null) {
                throw new NullPointerException("Null contextRestrictions");
            }
            this.d = restrictions;
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder e(String str) {
            if (str == null) {
                throw new NullPointerException("Null contextUri");
            }
            this.b = str;
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder f(String str) {
            if (str == null) {
                throw new NullPointerException("Null contextUrl");
            }
            this.c = str;
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder g(Long l) {
            this.l = Optional.e(l);
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder h(ContextIndex contextIndex) {
            this.f = Optional.e(contextIndex);
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder i(boolean z) {
            this.o = Boolean.valueOf(z);
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder j(boolean z) {
            this.n = Boolean.valueOf(z);
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder k(boolean z) {
            this.m = Boolean.valueOf(z);
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder l(boolean z) {
            this.f57p = Boolean.valueOf(z);
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder m(List list) {
            this.u = ImmutableList.o(list);
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder n(PlayerOptions playerOptions) {
            if (playerOptions == null) {
                throw new NullPointerException("Null options");
            }
            this.q = playerOptions;
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder o(Map map) {
            this.w = ImmutableMap.b(map);
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder p(PlayOrigin playOrigin) {
            if (playOrigin == null) {
                throw new NullPointerException("Null playOrigin");
            }
            this.e = playOrigin;
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder q(String str) {
            this.h = Optional.e(str);
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder r(PlaybackQuality playbackQuality) {
            this.i = Optional.e(playbackQuality);
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder s(Double d) {
            this.j = Optional.e(d);
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder t(Long l) {
            this.k = Optional.e(l);
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder u(List list) {
            this.t = ImmutableList.o(list);
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder v(String str) {
            if (str == null) {
                throw new NullPointerException("Null queueRevision");
            }
            this.y = str;
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder w(Restrictions restrictions) {
            if (restrictions == null) {
                throw new NullPointerException("Null restrictions");
            }
            this.r = restrictions;
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder x(String str) {
            if (str == null) {
                throw new NullPointerException("Null sessionId");
            }
            this.x = str;
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder y(List list) {
            this.A = ImmutableList.o(list);
            return this;
        }

        @Override // com.spotify.player.model.PlayerState.Builder
        public final PlayerState.Builder z(Suppressions suppressions) {
            if (suppressions == null) {
                throw new NullPointerException("Null suppressions");
            }
            this.s = suppressions;
            return this;
        }
    }

    public AutoValue_PlayerState(long j, String str, String str2, Restrictions restrictions, PlayOrigin playOrigin, Optional optional, Optional optional2, Optional optional3, Optional optional4, Optional optional5, Optional optional6, Optional optional7, boolean z, boolean z2, boolean z3, boolean z4, PlayerOptions playerOptions, Restrictions restrictions2, Suppressions suppressions, ImmutableList immutableList, ImmutableList immutableList2, ImmutableMap immutableMap, ImmutableMap immutableMap2, String str3, String str4, AudioStream audioStream, ImmutableList immutableList3) {
        this.a = j;
        this.b = str;
        this.c = str2;
        this.d = restrictions;
        this.e = playOrigin;
        this.f = optional;
        this.g = optional2;
        this.h = optional3;
        this.i = optional4;
        this.j = optional5;
        this.k = optional6;
        this.l = optional7;
        this.m = z;
        this.n = z2;
        this.o = z3;
        this.f56p = z4;
        this.q = playerOptions;
        this.r = restrictions2;
        this.s = suppressions;
        this.t = immutableList;
        this.u = immutableList2;
        this.v = immutableMap;
        this.w = immutableMap2;
        this.x = str3;
        this.y = str4;
        this.z = audioStream;
        this.A = immutableList3;
    }

    @Override // com.spotify.player.model.PlayerState
    public final long A() {
        return this.a;
    }

    @Override // com.spotify.player.model.PlayerState
    public final Optional B() {
        return this.g;
    }

    @Override // com.spotify.player.model.PlayerState
    public final AudioStream a() {
        return this.z;
    }

    @Override // com.spotify.player.model.PlayerState
    public final ImmutableMap c() {
        return this.v;
    }

    @Override // com.spotify.player.model.PlayerState
    public final Restrictions d() {
        return this.d;
    }

    @Override // com.spotify.player.model.PlayerState
    public final String e() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlayerState)) {
            return false;
        }
        PlayerState playerState = (PlayerState) obj;
        return this.a == playerState.A() && this.b.equals(playerState.e()) && this.c.equals(playerState.f()) && this.d.equals(playerState.d()) && this.e.equals(playerState.p()) && this.f.equals(playerState.h()) && this.g.equals(playerState.B()) && this.h.equals(playerState.q()) && this.i.equals(playerState.r()) && this.j.equals(playerState.s()) && this.k.equals(playerState.t()) && this.l.equals(playerState.g()) && this.m == playerState.k() && this.n == playerState.j() && this.o == playerState.i() && this.f56p == playerState.l() && this.q.equals(playerState.n()) && this.r.equals(playerState.w()) && this.s.equals(playerState.z()) && this.t.equals(playerState.u()) && this.u.equals(playerState.m()) && this.v.equals(playerState.c()) && this.w.equals(playerState.o()) && this.x.equals(playerState.x()) && this.y.equals(playerState.v()) && this.z.equals(playerState.a()) && this.A.equals(playerState.y());
    }

    @Override // com.spotify.player.model.PlayerState
    public final String f() {
        return this.c;
    }

    @Override // com.spotify.player.model.PlayerState
    public final Optional g() {
        return this.l;
    }

    @Override // com.spotify.player.model.PlayerState
    public final Optional h() {
        return this.f;
    }

    public final int hashCode() {
        long j = this.a;
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((((int) ((j >>> 32) ^ j)) ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode()) * 1000003) ^ this.f.hashCode()) * 1000003) ^ this.g.hashCode()) * 1000003) ^ this.h.hashCode()) * 1000003) ^ this.i.hashCode()) * 1000003) ^ this.j.hashCode()) * 1000003) ^ this.k.hashCode()) * 1000003) ^ this.l.hashCode()) * 1000003) ^ (this.m ? 1231 : 1237)) * 1000003) ^ (this.n ? 1231 : 1237)) * 1000003) ^ (this.o ? 1231 : 1237)) * 1000003) ^ (this.f56p ? 1231 : 1237)) * 1000003) ^ this.q.hashCode()) * 1000003) ^ this.r.hashCode()) * 1000003) ^ this.s.hashCode()) * 1000003) ^ this.t.hashCode()) * 1000003) ^ this.u.hashCode()) * 1000003) ^ this.v.hashCode()) * 1000003) ^ this.w.hashCode()) * 1000003) ^ this.x.hashCode()) * 1000003) ^ this.y.hashCode()) * 1000003) ^ this.z.hashCode()) * 1000003) ^ this.A.hashCode();
    }

    @Override // com.spotify.player.model.PlayerState
    public final boolean i() {
        return this.o;
    }

    @Override // com.spotify.player.model.PlayerState
    public final boolean j() {
        return this.n;
    }

    @Override // com.spotify.player.model.PlayerState
    public final boolean k() {
        return this.m;
    }

    @Override // com.spotify.player.model.PlayerState
    public final boolean l() {
        return this.f56p;
    }

    @Override // com.spotify.player.model.PlayerState
    public final ImmutableList m() {
        return this.u;
    }

    @Override // com.spotify.player.model.PlayerState
    public final PlayerOptions n() {
        return this.q;
    }

    @Override // com.spotify.player.model.PlayerState
    public final ImmutableMap o() {
        return this.w;
    }

    @Override // com.spotify.player.model.PlayerState
    public final PlayOrigin p() {
        return this.e;
    }

    @Override // com.spotify.player.model.PlayerState
    public final Optional q() {
        return this.h;
    }

    @Override // com.spotify.player.model.PlayerState
    public final Optional r() {
        return this.i;
    }

    @Override // com.spotify.player.model.PlayerState
    public final Optional s() {
        return this.j;
    }

    @Override // com.spotify.player.model.PlayerState
    public final Optional t() {
        return this.k;
    }

    public final String toString() {
        return "PlayerState{timestamp=" + this.a + ", contextUri=" + this.b + ", contextUrl=" + this.c + ", contextRestrictions=" + this.d + ", playOrigin=" + this.e + ", index=" + this.f + ", track=" + this.g + ", playbackId=" + this.h + ", playbackQuality=" + this.i + ", playbackSpeed=" + this.j + ", positionAsOfTimestamp=" + this.k + ", duration=" + this.l + ", isPlaying=" + this.m + ", isPaused=" + this.n + ", isBuffering=" + this.o + ", isSystemInitiated=" + this.f56p + ", options=" + this.q + ", restrictions=" + this.r + ", suppressions=" + this.s + ", prevTracks=" + this.t + ", nextTracks=" + this.u + ", contextMetadata=" + this.v + ", pageMetadata=" + this.w + ", sessionId=" + this.x + ", queueRevision=" + this.y + ", audioStream=" + this.z + ", signals=" + this.A + "}";
    }

    @Override // com.spotify.player.model.PlayerState
    public final ImmutableList u() {
        return this.t;
    }

    @Override // com.spotify.player.model.PlayerState
    public final String v() {
        return this.y;
    }

    @Override // com.spotify.player.model.PlayerState
    public final Restrictions w() {
        return this.r;
    }

    @Override // com.spotify.player.model.PlayerState
    public final String x() {
        return this.x;
    }

    @Override // com.spotify.player.model.PlayerState
    public final ImmutableList y() {
        return this.A;
    }

    @Override // com.spotify.player.model.PlayerState
    public final Suppressions z() {
        return this.s;
    }
}
