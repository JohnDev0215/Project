package com.spotify.player.model;

import com.google.common.collect.ImmutableSet;
import com.spotify.player.model.PlayOrigin;
import java.util.Set;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_PlayOrigin extends PlayOrigin {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final ImmutableSet g;
    public final String h;

    public static final class Builder extends PlayOrigin.Builder {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public ImmutableSet g;
        public String h;

        @Override // com.spotify.player.model.PlayOrigin.Builder
        public final PlayOrigin a() {
            String strH = this.a == null ? " featureIdentifier" : "";
            if (this.b == null) {
                strH = strH.concat(" featureVersion");
            }
            if (this.c == null) {
                strH = u4.h(strH, " viewUri");
            }
            if (this.d == null) {
                strH = u4.h(strH, " externalReferrer");
            }
            if (this.e == null) {
                strH = u4.h(strH, " referrerIdentifier");
            }
            if (this.f == null) {
                strH = u4.h(strH, " deviceIdentifier");
            }
            if (this.g == null) {
                strH = u4.h(strH, " featureClasses");
            }
            if (this.h == null) {
                strH = u4.h(strH, " restrictionIdentifier");
            }
            if (strH.isEmpty()) {
                return new AutoValue_PlayOrigin(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
            }
            throw new IllegalStateException("Missing required properties:".concat(strH));
        }

        @Override // com.spotify.player.model.PlayOrigin.Builder
        public final PlayOrigin.Builder b(String str) {
            if (str == null) {
                throw new NullPointerException("Null deviceIdentifier");
            }
            this.f = str;
            return this;
        }

        @Override // com.spotify.player.model.PlayOrigin.Builder
        public final PlayOrigin.Builder c(String str) {
            if (str == null) {
                throw new NullPointerException("Null externalReferrer");
            }
            this.d = str;
            return this;
        }

        @Override // com.spotify.player.model.PlayOrigin.Builder
        public final PlayOrigin.Builder d(Set set) {
            this.g = ImmutableSet.o(set);
            return this;
        }

        @Override // com.spotify.player.model.PlayOrigin.Builder
        public final PlayOrigin.Builder e(String str) {
            if (str == null) {
                throw new NullPointerException("Null featureIdentifier");
            }
            this.a = str;
            return this;
        }

        @Override // com.spotify.player.model.PlayOrigin.Builder
        public final PlayOrigin.Builder f(String str) {
            if (str == null) {
                throw new NullPointerException("Null featureVersion");
            }
            this.b = str;
            return this;
        }

        @Override // com.spotify.player.model.PlayOrigin.Builder
        public final PlayOrigin.Builder g(String str) {
            if (str == null) {
                throw new NullPointerException("Null referrerIdentifier");
            }
            this.e = str;
            return this;
        }

        @Override // com.spotify.player.model.PlayOrigin.Builder
        public final PlayOrigin.Builder h(String str) {
            if (str == null) {
                throw new NullPointerException("Null restrictionIdentifier");
            }
            this.h = str;
            return this;
        }

        @Override // com.spotify.player.model.PlayOrigin.Builder
        public final PlayOrigin.Builder i(String str) {
            if (str == null) {
                throw new NullPointerException("Null viewUri");
            }
            this.c = str;
            return this;
        }
    }

    public AutoValue_PlayOrigin(String str, String str2, String str3, String str4, String str5, String str6, ImmutableSet immutableSet, String str7) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = immutableSet;
        this.h = str7;
    }

    @Override // com.spotify.player.model.PlayOrigin
    public final String c() {
        return this.f;
    }

    @Override // com.spotify.player.model.PlayOrigin
    public final String d() {
        return this.d;
    }

    @Override // com.spotify.player.model.PlayOrigin
    public final ImmutableSet e() {
        return this.g;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlayOrigin)) {
            return false;
        }
        PlayOrigin playOrigin = (PlayOrigin) obj;
        return this.a.equals(playOrigin.f()) && this.b.equals(playOrigin.g()) && this.c.equals(playOrigin.k()) && this.d.equals(playOrigin.d()) && this.e.equals(playOrigin.h()) && this.f.equals(playOrigin.c()) && this.g.equals(playOrigin.e()) && this.h.equals(playOrigin.i());
    }

    @Override // com.spotify.player.model.PlayOrigin
    public final String f() {
        return this.a;
    }

    @Override // com.spotify.player.model.PlayOrigin
    public final String g() {
        return this.b;
    }

    @Override // com.spotify.player.model.PlayOrigin
    public final String h() {
        return this.e;
    }

    public final int hashCode() {
        return ((((((((((((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode()) * 1000003) ^ this.f.hashCode()) * 1000003) ^ this.g.hashCode()) * 1000003) ^ this.h.hashCode();
    }

    @Override // com.spotify.player.model.PlayOrigin
    public final String i() {
        return this.h;
    }

    @Override // com.spotify.player.model.PlayOrigin
    public final PlayOrigin.Builder j() {
        Builder builder = new Builder();
        builder.a = f();
        builder.b = g();
        builder.c = k();
        builder.d = d();
        builder.e = h();
        builder.f = c();
        builder.g = e();
        builder.h = i();
        return builder;
    }

    @Override // com.spotify.player.model.PlayOrigin
    public final String k() {
        return this.c;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PlayOrigin{featureIdentifier=");
        sb.append(this.a);
        sb.append(", featureVersion=");
        sb.append(this.b);
        sb.append(", viewUri=");
        sb.append(this.c);
        sb.append(", externalReferrer=");
        sb.append(this.d);
        sb.append(", referrerIdentifier=");
        sb.append(this.e);
        sb.append(", deviceIdentifier=");
        sb.append(this.f);
        sb.append(", featureClasses=");
        sb.append(this.g);
        sb.append(", restrictionIdentifier=");
        return u4.m(sb, this.h, "}");
    }
}
