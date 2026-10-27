package com.spotify.player.model;

import com.spotify.player.model.PlayerError;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_PlayerError extends PlayerError {
    public final ErrorType a;
    public final String b;
    public final String c;
    public final String d;

    public static final class Builder extends PlayerError.Builder {
        public ErrorType a;
        public String b;
        public String c;
        public String d;

        @Override // com.spotify.player.model.PlayerError.Builder
        public final PlayerError a() {
            String strH = this.a == null ? " error" : "";
            if (this.b == null) {
                strH = strH.concat(" trackUri");
            }
            if (this.c == null) {
                strH = u4.h(strH, " contextUri");
            }
            if (this.d == null) {
                strH = u4.h(strH, " reasons");
            }
            if (strH.isEmpty()) {
                return new AutoValue_PlayerError(this.a, this.b, this.c, this.d);
            }
            throw new IllegalStateException("Missing required properties:".concat(strH));
        }

        @Override // com.spotify.player.model.PlayerError.Builder
        public final PlayerError.Builder b(String str) {
            this.c = str;
            return this;
        }

        @Override // com.spotify.player.model.PlayerError.Builder
        public final PlayerError.Builder c(String str) {
            this.d = str;
            return this;
        }

        @Override // com.spotify.player.model.PlayerError.Builder
        public final PlayerError.Builder d(String str) {
            this.b = str;
            return this;
        }
    }

    public AutoValue_PlayerError(ErrorType errorType, String str, String str2, String str3) {
        this.a = errorType;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    @Override // com.spotify.player.model.PlayerError
    public final String b() {
        return this.c;
    }

    @Override // com.spotify.player.model.PlayerError
    public final ErrorType c() {
        return this.a;
    }

    @Override // com.spotify.player.model.PlayerError
    public final String d() {
        return this.d;
    }

    @Override // com.spotify.player.model.PlayerError
    public final String e() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlayerError)) {
            return false;
        }
        PlayerError playerError = (PlayerError) obj;
        return this.a.equals(playerError.c()) && this.b.equals(playerError.e()) && this.c.equals(playerError.b()) && this.d.equals(playerError.d());
    }

    public final int hashCode() {
        return ((((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PlayerError{error=");
        sb.append(this.a);
        sb.append(", trackUri=");
        sb.append(this.b);
        sb.append(", contextUri=");
        sb.append(this.c);
        sb.append(", reasons=");
        return u4.m(sb, this.d, "}");
    }
}
