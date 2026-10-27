package com.spotify.player.model;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_ContextIndex extends ContextIndex {
    public final long a;
    public final long b;

    public AutoValue_ContextIndex(long j, long j2) {
        this.a = j;
        this.b = j2;
    }

    @Override // com.spotify.player.model.ContextIndex
    public final long b() {
        return this.a;
    }

    @Override // com.spotify.player.model.ContextIndex
    public final long c() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ContextIndex)) {
            return false;
        }
        ContextIndex contextIndex = (ContextIndex) obj;
        return this.a == contextIndex.b() && this.b == contextIndex.c();
    }

    public final int hashCode() {
        long j = this.a;
        int i = (((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003;
        long j2 = this.b;
        return i ^ ((int) ((j2 >>> 32) ^ j2));
    }

    public final String toString() {
        return "ContextIndex{page=" + this.a + ", track=" + this.b + "}";
    }
}
