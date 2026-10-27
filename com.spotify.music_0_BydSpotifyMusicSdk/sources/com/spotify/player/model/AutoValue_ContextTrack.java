package com.spotify.player.model;

import com.google.common.collect.ImmutableMap;
import com.spotify.player.model.ContextTrack;
import java.util.Map;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_ContextTrack extends ContextTrack {
    public final String b;
    public final String c;
    public final ImmutableMap d;
    public final String e;

    public static final class Builder extends ContextTrack.Builder {
        public String a;
        public String b;
        public ImmutableMap c;
        public String d;

        @Override // com.spotify.player.model.ContextTrack.Builder
        public final ContextTrack a() {
            String strH = this.a == null ? " uri" : "";
            if (this.b == null) {
                strH = strH.concat(" uid");
            }
            if (this.c == null) {
                strH = u4.h(strH, " metadata");
            }
            if (this.d == null) {
                strH = u4.h(strH, " provider");
            }
            if (strH.isEmpty()) {
                return new AutoValue_ContextTrack(this.a, this.b, this.c, this.d);
            }
            throw new IllegalStateException("Missing required properties:".concat(strH));
        }

        @Override // com.spotify.player.model.ContextTrack.Builder
        public final ContextTrack.Builder b(Map map) {
            this.c = ImmutableMap.b(map);
            return this;
        }

        @Override // com.spotify.player.model.ContextTrack.Builder
        public final ContextTrack.Builder c(String str) {
            if (str == null) {
                throw new NullPointerException("Null provider");
            }
            this.d = str;
            return this;
        }

        @Override // com.spotify.player.model.ContextTrack.Builder
        public final ContextTrack.Builder d(String str) {
            if (str == null) {
                throw new NullPointerException("Null uid");
            }
            this.b = str;
            return this;
        }

        @Override // com.spotify.player.model.ContextTrack.Builder
        public final ContextTrack.Builder e(String str) {
            if (str == null) {
                throw new NullPointerException("Null uri");
            }
            this.a = str;
            return this;
        }
    }

    public AutoValue_ContextTrack(String str, String str2, ImmutableMap immutableMap, String str3) {
        this.b = str;
        this.c = str2;
        this.d = immutableMap;
        this.e = str3;
    }

    @Override // com.spotify.player.model.ContextTrack
    public final ImmutableMap b() {
        return this.d;
    }

    @Override // com.spotify.player.model.ContextTrack
    public final String c() {
        return this.e;
    }

    @Override // com.spotify.player.model.ContextTrack
    public final ContextTrack.Builder d() {
        Builder builder = new Builder();
        builder.a = f();
        builder.b = e();
        builder.c = b();
        builder.d = c();
        return builder;
    }

    @Override // com.spotify.player.model.ContextTrack
    public final String e() {
        return this.c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ContextTrack)) {
            return false;
        }
        ContextTrack contextTrack = (ContextTrack) obj;
        return this.b.equals(contextTrack.f()) && this.c.equals(contextTrack.e()) && this.d.equals(contextTrack.b()) && this.e.equals(contextTrack.c());
    }

    @Override // com.spotify.player.model.ContextTrack
    public final String f() {
        return this.b;
    }

    public final int hashCode() {
        return ((((((this.b.hashCode() ^ 1000003) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ContextTrack{uri=");
        sb.append(this.b);
        sb.append(", uid=");
        sb.append(this.c);
        sb.append(", metadata=");
        sb.append(this.d);
        sb.append(", provider=");
        return u4.m(sb, this.e, "}");
    }
}
