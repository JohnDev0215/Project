package com.spotify.player.model;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.spotify.player.model.ContextPage;
import java.util.Map;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_ContextPage extends ContextPage {
    public final String a;
    public final String b;
    public final Optional c;
    public final ImmutableMap d;

    public static final class Builder extends ContextPage.Builder {
        public String a;
        public String b;
        public Optional c = Optional.a();
        public ImmutableMap d;

        @Override // com.spotify.player.model.ContextPage.Builder
        public final ContextPage a() {
            String strH = this.a == null ? " pageUrl" : "";
            if (this.b == null) {
                strH = strH.concat(" nextPageUrl");
            }
            if (this.d == null) {
                strH = u4.h(strH, " metadata");
            }
            if (strH.isEmpty()) {
                return new AutoValue_ContextPage(this.a, this.b, this.c, this.d);
            }
            throw new IllegalStateException("Missing required properties:".concat(strH));
        }

        @Override // com.spotify.player.model.ContextPage.Builder
        public final ContextPage.Builder b(Map map) {
            this.d = ImmutableMap.b(map);
            return this;
        }

        @Override // com.spotify.player.model.ContextPage.Builder
        public final ContextPage.Builder c(String str) {
            this.b = str;
            return this;
        }

        @Override // com.spotify.player.model.ContextPage.Builder
        public final ContextPage.Builder d(String str) {
            this.a = str;
            return this;
        }
    }

    public AutoValue_ContextPage(String str, String str2, Optional optional, ImmutableMap immutableMap) {
        this.a = str;
        this.b = str2;
        this.c = optional;
        this.d = immutableMap;
    }

    @Override // com.spotify.player.model.ContextPage
    public final ImmutableMap b() {
        return this.d;
    }

    @Override // com.spotify.player.model.ContextPage
    public final String c() {
        return this.b;
    }

    @Override // com.spotify.player.model.ContextPage
    public final String d() {
        return this.a;
    }

    @Override // com.spotify.player.model.ContextPage
    public final Optional e() {
        return this.c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ContextPage)) {
            return false;
        }
        ContextPage contextPage = (ContextPage) obj;
        return this.a.equals(contextPage.d()) && this.b.equals(contextPage.c()) && this.c.equals(contextPage.e()) && this.d.equals(contextPage.b());
    }

    public final int hashCode() {
        return ((((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode();
    }

    public final String toString() {
        return "ContextPage{pageUrl=" + this.a + ", nextPageUrl=" + this.b + ", tracks=" + this.c + ", metadata=" + this.d + "}";
    }
}
