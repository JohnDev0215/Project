package x6;

import A7.m;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f23797a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f23798b;
    public final f c;
    public final d d;

    public c(b bVar, e eVar, f fVar, d dVar) {
        m.f(eVar, "timingConfig");
        this.f23797a = bVar;
        this.f23798b = eVar;
        this.c = fVar;
        this.d = dVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return m.a(this.f23797a, cVar.f23797a) && m.a(this.f23798b, cVar.f23798b) && m.a(this.c, cVar.c) && m.a(this.d, cVar.d);
    }

    public final int hashCode() {
        return this.d.hashCode() + ((this.c.hashCode() + ((this.f23798b.hashCode() + (this.f23797a.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "LyricsConfigs(colorConfig=" + this.f23797a + ", timingConfig=" + this.f23798b + ", uiConfig=" + this.c + ", layoutConfig=" + this.d + ")";
    }
}
