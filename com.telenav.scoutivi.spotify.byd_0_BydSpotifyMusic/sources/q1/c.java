package q1;

import java.io.File;
import java.util.List;
import o1.d;
import q1.f;
import u1.n;

/* JADX INFO: compiled from: DataCacheGenerator.java */
/* JADX INFO: loaded from: classes.dex */
class c implements f, d.a<Object> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final List<n1.f> f8950e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final g<?> f8951g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final f.a f8952h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f8953i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private n1.f f8954j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private List<u1.n<File, ?>> f8955k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f8956l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private volatile n.a<?> f8957m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private File f8958n;

    c(g<?> gVar, f.a aVar) {
        this(gVar.c(), gVar, aVar);
    }

    private boolean a() {
        return this.f8956l < this.f8955k.size();
    }

    @Override // q1.f
    public boolean b() {
        while (true) {
            boolean z6 = false;
            if (this.f8955k != null && a()) {
                this.f8957m = null;
                while (!z6 && a()) {
                    List<u1.n<File, ?>> list = this.f8955k;
                    int i7 = this.f8956l;
                    this.f8956l = i7 + 1;
                    this.f8957m = list.get(i7).b(this.f8958n, this.f8951g.s(), this.f8951g.f(), this.f8951g.k());
                    if (this.f8957m != null && this.f8951g.t(this.f8957m.f9615c.a())) {
                        this.f8957m.f9615c.c(this.f8951g.l(), this);
                        z6 = true;
                    }
                }
                return z6;
            }
            int i8 = this.f8953i + 1;
            this.f8953i = i8;
            if (i8 >= this.f8950e.size()) {
                return false;
            }
            n1.f fVar = this.f8950e.get(this.f8953i);
            File fileB = this.f8951g.d().b(new d(fVar, this.f8951g.o()));
            this.f8958n = fileB;
            if (fileB != null) {
                this.f8954j = fVar;
                this.f8955k = this.f8951g.j(fileB);
                this.f8956l = 0;
            }
        }
    }

    @Override // q1.f
    public void cancel() {
        n.a<?> aVar = this.f8957m;
        if (aVar != null) {
            aVar.f9615c.cancel();
        }
    }

    @Override // o1.d.a
    public void d(Exception exc) {
        this.f8952h.e(this.f8954j, exc, this.f8957m.f9615c, n1.a.DATA_DISK_CACHE);
    }

    @Override // o1.d.a
    public void f(Object obj) {
        this.f8952h.a(this.f8954j, obj, this.f8957m.f9615c, n1.a.DATA_DISK_CACHE, this.f8954j);
    }

    c(List<n1.f> list, g<?> gVar, f.a aVar) {
        this.f8953i = -1;
        this.f8950e = list;
        this.f8951g = gVar;
        this.f8952h = aVar;
    }
}
