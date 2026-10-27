package b.a.a.i;

/* JADX INFO: compiled from: FieldWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f198a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f199b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f200c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f201d;

    public d(c cVar, int i, String str, String str2) {
        if (cVar.o == null) {
            cVar.o = this;
        } else {
            cVar.p.f198a = this;
        }
        cVar.p = this;
        this.f199b = i;
        this.f200c = cVar.h(str);
        this.f201d = cVar.h(str2);
    }

    public int a() {
        return 8;
    }

    public void b(a aVar) {
        aVar.g(this.f199b & (-393217)).g(this.f200c).g(this.f201d);
        aVar.g(0);
    }

    public void c() {
    }
}
