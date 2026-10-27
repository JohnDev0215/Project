package D4;

import A7.m;
import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f1188a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f1189b;
    public final c c;
    public final float d;

    public f() {
        b bVar = b.f1181a;
        this.f1188a = bVar;
        this.f1189b = bVar;
        this.c = bVar;
        this.d = 0.75f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return m.a(this.f1188a, fVar.f1188a) && m.a(this.f1189b, fVar.f1189b) && m.a(this.c, fVar.c) && Float.valueOf(this.d).equals(Float.valueOf(fVar.d));
    }

    public final int hashCode() {
        return Float.hashCode(this.d) + ((this.c.hashCode() + ((this.f1189b.hashCode() + (this.f1188a.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("QrHighlighting(cornerEyes=");
        sb.append(this.f1188a);
        sb.append(", versionEyes=");
        sb.append(this.f1189b);
        sb.append(", timingLines=");
        sb.append(this.c);
        sb.append(", alpha=");
        return AbstractC1347d.h(sb, this.d, ')');
    }
}
