package com.spotify.player.model;

import com.google.common.collect.ImmutableSet;
import com.spotify.player.model.Restrictions;
import java.util.Set;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_Restrictions extends Restrictions {
    public final ImmutableSet b;
    public final ImmutableSet c;
    public final ImmutableSet d;
    public final ImmutableSet e;
    public final ImmutableSet f;
    public final ImmutableSet g;
    public final ImmutableSet h;
    public final ImmutableSet i;
    public final ImmutableSet j;
    public final ImmutableSet k;
    public final ImmutableSet l;
    public final ImmutableSet m;
    public final ImmutableSet n;
    public final ImmutableSet o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ImmutableSet f58p;
    public final ImmutableSet q;
    public final ImmutableSet r;
    public final ImmutableSet s;
    public final ImmutableSet t;
    public final ImmutableSet u;
    public final ImmutableSet v;
    public final ImmutableSet w;
    public final ImmutableSet x;

    public static final class Builder extends Restrictions.Builder {
        public ImmutableSet a;
        public ImmutableSet b;
        public ImmutableSet c;
        public ImmutableSet d;
        public ImmutableSet e;
        public ImmutableSet f;
        public ImmutableSet g;
        public ImmutableSet h;
        public ImmutableSet i;
        public ImmutableSet j;
        public ImmutableSet k;
        public ImmutableSet l;
        public ImmutableSet m;
        public ImmutableSet n;
        public ImmutableSet o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public ImmutableSet f59p;
        public ImmutableSet q;
        public ImmutableSet r;
        public ImmutableSet s;
        public ImmutableSet t;
        public ImmutableSet u;
        public ImmutableSet v;
        public ImmutableSet w;

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions a() {
            String strH = this.a == null ? " disallowPausingReasons" : "";
            if (this.b == null) {
                strH = strH.concat(" disallowResumingReasons");
            }
            if (this.c == null) {
                strH = u4.h(strH, " disallowSeekingReasons");
            }
            if (this.d == null) {
                strH = u4.h(strH, " disallowPeekingPrevReasons");
            }
            if (this.e == null) {
                strH = u4.h(strH, " disallowPeekingNextReasons");
            }
            if (this.f == null) {
                strH = u4.h(strH, " disallowSkippingPrevReasons");
            }
            if (this.g == null) {
                strH = u4.h(strH, " disallowSkippingNextReasons");
            }
            if (this.h == null) {
                strH = u4.h(strH, " disallowTogglingRepeatContextReasons");
            }
            if (this.i == null) {
                strH = u4.h(strH, " disallowTogglingRepeatTrackReasons");
            }
            if (this.j == null) {
                strH = u4.h(strH, " disallowTogglingShuffleReasons");
            }
            if (this.k == null) {
                strH = u4.h(strH, " disallowSetQueueReasons");
            }
            if (this.l == null) {
                strH = u4.h(strH, " disallowAddToQueueReasons");
            }
            if (this.m == null) {
                strH = u4.h(strH, " disallowInterruptingPlaybackReasons");
            }
            if (this.n == null) {
                strH = u4.h(strH, " disallowTransferringPlaybackReasons");
            }
            if (this.o == null) {
                strH = u4.h(strH, " disallowRemoteControlReasons");
            }
            if (this.f59p == null) {
                strH = u4.h(strH, " disallowInsertingIntoNextTracksReasons");
            }
            if (this.q == null) {
                strH = u4.h(strH, " disallowInsertingIntoContextTracksReasons");
            }
            if (this.r == null) {
                strH = u4.h(strH, " disallowReorderingInNextTracksReasons");
            }
            if (this.s == null) {
                strH = u4.h(strH, " disallowReorderingInContextTracksReasons");
            }
            if (this.t == null) {
                strH = u4.h(strH, " disallowRemovingFromNextTracksReasons");
            }
            if (this.u == null) {
                strH = u4.h(strH, " disallowRemovingFromContextTracksReasons");
            }
            if (this.v == null) {
                strH = u4.h(strH, " disallowUpdatingContextReasons");
            }
            if (this.w == null) {
                strH = u4.h(strH, " disallowSettingPlaybackSpeedReasons");
            }
            if (strH.isEmpty()) {
                return new AutoValue_Restrictions(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.f59p, this.q, this.r, this.s, this.t, this.u, this.v, this.w);
            }
            throw new IllegalStateException("Missing required properties:".concat(strH));
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder b(Set set) {
            this.l = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder c(Set set) {
            this.q = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder d(Set set) {
            this.f59p = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder e(Set set) {
            this.m = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder f(Set set) {
            this.a = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder g(Set set) {
            this.e = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder h(Set set) {
            this.d = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder i(Set set) {
            this.o = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder j(Set set) {
            this.u = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder k(Set set) {
            this.t = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder l(Set set) {
            this.s = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder m(Set set) {
            this.r = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder n(Set set) {
            this.b = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder o(Set set) {
            this.c = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder p(Set set) {
            this.k = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder q(Set set) {
            this.w = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder r(Set set) {
            this.g = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder s(Set set) {
            this.f = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder t(Set set) {
            this.h = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder u(Set set) {
            this.i = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder v(Set set) {
            this.j = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder w(Set set) {
            this.n = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.Restrictions.Builder
        public final Restrictions.Builder x(Set set) {
            this.v = ImmutableSet.o(set);
            return this;
        }
    }

    public AutoValue_Restrictions(ImmutableSet immutableSet, ImmutableSet immutableSet2, ImmutableSet immutableSet3, ImmutableSet immutableSet4, ImmutableSet immutableSet5, ImmutableSet immutableSet6, ImmutableSet immutableSet7, ImmutableSet immutableSet8, ImmutableSet immutableSet9, ImmutableSet immutableSet10, ImmutableSet immutableSet11, ImmutableSet immutableSet12, ImmutableSet immutableSet13, ImmutableSet immutableSet14, ImmutableSet immutableSet15, ImmutableSet immutableSet16, ImmutableSet immutableSet17, ImmutableSet immutableSet18, ImmutableSet immutableSet19, ImmutableSet immutableSet20, ImmutableSet immutableSet21, ImmutableSet immutableSet22, ImmutableSet immutableSet23) {
        this.b = immutableSet;
        this.c = immutableSet2;
        this.d = immutableSet3;
        this.e = immutableSet4;
        this.f = immutableSet5;
        this.g = immutableSet6;
        this.h = immutableSet7;
        this.i = immutableSet8;
        this.j = immutableSet9;
        this.k = immutableSet10;
        this.l = immutableSet11;
        this.m = immutableSet12;
        this.n = immutableSet13;
        this.o = immutableSet14;
        this.f58p = immutableSet15;
        this.q = immutableSet16;
        this.r = immutableSet17;
        this.s = immutableSet18;
        this.t = immutableSet19;
        this.u = immutableSet20;
        this.v = immutableSet21;
        this.w = immutableSet22;
        this.x = immutableSet23;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet b() {
        return this.m;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet c() {
        return this.r;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet d() {
        return this.q;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet e() {
        return this.n;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Restrictions)) {
            return false;
        }
        Restrictions restrictions = (Restrictions) obj;
        return this.b.equals(restrictions.f()) && this.c.equals(restrictions.n()) && this.d.equals(restrictions.o()) && this.e.equals(restrictions.h()) && this.f.equals(restrictions.g()) && this.g.equals(restrictions.s()) && this.h.equals(restrictions.r()) && this.i.equals(restrictions.t()) && this.j.equals(restrictions.u()) && this.k.equals(restrictions.v()) && this.l.equals(restrictions.p()) && this.m.equals(restrictions.b()) && this.n.equals(restrictions.e()) && this.o.equals(restrictions.w()) && this.f58p.equals(restrictions.i()) && this.q.equals(restrictions.d()) && this.r.equals(restrictions.c()) && this.s.equals(restrictions.m()) && this.t.equals(restrictions.l()) && this.u.equals(restrictions.k()) && this.v.equals(restrictions.j()) && this.w.equals(restrictions.x()) && this.x.equals(restrictions.q());
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet f() {
        return this.b;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet g() {
        return this.f;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet h() {
        return this.e;
    }

    public final int hashCode() {
        return ((((((((((((((((((((((((((((((((((((((((((((this.b.hashCode() ^ 1000003) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode()) * 1000003) ^ this.f.hashCode()) * 1000003) ^ this.g.hashCode()) * 1000003) ^ this.h.hashCode()) * 1000003) ^ this.i.hashCode()) * 1000003) ^ this.j.hashCode()) * 1000003) ^ this.k.hashCode()) * 1000003) ^ this.l.hashCode()) * 1000003) ^ this.m.hashCode()) * 1000003) ^ this.n.hashCode()) * 1000003) ^ this.o.hashCode()) * 1000003) ^ this.f58p.hashCode()) * 1000003) ^ this.q.hashCode()) * 1000003) ^ this.r.hashCode()) * 1000003) ^ this.s.hashCode()) * 1000003) ^ this.t.hashCode()) * 1000003) ^ this.u.hashCode()) * 1000003) ^ this.v.hashCode()) * 1000003) ^ this.w.hashCode()) * 1000003) ^ this.x.hashCode();
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet i() {
        return this.f58p;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet j() {
        return this.v;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet k() {
        return this.u;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet l() {
        return this.t;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet m() {
        return this.s;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet n() {
        return this.c;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet o() {
        return this.d;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet p() {
        return this.l;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet q() {
        return this.x;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet r() {
        return this.h;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet s() {
        return this.g;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet t() {
        return this.i;
    }

    public final String toString() {
        return "Restrictions{disallowPausingReasons=" + this.b + ", disallowResumingReasons=" + this.c + ", disallowSeekingReasons=" + this.d + ", disallowPeekingPrevReasons=" + this.e + ", disallowPeekingNextReasons=" + this.f + ", disallowSkippingPrevReasons=" + this.g + ", disallowSkippingNextReasons=" + this.h + ", disallowTogglingRepeatContextReasons=" + this.i + ", disallowTogglingRepeatTrackReasons=" + this.j + ", disallowTogglingShuffleReasons=" + this.k + ", disallowSetQueueReasons=" + this.l + ", disallowAddToQueueReasons=" + this.m + ", disallowInterruptingPlaybackReasons=" + this.n + ", disallowTransferringPlaybackReasons=" + this.o + ", disallowRemoteControlReasons=" + this.f58p + ", disallowInsertingIntoNextTracksReasons=" + this.q + ", disallowInsertingIntoContextTracksReasons=" + this.r + ", disallowReorderingInNextTracksReasons=" + this.s + ", disallowReorderingInContextTracksReasons=" + this.t + ", disallowRemovingFromNextTracksReasons=" + this.u + ", disallowRemovingFromContextTracksReasons=" + this.v + ", disallowUpdatingContextReasons=" + this.w + ", disallowSettingPlaybackSpeedReasons=" + this.x + "}";
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet u() {
        return this.j;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet v() {
        return this.k;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet w() {
        return this.o;
    }

    @Override // com.spotify.player.model.Restrictions
    public final ImmutableSet x() {
        return this.w;
    }
}
