package G4;

import D4.e;
import D4.f;
import F4.d;
import H4.j;
import H4.n;
import H4.v;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f2675a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f2676b;
    public final v c;
    public final j d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final n f2677e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final H4.a f2678f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final f f2679g;

    public c(float f10, d dVar, v vVar, j jVar, n nVar, H4.a aVar, f fVar) {
        this.f2675a = f10;
        this.f2676b = dVar;
        this.c = vVar;
        this.d = jVar;
        this.f2677e = nVar;
        this.f2678f = aVar;
        this.f2679g = fVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (!Float.valueOf(this.f2675a).equals(Float.valueOf(cVar.f2675a)) || !this.f2676b.equals(cVar.f2676b) || !this.c.equals(cVar.c)) {
            return false;
        }
        Object obj2 = F4.a.f2410b;
        return obj2.equals(obj2) && this.d.equals(cVar.d) && this.f2677e.equals(cVar.f2677e) && this.f2678f.equals(cVar.f2678f) && this.f2679g.equals(cVar.f2679g);
    }

    public final int hashCode() {
        return this.f2679g.hashCode() + ((e.Auto.hashCode() + ((this.f2678f.hashCode() + ((this.f2677e.hashCode() + ((this.d.hashCode() + ((F4.a.f2410b.hashCode() + ((this.c.hashCode() + ((this.f2676b.hashCode() + (Float.hashCode(this.f2675a) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 961);
    }

    public final String toString() {
        return "QrVectorOptions(padding=" + this.f2675a + ", offset=" + this.f2676b + ", shapes=" + this.c + ", codeShape=" + F4.a.f2410b + ", colors=" + this.d + ", logo=" + this.f2677e + ", background=" + this.f2678f + ", errorCorrectionLevel=" + e.Auto + ", fourthEyeEnabled=false, highlighting=" + this.f2679g + ')';
    }
}
