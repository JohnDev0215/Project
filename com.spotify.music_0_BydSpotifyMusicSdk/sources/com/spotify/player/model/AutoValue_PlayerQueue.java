package com.spotify.player.model;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.spotify.player.model.PlayerQueue;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_PlayerQueue extends PlayerQueue {
    public final String a;
    public final Optional b;
    public final ImmutableList c;
    public final ImmutableList d;

    public static final class Builder extends PlayerQueue.Builder {
        public String a;
        public Optional b = Optional.a();
        public ImmutableList c;
        public ImmutableList d;

        @Override // com.spotify.player.model.PlayerQueue.Builder
        public final PlayerQueue a() {
            String strH = this.a == null ? " revision" : "";
            if (this.c == null) {
                strH = strH.concat(" nextTracks");
            }
            if (this.d == null) {
                strH = u4.h(strH, " prevTracks");
            }
            if (strH.isEmpty()) {
                return new AutoValue_PlayerQueue(this.a, this.b, this.c, this.d);
            }
            throw new IllegalStateException("Missing required properties:".concat(strH));
        }

        @Override // com.spotify.player.model.PlayerQueue.Builder
        public final PlayerQueue.Builder b(ImmutableList immutableList) {
            if (immutableList == null) {
                throw new NullPointerException("Null nextTracks");
            }
            this.c = immutableList;
            return this;
        }

        @Override // com.spotify.player.model.PlayerQueue.Builder
        public final PlayerQueue.Builder c(ImmutableList immutableList) {
            if (immutableList == null) {
                throw new NullPointerException("Null prevTracks");
            }
            this.d = immutableList;
            return this;
        }

        @Override // com.spotify.player.model.PlayerQueue.Builder
        public final PlayerQueue.Builder d(String str) {
            if (str == null) {
                throw new NullPointerException("Null revision");
            }
            this.a = str;
            return this;
        }

        @Override // com.spotify.player.model.PlayerQueue.Builder
        public final PlayerQueue.Builder e(ContextTrack contextTrack) {
            this.b = Optional.e(contextTrack);
            return this;
        }
    }

    public AutoValue_PlayerQueue(String str, Optional optional, ImmutableList immutableList, ImmutableList immutableList2) {
        this.a = str;
        this.b = optional;
        this.c = immutableList;
        this.d = immutableList2;
    }

    @Override // com.spotify.player.model.PlayerQueue
    public final ImmutableList b() {
        return this.c;
    }

    @Override // com.spotify.player.model.PlayerQueue
    public final ImmutableList c() {
        return this.d;
    }

    @Override // com.spotify.player.model.PlayerQueue
    public final String d() {
        return this.a;
    }

    @Override // com.spotify.player.model.PlayerQueue
    public final PlayerQueue.Builder e() {
        Builder builder = new Builder();
        builder.b = Optional.a();
        builder.a = d();
        builder.b = f();
        builder.c = b();
        builder.d = c();
        return builder;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlayerQueue)) {
            return false;
        }
        PlayerQueue playerQueue = (PlayerQueue) obj;
        return this.a.equals(playerQueue.d()) && this.b.equals(playerQueue.f()) && this.c.equals(playerQueue.b()) && this.d.equals(playerQueue.c());
    }

    @Override // com.spotify.player.model.PlayerQueue
    public final Optional f() {
        return this.b;
    }

    public final int hashCode() {
        return ((((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode();
    }

    public final String toString() {
        return "PlayerQueue{revision=" + this.a + ", track=" + this.b + ", nextTracks=" + this.c + ", prevTracks=" + this.d + "}";
    }
}
