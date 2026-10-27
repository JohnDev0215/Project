package com.spotify.player.model;

/* JADX INFO: loaded from: classes.dex */
public abstract class ContextIndex {
    public static ContextIndex a(long j, long j2) {
        return new AutoValue_ContextIndex(j, j2);
    }

    public abstract long b();

    public abstract long c();
}
