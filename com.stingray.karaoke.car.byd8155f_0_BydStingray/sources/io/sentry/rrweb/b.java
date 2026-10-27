package io.sentry.rrweb;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    public c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f16836m = System.currentTimeMillis();

    public b(c cVar) {
        this.l = cVar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f16836m == bVar.f16836m && this.l == bVar.l;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.l, Long.valueOf(this.f16836m)});
    }
}
