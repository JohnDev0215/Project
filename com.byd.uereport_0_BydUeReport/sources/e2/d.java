package e2;

/* JADX INFO: compiled from: FieldWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f4541a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4542b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f4543c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f4544d;

    public d(c cVar, int i8, String str, String str2) {
        if (cVar.f4537o == null) {
            cVar.f4537o = this;
        } else {
            cVar.f4538p.f4541a = this;
        }
        cVar.f4538p = this;
        this.f4542b = i8;
        this.f4543c = cVar.h(str);
        this.f4544d = cVar.h(str2);
    }

    public int a() {
        return 8;
    }

    public void b(a aVar) {
        aVar.g(this.f4542b & (-393217)).g(this.f4543c).g(this.f4544d);
        aVar.g(0);
    }

    public void c() {
    }
}
