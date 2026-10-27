package q1;

import java.io.File;
import java.util.List;
import o1.d;
import q1.f;
import u1.n;

/* JADX INFO: compiled from: ResourceCacheGenerator.java */
/* JADX INFO: loaded from: classes.dex */
class w implements f, d.a<Object> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final f.a f9116e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final g<?> f9117g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f9118h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f9119i = -1;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private n1.f f9120j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private List<u1.n<File, ?>> f9121k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f9122l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private volatile n.a<?> f9123m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private File f9124n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private x f9125o;

    w(g<?> gVar, f.a aVar) {
        this.f9117g = gVar;
        this.f9116e = aVar;
    }

    private boolean a() {
        return this.f9122l < this.f9121k.size();
    }

    @Override // q1.f
    public boolean b() {
        List<n1.f> listC = this.f9117g.c();
        boolean z6 = false;
        if (listC.isEmpty()) {
            return false;
        }
        List<Class<?>> listM = this.f9117g.m();
        if (listM.isEmpty()) {
            if (File.class.equals(this.f9117g.q())) {
                return false;
            }
            throw new IllegalStateException("Failed to find any load path from " + this.f9117g.i() + " to " + this.f9117g.q());
        }
        while (true) {
            if (this.f9121k != null && a()) {
                this.f9123m = null;
                while (!z6 && a()) {
                    List<u1.n<File, ?>> list = this.f9121k;
                    int i7 = this.f9122l;
                    this.f9122l = i7 + 1;
                    this.f9123m = list.get(i7).b(this.f9124n, this.f9117g.s(), this.f9117g.f(), this.f9117g.k());
                    if (this.f9123m != null && this.f9117g.t(this.f9123m.f9615c.a())) {
                        this.f9123m.f9615c.c(this.f9117g.l(), this);
                        z6 = true;
                    }
                }
                return z6;
            }
            int i8 = this.f9119i + 1;
            this.f9119i = i8;
            if (i8 >= listM.size()) {
                int i9 = this.f9118h + 1;
                this.f9118h = i9;
                if (i9 >= listC.size()) {
                    return false;
                }
                this.f9119i = 0;
            }
            n1.f fVar = listC.get(this.f9118h);
            Class<?> cls = listM.get(this.f9119i);
            this.f9125o = new x(this.f9117g.b(), fVar, this.f9117g.o(), this.f9117g.s(), this.f9117g.f(), this.f9117g.r(cls), cls, this.f9117g.k());
            File fileB = this.f9117g.d().b(this.f9125o);
            this.f9124n = fileB;
            if (fileB != null) {
                this.f9120j = fVar;
                this.f9121k = this.f9117g.j(fileB);
                this.f9122l = 0;
            }
        }
    }

    @Override // q1.f
    public void cancel() {
        n.a<?> aVar = this.f9123m;
        if (aVar != null) {
            aVar.f9615c.cancel();
        }
    }

    @Override // o1.d.a
    public void d(Exception exc) {
        this.f9116e.e(this.f9125o, exc, this.f9123m.f9615c, n1.a.RESOURCE_DISK_CACHE);
    }

    @Override // o1.d.a
    public void f(Object obj) {
        this.f9116e.a(this.f9120j, obj, this.f9123m.f9615c, n1.a.RESOURCE_DISK_CACHE, this.f9125o);
    }
}
