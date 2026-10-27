package A9;

import A7.m;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g f478a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f479b;
    public final LinkedHashMap c;
    public final LinkedHashMap d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f480e;

    public f(g gVar, String str, LinkedHashMap linkedHashMap, LinkedHashMap linkedHashMap2, Object obj) {
        this.f478a = gVar;
        this.f479b = str;
        this.c = linkedHashMap;
        this.d = linkedHashMap2;
        this.f480e = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return this.f478a == fVar.f478a && this.f479b.equals(fVar.f479b) && this.c.equals(fVar.c) && this.d.equals(fVar.d) && m.a(this.f480e, fVar.f480e);
    }

    public final int hashCode() {
        int iHashCode = (this.d.hashCode() + ((this.c.hashCode() + M5.b.c(this.f479b, this.f478a.hashCode() * 31, 31)) * 31)) * 31;
        Object obj = this.f480e;
        return iHashCode + (obj == null ? 0 : obj.hashCode());
    }

    public final String toString() {
        return "RequestConfig(method=" + this.f478a + ", path=" + this.f479b + ", headers=" + this.c + ", query=" + this.d + ", body=" + this.f480e + ")";
    }
}
