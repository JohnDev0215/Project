package H4;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f2807a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i f2808b;
    public final i c;
    public final i d;

    public j(f fVar, f fVar2, f fVar3, int i7) {
        fVar = (i7 & 1) != 0 ? new f(com.bumptech.glide.d.c(4278190080L)) : fVar;
        h hVar = h.f2806a;
        i iVar = (i7 & 4) != 0 ? hVar : fVar2;
        i iVar2 = (i7 & 8) != 0 ? hVar : fVar3;
        A7.m.f(fVar, "dark");
        A7.m.f(iVar, "ball");
        A7.m.f(iVar2, "frame");
        this.f2807a = fVar;
        this.f2808b = hVar;
        this.c = iVar;
        this.d = iVar2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        return A7.m.a(this.f2807a, jVar.f2807a) && A7.m.a(this.f2808b, jVar.f2808b) && A7.m.a(this.c, jVar.c) && A7.m.a(this.d, jVar.d);
    }

    public final int hashCode() {
        return this.d.hashCode() + ((this.c.hashCode() + ((this.f2808b.hashCode() + (this.f2807a.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "QrVectorColors(dark=" + this.f2807a + ", light=" + this.f2808b + ", ball=" + this.c + ", frame=" + this.d + ')';
    }
}
