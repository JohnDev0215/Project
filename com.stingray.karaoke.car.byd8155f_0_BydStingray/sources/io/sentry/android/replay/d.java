package io.sentry.android.replay;

import java.io.File;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final File f16387a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f16388b;
    public final long c;

    public d(File file, int i7, long j3) {
        this.f16387a = file;
        this.f16388b = i7;
        this.c = j3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return A7.m.a(this.f16387a, dVar.f16387a) && this.f16388b == dVar.f16388b && this.c == dVar.c;
    }

    public final int hashCode() {
        return Long.hashCode(this.c) + AbstractC2641j.b(this.f16388b, this.f16387a.hashCode() * 31, 31);
    }

    public final String toString() {
        return "GeneratedVideo(video=" + this.f16387a + ", frameCount=" + this.f16388b + ", duration=" + this.c + ')';
    }
}
