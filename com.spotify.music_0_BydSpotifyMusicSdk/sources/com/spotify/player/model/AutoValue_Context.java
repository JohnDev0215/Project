package com.spotify.player.model;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.spotify.player.model.Context;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_Context extends Context {
    public final String a;
    public final String b;
    public final ImmutableMap c;
    public final Optional d;
    public final Optional e;

    public static final class Builder extends Context.Builder {
        public String a;
        public String b;
        public ImmutableMap c;
        public Optional d = Optional.a();
        public Optional e = Optional.a();

        @Override // com.spotify.player.model.Context.Builder
        public final Context a() {
            String strH = this.a == null ? " uri" : "";
            if (this.b == null) {
                strH = strH.concat(" url");
            }
            if (this.c == null) {
                strH = u4.h(strH, " metadata");
            }
            if (strH.isEmpty()) {
                return new AutoValue_Context(this.a, this.b, this.c, this.d, this.e);
            }
            throw new IllegalStateException("Missing required properties:".concat(strH));
        }

        @Override // com.spotify.player.model.Context.Builder
        public final Context.Builder b(ImmutableMap immutableMap) {
            if (immutableMap == null) {
                throw new NullPointerException("Null metadata");
            }
            this.c = immutableMap;
            return this;
        }

        @Override // com.spotify.player.model.Context.Builder
        public final Context.Builder c(ImmutableList immutableList) {
            this.d = Optional.e(immutableList);
            return this;
        }

        @Override // com.spotify.player.model.Context.Builder
        public final Context.Builder d(Restrictions restrictions) {
            this.e = Optional.e(restrictions);
            return this;
        }

        @Override // com.spotify.player.model.Context.Builder
        public final Context.Builder e(String str) {
            if (str == null) {
                throw new NullPointerException("Null url");
            }
            this.b = str;
            return this;
        }
    }

    public AutoValue_Context(String str, String str2, ImmutableMap immutableMap, Optional optional, Optional optional2) {
        this.a = str;
        this.b = str2;
        this.c = immutableMap;
        this.d = optional;
        this.e = optional2;
    }

    @Override // com.spotify.player.model.Context
    public final ImmutableMap d() {
        return this.c;
    }

    @Override // com.spotify.player.model.Context
    public final Optional e() {
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Context)) {
            return false;
        }
        Context context = (Context) obj;
        return this.a.equals(context.h()) && this.b.equals(context.i()) && this.c.equals(context.d()) && this.d.equals(context.e()) && this.e.equals(context.f());
    }

    @Override // com.spotify.player.model.Context
    public final Optional f() {
        return this.e;
    }

    @Override // com.spotify.player.model.Context
    public final Context.Builder g() {
        Builder builder = new Builder();
        builder.d = Optional.a();
        builder.e = Optional.a();
        builder.a = h();
        builder.b = i();
        builder.c = d();
        builder.d = e();
        builder.e = f();
        return builder;
    }

    @Override // com.spotify.player.model.Context
    public final String h() {
        return this.a;
    }

    public final int hashCode() {
        return ((((((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode();
    }

    @Override // com.spotify.player.model.Context
    public final String i() {
        return this.b;
    }

    public final String toString() {
        return "Context{uri=" + this.a + ", url=" + this.b + ", metadata=" + this.c + ", pages=" + this.d + ", restrictions=" + this.e + "}";
    }
}
