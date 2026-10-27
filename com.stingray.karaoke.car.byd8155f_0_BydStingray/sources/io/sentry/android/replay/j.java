package io.sentry.android.replay;

import h2.AbstractC1347d;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final File f16412a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f16413b;
    public final String c;

    public j(File file, long j3, String str) {
        this.f16412a = file;
        this.f16413b = j3;
        this.c = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        return A7.m.a(this.f16412a, jVar.f16412a) && this.f16413b == jVar.f16413b && A7.m.a(this.c, jVar.c);
    }

    public final int hashCode() {
        int iC = AbstractC1347d.c(this.f16412a.hashCode() * 31, 31, this.f16413b);
        String str = this.c;
        return iC + (str == null ? 0 : str.hashCode());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ReplayFrame(screenshot=");
        sb.append(this.f16412a);
        sb.append(", timestamp=");
        sb.append(this.f16413b);
        sb.append(", screen=");
        return android.hardware.bydauto.ac.tsimpl.a.k(sb, this.c, ')');
    }
}
